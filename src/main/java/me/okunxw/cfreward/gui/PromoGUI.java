package me.okunxw.cfreward.gui;

import me.okunxw.cfreward.utils.PromoManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class PromoGUI implements Listener {

    public static void openPromoMenu(Player player) {
        Inventory gui = Bukkit.createInventory(null, 27, "Промокоды");

        Map<String, List<String>> promoCodes = PromoManager.getPromoCodes();
        int slot = 0;

        for (Map.Entry<String, List<String>> entry : promoCodes.entrySet()) {
            if (slot >= gui.getSize()) break;
            String code = entry.getKey();
            List<String> rewards = entry.getValue();

            ItemStack promoItem = new ItemStack(Material.PAPER);
            ItemMeta meta = promoItem.getItemMeta();
            meta.setDisplayName("§aПромокод: " + code);
            meta.setLore(rewards);
            promoItem.setItemMeta(meta);

            gui.setItem(slot, promoItem);
            slot++;
        }

        player.openInventory(gui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("Промокоды")) return;
        event.setCancelled(true);

        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;

        Player player = (Player) event.getWhoClicked();
        String promoCode = event.getCurrentItem().getItemMeta().getDisplayName().replace("§aПромокод: ", "");

        if (PromoManager.canUsePromo(player, promoCode)) {
            PromoManager.applyPromo(player, promoCode);
            player.sendMessage("§aВы успешно использовали промокод: " + promoCode);
            openPromoMenu(player);
        } else {
            player.sendMessage("§cВы уже использовали этот промокод или он недоступен.");
        }
    }
}
