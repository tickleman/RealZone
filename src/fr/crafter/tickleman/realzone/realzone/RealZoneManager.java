package fr.crafter.tickleman.realzone.realzone;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import fr.crafter.tickleman.realzone.zonecore.ZonesManager;
import fr.crafter.tickleman.realzone.zonecore.Zone;
import fr.crafter.tickleman.realzone.zonecore.Zones;

public class RealZoneManager implements ZonesManager
{

	//--------------------------------------------------------------------------------- getFlagsNames
	@Override
	public Set<String> getFlagsNames()
	{
		return new HashSet<String>();
	}

	//--------------------------------------------------------------------------------------- getZone
	@Override
	public Zone getZone(String id)
	{
		return null;
	}

	//-------------------------------------------------------------------------------------- getZones
	@Override
	public Zones getZones(Location location)
	{
		return new Zones();
	}

	//------------------------------------------------------------------------------------- setPlugin
	@Override
	public ZonesManager setPlugin(Plugin plugin)
	{
		return this;
	}

}
