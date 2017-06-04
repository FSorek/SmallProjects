package assets.elementalpets.renders;

import assets.elementalpets.models.entities.ModelElementalWater;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import okito122.elementalpets.entities.EntityElementalBase;

public class RenderElementalWater extends RenderLiving<EntityElementalBase> {

	public RenderElementalWater(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelElementalWater(), 0.5f);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityElementalBase elemental) {
		// TODO Auto-generated method stub
		return elemental.getTextureType();
	}

}
