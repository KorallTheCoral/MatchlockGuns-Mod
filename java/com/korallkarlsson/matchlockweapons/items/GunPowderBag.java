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
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class GunPowderBag extends Item implements IHasModel {

	static int maxGunPowder = 256;
	
	public GunPowderBag(String name, CreativeTabs tab, int maxStackSize)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		this.maxStackSize = maxStackSize;
		ModItems.ITEMS.add(this);
	}
	
	public GunPowderBag(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.MISC_TAB);
		
		ModItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		
		Main.proxy.registerItemRenderer(this, 0, "inventory");
		
	}
	
	@Override
	public CreativeTabs getCreativeTab() {
		return Main.MISC_TAB;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
	
		if(stack.hasTagCompound())
		{
		if(stack.getTagCompound().hasKey("gunpowder"))
		{
			tooltip.add("Contains " + stack.getTagCompound().getInteger("gunpowder") + " gunpowder");
		}
		else
		{
			stack.getTagCompound().setInteger("gunpowder", 0);
		}
		}
		else
		{
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setInteger("gunpowder", 0);
			stack.setTagCompound(nbt);
		}
	}
	
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		
		if(playerIn.getHeldItemOffhand().getItem() == Items.GUNPOWDER && playerIn.getHeldItemMainhand().hasTagCompound())
		{
			ItemStack mainItem = playerIn.getHeldItemMainhand();
			ItemStack offHand = playerIn.getHeldItemOffhand();
			int amount = offHand.getCount();
			int gunpowder = mainItem.getTagCompound().getInteger("gunpowder");
			
			if(gunpowder + amount < maxGunPowder)
			{
				playerIn.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, ItemStack.EMPTY);
				gunpowder += amount;
			}
			else
			{
				amount = amount - (this.maxGunPowder - gunpowder);
				gunpowder = this.maxGunPowder;
				playerIn.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.GUNPOWDER, amount));
			}
			
			mainItem.getTagCompound().setInteger("gunpowder", gunpowder);
			playerIn.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, mainItem);
		}
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
}
