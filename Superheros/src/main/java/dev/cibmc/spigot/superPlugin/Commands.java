package dev.cibmc.spigot.superPlugin;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use that command!");
            return true;
        }
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("giveglass")) {
            player.getInventory().addItem(ItemManager.eldrichBlast);
        }

        if (command.getName().equalsIgnoreCase("giveStaff")) {
            player.getInventory().addItem(ItemManager.slowStaff);
        }

        if (command.getName().equalsIgnoreCase("hilt")) {
            player.getInventory().addItem(ItemManager.hilt);
        }

        if (command.getName().equalsIgnoreCase("glassstack")) {
            ItemStack glassStack = new ItemStack(Material.GLASS, 64);
            player.getInventory().addItem(glassStack);
        }
        
        return false;
    }
    
}
