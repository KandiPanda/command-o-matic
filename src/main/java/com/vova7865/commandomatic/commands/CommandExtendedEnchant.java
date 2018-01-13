package com.vova7865.commandomatic.commands;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class CommandExtendedEnchant extends CommandBase
{

	/**
     * Gets the name of the command
     */
    public String getName()
    {
        return "enchant";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    /**
     * Gets the usage string for the command.
     */
    public String getUsage(ICommandSender sender)
    {
        return "/enchant <IGN> add <enchantment> [level] -- adds an enchantment; /enchant <IGN> set <enchantment> [level] -- add the specified enchantment and clears all other ones; /enchant <IGN> clear -- clears all enchantments";
    }

    /**
     * Callback for when the command is executed
     */
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 2)
        {
            throw new WrongUsageException("/enchant <IGN> add <enchantment> [level] OR /enchant <IGN> set <enchantment> [level] OR /enchant <IGN> clear", new Object[0]);
            //commands . enchant . usage
        }
        else
        {
        	String mode = args[1];
            EntityLivingBase entitylivingbase = (EntityLivingBase)getEntity(server, sender, args[0], EntityLivingBase.class);
            sender.setCommandStat(net.minecraft.command.CommandResultStats.Type.AFFECTED_ITEMS, 0);
            
           
            {
                ItemStack itemstack = entitylivingbase.getHeldItemMainhand();

                if (itemstack.isEmpty())
                {
                    throw new CommandException("commands.enchant.noItem", new Object[0]);
                }
                switch (mode) {
                case "set": {
                	Enchantment enchantment;
                    
                    try
                    {
                        enchantment = Enchantment.getEnchantmentByID(parseInt(args[2], 0));
                    }
                    catch (NumberInvalidException var12)
                    {
                        enchantment = Enchantment.getEnchantmentByLocation(args[2]);
                    }

                    if (enchantment == null)
                    {
                        throw new NumberInvalidException("commands.enchant.notFound", new Object[] {args[2]});
                    }
                	if(args.length < 3) {
                		break;
                	}
                	if (itemstack.hasTagCompound())
                    {
                        itemstack.removeSubCompound("ench");
                    }
                	int i = 1;
                	if (args.length >= 4)
                    {
                		i = Integer.parseInt(args[3]);
                    }	

                   

                    itemstack.addEnchantment(enchantment, i);
                    notifyCommandListener(sender, this, "commands.enchant.success", new Object[0]);
                    sender.setCommandStat(net.minecraft.command.CommandResultStats.Type.AFFECTED_ITEMS, 1);
                }
                	break;
                case "add": {
                	int i = 1;
                	if(args.length < 3) {
                		break;
                	}
                	Enchantment enchantment;
                    
                    try
                    {
                        enchantment = Enchantment.getEnchantmentByID(parseInt(args[2], 0));
                    }
                    catch (NumberInvalidException var12)
                    {
                        enchantment = Enchantment.getEnchantmentByLocation(args[2]);
                    }

                    if (enchantment == null)
                    {
                        throw new NumberInvalidException("commands.enchant.notFound", new Object[] {args[2]});
                    }
                	
                	if (args.length >= 4)
                    {
                		i = Integer.parseInt(args[3]);
                    }	

                   

                   itemstack.addEnchantment(enchantment, i);
                    notifyCommandListener(sender, this, "Adding enchantment succeeded", new Object[0]);
                    sender.setCommandStat(net.minecraft.command.CommandResultStats.Type.AFFECTED_ITEMS, 1);
                }break;
                case "clear":{
                	int index = 0;
                	if (itemstack.hasTagCompound())
                    {
                		try {
                			while (true) {
                				itemstack.getEnchantmentTagList().removeTag(index);
                				++index;
                			}
                		} catch(IndexOutOfBoundsException ioobe) {
                			notifyCommandListener(sender, this, "Disenchanting succeeded", new Object[0]);
                			return;
                		}
                    }
                	
                }break;
                default:
                	notifyCommandListener(sender, this, "Wrong mode", new Object[0]);
                	break;
                }
//                else if (!enchantment.canApply(itemstack))
//                {
//                    throw new CommandException("commands.enchant.cantEnchant", new Object[0]);
//                }
//                else
//                {
//                    if (args.length >= 3)
//                    {
//                        	i = CommandBase.parseInt(args[2]);
//                    }	
//
//                   
//
//                    //itemstack.addEnchantment(enchantment, i);
//                    notifyCommandListener(sender, this, "commands.enchant.success", new Object[0]);
//                    sender.setCommandStat(net.minecraft.command.CommandResultStats.Type.AFFECTED_ITEMS, 1);
                }
            }
        }
    

    /**
     * Get a list of options for when the user presses the TAB key
     */
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
    	String[] modes = new String[] {"add","set","clear"};
        if (args.length == 1)
        {
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        }
        else
        {
            if(args.length == 2) {
            	return getListOfStringsMatchingLastWord(args, modes);
            } else {
            	return ((args.length == 3)) ? getListOfStringsMatchingLastWord(args, Enchantment.REGISTRY.getKeys()) : Collections.emptyList();
            }
        }
    }

    /**
     * Return whether the specified command parameter index is a username parameter.
     */
    public boolean isUsernameIndex(String[] args, int index)
    {
        return index == 0;
    }
}