package com.korallkarlsson.matchlockweapons.blocks;

import com.korallkarlsson.matchlockweapons.Main;
import com.korallkarlsson.matchlockweapons.init.ModRecipes;
import com.korallkarlsson.matchlockweapons.recipes.LatheRecipe;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IBlockStatePalette;

public class LatheBlock extends BlockBase {

	public LatheBlock(String name, Material material) {
		
		super(name, material);
		
		setSoundType(SoundType.WOOD);
		setHardness(3.0f);
		setResistance(400.0f);
		setCreativeTab(Main.MISC_TAB);
	}

	
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if(!worldIn.isRemote && hand == EnumHand.MAIN_HAND)
		{
		Item mainHandItem = playerIn.getHeldItemMainhand().getItem();
		Item offHandItem = playerIn.getHeldItemOffhand().getItem();
		
		for(LatheRecipe recipe : ModRecipes.LATHE_RECIPES)
		{
			if(recipe.tool == offHandItem && recipe.inPutItem == mainHandItem)
			{
				playerIn.addItemStackToInventory(new ItemStack(recipe.outPutItem, 1));
				
				ItemStack offHandStack = playerIn.getHeldItemOffhand();
				if(offHandStack.getItemDamage() + 1 >= offHandStack.getMaxDamage())
				{
					playerIn.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, ItemStack.EMPTY);
				}
				else
				{
					playerIn.getHeldItemOffhand().damageItem(1, playerIn);
				}
				
				playerIn.getHeldItemMainhand().setCount(playerIn.getHeldItemMainhand().getCount() - 1);
				
				
				//Effects
				
				WorldServer server = worldIn.getMinecraftServer().getWorld(playerIn.dimension);
				server.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, 1, 0d, 0d, 0d, 0.05d, 0);		

				worldIn.playSound(null, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, SoundEvents.BLOCK_STONE_HIT, SoundCategory.BLOCKS, 0.3f, 2);
				
				return true;
			}
		}
		}
		return false;
		//return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
	
}
