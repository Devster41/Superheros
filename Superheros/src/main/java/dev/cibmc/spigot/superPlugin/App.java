package dev.cibmc.spigot.superPlugin;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Hello, SpimingotMC!");
        ItemManager.init();
        getCommand("giveglass").setExecutor(new Commands());
    }
    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }
}