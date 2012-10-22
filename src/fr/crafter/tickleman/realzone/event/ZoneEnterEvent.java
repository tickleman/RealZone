package fr.crafter.tickleman.realzone.event;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

import fr.crafter.tickleman.realzone.zonecore.Zone;

public class ZoneEnterEvent extends ZoneEvent
{

	private static final HandlerList handlers = new HandlerList();

	//-------------------------------------------------------------------------------- ZoneEnterEvent
	public ZoneEnterEvent(Entity entity, Zone zone)
	{
		super(entity, zone);
	}

	//-------------------------------------------------------------------------------- getHandlerList
	public static HandlerList getHandlerList()
	{
		return handlers;
	}

	//----------------------------------------------------------------------------------- getHandlers
	public HandlerList getHandlers()
	{
		return handlers;
	}

}
