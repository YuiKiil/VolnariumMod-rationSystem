package fr.yuuki.vms.commands;

import fr.yuuki.vms.Utils.ItemBuilder;
import fr.yuuki.vms.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;

public class Methode {

    public boolean playerInv(Player player, Player target){
        /**
         * Player gestion menu
         *
         * Player info - player head
         * Freeze - Ice (A mettre dans le /mod dirrectement)
         * InvSee - Coffre
         * Report - Book
         * Retour - red Dye
         *
         * Gamemode - (item) [Admin Only]
         * Effect - (item) [Admin Only]
         * GodMod - (item) [Admin Only]
         * GestionInventaire - (item) [Admin Only]
         * Grade - NameTag [Admin Only] (permet de op uniquement si on est op)
         *
         * (Pour tout sous menu, mettre une option retour ramenand a ce menu)
         */

        ItemBuilder PlayerInfo = new ItemBuilder(Material.PLAYER_HEAD).setName("§r" + target.getName()).setLore(" ", "    §f- §7Vie: §c" + target.getHealth(), "  §f  - §7Faim: §6" + target.getFoodLevel(), "  §f  - §7Fly: §e" + target.isFlying(), "  §f  - §7Invulnerable: §e" + target.isInvulnerable());
        ItemBuilder Freeze = new ItemBuilder(Material.BLUE_ICE).setName("§3Freeze").setLore("§7Imobilise le joueur !");
        ItemBuilder Inv = new ItemBuilder(Material.CHEST).setName("§eInventaires de §f" + target.getName()).setLore("§7Ouvre les inventaires du joueur");
        ItemBuilder Report = new ItemBuilder(Material.BOOK).setName("§eReport").setLore("§7Ouvrir les reports en rapport avec les joueur (Reçu/Emis)");
        ItemBuilder Retour = new ItemBuilder(Material.ORANGE_DYE).setName("§cQuitter").setLore("§7Quitter le menu");


        if(player.hasPermission("vol.mod.playercommand.admin")){
            Inventory inv = Bukkit.createInventory(null, 3*9, target.getName());

            if(target.getGameMode() == GameMode.CREATIVE) {
                inv.setItem(10 ,new ItemBuilder(Material.GREEN_DYE).setName("§eGamemode de " + target.getName()).setLore("§7Gestion du Gamemode de §f" + target.getName(), "§7§c   - Gamemode Survie / Adventure", "§c   - Gamemode Spectateur", "§a   - Gamemode Créatif").toItemStack());
            } else if (target.getGameMode() == GameMode.SPECTATOR) {
                inv.setItem(10 ,new ItemBuilder(Material.GREEN_DYE).setName("§eGamemode de " + target.getName()).setLore("§7Gestion du Gamemode de §f" + target.getName(), "§c   - Gamemode Survie / Adventure", "§a   - Gamemode Spectateur", "§c   - Gamemode Créatif").toItemStack());
            } else if (target.getGameMode() == GameMode.SURVIVAL || target.getGameMode() == GameMode.ADVENTURE) {
                inv.setItem(10 ,new ItemBuilder(Material.GREEN_DYE).setName("§eGamemode de " + target.getName()).setLore("§7Gestion du Gamemode de §f" + target.getName(), "§a   - Gamemode Survie / Adventure", "§c   - Gamemode Spectateur", "§c   - Gamemode Créatif").toItemStack());

            }
            Collection<PotionEffect> effect = player.getActivePotionEffects();
            inv.setItem(12, new ItemBuilder(Material.POTION).setName("§eEffet de " + target.getName()).setLore(String.valueOf(effect)).toItemStack());

            if(target.isInvulnerable()){
                inv.setItem(14, new ItemBuilder(Material.TOTEM_OF_UNDYING).setName("§eInvulnerabiliter de " + target.getName()).setLore("§a    - Invulnérable", "§c    - Vulnérable").toItemStack());
            } else {
                inv.setItem(14, new ItemBuilder(Material.TOTEM_OF_UNDYING).setName("§eInvulnerabiliter de " + target.getName()).setLore("§c    - Invulnérable", "§a    - Vulnérable").toItemStack());
            }

            inv.setItem(16, new ItemBuilder(Material.NAME_TAG).setName("§cNon Implementé").toItemStack());
            inv.setItem(22, new ItemBuilder(Material.TRIDENT).setName("§cNon Implementé").toItemStack());

            inv.setItem(0, PlayerInfo.toItemStack());
            inv.setItem(2, Freeze.toItemStack());
            inv.setItem(4, Inv.toItemStack());
            inv.setItem(6, Report.toItemStack());
            inv.setItem(8, Retour.toItemStack());

            player.openInventory(inv);

            return false;

        } else {
            Inventory inv = Bukkit.createInventory(null, 9, target.getName());

            inv.setItem(0, PlayerInfo.toItemStack());
            inv.setItem(2, Freeze.toItemStack());
            inv.setItem(4, Inv.toItemStack());
            inv.setItem(6, Report.toItemStack());
            inv.setItem(8, Retour.toItemStack());

            player.openInventory(inv);

            return false;
        }
    }


