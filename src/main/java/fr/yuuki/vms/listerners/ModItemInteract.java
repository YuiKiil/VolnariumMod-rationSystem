package fr.yuuki.vms.listerners;

import fr.yuuki.vms.commands.Methode;
import fr.yuuki.vms.managers.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;


public class ModItemInteract implements Listener {

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent e){
        Player player = e.getPlayer();
        if(!PlayerManager.isInMod(player)) return;

        if(!(e.getRightClicked() instanceof Player)) return;

        if(!(e.getHand().equals(EquipmentSlot.HAND))) return;
        

        Player target = (Player) e.getRightClicked();
        e.setCancelled(true);
        PlayerManager pm = PlayerManager.getFromPlayer(player);

        switch (player.getInventory().getItemInMainHand().getType()){
            case CHEST:
                player.performCommand("invsee " + target.getName());
                break;
            case PLAYER_HEAD:
                player.performCommand("manage " + target.getName());
                break;
            case BLUE_ICE:
                player.performCommand("freeze " + target.getName());
                break;
            default: break;
        }
    }

}
