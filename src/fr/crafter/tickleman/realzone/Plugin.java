package fr.crafter.tickleman.realzone;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.crafter.tickleman.realplugin.RealPlugin;
import fr.crafter.tickleman.realzone.zonecore.Zone;

import fr.crafter.tickleman.zonecommand.ZoneCommandEvent;

public class Plugin extends RealPlugin
{

	public static Plugin plugin;

	//-------------------------------------------------------------------------------- RealZonePlugin
	public Plugin()
	{
		super();
		plugin = this;
	}

	//--------------------------------------------------------------------------------- getRealConfig
	@Override
	public Config getRealConfig()
	{
		return (Config) super.getRealConfig();
	}

	//------------------------------------------------------------------------------------ loadConfig
	@Override
	protected void loadConfig()
	{
		config = new Config(this).load();
	}

	//------------------------------------------------------------------------------------- onCommand
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		String cmd = command.getName();
		String p1 = args.length > 0 ? args[0] : "";
		String p2 = args.length > 1 ? args[1] : "";
		String p3 = args.length > 2 ? args[2] : "";
		if (cmd.equals("realzone")) {
			if ((sender instanceof Player) && hasPermission((Player) sender, "realzone." + p1)) {
				Player player = (Player) sender;
				if (p1.equals("list")) {
					player.sendMessage(RealZone.getZones(player).idsToString().replace(",", ", "));
				}
				else if (p1.equals("flag")) {
					if (!p2.isEmpty()) {
						if (!p3.isEmpty()) {
							String zoneId = p2;
							String flagName = p3;
							Zone zone = RealZone.getZone(zoneId);
							if (zone == null) {
								player.sendMessage("Region does not exist " + zoneId);
							}
							StringBuilder flagValue = new StringBuilder();
							boolean first = true;
							for (int i = 3; i < args.length; i++) {
								if (first) first = false; else flagValue.append(" ");
								flagValue.append(args[i]);
							}
							zone.setFlag(sender, flagName, flagValue.toString());
						}
						else {
							String flagName = p2;
							for (Zone zone : RealZone.getZones(player)) {
								player.sendMessage(zone.getId() + " : " + flagName + " = " + zone.getFlag(flagName));
							}
						}
					} else {
						player.sendMessage("/realzone flag <flagName>");
						player.sendMessage("/realzone flag <zoneId> <flagName> [<flagValue>]");
					}
				}
				else {
					player.sendMessage("/realzone flag <...>");
					player.sendMessage("/realzone list");
				}
			}
			return true;
		}
		return false;
	}

	//-------------------------------------------------------------------------------------- onEnable
	@Override
	public void onEnable()
	{
		super.onEnable();
		RealZone.registerZonePlugins(this);
		if (getRealConfig().commands) {
			getServer().getPluginManager().registerEvents(new ZoneCommandEvent(), this);
		}
	}

}
