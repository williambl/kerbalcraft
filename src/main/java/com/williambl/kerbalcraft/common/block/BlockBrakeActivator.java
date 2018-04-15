package com.williambl.kerbalcraft.common.block;

import com.williambl.kerbalcraft.KerbalCraft;
import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;
import net.minecraft.block.material.MapColor;

public class BlockBrakeActivator extends KCBlock {

    public BlockBrakeActivator(String registryName, MapColor mapColor) {
        super(registryName, mapColor);
    }

    public void runRPC (int power) {
        try {
            SpaceCenter.Vessel vessel = KerbalCraft.spaceCenter.getActiveVessel();
            vessel.getControl().setBrakes(power > 0);
        } catch (RPCException e) {
            e.printStackTrace();
        }
    }

}
