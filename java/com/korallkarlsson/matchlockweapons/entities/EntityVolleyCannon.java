package com.korallkarlsson.matchlockweapons.entities;

import com.korallkarlsson.matchlockweapons.entities.cannon.LoadType;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityVolleyCannon extends EntityCannon {

	public EntityVolleyCannon(World worldIn) {
		
		super(worldIn);
		this.acceptedShots = new LoadType[] {LoadType.VolleyShot};
		this.kickBack = 0f;
		this.height=0.72f;
		this.particleDistance=1.15f;
		this.carryDistance = 1.5f;
		this.soundPitch = 2.5f;
		this.slow = new PotionEffect(MobEffects.SLOWNESS, 2, 3, true, false);
		this.smokeAmount = 20;
	}

}
