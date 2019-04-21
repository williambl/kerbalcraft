package com.williambl.kerbalcraft.common.block;

import com.williambl.kerbalcraft.IIndicator;
import com.williambl.kerbalcraft.common.tileentity.TileEntityIndicator;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockIndicator extends KCTileEntityProviderBlock {

    public IIndicator iIndicator;
    public static final PropertyBool POWERED = PropertyBool.create("powered");

    public BlockIndicator(String registryName, IIndicator iIndicatorIn) {
        super(registryName);
        this.setDefaultState(this.blockState.getBaseState().withProperty(POWERED, Boolean.FALSE));
        iIndicator = iIndicatorIn;
    }

    // Redstone things

    @SuppressWarnings("deprecation")
    @Override
    public boolean canProvidePower(IBlockState state) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return blockState.getValue(POWERED) ? 15 : 0;
    }

    @SuppressWarnings("deprecation")
    @Override
    public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return blockState.getValue(POWERED) ? 15 : 0;
    }

    //Tile Entity things

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityIndicator(iIndicator);
    }

    //Blockstate things

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, POWERED);
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(POWERED, (meta & 1) > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(POWERED) ? 1 : 0;
    }

    @SuppressWarnings("deprecation")
    @Override
    public int getLightValue(IBlockState state) {
        return state.getValue(POWERED) ? 8 : 0;
    }

    public void activate(World worldIn, BlockPos pos, IBlockState blockstate) {
        worldIn.setBlockState(pos, blockstate.withProperty(POWERED, Boolean.TRUE));
    }

    public void deactivate(World worldIn, BlockPos pos, IBlockState blockstate) {
        worldIn.setBlockState(pos, blockstate.withProperty(POWERED, Boolean.FALSE));
    }

    public boolean isPowered(IBlockState blockstate) {
        return blockstate.getValue(POWERED);
    }
}
