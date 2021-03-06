package com.mraof.minestuck.item.weapon;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.mraof.minestuck.Minestuck;

public class ItemSickle extends ItemWeapon
{
	private int weaponDamage;
	private final EnumSickleType sickleType;
    public float efficiencyOnProperMaterial = 4.0F;
    
    public ItemSickle(EnumSickleType sickleType)
	{
		super();
		
		this.sickleType = sickleType;
		this.maxStackSize = 1;
		this.setMaxDamage(sickleType.getMaxUses());
		this.setCreativeTab(Minestuck.tabMinestuck);
		switch(sickleType)
		{
		case SICKLE:
			this.setUnlocalizedName("sickle");
			break;
		case HOMES:
			this.setUnlocalizedName("homesSmellYaLater");
			break;
		case REGISICKLE:
			this.setUnlocalizedName("regiSickle");
			break;
		case CLAW:
			this.setUnlocalizedName("clawSickle");
			break;
		}
		this.weaponDamage = 4 + sickleType.getDamageVsEntity();
	}
    
    @Override
	public int getAttackDamage() 
    {
		return weaponDamage;
	}
	
    @Override
	public int getItemEnchantability()
	{
		return this.sickleType.getEnchantability();
	}

    @Override
	public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase attacker)
	{
		itemStack.damageItem(1, attacker);
		return true;
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn)
	{
		if ((double)blockIn.getBlockHardness(worldIn, pos) != 0.0D)
		{
			stack.damageItem(2, playerIn);
		}
		
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}
	
}
