package me.okunxw.cfreward.commands;

import me.okunxw.cfreward.CFReward;
import me.okunxw.cfreward.managers.PromoCodeManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CFRewardCommand implements CommandExecutor {

    private final CFReward plugin;
    private final PromoCodeManager promoCodeManager;

    public CFRewardCommand(CFReward plugin, PromoCodeManager promoCodeManager) {
        this.plugin = plugin;
        this.promoCodeManager = promoCodeManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§aИспользование: /cfreward <подкоманда>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                if (!sender.hasPermission("cfreward.admin")) {
                    sender.sendMessage("§cУ вас нет прав!");
                    return true;
                }
                plugin.reloadConfig();
                sender.sendMessage("§aКонфиг перезагружен!");
                return true;

            case "give":
                if (!sender.hasPermission("cfreward.admin")) {
                    sender.sendMessage("§cУ вас нет прав!");
                    return true;
                }
                if (args.length < 3) {
                    sender.sendMessage("§cИспользование: /cfreward give <игрок> <код>");
                    return true;
                }
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage("§cИгрок не найден!");
                    return true;
                }
                String code = args[2];
                promoCodeManager.redeemCode(target, code);
                return true;

            default:
                sender.sendMessage("§cНеизвестная команда!");
                return true;
        }
    }
}
