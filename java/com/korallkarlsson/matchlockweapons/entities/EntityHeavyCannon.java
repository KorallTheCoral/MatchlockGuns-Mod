package com.korallkarlsson.matchlockweapons.entities;

import com.korallkarlsson.matchlockweapons.entities.cannon.LoadType;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityHeavyCannon extends EntityCannon {

	public EntityHeavyCannon(World worldIn) {
		
		super(worldIn);
		this.acceptedShots = new LoadType[] {LoadType.LargeRoundShot, LoadType.LargeGrapeShot};
		this.kickBack = 2f;
		this.height=1.5f;
		this.carryDistance = 2f;
		this.soundPitch = 0.5f;
		this.slow = new PotionEffect(MobEffects.SLOWNESS, 2, 4, true, false);
	}

}
