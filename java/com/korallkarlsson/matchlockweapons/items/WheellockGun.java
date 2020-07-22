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

public class WheellockGun extends Item implements IHasModel {

	
		private float inAccuracyMultiplier = 1;
		private float damage = 18;
		public int maxShots = 6;
		private int cooldown = 20;
		private double kickback = 0;
		private double failRate = 0;
		private int minSpring = 4;
		
		public WheellockGun(String name, float inAccuracy, float damage, int maxShots, int cooldown, double kickback, double failRate, int minSpring)
		{
			setUnlocalizedName(name);
			setRegistryName(name);
			setCreativeTab(CreativeTabs.COMBAT);
			setMaxStackSize(1);
			this.inAccuracyMultiplier = inAccuracy;
			this.damage = damage;
			this.maxShots = maxShots;
			this.cooldown = cooldown;
			this.kickback = kickback;
			this.failRate = failRate;
			this.minSpring = minSpring;
			ModItems.ITEMS.add(this);
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
						stack.getTagCompound().setInteger("spring", 0);
					}
				}
			
			
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		}
	
		
		@Override
		public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		
			if(stack.hasTagCompound())
			{
			if(stack.getTagCompound().hasKey("loadedshots"))
			{
				if(stack.getTagCompound().getInteger("loadedshots") > 0)
				{
					tooltip.add("Loaded " + "(" + stack.getTagCompound().getInteger("loadedshots") + ")");
				}
				else if(stack.getTagCompound().getInteger("loadedshots") == -1)
				{
					tooltip.add("Loaded *Reloading*");
				}
			}
			else
			{
				stack.getTagCompound().setInteger("loadedshots", 0);
			}
			}
			else
			{
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setInteger("loadedshots", 0);
				nbt.setInteger("spring", 0);
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
					
				player.getHeldItemMainhand().getTagCompound().setInteger("spring", 0);
				world.playSound(null, pos.x, pos.y, pos.z, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 1, 1);
				int shots = player.getHeldItemMainhand().getTagCompound().getInteger("loadedshots");
				ItemStack stack = new ItemStack(this, 1);
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setInteger("loadedshots", shots-1);
				nbt.setInteger("cooldown", cooldown);
				nbt.setInteger("spring", 0);
				stack.setTagCompound(nbt);
				player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, stack);
				
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
				
				Bullet shot = new Bullet(world, x, y, z, damage, player);
				
				
				dir = new Vec3d(dir.x*10, dir.y*10, dir.z*10);
				
				dir = dir.addVector((Math.random()-0.5)*inAccuracyMultiplier, (Math.random()-0.5)*inAccuracyMultiplier, (Math.random()-0.5)*inAccuracyMultiplier);
				
				shot.addVelocity(dir.x, dir.y, dir.z);
				
				
				world.spawnEntity(shot);
				
				}
				
			}
		}
		
		
		@Override
		public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

			if(!playerIn.getHeldItemMainhand().getTagCompound().hasKey("spring"))
			{
				playerIn.getHeldItemMainhand().getTagCompound().setInteger("spring", 0);
			}
					
			if(!playerIn.isInWater() && playerIn.getHeldItemMainhand().hasTagCompound())
			{
			if(playerIn.getHeldItemMainhand().getTagCompound().getInteger("loadedshots") > 0 && playerIn.getHeldItemMainhand().getTagCompound().getInteger("cooldown") <= 0 && playerIn.getHeldItemMainhand().getTagCompound().getInteger("spring") >= minSpring && !playerIn.isSneaking())
			{
				if(Math.random() > failRate)
				{
				Fire(worldIn, playerIn);
				}
				else
				{
				playerIn.getHeldItemMainhand().getTagCompound().setInteger("spring", 0);
				Jam(worldIn, playerIn);
				}
			}
			else if(!worldIn.isRemote && playerIn.getHeldItemMainhand().getTagCompound().getInteger("cooldown") <= 0 && playerIn.isSneaking() && playerIn.getHeldItemMainhand().getTagCompound().getInteger("spring") < minSpring)
			{
				int spring = playerIn.getHeldItemMainhand().getTagCompound().getInteger("spring");
				playerIn.getHeldItemMainhand().getTagCompound().setInteger("spring", spring + 1);
				worldIn.playSound(null, playerIn.getPositionVector().x, playerIn.getPositionVector().y, playerIn.getPositionVector().z, SoundEvents.BLOCK_TRIPWIRE_CLICK_ON, SoundCategory.PLAYERS, 0.2f, 0.5f);
			}
			else if(!worldIn.isRemote && playerIn.getHeldItemMainhand().getTagCompound().getInteger("loadedshots") == 0 && !playerIn.isSneaking())
			{
				playerIn.getHeldItemMainhand().getTagCompound().setInteger("spring", 0);
				worldIn.playSound(null, playerIn.getPositionVector().x, playerIn.getPositionVector().y, playerIn.getPositionVector().z, SoundEvents.BLOCK_WOOD_BUTTON_CLICK_ON, SoundCategory.PLAYERS, 1, 1);
			}
			}
			
		return super.onItemRightClick(worldIn, playerIn, handIn);
		}
		
}
