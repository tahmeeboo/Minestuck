package com.mraof.minestuck.command;

import java.util.ArrayList;
import java.util.List;

import com.mraof.minestuck.util.GristAmount;
import com.mraof.minestuck.util.GristSet;
import com.mraof.minestuck.util.GristType;
import com.mraof.minestuck.util.MinestuckPlayerData;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandGristSend extends CommandBase
{
	
	@Override
	public String getCommandName()
	{
		return "gristSend";
	}
	
	@Override
	public List getCommandAliases()
	{
		ArrayList<String> list = new ArrayList<String>();
		list.add("gristSend");
		list.add("gristGive");
		list.add("sendGrist");
		list.add("giveGrist");
		return list;
	}
	
	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return (sender instanceof EntityPlayerMP) ? "commands.gristSend.usage" : "commands.playerOnly.redirectGrist";
	}
	
	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException
	{
		if(!(sender instanceof EntityPlayerMP))
			throw new PlayerNotFoundException("commands.playerOnly.redirectGrist");
		EntityPlayerMP player = (EntityPlayerMP) sender;
		
		if(args.length < 3 || args.length % 2 == 0)
			throw new WrongUsageException(this.getCommandUsage(sender));
		
		String receiver = args[0];
		EntityPlayerMP receivingPlayer = MinecraftServer.getServer().getConfigurationManager().getPlayerByUsername(receiver);
		if(receivingPlayer != null)
		{
			GristSet set = new GristSet();
			GristAmount[] parsedAmounts = CommandGrist.parseGrist(args, 1);
			for(GristAmount amount : parsedAmounts)
				set.addGrist(amount);
			
			GristSet playerGrist = MinestuckPlayerData.getGristSet(player);
			GristSet receiverGrist = MinestuckPlayerData.getGristSet(receivingPlayer);
			
			StringBuilder costStr = new StringBuilder();
			boolean first = true;
			for(GristType type : GristType.values())
			{
				int i = Math.max(0, Math.min(playerGrist.getGrist(type), set.getGrist(type)));
				
				if(i > 0)
				{
					playerGrist.addGrist(type, -i);
					receiverGrist.addGrist(type, i);
					
					if(!first)
						costStr.append(", ");
					costStr.append(i + " " + type.getDisplayName());
					first = false;
				}
			}
			
			notifyOperators(sender, this, "commands.gristSend.success", receiver, costStr.toString());
			
		} else throw new PlayerNotFoundException("Couldn't find player \"%s\".", receiver);
	}
	
	@Override
	public int getRequiredPermissionLevel()
	{
		return 0;
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender)
	{
		return true;
	}
}