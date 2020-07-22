package com.korallkarlsson.matchlockweapons.items;

import java.util.ArrayList;
import java.util.List;

import com.korallkarlsson.matchlockweapons.Main;
import com.korallkarlsson.matchlockweapons.config.BoeConfig;
import com.korallkarlsson.matchlockweapons.entities.Bullet;
import com.korallkarlsson.matchlockweapons.init.ModItems;
import com.korallkarlsson.matchlockweapons.items.ItemEnums.GunTypeEnum;
import com.korallkarlsson.matchlockweapons.util.IHasModel;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.Vector3d;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.util.text.TextComponentString;

public class NewReloadGun extends Item implements IHasModel {

	
		private float inAccuracyMultiplier = 1;
		public float damage = 18;
		int numberOfShots = 1;
		public int maxShots = 6;
		public boolean canDual = false;
		public int cooldown = 20;
		double kickback = 0;
		double failRate = 0;
		boolean useRamRod = true;
		int reloadCooldown = 5;
		int gunPowderAmount = 1;
		Item ammoItem;
		Item cartridgeItem = null;
		
		
		public boolean noReloadSeperate = false;
		public static Item[] reloadItems = {Items.GUNPOWDER, ModItems.GUN_RAM_ROD, ModItems.ROUNDSHOT, ModItems.GUNPOWDER_BAG, ModItems.PAPER_SHOT, ModItems.PAPER_SHOT_HIGH, ModItems.PAPER_SHOT_LOW, ModItems.PERCUSSION_SHOT};
		
		public NewReloadGun(String name, float inAccuracy, float damage, int maxShots, int cooldown)
		{
			setUnlocalizedName(name);
			setRegistryName(name);
			setMaxStackSize(1);
			this.inAccuracyMultiplier = inAccuracy;
			this.damage = damage;
			this.maxShots = maxShots;
			this.cooldown = cooldown;
			ModItems.ITEMS.add(this);
			setCreativeTab(Main.GUN_TAB);
		}
		
		
		public NewReloadGun(String name, float inAccuracy, float damage, int maxShots, int cooldown, double kickback)
		{
			setUnlocalizedName(name);
			setRegistryName(name);
			setMaxStackSize(1);
			ModItems.ITEMS.add(this);
			this.inAccuracyMultiplier = inAccuracy;
			this.damage = damage;
			this.maxShots = maxShots;
			this.cooldown = cooldown;
			this.kickback = kickback;
			setCreativeTab(Main.GUN_TAB);
		}
		
		public NewReloadGun(String name, float inAccuracy, float damage, int maxShots, int cooldown, double kickback, double failRate, int reloadCooldown, boolean useRamRod, Item ammoItem, int gunPowderAmount, Item cartridgeItem, int durabillity)
		{
			setUnlocalizedName(name);
			setRegistryName(name);
			setMaxStackSize(1);
			ModItems.ITEMS.add(this);
			this.inAccuracyMultiplier = inAccuracy;
			this.damage = damage;
			this.maxShots = maxShots;
			this.cooldown = cooldown;
			this.kickback = kickback;
			this.failRate = failRate;
			this.reloadCooldown = reloadCooldown;
			this.useRamRod = useRamRod;
			this.ammoItem = ammoItem;
			this.gunPowderAmount = gunPowderAmount;
			this.cartridgeItem = cartridgeItem;
			this.setMaxDamage(durabillity);
			setCreativeTab(Main.GUN_TAB);
		}
		
		public NewReloadGun(String name, float inAccuracy, float damage, int maxShots, int cooldown, double kickback, double failRate, int reloadCooldown, boolean useRamRod, Item ammoItem, int gunPowderAmount, Item cartridgeItem, boolean canDual, int durabillity)
		{
			setUnlocalizedName(name);
			setRegistryName(name);
			setMaxStackSize(1);
			ModItems.ITEMS.add(this);
			this.inAccuracyMultiplier = inAccuracy;
			this.damage = damage;
			this.maxShots = maxShots;
			this.cooldown = cooldown;
			this.kickback = kickback;
			this.failRate = failRate;
			this.reloadCooldown = reloadCooldown;
			this.useRamRod = useRamRod;
			this.ammoItem = ammoItem;
			this.gunPowderAmount = gunPowderAmount;
			this.cartridgeItem = cartridgeItem;
			this.canDual = canDual;
			this.setMaxDamage(durabillity);
			setCreativeTab(Main.GUN_TAB);
		}
		
