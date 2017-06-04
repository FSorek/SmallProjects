package okito122.elementalpets.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import okito122.elementalpets.Reference;
import okito122.elementalpets.items.ItemSummoningStoneAer;
import okito122.elementalpets.items.ItemSummoningStoneAqua;
import okito122.elementalpets.items.ItemSummoningStoneIgnis;
import okito122.elementalpets.items.ItemSummoningStoneTerra;

public class ModItems {

	public static Item summoningstoneaqua;
	public static Item summoningstoneignis;
	public static Item summoningstoneterra;
	public static Item summoningstoneaer;
	
	public static void init()
	{
		summoningstoneaqua = new ItemSummoningStoneAqua();
		summoningstoneignis = new ItemSummoningStoneIgnis();
		summoningstoneterra = new ItemSummoningStoneTerra();
		summoningstoneaer = new ItemSummoningStoneAer();
	}
	
	public static void register()
	{
		GameRegistry.register(summoningstoneaqua);
		GameRegistry.register(summoningstoneignis);
		GameRegistry.register(summoningstoneterra);
		GameRegistry.register(summoningstoneaer);
	}
	
	public static void registerRenders()
	{
		registerRender(summoningstoneaqua);
		registerRender(summoningstoneignis);
		registerRender(summoningstoneterra);
		registerRender(summoningstoneaer);
	}
	
	public static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
