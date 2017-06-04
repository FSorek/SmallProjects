package okito122.elementalpets.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import okito122.elementalpets.entities.EntityElementalAir;
import okito122.elementalpets.entities.EntityElementalBase;
import okito122.elementalpets.entities.EntityElementalEarth;
import okito122.elementalpets.entities.EntityElementalFire;
import okito122.elementalpets.entities.EntityElementalWater;

public class ItemSummoningStoneBase extends Item {
	protected boolean isActivated = false;
	protected EntityElementalBase activeElemental = null;
	
	public ItemSummoningStoneBase() {
		super();
		this.setCreativeTab(CreativeTabs.MISC);
		this.maxStackSize = 1;
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_EGG_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        if(!worldIn.isRemote){
        if (!this.isActivated)
	        {
        	if(this.getClass().isAssignableFrom(ItemSummoningStoneAqua.class))
        		activeElemental = new EntityElementalWater(worldIn, playerIn);
        	else if(this.getClass().isAssignableFrom(ItemSummoningStoneIgnis.class))
        		activeElemental = new EntityElementalFire(worldIn, playerIn);
        	else if(this.getClass().isAssignableFrom(ItemSummoningStoneTerra.class))
        		activeElemental = new EntityElementalEarth(worldIn, playerIn);
        	else
        		activeElemental = new EntityElementalAir(worldIn, playerIn);
        	activeElemental.setPosition(playerIn.posX, playerIn.posY, playerIn.posZ);
	            worldIn.spawnEntityInWorld(activeElemental);
	            this.isActivated = true;
	        }else{
	        	activeElemental.setHealth(0);
	        	this.isActivated = false;
	        }
        }
        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
    }
}
