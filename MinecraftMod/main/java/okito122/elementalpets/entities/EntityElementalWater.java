package okito122.elementalpets.entities;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityElementalWater extends EntityElementalBase {
	public EntityElementalWater(World worldIn) {
		super(worldIn);
	}
	public EntityElementalWater(World worldIn, EntityPlayer playerIn) {
		super(worldIn, playerIn);
	}

	@Override
	protected void applyAttributes() {
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
	}

	@Override
	protected ResourceLocation applyTexture() {
		return this.makeElementalTexture("water");
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}
}
