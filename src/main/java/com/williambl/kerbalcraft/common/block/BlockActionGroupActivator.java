package com.williambl.kerbalcraft.common.block;

import com.williambl.kerbalcraft.KerbalCraft;
import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;
import net.minecraft.block.material.MapColor;

public class BlockActionGroupActivator extends KCBlock {

    int actionGroup;

    public BlockActionGroupActivator(String registryName, MapColor mapColor, int actionGroup) {
        super(registryName, mapColor);
        this.actionGroup = actionGroup;
    }

    public void runRPC (int power) {
        try {
            SpaceCenter.Vessel vessel = KerbalCraft.spaceCenter.getActiveVessel();
            vessel.getControl().setActionGroup(actionGroup, power > 0);
        } catch (RPCException e) {
            e.printStackTrace();
        }
    }

}
