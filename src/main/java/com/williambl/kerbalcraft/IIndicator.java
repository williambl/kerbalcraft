package com.williambl.kerbalcraft;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IIndicator {

    boolean runRPCGet(IBlockState state, World world, BlockPos pos);
}
