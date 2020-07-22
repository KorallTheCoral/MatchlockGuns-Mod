package com.korallkarlsson.matchlockweapons.creativetabs;

import com.korallkarlsson.matchlockweapons.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModTab extends CreativeTabs {

	
	Item icon;
	
	public ModTab(String label, Item iconItem) {
		super(label);
		icon = iconItem;
		
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(icon);
	}
	
	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(icon);
	}
	
	

}
