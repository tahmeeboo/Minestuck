package com.mraof.minestuck.item.weapon;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.collect.Sets;
import com.mraof.minestuck.util.MinestuckAchievementHandler;

public class ItemHammer extends ItemWeapon
{	
	private int weaponDamage;
	private final EnumHammerType hammerType;
	public float efficiencyOnProperMaterial = 4.0F;
	public static final Set<Block> blocksEffectiveAgainst = Sets.newHashSet(new Block[] {Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.coal_block, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail});

	
	public ItemHammer(EnumHammerType hammerType)
	{
		super(blocksEffectiveAgainst);
		
		this.hammerType = hammerType;
		this.setMaxDamage(hammerType.getMaxUses());
		this.efficiencyOnProperMaterial = hammerType.getEfficiencyOnProperMaterial();
		switch(hammerType)
		{
		case CLAW:
			this.setUnlocalizedName("clawHammer");
			break;
		case SLEDGE:
			this.setUnlocalizedName("sledgeHammer");
			break;
		case POGO:
			this.setUnlocalizedName("pogoHammer");
			break;
		case TELESCOPIC:
			this.setUnlocalizedName("telescopicSassacrusher");
			break;
		case FEARNOANVIL:
			this.setUnlocalizedName("fearNoAnvil");
			break;
		case ZILLYHOO:
			this.setUnlocalizedName("zillyhooHammer");
			break;
		case POPAMATIC:
			this.setUnlocalizedName("popamaticVrillyhoo");
			break;
		case SCARLET:
			this.setUnlocalizedName("scarletZillyhoo");
			break;
		}
		this.weaponDamage = 3 + hammerType.getDamageVsEntity();
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player)
	{
		if(this.hammerType.equals(EnumHammerType.CLAW))
			player.triggerAchievement(MinestuckAchievementHandler.getHammer);
	}
	
	@Override
	public boolean canHarvestBlock(Block block) 
	{
		return block != null && (block.getMaterial() == Material.iron || block.getMaterial() == Material.anvil || block.getMaterial() == Material.rock);
	}

	@Override
	public float getStrVsBlock(ItemStack itemStack, Block block)
	{
		return block != null && (block.getMaterial() == Material.iron || block.getMaterial() == Material.anvil || block.getMaterial() == Material.rock) ? this.efficiencyOnProperMaterial : super.getStrVsBlock(itemStack, block);
	}

	@Override
	public int getAttackDamage()
	{
		return this.weaponDamage;
	}

	@Override
	public int getItemEnchantability()
	{
		return this.hammerType.getEnchantability();
	}

	@Override
	public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase player)
	{
		itemStack.damageItem(1, player);
		if(hammerType.equals(EnumHammerType.POGO))
		{
			target.motionY = Math.max(target.motionY, Math.min(getPogoMotion(itemStack)*2, Math.abs(player.motionY) + target.motionY + getPogoMotion(itemStack)));
			player.motionY = 0;
			player.fallDistance = 0;
		}
		else if(hammerType.equals(EnumHammerType.SCARLET))
			target.setFire(50);
		else if (hammerType.equals(EnumHammerType.POPAMATIC) )
			target.attackEntityFrom(DamageSource.magic , (float) (player.worldObj.rand.nextInt(6)+1) * (player.worldObj.rand.nextInt(6)+1) );
		else if (hammerType.equals(EnumHammerType.FEARNOANVIL) && player.worldObj.rand.nextGaussian() > 0.9)	//Just a suggestion, keep it if you like it.
			target.addPotionEffect(new PotionEffect(2,100,3));	//Would prefer it being triggered by a critical hit instead, if it can.
		return true;
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn)
	{
		if ((double)blockIn.getBlockHardness(worldIn, pos) != 0.0D)
			stack.damageItem(2, playerIn);
		
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}
	
	public int getMaxItemUseDuration(ItemStack itemStack)
	{
		return Integer.MAX_VALUE;
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if(worldIn.getBlockState(new BlockPos(pos)).getBlock() != Blocks.air)
		{
			if (hammerType.equals(EnumHammerType.POGO))
			{
				playerIn.motionY = Math.max(playerIn.motionY, Math.min(getPogoMotion(stack)*2, Math.abs(playerIn.motionY) + getPogoMotion(stack)));
				playerIn.fallDistance = 0;
				stack.damageItem(1, playerIn);
				return true;
			} 
		}
		return false;
	}
	
	protected double getPogoMotion(ItemStack stack)
	{
		return 0.5 + EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, stack)*0.1;
	}
	
}
