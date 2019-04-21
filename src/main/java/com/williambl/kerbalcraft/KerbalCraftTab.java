package com.williambl.kerbalcraft;

import com.williambl.kerbalcraft.common.block.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class KerbalCraftTab extends CreativeTabs {

    public KerbalCraftTab() {
        super(KerbalCraft.MODID);
    }

    @Nonnull
    @Override
    public ItemStack createIcon() {
        return new ItemStack(Item.getItemFromBlock(ModBlocks.STAGER));
    }

}
