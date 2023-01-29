package fr.yuuki.vms.managers;

import fr.yuuki.vms.commands.Commands;
import fr.yuuki.vms.commands.FreezeCommand;
import fr.yuuki.vms.commands.InvSee;
import fr.yuuki.vms.commands.PlayerCommand;
import fr.yuuki.vms.listerners.ClickInv;
import fr.yuuki.vms.listerners.ModCancels;
import fr.yuuki.vms.listerners.ModItemInteract;
import fr.yuuki.vms.listerners.OnRightClick;
import fr.yuuki.vms.main;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;


public class EventsManager {


    public static void registers(){
        main instance = main.getInstance();
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new ClickInv(), instance);
        pm.registerEvents(new ModCancels(), instance);
        pm.registerEvents(new ModItemInteract(), instance);
        pm.registerEvents(new OnRightClick(), instance);

        instance.getCommand("mod").setExecutor(new Commands());
        instance.getCommand("report").setExecutor(new Commands());
        instance.getCommand("modtp").setExecutor(new Commands());
        instance.getCommand("invsee").setExecutor(new InvSee());
        instance.getCommand("manage").setExecutor(new PlayerCommand());
        instance.getCommand("freeze").setExecutor(new FreezeCommand());


    }
}
