package fr.crafter.tickleman.realzone;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import fr.crafter.tickleman.realzone.event.ZoneEnterEvent;
import fr.crafter.tickleman.realzone.event.ZoneLeaveEvent;
import fr.crafter.tickleman.realzone.worldguard.WorldGuardManager;
import fr.crafter.tickleman.realzone.zonecore.ZonesManager;
import fr.crafter.tickleman.realzone.zonecore.Zone;
import fr.crafter.tickleman.realzone.zonecore.Zones;

public abstract class RealZone
{

	private static Map<Class<? extends Plugin>, ZonesManager> zonesManagers = null;

	private static Map<Entity, Zones> entityZones = new HashMap<Entity, Zones>();

	//------------------------------------------------------------------------------------ callEvents
	public static boolean callEvents(Entity entity, Location location)
	{
		boolean cancelEvent = false;
		Zones oldZones = entityZones.get(entity);
		for (ZonesManager zoneManager : zonesManagers.values()) {
			boolean change = false;
			Zones newZones = zoneManager.getZones(location);
			if (oldZones != null) for (Zone oldZone : oldZones) {
				if (!newZones.contains(oldZone)) {
					change = true;
					ZoneLeaveEvent zoneLeaveEvent = new ZoneLeaveEvent(entity, oldZone);
					Bukkit.getServer().getPluginManager().callEvent(zoneLeaveEvent);
					cancelEvent = cancelEvent || zoneLeaveEvent.isCancelled();
				}
			}
			for (Zone newZone : newZones) {
				if ((oldZones == null) || !oldZones.contains(newZone)) {
					change = true;
					ZoneEnterEvent zoneEnterEvent = new ZoneEnterEvent(entity, newZone);
					Bukkit.getServer().getPluginManager().callEvent(zoneEnterEvent);
					cancelEvent = cancelEvent || zoneEnterEvent.isCancelled();
				}
			}
			if (change) {
				entityZones.put(entity, newZones);
			}
		}
		return cancelEvent;
	}

	//--------------------------------------------------------------------------------------- getZone
	public static Zone getZone(String id)
	{
		Zone zone = null;
		for (ZonesManager zoneManager : zonesManagers.values()) {
			zone = zoneManager.getZone(id);
			if (zone != null) break;
		}
		return zone;
	}

	//-------------------------------------------------------------------------------------- getZones
	public static Zones getZones(Player player)
	{
		return entityZones.get(player);
	}

	//---------------------------------------------------------------------------- registerZonePlugin
	public static void registerZonePlugin(
		Plugin plugin, String pluginName, Class<? extends ZonesManager> managerClass
	) {
		if (zonesManagers == null) {
			registerZonePlugins(plugin);
		}
		Plugin zonePlugin = Bukkit.getServer().getPluginManager().getPlugin(pluginName);
		if (zonePlugin != null) {
			try {
				zonesManagers.put(zonePlugin.getClass(), managerClass.newInstance().setPlugin(zonePlugin));
			} catch (InstantiationException e) {
				plugin.getLogger().log(Level.SEVERE, "Could not instanciate " + managerClass.getName());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				plugin.getLogger().log(Level.SEVERE, "Illegal access to " + managerClass.getName());
				e.printStackTrace();
			}
		}
	}

	//--------------------------------------------------------------------------- registerZonePlugins
	public static void registerZonePlugins(Plugin plugin)
	{
		if (zonesManagers == null) {
			Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), plugin);
			zonesManagers = new HashMap<Class<? extends Plugin>, ZonesManager>();
		}
		registerZonePlugin(plugin, "WorldGuard", WorldGuardManager.class);
	}

	//------------------------------------------------------------------------------ getZonesManagers
	public static Map<Class<? extends Plugin>, ZonesManager> getZonesManagers()
	{
		return zonesManagers;
	}

}
