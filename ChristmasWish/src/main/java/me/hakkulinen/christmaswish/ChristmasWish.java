package me.hakkulinen.christmaswish;

// Import necessary packages
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ChristmasWish extends JavaPlugin implements TabExecutor {
    private FileConfiguration config;

    @Override
    public void onEnable() {
        // Create the config file if it doesn't exist
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveResource("config.yml", false);
        }

        // Load the config file
        config = getConfig();

        getCommand("wish").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("wish")) {
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("clear")) {
                    clearWish(sender);
                    return true;
                } else {
                    String wishMessage = Arrays.stream(args)
                            .collect(Collectors.joining(" "));
                    storeWish(sender, wishMessage);
                    return true;
                }
            }
        }
        return false;
    }

    private void storeWish(CommandSender sender, String wish) {
        String playerName = sender.getName();
        if (!config.contains(playerName)) {
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String timeStamp = dateTime.format(formatter);

            config.set(playerName + ".time", timeStamp);
            config.set(playerName + ".message", wish);
            saveConfig();

            sender.sendMessage(ChatColor.AQUA + "Your wish has been sent to Santa!");
        } else {
            sender.sendMessage(ChatColor.RED + "You have already sent Santa your wish!");
        }
    }

    private void clearWish(CommandSender sender) {
        String playerName = sender.getName();
        if (config.contains(playerName)) {
            config.set(playerName, null);
            saveConfig();
            sender.sendMessage(ChatColor.AQUA + "Your wish has been cleared!");
        } else {
            sender.sendMessage(ChatColor.RED + "You don't have a wish sent!");
        }
    }
}
