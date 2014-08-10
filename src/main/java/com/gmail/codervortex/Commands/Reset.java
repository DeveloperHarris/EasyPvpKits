package com.gmail.codervortex.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import com.gmail.codervortex.Main;

public class Reset implements CommandExecutor
{
	public Main plugin;
	public Reset(Main instance){	
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
	if (commandLabel.equalsIgnoreCase("Reset") && sender instanceof Player){
		if (player.hasPermission("EasyPvpKits.Admin")){
			if (args.length == 0){
				player.sendMessage(ChatColor.RED + "Do /reset (Playername) to reset a player's kit.");
			}
			else if (args.length == 1){
				@SuppressWarnings("deprecation")
				Player target = player.getServer().getPlayer(args[0]);
				if(!(target == null))
				{
				if (plugin.kits.contains(target.getName()))
				{
					
					plugin.kits.remove(target.getName());
					plugin.Assassin.remove(target.getName());
					
					target.getInventory().clear();
					target.getInventory().setArmorContents(null);
					
					for (PotionEffect effect : target.getActivePotionEffects())
						target.removePotionEffect(effect.getType());
					target.sendMessage(ChatColor.RED + "Your kit has been reset by a staff member!");
					player.sendMessage(ChatColor.GOLD + "The player's kit has been removed!");
				}
				else player.sendMessage(ChatColor.RED + "This person has not chosen a kit!");
				}
				else player.sendMessage(ChatColor.RED + "This person is not currently on the server.");
			}
			else if (args.length >= 1){
				player.sendMessage(ChatColor.RED + "You have input too many args. Please only do /reset <Playername>.");
			}
		}
		else player.sendMessage(ChatColor.RED + "You do not have permission to excute this command.");
		return true;
	}
	return false;
}
	
	
	
}
