package com.williambl.kerbalcraft;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public interface IIndicator {

    int runRPCGet (IBlockState state, IBlockAccess blockAccess, BlockPos pos, EnumFacing side, boolean strongPower);
}
