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
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SonicBoom implements Listener {
    
    @EventHandler(ignoreCancelled = true)
    public static void onRightClick(PlayerInteractEvent event) throws InterruptedException {
        Player player = event.getPlayer();

        //Anne
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
        
            //Amanda
            if (player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                Bukkit.broadcastMessage("Item: " + player.getInventory().getItemInMainHand().getType().equals(Material.AIR));
                if (player.getDisplayName().equals("Devster41")) {
                    Block block = player.getTargetBlock(null, 200);
                    

                    Location mid = new Location(player.getWorld(), (block.getX() + player.getLocation().getX()) / 2, 
                                            (block.getY() + player.getLocation().getY()) / 2, 
                                            (block.getZ() + player.getLocation().getZ()) / 2);


                    Collection<Entity> blockDmg = block.getLocation().getWorld().getNearbyEntities(block.getLocation(), 5, 5, 5);
                    Collection<Entity> locDmg = player.getLocation().getWorld().getNearbyEntities(player.getLocation(), 5, 5, 5);
                    Collection<Entity> midDmg = mid.getWorld().getNearbyEntities(mid, 5, 5, 5);

                    player.spawnParticle(Particle.SONIC_BOOM, player.getLocation(), 1);
                    for (Entity tmp: locDmg) {
                        if ((tmp instanceof Damageable) && !(tmp instanceof Player))
                            ((Damageable) tmp).damage(2);
                    }
                    Thread.sleep(250);
                    player.spawnParticle(Particle.SONIC_BOOM, mid, 1);
                    for (Entity tmp: midDmg) {
                        if ((tmp instanceof Damageable) && !(tmp instanceof Player))
                            ((Damageable) tmp).damage(2);
                    }
                    Thread.sleep(250);
                    player.spawnParticle(Particle.SONIC_BOOM, block.getLocation(), 1);
                    for (Entity tmp: blockDmg) {
                        if ((tmp instanceof Damageable) && !(tmp instanceof Player))
                            ((Damageable) tmp).damage(2);
                    }
                }
            }
        }
    }
}
