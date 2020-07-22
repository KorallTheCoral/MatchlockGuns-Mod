package com.korallkarlsson.matchlockweapons.entities;

import com.korallkarlsson.matchlockweapons.entities.cannon.LoadType;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityPuckleCannon extends EntityCannon {

	public EntityPuckleCannon(World worldIn) {
		
		super(worldIn);
		this.acceptedShots = new LoadType[] {LoadType.PuckleShot};
		this.kickBack = 0f;
		this.height=1f;
		this.particleDistance=2.2f;
		this.carryDistance = 1f;
		this.soundPitch = 4f;
		this.slow = new PotionEffect(MobEffects.SLOWNESS, 2, 1, true, false);
		this.smokeAmount = 2;
	}

}
