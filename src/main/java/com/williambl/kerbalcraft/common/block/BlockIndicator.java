package com.williambl.kerbalcraft.common.block;

import com.williambl.kerbalcraft.IIndicator;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockIndicator extends KCBlock {

    public IIndicator iIndicator;

    public BlockIndicator(String registryName, MapColor mapColor, IIndicator iindicatorIn) {
        super(registryName, mapColor);
        iIndicator = iindicatorIn;
    }

    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return iIndicator.runRPCGet(blockState, blockAccess, pos, side, false);
    }

    public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return iIndicator.runRPCGet(blockState, blockAccess, pos, side, true);
    }
}
