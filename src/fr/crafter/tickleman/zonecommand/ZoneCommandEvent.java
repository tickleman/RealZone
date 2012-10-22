package fr.crafter.tickleman.zonecommand;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.crafter.tickleman.realzone.event.ZoneEnterEvent;
import fr.crafter.tickleman.realzone.event.ZoneLeaveEvent;
import fr.crafter.tickleman.realzone.zonecore.Zone;

public class ZoneCommandEvent implements Listener
{

	//------------------------------------------------------------------------------- executeCommands
	private void executeCommands(Player player, Zone zone, String flag)
	{
		for (String command : zone.getFlag(flag).split("\n")) {
			if (!command.isEmpty()) {
				Bukkit.getServer().dispatchCommand(player, command);
			}
		}
	}

	//----------------------------------------------------------------------------------- onZoneEnter
	@EventHandler
	public void onZoneEnter(ZoneEnterEvent event)
	{
		if (event.getEntity() instanceof Player) {
			executeCommands((Player) event.getEntity(), event.getZone(), "enter-command");
			executeCommands((Player) event.getEntity(), event.getZone(), "enter-commands");
		}
	}

	//----------------------------------------------------------------------------------- onZoneLeave
	@EventHandler
	public void onZoneLeave(ZoneLeaveEvent event)
	{
		if (event.getEntity() instanceof Player) {
			executeCommands((Player) event.getEntity(), event.getZone(), "leave-command");
			executeCommands((Player) event.getEntity(), event.getZone(), "leave-commands");
		}
	}

}
