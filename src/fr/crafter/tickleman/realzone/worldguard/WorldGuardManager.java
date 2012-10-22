package fr.crafter.tickleman.realzone.worldguard;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import fr.crafter.tickleman.realzone.zonecore.Cache;
import fr.crafter.tickleman.realzone.zonecore.ZonesManager;
import fr.crafter.tickleman.realzone.zonecore.Zone;
import fr.crafter.tickleman.realzone.zonecore.Zones;

public class WorldGuardManager implements ZonesManager
{

	private static Map<String, Flag<?>> flagNames;

	private static WorldGuardPlugin worldGuard;

	//--------------------------------------------------------------------------- RealZonesWorldGuard
	public WorldGuardManager()
	{
		getFlagsNames();
	}

	//--------------------------------------------------------------------------------------- getFlag
	public static Flag<?> getFlag(String flagName)
	{
		return flagNames.get(flagName);
	}

	//--------------------------------------------------------------------------------- getFlagsNames
	@Override
	public Set<String> getFlagsNames()
	{
		if (flagNames == null) {
			flagNames = new HashMap<String, Flag<?>>();
			for (Flag<?> flag : DefaultFlag.flagsList) {
				flagNames.put(flag.getName(), flag);
			}
		}
		return flagNames.keySet();
	}

	//--------------------------------------------------------------------------------------- getZone
	@Override
	public Zone getZone(String id)
	{
		ProtectedRegion region = null;
		for (World world : Bukkit.getServer().getWorlds()) {
			region = worldGuard.getGlobalRegionManager().get(world).getRegion(id);
			if (region != null) break;
		}
		return Cache.get(region, WorldGuardZone.class);
	}

	//-------------------------------------------------------------------------------- getPlayerZones
	@Override
	public Zones getZones(Location location)
	{
		Zones zones = new Zones();
		if (location != null) {
			ApplicableRegionSet regionSet = worldGuard.getGlobalRegionManager().get(location.getWorld())
				.getApplicableRegions(location);
			if (regionSet != null) {
				for (ProtectedRegion region : regionSet) {
					zones.add(Cache.get(region, WorldGuardZone.class));
				}
			}
		}
		return zones;
	}

	//------------------------------------------------------------------------------------- getPlugin
	public static WorldGuardPlugin getPlugin()
	{
		return worldGuard;
	}

	//------------------------------------------------------------------------------------- setPlugin
	@Override
	public ZonesManager setPlugin(Plugin plugin)
	{
		worldGuard = (WorldGuardPlugin) plugin;
		return this;
	}

}
