package com.korallkarlsson.matchlockweapons.entities;

import com.korallkarlsson.matchlockweapons.entities.cannon.LoadType;
import com.korallkarlsson.matchlockweapons.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityCannon extends EntityArmorStand {

	private EntityPlayer followPlayer = null;
	
	float kickBack = 1;
	
	float height = 1;
	
	int loaded = 0;
	Item ammo = ModItems.SLUG;
	int cooldown = 0;
	int maxPitch = 35;

	int loadedShots;
	int smokeAmount = 100;
	float particleDistance = 2;
	float carryDistance = 1;
	float soundPitch;
	
	
	Item ignitionItem = Items.FLINT_AND_STEEL;
	
	int loadedShotID;
	
	LoadType[] acceptedShots = {LoadType.RoundShot, LoadType.GrapeShot};
	
	PotionEffect slow = new PotionEffect(MobEffects.SLOWNESS, 2, 2, true, false);
	
	
	public LoadType getLoadType(ItemStack handItem)
	{
		for(int i = 0; i<acceptedShots.length; i++)
		{
			LoadType type = acceptedShots[i];
			if(type.ammo.getItem() == handItem.getItem() && type.ammo.getCount() <= handItem.getCount())
			{
				this.loadedShotID = i;
				return type;
			}
		}
			
		return null;
	}
	
	
	public EntityCannon(World worldIn) {
		super(worldIn);
		this.setDropItemsWhenDead(false);
	}
	
	
	public EntityCannon() {
		super(null);
		this.setDropItemsWhenDead(false);
	}
	
	@Override
	public void setDropItemsWhenDead(boolean dropWhenDead) {
		// TODO Auto-generated method stub
		super.setDropItemsWhenDead(false);
	}
	
	
	
	private void deathParticles()
	{
		WorldServer server = world.getMinecraftServer().getWorld(this.dimension);
		Vec3d pos = this.getPositionVector();
		server.spawnParticle(EnumParticleTypes.CRIT, pos.x, pos.y + 1.2, pos.z, 10, 1d, 1d, 1d, 0.05d, 0);		
		world.playSound(null, pos.x, pos.y, pos.z, SoundEvents.ENTITY_ARMORSTAND_BREAK, SoundCategory.NEUTRAL, 1.5f, 0.8f);
	}
	
	
	private void hurtParticles()
	{
		WorldServer server = world.getMinecraftServer().getWorld(this.dimension);
		Vec3d pos = this.getPositionVector();
		server.spawnParticle(EnumParticleTypes.CRIT, pos.x, pos.y + 1.2, pos.z, 10, 1d, 1d, 1d, 0.05d, 0);		
		world.playSound(null, pos.x, pos.y, pos.z, SoundEvents.ENTITY_ARMORSTAND_HIT, SoundCategory.NEUTRAL, 0.8f, 1.5f);
	}
	
	
@Override
public boolean attackEntityFrom(DamageSource source, float amount) {
	
	if(!world.isRemote && cooldown <= 0 && source != DamageSource.IN_WALL)
	{
	cooldown = 5;
	if(source == DamageSource.OUT_OF_WORLD)
	{
		this.setDead();
	}
	else
	{
		float h = this.getHealth();
		if(h - amount <= 0)
		{
			dropItem(ModItems.CANNON_BARREL, 1);
			deathParticles();
			this.setDead();
		}
		else
		{
			hurtParticles();
			this.setHealth(h - amount);
		}
	}
	}
	
	return false;
}
	

@Override
public NBTTagCompound writeToNBT(NBTTagCompound compound) {
	compound.setInteger("loadedstage", this.loaded);
	compound.setInteger("loadedShotID", loadedShotID);
	compound.setInteger("loadedShots", this.loadedShots);
	followPlayer = null;
	return super.writeToNBT(compound);
}

@Override
public void readFromNBT(NBTTagCompound compound) {
	
	followPlayer = null;
	
	if(compound.hasKey("loadedstage"))
	{
		this.loaded = compound.getInteger("loadedstage");
	}
	else
	{
		compound.setInteger("loadedstage", 0);
	}
	
	if(compound.hasKey("loadedShotID"))
	{
		this.loadedShotID = compound.getInteger("loadedShotID");
	}
	else
	{
		compound.setInteger("loadedShotID", 0);
	}
	
	if(compound.hasKey("loadedShots"))
	{
		this.loadedShots = compound.getInteger("loadedShots");
	}
	else
	{
		compound.setInteger("loadedShots", 0);
	}
		
	super.readFromNBT(compound);
}

	
	@Override
	public void onEntityUpdate() {
		
		super.onEntityUpdate();
		
		if(!world.isRemote)
		{
		
			if(cooldown > 0)
			{
				cooldown -= 1;
				if(cooldown == 0)
				{
					Vec3d pos = this.getPositionVector();
					//world.playSound(null, pos.x, pos.y, pos.z, SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF, SoundCategory.PLAYERS, 0.8f, 1.2f);
				}
			}
		
		if(followPlayer != null && this.getServer().getPlayerList().getPlayers().contains(followPlayer))
		{
			
		if(followPlayer.isEntityAlive() && !followPlayer.isSneaking() && followPlayer.isAddedToWorld() && this.getServer().getPlayerList().getPlayers().contains(followPlayer) && followPlayer.addedToChunk && followPlayer.onGround)
		{
			
			if(followPlayer.dimension == this.dimension && followPlayer.getDistance(this) < 10 && followPlayer.isServerWorld() && !followPlayer.isPlayerSleeping() && !followPlayer.isInWater() && !followPlayer.isInLava())
			{
			this.fallDistance = 0;
			
			this.motionX = 0;
			this.motionY = 0;
			this.motionZ = 0;
			this.velocityChanged = true;
			//this.setNoGravity(true); --Issues with desync and stuff when setting gravity?
			
			
			Vec3d pos = followPlayer.getPositionVector();
			
			Vec3d secondVec = new Vec3d(followPlayer.getLookVec().x, 0, followPlayer.getLookVec().z).normalize();
			
			secondVec = new Vec3d(secondVec.x*this.carryDistance, 0, secondVec.z*this.carryDistance);
			
			pos = pos.add(secondVec);
			
			
			followPlayer.addPotionEffect(slow); 
			
			if(!this.world.getBlockState(new BlockPos(pos.x,pos.y, pos.z)).isFullBlock() && new Vec3d(followPlayer.getLookVec().x, 0, followPlayer.getLookVec().z).normalize().distanceTo(new Vec3d(0, 0, 0)) > 0.1)
			{
				this.setPositionAndRotation(pos.x, pos.y, pos.z, followPlayer.rotationYaw, followPlayer.rotationPitch);
				if(this.rotationPitch < -maxPitch)
				{
					rotationPitch = -maxPitch;
				}
				else if(this.rotationPitch > maxPitch)
				{
					rotationPitch = maxPitch;
				}
			}
			else
			{
				followPlayer = null;
			}
			
			}
			else
			{
				followPlayer = null;
			}
		}
		else
		{
			followPlayer = null;
		}
		
		}
		else
		{
			followPlayer = null;
		}
		}
		
	}
	
	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
	}
	
	public void clear()
	{
		this.followPlayer = null;
	}
	
	@Override
	public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
		
		if(!world.isRemote && cooldown <= 0)
		{
			
		Vec3d pos = this.getPositionVector();
		if(loaded == 4 && player.getHeldItemMainhand().getItem() == ignitionItem && (followPlayer == null || kickBack == 0))
		{
			Fire();
			return EnumActionResult.SUCCESS;
		}
		else if(followPlayer == null && loaded == 0 && player.getHeldItemMainhand().getItem() == ModItems.GUNPOWDER_CHARGE)
		{
			cooldown = 15;
			this.loaded = 1;
			player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
			world.playSound(null, pos.x, pos.y, pos.z, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.PLAYERS, 0.8f, 1.2f);
			return EnumActionResult.SUCCESS;
		}
		else if(followPlayer == null && loaded == 1 && player.getHeldItemMainhand().getItem() == ModItems.RAM_ROD)
		{
			cooldown = 15;
			this.loaded = 2;
			world.playSound(null, pos.x, pos.y, pos.z, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.PLAYERS, 0.8f, 1.2f);
			return EnumActionResult.SUCCESS;
		}
		else if(followPlayer == null && loaded == 2 && player.getHeldItemMainhand() != ItemStack.EMPTY)
		{
			LoadType loadType = getLoadType(player.getHeldItemMainhand());
			if(loadType != null)
			{
			cooldown = 15;
			this.loaded = 3;
			world.playSound(null, pos.x, pos.y, pos.z, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.PLAYERS, 0.8f, 1.2f);
			ItemStack stack = player.getHeldItemMainhand();
			stack.setCount(stack.getCount() - loadType.ammo.getCount());
			if(stack.getCount() <= 0)
			{
				stack = ItemStack.EMPTY;
			}
			followPlayer.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, stack);
			return EnumActionResult.SUCCESS;
			}
		}
		else if(followPlayer == null && loaded == 3 && player.getHeldItemMainhand().getItem() == ModItems.RAM_ROD)
		{
			cooldown = 15;
			this.loaded = 4;
			this.loadedShots = acceptedShots[loadedShotID].loadedShots;
			world.playSound(null, pos.x, pos.y, pos.z, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.PLAYERS, 0.8f, 1.2f);
			return EnumActionResult.SUCCESS;
		}
		else if(player.getHeldItemMainhand().isEmpty() && followPlayer == null)
		{
			followPlayer = player;
			return EnumActionResult.SUCCESS;
		}
		else
		{
			return EnumActionResult.FAIL;
		}
		}
		else
		{
			return EnumActionResult.PASS;
		}
		return EnumActionResult.PASS;
	}
	
	private void Fire()
	{
		if(!this.world.isRemote)
		{	
		Vec3d pos = this.getPositionVector();
		Vec3d dir = this.getLookVec();
		LoadType loadType = this.acceptedShots[loadedShotID];
		this.loadedShots -= 1;
		cooldown = loadType.cooldown;
		if(this.loadedShots <= 0)
		{
		loaded = 0;
		}
		
		Bullet shots[] = new Bullet[loadType.numberOfShots];
		
		double x = pos.x;
		double y = pos.y + (0.75*this.height);
		double z = pos.z;
		
		double inAccuracyMultiplier = (2.82*loadType.spread);
		
		for(int i = 0; i<loadType.numberOfShots; i++)
		{
		shots[i] = new Bullet(world, x, y, z, loadType.damage, this, true, loadType.strenght);
		
		dir = this.getLookVec();
		dir = new Vec3d(dir.x*10, dir.y*10, dir.z*10);
		dir = dir.addVector((Math.random()-0.5)*inAccuracyMultiplier, (Math.random()-0.5)*inAccuracyMultiplier, (Math.random()-0.5)*inAccuracyMultiplier);
		
		dir = dir.normalize();
		
		dir = new Vec3d(dir.x*5, dir.y*5, dir.z*5);
		
		shots[i].addVelocity(dir.x, dir.y, dir.z);
		world.spawnEntity(shots[i]);
		
		}
		dir = this.getLookVec();
		WorldServer server = world.getMinecraftServer().getWorld(this.dimension);
		server.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.x + dir.x*particleDistance, pos.y + dir.y*particleDistance + (1.2*this.height), pos.z + dir.z*particleDistance, this.smokeAmount, 0d, 0d, 0d, 0.05d, 0);		
		world.playSound(null, pos.x, pos.y, pos.z, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 1.5f, 0.5f*this.soundPitch);
		
		this.addVelocity((-dir.x/4)*kickBack, 0.1f*kickBack, (-dir.z/4)*kickBack);
		this.rotationPitch -= 20*kickBack;
		if(this.rotationPitch < -maxPitch)
		{
			rotationPitch = -maxPitch;
		}
		if(this.rotationPitch > maxPitch)
		{
			rotationPitch = maxPitch;
		}
		if(followPlayer != null)
		{
			followPlayer.rotationPitch = this.rotationPitch;
			followPlayer.addVelocity((-dir.x/4)*kickBack, 0.1f*kickBack, (-dir.z/4)*kickBack);
			followPlayer.velocityChanged = true;
		}
		
		this.velocityChanged = true;
		}
	}

}
