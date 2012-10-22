package fr.crafter.tickleman.realzone.zonecore;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.plugin.Plugin;


public interface ZonesManager
{

	public Set<String> getFlagsNames();

	public Zones getZones(Location location);

	public ZonesManager setPlugin(Plugin plugin);

	public Zone getZone(String id);

}
