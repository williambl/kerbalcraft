package com.williambl.kerbalcraft;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public interface IEffector {

    void runRPC(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos);
}
