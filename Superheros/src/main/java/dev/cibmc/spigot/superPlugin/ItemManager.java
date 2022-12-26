package dev.cibmc.spigot.superPlugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


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
}
