package me.allestbs.core;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class Events implements Listener{
	
	@EventHandler
	public void chatEvent(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if (Inventory.banreason.contains(p.getName())) {
			if (e.getMessage().contains("griefing")) {
				BanManager.banreason = "You have been banned for griefing.";
				PlayerManager.getInstance().getConfig().set("manager." + PlayerMenu.p.getUniqueId() + ".banreasonraw", "You have been banned for griefing.");
				PlayerManager.getInstance().saveConfig();
				e.setCancelled(true);
			} else if (e.getMessage().contains("rules")) {
				BanManager.banreason = "You have been banned for ignoring the rules.";
				PlayerManager.getInstance().getConfig().set("manager." + PlayerMenu.p.getUniqueId() + ".banreasonraw", "You have been banned for ignoring the rules.");
				PlayerManager.getInstance().saveConfig();
				e.setCancelled(true);
			} else if (e.getMessage().contains("swearing")) {
				BanManager.banreason = "You have been banned for swearing in chat.";
				PlayerManager.getInstance().getConfig().set("manager." + PlayerMenu.p.getUniqueId() + ".banreasonraw", e.getMessage());
				PlayerManager.getInstance().saveConfig();
				e.setCancelled(true);
			} else {
				BanManager.banreason = e.getMessage();
				PlayerManager.getInstance().getConfig().set("manager." + PlayerMenu.p.getUniqueId() + ".banreasonraw", "You have been banned for swearing in chat.");
				PlayerManager.getInstance().saveConfig();
				e.setCancelled(true);
			}
			Inventory.banreason.remove(p.getName());
			Inventory.timestamp.add(p.getName());
			p.sendMessage(new String[] { ChatColor.GOLD + "Typ your banlength in the chat. ", ChatColor.GOLD + "Example: " + ChatColor.GRAY + "1h = 1 hour" });
		} else if (Inventory.timestamp.contains(p.getName())) {
				BanManager.bantime = TimeParser.parseLong(TimeParser.parseString(e.getMessage()), false);
				BanManager bm = new BanManager();
				bm.banOfflinePlayer(PlayerMenu.p, BanManager.banreason, p, System.currentTimeMillis() + TimeParser.parseString(e.getMessage()));
				e.setCancelled(true);
			
			
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerlogin(PlayerLoginEvent e) {
		FileConfiguration config = PlayerManager.getInstance().getConfig();
		Player p = e.getPlayer();
		if (e.getResult() == Result.KICK_BANNED) {
			e.setKickMessage(ChatColor.translateAlternateColorCodes('&', config.getString("manager." + p.getUniqueId() + ".banreason")));
		}
	}
	
	

}
