package simulator;

import java.awt.*;
import javax.swing.*;

public class SimulatorPanel extends JPanel {
    
    public static void choose(int itteration) {
        Data.choose(itteration);
    }
   
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        data.paint(g2d);
    } 
    
    public Data data = new Data("/home/wiekonek/Documents/c_files/particles/build/output.txt", this);
}