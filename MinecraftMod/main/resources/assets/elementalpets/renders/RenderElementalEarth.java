package assets.elementalpets.renders;

import assets.elementalpets.models.entities.ModelElementalEarth;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import okito122.elementalpets.entities.EntityElementalBase;

public class RenderElementalEarth extends RenderLiving<EntityElementalBase> {

	public RenderElementalEarth(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelElementalEarth(), 0.5f);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityElementalBase elemental) {
		// TODO Auto-generated method stub
		return elemental.getTextureType();
	}

}
