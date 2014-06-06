package me.allestbs.commands;

import java.util.UUID;

import me.allestbs.core.PlayerMenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player pl = (Player) sender;
		if (args.length == 0) {
			// send message
		}
		
		@SuppressWarnings("deprecation")
		Player idp = Bukkit.getPlayer(args[0]);
		UUID id = idp.getUniqueId();
		Player p = Bukkit.getPlayer(id);
		PlayerMenu menu = new PlayerMenu(p, ChatColor.DARK_BLUE + "PlayerManager", 9);
		menu.create(pl);
		return false;
	}
	
	

}
