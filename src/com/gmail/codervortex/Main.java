package com.gmail.codervortex;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.codervortex.Updater.ReleaseType;


public class Main extends JavaPlugin implements Listener
{
	 
	private static Main plugin;
	private Events events = new Events(this);
	private com.gmail.codervortex.GuiListener GUI = new GuiListener(this);
    private com.gmail.codervortex.Updater updater;
    
	public ArrayList<String> kits = new ArrayList<String>();
	public ArrayList<String> AssassinCooldown = new ArrayList<String>();
	public ArrayList<String> Assassin = new ArrayList<String>();
	
	public Map<String, Integer> killstreaks = new HashMap<String, Integer>();
	
	public static boolean update = false;
	public static String name = "";
	public static ReleaseType type = null;
	public static String version = "";
	public static String link = "";
	
	
	@Override
	public void onEnable()
	{
		plugin = this;
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("Easy Pvp Kits v.1.1.3 is now Enabled.");
		getCommands();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(events, this);
		pm.registerEvents(GUI, this);
		
		getConfig().options().copyDefaults(true);
		saveConfig();
	    if(plugin.getConfig().getBoolean("options.auto-update"))
	    {
		  updater = new Updater(this, 66838, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false); // Start Updater but just do a version check
		  update = updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE; // Determine if there is an update ready for us
		  name = updater.getLatestName(); // Get the latest name
		  version = updater.getLatestGameVersion(); // Get the latest game version
		  type = updater.getLatestType(); // Get the latest file's type
	      link = updater.getLatestFileLink(); // Get the latest link
	    }
	    else getLogger().info("EasyPvpKits's Auto Update Function is disabled. You can activate it in the config.");
	    
	    try {
	        Metrics metrics = new Metrics(this);
	        metrics.start();
	    } catch (IOException e) {
	        // Failed to submit the stats :-(
	    	getLogger().info("Metrics failed to connect to the database.");
	    }
	}
	
	@Override
	public void onDisable()
	{
		getLogger().info("Easy Pvp Kits is now Disabled. - Made by Vortex");
	}
	
	
	public static Main getPlugin()
	{
		return plugin;
	}
	
	public File getPluginFile()
	{
	    return this.getFile();
	}
	
	public void getCommands()
	{
		getCommand("Kits").setExecutor(new com.gmail.codervortex.Commands.kits(this));
		getCommand("Reset").setExecutor(new com.gmail.codervortex.Commands.Reset(this));
		getCommand("Archer").setExecutor(new com.gmail.codervortex.Kits.Archer(this));
		getCommand("Assassin").setExecutor(new com.gmail.codervortex.Kits.Assassin(this));
		getCommand("Heavy").setExecutor(new com.gmail.codervortex.Kits.Heavy(this));
		getCommand("Pyro").setExecutor(new com.gmail.codervortex.Kits.Pryo(this));
		getCommand("Pvp").setExecutor(new com.gmail.codervortex.Kits.Pvp(this));
		getCommand("Update").setExecutor(new com.gmail.codervortex.Commands.update(this));
	}
}
