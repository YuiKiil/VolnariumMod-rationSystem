package fr.yuuki.vms.listerners;

import fr.yuuki.vms.commands.Methode;
import fr.yuuki.vms.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ClickInv implements Listener {


    private Methode pm = new Methode();

    @EventHandler
    public void onClickInv (InventoryClickEvent e){
        if(e.getCurrentItem() == null) return;

        Player player = (Player) e.getWhoClicked();
        PlayerManager mod = PlayerManager.getFromPlayer(player);

        switch(e.getCurrentItem().getType()){
            case FEATHER:
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eGestion de la vitesse de vole")) {
                    e.setCancelled(true);
                    if(player.getFlySpeed() == (float) 0.2){
                        player.setFlySpeed((float) 0.3);
                    } else if (player.getFlySpeed() == (float) 0.3) {
                        player.setFlySpeed((float) 0.4);
                    } else if (player.getFlySpeed() == (float) 0.4) {
                        player.setFlySpeed((float) 0.5);
                    } else if (player.getFlySpeed() == (float) 0.5) {
                        player.setFlySpeed((float) 0.6);
                    } else if (player.getFlySpeed() >= (float) 0.6) {
                        player.setFlySpeed((float) 0.2);
                    }
                    player.closeInventory();
                    pm.ModManage(player);
                }
                break;
            case CHEST:
                /**
                 * Click InvSee
                 */
                e.setCancelled(true);
                if(e.getCurrentItem().getItemMeta().getDisplayName().startsWith("§eInventaire de §f")){
                    player.closeInventory();
                    String name = e.getView().getTitle().substring(19);
                    Player target = Bukkit.getPlayer(name);
                    OpenInv(player, target);
                    break;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().startsWith("§eInventaires de §f")) {
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "invsee " + e.getView().getTitle());
                    break;
                }
            case ENDER_CHEST:
                /**
                 * Click InvSee
                 */
                e.setCancelled(true);
                if(e.getCurrentItem().getItemMeta().getDisplayName().startsWith("§5EnderChest de §f")){

                    player.closeInventory();
                    String name = e.getView().getTitle().substring(19);
                    Player target = Bukkit.getPlayer(name);
                    OpenEnd(player, target);
                    break;
                }
                break;
            case ORANGE_DYE:
                e.setCancelled(true);
                player.closeInventory();
                break;
            case BLUE_ICE:
                e.setCancelled(true);
                player.closeInventory();
                player.performCommand("freeze " + e.getView().getTitle());
                break;
            case BOOK:
                e.setCancelled(true);
                player.closeInventory();
                player.sendMessage("§cReport pas implementer");
                break;
            case GREEN_DYE:
                String name = e.getView().getTitle();
                Player target = Bukkit.getPlayer(name);
                e.setCancelled(true);
                if(target.getGameMode() == GameMode.CREATIVE) {
                    target.setGameMode(GameMode.SURVIVAL);
                    target.sendMessage("§6§lModeration §7>> §fTu as été mis en gamemode §cSurvie §f!");
                } else if (target.getGameMode() == GameMode.SPECTATOR) {
                    target.setGameMode(GameMode.CREATIVE);
                    target.sendMessage("§6§lModeration §7>> §fTu as été mis en gamemode §cCréatif §f!");
                } else if (target.getGameMode() == GameMode.SURVIVAL || target.getGameMode() == GameMode.ADVENTURE) {
                    target.setGameMode(GameMode.SPECTATOR);
                    target.sendMessage("§6§lModeration §7>> §fTu as été mis en gamemode §cSpectateur §f!");
                }
                player.closeInventory();
                pm.playerInv(player, target);
                break;
            case TOTEM_OF_UNDYING:
                String nameto = e.getView().getTitle();
                Player targetto = Bukkit.getPlayer(nameto);
                e.setCancelled(true);
                if(targetto.isInvulnerable()){
                    targetto.setInvulnerable(false);
                    targetto.sendMessage("§6§lModeration §7>> §fTu as été mis en §cGod Mode §f!");
                } else {
                    targetto.setInvulnerable(true);
                    targetto.sendMessage("§6§lModeration §7>> §fTu as été retiré en §cGod Mode §f!");
                }
                player.closeInventory();
                pm.playerInv(player, targetto);
                break;
            case DIAMOND_BOOTS:
                e.setCancelled(true);
                if(player.getWalkSpeed() == (float) 0.2){
                    player.setWalkSpeed((float) 0.3);
                } else if (player.getWalkSpeed() == (float) 0.3) {
                    player.setWalkSpeed((float) 0.4);
                } else if (player.getWalkSpeed() == (float) 0.4) {
                    player.setWalkSpeed((float) 0.5);
                } else if (player.getWalkSpeed() == (float) 0.5) {
                    player.setWalkSpeed((float) 0.6);
                } else if (player.getWalkSpeed() >= (float) 0.6) {
                    player.setWalkSpeed((float) 0.2);
                }
                player.closeInventory();
                pm.ModManage(player);
                break;
            case GREEN_WOOL:
                player.setFlying(false);
                player.closeInventory();
                pm.ModManage(player);
                break;
            case RED_WOOL:
                player.setFlying(true);
                player.closeInventory();
                pm.ModManage(player);
                break;
            case GREEN_CANDLE:
                mod.setVanished(false);
                player.closeInventory();
                pm.ModManage(player);
                break;
            case RED_CANDLE:
                mod.setVanished(true);
                player.closeInventory();
                pm.ModManage(player);
                break;

            default: break;
        }


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
    public void OpenEnd(Player player, Player target){
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
