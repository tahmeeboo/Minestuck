package com.mraof.minestuck.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.oredict.OreDictionary;
import static net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.mraof.minestuck.Minestuck;
import com.mraof.minestuck.MinestuckConfig;
import com.mraof.minestuck.modSupport.ExtraUtilitiesSupport;
import com.mraof.minestuck.modSupport.Minegicka3Support;
import com.mraof.minestuck.modSupport.ModSupport;
import com.mraof.minestuck.modSupport.NeverSayNetherSupport;
import com.mraof.minestuck.modSupport.TinkersConstructSupport;
import com.mraof.minestuck.modSupport.minetweaker.Minetweaker3Support;

import static com.mraof.minestuck.util.CombinationRegistry.MODE_AND;
import static com.mraof.minestuck.util.CombinationRegistry.MODE_OR;

public class AlchemyRecipeHandler
{
	public static final String BASIC_MEDIUM_CHEST = "minestuck:basicMediumChest";	//Internal key for basic medium loot.
	public static final List<WeightedRandomChestContent> basicMediumChest = new ArrayList<WeightedRandomChestContent>();
	
	private static HashMap<List<Object>, Object> recipeList;
	private static HashMap<List<Object>, Boolean> lookedOver;
	private static int returned = 0;
	private static IRecipe cardRecipe;
	private volatile static boolean cardRecipeAdded;

	public static void registerVanillaRecipes() {
		
		//Set up Alchemiter recipes
		//Blocks
//		GristRegistry.addGristConversion(new ItemStack(Blocks.bedrock), false, new GristSet(new GristType[] {GristType.Artifact, GristType.Zillium}, new int[] {1, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.carpet), false, new GristSet(new GristType[] {GristType.Chalk}, new int[] {2}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.cobblestone), false, new GristSet(new GristType[] {GristType.Build}, new int[] {1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.deadbush), false, new GristSet(new GristType[] {GristType.Amber, GristType.Sulfur}, new int[] {1, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.dirt), false, new GristSet(new GristType[] {GristType.Build}, new int[] {1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.dragon_egg), false, new GristSet(new GristType[] {GristType.Uranium, GristType.Tar, GristType.Zillium}, new int[] {64, 64, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.end_stone), false, new GristSet(new GristType[] {GristType.Shale, GristType.Chalk}, new int[] {1, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.furnace), false, new GristSet(new GristType[] {GristType.Tar, GristType.Build}, new int[] {4, 8}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.glass), false, new GristSet(new GristType[] {GristType.Quartz}, new int[] {1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.grass), false, new GristSet(new GristType[] {GristType.Build}, new int[] {1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.gravel), false, new GristSet(new GristType[] {GristType.Shale}, new int[] {1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.hardened_clay), false, new GristSet(new GristType[] {GristType.Shale, GristType.Marble}, new int[] {16, 4}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.hay_block), false, new GristSet(new GristType[] {GristType.Iodine}, new int[] {72}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.ice), false, new GristSet(new GristType[] {GristType.Cobalt}, new int[] {8}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.lapis_block), false, new GristSet(new GristType[] {GristType.Amethyst}, new int[] {36}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.leaves), false, new GristSet(new GristType[] {GristType.Build, GristType.Amber}, new int[] {1, 1}));		
		GristRegistry.addGristConversion(new ItemStack(Blocks.log), false, new GristSet(new GristType[] {GristType.Build}, new int[] {8}));		
		GristRegistry.addGristConversion(new ItemStack(Blocks.log2), false, new GristSet(new GristType[] {GristType.Build}, new int[] {8}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.melon_block), false, new GristSet(new GristType[] {GristType.Amber, GristType.Chalk, GristType.Build}, new int[] {8, 8, 2}));
//		GristRegistry.addGristConversion(new ItemStack(Blocks.monster_egg), false, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Chalk, GristType.Caulk}, new int[] {3, 2, 2, 2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.mossy_cobblestone), false, new GristSet(new GristType[] {GristType.Iodine, GristType.Build}, new int[] {1, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.mycelium), false, new GristSet(new GristType[] {GristType.Iodine, GristType.Ruby, GristType.Build}, new int[] {2, 2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.netherrack), false, new GristSet(new GristType[] {GristType.Build, GristType.Tar}, new int[] {1, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.noteblock), false, new GristSet(new GristType[] {GristType.Build, GristType.Garnet}, new int[] {16, 4}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.obsidian), false, new GristSet(new GristType[] {GristType.Cobalt, GristType.Tar, GristType.Build}, new int[] {1, 1, 6}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.piston), false, new GristSet(new GristType[] {GristType.Build, GristType.Garnet}, new int[] {8, 8}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.planks), false, new GristSet(new GristType[] {GristType.Build}, new int[] {2}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.pumpkin), false, new GristSet(new GristType[] {GristType.Amber, GristType.Caulk}, new int[] {6, 2}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.sand), false, new GristSet(new GristType[] {GristType.Shale}, new int[] {1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.sandstone), false, new GristSet(new GristType[] {GristType.Shale}, new int[] {4}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.sapling), false, new GristSet(new GristType[] {GristType.Build}, new int[] {16})); 
		GristRegistry.addGristConversion(new ItemStack(Blocks.snow), false, new GristSet(new GristType[] {GristType.Cobalt, GristType.Build}, new int[] {4, 2}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.snow_layer), false, new GristSet(new GristType[] {GristType.Cobalt}, new int[] {4}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.soul_sand), false, new GristSet(new GristType[] {GristType.Tar, GristType.Shale}, new int[] {1, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.sponge, 1, 0), false, new GristSet(new GristType[] {GristType.Amber, GristType.Sulfur}, new int[] {20, 30}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.stained_hardened_clay), false, new GristSet(new GristType[] {GristType.Shale, GristType.Marble}, new int[] {12, 8}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.stone), false, new GristSet(new GristType[] {GristType.Build}, new int[] {1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.stone_button), false, new GristSet(new GristType[] {GristType.Build, GristType.Garnet}, new int[] {2, 3}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.stone_slab, 1, 5), true, new GristSet(new GristType[] {GristType.Build}, new int[] {1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.stonebrick, 1, 1), true, new GristSet(new GristType[] {GristType.Build, GristType.Shale}, new int[] {10, 6}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.stonebrick, 1, 2), true, new GristSet(new GristType[] {GristType.Build, GristType.Shale, GristType.Iodine}, new int[] {10, 2, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.stonebrick, 1, 3), true, new GristSet(new GristType[] {GristType.Build, GristType.Shale}, new int[] {12, 4}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.web), false, new GristSet(new GristType[] {GristType.Chalk}, new int[] {6}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.wooden_slab), false, new GristSet(new GristType[] {GristType.Build}, new int[] {1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.wool, 1, 0), true, new GristSet(new GristType[] {GristType.Chalk}, new int[] {4}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.wool, 1, 1), true, new GristSet(new GristType[] {GristType.Chalk, GristType.Garnet, GristType.Amber}, new int[] {4, 1, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.wool, 1, 10), true, new GristSet(new GristType[] {GristType.Chalk, GristType.Amethyst, GristType.Garnet}, new int[] {4, 1, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.wool, 1, 11), true, new GristSet(new GristType[] {GristType.Chalk, GristType.Amethyst}, new int[] {4, 2}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.wool, 1, 12), true, new GristSet(new GristType[] {GristType.Chalk, GristType.Iodine}, new int[] {4, 2}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.wool, 1, 13), true, new GristSet(new GristType[] {GristType.Chalk, GristType.Amber}, new int[] {4, 2}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.wool, 1, 14), true, new GristSet(new GristType[] {GristType.Chalk, GristType.Garnet}, new int[] {4, 2}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.wool, 1, 15), true, new GristSet(new GristType[] {GristType.Chalk, GristType.Tar}, new int[] {4, 2}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.wool, 1, 2), true, new GristSet(new GristType[] {GristType.Chalk, GristType.Amethyst, GristType.Garnet}, new int[] {4, 1, 2}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.wool, 1, 3), true, new GristSet(new GristType[] {GristType.Chalk, GristType.Amethyst}, new int[] {6, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.wool, 1, 4), true, new GristSet(new GristType[] {GristType.Chalk, GristType.Amber}, new int[] {4, 2}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.wool, 1, 5), true, new GristSet(new GristType[] {GristType.Chalk, GristType.Amber}, new int[] {4, 2}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.wool, 1, 6), true, new GristSet(new GristType[] {GristType.Chalk, GristType.Garnet}, new int[] {6, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.wool, 1, 7), true, new GristSet(new GristType[] {GristType.Chalk, GristType.Tar}, new int[] {6, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.wool, 1, 8), true, new GristSet(new GristType[] {GristType.Chalk, GristType.Tar}, new int[] {8, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.wool, 1, 9), true, new GristSet(new GristType[] {GristType.Chalk, GristType.Amethyst, GristType.Amber}, new int[] {4, 1, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.packed_ice), false, new GristSet(new GristType[] {GristType.Build, GristType.Cobalt}, new int[] {2, 8}));
		
		//Items
		GristRegistry.addGristConversion(new ItemStack(Items.blaze_rod), false, new GristSet(new GristType[] {GristType.Tar, GristType.Uranium}, new int[] {16, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.bone), false, new GristSet(new GristType[] {GristType.Chalk}, new int[] {12}));
		GristRegistry.addGristConversion(new ItemStack(Items.bow), false, new GristSet(new GristType[] {GristType.Chalk, GristType.Build}, new int[] {3, 3}));
		GristRegistry.addGristConversion(new ItemStack(Items.brick), false, new GristSet(new GristType[] {GristType.Shale, GristType.Tar}, new int[] {4, 1}));
		GristRegistry.addGristConversion(new ItemStack(Items.chainmail_boots), false, new GristSet(new GristType[] {GristType.Sulfur, GristType.Tar}, new int[] {8, 8}));
		GristRegistry.addGristConversion(new ItemStack(Items.chainmail_chestplate), false, new GristSet(new GristType[] {GristType.Sulfur, GristType.Tar}, new int[] {16, 16}));
		GristRegistry.addGristConversion(new ItemStack(Items.chainmail_helmet), false, new GristSet(new GristType[] {GristType.Sulfur, GristType.Tar}, new int[] {10, 10}));
		GristRegistry.addGristConversion(new ItemStack(Items.chainmail_leggings), false, new GristSet(new GristType[] {GristType.Sulfur, GristType.Tar}, new int[] {14, 14}));
		GristRegistry.addGristConversion(new ItemStack(Items.clay_ball), false, new GristSet(new GristType[] {GristType.Shale}, new int[] {4}));
		GristRegistry.addGristConversion(new ItemStack(Items.diamond_horse_armor), false, new GristSet(new GristType[] {GristType.Diamond}, new int[] {80}));
		GristRegistry.addGristConversion(new ItemStack(Items.dye, 1, 0), true, new GristSet(new GristType[] {GristType.Tar}, new int[] {4}));
		GristRegistry.addGristConversion(new ItemStack(Items.dye, 1, 1), true, new GristSet(new GristType[] {GristType.Garnet}, new int[] {4}));
		GristRegistry.addGristConversion(new ItemStack(Items.dye, 1, 10), true, new GristSet(new GristType[] {GristType.Amber}, new int[] {3}));
		GristRegistry.addGristConversion(new ItemStack(Items.dye, 1, 11), true, new GristSet(new GristType[] {GristType.Amber}, new int[] {4}));
		GristRegistry.addGristConversion(new ItemStack(Items.dye, 1, 12), true, new GristSet(new GristType[] {GristType.Amethyst, GristType.Chalk}, new int[] {2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.dye, 1, 13), true, new GristSet(new GristType[] {GristType.Amethyst, GristType.Garnet}, new int[] {1, 3}));
		GristRegistry.addGristConversion(new ItemStack(Items.dye, 1, 14), true, new GristSet(new GristType[] {GristType.Garnet, GristType.Amber}, new int[] {2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.dye, 1, 15), true, new GristSet(new GristType[] {GristType.Chalk}, new int[] {4}));
		GristRegistry.addGristConversion(new ItemStack(Items.dye, 1, 2), true, new GristSet(new GristType[] {GristType.Amber, GristType.Iodine}, new int[] {3, 1}));
		GristRegistry.addGristConversion(new ItemStack(Items.dye, 1, 3), true, new GristSet(new GristType[] {GristType.Iodine, GristType.Amber}, new int[] {3, 1}));
		GristRegistry.addGristConversion(new ItemStack(Items.dye, 1, 4), true, new GristSet(new GristType[] {GristType.Amethyst}, new int[] {4}));
		GristRegistry.addGristConversion(new ItemStack(Items.dye, 1, 5), true, new GristSet(new GristType[] {GristType.Amethyst, GristType.Garnet}, new int[] {2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.dye, 1, 6), true, new GristSet(new GristType[] {GristType.Amethyst, GristType.Amber, GristType.Iodine}, new int[] {2, 2, 1}));
		GristRegistry.addGristConversion(new ItemStack(Items.dye, 1, 7), true, new GristSet(new GristType[] {GristType.Tar, GristType.Chalk}, new int[] {1, 3}));
		GristRegistry.addGristConversion(new ItemStack(Items.dye, 1, 8), true, new GristSet(new GristType[] {GristType.Tar, GristType.Chalk}, new int[] {3, 1}));
		GristRegistry.addGristConversion(new ItemStack(Items.dye, 1, 9), true, new GristSet(new GristType[] {GristType.Garnet, GristType.Chalk}, new int[] {2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.enchanted_book), false, new GristSet(new GristType[] {GristType.Uranium, GristType.Quartz, GristType.Diamond, GristType.Ruby, GristType.Chalk, GristType.Iodine}, new int[] {8, 1, 4, 4, 16, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.ender_pearl), false, new GristSet(new GristType[] {GristType.Uranium, GristType.Diamond}, new int[] {8, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.experience_bottle), false, new GristSet(new GristType[] {GristType.Uranium, GristType.Quartz, GristType.Diamond, GristType.Ruby}, new int[] {8, 1, 4, 4}));
		GristRegistry.addGristConversion(new ItemStack(Items.feather), false, new GristSet(new GristType[] {GristType.Chalk}, new int[] {1}));
		GristRegistry.addGristConversion(new ItemStack(Items.firework_charge), false, new GristSet(new GristType[] {GristType.Sulfur, GristType.Chalk}, new int[] {2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.fireworks), false, new GristSet(new GristType[] {GristType.Sulfur, GristType.Chalk}, new int[] {2, 4}));
		GristRegistry.addGristConversion(new ItemStack(Items.flint), false, new GristSet(new GristType[] {GristType.Shale}, new int[] {1}));
		GristRegistry.addGristConversion(new ItemStack(Items.ghast_tear), false, new GristSet(new GristType[] {GristType.Cobalt, GristType.Chalk}, new int[] {10, 15}));
		GristRegistry.addGristConversion(new ItemStack(Items.glowstone_dust), false, new GristSet(new GristType[] {GristType.Tar, GristType.Chalk}, new int[] {4, 4}));
		GristRegistry.addGristConversion(new ItemStack(Items.golden_horse_armor), false, new GristSet(new GristType[] {GristType.Gold}, new int[] {80}));
		GristRegistry.addGristConversion(new ItemStack(Items.gunpowder), false, new GristSet(new GristType[] {GristType.Sulfur, GristType.Chalk}, new int[] {2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.iron_horse_armor), false, new GristSet(new GristType[] {GristType.Rust}, new int[] {80}));
		GristRegistry.addGristConversion(new ItemStack(Items.lava_bucket), false, new GristSet(new GristType[] {GristType.Rust, GristType.Tar}, new int[] {48, 16}));
		GristRegistry.addGristConversion(new ItemStack(Items.lead), false, new GristSet(new GristType[] {GristType.Chalk, GristType.Caulk}, new int[] {8, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.map), false, new GristSet(new GristType[] {GristType.Rust, GristType.Chalk, GristType.Garnet}, new int[] {32, 10, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.milk_bucket), false, new GristSet(new GristType[] {GristType.Rust, GristType.Chalk}, new int[] {48, 16}));
		GristRegistry.addGristConversion(new ItemStack(Items.name_tag), false, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Chalk, GristType.Caulk}, new int[] {3, 2, 2, 2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.nether_star), false, new GristSet(new GristType[] {GristType.Uranium, GristType.Tar, GristType.Diamond}, new int[] {256, 64, 64}));
		GristRegistry.addGristConversion(new ItemStack(Items.nether_wart), false, new GristSet(new GristType[] {GristType.Iodine, GristType.Tar}, new int[] {4, 4}));
		GristRegistry.addGristConversion(new ItemStack(Items.netherbrick), false, new GristSet(new GristType[] {GristType.Build, GristType.Tar}, new int[] {1, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.record_11), false, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Chalk, GristType.Caulk}, new int[] {3, 2, 2, 2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.record_13), false, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Chalk, GristType.Caulk}, new int[] {3, 2, 2, 2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.record_blocks), false, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Chalk, GristType.Caulk}, new int[] {3, 2, 2, 2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.record_cat), false, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Chalk, GristType.Caulk}, new int[] {3, 2, 2, 2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.record_chirp), false, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Chalk, GristType.Caulk}, new int[] {3, 2, 2, 2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.record_far), false, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Chalk, GristType.Caulk}, new int[] {3, 2, 2, 2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.record_mall), false, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Chalk, GristType.Caulk}, new int[] {3, 2, 2, 2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.record_mellohi), false, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Chalk, GristType.Caulk}, new int[] {3, 2, 2, 2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.record_stal), false, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Chalk, GristType.Caulk}, new int[] {3, 2, 2, 2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.record_strad), false, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Chalk, GristType.Caulk}, new int[] {3, 2, 2, 2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.record_wait), false, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Chalk, GristType.Caulk}, new int[] {3, 2, 2, 2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.record_ward), false, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Chalk, GristType.Caulk}, new int[] {3, 2, 2, 2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.redstone), false, new GristSet(new GristType[] {GristType.Garnet}, new int[] {4}));
		GristRegistry.addGristConversion(new ItemStack(Items.reeds), false, new GristSet(new GristType[] {GristType.Amber, GristType.Iodine}, new int[] {4, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.rotten_flesh), false, new GristSet(new GristType[] {GristType.Cobalt, GristType.Iodine}, new int[] {4, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.saddle), false, new GristSet(new GristType[] {GristType.Rust, GristType.Iodine, GristType.Chalk}, new int[] {16, 4, 4}));
		GristRegistry.addGristConversion(new ItemStack(Items.skull, 1, 0), true, new GristSet(new GristType[] {GristType.Chalk}, new int[] {12}));
		GristRegistry.addGristConversion(new ItemStack(Items.skull, 1, 1), true, new GristSet(new GristType[] {GristType.Uranium, GristType.Tar, GristType.Diamond}, new int[] {62, 48, 16}));
		GristRegistry.addGristConversion(new ItemStack(Items.skull, 1, 2), true, new GristSet(new GristType[] {GristType.Cobalt, GristType.Iodine}, new int[] {4, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.skull, 1, 3), true, new GristSet(new GristType[] {GristType.Artifact}, new int[] {4}));
		GristRegistry.addGristConversion(new ItemStack(Items.skull, 1, 4), true, new GristSet(new GristType[] {GristType.Sulfur, GristType.Chalk}, new int[] {2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.slime_ball), false, new GristSet(new GristType[] {GristType.Caulk}, new int[] {8}));
		GristRegistry.addGristConversion(new ItemStack(Items.snowball), false, new GristSet(new GristType[] {GristType.Cobalt}, new int[] {1}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 100), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Iodine}, new int[] {2, 2, 8, 6}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 120), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Ruby, GristType.Diamond}, new int[] {2, 2, 2, 8, 8}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 50), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Sulfur, GristType.Chalk}, new int[] {2, 2, 2, 2, 2}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 51), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Chalk, GristType.Uranium, GristType.Tar, GristType.Diamond}, new int[] {2, 2, 2, 12, 62, 48, 16}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 54), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Cobalt, GristType.Iodine}, new int[] {2, 2, 2, 4, 2}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 55), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber}, new int[] {2, 10, 2}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 56), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Cobalt, GristType.Chalk}, new int[] {2, 2, 2, 2, 2}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 57), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Gold}, new int[] {2, 2, 2, 2}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 58), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Uranium, GristType.Diamond}, new int[] {2, 2, 2, 8, 2}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 59), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Amber, GristType.Iodine}, new int[] {2, 2, 2, 4, 2}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 59), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Chalk}, new int[] {2, 2, 2, 1}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 60), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Chalk, GristType.Caulk}, new int[] {2, 2, 2, 2, 2}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 61), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Tar, GristType.Uranium}, new int[] {2, 2, 2, 16, 2}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 62), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Tar, GristType.Uranium, GristType.Caulk}, new int[] {2, 2, 2, 8, 1, 8}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 65), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber}, new int[] {3, 3, 3}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 66), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Quartz, GristType.Cobalt}, new int[] {2, 2, 2, 1, 16}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 90), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Iodine}, new int[] {2, 2, 2, 10}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 91), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Chalk}, new int[] {2, 2, 2, 4}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 92), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Iodine}, new int[] {2, 2, 2, 12}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 93), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Chalk}, new int[] {2, 2, 2, 1}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 94), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Tar}, new int[] {2, 2, 2, 4}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 95), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Chalk}, new int[] {2, 2, 2, 12}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 96), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Iodine, GristType.Ruby}, new int[] {2, 2, 2, 2, 2}));
