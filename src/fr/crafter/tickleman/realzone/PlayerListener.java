package fr.crafter.tickleman.realzone;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerListener implements Listener
{

	//---------------------------------------------------------------------------------- onPlayerMove
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event)
	{
		Location from = event.getFrom();
		Location to   = event.getTo();
		if (
			from.getBlockX() != to.getBlockX()
			|| from.getBlockY() != to.getBlockY()
			|| from.getBlockZ() != to.getBlockZ()
		) {
			event.setCancelled(RealZone.callEvents(event.getPlayer(), event.getTo()));
		}
	}

	//---------------------------------------------------------------------------------- onPlayerQuit
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		RealZone.callEvents(event.getPlayer(), null);
	}

	//------------------------------------------------------------------------------- onPlayerRespawn
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event)
	{
		RealZone.callEvents(event.getPlayer(), event.getRespawnLocation());
	}

	//------------------------------------------------------------------------------ onPlayerTeleport
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event)
	{
		event.setCancelled(RealZone.callEvents(event.getPlayer(), event.getTo()));
	}

}
