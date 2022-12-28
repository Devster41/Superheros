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
    public static void onRightClick(PlayerInteractEvent event) throws InterruptedException {
        Player player = event.getPlayer();
        Block block = player.getTargetBlock(null, 200);

        if ((event.getAction() == Action.PHYSICAL) && (player.getDisplayName().equals("Devster41"))) {
            player.setWalkSpeed((float) 0.3);
        }

        if (event.getAction() == Action.LEFT_CLICK_AIR) {
            if (player.getDisplayName().equals("Devster41")) {
                ItemStack i = new ItemStack(Material.NOTE_BLOCK);
                if (event.getItem().getItemMeta().equals(i.getItemMeta()) && (Math.abs(player.getLocation().distanceSquared(block.getLocation())) > 20)) {
                    double midy = (block.getY() + player.getLocation().getY()) / 2;
                    double midx = (block.getX() + player.getLocation().getX()) / 2;
                    double midz = (block.getZ() + player.getLocation().getZ()) / 2;
                    double midmid = (player.getLocation().distanceSquared(block.getLocation()) / 4);

                    Location mid = new Location(player.getWorld(), midx, midy, midz);

                    Location tf = new Location(player.getWorld(), midx + midmid, 
                                                midy + midmid, 
                                                midz + midmid);

                    Location of = new Location(player.getWorld(), midx + midmid, 
                                                midy + midmid, 
                                                midz + midmid);

                    Collection<Entity> blockNearby = block.getLocation().getWorld().getNearbyEntities(block.getLocation(), 5, 5, 5);

                    player.spawnParticle(Particle.SONIC_BOOM, of, 50);
                    Thread.sleep(100);
                    player.spawnParticle(Particle.SONIC_BOOM, mid, 50);
                    Thread.sleep(100);
                    player.spawnParticle(Particle.SONIC_BOOM, tf, 50);
                    Thread.sleep(100);
                    player.spawnParticle(Particle.SONIC_BOOM, block.getLocation(), 50);
                    for (Entity tmp: blockNearby) {
                        if ((tmp instanceof Damageable) && !(tmp instanceof Player))
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
