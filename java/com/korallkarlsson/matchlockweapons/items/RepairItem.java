package com.korallkarlsson.matchlockweapons.items;

import java.util.List;

import com.korallkarlsson.matchlockweapons.Main;
import com.korallkarlsson.matchlockweapons.items.ItemEnums.GunTypeEnum;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RepairItem extends ItemBase {

	public GunTypeEnum type;
	
	public RepairItem(String name, GunTypeEnum type) {
		super(name, Main.MISC_TAB, 1);
		this.type = type;
		this.setCreativeTab(Main.MISC_TAB);
	}
	
	@Override
	public CreativeTabs getCreativeTab() {
		return Main.MISC_TAB;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		

		tooltip.add("Used to repair " + type.toString().toLowerCase() + " guns");
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
}
