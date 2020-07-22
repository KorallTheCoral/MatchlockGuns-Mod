package com.korallkarlsson.matchlockweapons.items;

import com.korallkarlsson.matchlockweapons.Main;
import com.korallkarlsson.matchlockweapons.init.ModItems;
import com.korallkarlsson.matchlockweapons.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class DummyItem extends Item implements IHasModel {

	public DummyItem(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(null);
		
		ModItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		
		Main.proxy.registerItemRenderer(this, 0, "inventory");
		
	}
	
}
