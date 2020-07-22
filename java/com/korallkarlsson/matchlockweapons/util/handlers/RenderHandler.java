package com.korallkarlsson.matchlockweapons.util.handlers;

import com.korallkarlsson.matchlockweapons.entities.EntityCannon;
import com.korallkarlsson.matchlockweapons.entities.EntityHeavyCannon;
import com.korallkarlsson.matchlockweapons.entities.EntityPuckleCannon;
import com.korallkarlsson.matchlockweapons.entities.EntityVolleyCannon;
import com.korallkarlsson.matchlockweapons.entities.render.RenderCannon;
import com.korallkarlsson.matchlockweapons.entities.render.RenderHeavyCannon;
import com.korallkarlsson.matchlockweapons.entities.render.RenderPuckleCannon;
import com.korallkarlsson.matchlockweapons.entities.render.RenderVolleyCannon;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

	public static void registerEntityRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityCannon.class, new IRenderFactory<EntityCannon>()
				{
					@Override
					public Render<? super EntityCannon> createRenderFor(RenderManager manager) {
						return new RenderCannon(manager);
					}
				});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityHeavyCannon.class, new IRenderFactory<EntityHeavyCannon>()
		{
			@Override
			public Render<? super EntityHeavyCannon> createRenderFor(RenderManager manager) {
				return new RenderHeavyCannon(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityVolleyCannon.class, new IRenderFactory<EntityVolleyCannon>()
		{
			@Override
			public Render<? super EntityVolleyCannon> createRenderFor(RenderManager manager) {
				return new RenderVolleyCannon(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityPuckleCannon.class, new IRenderFactory<EntityPuckleCannon>()
		{
			@Override
			public Render<? super EntityPuckleCannon> createRenderFor(RenderManager manager) {
				return new RenderPuckleCannon(manager);
			}
		});
		
	}
	
}
