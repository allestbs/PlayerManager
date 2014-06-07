package me.allestbs.core;

import java.util.Date;

import org.bukkit.BanEntry;
import org.bukkit.BanList;
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
	public void banOfflinePlayer(OfflinePlayer p, String reason, Player banner, long date) {
		Date date1 = new Date(date);
		String banreason = ChatColor.GOLD + "Player " + ChatColor.GRAY + "'" + p.getName() + "'" + ChatColor.GOLD + " was banned by " + ChatColor.GRAY + banner.getName() + ChatColor.GOLD + " for " + ChatColor.GRAY + "'" + reason + "'" + ChatColor.GOLD + " and this ban expires: " + ChatColor.GRAY  + date1.toLocaleString() + ChatColor.GOLD + ".";
		String banreason2 = ChatColor.GOLD + "You " + "have been banned by " + ChatColor.GRAY + banner.getName() + ChatColor.GOLD + " for " + ChatColor.GRAY + "'" + reason + "'" + ChatColor.GOLD + " and this ban expires: " + ChatColor.GRAY + date1.toLocaleString() + ChatColor.GOLD + ".";
		Bukkit.broadcastMessage(banreason);
		config.set("manager." + p.getUniqueId() + ".banreasonraw", reason);
		config.set("manager." + p.getUniqueId() + ".bantime", date1.toLocaleString());
		config.set("manager." + p.getUniqueId() + ".banreason", ChatColor.translateAlternateColorCodes('&', banreason2));
		PlayerManager.getInstance().saveConfig();
		if (p.isOnline()) {
			p.getPlayer().kickPlayer(banreason2);
			BanList ban = Bukkit.getBanList(BanList.Type.NAME);
			ban.addBan(p.getName(), reason, date1, banner.getName());
			BanEntry entry = ban.getBanEntry(p.getName());
			entry.setExpiration(date1);
			entry.save();
			p.setBanned(true);
		} else {
			p.setBanned(true);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void unbanPlayer(OfflinePlayer p, Player unbanner) {
		p.setBanned(false);
		Bukkit.broadcastMessage(ChatColor.GOLD + "Player " + ChatColor.GRAY + p.getName() + ChatColor.GOLD + " was unbanned by " + ChatColor.GRAY + unbanner.getName() + ChatColor.GOLD + ".");
		PlayerManager.getInstance().getConfig().set("manager." + PlayerMenu.p.getUniqueId() + ".bantimelong", null);
		PlayerManager.getInstance().saveConfig();
	}
	

}
