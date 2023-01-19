package dev.cibmc.spigot.superPlugin;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {
    @Override
    public void onEnable() {
        Commands commands = new Commands();
        getLogger().info("Enabling Superhero plugin");
        ItemManager.init();
        getCommand("giveglass").setExecutor(commands);
        getCommand("giveStaff").setExecutor(commands);
        getCommand("hilt").setExecutor(commands);
        getCommand("glassstack").setExecutor(commands);
        getServer().getPluginManager().registerEvents(new GlassThrow(), this);
    }
    @Override
    public void onDisable() {
        getLogger().info("Disabling Superhero plugin");
    }
}