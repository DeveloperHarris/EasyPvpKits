package com.gmail.codervortex.Kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.gmail.codervortex.Main;

public class Pryo implements CommandExecutor
{
	public Main plugin;
	public Pryo(Main instance){	
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
	if(commandLabel.equalsIgnoreCase("Pyro") && sender instanceof Player){
		if (player.hasPermission("EasyPvpKits.Pyro")){
			if (!plugin.kits.contains(player.getName())){
				
				ItemStack Arrow = new ItemStack(Material.ARROW, 64);
				ItemStack SSword = new ItemStack(Material.STONE_SWORD);
				SSword.addEnchantment(Enchantment.FIRE_ASPECT, 1);
				ItemStack FBow = new ItemStack(Material.BOW);
				FBow.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 2);
				
				if(plugin.getConfig().getBoolean("options.sound-effects"))
				{
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 2.0F, 2.0F);
				}
				
				player.sendMessage(ChatColor.GOLD + "You have been given the Pyro kit!");
				
				plugin.kits.add(player.getName());
				
				player.getInventory().clear();	    
				
				player.getInventory().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
				player.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
				player.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
				player.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
				player.getInventory().addItem(SSword);
				player.getInventory().addItem(FBow);
				if(plugin.getConfig().getBoolean("options.soup-in-kits"))
				{
					for (int i = 0; i < 33; i++){
						player.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
					}
			    }
				player.getInventory().addItem(Arrow);					  
			}
			else player.sendMessage(ChatColor.RED + "You have already picked a kit!");

		}
		else player.sendMessage(ChatColor.RED + "You don't have permission to recieve this kit.");
		return true;
	}
	return false;
}
	
	
	
	
	
	
	
}
