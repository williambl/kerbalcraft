package com.williambl.kerbalcraft.common;

import com.williambl.kerbalcraft.common.command.ModCommands;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

    public void preInit () {}

    public void init () {}

    public void postInit() {
    }

    public void serverStart(FMLServerStartingEvent event) {
        ModCommands.registerCommands(event);
    }
}
