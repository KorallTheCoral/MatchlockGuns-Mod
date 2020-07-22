package com.korallkarlsson.matchlockweapons.init;

import java.util.ArrayList;
import java.util.List;

import com.korallkarlsson.matchlockweapons.items.ReloadGun;
import com.korallkarlsson.matchlockweapons.recipes.LatheRecipe;
import com.korallkarlsson.matchlockweapons.util.Reference;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {

	public static final List<LatheRecipe> LATHE_RECIPES = new ArrayList<LatheRecipe>();
	
	public static final LatheRecipe RECIPE_SMOOTH_BARREL = new LatheRecipe(ModItems.METAL_ROD, ModItems.SMOOTH_BARREL, ModItems.CHISEL);
	public static final LatheRecipe RECIPE_RIFLED_BARREL = new LatheRecipe(ModItems.METAL_ROD, ModItems.RIFLED_BARREL, ModItems.DIAMOND_CHISEL);
	public static final LatheRecipe RECIPE_IRON_MECHANICAL_PARTS = new LatheRecipe(Items.IRON_INGOT, ModItems.IRON_MECHANICAL_PARTS, ModItems.CHISEL);
	public static final LatheRecipe RECIPE_GOLD_MECHANICAL_PARTS = new LatheRecipe(Items.IRON_INGOT, ModItems.GOLD_MECHANICAL_PARTS, ModItems.DIAMOND_CHISEL);
	public static final LatheRecipe RECIPE_DIAMOND_MECHANICAL_PARTS = new LatheRecipe(Items.GOLD_INGOT, ModItems.DIAMOND_MECHANICAL_PARTS, ModItems.DIAMOND_CHISEL);
	public static final LatheRecipe EMERALD_DIAMOND_MECHANICAL_PARTS = new LatheRecipe(Items.DIAMOND, ModItems.EMERALD_MECHANICAL_PARTS, ModItems.DIAMOND_CHISEL);
	
	public static final LatheRecipe SPRING = new LatheRecipe(Items.IRON_NUGGET, ModItems.SPRING, ModItems.CHISEL);
	
	
	public static void init()
	{
		//createReloadRecipes(); --Legacy system
	}


	static void createReloadRecipes()
	{
		ReloadGun[] guns = new ReloadGun[0];
		
		Object[] params = {"PB", "G", "", "P", new ItemStack(Items.GUNPOWDER), "B", new ItemStack(Items.IRON_NUGGET), "G", null};
		
		
		for(ReloadGun gun : guns)
		{
			ItemStack inputItem = new ItemStack(gun);
			inputItem.setTagCompound(new NBTTagCompound());
			
			ItemStack outputItem = inputItem;
			
			for(int i = 0; i < gun.maxShots; i++)
			{
			inputItem.getTagCompound().setInteger("loadedshots", i);
			outputItem.getTagCompound().setInteger("loadedshots", i+1);
			
			params[7] = inputItem;
			
			//GameRegistry.addShapedRecipe(new ResourceLocation(Reference.MOD_ID + gun.getRegistryName() + "reloadrecipe" + i), new ResourceLocation(Reference.MOD_ID + "reloadguns"), outputItem, "PB", "G", 'P', new ItemStack(Items.GUNPOWDER), 'B', new ItemStack(Items.IRON_NUGGET), 'G', inputItem);
			
			GameRegistry.addShapedRecipe(new ResourceLocation(Reference.MOD_ID, outputItem.getItem().getRegistryName().getResourcePath()+"_reloadrecipe_" + i), null, outputItem, "PB", "G ", 'P', new ItemStack(Items.GUNPOWDER), 'B', new ItemStack(Items.IRON_NUGGET), 'G', inputItem);
			System.out.println("Registered: " + outputItem.getItem().getRegistryName().getResourcePath()+"_reloadrecipe_" + i);
			
			}
		}
		
	}
	
}
