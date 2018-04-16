package com.williambl.kerbalcraft.common.block;

import com.williambl.kerbalcraft.KerbalCraft;
import krpc.client.Connection;
import krpc.client.RPCException;
import krpc.client.services.KRPC;
import krpc.client.services.SpaceCenter;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.io.IOException;

public class BlockStager extends KCBlock {

    public BlockStager(String registryName, MapColor mapColor) {
        super(registryName, mapColor);
    }

    @Override
    public void runRPC (IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        int power = worldIn.getStrongPower(pos);
        if (power == 0)
            return;

        try {
            SpaceCenter.Vessel vessel = KerbalCraft.spaceCenter.getActiveVessel();
            vessel.getControl().activateNextStage();
        } catch (RPCException e) {
            e.printStackTrace();
        }
    }

}
