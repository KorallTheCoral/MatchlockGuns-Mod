package com.korallkarlsson.matchlockweapons.entities.cannon;
import net.minecraft.item.ItemStack;
import com.korallkarlsson.matchlockweapons.init.ModItems;
import net.minecraft.init.*;

public enum LoadType {

	RoundShot(new ItemStack(ModItems.SLUG, 1), 80, 3, 1, 1, 10, 0.01f),
	GrapeShot(new ItemStack(Blocks.GRAVEL, 16), 10, 0, 100, 1, 10, 0.8f),
	LargeRoundShot(new ItemStack(ModItems.SLUG, 1), 140, 5, 1, 1, 10, 0.005f),
	LargeGrapeShot(new ItemStack(Blocks.GRAVEL, 32), 10, 0, 200, 1, 10, 1.2f),
	VolleyShot(new ItemStack(ModItems.BULLET_PACK, 1), 24, 0.5f, 1, 8, 20, 0.05f),
	PuckleShot(new ItemStack(ModItems.BULLET_PACK, 1), 16, 0, 1, 11, 7, 0.02f);
	
	
	public ItemStack ammo;
	public float damage;
	public float strenght;
	public int numberOfShots;
	public int loadedShots;
	public int cooldown;
	public float spread;
	
	
	
	LoadType(ItemStack ammo, float damage, float strength, int numberOfShots, int loadedShots, int cooldown, float spread)
	{
		this.ammo = ammo;
		this.damage = damage;
		this.strenght = strength;
		this.numberOfShots = numberOfShots;
		this.loadedShots = loadedShots;
		this.cooldown = cooldown;
		this.spread = spread;
	}
	
}
