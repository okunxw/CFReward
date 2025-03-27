package me.okunxw.cfreward;

import org.bukkit.plugin.java.JavaPlugin;

public class CFReward extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("CFReward включен!");
    }

    @Override
    public void onDisable() {
        getLogger().info("CFReward выключен!");
    }
}
