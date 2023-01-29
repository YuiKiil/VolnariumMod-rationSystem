package fr.yuuki.vms.listerners;

import fr.yuuki.vms.managers.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.net.http.WebSocket;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        if(PlayerManager.isInModerationMod(player)){
            PlayerManager.getFromPlayer(player).removePlayer();
        }
    }

}
