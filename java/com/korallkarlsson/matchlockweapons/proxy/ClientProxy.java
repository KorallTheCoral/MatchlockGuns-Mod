package com.korallkarlsson.matchlockweapons.proxy;

import com.korallkarlsson.matchlockweapons.entities.Bullet;
import com.korallkarlsson.matchlockweapons.util.handlers.RenderHandler;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ClientProxy extends CommonProxy{

	public void registerItemRenderer(Item item, int meta, String id)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}	
	

}
