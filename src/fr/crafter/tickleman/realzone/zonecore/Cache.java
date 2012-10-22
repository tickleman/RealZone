package fr.crafter.tickleman.realzone.zonecore;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;

public class Cache
{

	private static Map<Object, Zone> zones = new HashMap<Object, Zone>();

	//------------------------------------------------------------------------------------------- get
	public static <T extends Zone> T get(Object nativeZone, Class<T> zoneClass)
	{
		@SuppressWarnings("unchecked")
		T zone = (T) zones.get(nativeZone);
		if (zone == null) {
			try {
				@SuppressWarnings("unchecked")
				T zone2 = (T) zoneClass.newInstance().setNativeZone(nativeZone);
				zone = zone2;
				zones.put(zoneClass, zone);
			} catch (InstantiationException e) {
				Bukkit.getLogger().severe("[RealZone] instantiation exception " + zoneClass.getClass());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				Bukkit.getLogger().severe("[RealZone] illegal access exception " + zoneClass.getClass());
				e.printStackTrace();
			}
		}
		return zone;
	}

}
