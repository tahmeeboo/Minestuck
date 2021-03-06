package com.mraof.minestuck.world.lands.terrain;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockRedSandstone;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.util.WeightedRandomChestContent;

import com.mraof.minestuck.util.AlchemyRecipeHandler;
import com.mraof.minestuck.world.lands.LandAspectRegistry;
import com.mraof.minestuck.world.lands.decorator.ILandDecorator;
import com.mraof.minestuck.world.lands.decorator.SurfaceDecoratorVein;

public class LandAspectSandstone extends TerrainAspect
{
	
	private final IBlockState[] surfaceBlocks;
	private final IBlockState[] upperBlocks;
	private final IBlockState[] structureBlocks;
	private final Vec3 skyColor;
	private final String name;
	private final List<TerrainAspect> variations;
	
	public LandAspectSandstone()
	{
		this("Sandstone");
	}
	
	public LandAspectSandstone(String name)
	{
		variations = new ArrayList<TerrainAspect>();
		this.name = name;
		if(name.equals("Sandstone"))
		{
			surfaceBlocks = new IBlockState[] {Blocks.sandstone.getDefaultState()};
			upperBlocks = new IBlockState[] {Blocks.stone.getDefaultState()};
			structureBlocks = new IBlockState[] {Blocks.sandstone.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.SMOOTH), Blocks.stonebrick.getDefaultState()};
			skyColor = new Vec3(0.9D, 0.7D, 0.05D);
			
			List<WeightedRandomChestContent> list = new ArrayList<WeightedRandomChestContent>();
			list.add(new WeightedRandomChestContent(new ItemStack(Blocks.sandstone, 1, 0), 4, 15, 6));
			list.add(new WeightedRandomChestContent(new ItemStack(Blocks.sandstone, 1, 1), 2, 7, 4));
			list.add(new WeightedRandomChestContent(new ItemStack(Blocks.sandstone, 1, 2), 1, 4, 3));
			list.add(new WeightedRandomChestContent(new ItemStack(Blocks.red_sandstone, 1, 0), 2, 6, 3));
			list.add(new WeightedRandomChestContent(new ItemStack(Blocks.red_sandstone, 1, 1), 1, 3, 2));
			list.add(new WeightedRandomChestContent(new ItemStack(Blocks.sand, 1, 0), 2, 7, 4));
			
			lootMap.put(AlchemyRecipeHandler.BASIC_MEDIUM_CHEST, list);
			
			variations.add(this);
			variations.add(new LandAspectSandstone("SandstoneRed"));
		}
		else
		{
			surfaceBlocks = new IBlockState[] {Blocks.red_sandstone.getDefaultState()};
			upperBlocks = new IBlockState[] {Blocks.stone.getDefaultState()};
			structureBlocks = new IBlockState[] {Blocks.red_sandstone.getDefaultState().withProperty(BlockRedSandstone.TYPE, BlockRedSandstone.EnumType.SMOOTH), Blocks.stonebrick.getDefaultState()};
			skyColor = new Vec3(0.9D, 0.5D, 0.05D);
			
			List<WeightedRandomChestContent> list = new ArrayList<WeightedRandomChestContent>();
			list.add(new WeightedRandomChestContent(new ItemStack(Blocks.red_sandstone, 1, 0), 4, 15, 6));
			list.add(new WeightedRandomChestContent(new ItemStack(Blocks.red_sandstone, 1, 1), 2, 7, 4));
			list.add(new WeightedRandomChestContent(new ItemStack(Blocks.red_sandstone, 1, 2), 1, 4, 3));
			list.add(new WeightedRandomChestContent(new ItemStack(Blocks.sandstone, 1, 0), 2, 6, 3));
			list.add(new WeightedRandomChestContent(new ItemStack(Blocks.sandstone, 1, 1), 1, 3, 2));
			list.add(new WeightedRandomChestContent(new ItemStack(Blocks.sand, 1, 1), 2, 7, 4));
			
			lootMap.put(AlchemyRecipeHandler.BASIC_MEDIUM_CHEST, list);
		}
	}
	
	@Override
	public String getPrimaryName()
	{
		return name;
	}
	
	@Override
	public String[] getNames()
	{
		return new String[] {"sandstone", "desertStone"};
	}
	
	@Override
	public IBlockState[] getSurfaceBlocks()
	{
		return surfaceBlocks;
	}
	
	@Override
	public IBlockState[] getUpperBlocks()
	{
		return upperBlocks;
	}
	
	@Override
	public IBlockState[] getStructureBlocks()
	{
		return structureBlocks;
	}
	
	@Override
	public IBlockState getDecorativeBlockFor(IBlockState state)
	{
		if(state.getBlock() == Blocks.stonebrick)
			return Blocks.stonebrick.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.CHISELED);
		else if(state.getBlock() == Blocks.sandstone)
			return Blocks.sandstone.getDefaultState().withProperty(BlockSandStone.TYPE, BlockSandStone.EnumType.CHISELED);
		else return Blocks.red_sandstone.getDefaultState().withProperty(BlockRedSandstone.TYPE, BlockRedSandstone.EnumType.CHISELED);
	}
	
	@Override
	public List<ILandDecorator> getOptionalDecorators()
	{
		List<ILandDecorator> list = new ArrayList<ILandDecorator>();
		return list;
	}
	
	@Override
	public List<ILandDecorator> getRequiredDecorators()
	{
		List<ILandDecorator> list = new ArrayList<ILandDecorator>();
		IBlockState sand = Blocks.sand.getDefaultState();
		if(name.equals("SandstoneRed"))
				sand = sand.withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
		list.add(new SurfaceDecoratorVein(sand, 10, 32));
		return list;
	}
	
	@Override
	public int getDayCycleMode()
	{
		return 0;
	}
	
	@Override
	public Vec3 getFogColor()
	{
		return skyColor;
	}
	
	@Override
	public TerrainAspect getPrimaryVariant()
	{
		return LandAspectRegistry.fromName("Sandstone");
	}
	
	@Override
	public List<TerrainAspect> getVariations()
	{
		return variations;
	}
	
}
