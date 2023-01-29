package fr.yuuki.vms.listerners;

import fr.yuuki.vms.main;
import fr.yuuki.vms.managers.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;

public class ModCancels implements Listener {

    private static main instance = main.getInstance();

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e){
        e.setCancelled(PlayerManager.isInMod(e.getPlayer()) || main.getInstance().isFreeze(e.getPlayer()));
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        e.setCancelled(PlayerManager.isInMod(e.getPlayer()) || main.getInstance().isFreeze(e.getPlayer()));
    }

    @EventHandler
    public void onDamageItem(PlayerItemDamageEvent e){
        e.setCancelled(PlayerManager.isInMod(e.getPlayer()) || main.getInstance().isFreeze(e.getPlayer()));
    }

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent e){
        e.setCancelled(PlayerManager.isInMod(e.getPlayer()) || main.getInstance().isFreeze(e.getPlayer()));
    }

    @EventHandler
    public void onDamageEntity(EntityDamageEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        e.setCancelled(PlayerManager.isInMod((Player) e.getEntity()) || main.getInstance().isFreeze((Player) e.getEntity()));
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        e.setCancelled(PlayerManager.isInMod(e.getPlayer()) || main.getInstance().isFreeze(e.getPlayer()));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        e.setCancelled(PlayerManager.isInMod((Player) e.getWhoClicked()));
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(main.getInstance().isFreeze(e.getPlayer())){
            e.setTo(e.getFrom());
            e.getPlayer().sendMessage("§6§lVolnarium §7>> §eVous avez êtes freeze!");
        }
    }

}
