package fr.yuuki.vms.listerners;

import fr.yuuki.vms.managers.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;


public class ModItemInteract implements Listener {

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent e){
        Player player = e.getPlayer();
        if(!PlayerManager.isInMod(player)) return;

        if(!(e.getRightClicked() instanceof Player)) return;

        Player target = (Player) e.getRightClicked();
        e.setCancelled(true);

        switch (player.getInventory().getItemInMainHand().getType()){
            case CHEST:
                /**
                 * InvSee
                 */
                break;

            default: break;
        }
    }

}
