package me.allestbs.core;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Events implements Listener{
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.DARK_BLUE + "PlayerManager")) {
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Ban Player")) {
				
			}
		} 
		
		if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.GRAY + "BanMenu")) {
			System.out.println("Heeft het goed");
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Ban Player")) {
				e.setCancelled(true);
				p.sendMessage("Je hebt " + PlayerMenu.p.getName() + " verbannen!");
			}
		}
	}

}
