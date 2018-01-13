package com.vova7865.commandomatic;

import com.vova7865.commandomatic.commands.CommandExtendedClone;
import com.vova7865.commandomatic.commands.CommandExtendedEnchant;
import com.vova7865.commandomatic.commands.CommandExtendedFill;
import com.vova7865.commandomatic.commands.CommandExtendedPublish;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommandRegistry {
	public static void overrideCommands(FMLServerStartingEvent e) {
		e.registerServerCommand(new CommandExtendedPublish());
    	e.registerServerCommand(new CommandExtendedEnchant());
    	e.registerServerCommand(new CommandExtendedFill());
    	e.registerServerCommand(new CommandExtendedClone());
	}
}
