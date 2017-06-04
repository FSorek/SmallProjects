package okito122.elementalpets;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import okito122.elementalpets.init.ModEntities;
import okito122.elementalpets.init.ModItems;
import okito122.elementalpets.proxy.CommonProxy;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = "[1.10.2]")
public class ElementalPets {

	@SidedProxy(clientSide = "okito122." + Reference.MOD_ID + ".proxy.ClientProxy", serverSide = "okito122." + Reference.MOD_ID + ".proxy.CommonProxy")
	public static CommonProxy proxy;

	@Mod.Instance(Reference.MOD_ID)
	public static ElementalPets instance;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) 
	{	
		//Config.mainRegistry(new Configuration(event.getSuggestedConfigurationFile()));
		//GolemItems.mainRegistry();
		ModItems.init();
		ModItems.register();
		ModEntities.mainRegistry();
		proxy.registerEntityRenders();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) 
	{		
		proxy.init();
		//registerCrafting();
		//proxy.registerEvents();
		/*if(event.getSide() == Side.CLIENT)
		{
			proxy.registerBlockRenders();
			proxy.registerItemRenders();
		}*/
	}
}
