package dk.bollsjen.customplayerworlds.worldgeneration;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

public class WorldGenerator {

    public static boolean GenerateWorld(String name){
        try{
            WorldCreator wc = new WorldCreator(name);

            wc.environment(World.Environment.NORMAL);
            wc.type(WorldType.NORMAL);

            wc.createWorld();
        }catch (Exception e){
            Bukkit.getServer().getConsoleSender().sendMessage(e.getMessage());
            return false;
        }
        return true;
    }

    public static World GenerateWorld(String name, int seed){
        World world = null;

        return world;
    }

}
