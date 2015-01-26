package simulator;

import java.awt.*;

public class Particle {
    public Particle(String ID, String name, Point[] positions, double r[], double m[]) {
        this.ID = ID;
        this.name = name;
        this.positions = positions;
        this.r = r;
        this.m = m;
    }
    
    public void show() {
        System.out.println(ID + "\t" + name + "\t" + r + "\t" + m);
        System.out.println("X\tY");
        for(Point position : positions)
            System.out.println(position.x + "\t" + position.y); 
    }
    
    public String ID, name;
    public Point[] positions;
    public double r[], m[];
}