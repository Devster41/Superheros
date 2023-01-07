package dev.cibmc.spigot.superPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BlockIterator;


public class GlassThrow implements Listener {
    public static void playNote(float pitch, Block nextBlock, Player player) throws InterruptedException {
        player.getWorld().playSound(nextBlock.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1.0F, 1.0F + pitch);
    }

    @EventHandler
    public void addSpeed(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage("Welcome to the SuperServer " + player.getDisplayName() + "!");
        switch (player.getDisplayName()) {
            case "Zippy":
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
                break;

            case "MatchstickReads":
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
                break;
            
            case "Anne":
                player.performCommand("giveglass");
                break;
            
            case "jestercrow8557":
                player.performCommand("giveglass");
                break;
            
            case "Ilse":
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
                break;
            
            case "HorcruxNo8":
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
                break;
                
            case "Devster41":
                player.performCommand("giveglass");
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
                break;

        }
    }

    @EventHandler
    public static void onClick(PlayerInteractEvent event) throws InterruptedException {
        Player player = event.getPlayer();

        //Amanda
        if (player.getDisplayName().equals("Maxis")) {
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                ItemStack i = new ItemStack(Material.NOTE_BLOCK);
                if (event.getItem().equals(i)) {
                    BlockIterator iterator = new BlockIterator(player, 15);
                    Block nextBlock = null;
                    Collection<Entity> blockNearby = null;
                    Random rand = new Random();
                    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
                    iterator.next();
                    while (iterator.hasNext()) {
                        nextBlock = iterator.next();
                        blockNearby = nextBlock.getLocation().getWorld().getNearbyEntities(nextBlock.getLocation(), 2, 2, 2);
                        player.spawnParticle(Particle.SONIC_BOOM, nextBlock.getLocation(), 10);
                        service.schedule(new NoteRunnable(player, nextBlock), 660, TimeUnit.MILLISECONDS);
                        for (Entity tmp : blockNearby) {
                            if ((tmp instanceof Damageable) && !(tmp instanceof Player))
                            ((Damageable) tmp).damage(3);
                        }
                        if (iterator.hasNext()) iterator.next();
                        if (iterator.hasNext()) iterator.next();
                        if (iterator.hasNext()) iterator.next();
                        Thread.sleep(115);
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
