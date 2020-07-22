package com.korallkarlsson.matchlockweapons.compat.JEI;

import java.util.Collection;
import java.util.IllegalFormatException;

import com.korallkarlsson.matchlockweapons.compat.JEI.lathe.LatheRecipeCategory;
import com.korallkarlsson.matchlockweapons.init.ModBlocks;
import com.korallkarlsson.matchlockweapons.init.ModItems;
import com.korallkarlsson.matchlockweapons.items.NewReloadGun;
import com.korallkarlsson.matchlockweapons.items.NewReloadGunMatchlock;
import com.korallkarlsson.matchlockweapons.items.NewReloadGunWheellock;
import com.korallkarlsson.matchlockweapons.util.Reference;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

@JEIPlugin
public class JEICompat implements IModPlugin {

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		final IJeiHelpers helpers = registry.getJeiHelpers();
		final IGuiHelper gui = helpers.getGuiHelper();
		
		//registry.addRecipeCategories(new LatheRecipeCategory());
	}
	
	@Override
	public void register(IModRegistry registry) {

		
		//registry.addRecipeCatalyst(new ItemStack(ModBlocks.LATHE), Reference.MOD_ID + ".lathe");
		Collection collection;
		
		registry.addDescription(new ItemStack(ModBlocks.LATHE), "Used to craft various mechanical parts by holding an ingredient in your mainhand and a chisel in your offhand");
		
		for(Item item : ModItems.ITEMS)
		{
			if(item instanceof NewReloadGun)
			{
				NewReloadGun gun = (NewReloadGun) item;
				
				String guntype = "flintlock";
				if(gun instanceof NewReloadGunWheellock)
				{
					guntype = "wheellock";
				}
				else if(gun instanceof NewReloadGunMatchlock)
				{
					guntype = "matchlock";
				}
				else if(gun.noReloadSeperate)
				{
					guntype = "caplock";
				}
				
				String hand = "two-handed";
				
				if(gun.canDual)
				{
					hand = "one-handed";
				}
				
				if(gun.maxShots != 1)
				{
				registry.addDescription(new ItemStack(item), "A " + hand + " " + guntype + " gun that can shoot " + gun.maxShots + " times, each shot dealing " + gun.damage + " damage");
				}
				else
				{
					registry.addDescription(new ItemStack(item), "A " + hand + " " + guntype + " gun that deals " + gun.damage + " damage");	
				}
			}
		}
		
		IModPlugin.super.register(registry);
	}
	
	public static String translateToLocal(String key)
	{
		if(I18n.canTranslate(key)) return I18n.translateToLocal(key);
		else return I18n.translateToFallback(key);
	}
	
	public static String translateToLocalFormatted(String key, Object ... format)
	{
		String s = translateToLocal(key);
		try {
			return String.format(s, format);
		} catch (IllegalFormatException e)
		{
			return "Format error: " + s;
		}
	}
	
}
