package com.williambl.kerbalcraft.common.command;

import com.williambl.kerbalcraft.KerbalCraft;
import krpc.client.RPCException;
import krpc.client.services.KRPC;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;

public class CommandKerbalCraft extends CommandBase {

    String commandName = "kerbalcraft";
    String usage = "/kerbalcraft <connect|disconnect> [host]";

    String[] subCommands = new String[]{"connect", "disconnect"};

    public CommandKerbalCraft() {
        super();
    }

    @Override
    public String getName() {
        return commandName;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return usage;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            sender.sendMessage(new TextComponentString(usage));
            return;
        }
        String subCommand = args[0];

        if (subCommand.equals("connect")) {
            if (args.length < 2) {
                sender.sendMessage(new TextComponentString("No host specified."));
                return;
            }
            InetAddress address;
            try {
                address = InetAddress.getByName(args[1]);
            } catch (UnknownHostException e) {
                e.printStackTrace();
                sender.sendMessage(new TextComponentString("Unknown Host."));
                return;
            }

            KRPC krpc = KerbalCraft.connectionManager.connect(address);
            if (krpc != null) {
                try {
                    sender.sendMessage(new TextComponentString("Connected to" + args[1] + " running kRPC version " + krpc.getStatus().getVersion()));
                    return;
                } catch (RPCException e) {
                    e.printStackTrace();
                    sender.sendMessage(new TextComponentString("Connected, but something went wrong conencting again to tell you that it was connected. Try reconnecting."));
                }
            }

            sender.sendMessage(new TextComponentString("Unable to connect."));

            return;
        }

        if (subCommand.equals("disconnect")) {
            KerbalCraft.connectionManager.disconnect();
            return;
        }

        sender.sendMessage(new TextComponentString(usage));
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {

        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, subCommands);
        }

        if (args[0].equals("connect")) {
            return getListOfStringsMatchingLastWord(args, "localhost", "127.0.0.1");
        }

        return Collections.emptyList();
    }
}
