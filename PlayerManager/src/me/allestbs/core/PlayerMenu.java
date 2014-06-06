package me.allestbs.core;

import me.allestbs.items.ItemStacks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PlayerMenu {
	
	String name;
	int slots;
	public static Player p;
	
	public PlayerMenu(Player p, String name, int slots) {
		this.name = name;
		this.slots = slots;
		PlayerMenu.p = p;
	}
	
	public void create(Player p) {
		Inventory inv = Bukkit.createInventory(null, this.slots, this.name);
		inv.setItem(0, ItemStacks.info());
		inv.setItem(2, ItemStacks.ban());
		p.openInventory(inv);
	}
	
	public void createBanMenu(Player holder, Player banned) {
		Inventory inv = Bukkit.createInventory(null, this.slots, this.name);
		if (banned.isBanned()) {
			inv.setItem(4, ItemStacks.unbanItem());
		} else {
			inv.setItem(4, ItemStacks.banItem());
		}
		holder.openInventory(inv);
		
	}
	
	

}
