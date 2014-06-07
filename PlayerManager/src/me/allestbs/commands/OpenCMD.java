package me.allestbs.commands;

import me.allestbs.core.Inventory;
import me.allestbs.core.PlayerMenu;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
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
		OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
		PlayerMenu.p = p;
		Inventory.open(pl);
		return false;
	}
	
	

}
