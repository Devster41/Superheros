package dev.cibmc.spigot.superPlugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class ItemManager {
    public static ItemStack eldrichBlast;
    public static ItemStack slowStaff;
    public static ItemStack hilt;

    public static void init() {
        createBlast();
        conductiveHilt();
        slowStaff();
    }

    private static void createBlast() {
        ItemStack item = new ItemStack(Material.GLASS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Eldrich Blast");
        List<String> lore = new ArrayList<>();
        lore.add("Anne's powerful glass blast");
        meta.addEnchant(Enchantment.DAMAGE_ALL, 7, true);
        item.setItemMeta(meta);
        eldrichBlast = item;
        meta.addItemFlags();
    }

    private static void slowStaff() {
        ItemStack item = new ItemStack(Material.BLAZE_ROD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Staff of Slowness");
        List<String> lore = new ArrayList<>();
        lore.add("Aya's staff to slow enemies down");
        meta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
        item.setItemMeta(meta);
        slowStaff = item;
        meta.addItemFlags();
    }

    private static void conductiveHilt() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Conductive Hilt");
        List<String> lore = new ArrayList<>();
        lore.add("Maxis' conductive hilt!");
        meta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
        meta.addEnchant(Enchantment.MENDING, 1, true);
        meta.addEnchant(Enchantment.DURABILITY, 3, true);
        item.setItemMeta(meta);
        hilt = item;
        meta.addItemFlags();
    }
}
