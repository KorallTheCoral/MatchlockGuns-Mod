package com.korallkarlsson.matchlockweapons.entities;

import com.korallkarlsson.matchlockweapons.Main;
import com.korallkarlsson.matchlockweapons.config.BoeConfig;
import com.korallkarlsson.matchlockweapons.util.IHasModel;

import net.minecraft.command.CommandParticle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class Bullet extends EntityThrowable implements IHasModel {
	
	public float damage = 0;
	private boolean explode = false;
	private Entity user;
	private float explodeStrength = 0;
	
	public Bullet(World worldIn, double x, double y, double z, float damage, Entity user) {
		super(worldIn, x, y, z);
		this.damage = damage;
		this.ignoreEntity = user;
		
		
	}
	
	public Bullet(World worldIn, double x, double y, double z, float damage, Entity user, boolean explode, float explodeStrength) {
		super(worldIn, x, y, z);
		this.damage = damage;
		this.ignoreEntity = user;
		this.explode = explode;
		this.explodeStrength = explodeStrength;
	}
	
	@Override
	public void onUpdate() {
		Vec3d pos = this.getPositionVector();
		WorldServer server = this.world.getMinecraftServer().getWorld(this.dimension);
		//server.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.x, pos.y, pos.z, 4, 0d, 0d, 0d, 0.01d, 0);	
		if(this.explode && this.ticksExisted > 1)
		{
			if(this.explodeStrength > 0.5f)
			{
				server.spawnParticle(EnumParticleTypes.CLOUD, true, pos.x, pos.y, pos.z, 10, 0.2d, 0.2d, 0.2d, 0.01d, 0);
			}
			else
			{	
				server.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, true, pos.x, pos.y, pos.z, 1, 0d, 0d, 0d, 0.01d, 0);	
			}
		}
		else if(this.ticksExisted > 1)
		{
			double ticks = this.ticksExisted;
			server.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, true, pos.x, pos.y, pos.z, 1, 0, 0, (double) 0, 0.01, 0);	
		}
		super.onUpdate();
	}
	
	@Override
	public boolean isEntityAlive() {
		return false;
	}
	
	@Override
	public boolean canBeCollidedWith() {

		return false;
	}
	
	@Override
	public String getName() {
		return "Bullet";
	}
	
	@Override
	protected void onImpact(RayTraceResult result) {
		
		
		Entity entity = result.entityHit;
		
		if(entity instanceof Bullet == false)
		{
			
		if(entity != user)
		{
		
			if(explode && explodeStrength > 0)
			{
				world.createExplosion(this, result.hitVec.x, result.hitVec.y, result.hitVec.z, explodeStrength, true);
			}
			
		if(entity != null)
		{
			
			double velocity = entity.motionX + entity.motionY + entity.motionZ;
			
			if(entity.isEntityAlive() && entity instanceof EntityLiving)
			{
				entity.attackEntityFrom(DamageSource.causeThrownDamage(this, user), this.damage*BoeConfig.damageMultiplier);
			
				//entity.addVelocity(motionX/10, motionY/10 + 0.3, motionZ/10);
				entity.motionX = motionX/10;
				entity.motionY = motionY/10 + 0.3;
				entity.motionZ = motionZ/10;
				entity.velocityChanged = true;
				EntityLiving livingEntity = (EntityLiving) entity;
				livingEntity.hurtResistantTime = 0;
			}
			else if(entity instanceof EntityPlayer)
			{
				entity.attackEntityFrom(DamageSource.causeThrownDamage(this, user), this.damage*BoeConfig.damageMultiplier);
				//entity.addVelocity(motionX/10, motionY/10 + 0.3, motionZ/10);
				entity.motionX = motionX/10;
				entity.motionY = motionY/10 + 0.3;
				entity.motionZ = motionZ/10;
				entity.velocityChanged = true;
				EntityPlayer livingEntity = (EntityPlayer) entity;
				livingEntity.hurtResistantTime = 0;
			}
		}
		

		if(result.typeOfHit != RayTraceResult.Type.BLOCK)
		{
		setDead();
		}
		}
		
		if(result.typeOfHit == RayTraceResult.Type.BLOCK)
		{
			if(this.world.getBlockState(result.getBlockPos()).isFullBlock() || this.world.getBlockState(result.getBlockPos()).isTopSolid())
			{
				if(explode && explodeStrength > 0)
				{
					world.createExplosion(this, result.hitVec.x, result.hitVec.y, result.hitVec.z, explodeStrength, true);
				}
			WorldServer server = this.world.getMinecraftServer().getWorld(this.dimension);
			server.spawnParticle(EnumParticleTypes.CLOUD, result.hitVec.x, result.hitVec.y, result.hitVec.z, 4, 0d, 0d, 0d, 0.1d, 0);		
			setDead();
			}
		}

		}
		
	}




	@Override
	public void registerModels() {
		// TODO Auto-generated method stub
		
	}

	
	
}
