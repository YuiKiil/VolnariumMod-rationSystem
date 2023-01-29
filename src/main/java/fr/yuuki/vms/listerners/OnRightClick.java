package fr.yuuki.vms.listerners;

import fr.yuuki.vms.commands.Methode;
import fr.yuuki.vms.managers.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
public class OnRightClick implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        Player player = e.getPlayer();
        Action a = e.getAction();
        if(!PlayerManager.isInMod(player)) return;

        if(!(a == Action.PHYSICAL)) return;


        e.setCancelled(true);
        PlayerManager pm = PlayerManager.getFromPlayer(player);

        switch (player.getInventory().getItemInMainHand().getType()){
            case BLAZE_POWDER:
                new Methode().ModManage(player);
                break;
            case GRAY_DYE:
                if(!pm.isVanished()){
                    player.sendMessage("§6§lVolnarium §7>> §fVous êtes en vanish !");
                    pm.setVanished(true);
                    break;
                }
                player.sendMessage("§6§lVolnarium §7>> §fVous êtes plus en vanish !");
                pm.setVanished(false);
                break;
        }
    }


}
