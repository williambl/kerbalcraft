package com.williambl.kerbalcraft.common.block;

import com.williambl.kerbalcraft.KerbalCraft;
import krpc.client.RPCException;
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

public class KCBlock extends Block {

    public static final PropertyBool POWERED = PropertyBool.create("powered");

    public KCBlock(String registryName, MapColor mapColor) {
        super(Material.IRON, mapColor);
        this.setDefaultState(this.blockState.getBaseState().withProperty(POWERED, Boolean.FALSE));
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.setHardness(3);
        this.setResistance(5);
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!worldIn.isRemote) {
            if (state.getValue(POWERED)) {
                if (worldIn.isBlockPowered(pos))
                    return;
                else
                    worldIn.setBlockState(pos, state.withProperty(POWERED, Boolean.FALSE));
            }
            if (worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, state.withProperty(POWERED, Boolean.TRUE));
                runRPC();
            }
        }
    }

    public void runRPC () {
    }


    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, POWERED);
    }


    public int getMetaFromState(IBlockState state) {
        if (((Boolean)state.getValue(POWERED)).booleanValue())
            return 1;
        return 0;
    }
}
