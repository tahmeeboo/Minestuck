package com.mraof.minestuck.world.lands.title;

import com.mraof.minestuck.world.lands.gen.ChunkProviderLands;

public class LandAspectFrogs extends TitleAspect
{
	
	@Override
	public String getPrimaryName()
	{
		return "Frogs";
	}
	
	@Override
	public String[] getNames()
	{
		return new String[]{"frog"};
	}
	
	@Override
	protected void prepareChunkProvider(ChunkProviderLands chunkProvider)
	{
		
	}
	
}
