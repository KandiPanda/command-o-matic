package com.vova7865.commandomatic;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main
{
	public static final String MODID = "commandomatic";
    public static final String VERSION = "1.3";
    
	@Instance(MODID)
	public static Main instance;
    
    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
    	CommandRegistry.overrideCommands(event);
    }
    @EventHandler
    public void init(FMLInitializationEvent event)
    { 
    	
    }
}
