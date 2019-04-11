package com.williambl.kerbalcraft.common.command;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class ModCommands {

    static CommandKerbalCraft kerbalCraftCommand = new CommandKerbalCraft();

    public static void registerCommands(FMLServerStartingEvent e) {
        e.registerServerCommand(kerbalCraftCommand);
    }
}
