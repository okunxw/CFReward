package me.okunxw.cfreward.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class PromoGUI implements Listener {

    public static void openPromoMenu(Player player) {
        Inventory gui = Bukkit.createInventory(null, 27, "Промокоды");
        
        ItemStack exampleCode = new ItemStack(Material.PAPER);
        ItemMeta meta = exampleCode.getItemMeta();
        meta.setDisplayName("§aПромокод: example_code");
        meta.setLore(Arrays.asList("§7Награды:", "§6- 1000 монет", "§6- 3 алмаза", "§6- VIP доступ"));
        exampleCode.setItemMeta(meta);
        
        gui.setItem(13, exampleCode);
        
        player.openInventory(gui);
    }
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Промокоды")) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
            
            Player player = (Player) event.getWhoClicked();
            player.sendMessage("§aВы выбрали промокод!");
            player.closeInventory();
        }
    }
}
