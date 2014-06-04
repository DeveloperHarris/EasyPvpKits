package com.gmail.codervortex.Kits;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import com.gmail.codervortex.Main;

public class Assassin implements CommandExecutor
{
	public Main plugin;
	public Assassin(Main instance){	
		plugin = instance;
	}

	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
	if (commandLabel.equalsIgnoreCase("Assassin") && sender instanceof Player){
		if (player.hasPermission("EasyPvpKits.Assassin")){
			if (!plugin.kits.contains(player.getName())){
				
				ItemStack NinjaStar = new ItemStack(Material.NETHER_STAR);
			    ItemMeta NinjaStarmeta = NinjaStar.getItemMeta();
			    NinjaStarmeta.setDisplayName(ChatColor.GOLD + "Ninja Star");
			    ArrayList<String> NinjaStarlore = new ArrayList<String>();
			    NinjaStarlore.add(ChatColor.RED + "Right click to activate assassination mode");
			    NinjaStarmeta.setLore(NinjaStarlore);
			    NinjaStar.setItemMeta(NinjaStarmeta);
				
				ItemStack AssassinHat = new ItemStack(Material.LEATHER_HELMET);
				LeatherArmorMeta AHat = (LeatherArmorMeta)AssassinHat.getItemMeta();
				AHat.setColor(Color.WHITE);
				AssassinHat.setItemMeta(AHat);
				AssassinHat.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
				
				ItemStack AssassinChest = new ItemStack(Material.LEATHER_CHESTPLATE);
				LeatherArmorMeta AChest = (LeatherArmorMeta)AssassinChest.getItemMeta();
				AChest.setColor(Color.WHITE);
				AssassinChest.setItemMeta(AChest);
				AssassinChest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
				
				ItemStack AssassinLegs = new ItemStack(Material.LEATHER_LEGGINGS);
				LeatherArmorMeta ALegs = (LeatherArmorMeta)AssassinLegs.getItemMeta();
				ALegs.setColor(Color.WHITE);
				AssassinLegs.setItemMeta(ALegs);
				AssassinLegs.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
				
				ItemStack AssassinBoots = new ItemStack(Material.LEATHER_BOOTS);
				LeatherArmorMeta ABoots = (LeatherArmorMeta)AssassinBoots.getItemMeta();
				ABoots.setColor(Color.WHITE);						
				AssassinBoots.setItemMeta(ABoots);
				AssassinBoots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
				AssassinBoots.addEnchantment(Enchantment.PROTECTION_FALL, 2);
				
				ItemStack SSword = new ItemStack(Material.STONE_SWORD);
				SSword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
				SSword.addEnchantment(Enchantment.DURABILITY, 3);
				
				player.sendMessage(ChatColor.GOLD + "You have been given the Assassin kit!");
				
				if(plugin.getConfig().getBoolean("options.sound-effects"))
				{
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 2.0F, 2.0F);
				}
				
				plugin.kits.add(player.getName());
				plugin.Assassin.add(player.getName());
				
				player.getInventory().clear();
				player.getInventory().setHelmet(AssassinHat);
				player.getInventory().setBoots(AssassinBoots);
				player.getInventory().setChestplate(AssassinChest);
				player.getInventory().setLeggings(AssassinLegs);
				player.getInventory().addItem(SSword);
				player.getInventory().addItem(NinjaStar);
				if(plugin.getConfig().getBoolean("options.soup-in-kits"))
				{
					for (int i = 0; i < 34; i++){
						player.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
					}
			    }
			}
			else player.sendMessage(ChatColor.RED + "You have already picked a kit!");				  
		}
		else player.sendMessage(ChatColor.RED + "You don't have permission to recieve this kit.");
		return true;
	}
	return false;
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
