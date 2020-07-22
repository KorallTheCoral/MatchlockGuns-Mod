package com.korallkarlsson.matchlockweapons.compat.JEI.lathe;

import java.awt.List;
import java.util.ArrayList;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class LatheRecipeWrapper implements IRecipeWrapper {

	ItemStack input;
	ItemStack tool;
	ItemStack output;
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		
		ArrayList<ItemStack> inputs = new ArrayList<ItemStack>();
		inputs.add(input);
		inputs.add(tool);
		
		ingredients.setInputs(ItemStack.class, inputs);
		ingredients.setOutput(ItemStack.class, output);
	}

}
