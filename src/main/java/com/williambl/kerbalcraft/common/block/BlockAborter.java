package com.williambl.kerbalcraft.common.block;

import com.williambl.kerbalcraft.KerbalCraft;
import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockAborter extends KCBlock {

    public BlockAborter(String registryName, MapColor mapColor) {
        super(registryName, mapColor);
    }

    @Override
    public void runRPC (IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        int power = worldIn.getStrongPower(pos);
        try {
            SpaceCenter.Vessel vessel = KerbalCraft.spaceCenter.getActiveVessel();
            vessel.getControl().setAbort(power > 0);
        } catch (RPCException e) {
            e.printStackTrace();
        }
    }

}
