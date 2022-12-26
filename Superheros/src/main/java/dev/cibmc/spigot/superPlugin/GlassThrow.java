package dev.cibmc.spigot.superPlugin;

import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.Vibration;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class GlassThrow implements Listener {
    
    @EventHandler
    public static void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() == Action.LEFT_CLICK_AIR) {
            if (player.getDisplayName().equals("Devster41")) {
                ItemStack i = new ItemStack(Material.NOTE_BLOCK);
                if (event.getItem().getItemMeta().equals(i.getItemMeta())) {
                    Block block = player.getTargetBlock(null, 200);
                    Location mid = new Location(player.getWorld(), (block.getX() + player.getLocation().getX()) / 2, 
                                                (block.getY() + player.getLocation().getY()) / 2, 
                                                (block.getZ() + player.getLocation().getZ()) / 2);

                    Collection<Entity> blockNearby = block.getLocation().getWorld().getNearbyEntities(block.getLocation(), 5, 5, 5);
                    Collection<Entity> midNearby = mid.getWorld().getNearbyEntities(block.getLocation(), 5, 5, 5);
                    Collection<Entity> playerNearby = player.getLocation().getWorld().getNearbyEntities(block.getLocation(), 5, 5, 5);
                    player.spawnParticle(Particle.SONIC_BOOM, player.getLocation(), 50);
                    for (Entity tmp: playerNearby) {
                        if ((tmp instanceof Damageable) && !(tmp instanceof Player))
                            ((Damageable) tmp).damage(2);
                    }
                    player.spawnParticle(Particle.SONIC_BOOM, mid, 50);
                    for (Entity tmp: midNearby) {
                        if ((tmp instanceof Damageable) && !(tmp instanceof Player))
                            ((Damageable) tmp).damage(2);
                    }
                    player.spawnParticle(Particle.SONIC_BOOM, block.getLocation(), 50);
                    for (Entity tmp: blockNearby) {
                        if ((tmp instanceof Damageable) && !(tmp instanceof Player))
                            ((Damageable) tmp).damage(2);
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
