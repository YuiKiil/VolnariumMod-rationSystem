package fr.yuuki.vms.commands;

import fr.yuuki.vms.Utils.ItemBuilder;
import fr.yuuki.vms.main;
import fr.yuuki.vms.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class InvSee implements CommandExecutor {


    private main instance = main.getInstance();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("Uniquement un joueur peux executer les commandes de modération !");
            return false;
        }

        Player player = (Player) commandSender;


        if(s.equalsIgnoreCase("invsee")){
            // Check de perm
            if(!player.hasPermission("vol.mod.invsee")){
                player.sendMessage("§6§lVolnarium §7>>§c Tu n'as pas la permission d'executer cette commande !");
                return false;
            }
            if(!PlayerManager.isInMod(player)){
                player.sendMessage("§6§lVolnarium §7>>§c Tu dois être en mode modération !");
                return false;
            }

            // Check si joueur specifier
            if(strings.length < 1){
                player.sendMessage("§6§lModeration §7>> §fTu dois specifier un joueur !");
                return false;
            }

            String targetName = strings[0];
            // Check si joueur existe
            if(Bukkit.getPlayer(targetName) == null){
                player.sendMessage("§6§lModeration §7>> §cLe joueur n'existe pas ou n'est pas connecté");
                return false;
            }

            Player target = Bukkit.getPlayer(targetName);
            // Création inv
            Inventory menu = Bukkit.createInventory(null, 3 * 9, "§eInventaires de §f" + target.getName());
            menu.setItem(12, new ItemBuilder(Material.CHEST).setName("§eInventaire de §f" + target.getName()).toItemStack());
            menu.setItem(14, new ItemBuilder(Material.ENDER_CHEST).setName("§5EnderChest de §f" + target.getName()).toItemStack());

            player.openInventory(menu);
        }

        return false;
    }

    /**
     * Ouvrir l'inv d'un joueur
     * @param player
     * @param target
     */
    public boolean OpenInv(Player player, Player target){
        Inventory inv = Bukkit.createInventory(null, 5*9, "Inventaire de " + target.getName());
        // Faire en methode et pas en commande
        for(int i = 0; i<36 ; i++){
            if(target.getInventory().getItem(i) != null){
                inv.setItem(i, target.getInventory().getItem(i));
            }
        }
        inv.setItem(36, target.getInventory().getHelmet());
        inv.setItem(37, target.getInventory().getChestplate());
        inv.setItem(38, target.getInventory().getLeggings());
        inv.setItem(39, target.getInventory().getBoots());

        player.openInventory(inv);

        return true;
    }

    /**
     * Ouvrir L'ec d'un joueur
     * @param player
     * @param target
     */
    public void OpenEnd(Player player, @NotNull Player target){
        Inventory inv = Bukkit.createInventory(null, 3*9, "EnderChest de " + target.getName());
        // Faire en methode et pas en commande
        for(int i = 0; i<27 ; i++){
            if(target.getEnderChest().getItem(i) != null){
                inv.setItem(i, target.getEnderChest().getItem(i));
            }
        }

        player.openInventory(inv);
    }
}
