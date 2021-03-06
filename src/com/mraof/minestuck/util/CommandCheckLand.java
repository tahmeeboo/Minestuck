package com.mraof.minestuck.util;

import java.util.ArrayList;
import java.util.List;

import com.mraof.minestuck.world.MinestuckDimensionHandler;
import com.mraof.minestuck.world.lands.LandAspectRegistry;
import com.mraof.minestuck.world.lands.gen.ChunkProviderLands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class CommandCheckLand extends CommandBase
{
	
	@Override
	public String getName()
	{
		return "checkLand";
	}
	
	@Override
	public List getAliases()
	{
		ArrayList<String> list = new ArrayList<String>();
		list.add("land");
		list.add("getLand");
		list.add("checkLandAspects");
		list.add("getLandAspects");
		return list;
	}
	
	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return "commands.checkLand.usage";
	}
	
	@Override
	public void execute(ICommandSender sender, String[] args) throws CommandException
	{
		if(!(sender instanceof EntityPlayerMP))
			throw new PlayerNotFoundException("This command is only useable by players.");
		EntityPlayerMP player = (EntityPlayerMP) sender;
		
		if(MinestuckDimensionHandler.isLandDimension(player.dimension))
		{
			LandAspectRegistry.AspectCombination aspects = MinestuckDimensionHandler.getAspects(player.dimension);
			ChunkProviderLands chunkProvider = (ChunkProviderLands) player.worldObj.provider.createChunkGenerator();
			IChatComponent aspect1 = new ChatComponentTranslation("land."+aspects.aspectTerrain.getNames()[chunkProvider.nameIndex1]);
			IChatComponent aspect2 = new ChatComponentTranslation("land."+aspects.aspectTitle.getNames()[chunkProvider.nameIndex2]);
			IChatComponent toSend;
			if(chunkProvider.nameOrder)
				toSend = new ChatComponentTranslation("land.message.check", aspect1, aspect2);
			else toSend = new ChatComponentTranslation("land.message.check", aspect2, aspect1);
			player.addChatMessage(toSend);
		}
		else
		{
			player.addChatMessage(new ChatComponentTranslation("land.message.checkFail"));
		}
	}
	
	@Override
	public int getRequiredPermissionLevel()
	{
		return 0;
	}
	
}