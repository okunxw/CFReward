package me.okunxw.cfreward;

import me.okunxw.cfreward.commands.PromoCommand;
import me.okunxw.cfreward.commands.PromoGUICommand;
import me.okunxw.cfreward.gui.PromoGUI;
import me.okunxw.cfreward.utils.ConfigManager;
import me.okunxw.cfreward.utils.PromoManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class CFReward extends JavaPlugin {
    private static CFReward instance;
    private static Economy econ = null;
    private PromoManager promoManager;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        configManager = new ConfigManager(this);
        promoManager = new PromoManager(this);

        if (!setupEconomy()) {
            getLogger().warning("[CFReward] Vault не найден! Экономическая система отключена.");
        }

        registerCommands();
        registerEvents();
        getLogger().info("[CFReward] Плагин успешно загружен!");
    }

    @Override
    public void onDisable() {
        getLogger().info("[CFReward] Плагин отключен.");
    }

    private void registerCommands() {
        getCommand("promo").setExecutor(new PromoCommand(this));
        getCommand("openpromo").setExecutor(new PromoGUICommand());
        getCommand("promogui").setExecutor(new PromoGUICommand());
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PromoGUI(), this);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static CFReward getInstance() {
        return instance;
    }

    public PromoManager getPromoManager() {
        return promoManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public static Economy getEconomy() {
        return econ;
    }
}
