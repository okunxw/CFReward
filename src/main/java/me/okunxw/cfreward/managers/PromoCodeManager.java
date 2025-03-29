package me.okunxw.cfreward.managers;

import me.okunxw.cfreward.CFReward;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

public class PromoCodeManager {
    private final CFReward plugin;
    private final FileConfiguration config;
    private final Map<String, PromoCode> promoCodes = new HashMap<>();

    public PromoCodeManager(CFReward plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        loadPromoCodes();
    }

    public void loadPromoCodes() {
        promoCodes.clear();
        if (!config.contains("promocodes")) return;

        for (String key : config.getConfigurationSection("promocodes").getKeys(false)) {
            String reward = config.getString("promocodes." + key + ".reward");
            int uses = config.getInt("promocodes." + key + ".uses", -1);
            long expiry = config.getLong("promocodes." + key + ".expiry", -1);
            promoCodes.put(key, new PromoCode(key, reward, uses, expiry));
        }
    }

    public boolean redeemCode(Player player, String code) {
        if (!promoCodes.containsKey(code)) return false;

        PromoCode promo = promoCodes.get(code);
        if (promo.isExpired()) {
            player.sendMessage("§cЭтот промокод уже истёк!");
            return false;
        }
        if (!promo.canUse()) {
            player.sendMessage("§cЭтот промокод больше нельзя использовать!");
            return false;
        }

        promo.use();
        executeReward(player, promo.getReward());
        player.sendMessage("§aВы успешно использовали промокод!");
        return true;
    }

    private void executeReward(Player player, String reward) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), reward.replace("{player}", player.getName()));
    }

    private static class PromoCode {
        private final String code;
        private final String reward;
        private int uses;
        private final long expiry;

        public PromoCode(String code, String reward, int uses, long expiry) {
            this.code = code;
            this.reward = reward;
            this.uses = uses;
            this.expiry = expiry;
        }

        public boolean isExpired() {
            return expiry > 0 && System.currentTimeMillis() > expiry;
        }

        public boolean canUse() {
            return uses != 0;
        }

        public void use() {
            if (uses > 0) uses--;
        }

        public String getReward() {
            return reward;
        }
    }
}
