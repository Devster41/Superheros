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

        if (player.getDisplayName().contains("Zippy") || player.getDisplayName().contains("12")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
        } else if (player.getDisplayName().contains("Ilse") || player.getDisplayName().contains("crux")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
        }
    }

    @EventHandler
    public static void onSpawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (player.getDisplayName().contains("Zippy")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
        } else if (player.getDisplayName().contains("Ilse")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
        }
    }

    @EventHandler 
    public static void onHit(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();

        if (!(player.getDisplayName().contains("Kandreil"))) {
            return;
        }

        if (event.getDamager() instanceof Projectile) {
            player.launchProjectile(((Projectile) event.getDamager()).getClass());
        } else if (event.getDamager() instanceof Damageable) {
            ((Damageable) event.getDamager()).damage(event.getDamage() * 2);
        }
    }

    @EventHandler
    public static void onClick(PlayerInteractEvent event) throws InterruptedException {
        Player player = event.getPlayer();

        if (player.getDisplayName().contains("Zippy")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
            return;
        }

        if (player.getDisplayName().contains("Ilse")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
        }

        //Amanda
            if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (event.getItem() != null) {
                    ItemStack i = new ItemStack(Material.NOTE_BLOCK);
                    if (event.getItem().equals(i) && player.getDisplayName().contains("Amanda")) {
                        BlockIterator iterator = new BlockIterator(player, 15);
                        Block nextBlock = null;
                        Collection<Entity> blockNearby = null;
                        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
                        iterator.next();
                        while (iterator.hasNext()) {
                            nextBlock = iterator.next();
                            blockNearby = nextBlock.getLocation().getWorld().getNearbyEntities(nextBlock.getLocation(), 2, 2, 2);
                            player.spawnParticle(Particle.SONIC_BOOM, nextBlock.getLocation(), 2);
                            service.schedule(new NoteRunnable(player, nextBlock), 660, TimeUnit.MILLISECONDS);
                            for (Entity tmp : blockNearby) {
                                if ((tmp instanceof Damageable) && !(tmp instanceof Player))
                                ((Damageable) tmp).damage(8.0);
                            }
                            if (iterator.hasNext()) iterator.next();
                            if (iterator.hasNext()) iterator.next();
                            if (iterator.hasNext()) iterator.next();
                            Thread.sleep(115);
                    }
                }
            }
        }

        //Anne and Aya
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null) {
                if (player.getDisplayName().contains("Anne")) {
                    if (event.getItem().getItemMeta().equals(ItemManager.eldrichBlast.getItemMeta())) {
                        player.launchProjectile(LlamaSpit.class);
                        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 1);
                    }
                } else if (player.getDisplayName().contains("Aya")) {
                    ItemStack stick = new ItemStack(Material.STICK);
                    if (event.getItem().equals(stick)) {
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
                    } else if (event.getItem().getItemMeta().equals(ItemManager.slowStaff.getItemMeta())) {
                        Block block = player.getTargetBlock(null, 50);
                        if (block != null) {
                            Collection<Entity> entities = block.getLocation().getWorld().getNearbyEntities(block.getLocation(), 5, 5, 5);
                            player.spawnParticle(Particle.SMOKE_NORMAL, player.getEyeLocation(), 10);
                            player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1, 1);
                            Thread.sleep(100);
                            player.spawnParticle(Particle.DRAGON_BREATH, block.getLocation(), 100);
                            player.playSound(block.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, null, 1, 1);
                            PotionEffect effect = new PotionEffect(PotionEffectType.SLOW, 150, 3);
                            for (Entity tmp : entities) {
                                if ((tmp instanceof LivingEntity) && !(tmp.equals(player))) {
                                    ((LivingEntity) tmp).addPotionEffect(effect);
                                }
                            }
                        }
                    }
                } else if (event.getItem().getItemMeta().equals(ItemManager.hilt.getItemMeta())) {
                    Bukkit.getServer().dispatchCommand(player, "smite");
                }
            } 
        }
    }
}
