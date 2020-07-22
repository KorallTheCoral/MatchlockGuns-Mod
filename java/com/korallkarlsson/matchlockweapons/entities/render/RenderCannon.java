package com.korallkarlsson.matchlockweapons.entities.render;

import com.korallkarlsson.matchlockweapons.entities.EntityCannon;
import com.korallkarlsson.matchlockweapons.init.ModItems;
import com.korallkarlsson.matchlockweapons.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBoat;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderCannon extends Render {

	
	ItemStack stackIron = new ItemStack(Blocks.IRON_BLOCK);
	EntityItem itemIron = new EntityItem(Minecraft.getMinecraft().world, 0D, 0D, 0D, stackIron);
	
	ItemStack stackWood = new ItemStack(Blocks.PLANKS);
	EntityItem itemWood = new EntityItem(Minecraft.getMinecraft().world, 0D, 0D, 0D, stackWood);
	
	EntityItem itemBarrel = new EntityItem(Minecraft.getMinecraft().world, 0D, 0D, 0D, new ItemStack(ModItems.CANNON_BARREL));
	EntityItem itemWheel = new EntityItem(Minecraft.getMinecraft().world, 0D, 0D, 0D, new ItemStack(ModItems.CANNON_WHEEL));
	
	
	public RenderCannon(RenderManager renderManagerIn) {
		super(renderManagerIn);

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		

		//super.doRender(entity, x, y, z, entity.rotationYaw, partialTicks);
		
		this.itemIron.hoverStart = 0F;
		this.itemWood.hoverStart = 0F;
		this.itemBarrel.hoverStart = 0F;
		this.itemWheel.hoverStart = 0F;
		
        GlStateManager.pushMatrix();
        {
        	GlStateManager.translate(x, y - (0.9), z);
        	GlStateManager.scale(4f, 4f, 4f);
        	GlStateManager.rotate(-entityYaw, 0, 1, 0);
        	GlStateManager.translate(0.20/2, 0, 0);
        	Minecraft.getMinecraft().getRenderManager().renderEntity(itemWheel, 0D, 0D, 0D, 0F, 0F, false);
        	GlStateManager.translate(-0.20, 0, 0);
        	Minecraft.getMinecraft().getRenderManager().renderEntity(itemWheel, 0D, 0D, 0D, 0F, 0F, false);
        	
        	GlStateManager.translate(0.20/2, 0.5D, 0.08D); //0.12
        	//GlStateManager.scale(3/4, 3/4, 3/4);
        	GlStateManager.rotate(entity.rotationPitch + 180F, 1, 0, 0);
        	GlStateManager.translate(0, -0.25D, 0);
        	Minecraft.getMinecraft().getRenderManager().renderEntity(itemBarrel, 0D, 0D, 0D, 0F, 0F, false);
        	
        	/*
        	GlStateManager.translate(0.25/2, 0, 0);
 
        	Minecraft.getMinecraft().getRenderManager().renderEntity(itemWood, 0D, 0D, 0D, 0F, 0F, false);
        	GlStateManager.translate(-0.25, 0, 0);
        	Minecraft.getMinecraft().getRenderManager().renderEntity(itemWood, 0D, 0D, 0D, 0F, 0F, false);
        	
        	GlStateManager.translate(0.25/2, 0.5, -0.1);
        	GlStateManager.rotate(entity.rotationPitch, 1, 0, 0);
        	GlStateManager.translate(0, -0.25, 0);
        	Minecraft.getMinecraft().getRenderManager().renderEntity(itemIron, 0D, 0D, 0D, 0F, 0F, false);
        	GlStateManager.translate(0, 0, 0.25);
        	Minecraft.getMinecraft().getRenderManager().renderEntity(itemIron, 0D, 0D, 0D, 0F, 0F, false);
        	GlStateManager.translate(0, 0, 0.25);
        	Minecraft.getMinecraft().getRenderManager().renderEntity(itemIron, 0D, 0D, 0D, 0F, 0F, false);
        	*/
        }
        GlStateManager.popMatrix();
        //renderOffsetAABB(entity.getEntityBoundingBox(), x - entity.lastTickPosX, y - entity.lastTickPosY, z - entity.lastTickPosZ);
        
		
	}
	

	
}
