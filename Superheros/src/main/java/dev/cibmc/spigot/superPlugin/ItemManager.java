package dev.cibmc.spigot.superPlugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class ItemManager {
    public static ItemStack eldrichBlast;
    public static ItemStack mic;
    public static ItemStack healingStaff;

    public static void init() {
        createBlast();
    }

    private static void createBlast() {
        ItemStack item = new ItemStack(Material.GLASS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Eldrich Blast");
        List<String> lore = new ArrayList<>();
        lore.add("Anne's powerful glass blast");
        meta.addEnchant(Enchantment.DAMAGE_ALL, 7, false);
        item.setItemMeta(meta);
        eldrichBlast = item;
        meta.addItemFlags();
    }

    private static void createMic() {
        ItemStack item = new ItemStack(Material.NOTE_BLOCK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Microphone");
        List<String> lore = new ArrayList<>();
        lore.add("Microphone for Amanda");
        meta.addEnchant(Enchantment.LUCK, 3, false);
        item.setItemMeta(meta);
        mic = item;
        meta.addItemFlags();

    }

    private static void createStaff() {
        ItemStack item = new ItemStack(Material.STICK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Healing Staff");
        List<String> lore = new ArrayList<>();
        lore.add("Aya's healing staff");
        meta.addEnchant(Enchantment.LUCK, 3, false);
        item.setItemMeta(meta);
        mic = item;
        meta.addItemFlags();

    }
}
