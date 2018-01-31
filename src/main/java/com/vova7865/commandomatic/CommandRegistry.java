package com.vova7865.commandomatic;

import com.vova7865.commandomatic.commands.CommandExtendedClone;
import com.vova7865.commandomatic.commands.CommandExtendedEnchant;
import com.vova7865.commandomatic.commands.CommandExtendedEntityData;
import com.vova7865.commandomatic.commands.CommandExtendedFill;
import com.vova7865.commandomatic.commands.CommandExtendedPublish;

import net.minecraft.command.CommandBase;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommandRegistry {
	public static void overrideCommands(FMLServerStartingEvent e) {
		doRegister(e, new CommandBase[]{
				new CommandExtendedClone(),
				new CommandExtendedEnchant(),
				new CommandExtendedEntityData(),
				new CommandExtendedFill(),
				new CommandExtendedPublish()
				});
	}
	private static void doRegister(FMLServerStartingEvent e, CommandBase... r) {
		for(CommandBase cmd:r) {
			e.registerServerCommand(cmd);
		}
	}
}
