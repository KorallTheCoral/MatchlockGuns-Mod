package com.korallkarlsson.matchlockweapons.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class NewReloadGunMatchlock extends NewReloadGun {

	public NewReloadGunMatchlock(String name, float inAccuracy, float damage, int maxShots, int cooldown,
			double kickback, double failRate, int reloadCooldown, boolean useRamRod, Item ammoItem, int gunPowderAmount,
			Item cartridgeItem, int durabillity) {
		super(name, inAccuracy, damage, maxShots, cooldown, kickback, failRate, reloadCooldown, useRamRod, ammoItem,
				gunPowderAmount, cartridgeItem, durabillity);
	}
	
	public NewReloadGunMatchlock(String name, float inAccuracy, float damage, int maxShots, int cooldown,
			double kickback, double failRate, int reloadCooldown, boolean useRamRod, Item ammoItem, int gunPowderAmount,
			Item cartridgeItem, int numberOfShots, int durabillity) {
		super(name, inAccuracy, damage, maxShots, cooldown, kickback, failRate, reloadCooldown, useRamRod, ammoItem,
				gunPowderAmount, cartridgeItem, durabillity);
		this.numberOfShots = numberOfShots;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
	
		if(stack.hasTagCompound())
		{
		if(stack.getTagCompound().hasKey("loadedshots") && stack.getTagCompound().hasKey("loadedshots"))
		{
			AddLore(stack, tooltip, flagIn);
		}
		else
		{
			stack.getTagCompound().setInteger("loadedshots", 0);
			stack.getTagCompound().setInteger("step", 0);
		}
		}
		else
		{
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setInteger("loadedshots", 0);
			nbt.setInteger("step", 0);
			nbt.setBoolean("lit", false);
			stack.setTagCompound(nbt);
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		
		
		if(!playerIn.isInWater() && playerIn.getHeldItemMainhand().hasTagCompound() && !worldIn.isRemote && handIn == EnumHand.MAIN_HAND && playerIn.getHeldItemMainhand().getItem() == this && playerIn.getHeldItem(EnumHand.MAIN_HAND).getItem() == this && playerIn.getActiveHand() == EnumHand.MAIN_HAND)
		{
		ItemStack mainItem = playerIn.getHeldItemMainhand();
		ItemStack offHand = playerIn.getHeldItemOffhand();
			
		if(offHand.getItem() instanceof RepairItem)
		{
				RepairItem kit = (RepairItem) offHand.getItem();
				
				if(kit.type == this.getType())
				{
					mainItem.getTagCompound().setInteger("damage", 0);
					offHand.setCount(0);
					
					Vec3d pos = playerIn.getPositionVector();
					
					worldIn.playSound(null, pos.x, pos.y, pos.z, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.PLAYERS, 0.5f, 1.3f);
				}
		}
		if(!containsReloadItem(playerIn.getHeldItemOffhand().getItem()) && playerIn.getHeldItemMainhand().getTagCompound().getInteger("loadedshots") > 0 && playerIn.getHeldItemMainhand().getTagCompound().getInteger("cooldown") <= 0 && playerIn.getHeldItemOffhand().isEmpty() && (!playerIn.isSneaking() || playerIn.getHeldItemMainhand().getTagCompound().getInteger("step") != 3) && playerIn.getHeldItemMainhand().getTagCompound().getBoolean("lit"))
		{
			if(Math.random() > failRate && playerIn.getHeldItemMainhand().getItemDamage() < this.getMaxDamage())
			{
			Fire(worldIn, playerIn, handIn);
			}
			else
			{
			Jam(worldIn, playerIn, handIn);
			}
		}
		else if(!worldIn.isRemote && playerIn.getHeldItemMainhand().getTagCompound().getBoolean("lit") == false && playerIn.getHeldItemOffhand().getItem() == Items.FLINT_AND_STEEL && !playerIn.isWet())
		{
			worldIn.playSound(null, playerIn.getPositionVector().x, playerIn.getPositionVector().y, playerIn.getPositionVector().z, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS, 1, 1);
			playerIn.getHeldItemMainhand().getTagCompound().setBoolean("lit", true);
		}
		else if(playerIn.getHeldItemMainhand().getTagCompound().getInteger("loadedshots") < this.maxShots && playerIn.getHeldItemMainhand().getTagCompound().getInteger("cooldown") <= 0)
		{
			if(!Load(playerIn, worldIn) && playerIn.getHeldItemOffhand().isEmpty() && playerIn.getHeldItemMainhand().getTagCompound().getInteger("loadedshots") == 0)
			{
				worldIn.playSound(null, playerIn.getPositionVector().x, playerIn.getPositionVector().y, playerIn.getPositionVector().z, SoundEvents.BLOCK_WOOD_BUTTON_CLICK_ON, SoundCategory.PLAYERS, 1, 1);
			}
		}
		else if(!worldIn.isRemote && playerIn.getHeldItemMainhand().getTagCompound().getInteger("loadedshots") == 0 && playerIn.getHeldItemOffhand().isEmpty())
		{
			worldIn.playSound(null, playerIn.getPositionVector().x, playerIn.getPositionVector().y, playerIn.getPositionVector().z, SoundEvents.BLOCK_WOOD_BUTTON_CLICK_ON, SoundCategory.PLAYERS, 1, 1);
		}
		}
		
	return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		
		if(stack.hasTagCompound() && !worldIn.isRemote)
		{
			if(stack.getTagCompound().hasKey("lit"))
			{
				if(stack.getTagCompound().getBoolean("lit") && Math.random() > 0.8)
				{
					Entity player = entityIn;
					Vec3d pos = player.getPositionVector();
					WorldServer server = worldIn.getMinecraftServer().getWorld(player.dimension);
					server.spawnParticle(EnumParticleTypes.FLAME, pos.x + player.getLookVec().x, pos.y + player.getLookVec().y + 1, pos.z + player.getLookVec().z, 1, 0d, 0d, 0d, 0d, 0);		
					
					if(Math.random() > 0.98 || entityIn.isWet())
					{
						worldIn.playSound(null, player.getPositionVector().x, player.getPositionVector().y, player.getPositionVector().z, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 0.5f, 1.5f);
						server.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.x + player.getLookVec().x, pos.y + player.getLookVec().y + 1, pos.z + player.getLookVec().z, 5, 0d, 0d, 0d, 0.01d, 0);		
						stack.getTagCompound().setBoolean("lit", false);
					}
				
				}
			}
		}
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
	}
}
