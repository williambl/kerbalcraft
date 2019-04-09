package com.williambl.kerbalcraft.common.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class KCTileEntityProviderBlock extends KCBlock implements ITileEntityProvider {

    public KCTileEntityProviderBlock(String registryName, MapColor mapColor) {
        super(registryName, mapColor);
        this.hasTileEntity = true;
    }

    @Nullable
    @Override
    abstract public TileEntity createNewTileEntity(World worldIn, int meta);

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        super.breakBlock(world, pos, state);
        world.removeTileEntity(pos);
    }

}
