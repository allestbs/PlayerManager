package me.allestbs.core;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Events implements Listener{
	
	@EventHandler
	public void chatEvent(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if (Inventory.banreason.contains(p.getName())) {
			if (e.getMessage().contains("griefing")) {
				BanManager.banreason = "You have been banned for griefing.";
				PlayerManager.getInstance().getConfig().set("manager." + PlayerMenu.p.getUniqueId() + ".banreason", "You have been banned for griefing.");
				PlayerManager.getInstance().saveConfig();
				e.setCancelled(true);
			} else if (e.getMessage().contains("rules")) {
				BanManager.banreason = "You have been banned for ignoring the rules.";
				PlayerManager.getInstance().getConfig().set("manager." + PlayerMenu.p.getUniqueId() + ".banreason", "You have been banned for ignoring the rules.");
				PlayerManager.getInstance().saveConfig();
				e.setCancelled(true);
			} else if (e.getMessage().contains("swearing")) {
				BanManager.banreason = "You have been banned for swearing in chat.";
				PlayerManager.getInstance().getConfig().set("manager." + PlayerMenu.p.getUniqueId() + ".banreason", e.getMessage());
				PlayerManager.getInstance().saveConfig();
				e.setCancelled(true);
			} else {
				BanManager.banreason = e.getMessage();
				PlayerManager.getInstance().getConfig().set("manager." + PlayerMenu.p.getUniqueId() + ".banreason", "You have been banned for swearing in chat.");
				PlayerManager.getInstance().saveConfig();
				e.setCancelled(true);
			}
			Inventory.banreason.remove(p.getName());
			Inventory.timestamp.add(p.getName());
		} else if (Inventory.timestamp.contains(p.getName())) {
			if (e.getMessage().contains("never")) {
				
			} else {
				PlayerManager.getInstance().getConfig().set("manager." + PlayerMenu.p.getUniqueId() + ".bantime", TimeParser.parseLong(TimeParser.parseString(e.getMessage()), false));
				PlayerManager.getInstance().saveConfig();
				BanManager bm = new BanManager();
				bm.banOfflinePlayer(PlayerMenu.p, BanManager.banreason, p);
				e.setCancelled(true);
			}
			
		}
	}

}
