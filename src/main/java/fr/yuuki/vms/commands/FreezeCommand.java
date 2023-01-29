package fr.yuuki.vms.commands;

import fr.yuuki.vms.main;
import fr.yuuki.vms.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FreezeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("Uniquement un joueur peux executer les commandes de modération !");
            return false;
        }

        Player player = (Player) commandSender;


        if(s.equalsIgnoreCase("freeze")) {
            // Check de perm
            if (!player.hasPermission("vol.mod.freeze")) {
                player.sendMessage("§6§lVolnarium §7>>§c Tu n'as pas la permission d'executer cette commande !");
                return false;
            }
            if (!PlayerManager.isInMod(player)) {
                player.sendMessage("§6§lVolnarium §7>>§c Tu dois être en mode modération !");
                return false;
            }

            // Check si joueur specifier
            if (strings.length < 1) {
                player.sendMessage("§6§lModeration §7>> §fTu dois specifier un joueur !");
                return false;
            }

            String targetName = strings[0];
            // Check si joueur existe
            if (Bukkit.getPlayer(targetName) == null) {
                player.sendMessage("§6§lModeration §7>> §cLe joueur n'existe pas ou n'est pas connecté");
                return false;
            }

            Player target = Bukkit.getPlayer(targetName);

            if(main.getInstance().getFrozenPlayers().containsKey(target.getUniqueId())){
                main.getInstance().getFrozenPlayers().remove(target.getUniqueId());
                target.sendMessage("§6§lVolnarium §7>> §eVous avez été unfreeze par §f" + player.getName());
                player.sendMessage("§6§lVolnarium §7>> §eVous avez unfreeze §f" + target.getName());
                return false;
            } else {
                main.getInstance().getFrozenPlayers().put(target.getUniqueId(), target.getLocation());
                target.sendMessage("§6§lVolnarium §7>> §eVous avez été freeze par §f" + player.getName());
                player.sendMessage("§6§lVolnarium §7>> §eVous avez freeze §f" + target.getName());
                return false;
            }

        }
        return false;
    }
}
