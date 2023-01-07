package dev.cibmc.spigot.superPlugin;

import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.bukkit.entity.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
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
        
        switch (player.getDisplayName()) {

            case "Zippy":
            case "MatchstickReads":
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
                event.setJoinMessage("Hello Zippy! Enjoy the speed bonus - Maxis");
                break;
        
            case "Anne":
            case "jestercrow8557":
                event.setJoinMessage("Hello Anne, type /giveglass to use your powers - Maxis");
                break;
            
            
            case "Ilse":  
            case "HorcruxNo8":
                event.setJoinMessage("Hello Ilse, you may find you're alot stronger than the rest of us- Maxis");
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
                break;
                
            case "Devster41":
            case "Maxis":
                event.setJoinMessage("Hello Maxis. You have the power of lightning - Maxis");
                break;
            
            case "Aya":
            case "puzzledpiggy":
                event.setJoinMessage("Hello Aya, to use your healing powers you'll need a stick - Maxis");
                break;
            
            case "Kandreil":
            case "AcidicMoss34872":
                event.setJoinMessage("Hello Kandreil. You may find if someone hits you they will regret it - Maxis");
                player.performCommand("nick &2Kandreil");
                break;
            
            case "Amanda":
            case "UthirTheGr8":
                event.setJoinMessage("Hello Amanda! To use your powers, you must aquire a note block");
                player.performCommand("nick &5Amanda");
                break;

        }
    }

    @EventHandler
    public static void onSpawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        switch (player.getName()) {
            case "HorcruxNo8":
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
                break;
            
            case "MatchstickReads":
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
                break;
        }
    }

    @EventHandler 
    public static void onHit(EntityDamageByEntityEvent event) {
        if ((event.getDamager() instanceof Damageable) && (event.getEntity().getName().equals("AcidicMoss34872"))) {
            ((Damageable) event.getDamager()).damage(event.getDamage() / 2);
        }
    }

    @EventHandler
    public static void onClick(PlayerInteractEvent event) throws InterruptedException {
        Player player = event.getPlayer();

        //Amanda
        if (player.getName().equals("UthirTheGr8")) {
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                ItemStack i = new ItemStack(Material.NOTE_BLOCK);
                if (event.getItem().equals(i)) {
                    BlockIterator iterator = new BlockIterator(player, 15);
                    Block nextBlock = null;
                    Collection<Entity> blockNearby = null;
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

        if (player.getName().contains("puzzledpiggy")) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                ItemStack i = new ItemStack(Material.STICK);
                if (event.getItem().equals(i)) {
                    BlockIterator iterator = new BlockIterator(player, 15);
                    Block nextBlock = null;
                    Collection<Entity> blockNearby = null;
                    iterator.next();
                    while (iterator.hasNext()) {
                        nextBlock = iterator.next();
                        blockNearby = nextBlock.getLocation().getWorld().getNearbyEntities(nextBlock.getLocation(), 2, 2, 2);
                        player.spawnParticle(Particle.HEART, nextBlock.getLocation(), 10);
                        for (Entity tmp : blockNearby) {
                            if ((tmp instanceof Damageable) && (tmp instanceof Player)) {
                                if (((Damageable) tmp).getHealth() < 15.0) {
                                    ((Damageable) tmp).setHealth(((Damageable) tmp).getHealth() + 5.0);
                                } else {
                                    ((Damageable) tmp).setHealth(20.0);
                                }
                                ((Player) tmp).playSound(tmp.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            }
                        }
                        if (iterator.hasNext()) iterator.next();
                        if (iterator.hasNext()) iterator.next();
                        if (iterator.hasNext()) iterator.next();
                        Thread.sleep(50);
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
