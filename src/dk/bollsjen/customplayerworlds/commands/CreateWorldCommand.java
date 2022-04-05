package dk.bollsjen.customplayerworlds.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dk.bollsjen.customplayerworlds.worldgeneration.WorldGenerator;

public class CreateWorldCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(sender instanceof Player){
            Player player = (Player)sender;

            if(args.length > 0)
                if(WorldGenerator.GenerateWorld(args[0]))
                    player.sendMessage("World generated");
                else
                    player.sendMessage("Could not generate world");
            else
                return false;
        }else
            return false;

        return true;
    }
}
