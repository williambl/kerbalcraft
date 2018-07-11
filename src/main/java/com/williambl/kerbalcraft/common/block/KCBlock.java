package com.williambl.kerbalcraft.common.block;

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

    public KCBlock(String registryName, MapColor mapColor) {
        super(Material.IRON, mapColor);
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.setHardness(3);
        this.setResistance(5);
        this.setRegistryName(registryName);
        this.setUnlocalizedName(this.getRegistryName().toString());
    }

}
