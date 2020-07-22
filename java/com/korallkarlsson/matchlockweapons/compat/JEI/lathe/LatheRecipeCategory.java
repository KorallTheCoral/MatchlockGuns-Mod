package com.korallkarlsson.matchlockweapons.compat.JEI.lathe;
import com.korallkarlsson.matchlockweapons.util.Reference;

import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;

public class LatheRecipeCategory implements IRecipeCategory {

	@Override
	public String getUid() {
		return Reference.MOD_ID + ".lathe";
	}

	@Override
	public String getTitle() {
		return "Lathe";
	}

	@Override
	public String getModName() {
		return Reference.NAME;
	}

	@Override
	public IDrawable getBackground() {
	return null;
	}
	
	

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		recipeLayout.setShapeless();
	}

}
