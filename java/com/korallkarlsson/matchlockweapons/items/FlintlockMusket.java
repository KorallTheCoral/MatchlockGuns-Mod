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

public class FlintlockMusket extends Item implements IHasModel {

	
		private float inAccuracyMultiplier = 1;
		private float damage = 18;
		private double kickback = 0;
		private double failRate = 0;
		private boolean explode = false;
		
		public FlintlockMusket(String name, float inAccuracy, float damage)
		{
			setUnlocalizedName(name);
			setRegistryName(name);
			setCreativeTab(CreativeTabs.COMBAT);
			setMaxStackSize(1);
			ModItems.ITEMS.add(this);
			this.inAccuracyMultiplier = inAccuracy;
			this.damage = damage;
		}
		
		public FlintlockMusket(String name, float inAccuracy, float damage, double kickback, double failRate)
		{
			setUnlocalizedName(name);
			setRegistryName(name);
			setCreativeTab(CreativeTabs.COMBAT);
			setMaxStackSize(1);
			ModItems.ITEMS.add(this);
			this.inAccuracyMultiplier = inAccuracy;
			this.damage = damage;
			this.kickback = kickback;
			this.failRate = failRate;
		}
		
		
		public FlintlockMusket(String name, float inAccuracy, float damage, double kickback, double failRate, boolean explode)
		{
			setUnlocalizedName(name);
			setRegistryName(name);
			setCreativeTab(CreativeTabs.COMBAT);
			setMaxStackSize(1);
			ModItems.ITEMS.add(this);
			this.inAccuracyMultiplier = inAccuracy;
			this.damage = damage;
			this.kickback = kickback;
			this.failRate = failRate;
			this.explode = explode;
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
	
		
		@Override
		public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		
			if(stack.hasTagCompound())
			{
			if(stack.getTagCompound().hasKey("loaded"))
			{
				if(stack.getTagCompound().getBoolean("loaded"))
				{
					tooltip.add("Loaded");
				}
			}
			else
			{
				stack.getTagCompound().setBoolean("loaded", false);
				stack.getTagCompound().setInteger("cooldown", 0);
			}
			}
			else
			{
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setBoolean("loaded", false);
				nbt.setInteger("cooldown", 0);
				stack.setTagCompound(nbt);
			}
		}
		

		private void Jam(World world, EntityPlayer player)
		{
			if(player.getHeldItemOffhand() == null || player.getHeldItemOffhand() == ItemStack.EMPTY)
			{
				if(!world.isRemote)
				{
				ItemStack stack = player.getHeldItemMainhand();
				stack.getTagCompound().setInteger("cooldown", 200);
				world.playSound(null, player.getPositionVector().x, player.getPositionVector().y, player.getPositionVector().z, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 1, 1);
				player.sendMessage(new TextComponentString("*Gun Jams*"));
				}
			}
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
				server.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.x + player.getLookVec().x, pos.y + player.getLookVec().y + 1.5, pos.z + player.getLookVec().z, 10, 0d, 0d, 0d, 0.05d, 0);		
				
				world.playSound(null, pos.x, pos.y, pos.z, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 1, 1);
				ItemStack stack = new ItemStack(this, 1);
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setBoolean("loaded", false);
				stack.setTagCompound(nbt);
				
				
				player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getTagCompound().setBoolean("loaded", false);
				
				/*
				if(player.getHeldItemMainhand().getItemDamage() + 1 > this.getMaxDamage())
				{
				//player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, stack);
				player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
				}
				else
				{
				player.getHeldItemMainhand().setItemDamage(player.getHeldItemMainhand().getItemDamage() + 1);
				}
				*/
				
				
				pos = player.getPositionVector();
				
				
				int range = 20;
				double x = pos.x;
				double y = pos.y + 1.5;
				double z = pos.z;
				
				Vec3d dir = player.getLookVec();
				
				//x += dir.x*2;
				//y += dir.y*2;
				//z += dir.z*2;
				
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
				
				Bullet shot = new Bullet(world, x, y, z, damage, player, explode, 1);
				
				
				dir = new Vec3d(dir.x*10, dir.y*10, dir.z*10);
				
				dir = dir.addVector((Math.random()-0.5)*inAccuracyMultiplier, (Math.random()-0.5)*inAccuracyMultiplier, (Math.random()-0.5)*inAccuracyMultiplier);
				
				shot.addVelocity(dir.x, dir.y, dir.z);
				
				
				world.spawnEntity(shot);
				
				
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
	
			if(!playerIn.isInWater() && playerIn.getHeldItemMainhand().hasTagCompound())
			{
			if(playerIn.getHeldItemMainhand().getTagCompound().getBoolean("loaded") && playerIn.getHeldItemMainhand().getTagCompound().getInteger("cooldown") <= 0)
			{
				if(Math.random() > failRate)
				{
				Fire(worldIn, playerIn);
				}
				else
				{
				Jam(worldIn, playerIn);
				}
			}
			else if(!worldIn.isRemote)
			{
				worldIn.playSound(null, playerIn.getPositionVector().x, playerIn.getPositionVector().y, playerIn.getPositionVector().z, SoundEvents.BLOCK_WOOD_BUTTON_CLICK_ON, SoundCategory.PLAYERS, 1, 1);
			}
			}
			
		return super.onItemRightClick(worldIn, playerIn, handIn);
		}
		
}
