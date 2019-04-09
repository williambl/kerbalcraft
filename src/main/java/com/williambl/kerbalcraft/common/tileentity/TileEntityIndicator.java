package com.williambl.kerbalcraft.common.tileentity;

import com.williambl.kerbalcraft.IIndicator;
import com.williambl.kerbalcraft.common.block.BlockIndicator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityIndicator extends TileEntity implements ITickable {

    IIndicator iIndicator;

    public TileEntityIndicator(IIndicator iIndicatorIn) {
        super();
        iIndicator = iIndicatorIn;
    }

    @Override
    public void update() {
        if (world.isRemote)
            return;

        IBlockState blockState = world.getBlockState(pos);

        BlockIndicator block = (BlockIndicator) (blockState.getBlock());
        if (iIndicator.runRPCGet(blockState, world, pos)) {
            block.activate(world, pos, blockState);
        } else {
            block.deactivate(world, pos, blockState);
        }
    }
}
