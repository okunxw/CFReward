package me.okunxw.cfreward;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CFReward extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("CFReward загружен!");

        // Подключение к Vault (деньги)
        if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
            getLogger().info("Vault найден, подключаемся...");
        } else {
            getLogger().warning("Vault не найден! Экономика работать не будет.");
        }

        // Подключение к LuckPerms (права)
        if (Bukkit.getPluginManager().getPlugin("LuckPerms") != null) {
            getLogger().info("LuckPerms найден, подключаемся...");
        } else {
            getLogger().warning("LuckPerms не найден! Выдача прав не будет работать.");
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("CFReward отключен.");
    }
}
