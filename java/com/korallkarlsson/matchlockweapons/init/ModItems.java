package com.korallkarlsson.matchlockweapons.init;
import java.util.ArrayList;
import java.util.List;

import com.korallkarlsson.matchlockweapons.Main;
import com.korallkarlsson.matchlockweapons.creativetabs.ModTab;
import com.korallkarlsson.matchlockweapons.entities.EntityCannon;
import com.korallkarlsson.matchlockweapons.entities.EntityHeavyCannon;
import com.korallkarlsson.matchlockweapons.entities.EntityPuckleCannon;
import com.korallkarlsson.matchlockweapons.entities.EntityVolleyCannon;
import com.korallkarlsson.matchlockweapons.items.Arquebus;
import com.korallkarlsson.matchlockweapons.items.ArquebusPrimed;
import com.korallkarlsson.matchlockweapons.items.CannonItem;
import com.korallkarlsson.matchlockweapons.items.CustomTool;
import com.korallkarlsson.matchlockweapons.items.DummyItem;
import com.korallkarlsson.matchlockweapons.items.FlintlockMusket;
import com.korallkarlsson.matchlockweapons.items.GuideItem;
import com.korallkarlsson.matchlockweapons.items.GunPowderBag;
import com.korallkarlsson.matchlockweapons.items.ItemBase;
import com.korallkarlsson.matchlockweapons.items.NewReloadGun;
import com.korallkarlsson.matchlockweapons.items.NewReloadGunMatchlock;
import com.korallkarlsson.matchlockweapons.items.NewReloadGunMelee;
import com.korallkarlsson.matchlockweapons.items.NewReloadGunWheellock;
import com.korallkarlsson.matchlockweapons.items.ReloadGun;
import com.korallkarlsson.matchlockweapons.items.RepairItem;
import com.korallkarlsson.matchlockweapons.items.WheellockGun;
import com.korallkarlsson.matchlockweapons.items.ItemEnums.GunTypeEnum;
import com.korallkarlsson.matchlockweapons.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	
	
	//Guns
	//public static final Item MATCHLOCK_GUN = new Arquebus("matchlock_gun", "matchlock_gun_ignited", 16, 1, 0.6f, 0.3d);
	//-NEEDS TO BE ADDED AGAIN : public static final Item MATCHLOCK_BLUNDERBUSS = new Arquebus("matchlock_blunderbuss", "matchlock_blunderbuss_ignited", 3, 8, 5f, 0.7d);
	//public static final Item MATCHLOCK_PISTOL = new Arquebus("matchlock_pistol", "matchlock_pistol_ignited", 14, 1, 1.6f, 0);
	
	//public static final Item FLINTLOCK_MUSKET = new FlintlockMusket("flintlock_musket", 0.4f, 18, 0.2d, 0.01d);
	//public static final Item FLINTLOCK_PISTOL = new FlintlockMusket("flintlock_pistol", 1.5f, 16, 0d, 0.005d);
	//public static final Item FLINTLOCK_REVOLVER = new ReloadGun("flintlock_revolver", 1.6f, 10, 6, 20, 0, 0.04d);
	//public static final Item FLINTLOCK_REVOLVER_MUSKET = new ReloadGun("flintlock_revolver_musket", 0.7f, 15, 6, 30, 0.2d, 0.05d);
	//public static final Item FLINTLOCK_RIFLE = new FlintlockMusket("flintlock_rifle", 0.08f, 19, 0.7d, 0.1d);
	//public static final Item VOLLEY_GUN = new ReloadGun("volley_gun", 1.3f, 12f, 8, 5, 0.5d, 0.03d);
	//public static final Item DOUBLE_BARRELED_RIFLE = new ReloadGun("double_barreled_rifle", 0.09f, 19, 2, 40, 0.7d, 0.12d);
	
	//public static final Item BLUNT_GUN = new FlintlockMusket("blunt_gun", 1.8f, 22, 0.9d, 0.15d, true);
	
	//public static final Item WHEELLOCK_MUSKET = new WheellockGun("wheellock_musket", 0.5f, 18f, 1, 20, 0.2d, 0.08d, 8);
	//public static final Item WHEELLOCK_PISTOL = new WheellockGun("wheellock_pistol", 1.6f, 16f, 1, 20, 0d, 0.05d, 3);
	//public static final Item WHEELLOCK_REPEATER = new WheellockGun("wheellock_repeater", 1f, 12f, 5, 20, 0d, 0.15d, 6);
	
	//CraftingItems
	public static final Item GRIP = new ItemBase("grip", CreativeTabs.MATERIALS, 1);
	public static final Item ROUNDSHOT = new ItemBase("roundshot", Main.MISC_TAB, 16);
	public static final Item SMOOTH_BARREL = new ItemBase("smooth_barrel", CreativeTabs.MATERIALS, 1);
	public static final Item RIFLED_BARREL = new ItemBase("rifled_barrel", CreativeTabs.MATERIALS, 1);
	public static final Item MATCHLOCK = new ItemBase("matchlock", CreativeTabs.MATERIALS, 1);
	public static final Item FLINTLOCK = new ItemBase("flintlock", CreativeTabs.MATERIALS, 1);
	public static final Item REVOLVER_MECHANISM = new ItemBase("revolver_mechanism", CreativeTabs.MATERIALS, 1);	
	public static final Item WHEELLOCK = new ItemBase("wheellock", CreativeTabs.MATERIALS, 1);
	public static final Item CAPLOCK = new ItemBase("caplock", CreativeTabs.MATERIALS, 1); 
	
	public static final Item SLUG = new ItemBase("slug", Main.MISC_TAB, 1);
	
	public static final Item METAL_ROD = new ItemBase("metal_rod", CreativeTabs.MATERIALS, 1);
	public static final Item IRON_MECHANICAL_PARTS = new ItemBase("iron_mechanical_parts", CreativeTabs.MATERIALS, 1);
	public static final Item GOLD_MECHANICAL_PARTS = new ItemBase("gold_mechanical_parts", CreativeTabs.MATERIALS, 1);
	public static final Item DIAMOND_MECHANICAL_PARTS = new ItemBase("diamond_mechanical_parts", CreativeTabs.MATERIALS, 1);
	public static final Item EMERALD_MECHANICAL_PARTS = new ItemBase("emerald_mechanical_parts", CreativeTabs.MATERIALS, 1);
	public static final Item SPRING = new ItemBase("spring", CreativeTabs.MATERIALS, 1);
	
	public static final Item GUNPOWDER_CHARGE = new ItemBase("gunpowder_charge", Main.MISC_TAB, 1);
	public static final Item BULLET_PACK = new ItemBase("bullet_pack", Main.MISC_TAB, 1);
	
	public static final Item PAPER_SHOT_LOW = new ItemBase("paper_shot_low", Main.MISC_TAB, 8);
	public static final Item PAPER_SHOT = new ItemBase("paper_shot", Main.MISC_TAB, 8);
	public static final Item PAPER_SHOT_HIGH = new ItemBase("paper_shot_high", Main.MISC_TAB, 8);
	
	public static final Item PERCUSSION_SHOT = new ItemBase("percussion_shot", Main.MISC_TAB, 8);
	
	//Tools
	public static final CustomTool CHISEL = new CustomTool("chisel", 30);
	public static final CustomTool DIAMOND_CHISEL = new CustomTool("diamond_chisel", 80); 
	public static final CustomTool RAM_ROD = new CustomTool("ram_rod", 50); 
	public static final CustomTool GUN_RAM_ROD = new CustomTool("gun_ram_rod", 50); 
	public static final GunPowderBag GUNPOWDER_BAG = new GunPowderBag("gunpowder_bag", Main.MISC_TAB, 1);
	
	//Cannons
	public static final Item CANON_ITEM = new CannonItem("canon_item", EntityCannon.class, "A light and mobile cannon designed for field combat");
	public static final Item HEAVY_CANNON_ITEM = new CannonItem("heavy_cannon_item", EntityHeavyCannon.class, "A heavy and powerful cannon, useful for bringing down walls or barricades, or defending bases");
	public static final Item VOLLEY_CANNON_ITEM = new CannonItem("volley_cannon_item", EntityVolleyCannon.class, "A cannon with multiple barrels, allowing for multiple shots");
	public static final Item PUCKLE_CANNON_ITEM = new CannonItem("puckle_cannon_item", EntityPuckleCannon.class, "A lightweight, mounted gun with a revolving cylinder capable of holding 11 shots.");
	
	//New Guns
	public static final Item FLINTLOCK_MUSKET = new NewReloadGun("flintlock_musket", 0.7f, 28f, 1, 5, 0.2d, 0.01d, 20, true, ModItems.ROUNDSHOT, 2, ModItems.PAPER_SHOT, 350);
	public static final Item FLINTLOCK_REVOLVER = new NewReloadGun("flintlock_revolver", 1.6f, 21f, 6, 20, 0d, 0.06d, 10, false, ModItems.ROUNDSHOT, 1, ModItems.PAPER_SHOT_LOW, true, 100);
	public static final Item FLINTLOCK_PISTOL = new NewReloadGun("flintlock_pistol", 1.4f, 21f, 1, 5, 0d, 0.01d, 10, true, ModItems.ROUNDSHOT, 1, ModItems.PAPER_SHOT_LOW, true, 400);
	public static final Item FLINTLOCK_REVOLVER_MUSKET = new NewReloadGun("flintlock_revolver_musket", 1f, 23f, 6, 25, 0d, 0.04d, 20, false, ModItems.ROUNDSHOT, 2, ModItems.PAPER_SHOT, 100);
	public static final Item FLINTLOCK_RIFLE = new NewReloadGun("flintlock_rifle", 0.08f, 29f, 1, 5, 0.4d, 0.01d, 35, true, ModItems.ROUNDSHOT, 3, ModItems.PAPER_SHOT_HIGH, 200);
	public static final Item DOUBLE_BARRELED_RIFLE = new NewReloadGun("double_barreled_rifle", 0.1f, 29f, 2, 20, 0.4d, 0.2d, 35, true, ModItems.ROUNDSHOT, 3, ModItems.PAPER_SHOT_HIGH, 150);
	public static final Item VOLLEY_GUN = new NewReloadGun("volley_gun", 1.3f, 14f, 8, 5, 0.5d, 0.1d, 15, false, ModItems.ROUNDSHOT, 2, ModItems.PAPER_SHOT, 150);
	public static final Item DOUBLE_FLINTLOCK_PISTOL = new NewReloadGun("double_flintlock_pistol", 1.6f, 21f, 2, 5, 0d, 0.1d, 10, true, ModItems.ROUNDSHOT, 1, ModItems.PAPER_SHOT_LOW, true, 250);
	public static final Item FLINTLOCK_MUSKETOON = new NewReloadGun("flintlock_musketoon", 1f, 21f, 1, 5, 0d, 0.01d, 15, true, ModItems.ROUNDSHOT, 1, ModItems.PAPER_SHOT_LOW, false, 300);
	public static final Item FLINTLOCK_BLUNDERBUSS = new NewReloadGun("flintlock_blunderbuss", 3.5f, 2.5f, 1, 5, 0.3D, 0.01D, 20, true, Item.getItemFromBlock(Blocks.GRAVEL), 2, null, false, 10, 250);
	
	public static final Item FLINTLOCK_MUSKET_BAYONET = new NewReloadGunMelee("flintlock_musket_bayonet", 0.7f, 28f, 1, 5, 0.2d, 0.01d, 20, true, ModItems.ROUNDSHOT, 2, ModItems.PAPER_SHOT, false, 1, 350, 5f);
	
	//New Matchlocks
	public static final Item MATCHLOCK_GUN = new NewReloadGunMatchlock("matchlock_gun", 0.7f, 28f, 1, 5, 0.2D, 0, 20, true, ModItems.ROUNDSHOT, 2, ModItems.PAPER_SHOT, 400);
	public static final Item MATCHLOCK_PISTOL = new NewReloadGunMatchlock("matchlock_pistol", 1.4f, 21f, 1, 5, 0, 0, 10, true, ModItems.ROUNDSHOT, 1, ModItems.PAPER_SHOT_LOW, 500);
	public static final Item MATCHLOCK_PEPPERBOX = new NewReloadGunMatchlock("matchlock_pepperbox", 1.8f, 18f, 4, 35, 0, 0.1D, 10, true, ModItems.ROUNDSHOT, 1, ModItems.PAPER_SHOT_LOW, 200);
	public static final Item DOUBLE_MATCHLOCK_PISTOL = new NewReloadGunMatchlock("double_matchlock_pistol", 1.5f, 21f, 2, 5, 0, 0.1D, 10, true, ModItems.ROUNDSHOT, 1, ModItems.PAPER_SHOT_LOW, 350);
	//public static final Item DOUBLE_MATCHLOCK_GUN = new NewReloadGunMatchlock("double_matchlock_gun", 0.8f, 23f, 2, 5, 0.2D, 0.2D, 20, true, ModItems.ROUNDSHOT, 2, ModItems.PAPER_SHOT);
	public static final Item MATCHLOCK_BLUNDERBUSS = new NewReloadGunMatchlock("matchlock_blunderbuss", 3.5f, 2.5f, 1, 5, 0.3D, 0.01D, 20, true, Item.getItemFromBlock(Blocks.GRAVEL), 2, null, 10, 300);
	
	//New Wheellocks
	public static final Item WHEELLOCK_MUSKET = new NewReloadGunWheellock("wheellock_musket", 0.7f, 28f, 1, 5, 0.2D, 0.01D, 20, true, ModItems.ROUNDSHOT, 2, ModItems.PAPER_SHOT, 8, 250);
	public static final Item WHEELLOCK_PISTOL = new NewReloadGunWheellock("wheellock_pistol", 1.4f, 21f, 1, 5, 0, 0.01D, 10, true, ModItems.ROUNDSHOT, 1, ModItems.PAPER_SHOT_LOW, 4, true, 300);
	public static final Item WHEELLOCK_REPEATER = new NewReloadGunWheellock("wheellock_repeater", 1f, 21f, 5, 10, 0.1D, 0.1D, 20, true, ModItems.ROUNDSHOT, 2, ModItems.PAPER_SHOT, 6, 100);
	public static final Item DOUBLE_WHEELLOCK_PISTOL = new NewReloadGunWheellock("double_wheellock_pistol", 1.6f, 21f, 2, 5, 0, 0.1D, 10, true, ModItems.ROUNDSHOT, 1, ModItems.PAPER_SHOT_LOW, 4, true, 200);
	public static final Item WHEELLOCK_BLUNDERBUSS = new NewReloadGunWheellock("wheellock_blunderbuss", 3.5f, 2.5f, 1, 5, 0.3D, 0.01D, 20, true, Item.getItemFromBlock(Blocks.GRAVEL), 2, null, 8, 15, 200);
	
	//Breechloaded percussion cap guns
	public static final Item CAPLOCK_RIFLE = new NewReloadGun("caplock_rifle", 0.08f, 26f, 1, 5, 0.4d, 0, 20, false, ModItems.ROUNDSHOT, 3, ModItems.PERCUSSION_SHOT, false, true, 100);
	public static final Item CAPLOCK_PISTOL = new NewReloadGun("caplock_pistol", 1.3f, 21f, 1, 5, 0d, 0, 10, false, ModItems.ROUNDSHOT, 1, ModItems.PERCUSSION_SHOT, false, true, 100);
	
	//public static final Item FLINTLOCK_MUSKETOON = new MeleeGun("flintlock_musketoon", 0.7f, 18, 0.1d, 0.01d);
	
	//Dummy Items
	public static final Item CANNON_BARREL = new ItemBase("cannon_barrel", Main.MATERIALS_TAB, 1);
	public static final Item CANNON_WHEEL = new ItemBase("cannon_wheel", Main.MATERIALS_TAB, 1);
	public static final Item VOLLEY_BARREL = new ItemBase("volley_barrel", Main.MATERIALS_TAB, 1);
	public static final Item PUCKLE_BARREL = new ItemBase("puckle_gun", Main.MATERIALS_TAB, 1);
	public static final Item PUCKLE_STAND = new ItemBase("puckle_stand", Main.MATERIALS_TAB, 1);
	
	//Guides:
	public static final Item FLINTLOCK_GUIDE = new GuideItem("flintlock_guide", 0);
	public static final Item WHEELLOCK_GUIDE = new GuideItem("wheellock_guide", 1);
	public static final Item MATCHLOCK_GUIDE = new GuideItem("matchlock_guide", 2);
	public static final Item SPECIAL_GUIDE = new GuideItem("special_guide", 3);
	
	//Repair Kits
	public static final Item FLINTLOCK_REPAIR_KIT = new RepairItem("flintlock_repair_kit", GunTypeEnum.Flintlock);
	public static final Item MATCHLOCK_REPAIR_KIT = new RepairItem("matchlock_repair_kit", GunTypeEnum.Matchlock);
	public static final Item WHEELLOCK_REPAIR_KIT = new RepairItem("wheellock_repair_kit", GunTypeEnum.Wheellock);
	public static final Item CAPLOCK_REPAIR_KIT = new RepairItem("caplock_repair_kit", GunTypeEnum.Caplock);
	
	
}
