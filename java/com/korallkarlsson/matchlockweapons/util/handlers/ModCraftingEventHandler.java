package com.korallkarlsson.matchlockweapons.util.handlers;

import com.korallkarlsson.matchlockweapons.Main;
import com.korallkarlsson.matchlockweapons.entities.EntityCannon;
import com.korallkarlsson.matchlockweapons.init.ModItems;
import com.korallkarlsson.matchlockweapons.items.Arquebus;
import com.korallkarlsson.matchlockweapons.items.FlintlockMusket;
import com.korallkarlsson.matchlockweapons.items.ReloadGun;
import com.korallkarlsson.matchlockweapons.items.WheellockGun;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;

public class ModCraftingEventHandler {
	
	@SubscribeEvent
	public static void onServerShutdown(FMLServerStoppingEvent event)
	{
		if(event.getSide().isServer())
		{
			MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
			for(World world : server.worlds)
			{
				for(Entity entity : world.loadedEntityList)
				{
					if(entity instanceof EntityCannon)
					{
						EntityCannon canon = (EntityCannon) entity;
						canon.clear();
					}
				}
			}
		}
	}
	
	@SubscribeEvent(priority=EventPriority.LOWEST)
	public void onJump(LivingJumpEvent event)
	{
		Entity entity =  event.getEntity();
		if(entity instanceof EntityPlayer)
		{
			PotionEffect potion = ((EntityPlayer) entity).getActivePotionEffect(MobEffects.SLOWNESS);
			if(potion != null)
			{
			if(potion.getAmplifier() > 100)
			{
				entity.motionY = -1;
				entity.velocityChanged = true;
			}
			}
		}
	}
	
	
	@SubscribeEvent
	public static void onPlayerLeave(PlayerLoggedOutEvent event)
	{
		EntityPlayer player = event.player;
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		for(World world : server.worlds)
		{
			for(Entity entity : world.loadedEntityList)
			{
				if(entity instanceof EntityCannon)
				{
					EntityCannon canon = (EntityCannon) entity;
					//canon.clear();
				}
			}
		}
	}
	
