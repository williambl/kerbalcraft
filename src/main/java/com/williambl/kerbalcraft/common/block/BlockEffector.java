package com.williambl.kerbalcraft.common.block;

import com.williambl.kerbalcraft.IEffector;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BlockEffector extends KCBlock {

    public static final PropertyBool POWERED = PropertyBool.create("powered");

    public IEffector iEffector;

    public BlockEffector(String registryName, MapColor mapColor, IEffector iEffectorIn) {
        super(registryName, mapColor);
        this.setDefaultState(this.blockState.getBaseState().withProperty(POWERED, Boolean.FALSE));
        iEffector = iEffectorIn;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!worldIn.isRemote) {
            if (state.getValue(POWERED)) {
                if (worldIn.isBlockPowered(pos))
                    return;
                else
                    worldIn.setBlockState(pos, state.withProperty(POWERED, Boolean.FALSE));
            }
            if (worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, state.withProperty(POWERED, Boolean.TRUE));
            }
            iEffector.runRPC(state, worldIn, pos, blockIn, fromPos);
        }
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, POWERED);
    }

    public int getMetaFromState(IBlockState state) {
        return (state.getValue(POWERED)) ? 1 : 0;
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(POWERED, meta == 1);
    }
}
