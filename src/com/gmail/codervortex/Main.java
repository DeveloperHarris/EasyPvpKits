package com.gmail.codervortex;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.codervortex.Updater.ReleaseType;


public class Main extends JavaPlugin implements Listener
{
	
	 File configFile;
	 FileConfiguration config;
	 
	private static Main plugin;
	private Events events = new Events(this);
	private com.gmail.codervortex.GuiListener GUI = new GuiListener(this);
    private com.gmail.codervortex.Updater updater;
	public ArrayList<String> kits = new ArrayList<String>();
	public ArrayList<String> AssassinCooldown = new ArrayList<String>();
	public ArrayList<String> Assassin = new ArrayList<String>();
	
	
	public static boolean update = false;
	public static String name = "";
	public static ReleaseType type = null;
	public static String version = "";
	public static String link = "";

	
	
	@Override
	public void onEnable() {
		plugin = this;
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("Easy Pvp Kits v.1.1.2 is now Enabled.");
		getCommands();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(events, this);
		pm.registerEvents(GUI, this);
		try {
	        firstRun();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		loadYamls();
		
	    if(plugin.getConfig().getBoolean("options.auto-update")){
		  updater = new Updater(this, 66838, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false); // Start Updater but just do a version check
		  update = updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE; // Determine if there is an update ready for us
		  name = updater.getLatestName(); // Get the latest name
		  version = updater.getLatestGameVersion(); // Get the latest game version
		  type = updater.getLatestType(); // Get the latest file's type
	      link = updater.getLatestFileLink(); // Get the latest link
	    }
	    else getLogger().info("EasyPvpKits's Auto Update Function is disabled. You can reactivate it in the config.");
	}
	
	@Override
	public void onDisable(){
		getLogger().info("Easy Pvp Kits is now Disabled. - Made by Vortex");
		saveYamls();
	}
	
	public void saveYamls() {
	    try {
	        config.save(configFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	public void loadYamls() {
	    try {
	        config.load(configFile);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	private void firstRun() throws Exception {
		 if(!configFile.exists()){
			    configFile = new File(getDataFolder(), "config.yml");
				config = new YamlConfiguration();
		        configFile.getParentFile().mkdirs();
		        copy(getResource("config.yml"), configFile);
		    }
	}
	private void copy(InputStream in, File file) {
	    try {
	        OutputStream out = new FileOutputStream(file);
	        byte[] buf = new byte[1024];
	        int len;
	        while((len=in.read(buf))>0){
	            out.write(buf,0,len);
	        }
	        out.close();
	        in.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	public static Main getPlugin() {
		return plugin;
	}
	
	public File getPluginFile() {
		  return this.getFile();
		 }
	
	
	public void getCommands(){
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
