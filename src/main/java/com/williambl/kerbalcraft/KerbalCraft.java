package com.williambl.kerbalcraft;

import com.williambl.kerbalcraft.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = KerbalCraft.MODID, version = KerbalCraft.VERSION)
public class KerbalCraft {

	@Mod.Instance("KerbalCraft")
	public static KerbalCraft instance;

    public static final String MODID = "kerbalcraft";
    public static final String VERSION = "1.2.1";

    @SidedProxy(clientSide="net.williambl.KerbalCraft.client.ClientProxy", serverSide="net.williambl.KerbalCraft.server.ServerProxy")
    public static CommonProxy proxy;

    @EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
    	proxy.preInit();
	}

    @EventHandler
	public void init(FMLInitializationEvent event)
	{
    	proxy.init();
	}

    @EventHandler
	public void Postinit(FMLPostInitializationEvent event)
	{
    	proxy.postInit();
	}

}