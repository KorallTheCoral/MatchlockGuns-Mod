package com.korallkarlsson.matchlockweapons.items;

import com.korallkarlsson.matchlockweapons.Main;
import com.korallkarlsson.matchlockweapons.init.ModItems;
import com.korallkarlsson.matchlockweapons.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {

	
	CreativeTabs tab = Main.MATERIALS_TAB;
	int i = 0;
	
	
	public ItemBase(String name, CreativeTabs tab, int maxStackSize)
	{
		this.tab = tab;
		this.setCreativeTab(tab);
		setUnlocalizedName(name);
		setRegistryName(name);
		this.maxStackSize = maxStackSize;
		ModItems.ITEMS.add(this);
		
		if(tab == Main.MISC_TAB)
		{
			i = 1;
		}
		
	}
	
	public ItemBase(String name)
	{
		
		this.setCreativeTab(Main.MISC_TAB);
		setUnlocalizedName(name);
		setRegistryName(name);
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public CreativeTabs getCreativeTab() {
		if(tab == CreativeTabs.MATERIALS)
		{
			return Main.MATERIALS_TAB;	
		}
		return Main.MISC_TAB;
	}
	
	@Override
	public Item setCreativeTab(CreativeTabs tab) {
		this.tab = tab;
		return this;
	}

	@Override
	public void registerModels() {
		
		Main.proxy.registerItemRenderer(this, 0, "inventory");
		
	}
	
}
