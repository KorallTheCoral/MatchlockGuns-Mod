package com.korallkarlsson.matchlockweapons.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class BoeConfig {
	
	private static Configuration config = null;
	
	public static final String CATEGORY_NAME_VALUES = "gun_values";
	
	public static float damageMultiplier = 1f;
	public static float inAccuracyMultiplier = 1f;
	public static boolean enableDurabillity = true;
	
	public static void init() {
		File configFile = new File("config/MatchlockGuns.cfg");
		config = new Configuration(configFile);
		if(config.hasKey(CATEGORY_NAME_VALUES, "damageMultiplier") && config.hasKey(CATEGORY_NAME_VALUES, "inAccuracyMultiplier") && config.hasKey(CATEGORY_NAME_VALUES, "enableDurabillity"))
		{
		loadConfig();
		}
		else
		{
		saveConfig();
		}
	}
	
	public static void saveConfig() {
		updateConfig(false);
		config.save();
	}
	
	public static void loadConfig() {
		config.load();
		updateConfig(true);
	}
	
	private static void updateConfig(boolean load)
	{
		Property propertyDamage = config.get(CATEGORY_NAME_VALUES, "damageMultiplier", 1f);
		Property propertyAccuracy = config.get(CATEGORY_NAME_VALUES, "inAccuracyMultiplier", 1F);
		Property propertyDurabillity = config.get(CATEGORY_NAME_VALUES, "enableDurabillity", true);
		if(load)
		{
			inAccuracyMultiplier = (float) propertyAccuracy.getDouble();
			damageMultiplier = (float) propertyDamage.getDouble();
			enableDurabillity = (boolean) propertyDurabillity.getBoolean();
		}
		else
		{
			propertyAccuracy.set(inAccuracyMultiplier);
			propertyDamage.setValue(damageMultiplier);
			propertyDurabillity.set(enableDurabillity);
		}
	}
}
