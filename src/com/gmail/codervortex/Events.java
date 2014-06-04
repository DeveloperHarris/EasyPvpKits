package com.gmail.codervortex;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Events implements Listener
{
	public Main plugin;
	public Events(Main instance)
	{	
		plugin = instance;
	}




	@EventHandler
	public void potionRemove(PlayerRespawnEvent e)
	{
		Player player = e.getPlayer();
		for (PotionEffect effect : player.getActivePotionEffects())
			player.removePotionEffect(effect.getType());
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
	  Player player = event.getPlayer();
	  if(player.hasPermission("foo.bar") && Main.update)
	  {
		  if(plugin.getConfig().getBoolean("options.auto-update-onlogin-notice")){
			  
			  player.sendMessage(ChatColor.GREEN + "An update is available: " + Main.name + ", a " + Main.type + " for " + Main.version + ".");
     		  player.sendMessage(ChatColor.GOLD + "Type /update if you would like to automatically update.");
			 }
	  }
	}


	@EventHandler
	public void OnPlayerSoup(PlayerInteractEvent event){
		Player player = event.getPlayer();
		if(plugin.getConfig().getBoolean("options.soup"))
		{
		if (player.hasPermission("EasyPvpKits.Soup"))
		 {
			if(player.getHealth() == 20){}
			else
			{
				int soup = +7;
				if((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() ==Action.RIGHT_CLICK_BLOCK) && player.getItemInHand().getType() == Material.MUSHROOM_SOUP)
				{
					player.setHealth(player.getHealth() + soup > player.getMaxHealth() ? player.getMaxHealth() : player.getHealth() + soup);
					event.getPlayer().getItemInHand().setType(Material.BOWL);	            
				}
			}
		 }
	    }	
	}







	@EventHandler(priority = EventPriority.MONITOR)
	public void onFoodLevelChange(final FoodLevelChangeEvent e){
		if (e.getEntity().hasPermission("EasyPvpKits.NoHunger")){
			e.setFoodLevel(20);
			new BukkitRunnable() {
				public void run() {
					e.setCancelled(true);
				}
			}.runTaskLater(plugin, 1);
		}
	}






	@EventHandler
	public void onSignCreate(SignChangeEvent sign){
		Player player = sign.getPlayer();
		if (player.hasPermission("EasyPvpKits.Sign.Create")){
			if (sign.getLine(0).equalsIgnoreCase("[EPK]")){
				if (sign.getLine(1).equalsIgnoreCase("Pvp")){
					sign.setLine(1, ChatColor.DARK_BLUE + "[Pvp]");
					sign.setLine(0, "");
					sign.setLine(2, "");
					sign.setLine(3, "");
					player.sendMessage(ChatColor.GREEN + "You have created a pvp kit sign successfully.");
				}
				if (sign.getLine(1).equalsIgnoreCase("Archer")){
					sign.setLine(1, ChatColor.DARK_BLUE + "[Archer]");
					sign.setLine(0, "");
					sign.setLine(2, "");
					sign.setLine(3, "");
					player.sendMessage(ChatColor.GREEN + "You have created a archer kit sign successfully.");
				}
				if (sign.getLine(1).equalsIgnoreCase("Heavy")){
					sign.setLine(1, ChatColor.DARK_BLUE + "[Heavy]");
					sign.setLine(0, "");
					sign.setLine(2, "");
					sign.setLine(3, "");
					player.sendMessage(ChatColor.GREEN + "You have created a heavy kit sign successfully");
				}
				if (sign.getLine(1).equalsIgnoreCase("Assassin")){
					sign.setLine(1, ChatColor.DARK_BLUE + "[Assassin]");
					sign.setLine(0, "");
					sign.setLine(2, "");
					sign.setLine(3, "");
					player.sendMessage(ChatColor.GREEN + "You have created an assassin kit sign successfully.");					
				}
				if (sign.getLine(1).equalsIgnoreCase("Pyro")){
					sign.setLine(1, ChatColor.DARK_BLUE + "[Pyro]");
					sign.setLine(0, "");
					sign.setLine(2, "");
					sign.setLine(3, "");
					player.sendMessage(ChatColor.GREEN + "You have created an pyro kit sign successfully.");
				}
			}
		}
	}

	@EventHandler
	public void playerDeath(PlayerDeathEvent e) {
		plugin.kits.remove(e.getEntity().getName());
	}



	@SuppressWarnings("deprecation")
	@EventHandler
	public void onSignClick(PlayerInteractEvent event){
		final Player player = event.getPlayer();
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
			Block block = event.getClickedBlock();
			if (block.getType() == Material.SIGN_POST || block.getType() == Material.SIGN || block.getType() == Material.WALL_SIGN){
				Sign sign = (Sign) event.getClickedBlock().getState();
				if (player.hasPermission("EasyPvpKits.Sign.Use")){
					if (sign.getLine(1).equalsIgnoreCase(ChatColor.DARK_BLUE + "[Pvp]")){
						if (player.hasPermission("EasyPvpKits.Sign.Kits.Pvp")){
							player.getInventory().clear();
							for (PotionEffect effect : player.getActivePotionEffects())
								player.removePotionEffect(effect.getType());
							    plugin.kits.remove(player.getName());

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
									player.updateInventory();

								 
						}
						else{
							player.sendMessage(ChatColor.RED + "You don't have permission to recieve this kit from a sign.");
						}
					}
					if (sign.getLine(1).equalsIgnoreCase(ChatColor.DARK_BLUE + "[Archer]")){
						if (player.hasPermission("EasyPvpKits.Sign.Kits.Archer")){
							player.getInventory().clear();
							for (PotionEffect effect : player.getActivePotionEffects())
								player.removePotionEffect(effect.getType());
							plugin.kits.remove(player.getName());
									ItemStack ABow = new ItemStack(Material.BOW);
									ABow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 2);

									if(plugin.getConfig().getBoolean("options.sound-effects"))
									{
									player.playSound(player.getLocation(), Sound.LEVEL_UP, 2.0F, 2.0F);
									}

									player.sendMessage(ChatColor.GOLD + "You have been given the Archer kit!");

									plugin.kits.add(player.getName());

									player.getInventory().clear();			    		
									player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));			
									player.getInventory().addItem(ABow);
									player.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
									player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
									player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
									player.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
									if(plugin.getConfig().getBoolean("options.soup-in-kits"))
									{
										for (int i = 0; i < 33; i++){
											player.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
										}
								    }		
									player.getInventory().addItem(new ItemStack(Material.ARROW));
									player.updateInventory();
						}
							
							else{
								player.sendMessage(ChatColor.RED + "You don't have permission to recieve this kit from a sign.");
							}
						}
					}
					if (sign.getLine(1).equalsIgnoreCase(ChatColor.DARK_BLUE + "[Heavy]")){
						if (player.hasPermission("EasyPvpKits.Sign.Kits.Heavy")){
							player.getInventory().clear();
							for (PotionEffect effect : player.getActivePotionEffects())
								player.removePotionEffect(effect.getType());
							plugin.kits.remove(player.getName());
									

									player.sendMessage(ChatColor.GOLD + "You have been given the Heavy kit!");

									if(plugin.getConfig().getBoolean("options.sound-effects"))
									{
									player.playSound(player.getLocation(), Sound.LEVEL_UP, 2.0F, 2.0F);
									}

									player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 1));

									plugin.kits.add(player.getName());

									player.getInventory().clear();
									player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
									player.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
									player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
									player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
									player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
									if(plugin.getConfig().getBoolean("options.soup-in-kits"))
									{
										for (int i = 0; i < 35; i++){
											player.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
										}
								    }	
									player.updateInventory();
							 
						}
						else{
							player.sendMessage(ChatColor.RED + "You don't have permission to recieve this kit from a sign.");
						}
					}
					if (sign.getLine(1).equalsIgnoreCase(ChatColor.DARK_BLUE + "[Assassin]")){
						if (player.hasPermission("EasyPvpKits.Sign.Kits.Assassin")){
							player.getInventory().clear();
							for (PotionEffect effect : player.getActivePotionEffects())
								player.removePotionEffect(effect.getType());
							plugin.kits.remove(player.getName());									

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
									player.getInventory().addItem(new ItemStack(Material.NETHER_STAR));
									if(plugin.getConfig().getBoolean("options.soup-in-kits"))
									{
										for (int i = 0; i < 34; i++){
											player.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
										}
								    }	
									player.updateInventory();

								
						}
						else{
							player.sendMessage(ChatColor.RED + "You don't have permission to recieve this kit from a sign.");
						}
					}
					if (sign.getLine(1).equalsIgnoreCase(ChatColor.DARK_BLUE + "[Pyro]")){
						if (player.hasPermission("EasyPvpKits.Sign.Kits.Pyro")){
							player.getInventory().clear();
							for (PotionEffect effect : player.getActivePotionEffects())
								player.removePotionEffect(effect.getType());
							plugin.kits.remove(player.getName());
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
									player.updateInventory();
						}
						else{
							player.sendMessage(ChatColor.RED + "You don't have permission to recieve this kit from a sign.");
						}
					}  
				}
			}	
		
	}


	@EventHandler
	public void onClickItem(PlayerInteractEvent event1){
		final Player player1 = event1.getPlayer();
		if(event1.hasItem() && event1.getPlayer().getItemInHand().getType().equals(Material.NETHER_STAR)) {
			if (!plugin.AssassinCooldown.contains(player1.getName())){
				if(event1.getAction().equals(Action.RIGHT_CLICK_AIR) || event1.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					if (plugin.Assassin.contains(player1.getName())){
						player1.sendMessage(ChatColor.DARK_PURPLE + "Time to assassinate!");
						player1.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 300, 0));
						player1.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 300, 9 ));
						player1.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 1));
						player1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 1));
						plugin.AssassinCooldown.add(player1.getName());
						final Map<Player, ItemStack[]> pArmor = new HashMap<Player, ItemStack[]>();
						pArmor.put(player1, player1.getInventory().getArmorContents());
						new BukkitRunnable() {
							public void run() {
								player1.getInventory().setArmorContents(null);
							}
						}.runTaskLater(plugin, 1); 	        	 	 
						new BukkitRunnable() {
							public void run() {
								player1.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 300, 2));
								player1.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));
								if (pArmor.containsKey(player1)) {
									player1.getInventory().setArmorContents(pArmor.get(player1));
								}
								player1.sendMessage(ChatColor.GRAY + "You feel your power weaken.");
							}
						}.runTaskLater(plugin, 15*20); 
						new BukkitRunnable() {
							public void run() {
								plugin.AssassinCooldown.remove(player1.getName());
							}
						}.runTaskLater(plugin, 60*20);
					}
					else player1.sendMessage(ChatColor.RED + "You aren't an assassin.");
				}
			}
			else player1.sendMessage(ChatColor.RED + "You must wait to assassinate again!");
		}	  	 
	}
}