package dk.bollsjen.customplayerworlds.database;

import java.sql.*;
import dk.bollsjen.customplayerworlds.customdatatypes.*;
import org.bukkit.*;

public class DatabaseConnection {

    private static Connection Connection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3307/minecraftstuff", "root", "Nuster13");
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static boolean SetCoords(String name, String UUID, Vector3 coords, double rotation){
        Statement stmt = null;
        ResultSet rs = null;
        if(GetCoords(name, UUID) == null){
            try {
                stmt = Connection().createStatement();
            }catch (Exception e) {
                System.out.println(ChatColor.RED + "COORDS INSERT STMT ERROR: " + e);
                return false;
            }

            try {
                String sql = "INSERT INTO world_last_positions (UUID, world_name, player_x, player_y, player_z, player_rotation) VALUES ('"+UUID+"', '"+ name +"', "+coords.x+", "+coords.y+", "+coords.z+", "+rotation+")";
                    int effectedRows = stmt.executeUpdate(sql);
                    System.out.println(effectedRows + " effected rows for INSERTING\n"+sql);
            }catch (Exception e) {
                System.out.println(ChatColor.RED + "COORDS INSERT ERROR: " + e);
                return false;
            }
        }else{
            try {
                stmt = Connection().createStatement();
            }catch (Exception e) {
                System.out.println(ChatColor.RED + "COORDS UPDATE STMT ERROR: " + e);
                return false;
            }

            try {
                String sql = "UPDATE world_last_positions SET player_x = "+coords.x+", player_y = "+coords.y+", player_z = "+coords.z+", player_rotation = "+rotation+" WHERE UUID = '"+UUID+"' AND world_name = '"+name+"'";
                int effectedRows = stmt.executeUpdate(sql);
                System.out.println(effectedRows + " effected rows for UPDATING\n"+sql);
            }catch (Exception e) {
                System.out.println(ChatColor.RED + "COORDS UPDATE ERROR: " + e);
                return false;
            }
        }

        return true;
    }

    public static PlayerPositionRotation GetCoords(String name, String UUID){
        PlayerPositionRotation position = null;
        Rotation rotation = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = Connection().createStatement();
        }catch (Exception e) {
            System.out.println(ChatColor.RED + "COORDS GET STMT ERROR: " + e);
            return null;
        }

        try {
            rs = stmt.executeQuery("SELECT * FROM world_last_positions WHERE world_name = '" + name + "' AND UUID = '"+ UUID +"'");
        }catch (Exception e) {
            System.out.println(ChatColor.RED + "COORDS GET ERROR: " + e);
            return null;
        }

        try {
            position = new PlayerPositionRotation();
            System.out.println("Getting coords");
            int rows = 0;
            while (rs.next()) {
                rows ++;
                position.x = (rs.getInt(4));
                position.y = (rs.getInt(5));
                position.z = (rs.getInt(6));
                position.rotation = (rs.getInt(7));
            }
            if(rows == 0)
                position = null;
        }catch (Exception e) {
            System.out.println(ChatColor.RED + "COORDS GET RESULT ERROR: " + e);
            return null;
        }

        try {
            Connection().close();
            return position;
        }catch (Exception e){
            System.out.println(ChatColor.RED + "COORDS GET CLOSING ERROR: " + e);
            return null;
        }
    }

}