	@SuppressWarnings("unused")
	@SubscribeEvent
	public void crafting(ItemCraftedEvent event)
	{
		IInventory matrix = event.craftMatrix;
		if(event.crafting.getItem() instanceof ReloadGun)
		{
			for(int i = 0; i < matrix.getSizeInventory(); i++)
			{
				ItemStack item = matrix.getStackInSlot(i);
				if(item.getItem() instanceof ReloadGun && item.getItem() == event.crafting.getItem())
				{
					if(item.hasTagCompound())
					{
						
						EntityPlayer player = event.player;
						ReloadGun gun = (ReloadGun) item.getItem();
						for(int i3 = 0; i3<player.inventory.getSizeInventory(); i3++)
						{
							ItemStack slotItem = player.inventory.getStackInSlot(i3);
							if(slotItem.getItem() == event.crafting.getItem())
							{
								if(slotItem.hasTagCompound())
								{
									if(slotItem.getTagCompound().getInteger("loadedshots") == -1)
									{
										slotItem.setItemDamage(item.getItemDamage());
										if(item.getTagCompound().getInteger("loadedshots") < gun.maxShots)
										{
										slotItem.getTagCompound().setInteger("loadedshots", item.getTagCompound().getInteger("loadedshots") + 1);
										}
										else
										{
										slotItem.getTagCompound().setInteger("loadedshots", gun.maxShots);
										}
										break;
									}
								}
							}
						}
						

						if(item.getTagCompound().getInteger("loadedshots") + 1 <= gun.maxShots)
						{
						event.crafting.setItemDamage(item.getItemDamage());
						event.crafting.getTagCompound().setInteger("loadedshots", item.getTagCompound().getInteger("loadedshots") + 1);;
						}
						else
						{
							if(event.player.world.isRemote)
							{
							event.player.sendMessage(new TextComponentString("*You try squeezing in one more shot, but it simply does not work*"));
							}
							event.crafting.setItemDamage(item.getItemDamage());
							event.crafting.getTagCompound().setInteger("loadedshots", gun.maxShots);
							for(int i2 = 0; i2 < matrix.getSizeInventory(); i2++)
							{
								if(matrix.getStackInSlot(i2).getItem() instanceof ReloadGun == false)
								{
								event.player.addItemStackToInventory(new ItemStack(matrix.getStackInSlot(i2).getItem(), 1));
								}
							}
							break;
						}
					}
				}
			}
		}
		else if(event.crafting.getItem() instanceof WheellockGun)
		{
			for(int i = 0; i < matrix.getSizeInventory(); i++)
			{
				ItemStack item = matrix.getStackInSlot(i);
				if(item.getItem() instanceof WheellockGun && item.getItem() == event.crafting.getItem())
				{
					if(item.hasTagCompound())
					{
						
						EntityPlayer player = event.player;
						WheellockGun gun = (WheellockGun) item.getItem();
						for(int i3 = 0; i3<player.inventory.getSizeInventory(); i3++)
						{
							ItemStack slotItem = player.inventory.getStackInSlot(i3);
							if(slotItem.getItem() == event.crafting.getItem())
							{
								if(slotItem.hasTagCompound())
								{
									if(slotItem.getTagCompound().getInteger("loadedshots") == -1)
									{
										slotItem.setItemDamage(item.getItemDamage());
										if(item.getTagCompound().getInteger("loadedshots") < gun.maxShots)
										{
										slotItem.getTagCompound().setInteger("loadedshots", item.getTagCompound().getInteger("loadedshots") + 1);
										}
										else
										{
										slotItem.getTagCompound().setInteger("loadedshots", gun.maxShots);
										}
										break;
									}
								}
							}
						}
						

						if(item.getTagCompound().getInteger("loadedshots") + 1 <= gun.maxShots)
						{
						event.crafting.setItemDamage(item.getItemDamage());
						event.crafting.getTagCompound().setInteger("loadedshots", item.getTagCompound().getInteger("loadedshots") + 1);;
						}
						else
						{
							if(event.player.world.isRemote)
							{
							event.player.sendMessage(new TextComponentString("*You try squeezing in one more shot, but it simply does not work*"));
							}
							event.crafting.setItemDamage(item.getItemDamage());
							event.crafting.getTagCompound().setInteger("loadedshots", gun.maxShots);
							for(int i2 = 0; i2 < matrix.getSizeInventory(); i2++)
							{
								if(matrix.getStackInSlot(i2).getItem() instanceof WheellockGun == false)
								{
								event.player.addItemStackToInventory(new ItemStack(matrix.getStackInSlot(i2).getItem(), 1));
								}
							}
							break;
						}
					}
				}
			}
		}
		else if(event.crafting.getItem() instanceof Arquebus || event.crafting.getItem() instanceof FlintlockMusket)
		{
			if(false)
			{
			//Only transfer over item damage
			for(int i = 0; i < matrix.getSizeInventory(); i++)
			{
				ItemStack item = matrix.getStackInSlot(i);
				if(item.getItem() instanceof Arquebus || item.getItem() instanceof FlintlockMusket && item.getItem() == event.crafting.getItem())
				{
					event.crafting.setItemDamage(item.getItemDamage());
					event.crafting.getTagCompound().removeTag("craftingReload");
					EntityPlayer player = event.player;
					for(int i3 = 0; i3<player.inventory.getSizeInventory(); i3++)
					{
						ItemStack slotItem = player.inventory.getStackInSlot(i3);
						if(slotItem.getItem() == event.crafting.getItem() && slotItem.hasTagCompound())
						{
							if(slotItem.getTagCompound().hasKey("craftingReload"))
							{
								slotItem.setItemDamage(item.getItemDamage());
								slotItem.getTagCompound().removeTag("craftingReload");
							}
						}
					}
					
				}
			}
			}
			
		}
	}
	
	
	
	
	
}
