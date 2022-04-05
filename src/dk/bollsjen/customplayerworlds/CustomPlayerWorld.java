package dk.bollsjen.customplayerworlds;

import dk.bollsjen.customplayerworlds.commands.*;
import dk.bollsjen.customplayerworlds.events.DimensionChangeEvent;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomPlayerWorld extends JavaPlugin {

    @Override
    public void onEnable(){
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[CustomPlayerWorld] Plugin enabled");
        getServer().getPluginManager().registerEvents(new DimensionChangeEvent(), this);
        this.getCommand("genWorld").setExecutor(new CreateWorldCommand());
        this.getCommand("goToWorld").setExecutor(new GoToWorld());
    }

    @Override
    public void onDisable(){
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[CustomPlayerWorld] Plugin disabled");
    }

}
