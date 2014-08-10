package com.gmail.codervortex.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.codervortex.Main;

public class Soup implements CommandExecutor 
{
	public Main plugin;
	public Soup(Main instance)
	{	
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	  {
		 if (cmd.getName().equalsIgnoreCase("soup"))
		    {
			 if (sender instanceof Player)
		      {
				 final Player p = (Player) sender;
				 if(p.hasPermission("EasyPvpKits.SoupCommand"))
				{	 
				 if(plugin.SoupCooldown.contains(p.getName()))
				 {
					 p.sendMessage(ChatColor.RED + "You must wait to use /soup again.");
				 }
				 if(!plugin.SoupCooldown.contains(p.getName()))
				 {
					 plugin.SoupCooldown.add(p.getName());
					 
					 new BukkitRunnable()
					 {
							public void run()
							{
							 plugin.SoupCooldown.remove(p.getName());
							}
						}.runTaskLater(plugin, plugin.getConfig().getInt("options.soup-command-cooldown")*20); 
					  
						
						for (int i = 0; i < p.getInventory().getSize(); i++)
						{
							
							if (p.getInventory().getItem(i) == null)
							{
								p.getInventory().setItem(i, new ItemStack(Material.MUSHROOM_SOUP, 1));
								
							} else if (p.getInventory().getItem(i).getType().equals(Material.BOWL))
							{	
								p.getInventory().setItem(i, new ItemStack(Material.MUSHROOM_SOUP, 1));	
							}
						}
						p.sendMessage(ChatColor.GOLD + "You inventory has been refilled with soup.");
				    }
				 return true;
				   }
				 else p.sendMessage(ChatColor.RED + "You do not have permission to excute this command.");
		      }
		    }
		return false;
	  }
}