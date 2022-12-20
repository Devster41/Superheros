package dev.cibmc.spigot.superPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Vibration;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.LlamaSpit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class GlassThrow implements Listener {
    
    @EventHandler
    public static void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() != null) {
                if (event.getItem().getItemMeta().equals(ItemManager.eldrichBlast.getItemMeta())) {
                    player.launchProjectile(LlamaSpit.class);
                    player.getWorld().playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 1);
                } else if (player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                    //if (player.getName() == "Michelle") {
                        Block block = player.getTargetBlock(null, 200);
                        if (block != null) {
                            Vibration vibration = new Vibration(player.getLocation(), new Vibration.Destination.BlockDestination(block), 40);
                            player.getWorld().spawnParticle(Particle.VIBRATION, player.getLocation(), 1, vibration);
                        }
                    //}
                }
            } 
        }
    }
}
