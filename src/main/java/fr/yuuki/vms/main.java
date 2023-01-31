package fr.yuuki.vms;

import fr.yuuki.vms.managers.EventsManager;
import fr.yuuki.vms.managers.PlayerManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class main extends JavaPlugin {

    private static main main;
    public ArrayList<UUID> moderateurs = new ArrayList<>();
    public HashMap<UUID, PlayerManager> players = new HashMap<>();
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