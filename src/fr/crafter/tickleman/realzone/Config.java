package fr.crafter.tickleman.realzone;

import fr.crafter.tickleman.realplugin.RealConfig;

public class Config extends RealConfig
{

	/** Default configuration values (if not in file) */
	public boolean commands = true;

	//-------------------------------------------------------------------------------- RealZoneConfig
	public Config(final Plugin plugin)
	{
		super(plugin);
	}

	//------------------------------------------------------------------------------------- getPlugin
	@Override
	protected Plugin getPlugin()
	{
		return (Plugin) super.getPlugin();
	}

}
