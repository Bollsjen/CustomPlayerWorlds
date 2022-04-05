package dk.bollsjen.customplayerworlds.customdatatypes;

public class PlayerPositionRotation {
    public double x;
    public double y;
    public double z;
    public float rotation;
    public float pitch;

    public PlayerPositionRotation(){}

    public PlayerPositionRotation(double x, double y, double z, float rotation, float pitch){
        this.x = x;
        this.y = y;
        this.z = z;
        this.rotation = rotation;
        this.pitch = pitch;
    }
}
