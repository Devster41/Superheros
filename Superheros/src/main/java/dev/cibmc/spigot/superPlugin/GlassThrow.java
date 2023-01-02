package dev.cibmc.spigot.superPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.entity.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.RayTraceResult;

public class GlassThrow implements Listener {
    @EventHandler
    public void addSpeed(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.getDisplayName().equals("Devster41")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
        }
    }

    @EventHandler
    public static void onClick(PlayerInteractEvent event) throws InterruptedException {
        Player player = event.getPlayer();

        //Amanda
        if (player.getDisplayName().equals("Devster41")) {
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                ItemStack i = new ItemStack(Material.NOTE_BLOCK);

                if (event.getItem().getItemMeta().equals(i.getItemMeta())) {
                    BlockIterator iterator = new BlockIterator(player, 10);
                    Block nextBlock = null;
                    Collection<Entity> blockNearby = null;
                    while (iterator.hasNext()) {
                        nextBlock = iterator.next();
                        blockNearby = nextBlock.getLocation().getWorld().getNearbyEntities(nextBlock.getLocation(), 2, 2, 2);
                        player.spawnParticle(Particle.SONIC_BOOM, nextBlock.getLocation(), 10);
                        for (Entity tmp : blockNearby) {
                            if ((tmp instanceof Damageable) && !(tmp instanceof Player))
                            ((Damageable) tmp).damage(3);
                        }
                    }

                     
                    /* 
                    Collection<Entity> blockNearby = block.getLocation().getWorld().getNearbyEntities(block.getLocation(), 2, 2, 2);

                    
                    List<Entity> enemies = new ArrayList<>();
                    for (Entity tmp: blockNearby) {
                        if ((tmp instanceof Damageable) && !(tmp instanceof Player))
                            enemies.add(tmp);
                    }

                   
                    double midy = (block.getLocation().getY() + player.getLocation().getY()) / 2;
                    double midx = (block.getLocation().getX() + player.getLocation().getX()) / 2;
                    double midz = (block.getLocation().getZ() + player.getLocation().getZ()) / 2;

                    Location mid = new Location(player.getWorld(), midx, midy, midz);
                    Thread.sleep(100);
                    player.spawnParticle(Particle.SONIC_BOOM, mid, 1);
                    Thread.sleep(100); 
                    */
                   
                    
                    for (Entity tmp: blockNearby) {
                        if ((tmp instanceof Damageable) && !(tmp instanceof Player))
                            player.spawnParticle(Particle.SONIC_BOOM, tmp.getLocation(), 30);
                            ((Damageable) tmp).damage(3);
                    }
                }
            }
        }
        //Anne
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() != null) {
                if (event.getItem().getItemMeta().equals(ItemManager.eldrichBlast.getItemMeta())) {
                    player.launchProjectile(LlamaSpit.class);
                    player.getWorld().playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 1);
                }
            } 
        }
    }
}
