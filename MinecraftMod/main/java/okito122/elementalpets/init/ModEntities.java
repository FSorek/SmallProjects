package okito122.elementalpets.init;

import net.minecraft.entity.EntityCreature;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import okito122.elementalpets.ElementalPets;
import okito122.elementalpets.entities.EntityElementalAir;
import okito122.elementalpets.entities.EntityElementalEarth;
import okito122.elementalpets.entities.EntityElementalFire;
import okito122.elementalpets.entities.EntityElementalWater;

public class ModEntities {
	private static int elementalEntityCount = 0;

	public static void mainRegistry() 
	{
		registerEntities();
	}
	
	public static void registerEntities()
	{
		register(EntityElementalWater.class, "elemental_water");
		register(EntityElementalFire.class, "elemental_fire");
		register(EntityElementalEarth.class, "elemental_earth");
		register(EntityElementalAir.class, "elemental_air");
	}
	
	private static void register(Class<? extends EntityCreature> entityClass, String name)
	{
		EntityRegistry.registerModEntity(entityClass, name, ++elementalEntityCount, ElementalPets.instance, 48, 3, true);
	}
}
