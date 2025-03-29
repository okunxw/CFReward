package me.okunxw.cfreward;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CFReward extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("CFReward загружен!");

        // Проверка наличия Vault
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            getLogger().warning("Vault не найден! Экономика работать не будет.");
        } else {
            getLogger().info("Vault найден, подключаемся...");
            // Инициализация экономики через Vault
            setupEconomy();
        }

        // Проверка наличия LuckPerms
        if (Bukkit.getPluginManager().getPlugin("LuckPerms") == null) {
            getLogger().warning("LuckPerms не найден! Выдача прав не будет работать.");
        } else {
            getLogger().info("LuckPerms найден, подключаемся...");
            // Инициализация системы прав через LuckPerms
            setupPermissions();
        }

        // Регистрация команд
        getCommand("cfreward").setExecutor(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("CFReward отключен.");
    }

    // Метод для инициализации экономики
    private void setupEconomy() {
        // Код для настройки экономики через Vault
    }

    // Метод для инициализации системы прав
    private void setupPermissions() {
        // Код для настройки прав через LuckPerms
    }

    // Обработка команд
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("cfreward")) {
            if (args.length == 0) {
                sender.sendMessage("Использование: /cfreward [args]");
                return true;
            }
            // Обработка подкоманд
            if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage("Список доступных команд:");
                // Добавь сюда список команд
            } else if (args[0].equalsIgnoreCase("create")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    // Код для создания промокода
                    player.sendMessage("Промокод создан!");
                } else {
                    sender.sendMessage("Эту команду может выполнить только игрок.");
                }
            }
            // Добавь обработку других подкоманд
            return true;
        }
        return false;
    }
}
