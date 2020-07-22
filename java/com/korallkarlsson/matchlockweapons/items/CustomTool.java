package com.korallkarlsson.matchlockweapons.items;

import com.korallkarlsson.matchlockweapons.Main;
import com.korallkarlsson.matchlockweapons.init.ModItems;
import com.korallkarlsson.matchlockweapons.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CustomTool extends Item implements IHasModel {

	public CustomTool(String name, int maxDamage)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(1);
		setMaxDamage(maxDamage);
		ModItems.ITEMS.add(this);
		this.setCreativeTab(Main.MISC_TAB);
	}
	
	@Override
	public CreativeTabs getCreativeTab() {
		return Main.MISC_TAB;
	}
	
	@Override
	public void registerModels() {
		
		Main.proxy.registerItemRenderer(this, 0, "inventory");
		
	}

}
