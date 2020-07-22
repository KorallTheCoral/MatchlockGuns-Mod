package com.korallkarlsson.matchlockweapons.recipes;

import com.korallkarlsson.matchlockweapons.init.ModRecipes;
import com.korallkarlsson.matchlockweapons.items.CustomTool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LatheRecipe {

	public Item inPutItem;
	public CustomTool tool;
	public Item outPutItem;
	
	public LatheRecipe(Item input, Item output, CustomTool tool)
	{
		this.inPutItem = input;
		this.outPutItem = output;
		this.tool = tool;
		
		ModRecipes.LATHE_RECIPES.add(this);
	}
	
}
