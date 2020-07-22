package com.korallkarlsson.matchlockweapons;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

import com.korallkarlsson.matchlockweapons.config.BoeConfig;
import com.korallkarlsson.matchlockweapons.creativetabs.ModTab;
import com.korallkarlsson.matchlockweapons.init.EntityInit;
import com.korallkarlsson.matchlockweapons.init.ModItems;
import com.korallkarlsson.matchlockweapons.init.ModRecipes;
import com.korallkarlsson.matchlockweapons.proxy.CommonProxy;
import com.korallkarlsson.matchlockweapons.util.Reference;
import com.korallkarlsson.matchlockweapons.util.handlers.ModCraftingEventHandler;
import com.korallkarlsson.matchlockweapons.util.handlers.RenderHandler;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {

	@Instance
	public static Main instance;

	public static final ModTab GUN_TAB = new ModTab("guns", ModItems.MATCHLOCK_GUN);
	public static final ModTab MATERIALS_TAB = new ModTab("gun_materials", ModItems.GOLD_MECHANICAL_PARTS);
	public static final ModTab MISC_TAB = new ModTab("gun_misc", ModItems.GUN_RAM_ROD);
	public static final ModTab CANNON_TAB = new ModTab("cannons", ModItems.BULLET_PACK);
	
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event)
	{
		EntityInit.RegisterEntities();
		BoeConfig.init();
		
		if(event.getSide() == Side.CLIENT)
		{
			RenderHandler.registerEntityRenderers();
		}
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		ModRecipes.init();
		MinecraftForge.EVENT_BUS.register(new ModCraftingEventHandler());
	}
	
	@EventHandler
	public static void Postinit(FMLPostInitializationEvent event)
	{
	
	}
	
	
}