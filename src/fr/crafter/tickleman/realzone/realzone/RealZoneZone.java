package fr.crafter.tickleman.realzone.realzone;

import fr.crafter.tickleman.realzone.zonecore.Zone;

public class RealZoneZone extends Zone
{

	private String name;

	//----------------------------------------------------------------------------------------- getId
	@Override
	public String getId()
	{
		return name;
	}

	//--------------------------------------------------------------------------------- setNativeZone
	@Override
	public Zone setNativeZone(Object nativeZone)
	{
		return this;
	}

}
