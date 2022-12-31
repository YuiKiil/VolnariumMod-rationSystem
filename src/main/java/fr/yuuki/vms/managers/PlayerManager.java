package fr.yuuki.vms.managers;

import fr.yuuki.vms.Utils.ItemBuilder;
import fr.yuuki.vms.main;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerManager {

    private static main instance = main.getInstance();

    private Player player;
    private ItemStack[] items = new ItemStack[40];;
    private Location location;

    public PlayerManager(Player player){
        this.player = player;
    }

    public void init(){
        instance.players.put(player.getUniqueId(), this);
        this.location = player.getLocation();
        player.setGameMode(GameMode.SURVIVAL);
        player.setAllowFlight(true);
        player.setFlying(true);
        player.setFlySpeed((float) 0.1);

        this.saveInv();
        player.getInventory().clear();
        // TODO Mise auto en vanish
        /**
         * TODO give item moderation
         * InvManager (inv et ec) - Chest
         * PlayerManager (Tout se qui conserne le joueur) - PlayerHead
         * Moderation (Moderation général du server) - Book
         * ModérateurManager (Gamemode 0 ou 3, Speed, Fly, Vanish ?)
         * Sortie du mode Moderateur (tp au /mod)
         */

        ItemBuilder invManager = new ItemBuilder(Material.CHEST).setName("§eInventory Manager").setLore(" ", "§7Clique droit sur un joueur", "§7Pour voir son Inventaire ou son EnderChest.");
        ItemBuilder playerManager = new ItemBuilder(Material.PLAYER_HEAD).setName("§bPlayer Manager").setLore(" ", "§7Clique droit sur un joueur", "§7Pour pouvoir acceder a tous ce qui le concerne.");
        ItemBuilder moderation = new ItemBuilder(Material.BOOK).setName("§aGeneral Moderation").setLore(" ", "§7Clique droit pour ouvrir le menu", "§7Permet de géré la modération général du server.");
        ItemBuilder moderateurManager = new ItemBuilder(Material.BLAZE_POWDER).setName("§5Moderator Manager").setLore(" ", "§7Clique droit pour ouvrir le menu", "§7Permet de géré tout se qui concerne le modérateur.");
        ItemBuilder randomTP = new ItemBuilder(Material.ENDER_PEARL).setName("§dRandom TP").setLore(" ", "§7Clique droit pour se téléporter aléatoirement a un joueur");
        ItemBuilder exit = new ItemBuilder(Material.BARRIER).setName("§cExit").setLore(" ", "§7Clique droit pour sortir du mod modération");

        player.getInventory().setItem(0, invManager.toItemStack()); // InvManager (inv et ec) - Chest
        player.getInventory().setItem(1, playerManager.toItemStack()); // PlayerManager (Tout se qui conserne le joueur) - PlayerHead
        player.getInventory().setItem(3, moderation.toItemStack()); // Moderation (Moderation général du server) - Book
        player.getInventory().setItem(5, moderateurManager.toItemStack()); // ModérateurManager (Gamemode 0 ou 3, Speed, Fly, Vanish ?)
        player.getInventory().setItem(7, randomTP.toItemStack()); // RandomTp - téléporte a un joueur rdm (en vanish)
        player.getInventory().setItem(8, exit.toItemStack()); // Sortie du mode Moderateur (tp au /mod)
    }

    public void removePlayer(){
        instance.players.remove(player.getUniqueId());
        player.teleport(location);
        player.getInventory().clear();
        player.setGameMode(GameMode.SURVIVAL);
        player.setAllowFlight(false);
        player.setFlying(false);
        this.giveInv();
        // TODO Mise auto en dé-vanish
    }

    public static PlayerManager getFromPlayer(Player player){
        return instance.players.get(player.getUniqueId());
    }

    public ItemStack[] getItems() {
        return items;
    }

    public void saveInv(){
        for(int slot = 0; slot < 36; slot++){
            ItemStack item = player.getInventory().getItem(slot);
            if(item != null){
                items[slot] = item;
            }
        }

        items[36] = player.getInventory().getHelmet();
        items[37] = player.getInventory().getChestplate();
        items[38] = player.getInventory().getLeggings();
        items[39] = player.getInventory().getBoots();

        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        player.getInventory().clear();
    }

    public void giveInv() {
        player.getInventory().clear();
        for(int slot = 0; slot < 36; slot++){
            ItemStack item = items[slot];
            if(item != null){
                player.getInventory().setItem(slot, item);
            }
        }

        player.getInventory().setHelmet(items[36]);
        player.getInventory().setChestplate(items[37]);
        player.getInventory().setLeggings(items[38]);
        player.getInventory().setBoots(items[39]);
    }

    public static boolean isInMod(Player player){
        return instance.moderateurs.contains(player.getUniqueId()); // TODO modifier toutes les condition
    }
}
