package com.gmail.codervortex;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import com.gmail.codervortex.Main;

public class GuiListener implements Listener
{
	public Main plugin;
	public GuiListener(Main instance){	
		plugin = instance;
	}
	
	 @EventHandler
	  public void onClick(InventoryClickEvent event)
	  {
		 Inventory inv = event.getInventory();
	      Player p = (Player)event.getWhoClicked();
		      if ((inv.getTitle().equals(ChatColor.GOLD + "Kit Selector")))
		      {
		    	  if ((event.getCurrentItem() != null) && (event.getCurrentItem().getItemMeta() != null))
		    	  {
		    	  if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Pvp"))
		    	  {
		    		  Bukkit.dispatchCommand(p, "Pvp");
		    		  if(plugin.getConfig().getBoolean("options.sound-effects"))
						{
		    			  p.playSound(p.getLocation(), Sound.CLICK, 2.0F, 2.0F);
						}
		    		  event.setCancelled(true);
		    		  p.closeInventory();
		    	  }
		    	  if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Archer"))
		    	  {
		    		  Bukkit.dispatchCommand(p, "Archer");
		    		  if(plugin.getConfig().getBoolean("options.sound-effects"))
						{
		    			  p.playSound(p.getLocation(), Sound.CLICK, 2.0F, 2.0F);
						}
		    		  event.setCancelled(true);
		    		  p.closeInventory();
		    	  }
		    	  if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Heavy"))
		    	  {
		    		  Bukkit.dispatchCommand(p, "Heavy");
		    		  if(plugin.getConfig().getBoolean("options.sound-effects"))
						{
		    			  p.playSound(p.getLocation(), Sound.CLICK, 2.0F, 2.0F);
						}
		    		  event.setCancelled(true);
		    		  p.closeInventory();
		    	  }
		    	  if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Assassin"))
		    	  {
		    		  Bukkit.dispatchCommand(p, "Assassin");
		    		  if(plugin.getConfig().getBoolean("options.sound-effects"))
						{
		    			  p.playSound(p.getLocation(), Sound.CLICK, 2.0F, 2.0F);
						}
		    		  event.setCancelled(true);
		    		  p.closeInventory();
		    	  }
		    	  if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Pyro"))
		    	  {
		    		  Bukkit.dispatchCommand(p, "Pyro");
		    		  if(plugin.getConfig().getBoolean("options.sound-effects"))
						{
		    			  p.playSound(p.getLocation(), Sound.CLICK, 2.0F, 2.0F);
						}
		    		  event.setCancelled(true);
		    		  p.closeInventory();
		    	  }
		    	  if ((!event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Pvp")) || 
		    	      (!event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Archer")) || 
                      (!event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Heavy")) ||
		    		  (!event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Pyro")) ||
		    		  (!event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Assassin")))
		    	      {
		    		   event.setCancelled(true);
		    	       }  
		      }
		    }			 
		}
	  }
	 
	 
	
	
	
	
	
	
	
	
	

