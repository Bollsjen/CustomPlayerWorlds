package dk.bollsjen.customplayerworlds.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import dk.bollsjen.customplayerworlds.database.*;
import dk.bollsjen.customplayerworlds.customdatatypes.*;

public class DimensionChangeEvent implements Listener {

    @EventHandler
    public static void onPlayerChangedWorld(PlayerChangedWorldEvent event){
        Player player = event.getPlayer();
        PlayerPositionRotation position = DatabaseConnection.GetCoords(player.getWorld().getName(),player.getUniqueId().toString());
        if(position != null){
            Location location = player.getLocation();
            location.setYaw(position.rotation + 90);
            location.setPitch(position.pitch);
            player.teleport(location);
        }
    }

}
