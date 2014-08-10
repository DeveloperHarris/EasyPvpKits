package com.gmail.codervortex.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.codervortex.Main;
import com.gmail.codervortex.Updater;

public class update implements CommandExecutor
{
	public Main plugin;
	public update(Main instance){	
		plugin = instance;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	  {
		Player player = (Player) sender; 
		 if (cmd.getName().equalsIgnoreCase("update"))
		    {
			 if(player.hasPermission("EasyPvpKits.Admin")){
				 if(plugin.getConfig().getBoolean("options.auto-update"))
	    		  {
			 if (Main.update)
			 		{
		      if ((sender instanceof Player))
		       			{  
		    	  player.sendMessage(ChatColor.GOLD + "Your plugin will now be updated. Please do /reload once it has downloaded to load it.");
		    	  @SuppressWarnings("unused")
				Updater updater = new Updater(Main.getPlugin(), 66838, Main.getPlugin().getPluginFile(), Updater.UpdateType.NO_VERSION_CHECK, true);
		    	  return true;  
		       			}
			 		}
			     }
				 else player.sendMessage(ChatColor.RED + "EasyPvpKits' Auto Update Feature is currently disabled. You can enable it in the config.");
				 return true;
		    }
			 else player.sendMessage(ChatColor.RED + "You do not have permission to excute this command.");
			 return true;
		   }
		return false; 
	  }
}
