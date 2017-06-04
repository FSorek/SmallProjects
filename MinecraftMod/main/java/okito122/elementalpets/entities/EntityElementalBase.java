package okito122.elementalpets.entities;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import okito122.elementalpets.Reference;

public abstract class EntityElementalBase extends EntityTameable  {
	protected ResourceLocation textureLoc;
	
	protected abstract void applyAttributes();
	protected abstract ResourceLocation applyTexture();
	
	public EntityElementalBase(World worldIn) {
		super(worldIn);
		InitializeAITasks();
		InitializeBasicFunctionality();
	}
	
	public EntityElementalBase(World worldIn, EntityPlayer playerIn) {
		super(worldIn);
		this.setOwnerId(playerIn.getUniqueID());
		this.setTamed(true);
		InitializeAITasks();
		InitializeBasicFunctionality();
	}

	
	public void InitializeBasicFunctionality()
	{
        this.setEntityInvulnerable(true);
        this.setSize(1f, 2f);
	}
	
	public void InitializeAITasks()
	{
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.setTextureType(this.applyTexture());
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.applyAttributes();
	}
	

	
	
	public static ResourceLocation makeElementalTexture(String texture)
	{
		return makeElementalTexture(Reference.MOD_ID, texture);
	}
	
	public static ResourceLocation makeElementalTexture(final String MODID, String suffix)
	{
		return new ResourceLocation(MODID + ":textures/entities/elemental_" + suffix + ".png");
	}
	
	public void setTextureType(ResourceLocation texturelocation)
	{
		this.textureLoc = texturelocation;
	}
	
	public ResourceLocation getTextureType()
	{
		return this.textureLoc;
	}
	
	@Override
	public void setTamed(boolean tamed)
    {
        super.setTamed(tamed);

        if (tamed)
        {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        }
    }
	
	 public void onLivingUpdate()
	 {
		 super.onLivingUpdate();
		 
	     if (!this.onGround && this.motionY < 0.0D)
	         this.motionY *= 0.6D;

		 if(this.getOwner()==null)
			 this.setHealth(0);
		 if(this.getOwner()!=null)
			 if(this.getOwner().isDead)
				 this.setHealth(0);
	 }
}
