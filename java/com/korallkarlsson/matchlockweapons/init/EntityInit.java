package com.korallkarlsson.matchlockweapons.init;

import com.korallkarlsson.matchlockweapons.Main;
import com.korallkarlsson.matchlockweapons.entities.EntityCannon;
import com.korallkarlsson.matchlockweapons.entities.EntityHeavyCannon;
import com.korallkarlsson.matchlockweapons.entities.EntityPuckleCannon;
import com.korallkarlsson.matchlockweapons.entities.EntityVolleyCannon;
import com.korallkarlsson.matchlockweapons.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {

	
	public static void RegisterEntities()
	{
		RegisterEntity("canon", EntityCannon.class, Reference.ENTITY_CANON, 50);
		RegisterEntity("cannon_heavy", EntityHeavyCannon.class, Reference.ENTITY_CANON_HEAVY, 50);
		RegisterEntity("volley_cannon", EntityVolleyCannon.class, Reference.ENTITY_CANON_VOLLEY, 50);
		RegisterEntity("puckle_cannon", EntityPuckleCannon.class, Reference.ENTITY_CANON_PUCKLE, 50);
	}
	
	private static void RegisterEntity(String name, Class<? extends Entity> entity, int id, int range)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, Main.instance, range, 1, true);
	}
	
}
