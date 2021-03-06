package me.allestbs.core;

import java.util.ArrayList;

import me.allestbs.inventoryapi.InventoryAPI;
import me.allestbs.inventoryapi.ItemStackBuilder;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class Inventory {
	
	static ArrayList<String> banreason = new ArrayList<String>();
	static ArrayList<String> timestamp = new ArrayList<String>();
	
	static InventoryAPI inv = new InventoryAPI(ChatColor.DARK_BLUE + "PlayerManager", 9);
	static InventoryAPI baninv = new InventoryAPI(ChatColor.GOLD + "BanMenu", 9);
	
	public static void startUp(final Player p) {
		inv.setItem(0, new ItemStackBuilder(new ItemStack(Material.SKULL_ITEM, 1, (short) 3)).setDisplayName(ChatColor.GOLD + "Basic info of player - " + PlayerMenu.p.getName()).setLores("",
				ChatColor.RED + "Warinings: " + ChatColor.WHITE + PlayerManager.getInstance().getConfig().get("manager." + PlayerMenu.p.getName() + ".warnings"), 
				ChatColor.RED + "Usernotes: " + ChatColor.WHITE + PlayerManager.getInstance().getConfig().get("manager." + PlayerMenu.p.getName() + ".usernotes")).toItemStack());
		
		inv.setItem(2, new ItemStackBuilder(new ItemStack(Material.SIGN)).setDisplayName(ChatColor.GOLD + "Ban Player").setLores("", ChatColor.DARK_BLUE + "Click to ban the player!").toItemStack(), 
				new Runnable() {
			
			@Override
			public void run() {
				if (PlayerMenu.p.isBanned()) {
					baninv.setItem(4, new ItemStackBuilder(new ItemStack(Material.SKULL_ITEM, 1, (short) 4)).setDisplayName(ChatColor.GOLD + "Unban Player").setLores(
							"", 
							ChatColor.RED + "Player " + PlayerMenu.p.getName() + " is already banned!", 
							"",
							ChatColor.DARK_BLUE + "Ban information:", 
							ChatColor.GOLD + "Ban reason: " + ChatColor.WHITE + PlayerManager.getInstance().getConfig().getString("manager." + PlayerMenu.p.getUniqueId() + ".banreasonraw", 
							ChatColor.GOLD + "Ban expires: " + ChatColor.WHITE + PlayerManager.getInstance().getConfig().getString("manager." + PlayerMenu.p.getUniqueId() + ".bantime"))).toItemStack(), 
							new Runnable() {
						
						@Override
						public void run() {
							BanManager bn = new BanManager();
							bn.unbanPlayer(PlayerMenu.p, p);
							
						}
					});
				} else {
					baninv.setItem(4, new ItemStackBuilder(new ItemStack(Material.SKULL_ITEM, 1, (short) 4)).setDisplayName(ChatColor.GOLD + "Ban Player").setLores("", ChatColor.DARK_BLUE + "Click to ban the player!").toItemStack(), 
					new Runnable() {
						
						@Override
						public void run() {
							banreason.add(p.getName());
							baninv.close(p);
							p.sendMessage(new String[] { ChatColor.GOLD + "Typ your banreason in the chat.", ChatColor.GOLD + "The words " + ChatColor.GRAY + "griefing, rules and swearing" + ChatColor.GOLD + " will automatically give a banreason!" });
							
						}
					});
				}
				baninv.open(p);
			}
		});
	}
	
	public static void register(Plugin pl) {
		inv.register(pl);
		baninv.register(pl);
	}
	
	public static void open(Player p) {
		startUp(p);
		inv.open(p);
	}

}
