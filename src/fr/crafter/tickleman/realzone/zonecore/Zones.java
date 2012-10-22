package fr.crafter.tickleman.realzone.zonecore;

import java.util.HashSet;


public class Zones extends HashSet<Zone>
{

	private static final long serialVersionUID = 3306200736623613452L;

	//--------------------------------------------------------------------------------------- getList
	public String idsToString()
	{
		StringBuilder str = new StringBuilder();
		boolean first = true;
		for (Zone zone : this) {
			if (first) first = false;
			else str.append(',');
			str.append(zone.getId());
		}
		return str.toString();
	}

}
