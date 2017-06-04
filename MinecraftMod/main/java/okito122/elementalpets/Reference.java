package okito122.elementalpets;

public class Reference {

	public static final String MOD_ID = "elementalpets";
	public static final String NAME = "Elemental Pets";
	public static final String VERSION = "0.1a";
	
	public static final String CLIENT_PROXY_CLASS = "okito122.elementalpets.proxy.ClientProxy";
	
	
	public static enum ElementalPetsItems{
		SUMMONINGSTONEAQUA("summoningstoneaqua", "ItemSummoningStoneAqua"),
		SUMMONINGSTONEIGNIS("summoningstoneignis", "ItemSummoningStoneIgnis"),
		SUMMONINGSTONETERRA("summoningstoneterra", "ItemSummoningStoneTerra"),
		SUMMONINGSTONEAER("summoningstoneaer", "ItemSummoningStoneAer");
		
		
		private String unlocalizedName;
		private String registryName;
		ElementalPetsItems(String _unlocalizedName, String _registryName)
		{
			this.unlocalizedName = _unlocalizedName;
			this.registryName = _registryName;
		}
		
		public String getUnlocalizedName() {
			return unlocalizedName;
		}
		
		public String getRegistryName() {
			return registryName;
		}
	}
	
}
