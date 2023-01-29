package fr.yuuki.vms;

import fr.yuuki.vms.managers.EventsManager;
import fr.yuuki.vms.managers.PlayerManager;
import fr.yuuki.vms.managers.ReportManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class main extends JavaPlugin {

    private static main main;
    private static final AtomicInteger repID = new AtomicInteger();

    public ArrayList<UUID> moderateurs = new ArrayList<>();
    public HashMap<UUID, PlayerManager> players = new HashMap<>();
    public HashMap<Integer, ReportManager> reports = new HashMap<Integer, ReportManager>();
    private Map<UUID, Location> frozenPlayers = new HashMap<>();

    @Override
    public void onEnable() {
        main = this;

        getLogger().info("Starting ....");

        EventsManager.registers();
    }

    public static main getInstance() {
        return main;
    }

    public static int nextRepID(){
        return repID.getAndIncrement();
    }

    public Map<UUID, Location> getFrozenPlayers() {
        return frozenPlayers;
    }

    public boolean isFreeze(Player player){
        return getFrozenPlayers().containsKey(player.getUniqueId());
    }

    public ArrayList<UUID> getModerateurs() {
        return moderateurs;
    }
}