package okito122.elementalpets.proxy;

import assets.elementalpets.renders.RenderElementalAir;
import assets.elementalpets.renders.RenderElementalEarth;
import assets.elementalpets.renders.RenderElementalFire;
import assets.elementalpets.renders.RenderElementalWater;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import okito122.elementalpets.entities.EntityElementalAir;
import okito122.elementalpets.entities.EntityElementalBase;
import okito122.elementalpets.entities.EntityElementalEarth;
import okito122.elementalpets.entities.EntityElementalFire;
import okito122.elementalpets.entities.EntityElementalWater;
import okito122.elementalpets.init.ModItems;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void init(){
		ModItems.registerRenders();
	}
	
	@Override
	public void registerEntityRenders()
	{
		registerTextured(EntityElementalWater.class);
		registerTextured(EntityElementalFire.class);
		registerTextured(EntityElementalEarth.class);
		registerTextured(EntityElementalAir.class);
	}
	
	public static void registerTextured(final Class<? extends EntityElementalBase> elemental)
	{
		RenderingRegistry.registerEntityRenderingHandler(elemental, new IRenderFactory<EntityElementalBase>() 
		{
			@Override
			public Render<? super EntityElementalBase> createRenderFor(RenderManager manager) 
			{
				if(elemental.isAssignableFrom(EntityElementalWater.class))
					return new RenderElementalWater(manager);
				else if(elemental.isAssignableFrom(EntityElementalFire.class))
					return new RenderElementalFire(manager);
				else if(elemental.isAssignableFrom(EntityElementalEarth.class))
					return new RenderElementalEarth(manager);
				return new RenderElementalAir(manager);
			}
		});
	}

}
