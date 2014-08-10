package com.gmail.codervortex.Kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gmail.codervortex.Main;

public class Pvp implements CommandExecutor
{
	public Main plugin;
	public Pvp(Main instance){	
		plugin = instance;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
	if (commandLabel.equalsIgnoreCase("Pvp") && sender instanceof Player) {
		if (player.hasPermission("EasyPvpKits.Pvp")) {
			if (!plugin.kits.contains(player.getName())) {
				
				player.sendMessage(ChatColor.GOLD + "You have been given the Pvp kit!");
				
				if(plugin.getConfig().getBoolean("options.sound-effects"))
				{
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 2.0F, 2.0F);
				}
				
				plugin.kits.add(player.getName());
				
				player.getInventory().clear();
				player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
				player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
				player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
				player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
				player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
				if(plugin.getConfig().getBoolean("options.soup-in-kits"))
				{
					for (int i = 0; i < 35; i++){
						player.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
					}
			    }
			}
			else { player.sendMessage(ChatColor.RED + "You have already picked a kit!");
			}
		} 
		else {
			player.sendMessage(ChatColor.RED + "You don't have permission to recieve this kit.");
		}
		return true;
	}
	return false; 
  }	
}
