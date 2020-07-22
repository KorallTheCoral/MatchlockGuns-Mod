package com.korallkarlsson.matchlockweapons.init;

import java.util.ArrayList;
import java.util.List;

import com.korallkarlsson.matchlockweapons.blocks.LatheBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {
	
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block LATHE = new LatheBlock("lathe", Material.WOOD);
	
}
