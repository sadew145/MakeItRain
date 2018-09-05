package Smalltalk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class rain extends JPanel{
    private static final int W = 800;
    private static final int H = 600;
    
    HashSet<drops> drops;
    public rain() {
    	drops= new HashSet<drops>();
    	Random r = new Random();
    	for(int i = 0; i<1000; i++) {
    		int x=r.nextInt(W);
            int y=r.nextInt(200);
            int speed = r.nextInt(8)+6;
            int len = r.nextInt(11)+6;
    		drops.add(new drops(x,y,speed,len));
    	}
    		
        Timer timer = new Timer(45, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //for(drops d : drops) {
                 //   d.fall();}
                    repaint();
                //}
                
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(drops d : drops)
        {
        	d.fall();
        	d.drawDrops(g);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(W, H);
    }

    public class drops {       
        int x,y,speed,len;
         
        public drops(int x, int y, int speed, int len) {
        	this.x=x;
        	this.y=y;
        	this.speed=speed;
        	this.len=len;
        }
        
        public void drawDrops(Graphics g) {       	
        	g.setColor(new Color(0,0,255));
            g.drawLine(x, y, x, y+len);
            
        }

        public void fall() {
            if (y >= H) {
                y = 0;
            } else {
                y += speed;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.setTitle("rainhash");
                frame.add(new rain());
                frame.setBackground(new Color(135,206,250));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}