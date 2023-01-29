package fr.yuuki.vms.commands;

import fr.yuuki.vms.main;
import fr.yuuki.vms.Utils.ItemBuilder;
import fr.yuuki.vms.managers.PlayerManager;
import net.minecraft.network.protocol.game.PacketPlayOutEntityHeadRotation;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Commands implements CommandExecutor {

    private main instance = main.getInstance();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("Uniquement un joueur peux executer les commandes de modération !");
            return false;
        }

        Player player = (Player) commandSender;

        if(s.equalsIgnoreCase("modtp")){
            if(!player.hasPermission("vol.mod.tp")) {
                player.sendMessage("§6§lVolnarium §7>> §c Tu n'as pas la permission d'executer cette commande !");
                return false;
            }

            if(strings.length < 1){
                player.sendMessage("§6§lModeration §7>> §fTu dois specifier un joueur !");
                return false;
            }

            String targetName = strings[0];

            if(Bukkit.getPlayer(targetName) == null){
                player.sendMessage("§6§lModeration §7>> §cLe joueur n'existe pas ou n'est pas connecté");

                return false;
            }

            Player target = Bukkit.getPlayer(targetName);

            if(!instance.moderateurs.contains(player.getUniqueId())){
                PlayerManager pm = new PlayerManager(player);
                main.getInstance().moderateurs.add(player.getUniqueId());
                pm.init();
                pm.saveInv();
                player.sendMessage("§6§lModeration §7>> §fTu as été mis en mode modérateur et téléporté a §a" + target.getName());
                pm.setVanished(true);
                player.teleport(target);
                return false;
            } else {
                PlayerManager pm = new PlayerManager(player);
                if(!pm.isVanished()){
                    pm.setVanished(true);
                    player.sendMessage("§6§lModeration §7>> §fTu as été mis en vanish et téléporté a §a" + target.getName());
                } else {
                    pm.setVanished(false);
                    player.sendMessage("§6§lModeration §7>> §fTu as été téléporté a §a" + target.getName());
                }
                player.teleport(target);
                return false;
            }
        }

        if(s.equalsIgnoreCase("mod")){
            if(!player.hasPermission("vol.mod.mod")){
                player.sendMessage("§6§lVolnarium §7>> §c Tu n'as pas la permission d'executer cette commande !");
                return false;
            }



            if(main.getInstance().moderateurs.contains(player.getUniqueId())){
                PlayerManager pm = PlayerManager.getFromPlayer(player);
                main.getInstance().moderateurs.remove(player.getUniqueId());
                player.sendMessage("§6§lModeration §7>> §fTu as été retiré du mode moderation");
                pm.removePlayer();
                return false;
            }

            PlayerManager pm = new PlayerManager(player);
            pm.init();

            main.getInstance().moderateurs.add(player.getUniqueId());
            player.sendMessage("§6§lModeration §7>> §fTu as été ajouter au mode moderation");
        }

        if(s.equalsIgnoreCase("report")){
            if(strings.length < 1 ){
                player.sendMessage("§c§lReport §7>> §cMerci de donner le pseudo du joueur");
                return false;
            }

            String targetName = strings[0];

            if(Bukkit.getPlayer(targetName) == null){
                player.sendMessage("§c§lReport §7>> §cLe joueur n'existe pas ou n'est pas connecté");
                return false;
            }

            Player target = Bukkit.getPlayer(targetName);

            Inventory menu = Bukkit.createInventory(null, 27, "§c§lReport §7>> §c" + target.getName());
            menu.setItem(11, new ItemBuilder(Material.IRON_SWORD).setName("§bTriche§8").setLore("§7Tout mod ou client de triche comme le fly par exemple").toItemStack());
            menu.setItem(12, new ItemBuilder(Material.RED_DYE).setName("§bAnti-Afk§8").setLore("§7Tout joueur utilisant des methodes pour eviter le kick pour afk").toItemStack());
            menu.setItem(13, new ItemBuilder(Material.BARRIER).setName("§bBug-Abuse§7").setLore("§7Tout joueur abusant de bugs").toItemStack());
            menu.setItem(14, new ItemBuilder(Material.FEATHER).setName("§bLanguage§6").setLore("§7Tout joueur ayant un language deplacer").toItemStack());
            menu.setItem(15, new ItemBuilder(Material.BLUE_BANNER).setName("§bAutre§2").setLore("§7Tout autres choses contre le reglement").toItemStack());
            player.openInventory(menu);
        }

        return false;
    }

}
