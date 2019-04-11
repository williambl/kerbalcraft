package com.williambl.kerbalcraft.common;

import com.williambl.kerbalcraft.KerbalCraft;
import com.williambl.kerbalcraft.common.command.ModCommands;
import krpc.client.Connection;
import krpc.client.RPCException;
import krpc.client.services.KRPC;
import krpc.client.services.SpaceCenter;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.io.IOException;

public class CommonProxy {

    public void preInit () {}

    public void init () {}

    public void postInit () {
        try {
            KerbalCraft.rpcConn = Connection.newInstance();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        KerbalCraft.krpc = KRPC.newInstance(KerbalCraft.rpcConn);
        try {
            System.out.println("Connected to kRPC version " + KerbalCraft.krpc.getStatus().getVersion());

            KerbalCraft.spaceCenter = SpaceCenter.newInstance(KerbalCraft.rpcConn);

        } catch (RPCException e) {
            e.printStackTrace();
        }
    }

    public void serverStart(FMLServerStartingEvent event) {
        ModCommands.registerCommands(event);
    }
}
