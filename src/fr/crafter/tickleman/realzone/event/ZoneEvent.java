package fr.crafter.tickleman.realzone.event;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.entity.EntityEvent;

import fr.crafter.tickleman.realzone.zonecore.Zone;

public abstract class ZoneEvent extends EntityEvent implements Cancellable
{

	private boolean cancelled = false;

	private Zone zone;

	//------------------------------------------------------------------------------------- ZoneEvent
	public ZoneEvent(Entity entity, Zone zone)
	{
		super(entity);
		this.zone = zone;
	}

	//--------------------------------------------------------------------------------------- getZone
	public Zone getZone()
	{
		return zone;
	}

	//----------------------------------------------------------------------------------- isCancelled
	@Override
	public boolean isCancelled()
	{
		return cancelled;
	}

	//---------------------------------------------------------------------------------- setCancelled
	@Override
	public void setCancelled(boolean cancelled)
	{
		this.cancelled = cancelled;
	}

}
