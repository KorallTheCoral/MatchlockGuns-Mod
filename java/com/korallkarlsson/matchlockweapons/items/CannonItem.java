package com.korallkarlsson.matchlockweapons.items;

import java.util.List;

import com.korallkarlsson.matchlockweapons.Main;
import com.korallkarlsson.matchlockweapons.entities.EntityCannon;
import com.korallkarlsson.matchlockweapons.init.ModItems;
import com.korallkarlsson.matchlockweapons.util.IHasModel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CannonItem extends Item implements IHasModel {
 
	private Class<? extends EntityCannon> cannon;
	private String desc;
	
	public CannonItem(String name, Class<? extends EntityCannon> cannon, String desc)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxStackSize(1);
		this.cannon = cannon;
		ModItems.ITEMS.add(this);
		this.desc = desc;
		this.setCreativeTab(Main.CANNON_TAB);
	}
	
	@Override
	public CreativeTabs getCreativeTab() {
		return Main.CANNON_TAB;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(desc);
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	 
	@Override
	public void registerModels() {
  
		Main.proxy.registerItemRenderer(this, 0, "inventory");
		
	} 
	
	 
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if(!worldIn.isRemote && player.getHeldItemMainhand().getItem() == this)
		{
			EntityCannon canon = (EntityCannon) EntityList.newEntity(cannon, worldIn);
			
			if(canon != null)
			{
			canon.world = worldIn;
			canon.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
			canon.rotationYaw = player.rotationYaw;
			worldIn.spawnEntity(canon);
			player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
			}
		}
			
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

	
	
}
