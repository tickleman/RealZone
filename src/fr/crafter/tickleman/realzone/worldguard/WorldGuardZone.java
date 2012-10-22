package fr.crafter.tickleman.realzone.worldguard;

import org.bukkit.command.CommandSender;

import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.InvalidFlagFormat;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import fr.crafter.tickleman.realzone.zonecore.Zone;

public class WorldGuardZone extends Zone
{

	private ProtectedRegion region;

	//-------------------------------------------------------------------------------- WorldGuardZone
	public WorldGuardZone()
	{
	}

	//--------------------------------------------------------------------------------------- getFlag
	@Override
	public boolean equals(Object zone)
	{
		return (zone instanceof WorldGuardZone) && region.equals(((WorldGuardZone) zone).region);
	}

	//--------------------------------------------------------------------------------------- getFlag
	@Override
	public String getFlag(String flagName)
	{
		Flag<?> flag = WorldGuardManager.getFlag(flagName);
		return (flag == null) ? super.getFlag(flagName) : region.getFlag(flag).toString();
	}

	//--------------------------------------------------------------------------------------- getName
	@Override
	public String getId()
	{
		return region.getId();
	}

	//-------------------------------------------------------------------------------------- hashCode
	@Override
	public int hashCode()
	{
		return region.hashCode();
	}

	//--------------------------------------------------------------------------------------- setFlag
	@Override
	public void setFlag(CommandSender sender, String flagName, String flagValue)
	{
		setWGFlag(sender, flagName, flagValue);
	}

	//--------------------------------------------------------------------------------- setNativeZone
	@Override
	public Zone setNativeZone(Object nativeZone)
	{
		region = (ProtectedRegion) nativeZone;
		return this;
	}

	//------------------------------------------------------------------------------------- setWGFlag
	private <V> void setWGFlag(CommandSender sender, String flagName, String flagValue)
	{
		@SuppressWarnings("unchecked")
		Flag<V> flag = (Flag<V>) WorldGuardManager.getFlag(flagName);
		if (flag == null) {
			super.setFlag(sender, flagName, flagValue);
		}
		else {
			try {
				region.setFlag(flag, flag.parseInput(WorldGuardManager.getPlugin(), sender, flagValue));
			} catch (InvalidFlagFormat e) {
				sender.sendMessage("Invalid value for flag");
			}
		}
	}

}
