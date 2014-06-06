package me.allestbs.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class BanManager {
	
	public static String bantime;
	public static String banreason;
	FileConfiguration config = PlayerManager.getInstance().getConfig();
	
	public BanManager() {
		
	}
	
	@SuppressWarnings("deprecation")
	public void banOfflinePlayer(OfflinePlayer p, String reason, Player banner) {
		String banreason = ChatColor.GOLD + "Player " + ChatColor.GRAY + "'" + p.getName() + "'" + ChatColor.GOLD + " was banned by " + ChatColor.GRAY + banner.getName() + ChatColor.GOLD + " for " + ChatColor.GRAY + reason + ChatColor.GOLD + " and this ban expires " + TimeParser.parseLong(TimeParser.parseString(bantime), false) + ChatColor.GOLD + ".";
		config.set("manager." + p.getUniqueId() + ".banreason", reason);
		PlayerManager.getInstance().saveConfig();
		if (p.isOnline()) {
			p.getPlayer().kickPlayer(banreason);
		} else {
			p.setBanned(true);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void unbanPlayer(OfflinePlayer p, Player unbanner) {
		p.setBanned(false);
		Bukkit.broadcastMessage(ChatColor.GOLD + "Player " + ChatColor.GRAY + p.getName() + ChatColor.GOLD + " was unbanned by " + ChatColor.GRAY + unbanner.getName() + ChatColor.GOLD + ".");
	}
	

}
