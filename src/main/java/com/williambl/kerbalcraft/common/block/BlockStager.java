package com.williambl.kerbalcraft.common.block;

import krpc.client.Connection;
import krpc.client.RPCException;
import krpc.client.services.KRPC;
import krpc.client.services.SpaceCenter;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.io.IOException;

public class BlockStager extends Block {


    public BlockStager(String registryName, Material material, MapColor mapColor, float hardness, float resistance) {
        super(material, mapColor);
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!worldIn.isRemote) {
            if (worldIn.isBlockPowered(pos)) {
                Connection connection = null;
                try {
                    connection = Connection.newInstance();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                KRPC krpc = KRPC.newInstance(connection);
                try {
                    System.out.println("Connected to kRPC version " + krpc.getStatus().getVersion());

                    SpaceCenter spaceCenter = SpaceCenter.newInstance(connection);
                    SpaceCenter.Vessel vessel = null;

                    vessel = spaceCenter.getActiveVessel();

                    vessel.getControl().activateNextStage();

                } catch (RPCException e) {
                    e.printStackTrace();
                }
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
