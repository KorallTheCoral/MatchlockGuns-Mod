package com.korallkarlsson.matchlockweapons.items;

import java.util.List;

import com.korallkarlsson.matchlockweapons.Main;
import com.korallkarlsson.matchlockweapons.init.ModItems;
import com.korallkarlsson.matchlockweapons.util.IHasModel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class Arquebus extends Item implements IHasModel {

		private ArquebusPrimed primedGun;
	
		public Arquebus(String name)
		{
			setUnlocalizedName(name);
			setRegistryName(name);
			setCreativeTab(CreativeTabs.COMBAT);
			setMaxStackSize(1);
			ModItems.ITEMS.add(this);
		}
		
		public Arquebus(String name, String primedName, float damage, int numberOfShots, double inAccuracyMultiplier, double kickback)
		{
			setUnlocalizedName(name);
			setRegistryName(name);
			setCreativeTab(CreativeTabs.COMBAT);
			setMaxStackSize(1);
			this.primedGun = new ArquebusPrimed(primedName, damage, numberOfShots, inAccuracyMultiplier, kickback, this);
			
			
			ModItems.ITEMS.add(this);
		}


		
		@Override
		public void registerModels() {
			
			Main.proxy.registerItemRenderer(this, 0, "inventory");
			
		}
		
		
		@Override
		public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		
			if(stack.hasTagCompound())
			{
			if(stack.getTagCompound().hasKey("loaded"))
			{
				if(stack.getTagCompound().getBoolean("loaded"))
				{
					tooltip.add("Loaded");
				}
			}
			else
			{
				stack.getTagCompound().setBoolean("loaded", false);
			}
			}
			else
			{
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setBoolean("loaded", false);
				stack.setTagCompound(nbt);
			}
		}
		
		
		private void Ignite(EntityPlayer player, EnumHand hand, World world)
		{
			ItemStack offHandItem = player.getHeldItemOffhand();
			ItemStack mainHandItem = player.getHeldItemMainhand();
			if(mainHandItem.hasTagCompound())
			{
			if(offHandItem.getItem() == Items.FLINT_AND_STEEL && !world.isRainingAt(player.getPosition()) && !player.isInWater() && mainHandItem.getTagCompound().getBoolean("loaded"))
			{
			player.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(primedGun, 1));
			int damage = offHandItem.getItemDamage() + 1;
			if(damage >= offHandItem.getMaxDamage())
			{
				offHandItem = ItemStack.EMPTY;
			}
			else
			{
				offHandItem.setItemDamage(damage);
			}
			player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, offHandItem);
			}
			}
		}
		
		@Override
		public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
			if(worldIn.isRemote == false)
			{
			Ignite(playerIn, handIn, worldIn);	
			}
		return super.onItemRightClick(worldIn, playerIn, handIn);
		}
		
}
