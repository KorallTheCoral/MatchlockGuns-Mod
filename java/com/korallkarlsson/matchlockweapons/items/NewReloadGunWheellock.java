package com.korallkarlsson.matchlockweapons.items;

import java.util.List;

import com.korallkarlsson.matchlockweapons.init.ModItems;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class NewReloadGunWheellock extends NewReloadGun {

	int minSpring = 5;
	
	public NewReloadGunWheellock(String name, float inAccuracy, float damage, int maxShots, int cooldown,
			double kickback, double failRate, int reloadCooldown, boolean useRamRod, Item ammoItem, int gunPowderAmount,
			Item cartridgeItem, int springAmount, int durabillity) {
		super(name, inAccuracy, damage, maxShots, cooldown, kickback, failRate, reloadCooldown, useRamRod, ammoItem,
				gunPowderAmount, cartridgeItem, durabillity);
		this.minSpring = springAmount;
	}
	
	public NewReloadGunWheellock(String name, float inAccuracy, float damage, int maxShots, int cooldown,
			double kickback, double failRate, int reloadCooldown, boolean useRamRod, Item ammoItem, int gunPowderAmount,
			Item cartridgeItem, int springAmount, boolean canDual, int durabillity) {
		super(name, inAccuracy, damage, maxShots, cooldown, kickback, failRate, reloadCooldown, useRamRod, ammoItem,
				gunPowderAmount, cartridgeItem, canDual, durabillity);
		this.minSpring = springAmount;
	}
	
	public NewReloadGunWheellock(String name, float inAccuracy, float damage, int maxShots, int cooldown,
			double kickback, double failRate, int reloadCooldown, boolean useRamRod, Item ammoItem, int gunPowderAmount,
			Item cartridgeItem, int springAmount, int numberOfShots, int durabillity) {
		super(name, inAccuracy, damage, maxShots, cooldown, kickback, failRate, reloadCooldown, useRamRod, ammoItem,
				gunPowderAmount, cartridgeItem, durabillity);
		this.numberOfShots = numberOfShots;
		this.minSpring = springAmount;
	}


	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
	
		if(stack.hasTagCompound())
		{
		if(stack.getTagCompound().hasKey("loadedshots") && stack.getTagCompound().hasKey("step"))
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
			nbt.setInteger("spring", 0);
			stack.setTagCompound(nbt);
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		EnumHand otherHand = EnumHand.OFF_HAND;
		if(handIn == EnumHand.MAIN_HAND)
		{
			otherHand = EnumHand.OFF_HAND;
		}
		else if(handIn == EnumHand.OFF_HAND)
		{
			otherHand = EnumHand.MAIN_HAND;
		}
		
		ItemStack mainItem = playerIn.getHeldItem(handIn);
		ItemStack offHand = playerIn.getHeldItem(otherHand);
		
		int load1 = 0;
		int load2 = 0;
		int cooldown = 0;
		
		boolean changedW = false;
		
		if(mainItem.hasTagCompound() && offHand.hasTagCompound() && mainItem.getItem() instanceof NewReloadGun && offHand.getItem() instanceof NewReloadGun)
		{
			load1 = offHand.getTagCompound().getInteger("loadedshots");
			load2 = mainItem.getTagCompound().getInteger("loadedshots");
			cooldown = offHand.getTagCompound().getInteger("cooldown");
			changedW = true;
		}
		
		int maxCooldown = 0;
		
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
		if(offHand.getItem() instanceof NewReloadGun)
		{
			NewReloadGun gun = (NewReloadGun) offHand.getItem();
			maxCooldown = gun.cooldown;
		}
		
		if(load2 > load1 && cooldown == 0)
		{
			preFire(handIn, playerIn, mainItem, worldIn, offHand);
		}
		else if(load2 > load1 && maxCooldown != 0)
		{
			if(cooldown != maxCooldown)
			{
			preFire(handIn, playerIn, mainItem, worldIn, offHand);
			}
		}
		else if(load1 == load2 && handIn == EnumHand.MAIN_HAND)
		{
			preFire(handIn, playerIn, mainItem, worldIn, offHand);
		}
		else if(changedW == false)
		{
			preFire(handIn, playerIn, mainItem, worldIn, offHand);
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}
	
	
	
	EnumActionResult preFire(EnumHand handIn, EntityPlayer playerIn, ItemStack mainItem, World worldIn, ItemStack offHand)
	{
		if(handIn == EnumHand.MAIN_HAND || canDual)
		{
		if(!playerIn.isInWater() && mainItem.hasTagCompound() && !worldIn.isRemote)
		{
		if(!containsReloadItem(offHand.getItem()) && mainItem.getTagCompound().getInteger("loadedshots") > 0 && mainItem.getTagCompound().getInteger("cooldown") <= 0 && (!playerIn.isSneaking() || mainItem.getTagCompound().getInteger("step") != 3) && !playerIn.isSneaking() && mainItem.getTagCompound().getInteger("spring") >= minSpring)
		{
			if(Math.random() > failRate && mainItem.getItemDamage() < this.getMaxDamage())
			{
			mainItem.getTagCompound().setInteger("spring", 0);
			Fire(worldIn, playerIn, handIn);
			}
			else
			{
			mainItem.getTagCompound().setInteger("spring", 0);
			Jam(worldIn, playerIn, handIn);
			}
		}
		else if(playerIn.getHeldItemOffhand().isEmpty() && !worldIn.isRemote && mainItem.getTagCompound().getInteger("cooldown") <= 0 && playerIn.isSneaking() && mainItem.getTagCompound().getInteger("spring") < minSpring)
		{
			int spring = mainItem.getTagCompound().getInteger("spring");
			mainItem.getTagCompound().setInteger("spring", spring + 1);
			worldIn.playSound(null, playerIn.getPositionVector().x, playerIn.getPositionVector().y, playerIn.getPositionVector().z, SoundEvents.BLOCK_TRIPWIRE_CLICK_ON, SoundCategory.PLAYERS, 0.2f, 0.5f);
		}
		else if(mainItem.getTagCompound().getInteger("loadedshots") < this.maxShots && mainItem.getTagCompound().getInteger("cooldown") <= 0 && !playerIn.isSneaking())
		{
			if(!Load(playerIn, worldIn) && offHand.isEmpty() && mainItem.getTagCompound().getInteger("loadedshots") == 0)
			{
				mainItem.getTagCompound().setInteger("spring", 0);
				worldIn.playSound(null, playerIn.getPositionVector().x, playerIn.getPositionVector().y, playerIn.getPositionVector().z, SoundEvents.BLOCK_WOOD_BUTTON_CLICK_ON, SoundCategory.PLAYERS, 1, 1);
			}
		}
		else if(!playerIn.isSneaking() && !worldIn.isRemote && mainItem.getTagCompound().getInteger("loadedshots") == 0)
		{
			mainItem.getTagCompound().setInteger("spring", 0);
			worldIn.playSound(null, playerIn.getPositionVector().x, playerIn.getPositionVector().y, playerIn.getPositionVector().z, SoundEvents.BLOCK_WOOD_BUTTON_CLICK_ON, SoundCategory.PLAYERS, 1, 1);
		}
		}
		
	
	}
		return EnumActionResult.FAIL;
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
					
					if(Math.random() > 0.95 || entityIn.isWet())
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
	
	@Override
	boolean Load(EntityPlayer player, World worldIn)
	{
		ItemStack item = player.getHeldItemMainhand();
		ItemStack offHandItem = player.getHeldItemOffhand();
		boolean changed = false;
		int step = item.getTagCompound().getInteger("step");
		if(step == 0 && offHandItem.getItem() == this.cartridgeItem && this.cartridgeItem != null)
		{
			if(this.useRamRod)
			{
			step += 2;
			}
			else
			{
			step += 3;
			}
			changed = true;
			player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(this.cartridgeItem, offHandItem.getCount() - 1));
		}
		else if(step == 0 && offHandItem.getItem() == Items.GUNPOWDER && offHandItem.getCount() == this.gunPowderAmount)
		{
			step ++;
			changed = true;
			player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, ItemStack.EMPTY);
			worldIn.playSound(null, player.getPositionVector().x, player.getPositionVector().y, player.getPositionVector().z, SoundEvents.BLOCK_SAND_PLACE, SoundCategory.PLAYERS, 1, 1);
		}
		else if(step == 0 && offHandItem.getItem() == ModItems.GUNPOWDER_BAG && offHandItem.hasTagCompound())
		{
			if(offHandItem.getTagCompound().getInteger("gunpowder") >= this.gunPowderAmount)
			{
				int value = offHandItem.getTagCompound().getInteger("gunpowder");
				offHandItem.getTagCompound().setInteger("gunpowder", value - this.gunPowderAmount);
				player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, offHandItem);
				step ++;
				changed = true;
				worldIn.playSound(null, player.getPositionVector().x, player.getPositionVector().y, player.getPositionVector().z, SoundEvents.BLOCK_SAND_PLACE, SoundCategory.PLAYERS, 1, 1);
			}
		}
		else if(step == 1 && offHandItem.getItem() == this.ammoItem)
		{
			if(this.useRamRod)
			{
			step ++;
			}
			else
			{
			step += 2;
			}
			changed = true;
			player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(this.ammoItem, offHandItem.getCount() - 1));
			worldIn.playSound(null, player.getPositionVector().x, player.getPositionVector().y, player.getPositionVector().z, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1, 1f);
		}
		else if(step == 2 && offHandItem.getItem() == ModItems.GUN_RAM_ROD)
		{
			step += 2;
			changed = true;
			worldIn.playSound(null, player.getPositionVector().x, player.getPositionVector().y, player.getPositionVector().z, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1, 0.5f);
		}
		else if(step == 3 && player.isSneaking() && offHandItem.isEmpty())
		{
			step ++;
			changed = true;
			worldIn.playSound(null, player.getPositionVector().x, player.getPositionVector().y, player.getPositionVector().z, SoundEvents.BLOCK_TRIPWIRE_CLICK_OFF, SoundCategory.PLAYERS, 1, 1);
		}
			
		if(changed)
		{
		
		if(step > 3)
		{
			step = 0;
			int loadedShots = item.getTagCompound().getInteger("loadedshots") + 1;
			item.getTagCompound().setInteger("loadedshots", loadedShots);
			PotionEffect slow = new PotionEffect(MobEffects.SLOWNESS, this.reloadCooldown, 2, true, false);
			
			player.addPotionEffect(slow);
			
			item.getTagCompound().setInteger("cooldown", this.reloadCooldown);
		}
		else
		{
			PotionEffect slow = new PotionEffect(MobEffects.SLOWNESS, this.reloadCooldown, 2, true, false);
			if(this.gunPowderAmount != 1)
			{
			slow = new PotionEffect(MobEffects.SLOWNESS, this.reloadCooldown, 254, true, false);
			}
			
			player.addPotionEffect(slow);
			
			item.getTagCompound().setInteger("cooldown", this.reloadCooldown);
		}
		item.getTagCompound().setInteger("step", step);
		player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, item);
		}
		
		return changed;
	}
	
}
