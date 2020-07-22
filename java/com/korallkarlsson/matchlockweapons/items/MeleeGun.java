
/*
package com.korallkarlsson.matchlockweapons.items;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.korallkarlsson.matchlockweapons.Main;
import com.korallkarlsson.matchlockweapons.entities.Bullet;
import com.korallkarlsson.matchlockweapons.init.ModItems;
import com.korallkarlsson.matchlockweapons.util.IHasModel;

import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.Vector3d;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.util.text.TextComponentString;

public class MeleeGun extends NewReloadGun implements IHasModel {

	float meleeDamage;
	
	public MeleeGun(String name, float inAccuracy, float damage, int maxShots, int cooldown, double kickback,
			double failRate, int reloadCooldown, boolean useRamRod, Item ammoItem, int gunPowderAmount,
			Item cartridgeItem, float meleeDamage) {
		super(name, inAccuracy, damage, maxShots, cooldown, kickback, failRate, reloadCooldown, useRamRod, ammoItem,
				gunPowderAmount, cartridgeItem);
		this.meleeDamage = meleeDamage;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		
		Multimap<String, AttributeModifier> modifiers = super.getAttributeModifiers(slot, stack);
		
		modifiers.put(Item.ATTACK_DAMAGE_MODIFIER.toString(), new AttributeModifier(Item.ATTACK_DAMAGE_MODIFIER, Item.ATTACK_DAMAGE_MODIFIER.toString(), meleeDamage, 0));
		
		return modifiers;
	}
		
}
*/