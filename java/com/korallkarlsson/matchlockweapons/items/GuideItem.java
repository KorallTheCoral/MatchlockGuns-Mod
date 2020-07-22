package com.korallkarlsson.matchlockweapons.items;

import java.util.List;

import com.korallkarlsson.matchlockweapons.Main;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GuideItem extends ItemBase {

	int type;
	
	public GuideItem(String name, int type) {
		super(name, Main.MISC_TAB, 1);
		this.type = type;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public CreativeTabs getCreativeTab() {
		return Main.MISC_TAB;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		
		if(type == 0)
		{
		tooltip.add("To fire a flintlock gun:");
		tooltip.add("1. Right click with the gun in your mainhand with the exact amount of gunpowder required for your gun (1-3)");
		tooltip.add("2. Right click with the gun in your mainhand with a roundshot in your offhand");
		tooltip.add("3. Right click the gun with a gun ramrod in your offhand, unless your gun does not require a ramrod");
		tooltip.add("4. Sneak and right click the gun with with an empty offhand to arm it");
		tooltip.add("Repeat this if your gun can hold multiple shots");
		tooltip.add("Right click with an empty offhand to shoot");
		}
		else if(type == 1)
		{
		tooltip.add("To fire a wheellock gun:");
		tooltip.add("1. Right click with the gun in your mainhand with the exact amount of gunpowder required for your gun (1-3)");
		tooltip.add("2. Right click with the gun in your mainhand with a roundshot in your offhand");
		tooltip.add("3. Right click the gun with a gun ramrod in your offhand, unless your gun does not require a ramrod");
		tooltip.add("Repeat this if your gun can hold multiple shots");
		tooltip.add("4. Sneak and hold right click with your gun until it stops making noises");
		tooltip.add("Right click with an empty offhand to shoot");
		tooltip.add("Note that every time the gun is shot, it needs to be wound up again");
		}
		else if(type == 2)
		{
		tooltip.add("To fire a matchlock gun:");
		tooltip.add("1. Right click with the gun in your mainhand with the exact amount of gunpowder required for your gun (1-3)");
		tooltip.add("2. Right click with the gun in your mainhand with a roundshot in your offhand");
		tooltip.add("3. Right click the gun with a gun ramrod in your offhand, unless your gun does not require a ramrod");
		tooltip.add("4. Sneak and right click the gun with with an empty offhand to arm it");
		tooltip.add("Repeat this if your gun can hold multiple shots");
		tooltip.add("5. Right click the gun with a flint and steel in your offhand");
		tooltip.add("Right click with an empty offhand to shoot, if your gun is still lit");
		}
		else if(type == 3)
		{
		tooltip.add("Paper cartridges:");
		tooltip.add("Paper cartridges replace both the need for a roundshot and gunpowder");
		tooltip.add("Simply right click your gun with the right cartridge in your offhand to skip step 1 and 2 of loading a gun");
		tooltip.add("");
		tooltip.add("Gunpowder bag:");
		tooltip.add("The gunpowder bag can hold your gunpowder and load your guns");
		tooltip.add("It replaces step 2 of loading a gun");
		tooltip.add("To fill the bag, right click it with gunpowder in your offhand");
		}
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
}
