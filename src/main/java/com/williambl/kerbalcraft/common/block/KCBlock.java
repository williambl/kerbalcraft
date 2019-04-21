package com.williambl.kerbalcraft.common.block;

import com.williambl.kerbalcraft.KerbalCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class KCBlock extends Block {

    public KCBlock(String registryName) {
        super(Material.IRON);
        this.setCreativeTab(KerbalCraft.tab);
        this.setHardness(3);
        this.setResistance(5);
        this.setRegistryName(registryName);
        this.setTranslationKey(this.getRegistryName().toString());
    }

    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
