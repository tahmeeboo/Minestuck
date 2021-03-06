package com.mraof.minestuck.item.weapon;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.mraof.minestuck.Minestuck;

//I called it a spork because it includes both
public class ItemSpork extends ItemWeapon 
{
	private int weaponDamage;
	private final EnumSporkType sporkType;
	public float efficiencyOnProperMaterial = 4.0F;
	/**
	 * whether it's a spoon or a fork, unused for the crocker spork, as it depends on the meta.
	 */
	public boolean isSpoon;

	public ItemSpork(EnumSporkType sporkType) 
	{
		super();
		this.isSpoon = sporkType.getIsSpoon();
		this.sporkType = sporkType;
		this.maxStackSize = 1;
		this.setMaxDamage(sporkType.getMaxUses());
		this.setCreativeTab(Minestuck.tabMinestuck);
		switch(sporkType)
		{
		case CROCKER:
			this.setUnlocalizedName("crockerSpork");
			break;
		case SKAIA:
			this.setUnlocalizedName("skaiaFork");
			break;
		}
		this.weaponDamage = 2 + sporkType.getDamageVsEntity();
	}
	
	@Override
	public int getAttackDamage() 
	{
		return isSpoon ? weaponDamage : weaponDamage + 2;
	}
	
	@Override
	public int getItemEnchantability()
	{
		return this.sporkType.getEnchantability();
	}

	@Override
	public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase player)
	{
//		if (sporkType.equals(EnumSporkType.CROCKER) && !isSpoon(itemStack)){
//			target.hurtResistantTime = 0;	//A somewhat hackish way, but I find attributes too complicated, for now.
//			target.attackEntityFrom(DamageSource.causeMobDamage(player), 2F);
//		}
		
		itemStack.damageItem(isSpoon(itemStack) ? 1 : 2, player);
		return true;
	}

	public boolean isSpoon(ItemStack itemStack) {
		if (sporkType.equals(EnumSporkType.CROCKER))
			return !itemStack.hasTagCompound() ? true : itemStack.getTagCompound().getBoolean("isSpoon");
		else return isSpoon;
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
	
	public int getMaxItemUseDuration(ItemStack itemStack)
	{
		return 72000;
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) 
	{
		if(!world.isRemote)
			if (sporkType.equals(EnumSporkType.CROCKER)) {
				if(stack.getTagCompound().getByte("delay") > 0)
					return stack;
				else stack.getTagCompound().setByte("delay", (byte) 10);
				
				stack.getTagCompound().setBoolean("isSpoon", !stack.getTagCompound().getBoolean("isSpoon"));
				
				if(!stack.getTagCompound().hasKey("AttributeModifiers"))
					stack.getTagCompound().setTag("AttributeModifiers", new NBTTagList());
				NBTTagList list = stack.getTagCompound().getTagList("AttributeModifiers", 10);
				boolean found = false;
				for(int i = 0; i < list.tagCount(); i++) {
					NBTTagCompound nbt = (NBTTagCompound) list.getCompoundTagAt(i);
					if(nbt.getString("AttributeName").equals(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName())) {
						nbt.setDouble("Amount", isSpoon(stack)?3:5);
						found = true;
						break;
					}
				}
				
				if(!found) {
					NBTTagCompound nbt = new NBTTagCompound();
					nbt.setString("AttributeName", SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName());
					nbt.setLong("UUIDMost", itemModifierUUID.getMostSignificantBits());
					nbt.setLong("UUIDLeast", itemModifierUUID.getLeastSignificantBits());
					nbt.setString("Name", "Tool Modifier");
					nbt.setDouble("Amount", isSpoon(stack)?3:5);
					nbt.setInteger("Operation", 0);
					list.appendTag(nbt);
				}
			}
		return stack;
	}
	
	public void checkTagCompound(ItemStack stack)
	{
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		if(!stack.getTagCompound().hasKey("isSpoon"))
			stack.getTagCompound().setBoolean("isSpoon", true);
		if(!stack.getTagCompound().hasKey("delay"))
			stack.getTagCompound().setByte("delay", (byte) 0);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		if(this.sporkType != EnumSporkType.CROCKER)
			return getUnlocalizedName();
		else return "item."+(isSpoon(stack)?"crockerSpoon":"crockerFork");
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
	{
		checkTagCompound(stack);
		
		if(stack.getTagCompound().getByte("delay") > 0)
			stack.getTagCompound().setByte("delay", (byte) (stack.getTagCompound().getByte("delay")-1));
	}
	
}
