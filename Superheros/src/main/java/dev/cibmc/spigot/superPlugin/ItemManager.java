package dev.cibmc.spigot.superPlugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.SmallFireball;
import org.bukkit.Sound;

public class ItemManager {
    public static ItemStack eldrichBlast;

    public static void init() {
        createBlast();
    }

    private static void createBlast() {
        ItemStack item = new ItemStack(Material.GLASS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("&6Eldrich Blast");
        List<String> lore = new ArrayList<>();
        lore.add("&7Anne's powerful glass blast");
        meta.addEnchant(Enchantment.DAMAGE_ALL, 5, false);
        item.setItemMeta(meta);
        eldrichBlast = item;
        meta.addItemFlags();
    }

    public void effect(Event event, Player player) {
        player.launchProjectile(SmallFireball.class);
        player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, null, 1.0f, 1.0f);
    }

    @EventHandler(priority=EventPriority.HIGH)
    public void onPlayerUse(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (p.getItemInUse() == eldrichBlast) {
            effect(event, p);
        }
    }
}
