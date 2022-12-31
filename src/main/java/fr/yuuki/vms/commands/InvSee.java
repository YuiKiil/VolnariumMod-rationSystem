package fr.yuuki.vms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvSee implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("Uniquement un joueur peux executer les commandes de modération !");
            return false;
        }

        Player player = (Player) commandSender;

        if(s.equalsIgnoreCase("invsee")){
            if(!player.hasPermission("vol.mod.invsee")){
                player.sendMessage("§6§lVolnarium §7>> §c Tu n'as pas la permission d'executer cette commande !");
                return false;
            }

            i
        }

        return false;
    }
}
