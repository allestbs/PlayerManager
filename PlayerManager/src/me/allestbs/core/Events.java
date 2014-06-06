package me.allestbs.core;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Events implements Listener{
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.DARK_BLUE + "PlayerManager")) {
			if (e.getCurrentItem().getType().equals(Material.SIGN)) {
				PlayerMenu menu = new PlayerMenu(PlayerMenu.p, ChatColor.GRAY + "BanMenu", 9);
				menu.createBanMenu(p, PlayerMenu.p);
			}
		} 
		
		if (e.getInventory().getName().equalsIgnoreCase(ChatColor.GRAY + "BanMenu")) {
			System.out.println("Heeft het goed");
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.GOLD + "Ban player")) {
				e.setCancelled(true);
				p.sendMessage("Je hebt " + PlayerMenu.p.getName() + " verbannen!");
			}
		}
	}

}
