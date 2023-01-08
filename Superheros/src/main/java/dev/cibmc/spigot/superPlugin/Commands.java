package dev.cibmc.spigot.superPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

        if (command.getName().equalsIgnoreCase("summonStaff")) {
            player.getInventory().addItem(ItemManager.healingStaff);
        }

        if (command.getName().equalsIgnoreCase("giveMic")) {
            player.getInventory().addItem(ItemManager.mic);
        }
        return false;
    }
    
}
