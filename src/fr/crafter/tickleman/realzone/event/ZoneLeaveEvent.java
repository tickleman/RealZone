package fr.crafter.tickleman.realzone.event;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

import fr.crafter.tickleman.realzone.zonecore.Zone;

public class ZoneLeaveEvent extends ZoneEvent
{

	private static final HandlerList handlers = new HandlerList();

	//-------------------------------------------------------------------------------- getHandlerList
	public static HandlerList getHandlerList()
	{
		return handlers;
	}

	//-------------------------------------------------------------------------------- ZoneLeaveEvent
	public ZoneLeaveEvent(Entity entity, Zone zone)
	{
		super(entity, zone);
	}

	//----------------------------------------------------------------------------------- getHandlers
	public HandlerList getHandlers()
	{
		return handlers;
	}

}
