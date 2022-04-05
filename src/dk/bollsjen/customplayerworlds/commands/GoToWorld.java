package dk.bollsjen.customplayerworlds.commands;

import dk.bollsjen.customplayerworlds.customdatatypes.PlayerPositionRotation;
import dk.bollsjen.customplayerworlds.customdatatypes.Vector3;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

import dk.bollsjen.customplayerworlds.database.*;

public class GoToWorld implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(sender instanceof Player){
            Player player = (Player)sender;

            if(args.length > 0){
                if(Bukkit.getServer().getWorld(args[0]) != null){
                    World currentWorld = player.getWorld();
                    UUID UUID = player.getUniqueId();
                    String worldName = args[0];
                    Vector3 position = new Vector3(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
                    double rotation = (player.getLocation().getYaw() - 90) % 360;

                    DatabaseConnection.SetCoords(currentWorld.getName(),UUID.toString(), position, rotation);

                    PlayerPositionRotation newPosition = DatabaseConnection.GetCoords(worldName,UUID.toString());
                    World newWorld = Bukkit.getWorld(worldName);

                    if(newPosition != null){
                        player.teleport(new Location(newWorld, newPosition.x, newPosition.y, newPosition.z));
                    }
                    else
                        player.teleport(new Location(newWorld, newWorld.getSpawnLocation().getBlockX(),newWorld.getSpawnLocation().getBlockY(),newWorld.getSpawnLocation().getBlockZ()));
                }
            }
        }else
            return false;

        return true;
    }
}
