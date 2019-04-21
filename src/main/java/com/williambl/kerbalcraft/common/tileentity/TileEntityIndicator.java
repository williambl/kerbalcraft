package com.williambl.kerbalcraft.common.tileentity;

import com.williambl.kerbalcraft.IIndicator;
import com.williambl.kerbalcraft.KerbalCraft;
import com.williambl.kerbalcraft.common.block.BlockIndicator;
import krpc.client.RPCException;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityIndicator extends TileEntity implements ITickable {

    IIndicator iIndicator;

    public TileEntityIndicator(IIndicator iIndicatorIn) {
        super();
        iIndicator = iIndicatorIn;
    }

    public TileEntityIndicator() {
    }

    @Override
    public void update() {
        if (world.isRemote)
            return;

        if (iIndicator == null) {
            BlockIndicator block = (BlockIndicator) world.getBlockState(pos).getBlock();
            iIndicator = block.iIndicator;
        }

        if (!KerbalCraft.connectionManager.isConnected())
            return;

        IBlockState blockState = world.getBlockState(pos);

        BlockIndicator block = (BlockIndicator) (blockState.getBlock());


        boolean result = false;
        try {
            result = iIndicator.runRPCGet(blockState, world, pos);
        } catch (RPCException e) {
            e.printStackTrace();
        }

        if (result) {
            block.activate(world, pos, blockState);
        } else {
            block.deactivate(world, pos, blockState);
        }
    }
}