		public NewReloadGun(String name, float inAccuracy, float damage, int maxShots, int cooldown, double kickback, double failRate, int reloadCooldown, boolean useRamRod, Item ammoItem, int gunPowderAmount, Item cartridgeItem, boolean canDual, int numberOfShots, int durabillity)
		{
			setUnlocalizedName(name);
			setRegistryName(name);
			setMaxStackSize(1);
			ModItems.ITEMS.add(this);
			this.inAccuracyMultiplier = inAccuracy;
			this.damage = damage;
			this.maxShots = maxShots;
			this.cooldown = cooldown;
			this.kickback = kickback;
			this.failRate = failRate;
			this.reloadCooldown = reloadCooldown;
			this.useRamRod = useRamRod;
			this.ammoItem = ammoItem;
			this.gunPowderAmount = gunPowderAmount;
			this.cartridgeItem = cartridgeItem;
			this.canDual = canDual;
			this.numberOfShots = numberOfShots;
			this.setMaxDamage(durabillity);
			setCreativeTab(Main.GUN_TAB);
		}
		
		public NewReloadGun(String name, float inAccuracy, float damage, int maxShots, int cooldown, double kickback, double failRate, int reloadCooldown, boolean useRamRod, Item ammoItem, int gunPowderAmount, Item cartridgeItem, boolean canDual, boolean noReloadSeperate, int durabillity)
		{
			setUnlocalizedName(name);
			setRegistryName(name);
			setMaxStackSize(1);
			ModItems.ITEMS.add(this);
			this.inAccuracyMultiplier = inAccuracy;
			this.damage = damage;
			this.maxShots = maxShots;
			this.cooldown = cooldown;
			this.kickback = kickback;
			this.failRate = failRate;
			this.reloadCooldown = reloadCooldown;
			this.useRamRod = useRamRod;
			this.ammoItem = ammoItem;
			this.gunPowderAmount = gunPowderAmount;
			this.cartridgeItem = cartridgeItem;
			this.canDual = canDual;
			this.noReloadSeperate = noReloadSeperate;
			this.setMaxDamage(durabillity);
			setCreativeTab(Main.GUN_TAB);
		}
		
		@Override
		public boolean isDamageable() {
		return true;
		}
		
		@Override
		public CreativeTabs getCreativeTab() {
		return Main.GUN_TAB;
		}
		
		

		@Override
		public void registerModels() {
			
			Main.proxy.registerItemRenderer(this, 0, "inventory");
			
		}
		
		@Override
		public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

