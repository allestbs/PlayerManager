package me.allestbs.items;

import java.util.Arrays;

import me.allestbs.core.PlayerManager;
import me.allestbs.core.PlayerMenu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStacks {
	
	public static ItemStack info() {
		ItemStack info = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		ItemMeta im = info.getItemMeta();
		im.setDisplayName(ChatColor.GOLD + "Basic info of player - " + PlayerMenu.p.getName());
		im.setLore(Arrays.asList(
				"",
				ChatColor.RED + "Warinings: " + ChatColor.WHITE + PlayerManager.getInstance().getConfig().get("manager." + PlayerMenu.p.getName() + ".warnings"), 
				ChatColor.RED + "Usernotes: " + ChatColor.WHITE + PlayerManager.getInstance().getConfig().get("manager." + PlayerMenu.p.getName() + ".usernotes")
				));
		info.setItemMeta(im);
		return info;
	}
	
	public static ItemStack ban() {
		ItemStack info = new ItemStack(Material.SIGN);
		ItemMeta im = info.getItemMeta();
		im.setDisplayName(ChatColor.GOLD + "Ban player");
		im.setLore(Arrays.asList("", ChatColor.DARK_BLUE + "Click to ban the player!"));
		info.setItemMeta(im);
		return info;
	}
	
	public static ItemStack kick() {
		ItemStack info = new ItemStack(Material.BOW);
		ItemMeta im = info.getItemMeta();
		im.setDisplayName(ChatColor.GOLD + "Kick Player");
		im.setLore(Arrays.asList("", ChatColor.DARK_BLUE + "Click to kick the player!"));
		info.setItemMeta(im);
		return info;
	}
	
	public static ItemStack banItem() {
		ItemStack info = new ItemStack(Material.SKULL_ITEM, 1, (short) 2);
		ItemMeta im = info.getItemMeta();
		im.setDisplayName(ChatColor.GOLD + "Ban Player");
		im.setLore(Arrays.asList("", ChatColor.DARK_BLUE + "Click to ban the player!"));
		info.setItemMeta(im);
		return info;
	}
	
	public static ItemStack unbanItem() {
		ItemStack info = new ItemStack(Material.SKULL_ITEM, 1, (short) 2);
		ItemMeta im = info.getItemMeta();
		im.setDisplayName(ChatColor.GOLD + "Unban Player");
		im.setLore(Arrays.asList(
				"", 
				ChatColor.RED + "Player " + PlayerMenu.p.getName() + " is already banned!", 
				"", 
				ChatColor.DARK_BLUE + "Ban information:", 
				ChatColor.GOLD + "Ban reason: " + ChatColor.WHITE + PlayerManager.getInstance().getConfig().getString("manager." + PlayerMenu.p.getUniqueId() + ".banreason", 
				ChatColor.GOLD + "Date of unban: " + ChatColor.WHITE + PlayerManager.getInstance().getConfig().getString("manager." + PlayerMenu.p.getUniqueId() + ".bantime"))
				));
		info.setItemMeta(im);
		return info;
	}
}
