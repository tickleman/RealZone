package fr.crafter.tickleman.realzone.zonecore;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.CommandSender;

import fr.crafter.tickleman.realplugin.RealFileTools;
import fr.crafter.tickleman.realzone.Plugin;

public abstract class Zone
{

	private Map<String, String> flags;

	private boolean loaded = false;

	//----------------------------------------------------------------------------------------- getId
	public abstract String getId();

	//----------------------------------------------------------------------------------- getFilename
	private String getFilename()
	{
		return getFilepath() + "/" + getId() + ".region";		
	}

	//----------------------------------------------------------------------------------- getFilepath
	private String getFilepath()
	{
		return Plugin.plugin.getDataFolder().getPath() + "/" + getClass().getSimpleName();
	}

	//--------------------------------------------------------------------------------------- getFlag
	public String getFlag(String flagName)
	{
		if (!loaded) load();
		if (flags == null) return "";
		String flagValue = flags.get(flagName);
		if (flagValue == null) return "";
		return flagValue;
	}

	//------------------------------------------------------------------------------------------ load
	private void load()
	{
		loaded = true;
		String buffer = "";
		try {
			buffer = RealFileTools.getFileContents(getFilename());
		} catch (IOException e) {
		}
		System.out.println("buffer = " + buffer);
		for (String line : buffer.split("\n")) {
			System.out.println("- line = " + line);
			int i = line.indexOf('=');
			if (i > 0) {
				if (flags == null) flags = new HashMap<String, String>();
				flags.put(line.substring(0, i), line.substring(i + 1));
			}
		}
	}

	//------------------------------------------------------------------------------------------ save
	private void save()
	{
		if (flags != null) {
			RealFileTools.mkDir(getFilepath());
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(getFilename()));
				for (String flagName : flags.keySet()) {
					writer.write(flagName + "=" + flags.get(flagName) + "\n");
				}
				writer.close();
			} catch (IOException e) {
				Plugin.plugin.getLogger().severe(
					"Could not save region file " + getFilename() + " : " + e.getMessage()
				);
				e.printStackTrace();
			}
		}
	}

	//--------------------------------------------------------------------------------------- setFlag
	public void setFlag(CommandSender sender, String flagName, String flagValue)
	{
		System.out.println("setFlag(" + flagName + ", " + flagValue);
		if (flags == null) flags = new HashMap<String, String>();
		if (!loaded) load();
		flags.put(flagName, flagValue);
		save();
	}

	//--------------------------------------------------------------------------------- setNativeZone
	public abstract Zone setNativeZone(Object nativeZone);

}
