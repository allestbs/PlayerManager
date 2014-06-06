package me.allestbs.core;

import me.allestbs.commands.OpenCMD;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerManager extends JavaPlugin{
	
	static PlayerManager plugin;
	
	@Override
	public void onEnable() {
		PluginDescriptionFile pdf = getDescription();
		System.out.println("[PlayerManager] " + pdf.getVersion() + " is now enabled!");
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Events(), this);
		Inventory.register(this);
		getCommand("playermanager").setExecutor(new OpenCMD());
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			if ((!getConfig().contains("manager." + p.getName() + ".warnings")) && (!getConfig().contains("manager." + p.getName() + ".usernotes"))) {
				getConfig().set("manager." + p.getName() + ".warnings", 0);
				getConfig().set("manager." + p.getName() + ".usernotes", 0);
				saveConfig();
			}
			
		}
	
		plugin = this;
	}
	
	public static Plugin getInstance() {
		return plugin;
	}

}
