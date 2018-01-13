package com.vova7865.commandomatic.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameType;

public class CommandExtendedPublish extends CommandBase
{
    /**
     * Gets the name of the command
     */
    public String getName()
    {
        return "publish";
    }

    /**
     * Gets the usage string for the command.
     */
    public String getUsage(ICommandSender sender)
    {
        return "commands.publish.usage";
    }
    public GameType parseGm(String attempt) {
    	GameType gm = GameType.NOT_SET;
    	try {
			gm = GameType.parseGameTypeWithDefault(parseInt(attempt), GameType.NOT_SET);
		} catch (NumberInvalidException e){
			gm = GameType.parseGameTypeWithDefault(attempt, GameType.NOT_SET);
		}
    	
    	return gm;
    }
    public boolean parseCheats(String attempt) {
    	String attempt2 = attempt.toLowerCase();
		boolean cheats = ((((attempt2 == "true")||(attempt2 == "yes"))||((attempt2 == "y")||(attempt2 == "t")))||attempt2 == "+")&&!((((attempt2 == "false")||(attempt2 == "no"))||((attempt2 == "y")||(attempt2 == "f")))||attempt2 == "-");
		
    	// TODO Auto-generated method stub
    	return cheats;
	}
    /**
     * Callback for when the command is executed
     */
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {	
    	boolean cheats = false;
    	GameType gm = GameType.SURVIVAL;
    	if(args.length == 1) {
    		gm = parseGm(args[0]);
    	}
    	if(args.length == 2) {
    		gm = parseGm(args[0]);
    		cheats = parseCheats(args[1]);
    	}
    	if(args.length == 0) {
    		throw new WrongUsageException("/publish <gamemode> [cheats]", new Object[0]);
    	}
        String s = server.shareToLAN(gm, cheats);
        

        if (s != null)
        {
            notifyCommandListener(sender, this, "commands.publish.started", new Object[] {s});
        }
        else
        {
            notifyCommandListener(sender, this, "commands.publish.failed", new Object[0]);
        }
    }
}