				if(stack.hasTagCompound())
				{
					if(stack.getTagCompound().hasKey("cooldown"))
					{
						int value = stack.getTagCompound().getInteger("cooldown");
						if(value > 0)
						{
						if(entityIn instanceof EntityPlayer)
						{
							EntityPlayer player = (EntityPlayer) entityIn;
							player.swingProgress = 0;
						}
						stack.getTagCompound().setInteger("cooldown", value  - 1);
						if(value - 1 == 0 && !worldIn.isRemote)
						{
							Vec3d pos = entityIn.getPositionVector();
							worldIn.playSound(null, pos.x, pos.y, pos.z, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.PLAYERS, 1, 1);
						}
						}
					}
					else
					{
						stack.getTagCompound().setInteger("cooldown", 0);
					}
				}
			
			
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		}
	
		
		public GunTypeEnum getType()
		{
			if(this instanceof NewReloadGunMatchlock)
			{
				return GunTypeEnum.Matchlock;
			}
			else if(this instanceof NewReloadGunWheellock)
			{
				return GunTypeEnum.Wheellock;
			}
			else if(this.noReloadSeperate)
			{
				return GunTypeEnum.Caplock;
			}
			else
			{
				return GunTypeEnum.Flintlock;
			}
		}
		
		List<String> AddLore(ItemStack stack, List<String> tooltip, ITooltipFlag flagIn)
		{
			
			if(this instanceof NewReloadGunMatchlock)
			{
				tooltip.add(TextFormatting.YELLOW + "[Matchlock]");
			}
			else if(this instanceof NewReloadGunWheellock)
			{
				tooltip.add(TextFormatting.WHITE + "[Wheellock]");
			}
			else if(this.noReloadSeperate)
			{
				tooltip.add(TextFormatting.AQUA + "[Caplock]");
			}
			else
			{
				tooltip.add(TextFormatting.DARK_GRAY + "[Flintlock]");
			}
				
			if(stack.getTagCompound().getInteger("loadedshots") >= 0)
			{
				tooltip.add("Loaded " + "(" + stack.getTagCompound().getInteger("loadedshots") + "/" + this.maxShots + ")");
			}
			else if(stack.getTagCompound().getInteger("loadedshots") == -1)
			{
				tooltip.add("Loaded *Reloading*");
			}
			
			if(this.gunPowderAmount == 1)
			{
				tooltip.add("Uses low power ammunition");
			}
			else if(this.gunPowderAmount == 2)
			{
				tooltip.add("Uses medium power ammunition");
			}
			else if(this.gunPowderAmount == 3)
			{
				tooltip.add("Uses high power ammunition");
			}
			
			if(this.useRamRod)
			{
				tooltip.add("Requires the use of a ramrod");
			}
			else
			{
				tooltip.add("Does not require a ramrod");
			}
			
			if(this.canDual)
			{
				tooltip.add("Can be dual wielded");
			}
			
			if(!GuiScreen.isShiftKeyDown())
			{
				tooltip.add("[Hold shift]");
			}
			else
			{
				float reloadSpeedFloat = this.reloadCooldown;
				float fireSpeedFloat = this.cooldown;
				
				String accuracy = "" + 2/this.inAccuracyMultiplier;
				String reloadingSpeed = "" + 20/reloadSpeedFloat;
				String fireSpeed = "" + 20/fireSpeedFloat;
				if(accuracy.length() > 4) accuracy = accuracy.substring(0, 4);
				if(fireSpeed.length() > 4) fireSpeed = fireSpeed.substring(0, 4);
				if(reloadingSpeed.length() > 4) reloadingSpeed = reloadingSpeed.substring(0, 4);
				
				tooltip.add("Damage: " + this.damage);
				tooltip.add("Accuracy: " + accuracy);
				tooltip.add("Reloading speed: " + reloadingSpeed);
				if(this.maxShots > 1)
				{
				tooltip.add("Firing speed: " + fireSpeed);
				}
				tooltip.add("Fail rate: " + this.failRate*100 + "%");
				
			}
			
			tooltip.add(TextFormatting.BOLD + "Durability: " + (this.getMaxDamage()-this.getDamage(stack)) + "/" + this.getMaxDamage());
			
			if(stack.getTagCompound().getInteger("step") != 0)
			{
				//tooltip.add("Reloading " + "(" + stack.getTagCompound().getInteger("step") + "/4)");
			}
			return tooltip;
		}
		
		@Override
		public int getDamage(ItemStack stack) {
		
			if(stack.hasTagCompound())
			{
				if(stack.getTagCompound().hasKey("damage"))
				{
					return stack.getTagCompound().getInteger("damage");
				}
			}
			return 0;
		}
		
		@Override
		public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		
			if(stack.hasTagCompound())
			{
			if(stack.getTagCompound().hasKey("loadedshots") && stack.getTagCompound().hasKey("step"))
			{
				AddLore(stack, tooltip, flagIn);
			}
			else
			{
				stack.getTagCompound().setInteger("loadedshots", 0);
				stack.getTagCompound().setInteger("step", 0);
			}
			}
			else
			{
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setInteger("loadedshots", 0);
				nbt.setInteger("step", 0);
				stack.setTagCompound(nbt);
			}
		}
		
		
		public void Jam(World world, EntityPlayer player, EnumHand hand)
		{
				
				if(!world.isRemote)
				{
				ItemStack stack = player.getHeldItem(hand);
				stack.getTagCompound().setInteger("cooldown", 200);
				world.playSound(null, player.getPositionVector().x, player.getPositionVector().y, player.getPositionVector().z, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 1, 1);
				player.sendMessage(new TextComponentString("*Gun Jams*"));
				}
		}
		
		public void Fire(World world, EntityPlayer player, EnumHand hand)
		{
			
			Vec3d pos = player.getPositionVector();
			
			
			
				if(!world.isRemote)
				{
				
			
					
				WorldServer server = world.getMinecraftServer().getWorld(player.dimension);
				
				if(this.gunPowderAmount == 1)
				{
				server.spawnParticle(EnumParticleTypes.CLOUD, pos.x + player.getLookVec().x, pos.y + player.getLookVec().y + 1.5, pos.z + player.getLookVec().z, 5, 0d, 0d, 0d, 0.04d, 0);		
				server.spawnParticle(EnumParticleTypes.CLOUD, pos.x + player.getLookVec().x*2, pos.y + player.getLookVec().y*2 + 1.5, pos.z + player.getLookVec().z*2, 5, 0.1d, 0.1d, 0.1d, 0.04d, 0);		
				server.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.x + player.getLookVec().x, pos.y + player.getLookVec().y + 1.5, pos.z + player.getLookVec().z, 5, 0.01d, 0.01d, 0.01d, 0.02d, 0);		
				}
				
				if(this.gunPowderAmount == 2)
				{
				server.spawnParticle(EnumParticleTypes.CLOUD, pos.x + player.getLookVec().x, pos.y + player.getLookVec().y + 1.5, pos.z + player.getLookVec().z, 5, 0d, 0d, 0d, 0.01d, 0);		
				server.spawnParticle(EnumParticleTypes.CLOUD, pos.x + player.getLookVec().x*2, pos.y + player.getLookVec().y*2 + 1.5, pos.z + player.getLookVec().z*2, 5, 0.1d, 0.1d, 0.1d, 0.04d, 0);		
				server.spawnParticle(EnumParticleTypes.CLOUD, pos.x + player.getLookVec().x*3, pos.y + player.getLookVec().y*3 + 1.5, pos.z + player.getLookVec().z*3, 5, 0.2d, 0.2d, 0.2d, 0.04d, 0);		
				server.spawnParticle(EnumParticleTypes.CLOUD, pos.x + player.getLookVec().x*4, pos.y + player.getLookVec().y*4 + 1.5, pos.z + player.getLookVec().z*4, 5, 0.3d, 0.3d, 0.3d, 0.04d, 0);	
				server.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.x + player.getLookVec().x, pos.y + player.getLookVec().y + 1.5, pos.z + player.getLookVec().z, 5, 0.01d, 0.01d, 0.01d, 0.02d, 0);		
				}
				if(this.gunPowderAmount == 3)
				{
				server.spawnParticle(EnumParticleTypes.CLOUD, pos.x + player.getLookVec().x, pos.y + player.getLookVec().y + 1.5, pos.z + player.getLookVec().z, 5, 0d, 0d, 0d, 0.01d, 0);		
				server.spawnParticle(EnumParticleTypes.CLOUD, pos.x + player.getLookVec().x*2, pos.y + player.getLookVec().y*2 + 1.5, pos.z + player.getLookVec().z*2, 5, 0.1d, 0.1d, 0.1d, 0.04d, 0);		
				server.spawnParticle(EnumParticleTypes.CLOUD, pos.x + player.getLookVec().x*3, pos.y + player.getLookVec().y*3 + 1.5, pos.z + player.getLookVec().z*3, 5, 0.2d, 0.2d, 0.2d, 0.04d, 0);		
				server.spawnParticle(EnumParticleTypes.CLOUD, pos.x + player.getLookVec().x*4, pos.y + player.getLookVec().y*4 + 1.5, pos.z + player.getLookVec().z*4, 5, 0.3d, 0.3d, 0.3d, 0.04d, 0);	
				server.spawnParticle(EnumParticleTypes.CLOUD, pos.x + player.getLookVec().x*5, pos.y + player.getLookVec().y*5 + 1.5, pos.z + player.getLookVec().z*5, 5, 0.2d, 0.2d, 0.2d, 0.04d, 0);		
				//server.spawnParticle(EnumParticleTypes.CLOUD, pos.x + player.getLookVec().x*6, pos.y + player.getLookVec().y*6 + 1.5, pos.z + player.getLookVec().z*6, 10, 0.3d, 0.3d, 0.3d, 0.04d, 0);	
				server.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.x + player.getLookVec().x, pos.y + player.getLookVec().y + 1.5, pos.z + player.getLookVec().z, 5, 0.01d, 0.01d, 0.01d, 0.02d, 0);		
				}
				
				
				world.playSound(null, pos.x, pos.y, pos.z, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 1, 1);
				int shots = player.getHeldItem(hand).getTagCompound().getInteger("loadedshots");
				ItemStack stack = new ItemStack(this, 1);
				NBTTagCompound nbt = player.getHeldItem(hand).getTagCompound();
				nbt.setInteger("loadedshots", shots-1);
				nbt.setInteger("cooldown", cooldown);
				
				int itemdamage = this.getDamage(player.getHeldItem(hand)) + 1;
				//player.sendMessage(new TextComponentString("damage: " + itemdamage));
				nbt.setInteger("damage", itemdamage);
				stack.setTagCompound(nbt);
				
				player.setHeldItem(hand, stack);
				
				pos = player.getPositionVector();
				
				double x = pos.x;
				double y = pos.y + 1.5;
				double z = pos.z;
				
				Vec3d dir = player.getLookVec();

				if(kickback != 0)
				{
					
				Vec3d vel = new Vec3d(dir.x*-kickback, dir.y*-kickback + 0.2, dir.z*-kickback);
				
				if(vel.y > 0.4)
				{
					vel = new Vec3d(vel.x, 0.4, vel.z);
				}
				
				player.addVelocity(vel.x, vel.y, vel.z);
				player.velocityChanged = true;
				}
				
				Bullet bullets[] = new Bullet[numberOfShots];
				
				
				
				for(int i = 0; i<numberOfShots; i++)
				{
				bullets[i] = new Bullet(world, x, y, z, damage*BoeConfig.damageMultiplier, player);
				
				dir = player.getLookVec();
				
				dir = new Vec3d(dir.x*10, dir.y*10, dir.z*10);
				
				dir = dir.addVector((Math.random()-0.5)*inAccuracyMultiplier*BoeConfig.inAccuracyMultiplier, (Math.random()-0.5)*inAccuracyMultiplier*BoeConfig.inAccuracyMultiplier, (Math.random()-0.5)*inAccuracyMultiplier*BoeConfig.inAccuracyMultiplier);
				
				bullets[i].addVelocity((dir.x/3)*this.gunPowderAmount, (dir.y/3)*this.gunPowderAmount, (dir.z/3)*this.gunPowderAmount);
				world.spawnEntity(bullets[i]);
				}
				
				}
				
			}
		
		
		/*@Override
	public EnumAction getItemUseAction(ItemStack stack) {
	// TODO Auto-generated method stub
	return EnumAction.BOW;
	}
		
		@Override
		public int getMaxItemUseDuration(ItemStack stack) {
		return 100;
		}*/
		
		
		@Override
		public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

			EnumActionResult result = EnumActionResult.FAIL;
			
			EnumHand otherHand = handIn;
			if(handIn == EnumHand.MAIN_HAND)
			{
				otherHand = EnumHand.OFF_HAND;
			}
			else if(handIn == EnumHand.OFF_HAND)
			{
				otherHand = EnumHand.MAIN_HAND;
			}
			
			ItemStack mainItem = playerIn.getHeldItem(handIn);
			ItemStack offHand = playerIn.getHeldItem(otherHand);
			
			int load1 = 0;
			int load2 = 0;
			int cooldown = 0;
			
			boolean changedW = false;
			
			if(mainItem.hasTagCompound() && offHand.hasTagCompound() && mainItem.getItem() instanceof NewReloadGun && offHand.getItem() instanceof NewReloadGun)
			{
				load1 = offHand.getTagCompound().getInteger("loadedshots");
				load2 = mainItem.getTagCompound().getInteger("loadedshots");
				cooldown = offHand.getTagCompound().getInteger("cooldown");
				changedW = true;
			}
			
			int maxCooldown = 0;
			
			if(offHand.getItem() instanceof NewReloadGun)
			{
				NewReloadGun gun = (NewReloadGun) offHand.getItem();
				maxCooldown = gun.cooldown;
			}
			
			if(offHand.getItem() instanceof RepairItem)
			{
				RepairItem kit = (RepairItem) offHand.getItem();
				
				if(kit.type == this.getType())
				{
					mainItem.getTagCompound().setInteger("damage", 0);
					offHand.setCount(0);
					
					Vec3d pos = playerIn.getPositionVector();
					
					worldIn.playSound(null, pos.x, pos.y, pos.z, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.PLAYERS, 0.5f, 1.3f);
				}
			}
			else if(load2 > load1 && cooldown == 0)
			{
				result = preFire(handIn, playerIn, mainItem, worldIn, offHand);
			}
			else if(load2 > load1 && maxCooldown != 0)
			{
				if(cooldown != maxCooldown)
				{
					result = preFire(handIn, playerIn, mainItem, worldIn, offHand);
				}
			}
			else if(load1 == load2 && handIn == EnumHand.MAIN_HAND)
			{
				result = preFire(handIn, playerIn, mainItem, worldIn, offHand);
			}
			else if(changedW == false)
			{
				result = preFire(handIn, playerIn, mainItem, worldIn, offHand);
			}
			
			
			//return super.onItemRightClick(worldIn, playerIn, handIn);
			//playerIn.setActiveHand(handIn);
			return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
		}
		
		
		public static boolean containsReloadItem(Item item)
		{
			boolean isTrue = false;
			
			for(Item x : reloadItems)
			{
				if(x == item)
				{
					isTrue = true;
				}
			}
			
			return isTrue;
		}
		
		
		
		EnumActionResult preFire(EnumHand handIn, EntityPlayer playerIn, ItemStack mainItem, World worldIn, ItemStack offHand)
		{
			if(handIn == EnumHand.MAIN_HAND || canDual)
			{
				
			if(!playerIn.isInWater() && mainItem.hasTagCompound() && !worldIn.isRemote)
			{
			if(!containsReloadItem(offHand.getItem()) && playerIn.getHeldItemOffhand().getItem() != ModItems.GUN_RAM_ROD && mainItem.getTagCompound().getInteger("loadedshots") > 0 && mainItem.getTagCompound().getInteger("cooldown") <= 0 && (offHand.isEmpty() || (this.canDual && offHand.getItem() instanceof ItemBase == false)) && (!playerIn.isSneaking() || mainItem.getTagCompound().getInteger("step") != 3))
			{
				if(Math.random() > failRate && this.getDamage(mainItem) < this.getMaxDamage())
				{
				Fire(worldIn, playerIn, handIn);
				return EnumActionResult.SUCCESS;
				}
				else
				{
				Jam(worldIn, playerIn, handIn);
				return EnumActionResult.SUCCESS;
				}
			}
			else if(handIn == EnumHand.MAIN_HAND && mainItem.getTagCompound().getInteger("loadedshots") < this.maxShots && mainItem.getTagCompound().getInteger("cooldown") <= 0)
			{
				if(!Load(playerIn, worldIn) && (playerIn.getHeldItemOffhand().isEmpty() || canDual) && playerIn.getHeldItemMainhand().getTagCompound().getInteger("loadedshots") == 0)
				{
					worldIn.playSound(null, playerIn.getPositionVector().x, playerIn.getPositionVector().y, playerIn.getPositionVector().z, SoundEvents.BLOCK_WOOD_BUTTON_CLICK_ON, SoundCategory.PLAYERS, 1, 1);
					return EnumActionResult.SUCCESS;
				}
				return EnumActionResult.PASS;
			}
			else if(!worldIn.isRemote && mainItem.getTagCompound().getInteger("loadedshots") == 0 && (offHand.isEmpty() || canDual))
			{
				worldIn.playSound(null, playerIn.getPositionVector().x, playerIn.getPositionVector().y, playerIn.getPositionVector().z, SoundEvents.BLOCK_WOOD_BUTTON_CLICK_ON, SoundCategory.PLAYERS, 1, 1);
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.PASS;
			}
			
			}
			return EnumActionResult.FAIL;
		}
		
		boolean Load(EntityPlayer player, World worldIn)
		{
			ItemStack item = player.getHeldItemMainhand();
			ItemStack offHandItem = player.getHeldItemOffhand();
			boolean changed = false;
			int step = item.getTagCompound().getInteger("step");
			if(step == 0 && offHandItem.getItem() == this.cartridgeItem && this.cartridgeItem != null)
			{
				if(this.useRamRod)
				{
				step += 2;
				}
				else
				{
				step += 3;
				}
				changed = true;
				player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(this.cartridgeItem, offHandItem.getCount() - 1));
			}
			else if(step == 0 && offHandItem.getItem() == Items.GUNPOWDER && offHandItem.getCount() == this.gunPowderAmount && !this.noReloadSeperate)
			{
				step ++;
				changed = true;
				player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, ItemStack.EMPTY);
				worldIn.playSound(null, player.getPositionVector().x, player.getPositionVector().y, player.getPositionVector().z, SoundEvents.BLOCK_SAND_PLACE, SoundCategory.PLAYERS, 1, 1);
			}
			else if(step == 0 && offHandItem.getItem() == ModItems.GUNPOWDER_BAG && offHandItem.hasTagCompound() && !this.noReloadSeperate)
			{
				if(offHandItem.getTagCompound().getInteger("gunpowder") >= this.gunPowderAmount)
				{
					int value = offHandItem.getTagCompound().getInteger("gunpowder");
					offHandItem.getTagCompound().setInteger("gunpowder", value - this.gunPowderAmount);
					player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, offHandItem);
					step ++;
					changed = true;
					worldIn.playSound(null, player.getPositionVector().x, player.getPositionVector().y, player.getPositionVector().z, SoundEvents.BLOCK_SAND_PLACE, SoundCategory.PLAYERS, 1, 1);
				}
			}
			else if(step == 1 && offHandItem.getItem() == this.ammoItem)
			{
				if(this.useRamRod)
				{
				step ++;
				}
				else
				{
				step += 2;
				}
				changed = true;
				player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(this.ammoItem, offHandItem.getCount() - 1));
				worldIn.playSound(null, player.getPositionVector().x, player.getPositionVector().y, player.getPositionVector().z, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1, 1f);
			}
			else if(step == 2 && offHandItem.getItem() == ModItems.GUN_RAM_ROD)
			{
				step ++;
				changed = true;
				worldIn.playSound(null, player.getPositionVector().x, player.getPositionVector().y, player.getPositionVector().z, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1, 0.5f);
			}
			else if(step == 3 && player.isSneaking() && offHandItem.isEmpty())
			{
				step ++;
				changed = true;
				worldIn.playSound(null, player.getPositionVector().x, player.getPositionVector().y, player.getPositionVector().z, SoundEvents.BLOCK_TRIPWIRE_CLICK_OFF, SoundCategory.PLAYERS, 1, 1);
			}
				
			if(changed)
			{
			
			if(step > 3)
			{
				step = 0;
				int loadedShots = item.getTagCompound().getInteger("loadedshots") + 1;
				item.getTagCompound().setInteger("loadedshots", loadedShots);
			}
			else
			{
				PotionEffect slow = new PotionEffect(MobEffects.SLOWNESS, this.reloadCooldown, 2, true, false);
				if(this.gunPowderAmount != 1)
				{
				slow = new PotionEffect(MobEffects.SLOWNESS, this.reloadCooldown, 254, true, false);
				}
				
				player.addPotionEffect(slow);
				
				item.getTagCompound().setInteger("cooldown", this.reloadCooldown);
			}
			item.getTagCompound().setInteger("step", step);
			player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, item);
			}
			
			return changed;
		}
		
}