//		GristRegistry.addGristConversion(new ItemStack(Items.spawn_egg, 1, 98), true, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber, GristType.Caulk, GristType.Amber, GristType.Cobalt}, new int[] {2, 2, 2, 4, 4, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.spider_eye), false, new GristSet(new GristType[] {GristType.Amber, GristType.Iodine}, new int[] {4, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.string), false, new GristSet(new GristType[] {GristType.Chalk}, new int[] {1}));
		GristRegistry.addGristConversion(new ItemStack(Items.water_bucket), false, new GristSet(new GristType[] {GristType.Rust, GristType.Cobalt}, new int[] {48, 16}));
		GristRegistry.addGristConversion(new ItemStack(Items.wheat), false, new GristSet(new GristType[] {GristType.Iodine}, new int[] {8}));
		GristRegistry.addGristConversion(new ItemStack(Items.wheat_seeds), false, new GristSet(new GristType[] {GristType.Amber, GristType.Iodine}, new int[] {1, 1}));
		GristRegistry.addGristConversion(new ItemStack(Items.writable_book), false, new GristSet(new GristType[] {GristType.Chalk, GristType.Iodine}, new int[] {16, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.prismarine_crystals), new GristSet(new GristType[] {GristType.Cobalt, GristType.Diamond}, new int[] {10, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.prismarine_shard), new GristSet(new GristType[] {GristType.Build, GristType.Cobalt}, new int[] {3, 5}));
		GristRegistry.addGristConversion(new ItemStack(Items.rabbit_hide), new GristSet(new GristType[] {GristType.Iodine, GristType.Chalk}, new int[] {1, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.sponge, 1, 1), new GristSet(new GristType[] {GristType.Amber, GristType.Sulfur, GristType.Cobalt}, new int[] {20, 30, 10}));
		
		//Ores
		GristRegistry.addGristConversion(new ItemStack(Blocks.coal_ore), false, new GristSet(new GristType[] {GristType.Build, GristType.Tar}, new int[] {4, 16}));
		GristRegistry.addGristConversion(new ItemStack(Items.coal, 1, 0), true, new GristSet(new GristType[] {GristType.Tar}, new int[] {16}));
		GristRegistry.addGristConversion(new ItemStack(Items.coal, 1, 1), true, new GristSet(new GristType[] {GristType.Tar, GristType.Amber}, new int[] {12, 4}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.coal_block), false, new GristSet(new GristType[] {GristType.Tar}, new int[] {144}));
		GristRegistry.addGristConversion("oreIron", new GristSet(new GristType[] {GristType.Build, GristType.Rust}, new int[] {4, 18}));
		GristRegistry.addGristConversion("ingotIron", new GristSet(new GristType[] {GristType.Rust}, new int[] {18}));
		GristRegistry.addGristConversion("oreGold", new GristSet(new GristType[] {GristType.Build, GristType.Gold}, new int[] {4, 18}));
		GristRegistry.addGristConversion("ingotGold", new GristSet(new GristType[] {GristType.Gold}, new int[] {18}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.redstone_ore), false, new GristSet(new GristType[] {GristType.Garnet, GristType.Build}, new int[] {16, 4}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.lapis_ore), false, new GristSet(new GristType[] {GristType.Amethyst, GristType.Build}, new int[] {16, 4}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.diamond_ore), false, new GristSet(new GristType[] {GristType.Diamond, GristType.Build}, new int[] {16, 4}));
		GristRegistry.addGristConversion(new ItemStack(Items.diamond), false, new GristSet(new GristType[] {GristType.Diamond}, new int[] {16}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.emerald_ore), false, new GristSet(new GristType[] {GristType.Ruby, GristType.Diamond, GristType.Build}, new int[] {8, 8, 4}));
		GristRegistry.addGristConversion(new ItemStack(Items.emerald), false, new GristSet(new GristType[] {GristType.Ruby, GristType.Diamond}, new int[] {8, 8}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.quartz_ore), false, new GristSet(new GristType[] {GristType.Quartz, GristType.Marble, GristType.Build}, new int[] {8, 2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.quartz), false, new GristSet(new GristType[] {GristType.Quartz, GristType.Marble}, new int[] {4, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.quartz_block), false, new GristSet(new GristType[] {GristType.Quartz, GristType.Marble}, new int[] {16, 4}));
		
		//Plants
		GristRegistry.addGristConversion(new ItemStack(Blocks.leaves2), false, new GristSet(new GristType[] {GristType.Build, GristType.Amber}, new int[] {1, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.yellow_flower), false, new GristSet(new GristType[] {GristType.Amber, GristType.Iodine}, new int[] {4, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.red_flower, 1, 0), true, new GristSet(new GristType[] {GristType.Garnet, GristType.Iodine}, new int[] {4, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.red_flower, 1, 1), true, new GristSet(new GristType[] {GristType.Amethyst, GristType.Chalk, GristType.Iodine}, new int[] {2, 2, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.red_flower, 1, 2), true, new GristSet(new GristType[] {GristType.Amethyst, GristType.Garnet, GristType.Iodine}, new int[] {1, 3, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.red_flower, 1, 3), true, new GristSet(new GristType[] {GristType.Tar, GristType.Chalk, GristType.Iodine}, new int[] {1, 3, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.red_flower, 1, 4), true, new GristSet(new GristType[] {GristType.Tar, GristType.Chalk, GristType.Iodine}, new int[] {1, 3, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.red_flower, 1, 5), true, new GristSet(new GristType[] {GristType.Garnet, GristType.Amber, GristType.Iodine}, new int[] {2, 2, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.red_flower, 1, 6), true, new GristSet(new GristType[] {GristType.Amethyst, GristType.Chalk, GristType.Iodine}, new int[] {2, 2, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.red_flower, 1, 7), true, new GristSet(new GristType[] {GristType.Garnet, GristType.Iodine}, new int[] {4, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.red_flower, 1, 8), true, new GristSet(new GristType[] {GristType.Tar, GristType.Chalk, GristType.Iodine}, new int[] {1, 3, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.brown_mushroom), false, new GristSet(new GristType[] {GristType.Iodine}, new int[] {2}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.red_mushroom), false, new GristSet(new GristType[] {GristType.Ruby}, new int[] {2}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.cactus), false, new GristSet(new GristType[] {GristType.Amber, GristType.Iodine}, new int[] {4, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.tallgrass), false, new GristSet(new GristType[] {GristType.Amber}, new int[] {1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.vine), false, new GristSet(new GristType[] {GristType.Build, GristType.Amber}, new int[] {8, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.waterlily), false, new GristSet(new GristType[] {GristType.Amber, GristType.Iodine}, new int[] {4, 1}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.double_plant, 1, 0), true, new GristSet(new GristType[] {GristType.Amber, GristType.Iodine}, new int[] {7, 2}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.double_plant, 1, 1), true, new GristSet(new GristType[] {GristType.Amethyst, GristType.Garnet, GristType.Iodine}, new int[] {2, 5, 2}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.double_plant, 1, 2), true, new GristSet(new GristType[] {GristType.Amber}, new int[] {2}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.double_plant, 1, 3), true, new GristSet(new GristType[] {GristType.Amber}, new int[] {2}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.double_plant, 1, 4), true, new GristSet(new GristType[] {GristType.Garnet, GristType.Iodine}, new int[] {7, 2}));
		GristRegistry.addGristConversion(new ItemStack(Blocks.double_plant, 1, 5), true, new GristSet(new GristType[] {GristType.Garnet, GristType.Chalk, GristType.Iodine}, new int[] {4, 4, 2}));
		
		//Food
		GristRegistry.addGristConversion(new ItemStack(Items.apple), false, new GristSet(new GristType[] {GristType.Amber, GristType.Shale}, new int[] {2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.carrot), false, new GristSet(new GristType[] {GristType.Amber, GristType.Chalk}, new int[] {3, 1}));
		GristRegistry.addGristConversion(new ItemStack(Items.potato), false, new GristSet(new GristType[] {GristType.Amber}, new int[] {4}));
		GristRegistry.addGristConversion(new ItemStack(Items.poisonous_potato), false, new GristSet(new GristType[] {GristType.Amber, GristType.Iodine}, new int[] {4, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.baked_potato), false, new GristSet(new GristType[] {GristType.Amber, GristType.Tar}, new int[] {4, 1}));
		GristRegistry.addGristConversion(new ItemStack(Items.melon), false, new GristSet(new GristType[] {GristType.Amber, GristType.Caulk}, new int[] {1, 1}));
		GristRegistry.addGristConversion(new ItemStack(Items.egg), false, new GristSet(new GristType[] {GristType.Build, GristType.Caulk, GristType.Amber}, new int[] {2, 2, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.cake), false, new GristSet(new GristType[] {GristType.Build, GristType.Amber, GristType.Chalk, GristType.Iodine}, new int[] {4, 6, 52, 26}));
		GristRegistry.addGristConversion(new ItemStack(Items.beef), false, new GristSet(new GristType[] {GristType.Iodine}, new int[] {12}));
		GristRegistry.addGristConversion(new ItemStack(Items.porkchop), false, new GristSet(new GristType[] {GristType.Iodine}, new int[] {10}));
		GristRegistry.addGristConversion(new ItemStack(Items.chicken), false, new GristSet(new GristType[] {GristType.Iodine}, new int[] {10}));
		GristRegistry.addGristConversion(new ItemStack(Items.fish, 1, 0), true, new GristSet(new GristType[] {GristType.Caulk, GristType.Amber, GristType.Cobalt}, new int[] {4, 4, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.mutton), new GristSet(GristType.Iodine, 10));
		GristRegistry.addGristConversion(new ItemStack(Items.rabbit), new GristSet(GristType.Iodine, 8));
		GristRegistry.addGristConversion(new ItemStack(Items.cooked_beef), false, new GristSet(new GristType[] {GristType.Iodine, GristType.Tar}, new int[] {12, 1}));
		GristRegistry.addGristConversion(new ItemStack(Items.cooked_porkchop), false, new GristSet(new GristType[] {GristType.Iodine, GristType.Tar}, new int[] {10, 4}));
		GristRegistry.addGristConversion(new ItemStack(Items.cooked_chicken), false, new GristSet(new GristType[] {GristType.Iodine, GristType.Tar}, new int[] {10, 1}));
		GristRegistry.addGristConversion(new ItemStack(Items.cooked_fish, 1, 0), true, new GristSet(new GristType[] {GristType.Caulk, GristType.Amber, GristType.Cobalt, GristType.Tar}, new int[] {4, 4, 2, 1}));
		GristRegistry.addGristConversion(new ItemStack(Items.cooked_mutton), new GristSet(new GristType[] {GristType.Iodine, GristType.Tar}, new int[] {10, 1}));
		GristRegistry.addGristConversion(new ItemStack(Items.cooked_rabbit), new GristSet(new GristType[] {GristType.Iodine, GristType.Tar}, new int[] {8, 1}));
		
		//Potions
		GristRegistry.addGristConversion(new ItemStack(Items.potionitem, 1, 0), true, new GristSet(new GristType[] {GristType.Quartz, GristType.Cobalt}, new int[] {1, 16}));
		GristRegistry.addGristConversion(new ItemStack(Items.potionitem, 1, 1), true, new GristSet(new GristType[] {GristType.Quartz, GristType.Cobalt, GristType.Chalk}, new int[] {1, 18, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.potionitem, 1, 10), true, new GristSet(new GristType[] {GristType.Quartz, GristType.Cobalt, GristType.Iodine, GristType.Amber, GristType.Tar}, new int[] {1, 16, 8, 6, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.potionitem, 1, 12), true, new GristSet(new GristType[] {GristType.Quartz, GristType.Cobalt, GristType.Gold, GristType.Amber, GristType.Iodine, GristType.Tar, GristType.Chalk}, new int[] {1, 16, 16, 7, 4, 2, 1}));
		GristRegistry.addGristConversion(new ItemStack(Items.potionitem, 1, 14), true, new GristSet(new GristType[] {GristType.Quartz, GristType.Cobalt, GristType.Amber, GristType.Chalk, GristType.Gold, GristType.Iodine, GristType.Tar}, new int[] {1, 16, 9, 1, 16, 4, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.potionitem, 1, 16), true, new GristSet(new GristType[] {GristType.Quartz, GristType.Cobalt, GristType.Iodine, GristType.Tar}, new int[] {1, 16, 4, 4}));
		GristRegistry.addGristConversion(new ItemStack(Items.potionitem, 1, 2), true, new GristSet(new GristType[] {GristType.Quartz, GristType.Cobalt, GristType.Iodine}, new int[] {1, 16, 4}));
		GristRegistry.addGristConversion(new ItemStack(Items.potionitem, 1, 3), true, new GristSet(new GristType[] {GristType.Quartz, GristType.Cobalt, GristType.Tar, GristType.Uranium, GristType.Caulk}, new int[] {1, 16, 8, 1, 8}));
		GristRegistry.addGristConversion(new ItemStack(Items.potionitem, 1, 32), true, new GristSet(new GristType[] {GristType.Quartz, GristType.Cobalt}, new int[] {1, 16}));
		GristRegistry.addGristConversion(new ItemStack(Items.potionitem, 1, 4), true, new GristSet(new GristType[] {GristType.Quartz, GristType.Cobalt, GristType.Amber, GristType.Iodine}, new int[] {1, 16, 4, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.potionitem, 1, 5), true, new GristSet(new GristType[] {GristType.Quartz, GristType.Cobalt, GristType.Gold, GristType.Amber, GristType.Chalk}, new int[] {1, 16, 16, 1, 1}));
		GristRegistry.addGristConversion(new ItemStack(Items.potionitem, 1, 6), true, new GristSet(new GristType[] {GristType.Quartz, GristType.Cobalt, GristType.Amber, GristType.Chalk, GristType.Gold}, new int[] {1, 16, 3, 1, 16}));
		GristRegistry.addGristConversion(new ItemStack(Items.potionitem, 1, 8), true, new GristSet(new GristType[] {GristType.Quartz, GristType.Cobalt, GristType.Amber, GristType.Iodine, GristType.Tar}, new int[] {1, 16, 6, 4, 2}));
		GristRegistry.addGristConversion(new ItemStack(Items.potionitem, 1, 9), true, new GristSet(new GristType[] {GristType.Quartz, GristType.Tar, GristType.Uranium}, new int[] {1, 8, 1}));
		
		//Set up Punch Designix recipes
		
		//Wood
		final String[] woodDict = {"logWood","plankWood","slabWood","stairWood","treeSapling","treeLeaves","doorWood","fenceWood","fencegateWood"};
		final ItemStack[][] woodItems = {
				{new ItemStack(Blocks.log,1,0),new ItemStack(Blocks.log,1,1),new ItemStack(Blocks.log,1,2),new ItemStack(Blocks.log,1,3),new ItemStack(Blocks.log2,1,0),new ItemStack(Blocks.log2,1,1)},
				{new ItemStack(Blocks.planks,1,0),new ItemStack(Blocks.planks,1,1),new ItemStack(Blocks.planks,1,2),new ItemStack(Blocks.planks,1,3),new ItemStack(Blocks.planks,1,4),new ItemStack(Blocks.planks,1,5)},
				{new ItemStack(Blocks.wooden_slab,1,0),new ItemStack(Blocks.wooden_slab,1,1),new ItemStack(Blocks.wooden_slab,1,2),new ItemStack(Blocks.wooden_slab,1,3),new ItemStack(Blocks.wooden_slab,1,4),new ItemStack(Blocks.wooden_slab,1,5)},
				{new ItemStack(Blocks.oak_stairs),new ItemStack(Blocks.spruce_stairs),new ItemStack(Blocks.birch_stairs),new ItemStack(Blocks.jungle_stairs),new ItemStack(Blocks.acacia_stairs),new ItemStack(Blocks.dark_oak_stairs)},
				{new ItemStack(Blocks.sapling,1,0),new ItemStack(Blocks.sapling,1,1),new ItemStack(Blocks.sapling,1,2),new ItemStack(Blocks.sapling,1,3),new ItemStack(Blocks.sapling,1,4),new ItemStack(Blocks.sapling,1,5)},
				{new ItemStack(Blocks.leaves,1,0),new ItemStack(Blocks.leaves,1,1),new ItemStack(Blocks.leaves,1,2),new ItemStack(Blocks.leaves,1,3),new ItemStack(Blocks.leaves2,1,0),new ItemStack(Blocks.leaves2,1,1)},
				{new ItemStack(Items.oak_door),new ItemStack(Items.spruce_door),new ItemStack(Items.birch_door),new ItemStack(Items.jungle_door),new ItemStack(Items.acacia_door),new ItemStack(Items.dark_oak_door)},
				{new ItemStack(Blocks.oak_fence),new ItemStack(Blocks.spruce_fence),new ItemStack(Blocks.birch_fence),new ItemStack(Blocks.jungle_fence),new ItemStack(Blocks.acacia_fence),new ItemStack(Blocks.dark_oak_fence)},
				{new ItemStack(Blocks.oak_fence_gate),new ItemStack(Blocks.spruce_fence_gate),new ItemStack(Blocks.birch_fence_gate),new ItemStack(Blocks.jungle_fence_gate),new ItemStack(Blocks.acacia_fence_gate),new ItemStack(Blocks.dark_oak_fence_gate)}};
		for(int i = 6; i < woodItems.length; i++)
			for(ItemStack stack : woodItems[i])
				checkRegistered(stack, woodDict[i]);
		
		for(int i1 = 0; i1 < woodItems.length; i1++)
		{
			CombinationRegistry.addCombination(woodItems[i1][0], woodItems[i1][1], MODE_OR, woodItems[i1][5]);	//Oak | spruce -> dark oak
			CombinationRegistry.addCombination(woodItems[i1][2], woodItems[i1][3], MODE_OR, woodItems[i1][4]);	//Birch | jungle -> acacia
		}
		for(int i2 = 0; i2 < woodItems[0].length; i2++)
		{
			CombinationRegistry.addCombination(woodItems[1][i2], woodItems[2][i2], MODE_OR, woodItems[3][i2]);	//plank | slab -> stair
			CombinationRegistry.addCombination(woodItems[0][i2], woodItems[5][i2], MODE_OR, woodItems[4][i2]);	//leaf | log -> sapling
			CombinationRegistry.addCombination(woodItems[6][i2], woodItems[7][i2], MODE_OR, woodItems[8][i2]);	//door | fence -> fence gate
			CombinationRegistry.addCombination(new ItemStack(Items.wheat_seeds), woodItems[5][i2], MODE_AND, new ItemStack(Blocks.sapling, 1, i2));
			CombinationRegistry.addCombination(new ItemStack(Items.stick), woodItems[5][i2], MODE_AND, new ItemStack(Blocks.sapling, 1, i2));
		}
		CombinationRegistry.addCombination(woodItems[1][0], woodItems[2][1], MODE_OR, woodItems[3][5]);
		CombinationRegistry.addCombination(woodItems[2][0], woodItems[1][1], MODE_OR, woodItems[3][5]);
		CombinationRegistry.addCombination(woodItems[0][0], woodItems[5][1], MODE_OR, woodItems[4][5]);
		CombinationRegistry.addCombination(woodItems[5][0], woodItems[0][1], MODE_OR, woodItems[4][5]);
		CombinationRegistry.addCombination(woodItems[6][0], woodItems[7][1], MODE_OR, woodItems[8][5]);
		CombinationRegistry.addCombination(woodItems[7][0], woodItems[6][1], MODE_OR, woodItems[8][5]);
		
		CombinationRegistry.addCombination(woodItems[1][2], woodItems[2][3], MODE_OR, woodItems[3][4]);
		CombinationRegistry.addCombination(woodItems[2][2], woodItems[1][3], MODE_OR, woodItems[3][4]);
		CombinationRegistry.addCombination(woodItems[0][2], woodItems[5][3], MODE_OR, woodItems[4][4]);
		CombinationRegistry.addCombination(woodItems[5][2], woodItems[0][3], MODE_OR, woodItems[4][4]);
		CombinationRegistry.addCombination(woodItems[6][2], woodItems[7][3], MODE_OR, woodItems[8][4]);
		CombinationRegistry.addCombination(woodItems[7][2], woodItems[6][3], MODE_OR, woodItems[8][4]);
		
		CombinationRegistry.addCombination("doorWood", Items.iron_ingot, WILDCARD_VALUE, MODE_AND, new ItemStack(Items.iron_door));
		CombinationRegistry.addCombination("fenceWood", Blocks.nether_brick, WILDCARD_VALUE, MODE_AND, new ItemStack(Blocks.nether_brick_fence));
		CombinationRegistry.addCombination("stairWood", Blocks.nether_brick, WILDCARD_VALUE, MODE_AND, new ItemStack(Blocks.nether_brick_stairs));
		CombinationRegistry.addCombination("fenceWood", Items.netherbrick, WILDCARD_VALUE, MODE_AND, new ItemStack(Blocks.nether_brick_fence));
		CombinationRegistry.addCombination("stairWood", Items.netherbrick, WILDCARD_VALUE, MODE_AND, new ItemStack(Blocks.nether_brick_stairs));
		CombinationRegistry.addCombination("doorWood", "slabWood", MODE_AND, new ItemStack(Blocks.trapdoor));
		CombinationRegistry.addCombination("logWood", Items.coal, 0, MODE_AND, new ItemStack(Items.coal, 1, 1));
		
		//Dye
		Block[] coloredBlocks = {Blocks.wool, Blocks.stained_hardened_clay, Blocks.stained_glass, Blocks.stained_glass_pane};
		for(int i1 = 0; i1 < coloredBlocks.length; i1++)
		{
			for (EnumDyeColor color : EnumDyeColor.values())
			{
				if(color != EnumDyeColor.WHITE)
					CombinationRegistry.addCombination(new ItemStack(Items.dye, 1, color.getDyeDamage()), new ItemStack(coloredBlocks[i1], 1, EnumDyeColor.WHITE.getMetadata()), MODE_OR, new ItemStack(coloredBlocks[i1], 1, color.getMetadata()));
			}
		}
		for (EnumDyeColor color : EnumDyeColor.values())
		{
			CombinationRegistry.addCombination(new ItemStack(Blocks.glass), new ItemStack(Items.dye, 1, color.getDyeDamage()), MODE_AND, new ItemStack(Blocks.stained_glass, 1, color.getMetadata()));
			CombinationRegistry.addCombination(new ItemStack(Blocks.glass_pane), new ItemStack(Items.dye, 1, color.getDyeDamage()), MODE_AND, new ItemStack(Blocks.stained_glass_pane, 1, color.getMetadata()));
			CombinationRegistry.addCombination(new ItemStack(Blocks.hardened_clay), new ItemStack(Items.dye, 1, color.getDyeDamage()), MODE_AND, new ItemStack(Blocks.stained_hardened_clay, 1, color.getMetadata()));
		}
		
		//ore related
		CombinationRegistry.addCombination(new ItemStack(Items.coal),new ItemStack(Blocks.stone),MODE_AND, new ItemStack(Blocks.coal_ore));
		CombinationRegistry.addCombination(new ItemStack(Items.coal),new ItemStack(Blocks.stone),MODE_OR, new ItemStack(Blocks.coal_block));
		CombinationRegistry.addCombination(new ItemStack(Items.diamond),new ItemStack(Blocks.stone),MODE_AND, new ItemStack(Blocks.diamond_ore));
		CombinationRegistry.addCombination(new ItemStack(Items.diamond),new ItemStack(Blocks.stone),MODE_OR, new ItemStack(Blocks.diamond_block));
		CombinationRegistry.addCombination(new ItemStack(Items.dye,1,4),new ItemStack(Blocks.stone),MODE_AND, new ItemStack(Blocks.lapis_ore));
		CombinationRegistry.addCombination(new ItemStack(Items.dye,1,4),new ItemStack(Blocks.stone),MODE_OR, new ItemStack(Blocks.lapis_block));
		CombinationRegistry.addCombination(new ItemStack(Items.emerald),new ItemStack(Blocks.stone),MODE_AND, new ItemStack(Blocks.emerald_ore));
		CombinationRegistry.addCombination(new ItemStack(Items.emerald),new ItemStack(Blocks.stone),MODE_OR, new ItemStack(Blocks.emerald_block));
		CombinationRegistry.addCombination(new ItemStack(Items.gold_ingot),new ItemStack(Blocks.stone),MODE_AND, new ItemStack(Blocks.gold_ore));
		CombinationRegistry.addCombination(new ItemStack(Items.gold_ingot),new ItemStack(Blocks.stone),MODE_OR, new ItemStack(Blocks.gold_block));
		CombinationRegistry.addCombination(new ItemStack(Items.iron_ingot),new ItemStack(Blocks.stone),MODE_AND, new ItemStack(Blocks.iron_ore));
		CombinationRegistry.addCombination(new ItemStack(Items.iron_ingot),new ItemStack(Blocks.stone),MODE_OR, new ItemStack(Blocks.iron_block));
		CombinationRegistry.addCombination(new ItemStack(Items.quartz),new ItemStack(Blocks.netherrack),MODE_AND, new ItemStack(Blocks.quartz_ore));
		CombinationRegistry.addCombination(new ItemStack(Items.quartz),new ItemStack(Blocks.stone),MODE_OR, new ItemStack(Blocks.quartz_block));
		CombinationRegistry.addCombination(new ItemStack(Items.redstone),new ItemStack(Blocks.stone),MODE_AND, new ItemStack(Blocks.redstone_ore));
		CombinationRegistry.addCombination(new ItemStack(Items.redstone),new ItemStack(Blocks.stone),MODE_OR, new ItemStack(Blocks.redstone_block));
		
		CombinationRegistry.addCombination(new ItemStack(Blocks.stone), new ItemStack(Blocks.stonebrick, 1, 2), MODE_AND, new ItemStack(Blocks.stonebrick, 1, 0));
		CombinationRegistry.addCombination(new ItemStack(Blocks.stone), new ItemStack(Blocks.stonebrick, 1, 2), MODE_OR, new ItemStack(Blocks.cobblestone));
		CombinationRegistry.addCombination(new ItemStack(Blocks.stone), new ItemStack(Blocks.gravel), MODE_AND, new ItemStack(Items.flint));
		CombinationRegistry.addCombination(new ItemStack(Blocks.stone), new ItemStack(Blocks.gravel), MODE_OR, new ItemStack(Blocks.cobblestone));
		CombinationRegistry.addCombination(new ItemStack(Blocks.stone), new ItemStack(Blocks.sand, 1, 0), MODE_OR, new ItemStack(Blocks.sandstone, 1, 0));
		CombinationRegistry.addCombination(new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.sandstone, 1, 2), MODE_AND, new ItemStack(Blocks.stone));
		CombinationRegistry.addCombination(new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.sandstone, 1, 2), MODE_OR, new ItemStack(Blocks.sandstone, 1, 0));
		CombinationRegistry.addCombination(new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.sand), MODE_AND, true, false, new ItemStack(Blocks.gravel));
		CombinationRegistry.addCombination(new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.sand, 1, 0), MODE_OR, new ItemStack(Blocks.sandstone, 1, 0));
		CombinationRegistry.addCombination(new ItemStack(Blocks.gravel), new ItemStack(Blocks.sandstone, 1, 2), MODE_OR, new ItemStack(Blocks.sandstone, 1, 0));
		CombinationRegistry.addCombination(new ItemStack(Blocks.sandstone, 1, 2), new ItemStack(Blocks.sand, 1, 0), MODE_OR, new ItemStack(Blocks.sandstone, 1, 0));
		
		//misc
		CombinationRegistry.addCombination(new ItemStack(Blocks.cobblestone),new ItemStack(Items.coal),MODE_AND, new ItemStack(Blocks.furnace));
		CombinationRegistry.addCombination(new ItemStack(Blocks.cobblestone),new ItemStack(Items.wheat_seeds),MODE_OR, new ItemStack(Blocks.mossy_cobblestone));
		CombinationRegistry.addCombination(new ItemStack(Blocks.cobblestone_wall),new ItemStack(Items.wheat_seeds),MODE_OR, new ItemStack(Blocks.cobblestone_wall,1,1));
		CombinationRegistry.addCombination(new ItemStack(Blocks.dirt), new ItemStack(Blocks.tallgrass), MODE_OR, true, false, new ItemStack(Blocks.grass));
		CombinationRegistry.addCombination(new ItemStack(Blocks.dirt),new ItemStack(Items.wheat_seeds),MODE_AND, new ItemStack(Blocks.grass));
		CombinationRegistry.addCombination(new ItemStack(Blocks.grass),new ItemStack(Blocks.brown_mushroom),MODE_AND, new ItemStack(Blocks.mycelium));
		CombinationRegistry.addCombination(new ItemStack(Blocks.grass),new ItemStack(Blocks.red_mushroom),MODE_AND, new ItemStack(Blocks.mycelium));
		CombinationRegistry.addCombination(new ItemStack(Blocks.ladder),new ItemStack(Items.iron_ingot),MODE_AND, new ItemStack(Blocks.rail));
		CombinationRegistry.addCombination(new ItemStack(Blocks.netherrack), new ItemStack(Blocks.brick_block), MODE_AND, new ItemStack(Blocks.nether_brick));
		CombinationRegistry.addCombination(new ItemStack(Blocks.netherrack), new ItemStack(Items.brick), MODE_AND, new ItemStack(Blocks.nether_brick));
		CombinationRegistry.addCombination(new ItemStack(Blocks.netherrack), new ItemStack(Items.brick), MODE_OR, new ItemStack(Items.netherbrick));
		CombinationRegistry.addCombination(new ItemStack(Blocks.nether_brick), new ItemStack(Items.brick), MODE_OR, new ItemStack(Items.netherbrick));
		CombinationRegistry.addCombination(new ItemStack(Blocks.netherrack),new ItemStack(Items.glowstone_dust),MODE_AND, new ItemStack(Blocks.glowstone));
		CombinationRegistry.addCombination(new ItemStack(Blocks.noteblock),new ItemStack(Items.diamond),MODE_AND, new ItemStack(Blocks.jukebox));
		CombinationRegistry.addCombination(new ItemStack(Blocks.rail), new ItemStack(Blocks.planks), MODE_AND, true, false, new ItemStack(Blocks.ladder));
		CombinationRegistry.addCombination(new ItemStack(Blocks.sapling, 1, 0),new ItemStack(Items.wheat_seeds),MODE_AND,true,false, new ItemStack(Items.apple));
		CombinationRegistry.addCombination(new ItemStack(Blocks.leaves, 1, 0),new ItemStack(Items.wheat_seeds),MODE_OR, true, false, new ItemStack(Items.apple));
		CombinationRegistry.addCombination(new ItemStack(Blocks.stone),new ItemStack(Items.ender_pearl),MODE_AND, new ItemStack(Blocks.end_stone));
		CombinationRegistry.addCombination(new ItemStack(Blocks.stonebrick, 1, 0), new ItemStack(Items.wheat_seeds), MODE_OR, new ItemStack(Blocks.stonebrick, 1, 1));
		CombinationRegistry.addCombination(new ItemStack(Items.apple),new ItemStack(Items.gold_ingot),MODE_AND, new ItemStack(Items.golden_apple, 1, 0));
		CombinationRegistry.addCombination(new ItemStack(Items.apple),new ItemStack(Items.gold_nugget),MODE_AND, new ItemStack(Items.golden_apple, 1, 0));
		CombinationRegistry.addCombination(new ItemStack(Items.apple),new ItemStack(Blocks.gold_block),MODE_AND, new ItemStack(Items.golden_apple, 1, 1));
		CombinationRegistry.addCombination(new ItemStack(Items.carrot),new ItemStack(Items.wheat_seeds),MODE_AND, new ItemStack(Items.potato));
		CombinationRegistry.addCombination(new ItemStack(Items.clock),new ItemStack(Items.iron_ingot),MODE_AND, new ItemStack(Items.compass));
		CombinationRegistry.addCombination(new ItemStack(Items.compass),new ItemStack(Items.gold_ingot),MODE_AND, new ItemStack(Items.clock));
		CombinationRegistry.addCombination(new ItemStack(Items.diamond),new ItemStack(Items.saddle),MODE_AND, new ItemStack(Items.diamond_horse_armor));
		CombinationRegistry.addCombination(new ItemStack(Items.ender_eye),new ItemStack(Items.egg),MODE_AND, new ItemStack(Blocks.dragon_egg));
		CombinationRegistry.addCombination(new ItemStack(Items.ender_pearl),new ItemStack(Items.blaze_powder),MODE_AND, new ItemStack(Items.ender_eye));
		CombinationRegistry.addCombination(new ItemStack(Items.gold_ingot),new ItemStack(Items.saddle),MODE_AND, new ItemStack(Items.golden_horse_armor));
		CombinationRegistry.addCombination(new ItemStack(Items.gunpowder),new ItemStack(Blocks.sand), true, false, MODE_AND, new ItemStack(Blocks.tnt));
		CombinationRegistry.addCombination(new ItemStack(Items.iron_ingot), new ItemStack(Blocks.tallgrass), MODE_AND, true, false, new ItemStack(Items.shears));
		CombinationRegistry.addCombination(new ItemStack(Items.iron_ingot),new ItemStack(Items.saddle),MODE_AND, new ItemStack(Items.iron_horse_armor));
		CombinationRegistry.addCombination(new ItemStack(Items.potato),new ItemStack(Items.wheat_seeds),MODE_OR, new ItemStack(Items.carrot));
		CombinationRegistry.addCombination(new ItemStack(Items.redstone),new ItemStack(Items.gold_ingot),MODE_OR, new ItemStack(Items.clock));
		CombinationRegistry.addCombination(new ItemStack(Items.redstone),new ItemStack(Items.iron_ingot),MODE_OR, new ItemStack(Items.compass));
		CombinationRegistry.addCombination(new ItemStack(Items.rotten_flesh),new ItemStack(Items.carrot),MODE_OR, new ItemStack(Items.porkchop));
		CombinationRegistry.addCombination(new ItemStack(Items.rotten_flesh),new ItemStack(Items.water_bucket),MODE_OR, new ItemStack(Items.leather));
		CombinationRegistry.addCombination(new ItemStack(Items.rotten_flesh),new ItemStack(Items.wheat),MODE_OR, new ItemStack(Items.beef));
		CombinationRegistry.addCombination(new ItemStack(Items.rotten_flesh),new ItemStack(Items.wheat_seeds),MODE_OR, new ItemStack(Items.chicken));
		CombinationRegistry.addCombination(new ItemStack(Items.slime_ball),new ItemStack(Items.blaze_powder),MODE_AND, new ItemStack(Items.magma_cream));
		CombinationRegistry.addCombination("stickWood", Items.lava_bucket, WILDCARD_VALUE, MODE_AND, new ItemStack(Items.blaze_rod));
		CombinationRegistry.addCombination(new ItemStack(Items.string),new ItemStack(Items.leather),MODE_AND, new ItemStack(Items.saddle));
		CombinationRegistry.addCombination(new ItemStack(Items.water_bucket),new ItemStack(Items.lava_bucket),MODE_AND, new ItemStack(Blocks.obsidian));
		CombinationRegistry.addCombination(new ItemStack(Items.redstone), new ItemStack(Items.lava_bucket), MODE_OR, new ItemStack(Items.blaze_powder));
		CombinationRegistry.addCombination(new ItemStack(Items.redstone), new ItemStack(Blocks.netherrack), MODE_OR, new ItemStack(Items.blaze_powder));
		CombinationRegistry.addCombination("stickWood", Items.blaze_powder, WILDCARD_VALUE, MODE_AND, new ItemStack(Items.blaze_rod));
		CombinationRegistry.addCombination(new ItemStack(Items.boat), new ItemStack(Blocks.rail), MODE_OR, new ItemStack(Items.minecart));
		CombinationRegistry.addCombination(new ItemStack(Items.iron_ingot), new ItemStack(Blocks.trapdoor), MODE_OR, new ItemStack(Blocks.iron_trapdoor));
		CombinationRegistry.addCombination(new ItemStack(Blocks.iron_block), new ItemStack(Blocks.trapdoor), MODE_OR, new ItemStack(Blocks.iron_trapdoor));
		CombinationRegistry.addCombination("stickWood", Blocks.iron_bars, WILDCARD_VALUE, MODE_AND, new ItemStack(Blocks.rail));
		CombinationRegistry.addCombination(new ItemStack(Blocks.cobblestone_wall), new ItemStack(Blocks.mossy_cobblestone), MODE_OR, new ItemStack(Blocks.cobblestone_wall, 1, 1));
		CombinationRegistry.addCombination(new ItemStack(Blocks.dispenser), new ItemStack(Blocks.hopper), MODE_AND, new ItemStack(Blocks.dropper));
		CombinationRegistry.addCombination(new ItemStack(Blocks.torch), new ItemStack(Items.redstone), MODE_OR, new ItemStack(Blocks.redstone_torch));
		CombinationRegistry.addCombination(new ItemStack(Items.minecart), new ItemStack(Blocks.chest), MODE_AND, new ItemStack(Items.chest_minecart));
		CombinationRegistry.addCombination(new ItemStack(Items.minecart), new ItemStack(Blocks.furnace), MODE_AND, new ItemStack(Items.furnace_minecart));
		CombinationRegistry.addCombination(new ItemStack(Items.minecart), new ItemStack(Blocks.tnt), MODE_AND, new ItemStack(Items.tnt_minecart));
		CombinationRegistry.addCombination(new ItemStack(Items.minecart), new ItemStack(Blocks.hopper), MODE_AND, new ItemStack(Items.hopper_minecart));
		CombinationRegistry.addCombination(new ItemStack(Blocks.rail), new ItemStack(Blocks.redstone_torch), MODE_AND, new ItemStack(Blocks.activator_rail));
		CombinationRegistry.addCombination(new ItemStack(Blocks.rail), new ItemStack(Blocks.wooden_pressure_plate), MODE_AND, new ItemStack(Blocks.detector_rail));
		CombinationRegistry.addCombination(new ItemStack(Blocks.rail), new ItemStack(Blocks.stone_pressure_plate), MODE_AND, new ItemStack(Blocks.detector_rail));
		CombinationRegistry.addCombination(new ItemStack(Blocks.rail), new ItemStack(Blocks.light_weighted_pressure_plate), MODE_AND, new ItemStack(Blocks.detector_rail));
		CombinationRegistry.addCombination(new ItemStack(Blocks.rail), new ItemStack(Blocks.heavy_weighted_pressure_plate), MODE_AND, new ItemStack(Blocks.detector_rail));
		CombinationRegistry.addCombination(new ItemStack(Blocks.rail), new ItemStack(Items.gold_ingot), MODE_AND, new ItemStack(Blocks.golden_rail));
		CombinationRegistry.addCombination(new ItemStack(Blocks.rail), new ItemStack(Items.furnace_minecart), MODE_OR, new ItemStack(Blocks.golden_rail));
		CombinationRegistry.addCombination(new ItemStack(Blocks.glowstone), new ItemStack(Blocks.redstone_torch), MODE_AND, new ItemStack(Blocks.redstone_lamp));
		CombinationRegistry.addCombination(new ItemStack(Items.prismarine_shard), new ItemStack(Items.diamond), MODE_OR, new ItemStack(Items.prismarine_crystals));
		CombinationRegistry.addCombination(new ItemStack(Items.prismarine_shard), new ItemStack(Items.emerald), MODE_OR, new ItemStack(Items.prismarine_crystals));
		CombinationRegistry.addCombination(new ItemStack(Blocks.tallgrass), new ItemStack(Blocks.sand), false, false, MODE_OR, new ItemStack(Blocks.deadbush));
		CombinationRegistry.addCombination(new ItemStack(Blocks.tallgrass), new ItemStack(Blocks.sand), false, false, MODE_AND, new ItemStack(Blocks.cactus));
		CombinationRegistry.addCombination("treeSapling", Blocks.sand, WILDCARD_VALUE, MODE_AND, new ItemStack(Blocks.deadbush));
		CombinationRegistry.addCombination(new ItemStack(Items.ender_pearl), new ItemStack(Blocks.chest), MODE_AND, new ItemStack(Blocks.ender_chest));
		CombinationRegistry.addCombination(new ItemStack(Blocks.glass), new ItemStack(Blocks.snow), MODE_AND, new ItemStack(Blocks.ice));
		CombinationRegistry.addCombination(new ItemStack(Blocks.sponge, 1, 0), new ItemStack(Items.water_bucket), MODE_AND, new ItemStack(Blocks.sponge, 1, 1));
		CombinationRegistry.addCombination(new ItemStack(Items.blaze_powder), new ItemStack(Items.gunpowder), MODE_OR, new ItemStack(Items.fire_charge));
		CombinationRegistry.addCombination(new ItemStack(Blocks.sand, 1, 0), new ItemStack(Blocks.stone_stairs), MODE_OR, new ItemStack(Blocks.sandstone_stairs));
		CombinationRegistry.addCombination(new ItemStack(Blocks.sand, 1, 1), new ItemStack(Blocks.stone_stairs), MODE_OR, new ItemStack(Blocks.red_sandstone_stairs));
		CombinationRegistry.addCombination(new ItemStack(Blocks.sand, 1, 0), new ItemStack(Items.dye, 1, EnumDyeColor.RED.getDyeDamage()), MODE_AND, new ItemStack(Blocks.sand, 1, 1));
		for(int i = 0; i <= 2; i++)
			CombinationRegistry.addCombination(new ItemStack(Blocks.sandstone, 1, i), new ItemStack(Items.dye, 1, EnumDyeColor.RED.getDyeDamage()), MODE_AND, new ItemStack(Blocks.red_sandstone, 1, i));
		CombinationRegistry.addCombination(new ItemStack(Blocks.sandstone_stairs), new ItemStack(Items.dye, 1, EnumDyeColor.RED.getDyeDamage()), MODE_AND, new ItemStack(Blocks.red_sandstone_stairs));
		CombinationRegistry.addCombination(new ItemStack(Items.book), new ItemStack(Blocks.planks), true, false, MODE_OR, new ItemStack(Blocks.bookshelf));
		CombinationRegistry.addCombination("record", Blocks.noteblock, WILDCARD_VALUE, MODE_AND, new ItemStack(Blocks.jukebox));
		CombinationRegistry.addCombination("stickWood", Blocks.vine, WILDCARD_VALUE, MODE_AND, new ItemStack(Blocks.ladder));
		CombinationRegistry.addCombination(new ItemStack(Items.prismarine_shard), new ItemStack(Blocks.cobblestone), MODE_AND, new ItemStack(Blocks.prismarine, 1, 0));
		CombinationRegistry.addCombination(new ItemStack(Items.prismarine_shard), new ItemStack(Blocks.stonebrick, 1, 0), MODE_AND, new ItemStack(Blocks.prismarine, 1, 1));
		CombinationRegistry.addCombination(new ItemStack(Blocks.prismarine), new ItemStack(Items.dye, 1, EnumDyeColor.BLACK.getDyeDamage()), MODE_AND, false, true, new ItemStack(Blocks.prismarine, 1, 2));
		CombinationRegistry.addCombination(new ItemStack(Items.prismarine_crystals), new ItemStack(Blocks.glowstone), MODE_OR, new ItemStack(Blocks.sea_lantern));
		CombinationRegistry.addCombination(new ItemStack(Items.prismarine_crystals), new ItemStack(Items.prismarine_shard), MODE_AND, new ItemStack(Blocks.sea_lantern));
		CombinationRegistry.addCombination(new ItemStack(Items.prismarine_crystals), new ItemStack(Blocks.prismarine), MODE_AND, true, false, new ItemStack(Blocks.sea_lantern));
		CombinationRegistry.addCombination(new ItemStack(Blocks.red_mushroom), new ItemStack(Blocks.soul_sand), MODE_AND, new ItemStack(Items.nether_wart));
		CombinationRegistry.addCombination(new ItemStack(Blocks.prismarine), new ItemStack(Items.flint), MODE_OR, false, true, new ItemStack(Items.prismarine_shard));
		
	}
	
	public static void registerMinestuckRecipes() {
		
		//set up vanilla recipes
		RecipeSorter.register("minestuck:notmirrored", Recipes.NonMirroredRecipe.class, RecipeSorter.Category.SHAPED, "before:minecraft:shaped");
		RecipeSorter.register("minestuck:emptycard", Recipes.EmptyCardRecipe.class, RecipeSorter.Category.SHAPED, "before:minecraft:shaped");
		
		GameRegistry.addRecipe(new ItemStack(Minestuck.blockComputerOff,1,0),new Object[]{ "XXX","XYX","XXX",'Y',new ItemStack(Minestuck.blockStorage, 1, 0),'X',new ItemStack(Items.iron_ingot,1)});
		GameRegistry.addRecipe(new ItemStack(Minestuck.blockStorage,1,0),new Object[]{ "XXX","XXX","XXX",'X',new ItemStack(Minestuck.rawCruxite, 1)});
		cardRecipe = GameRegistry.addShapedRecipe(new ItemStack(Minestuck.captchaCard,8,0),new Object[]{ "XXX","XYX","XXX",'Y',new ItemStack(Minestuck.rawCruxite, 1),'X',new ItemStack(Items.paper,1)});
		cardRecipeAdded = true;
		GameRegistry.addRecipe(new ItemStack(Minestuck.clawHammer),new Object[]{ " XX","XY "," Y ",'X',new ItemStack(Items.iron_ingot),'Y',new ItemStack(Items.stick)});
		GameRegistry.addRecipe(new ItemStack(Minestuck.component,1,0),new Object[]{ " X "," Y "," Y ",'X',new ItemStack(Items.bowl),'Y',new ItemStack(Items.stick)});
		GameRegistry.addRecipe(new ItemStack(Minestuck.component,1,2),new Object[]{ "XYX","YXY","XYX",'X',new ItemStack(Blocks.stained_hardened_clay,1,0),'Y',new ItemStack(Blocks.stained_hardened_clay,1,15)});
		GameRegistry.addRecipe(new ItemStack(Minestuck.component,1,2),new Object[]{ "XYX","YXY","XYX",'Y',new ItemStack(Blocks.stained_hardened_clay,1,0),'X',new ItemStack(Blocks.stained_hardened_clay,1,15)});
		GameRegistry.addRecipe(new ItemStack(Minestuck.disk, 1, 0),new Object[]{ "X X"," Y ","X X",'X',new ItemStack(Minestuck.rawCruxite, 1),'Y',new ItemStack(Items.iron_ingot,1)});
		GameRegistry.addRecipe(new ItemStack(Minestuck.disk, 1, 1),new Object[]{ " X ","XYX"," X ",'X',new ItemStack(Minestuck.rawCruxite, 1),'Y',new ItemStack(Items.iron_ingot,1)});
		GameRegistry.addShapelessRecipe(new ItemStack(Minestuck.rawCruxite, 9),new  ItemStack(Minestuck.blockStorage,1,0));
		GameRegistry.addRecipe(new ItemStack(Minestuck.cane, 1), new Object[] {"  X", " X ", "X  ", 'X', new ItemStack(Items.stick, 1)});
		GameRegistry.addRecipe(new ItemStack(Minestuck.deuceClub, 1), new Object[] {"  Y", " X ", "X  ", 'X', new ItemStack(Items.stick, 1), 'Y', new ItemStack(Blocks.planks, 1)});
		ItemStack crux = new ItemStack(Minestuck.rawCruxite);
		ItemStack cruxBl = new ItemStack(Minestuck.blockStorage, 1, 0);
		ItemStack card = new ItemStack(Minestuck.captchaCard);
		GameRegistry.addRecipe(new Recipes.EmptyCardRecipe(3, 1, new ItemStack[]{cruxBl.copy(), card.copy(), crux.copy()}, new ItemStack(Minestuck.modusCard, 1, 0)));
		GameRegistry.addRecipe(new Recipes.EmptyCardRecipe(3, 1, new ItemStack[]{crux.copy(), card.copy(), cruxBl.copy()}, new ItemStack(Minestuck.modusCard, 1, 1)));
		GameRegistry.addSmelting(Minestuck.goldSeeds, new ItemStack(Items.gold_nugget), 0.1F);
		
		//add grist conversions
		GristRegistry.addGristConversion(new ItemStack(Minestuck.coloredDirt, 1), false, new GristSet(new GristType[] {GristType. Build}, new int[] {1}));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.blockMachine,1,4), true, new GristSet(new GristType[] {GristType. Build,GristType.Garnet,GristType.Ruby}, new int[] {150, 25, 10}));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.blockStorage, 1, 1), true, new GristSet(new GristType[] {GristType. Build}, new int[] {2}));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.component,1, 1), true, new GristSet(new GristType[] {GristType.Build, GristType.Mercury}, new int[] {5, 12}));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.component,1, 2), true, new GristSet(new GristType[] {GristType.Shale, GristType.Marble}, new int[] {25, 25}));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.crockerSpork), false, new GristSet(new GristType[] {GristType.Build, GristType.Iodine,GristType.Chalk,GristType.Ruby}, new int[] {70, 34, 34, 6}));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.cruxiteApple, 1), false, new GristSet());
		GristRegistry.addGristConversion(new ItemStack(Minestuck.clawHammer), false, new GristSet(GristType.Build, 5));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.fearNoAnvil), false, new GristSet(new GristType[] {GristType.Build, GristType.Garnet, GristType.Diamond, GristType.Gold, GristType.Quartz}, new int[] {120, 40, 5, 5, 10}));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.ninjaSword), false, new GristSet(new GristType[] {GristType.Build, GristType.Quartz, GristType.Rust}, new int[] {20, 5, 4}));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.pogoHammer), false, new GristSet(new GristType[] {GristType.Build, GristType.Shale}, new int[] {20, 10}));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.regiSickle), false, new GristSet(new GristType[] {GristType.Build, GristType.Tar,GristType.Gold}, new int[] {60, 15, 5}));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.regisword), false, new GristSet(new GristType[] {GristType.Build, GristType.Tar,GristType.Gold}, new int[] {64, 20, 8}));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.sickle), false, new GristSet(new GristType[] {GristType.Build}, new int[] {10}));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.sledgeHammer), false, new GristSet(new GristType[] {GristType.Build, GristType.Shale}, new int[] {10, 2}));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.homesSmellYaLater), false, new GristSet(new GristType[] {GristType.Build, GristType.Amber, GristType.Amethyst}, new int[] {20, 8, 4}));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.spearCane), false, new GristSet(new GristType[] {GristType.Build, GristType.Mercury, GristType.Quartz}, new int[] {20, 6, 3}));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.katana), false, new GristSet(new GristType[] {GristType.Build, GristType.Diamond, GristType.Quartz}, new int[] {80, 8, 20}));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.transportalizer), false, new GristSet(new GristType[] {GristType.Build, GristType.Garnet, GristType.Rust, GristType.Uranium}, new int[] {110, 10, 10, 8}));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.modusCard, 1, 2), true, new GristSet(GristType.Build, 90));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.goldSeeds), new GristSet(GristType.Gold, 3));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.modusCard, 1, 3), true, new GristSet(GristType.Build, 200));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.metalBoat, 1, 0), true, new GristSet(GristType.Rust, 65));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.metalBoat, 1, 1), true, new GristSet(GristType.Gold, 65));
		GristRegistry.addGristConversion(new ItemStack(Minestuck.layeredSand), new GristSet(GristType.Shale, 1));
		
		//add Designix combinations
		CombinationRegistry.addCombination(new ItemStack(Items.iron_sword), new ItemStack(Items.rotten_flesh), MODE_AND, false, true, new ItemStack(Minestuck.ninjaSword));
		CombinationRegistry.addCombination(new ItemStack(Items.iron_sword), new ItemStack(Minestuck.component, 1, 2), MODE_AND, false, true, new ItemStack(Minestuck.regisword));
		CombinationRegistry.addCombination(new ItemStack(Minestuck.ninjaSword), new ItemStack(Minestuck.component, 1, 2), MODE_AND, false, true, new ItemStack(Minestuck.regisword));
		CombinationRegistry.addCombination(new ItemStack(Minestuck.ninjaSword), new ItemStack(Blocks.obsidian), MODE_AND, new ItemStack(Minestuck.katana));
		
		CombinationRegistry.addCombination(new ItemStack(Items.iron_hoe), new ItemStack(Items.wheat), MODE_AND, false, true, new ItemStack(Minestuck.sickle));
		CombinationRegistry.addCombination(new ItemStack(Minestuck.sickle), new ItemStack(Minestuck.component, 1, 2), MODE_AND, false, true, new ItemStack(Minestuck.regiSickle));
		
		CombinationRegistry.addCombination(new ItemStack(Minestuck.clawHammer), new ItemStack(Blocks.brick_block), MODE_AND, false, false, new ItemStack(Minestuck.sledgeHammer));
		CombinationRegistry.addCombination(new ItemStack(Items.slime_ball), new ItemStack(Minestuck.sledgeHammer), MODE_AND, false, false, new ItemStack(Minestuck.pogoHammer));
		CombinationRegistry.addCombination(new ItemStack(Minestuck.pogoHammer), new ItemStack(Blocks.anvil), MODE_AND, false, false, new ItemStack(Minestuck.fearNoAnvil));
		
		CombinationRegistry.addCombination(new ItemStack(Minestuck.cane), new ItemStack(Items.iron_sword), MODE_AND, new ItemStack(Minestuck.spearCane));
		CombinationRegistry.addCombination(new ItemStack(Minestuck.cane), new ItemStack(Minestuck.ninjaSword), MODE_AND, new ItemStack(Minestuck.spearCane));
		
		CombinationRegistry.addCombination(new ItemStack(Minestuck.component, 1, 0), new ItemStack(Items.iron_ingot), MODE_AND, new ItemStack(Minestuck.component, 1, 1));
		CombinationRegistry.addCombination(new ItemStack(Minestuck.component, 1, 1), new ItemStack(Items.cake), MODE_AND, new ItemStack(Minestuck.crockerSpork));
		
		CombinationRegistry.addCombination(new ItemStack(Minestuck.crockerSpork), new ItemStack(Minestuck.captchaCard), MODE_OR, false, true, new ItemStack(Minestuck.blockMachine, 1, 4));
		CombinationRegistry.addCombination(new ItemStack(Items.ender_pearl), new ItemStack(Blocks.iron_block), MODE_AND, false, false, new ItemStack(Minestuck.transportalizer));
		
		CombinationRegistry.addCombination(new ItemStack(Minestuck.modusCard, 1, 0), new ItemStack(Minestuck.modusCard, 1, 1), MODE_AND, true, true, new ItemStack(Minestuck.modusCard, 1, 2));
		CombinationRegistry.addCombination("stickWood", Minestuck.modusCard, OreDictionary.WILDCARD_VALUE, MODE_OR, new ItemStack(Minestuck.modusCard, 1, 3));
		CombinationRegistry.addCombination("treeSapling", Minestuck.modusCard, OreDictionary.WILDCARD_VALUE, MODE_OR, new ItemStack(Minestuck.modusCard, 1, 3));
		CombinationRegistry.addCombination("treeLeaves", Minestuck.modusCard, OreDictionary.WILDCARD_VALUE, MODE_OR, new ItemStack(Minestuck.modusCard, 1, 3));	//Not planks and logs though. Too little branch-related.
		
		CombinationRegistry.addCombination(new ItemStack(Items.wheat_seeds), new ItemStack(Items.gold_nugget), MODE_AND, new ItemStack(Minestuck.goldSeeds));
		CombinationRegistry.addCombination(new ItemStack(Items.wheat_seeds), new ItemStack(Items.gold_ingot), MODE_AND, new ItemStack(Minestuck.goldSeeds));
		CombinationRegistry.addCombination(new ItemStack(Items.boat), new ItemStack(Items.minecart), MODE_OR, new ItemStack(Minestuck.metalBoat, 1, 0));
		CombinationRegistry.addCombination(new ItemStack(Items.boat), new ItemStack(Items.iron_ingot), MODE_AND, new ItemStack(Minestuck.metalBoat, 1, 0));
		CombinationRegistry.addCombination(new ItemStack(Items.boat), new ItemStack(Blocks.iron_block), MODE_AND, new ItemStack(Minestuck.metalBoat, 1, 0));
		CombinationRegistry.addCombination(new ItemStack(Items.boat), new ItemStack(Items.gold_ingot), MODE_AND, new ItemStack(Minestuck.metalBoat, 1, 1));
		CombinationRegistry.addCombination(new ItemStack(Items.boat), new ItemStack(Blocks.gold_block), MODE_AND, new ItemStack(Minestuck.metalBoat, 1, 1));
		CombinationRegistry.addCombination(new ItemStack(Blocks.dirt), new ItemStack(Items.dye, 1, EnumDyeColor.BLUE.getDyeDamage()), MODE_OR, new ItemStack(Minestuck.coloredDirt, 1, 0));
		CombinationRegistry.addCombination(new ItemStack(Blocks.dirt), new ItemStack(Items.dye, 1, EnumDyeColor.LIME.getDyeDamage()), MODE_OR, new ItemStack(Minestuck.coloredDirt, 1, 1));
		CombinationRegistry.addCombination(new ItemStack(Blocks.sand), new ItemStack(Blocks.snow_layer), MODE_OR, new ItemStack(Minestuck.layeredSand));
		
		//Register chest loot
		if(MinestuckConfig.cardLoot)
		{
			ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(Minestuck.captchaCard, 0, 1, 3, 10));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING).addItem(new WeightedRandomChestContent(Minestuck.captchaCard, 0, 1, 2, 8));
			ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(Minestuck.captchaCard, 0, 1, 4, 10));
		}
		
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Items.experience_bottle, 1, 0), 1, 2, 3));
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Items.iron_ingot, 1, 0), 1, 5, 9));
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Items.bow, 1, 0), 1, 1, 5));
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Items.redstone, 1, 0), 1, 6, 7));
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Items.stone_sword, 1, 0), 1, 1, 5));
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Items.gold_ingot, 1, 0), 1, 3, 6));
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Items.diamond, 1, 0), 1, 2, 1));
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Items.saddle, 1, 0), 1, 1, 3));
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Items.iron_sword, 1, 0), 1, 1, 2));
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Items.ender_pearl, 1, 0), 1, 2, 1));
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Items.arrow, 1, 0), 2, 10, 6));
		
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Minestuck.blockStorage, 1, 1), 1, 1, 3));
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Minestuck.clawHammer, 1, 0), 1, 1, 5));
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Minestuck.sickle, 1, 0), 1, 1, 5));
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Minestuck.ninjaSword, 1, 0), 1, 1, 5));
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Minestuck.sledgeHammer, 1, 0), 1, 1, 4));
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Minestuck.component, 1, 0), 1, 1, 5));
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Minestuck.component, 1, 1), 1, 1, 4));
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Minestuck.transportalizer, 1, 0), 1, 1, 2));
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Minestuck.rawCruxite, 1, 0), 1, 5, 5));
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Minestuck.homesSmellYaLater, 1, 0), 1, 1, 2));
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Minestuck.pogoHammer, 1, 0), 1, 1, 2));
		basicMediumChest.add(new WeightedRandomChestContent(new ItemStack(Minestuck.spearCane, 1, 0), 1, 1, 2));
	}
	
	public static void registerModRecipes() 
	{
		
		GristRegistry.addGristConversion("ingotCopper", new GristSet(new GristType[] {GristType.Rust, GristType.Cobalt}, new int[] {16, 3}));
		GristRegistry.addGristConversion("oreCopper", new GristSet(new GristType[] {GristType.Rust, GristType.Cobalt, GristType.Build}, new int[] {16, 3, 4}));
		GristRegistry.addGristConversion("ingotTin", new GristSet(new GristType[] {GristType.Rust, GristType.Caulk}, new int[] {12, 8}));
		GristRegistry.addGristConversion("oreTin", new GristSet(new GristType[] {GristType.Rust, GristType.Caulk, GristType.Build}, new int[] {12, 8, 4}));
		GristRegistry.addGristConversion("ingotSilver", new GristSet(new GristType[] {GristType.Rust, GristType.Mercury}, new int[] {12, 8}));
		GristRegistry.addGristConversion("oreSilver", new GristSet(new GristType[] {GristType.Rust, GristType.Mercury, GristType.Build}, new int[] {12, 8, 4}));
		GristRegistry.addGristConversion("ingotLead", new GristSet(new GristType[] {GristType.Rust, GristType.Cobalt, GristType.Shale}, new int[] {12, 4, 4}));
		GristRegistry.addGristConversion("oreLead", new GristSet(new GristType[] {GristType.Rust, GristType.Cobalt, GristType.Shale, GristType.Build}, new int[] {12, 4, 4, 4}));
		GristRegistry.addGristConversion("ingotNickel", new GristSet(new GristType[] {GristType.Rust, GristType.Sulfur}, new int[] {12, 8}));
		GristRegistry.addGristConversion("oreNickel", new GristSet(new GristType[] {GristType.Rust, GristType.Sulfur, GristType.Build}, new int[] {12, 8, 4}));
		GristRegistry.addGristConversion("ingotInvar", new GristSet(new GristType[] {GristType.Rust, GristType.Sulfur}, new int[] {12, 5}));
		GristRegistry.addGristConversion("ingotAluminium", new GristSet(new GristType[] {GristType.Rust, GristType.Chalk}, new int[] {12, 6}));
		GristRegistry.addGristConversion("oreAluminium", new GristSet(new GristType[] {GristType.Rust, GristType.Chalk, GristType.Build}, new int[] {12, 6, 4}));
		
		GristRegistry.addGristConversion("ingotCobalt", new GristSet(new GristType[] {GristType.Cobalt}, new int[] {18}));
		GristRegistry.addGristConversion("oreCobalt", new GristSet(new GristType[] {GristType.Cobalt, GristType.Build}, new int[] {18, 4}));
		GristRegistry.addGristConversion("ingotArdite", new GristSet(new GristType[] {GristType.Garnet, GristType.Sulfur}, new int[] {12, 8}));
		GristRegistry.addGristConversion("oreArdite", new GristSet(new GristType[] {GristType.Garnet, GristType.Sulfur, GristType.Build}, new int[] {12, 8, 4}));
		GristRegistry.addGristConversion("ingotRedAlloy", new GristSet(new GristType[] {GristType.Rust, GristType.Garnet}, new int[] {18, 32}));
		if(!OreDictionary.getOres("ingotRedAlloy").isEmpty())
			CombinationRegistry.addCombination(new ItemStack(Items.iron_ingot), new ItemStack(Items.redstone), MODE_OR, OreDictionary.getOres("ingotRedAlloy").get(0));
		
		try 
		{
			if(Loader.isModLoaded("IronChest"))
			{
				Block ironChest = ((Block) (Class.forName("cpw.mods.ironchest.IronChest").getField("ironChestBlock").get(null)));
				GristRegistry.addGristConversion(ironChest, 0, new GristSet(new GristType[] {GristType.Build, GristType.Rust}, new int[] {16, 128}));
				CombinationRegistry.addCombination(new ItemStack(Blocks.chest), new ItemStack(Items.iron_ingot), true, new ItemStack(ironChest, 1, 0));
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			Debug.print("Exception while getting things for mod \"IronChest\".");
		}
		
		
		registerRecipes(new Minegicka3Support(), "minegicka3", false);
		registerRecipes(new NeverSayNetherSupport(), "nsn", false);
		registerRecipes(new ExtraUtilitiesSupport(), "ExtraUtilities", false);
		registerRecipes(new TinkersConstructSupport(), "TConstruct", false);
		
		if(Loader.isModLoaded("MineTweaker3"))
			Minetweaker3Support.registerClasses();
		
	}
	
	public static ItemStack getFirstOreItem(String name)
	{
		if(OreDictionary.getOres(name).isEmpty())
			return null;
		else return OreDictionary.getOres(name).get(0);
	}
	/**
	 * Given a punched card or a carved dowel, returns a new item that represents the encoded data.
	 * 
	 * @param card - The dowel or card with encoded data
	 * @param b - If it is used for a dowel in alchemy.
	 * @return An item, or null if the data was invalid.
	 */
	public static ItemStack getDecodedItem(ItemStack card)
	{
		
		if (card == null) {return null;}
		
		NBTTagCompound tag = card.getTagCompound();
		
		if (tag == null || !tag.hasKey("contentID"))
		{
			return null;
		}
		
		if (!Item.itemRegistry.containsKey(new ResourceLocation(tag.getString("contentID")))) {return null;}
		ItemStack newItem = new ItemStack((Item)Item.itemRegistry.getObject(new ResourceLocation(tag.getString(("contentID")))), 1, tag.getInteger("contentMeta"));
		
		if(tag.hasKey("contentTags"))
			newItem.setTagCompound(tag.getCompoundTag("contentTags"));
		if(tag.hasKey("contentSize"))
			newItem.stackSize = tag.getInteger("contentSize");
		
		return newItem;
		
	}
	
	/**
	 * Given a punched card, this method returns a new item that represents the encoded data,
	 * or it just returns the item directly if it's not a punched card.
	 */
	public static ItemStack getDecodedItemDesignix(ItemStack card)
	{
		
		if (card == null) {return null;}
		
		if (!(card.getItem().equals(Minestuck.captchaCard) && card.hasTagCompound() && card.getTagCompound().hasKey("contentID")))
		{
			return card;
		}
		else
		{
			return getDecodedItem(card);
		}
	}
	
	public static ItemStack createEncodedItem(ItemStack item, boolean registerToCard) {
		NBTTagCompound nbt = null;
		if(item != null) {
			nbt = new NBTTagCompound();
			nbt.setString("contentID", Item.itemRegistry.getNameForObject(item.getItem()).toString());
			nbt.setInteger("contentMeta", item.getItemDamage());
		}
		ItemStack stack = new ItemStack(registerToCard?Minestuck.captchaCard:Minestuck.cruxiteDowel);
		stack.setTagCompound(nbt);
		return stack;
	}
	
	public static ItemStack createCard(ItemStack item, boolean punched)
	{
		ItemStack stack = createEncodedItem(item, true);
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setBoolean("punched", punched);
		if(!punched)
		{
			if(item.hasTagCompound())
				stack.getTagCompound().setTag("contentTags", item.getTagCompound());
			stack.getTagCompound().setInteger("contentSize", item.stackSize);
		}
		
		return stack;
	}
	
	/**
	 * Adds all the recipes that are based on the existing vanilla crafting registries, like grist conversions of items composed of oither things.
	 */
	public static void registerDynamicRecipes() {
		
		recipeList = new HashMap<List<Object>, Object>();
		int invalid = 0;
		
		Debug.print("Looking for dynamic grist conversions...");
		for (Object recipe : CraftingManager.getInstance().getRecipeList())
		{
			try
			{
				if (recipe instanceof ShapedRecipes) {
					ShapedRecipes newRecipe = (ShapedRecipes) recipe;
					//Debug.print("Found the recipe for "+"ITEM"+", id "+newRecipe.getRecipeOutput());
					recipeList.put(Arrays.asList(newRecipe.getRecipeOutput().getItem(),newRecipe.getRecipeOutput().getItemDamage()), recipe);
				} else if (recipe instanceof ShapelessRecipes) {
					ShapelessRecipes newRecipe = (ShapelessRecipes) recipe;
					//Debug.print("Found the recipe for "+"ITEM"+", id "+newRecipe.getRecipeOutput().itemID+":"+newRecipe.getRecipeOutput().getItemDamage());
					recipeList.put(Arrays.asList(newRecipe.getRecipeOutput().getItem(),newRecipe.getRecipeOutput().getItemDamage()), recipe);
				} else if (recipe instanceof ShapedOreRecipe) {
					ShapedOreRecipe newRecipe = (ShapedOreRecipe) recipe;
					//Debug.print("Found the recipe for "+"ITEM"+", id "+newRecipe.getRecipeOutput().itemID+":"+newRecipe.getRecipeOutput().getItemDamage());
					recipeList.put(Arrays.asList(newRecipe.getRecipeOutput().getItem(),newRecipe.getRecipeOutput().getItemDamage()), recipe);
				} else if (recipe instanceof ShapelessOreRecipe) {
					ShapelessOreRecipe newRecipe = (ShapelessOreRecipe) recipe;
					//Debug.print("Found the recipe for "+"ITEM"+", id "+newRecipe.getRecipeOutput().itemID+":"+newRecipe.getRecipeOutput().getItemDamage());
					recipeList.put(Arrays.asList(newRecipe.getRecipeOutput().getItem(),newRecipe.getRecipeOutput().getItemDamage()), recipe);
				} else {
					//Debug.print("Found the recipe for unkown format: "+recipe.getClass());
					invalid++;
				}
			}
			catch (NullPointerException e)
			{
				Debug.printf("a null pointer exception was thrown for %s", recipe);
			}
		}
		Debug.print("Found "+recipeList.size()+" valid recipes, and "+invalid+" unknown ones.");
		
		Debug.print("Calculating grist conversion...");
		Iterator<Entry<List<Object>, Object>> it = recipeList.entrySet().iterator();
        while (it.hasNext()) {
        	Map.Entry<List<Object>, Object> pairs = it.next();
        	//Debug.print("Getting recipe with key"+pairs.getKey()+" and value "+pairs.getValue());
			lookedOver = new HashMap<List<Object>, Boolean>();
        	getRecipe(pairs.getValue());
        }
		
		registerRecipes(new Minegicka3Support(), "minegicka3", true);
		
		Debug.print("Done. Added "+returned+" grist conversions.");
	}
	
	private static boolean getRecipe(Object recipe) {
		if (recipe instanceof ShapedRecipes) {
			ShapedRecipes newRecipe = (ShapedRecipes) recipe;
			//Debug.print("found shaped recipe. Output of "+newRecipe.getRecipeOutput());
			if (lookedOver.get(Arrays.asList(newRecipe.getRecipeOutput().getItem(),newRecipe.getRecipeOutput().getHasSubtypes() ? newRecipe.getRecipeOutput().getItemDamage() : 0)) != null) {
				////Debug.print("	Recursive recipe! Recipe failed.");
				return false;
			} else {
				lookedOver.put(Arrays.asList(newRecipe.getRecipeOutput().getItem(),newRecipe.getRecipeOutput().getHasSubtypes() ? newRecipe.getRecipeOutput().getItemDamage() : 0),true);
			}
			if (GristRegistry.getGristConversion(newRecipe.getRecipeOutput()) != null) {return false;};
			GristSet set = new GristSet();
			for (ItemStack item : newRecipe.recipeItems) {
				if (GristRegistry.getGristConversion(item) != null) {
					//Debug.print("	Adding compo: "+item);
					set.addGrist(GristRegistry.getGristConversion(item));
				} else if (item != null && item.getItem() != null) {
					Object subrecipe = recipeList.get(Arrays.asList(item.getItem(),item.getHasSubtypes() && !((Integer)item.getItemDamage()).equals(32767) ? item.getItemDamage() : 0));
					if (subrecipe != null) {
						//Debug.print("	Could not find "+item+". Looking up subrecipe... {");
						 if (getRecipe(subrecipe)) {
							 if (GristRegistry.getGristConversion(item) == null) {
								//Debug.print("	} Recipe failure! getRecipe did not return expeted boolean value.");
								 return false;
							 }
							set.addGrist(GristRegistry.getGristConversion(item));
							//Debug.print("	}");
						 } else {
							//Debug.print("	}");
							 return false;
						 }
					} else {
						//Debug.print("	Could not find "+"ITEM"+" ("+item+"). Recipe failed!");
						return false;
					}
				}
			}
			set.scaleGrist(1/(float)newRecipe.getRecipeOutput().stackSize);
			GristRegistry.addGristConversion(newRecipe.getRecipeOutput(),newRecipe.getRecipeOutput().getHasSubtypes(),set);
		} else if (recipe instanceof ShapelessRecipes) {
			//Debug.print("found shapeless recipe. Output of "+"ITEM");
			ShapelessRecipes newRecipe = (ShapelessRecipes) recipe;
			if (lookedOver.get(Arrays.asList(newRecipe.getRecipeOutput().getItem(),newRecipe.getRecipeOutput().getHasSubtypes() ? newRecipe.getRecipeOutput().getItemDamage() : 0)) != null) {
				//Debug.print("	Recursive recipe! Recipe failed.");
				return false;
			} else {
				lookedOver.put(Arrays.asList(newRecipe.getRecipeOutput().getItem(),newRecipe.getRecipeOutput().getHasSubtypes() ? newRecipe.getRecipeOutput().getItemDamage() : 0),true);
			}
			if (GristRegistry.getGristConversion(newRecipe.getRecipeOutput()) != null) {return false;};
			GristSet set = new GristSet();
			for (Object obj : newRecipe.recipeItems) {
				ItemStack item = (ItemStack) obj;
				if (GristRegistry.getGristConversion(item) != null) {
					//Debug.print("	Adding compo: "+"ITEM");
					set.addGrist(GristRegistry.getGristConversion(item));
				} else if (item != null) {
					Object subrecipe = recipeList.get(Arrays.asList(item.getItem(),item.getHasSubtypes() && !((Integer)item.getItemDamage()).equals(32767) ? item.getItemDamage() : 0));
					if (subrecipe != null) {
						//Debug.print("	Could not find "+"ITEM"+". Looking up subrecipe... {");
						 if (getRecipe(subrecipe)) {
							 if (GristRegistry.getGristConversion(item) == null) {
								 //Debug.print("	} Recipe failure! getRecipe did not return expeted boolean value.");
								 return false;
							 }
							 set.addGrist(GristRegistry.getGristConversion(item));
							 //Debug.print("	}");
						 } else {
							 //Debug.print("	}");
							 return false;
						 }
					} else {
						//Debug.print("	Could not find "+"ITEM"+" ("+item.itemID+":"+item.getItemDamage()+"). Recipe failed!");
						return false;
					}
				}
			}
			set.scaleGrist(1/(float)newRecipe.getRecipeOutput().stackSize);
			GristRegistry.addGristConversion(newRecipe.getRecipeOutput(),newRecipe.getRecipeOutput().getHasSubtypes(),set);
		} else if (recipe instanceof ShapedOreRecipe) {
			ShapedOreRecipe newRecipe = (ShapedOreRecipe) recipe;
			//Debug.print("found shaped oredict recipe. Output of "+newRecipe.getRecipeOutput());
			if (GristRegistry.getGristConversion(newRecipe.getRecipeOutput()) != null) {return false;};
			if (lookedOver.get(Arrays.asList(newRecipe.getRecipeOutput().getItem(),newRecipe.getRecipeOutput().getHasSubtypes() ? newRecipe.getRecipeOutput().getItemDamage() : 0)) != null) {
				//Debug.print("	Recursive recipe! Recipe failed.");
				return false;
			} else {
				lookedOver.put(Arrays.asList(newRecipe.getRecipeOutput().getItem(),newRecipe.getRecipeOutput().getHasSubtypes() ? newRecipe.getRecipeOutput().getItemDamage() : 0),true);
			}
			GristSet set = new GristSet();
			for (Object obj : newRecipe.getInput())
			{
				ItemStack item = null;
				if (obj == null)
					continue;
				if (obj instanceof List)
				{
					if (((List<?>) obj).size() == 0)
					{
						break;
					}
					item = (ItemStack) ((List<?>) obj).get(0);
				} else {
					item = (ItemStack) obj;
				}
				if (GristRegistry.getGristConversion(item) != null) {
					//Debug.print("	Adding compo: "+item);
					set.addGrist(GristRegistry.getGristConversion(item));
				} else if (item != null) {
					Object subrecipe = recipeList.get(Arrays.asList(item.getItem(),item.getHasSubtypes() && !((Integer)item.getItemDamage()).equals(32767) ? item.getItemDamage() : 0));
					if (subrecipe != null) {
						//Debug.print("	Could not find "+item+". Looking up subrecipe... {");
						 if (getRecipe(subrecipe)) {
							 if (GristRegistry.getGristConversion(item) == null) {
								//Debug.print("	} Recipe failure! getRecipe did not return expeted boolean value.");
								 return false;
							 }
							 set.addGrist(GristRegistry.getGristConversion(item));
							//Debug.print("	}");
						 } else {
							//Debug.print("	}");
							 return false;
						 }
					} else {
						//Debug.print("	Could not find "+item+". Recipe failed!");
						return false;
					}
				}
			}
			set.scaleGrist(1/(float)newRecipe.getRecipeOutput().stackSize);
			GristRegistry.addGristConversion(newRecipe.getRecipeOutput(),newRecipe.getRecipeOutput().getHasSubtypes(),set);
		} else if (recipe instanceof ShapelessOreRecipe) {
			//Debug.print("found shapeless oredict recipe. Output of "+"ITEM");
			ShapelessOreRecipe newRecipe = (ShapelessOreRecipe) recipe;
			if (lookedOver.get(Arrays.asList(newRecipe.getRecipeOutput().getItem(),newRecipe.getRecipeOutput().getHasSubtypes() ? newRecipe.getRecipeOutput().getItemDamage() : 0)) != null) {
				//Debug.print("	Recursive recipe! Recipe failed.");
				return false;
			} else {
				lookedOver.put(Arrays.asList(newRecipe.getRecipeOutput().getItem(),newRecipe.getRecipeOutput().getHasSubtypes() ? newRecipe.getRecipeOutput().getItemDamage() : 0),true);
			}
			if (GristRegistry.getGristConversion(newRecipe.getRecipeOutput()) != null) {return false;};
			GristSet set = new GristSet();
			for (Object obj : newRecipe.getInput()) {
				ItemStack item = null;
				if (obj == null) {break;}
				if (obj instanceof List) {
					if (((List<?>) obj).size() == 0) {
						//Debug.print("	Input list was empty!");
						break;
					}
					item = (ItemStack) ((List<?>) obj).get(0);
				} else {
					item = (ItemStack) obj;
				}
				if (GristRegistry.getGristConversion(item) != null) {
					//Debug.print("	Adding compo: "+"ITEM");
					set.addGrist(GristRegistry.getGristConversion(item));
				} else if (item != null) {
					Object subrecipe = recipeList.get(Arrays.asList(item.getItem(),item.getHasSubtypes() && !((Integer)item.getItemDamage()).equals(32767) ? item.getItemDamage() : 0));
					if (subrecipe != null) {
						//Debug.print("	Could not find "+"ITEM"+". Looking up subrecipe... {");
						 if (getRecipe(subrecipe)) {
							 if (GristRegistry.getGristConversion(item) == null) {
								 //Debug.print("	} Recipe failure! getRecipe did not return expeted boolean value.");
								 return false;
							 }
							 set.addGrist(GristRegistry.getGristConversion(item));
							 //Debug.print("	}");
						 } else {
							 //Debug.print("	}");
							 return false;
						 }
					} else {
						//Debug.print("	Could not find "+"ITEM"+" ("+item.itemID+":"+item.getItemDamage()+"). Recipe failed!");
						return false;
					}
				}
			}
			set.scaleGrist(1/(float)newRecipe.getRecipeOutput().stackSize);
			GristRegistry.addGristConversion(newRecipe.getRecipeOutput(),newRecipe.getRecipeOutput().getHasSubtypes(),set);
		} else {
			//Debug.print("found other recipe class: "+recipe.getClass());
		}
		
		returned ++;
		return true;
	}
	
	private static void registerRecipes(ModSupport modSupport, String modname, boolean dynamic)
	{
		try
		{
			if(Loader.isModLoaded(modname))
			{
				if(dynamic)
					modSupport.registerDynamicRecipes();
				else modSupport.registerRecipes();
			}
		}
		catch(Exception e)
		{
			Debug.print("Exception while creating"+(dynamic?" dynamic":"")+" recipes for mod \""+modname+"\":");
			e.printStackTrace();
		}
	}
	
	public static List<ItemStack> getItems(Object item, int damage)
	{
		if(item instanceof ItemStack)
			return Arrays.asList((ItemStack)item);
		if(item instanceof Item)
			return Arrays.asList(new ItemStack((Item) item, 1, damage));
		else
		{
			List<ItemStack> list = OreDictionary.getOres((String) item);
			return list;
		}
	}
	
	public static void checkRegistered(Block block, String name)
	{
		checkRegistered(new ItemStack(block, 1, WILDCARD_VALUE), name);
	}
	
	public static void checkRegistered(Item item, String name)
	{
		checkRegistered(new ItemStack(item, 1, WILDCARD_VALUE), name);
	}
	
	public static void checkRegistered(ItemStack item, String name)
	{
		String[] names = CombinationRegistry.getDictionaryNames(item);
		for(String toCompare : names)
			if(toCompare.equals(name))
				return;
		
		OreDictionary.registerOre(name, item);
	}
	
	public static void addOrRemoveRecipes(boolean addCardRecipe)
	{
		if(addCardRecipe && !cardRecipeAdded)
		{
			CraftingManager.getInstance().getRecipeList().add(cardRecipe);
			cardRecipeAdded = true;
		}
		else if(!addCardRecipe && cardRecipeAdded)
		{
			CraftingManager.getInstance().getRecipeList().remove(cardRecipe);
			cardRecipeAdded = false;
		}
	}
	
}
