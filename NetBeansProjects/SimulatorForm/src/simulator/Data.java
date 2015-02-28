package simulator;

import java.awt.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

public class Data {
    
    public Data(String file_name, SimulatorPanel simulator)
    {
//        File file;
//        final JFileChooser fc = new JFileChooser();
//        int returnVal = fc.showOpenDialog(null);
//
//        if (returnVal == JFileChooser.APPROVE_OPTION) {
//            file = fc.getSelectedFile();
//        } else {
//            return;
//        }
//        asdaasdasd

        File file = new File(file_name);
        FileInputStream input = null;
        try {
            input = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] bdata;
        bdata = new byte[(int) file.length()];
        try {
            input.read(bdata);
            input.close();
        } catch (IOException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        data = new String(bdata);
        
        
        for( int i = 0; i < (int) file.length(); i++ )
            if( data.charAt(i) == 't')
                itt++;
        
        pListCreator();
    }

    public static void choose(int itteration) {
        choosen = itteration;
    }
    
    public void paint(Graphics2D g) {
        for(Particle particle : particle_list) {
            if(particle.positions[choosen].x != -1) {
                g.setColor(Color.BLACK);
                if("PH".equals(particle.name))
                    g.setColor(Color.MAGENTA);
                if("DU".equals(particle.name))
                    g.setColor(Color.BLUE);
                if("DF".equals(particle.name))
                    g.setColor(Color.GREEN);
                if("FI".equals(particle.name))
                    g.setColor(Color.ORANGE);
                g.fillOval(particle.positions[choosen].x, particle.positions[choosen].y, (int)(particle.r[choosen]), (int)(particle.r[choosen]) );
            }
        }
    }
    
    public String getInfo(Point p, int n) {
        String ret = "";
        for(Particle particle : particle_list) {
            if(     p.x > particle.positions[n].x - particle.r[n] && 
                    p.x < particle.positions[n].x + particle.r[n] &&
                    p.y > particle.positions[n].y - particle.r[n] &&
                    p.y < particle.positions[n].y + particle.r[n] )
                ret = particle.ID + " " + particle.name + " " + particle.positions[n].x + " " + particle.positions[n].y + " " + particle.r[n] + " " + particle.m[n];
        }
        return ret;
    }
    
    public int max() {
        return itt;
    }

    private String[] createPoitnsList() {
        String ret = "";
        String[] lines, values;
        lines = data.split("\n");
        for(String line : lines) {
            values = line.split("\t");
            if( !values[0].isEmpty() && values[0].contains("p") && !ret.contains(values[0]) )
                ret = ret + values[0] + " ";
        }
        return ret.split(" ");
    }
    
    private void pListCreator() {
        
        String[] points, itterations, values;
        Point[] ppoints;
        double r[], m[];
        int i = 0, j = 0;
        
        points = createPoitnsList();
        
        itterations = data.split("t");
        
        for(String itteration : itterations) {
            itterations[i++] = itteration.replaceFirst("\\nk[\\S\\s]*", "");
        }
        
        particle_list = new Particle[points.length];
        
        for(String point : points) {
            i = 0;
            ppoints = new Point[itt+1];
            r = new double[itt+1];
            m = new double[itt+1];
            for(String itteration : itterations) {
                if(itteration.contains(point + "\t")) {
                    for(String line : itteration.split("\n")) 
                        if(line.contains(point + "\t")) {
                            r[i] = Double.parseDouble(line.split("\t")[4])*50;
                            m[i] = Double.parseDouble(line.split("\t")[5]);
                            ppoints[i++] = new Point((int)((Double.parseDouble(line.split("\t")[2]))*50), (int)((Double.parseDouble(line.split("\t")[3]))*50));
                        }
                } else {
                    r[i] = 0;
                    m[i] = 0;
                    ppoints[i++] = new Point (-1, -1); 
                }
            }
            
            for(String line : data.split("\n"))
                if(line.contains(point + "\t")) {
                    values = line.split("\t");
                    particle_list[j++] = new Particle(values[0], values[1], ppoints, r, m );
                    break;
                }
        }
        
    }
    

    private static int choosen;
    private String data;
    public Particle[] particle_list;
    public static int itt;
  
}