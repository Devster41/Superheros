package dev.cibmc.spigot.superPlugin;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {
    @Override
    public void onEnable() {
        Commands commands = new Commands();
        getLogger().info("Hello, SpimingotMC!");
        ItemManager.init();
        getCommand("giveglass").setExecutor(commands);
        getCommand("giveStaff").setExecutor(commands);
        getCommand("hilt").setExecutor(commands);
        getServer().getPluginManager().registerEvents(new GlassThrow(), this);
    }
    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }
}