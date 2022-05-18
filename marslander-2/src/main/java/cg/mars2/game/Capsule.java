package cg.mars2.game;

public class Capsule extends Point {
    public double HS;
    public double VS;
    public int fuel;
    public double rotation;
    public int power;

    double save_x;
    double save_y;
    double save_HS;
    double save_VS;
    int save_fuel;
    double save_rotation;
    int save_power;


    public Capsule(double x, double y, int HS, int VS, int fuel, int rotation, int power) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.HS = HS;
        this.VS = VS;
        this.fuel = fuel;
        this.rotation = rotation;
        this.power = power;
    }


    public void apply(Move move) {
        this.rotation = this.rotation + move.rotate;
        

    }

    public void saveState() {
        this.save_x = x;
        this.save_y = y;
        this.save_HS = HS;
        this.save_VS = VS;
        this.save_fuel = fuel;
        this.save_rotation = rotation;
        this.save_power = power;
    }

    public void restoreState() {
        x = save_x;
        y = save_y;
        HS = save_HS;
        VS = save_VS;
        fuel = save_fuel;
        rotation = save_rotation;
        power = save_power;
    }
}
