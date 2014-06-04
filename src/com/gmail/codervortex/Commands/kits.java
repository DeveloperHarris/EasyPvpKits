package com.gmail.codervortex.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gmail.codervortex.Main;

public class kits implements CommandExecutor
{
	public Main plugin;
	public kits(Main instance){	
		plugin = instance;
	}
	
	public static Inventory kits = Bukkit.createInventory(null, 27, ChatColor.GOLD + "Kit Selector");
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	  {
	    if (cmd.getName().equalsIgnoreCase("kits"))
	    {
	      if ((sender instanceof Player))
	      {
	       Player player = (Player) sender; 
	        
	        kits.clear();
	        
	        ItemStack pvp = new ItemStack(Material.DIAMOND_SWORD);
	        ItemMeta pvpmeta = pvp.getItemMeta();
	        pvpmeta.setDisplayName(ChatColor.GOLD + "Pvp");
			ArrayList<String> pvplore = new ArrayList<String>();
	        pvplore.add(ChatColor.RED + "Choose the Pvp kit.");
	        pvpmeta.setLore(pvplore);
	        pvp.setItemMeta(pvpmeta);
	        kits.setItem(10, pvp);
	        
	        
	        ItemStack archer = new ItemStack(Material.BOW);
	        ItemMeta archermeta = archer.getItemMeta();
	        archermeta.setDisplayName(ChatColor.GOLD + "Archer");
	        ArrayList<String> archerlore = new ArrayList<String>();
	        archerlore.add(ChatColor.RED + "Choose the Archer kit.");
	        archermeta.setLore(archerlore);
	        archer.setItemMeta(archermeta);
	        kits.setItem(11, archer);
	        
	        ItemStack heavy = new ItemStack(Material.DIAMOND_CHESTPLATE);
	        ItemMeta heavymeta = heavy.getItemMeta();
	        heavymeta.setDisplayName(ChatColor.GOLD + "Heavy");
	        ArrayList<String> heavylore = new ArrayList<String>();
	        heavylore.add(ChatColor.RED + "Choose the Heavy kit.");
	        heavymeta.setLore(heavylore);
	        heavy.setItemMeta(heavymeta);
	        kits.setItem(12, heavy);
	        
	        ItemStack assassin = new ItemStack(Material.NETHER_STAR);
	        ItemMeta assassinmeta = assassin.getItemMeta();
	        assassinmeta.setDisplayName(ChatColor.GOLD + "Assassin");
	        ArrayList<String> assassinlore = new ArrayList<String>();
	        assassinlore.add(ChatColor.RED + "Choose the Assassin kit.");
	        assassinmeta.setLore(assassinlore);
	        assassin.setItemMeta(assassinmeta);
	        kits.setItem(13, assassin);
	        
	        ItemStack pyro = new ItemStack(Material.FLINT_AND_STEEL);
	        ItemMeta pyrometa = pyro.getItemMeta();
	        pyrometa.setDisplayName(ChatColor.GOLD + "Pyro");
	        ArrayList<String> pyrolore = new ArrayList<String>();
	        pyrolore.add(ChatColor.RED + "Choose the Pyro kit.");
	        pyrometa.setLore(pyrolore);
	        pyro.setItemMeta(pyrometa);
	        kits.setItem(14, pyro);
	        
	        
	        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
	        ItemMeta fillermeta = filler.getItemMeta();
	        fillermeta.setDisplayName(" ");
	        ArrayList<String> fillerlore = new ArrayList<String>();
	        fillerlore.add(" ");
	        fillermeta.setLore(fillerlore);
	        filler.setItemMeta(fillermeta);
	        kits.setItem(0, filler);
	        kits.setItem(1, filler);
	        kits.setItem(2, filler);
	        kits.setItem(3, filler);
	        kits.setItem(4, filler);
	        kits.setItem(5, filler);
	        kits.setItem(6, filler);
	        kits.setItem(7, filler);
	        kits.setItem(8, filler);
	        kits.setItem(9, filler);
	        kits.setItem(17, filler);
	        kits.setItem(18, filler);
	        kits.setItem(19, filler);
	        kits.setItem(20, filler);
	        kits.setItem(21, filler);
	        kits.setItem(22, filler);
	        kits.setItem(23, filler);
	        kits.setItem(24, filler);
	        kits.setItem(25, filler);
	        kits.setItem(26, filler);
	
	        player.openInventory(kits);
	        return true;
	      }
	    }
		return false;
	  }
}
