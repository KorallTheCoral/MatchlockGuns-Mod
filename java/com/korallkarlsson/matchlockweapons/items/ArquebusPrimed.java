package com.korallkarlsson.matchlockweapons.items;

import java.util.List;

import com.korallkarlsson.matchlockweapons.Main;
import com.korallkarlsson.matchlockweapons.entities.Bullet;
import com.korallkarlsson.matchlockweapons.init.ModItems;
import com.korallkarlsson.matchlockweapons.util.IHasModel;

import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.Vector3d;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

public class ArquebusPrimed extends Item implements IHasModel {

		private int numberOfShots;
		private double kickback;
		private float damage;
		private double inAccuracyMultiplier;
		private Arquebus unPrimedGun;
		
		public ArquebusPrimed(String name)
		{
			setUnlocalizedName(name);
			setRegistryName(name);
			setCreativeTab(null);
			setMaxStackSize(1);
			ModItems.ITEMS.add(this);
		}

		public ArquebusPrimed(String name, float damage, int numberOfShots, double inAccuracyMultiplier, double kickback, Arquebus unPrimedGun)
		{
			setUnlocalizedName(name);
			setRegistryName(name);
			setCreativeTab(null);
			setMaxStackSize(1);
			
			this.numberOfShots = numberOfShots;
			this.damage = damage;
			this.inAccuracyMultiplier = inAccuracyMultiplier;
			this.unPrimedGun = unPrimedGun;
			this.kickback = kickback;
			ModItems.ITEMS.add(this);
		}
		
		@Override
		public void registerModels() {
			
			Main.proxy.registerItemRenderer(this, 0, "inventory");
			
		}
		
		
		@Override
		public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
			tooltip.add("Loaded");
			tooltip.add("§e" + "Primed");
		}
		
		
		@Override
		public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
			
			if(entityIn instanceof EntityPlayer)
			{
				//((EntityPlayer) entityIn).limbSwing = 0;
			}
			
			if(!worldIn.isRemote)
			{
				int damage = 0;
				if(stack.hasTagCompound())
				{
				damage = stack.getTagCompound().getInteger("timer");
				}
				else
				{
				NBTTagCompound timerNBT = new NBTTagCompound();
				timerNBT.setInteger("timer", 0);
				stack.setTagCompound(timerNBT);
				}
				
				if(damage >= 18 || entityIn.isInWater() || worldIn.isRainingAt(entityIn.getPosition()))
				{
					stack = new ItemStack(unPrimedGun, 1);
					NBTTagCompound nbt = new NBTTagCompound();
					nbt.setBoolean("loaded", true);
					stack.setTagCompound(nbt);
					entityIn.replaceItemInInventory(itemSlot, stack);
				}
				else
				{
					int add = 0;
					
					if(Math.random() >= 0.95)
					{
						add = 1;
					}
					
					stack.getTagCompound().setInteger("timer", damage + add);
				}
			}
			
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		}
		
		private void Fire(World world, EntityPlayer player)
		{
			
			Vec3d pos = player.getPositionVector();
			if(player.getHeldItemOffhand() == null || player.getHeldItemOffhand() == ItemStack.EMPTY || player.getHeldItemOffhand().isEmpty())
			{
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.x + player.getLookVec().x, pos.y + 1 + player.getLookVec().y, pos.z + player.getLookVec().z, 0, 0, 0, 0);
			}
			
			if(player.getHeldItemOffhand() == null || player.getHeldItemOffhand() == ItemStack.EMPTY || player.getHeldItemOffhand().isEmpty())
			{
				if(!world.isRemote)
				{
					
					WorldServer server = world.getMinecraftServer().getWorld(player.dimension);
				server.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.x + player.getLookVec().x, pos.y + player.getLookVec().y + 1.5, pos.z + player.getLookVec().z, 10, 0d, 0d, 0d, 0.05d, 0);		

				world.playSound(null, pos.x, pos.y, pos.z, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 1, 1);
				ItemStack stack = new ItemStack(unPrimedGun, 1);
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setBoolean("loaded", false);
				stack.setTagCompound(nbt);
				player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, stack);
				
				pos = player.getPositionVector();
				
				
				int range = 20;
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
				
				//x += dir.x*2;
				//y += dir.y*2;
				//z += dir.z*2;
				
				Bullet shots[] = new Bullet[numberOfShots];
				
				
				
				for(int i = 0; i<numberOfShots; i++)
				{
				shots[i] = new Bullet(world, x, y, z, damage, player);
				
				dir = player.getLookVec();
				
				dir = new Vec3d(dir.x*10, dir.y*10, dir.z*10);
				
				dir = dir.addVector((Math.random()-0.5)*inAccuracyMultiplier, (Math.random()-0.5)*inAccuracyMultiplier, (Math.random()-0.5)*inAccuracyMultiplier);
				
				shots[i].addVelocity(dir.x, dir.y, dir.z);
				world.spawnEntity(shots[i]);
				
				}
				
				/*
				for(int i = 0; i < range; i ++)
				{
					pos = new BlockPos(Math.round(x),Math.round(y),Math.round(z));
					
					if(world.getBlockState(pos).isNormalCube() && false)
					{
						i = range;
						break;
					}
					else
					{
					world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, x, y, z, 0, 0, 0, 0);
					//world.setBlockToAir(pos);

					if(!world.isRemote)
					{
					List<Entity> entities = world.getEntitiesInAABBexcluding(player, new AxisAlignedBB(pos.subtract(new Vec3i(2, 2, 2)), new BlockPos(2, 2, 2)), null);
					for(Entity entity : entities)
					{
					if(entity.isEntityAlive())
					{
						entity.attackEntityFrom(DamageSource.GENERIC, 18);
						i = range;
						break;
					}
					}
					}
					}
				
					
					x += dir.x;
					y += dir.y;
					z += dir.z;
					
					
				}*/
				
				}
				
			}
		}
		
		@Override
		public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
	
			Fire(worldIn, playerIn);
			
		return super.onItemRightClick(worldIn, playerIn, handIn);
		}
		
}