    public boolean ModManage(Player player){

        Inventory inv = Bukkit.createInventory(null, 9, player.getName());

        PlayerManager pm = PlayerManager.getFromPlayer(player);

        if(player.getFlySpeed() <= (float) 0.2){
            inv.setItem(1, new ItemBuilder(Material.FEATHER).setName("§eGestion de la vitesse de vole").setLore("§a    - Speed 1", "§c    - Speed 2", "§c    - Speed 3", "§c    - Speed 4", "§c    - Speed 5+").toItemStack());
        } else if (player.getFlySpeed() == (float) 0.3) {
            inv.setItem(1, new ItemBuilder(Material.FEATHER).setName("§eGestion de la vitesse de vole").setLore("§c    - Speed 1", "§a    - Speed 2", "§c    - Speed 3", "§c    - Speed 4", "§c    - Speed 5+").toItemStack());
        } else if (player.getFlySpeed() == (float) 0.4) {
            inv.setItem(1, new ItemBuilder(Material.FEATHER).setName("§eGestion de la vitesse de vole").setLore("§c    - Speed 1", "§c    - Speed 2", "§a    - Speed 3", "§c    - Speed 4", "§c    - Speed 5+").toItemStack());
        } else if (player.getFlySpeed() == (float) 0.5) {
            inv.setItem(1, new ItemBuilder(Material.FEATHER).setName("§eGestion de la vitesse de vole").setLore("§c    - Speed 1", "§c    - Speed 2", "§c    - Speed 3", "§a    - Speed 4", "§c    - Speed 5+").toItemStack());
        } else if (player.getFlySpeed() >= (float) 0.6) {
            inv.setItem(1, new ItemBuilder(Material.FEATHER).setName("§eGestion de la vitesse de vole").setLore("§c    - Speed 1", "§c    - Speed 2", "§c    - Speed 3", "§c    - Speed 4", "§a    - Speed 5+").toItemStack());
        }

        if(player.getWalkSpeed() <= (float) 0.2){
            inv.setItem(3, new ItemBuilder(Material.DIAMOND_BOOTS).setName("§eGestion de la vitesse de marche").setLore("§a    - Speed 1", "§c    - Speed 2", "§c    - Speed 3", "§c    - Speed 4", "§c    - Speed 5+").toItemStack());
        } else if (player.getWalkSpeed() == (float) 0.3) {
            inv.setItem(3, new ItemBuilder(Material.DIAMOND_BOOTS).setName("§eGestion de la vitesse de marche").setLore("§c    - Speed 1", "§a    - Speed 2", "§c    - Speed 3", "§c    - Speed 4", "§c    - Speed 5+").toItemStack());
        } else if (player.getWalkSpeed() == (float) 0.4) {
            inv.setItem(3, new ItemBuilder(Material.DIAMOND_BOOTS).setName("§eGestion de la vitesse de marche").setLore("§c    - Speed 1", "§c    - Speed 2", "§a    - Speed 3", "§c    - Speed 4", "§c    - Speed 5+").toItemStack());
        } else if (player.getWalkSpeed() == (float) 0.5) {
            inv.setItem(3, new ItemBuilder(Material.DIAMOND_BOOTS).setName("§eGestion de la vitesse de marche").setLore("§c    - Speed 1", "§c    - Speed 2", "§c    - Speed 3", "§a    - Speed 4", "§c    - Speed 5+").toItemStack());
        } else if (player.getWalkSpeed() >= (float) 0.6) {
            inv.setItem(3, new ItemBuilder(Material.DIAMOND_BOOTS).setName("§eGestion de la vitesse de marche").setLore("§c    - Speed 1", "§c    - Speed 2", "§c    - Speed 3", "§c    - Speed 4", "§a    - Speed 5+").toItemStack());
        }

        if(player.isFlying()){
            inv.setItem(5, new ItemBuilder(Material.GREEN_WOOL).setName("§eGestion du Fly").setLore("§a    - Fly", "§c    - Pas Fly").toItemStack());
        } else {
            inv.setItem(5, new ItemBuilder(Material.RED_WOOL).setName("§eGestion du Fly").setLore("§c    - Fly", "§a    - Pas Fly").toItemStack());
        }

        if(pm.isVanished()){
            inv.setItem(7, new ItemBuilder(Material.GREEN_CANDLE).setName("§aVanish").toItemStack());
        } else {
            inv.setItem(7, new ItemBuilder(Material.RED_CANDLE).setName("§cVanish").toItemStack());
        }

        player.openInventory(inv);

        return false;
    }




}
