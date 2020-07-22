package com.korallkarlsson.matchlockweapons.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;

public class NewReloadGunMelee extends NewReloadGun {

	float meleeDamage = 0;
	
	
	public NewReloadGunMelee(String name, float inAccuracy, float damage, int maxShots, int cooldown, double kickback, double failRate, int reloadCooldown, boolean useRamRod, Item ammoItem, int gunPowderAmount,Item cartridgeItem, boolean canDual, int numberOfShots, int durabillity, float meleeDamage) {
		
		super(name, inAccuracy, damage, maxShots, cooldown, kickback, failRate, reloadCooldown, useRamRod, ammoItem,
				gunPowderAmount, cartridgeItem, canDual, numberOfShots, durabillity);
		
		this.meleeDamage = meleeDamage;
		
	}
	
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		
		if(entity instanceof EntityLiving && !player.getCooldownTracker().hasCooldown(this) && !player.world.isRemote)
		{
			EntityLiving ent = (EntityLiving) entity;
			
			ent.attackEntityFrom(DamageSource.causePlayerDamage(player), meleeDamage);
			Vec3d pos = player.getPositionVector();
			player.world.playSound(null, pos.x, pos.y, pos.z, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1, 2);
			player.getCooldownTracker().setCooldown(this, 40);
		}
		
		return true;
	}
	


}
