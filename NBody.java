import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

//this is the class that implements GUI
//requirement 3

public class NBody{

	private static final double G = 6.673e-11; // gravitational constant
	/**
     * @param args 
     */

    //calls and reads the info from the input.txt file
    public static void main(String[] args) {
        NBodyList list = new NBodyList("nbody_input.txt");
        new NBody(list);
    }

    //sets JFrame
    public NBody(NBodyList l) {
    	NBodyList list = l;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("NBody Simulation");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new NBodyPane(list));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class NBodyPane extends JPanel {

        private NBodyList list;
        private int [] colors;
        public NBodyPane(NBodyList l) {
        	this.list = l;
        	this.colors = new int[list.size()];
        	this.setColors();
            Timer timer = new Timer(1, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	moveBodies();
                    repaint();
                }
            });
            timer.start();

        }

        //randomly chooses a color for each celestial body
        public void setColors(){
        	for(int i = 0; i < colors.length; i ++){
        		colors[i] = ((int)(Math.random() * 0x1000000));
        	}
        }

        //requirement 4
        //uses the physics to calculate new velocities 
        public void calcNewVeloc(){
        	for(int i = 0; i < list.size(); i++){
        		NBodyNode iNode = list.get(i);
        		double mass = list.get(i).getMass();
        		double forceSumX = 0, forceSumY = 0;
        		for(int c = 0; c < list.size(); c++){
        			if(c != i){
        				NBodyNode cNode = list.get(c);
        				double scale = (double)list.scale();
        				double dist = calcDistance(iNode.getX() * scale, cNode.getX() * scale, iNode.getY() * scale, cNode.getY() * scale);
        				
        				double deltaX = ((cNode.getX()  * scale) - (iNode.getX() * scale)), deltaY = ((cNode.getY()  * scale) - (iNode.getY()  * scale));
        				double force = ((G * mass * list.get(c).getMass()) / (dist * dist));
        				
        				forceSumX = forceSumX + (force * (deltaX / dist));
        				forceSumY = forceSumY + (force * (deltaY / dist));
        				
        			} 
        		}
        		
        		double accelerationX = (forceSumX / mass);
        		double accelerationY = (forceSumY / mass);
        		
        		iNode.setXVel(list.get(i).getXVel() + accelerationX/list.scale());
        		iNode.setYVel(list.get(i).getYVel() + accelerationY/list.scale());
        		
        	}
        }

        public double calcDistance(double x_one, double x_two, double y_one, double y_two){
        	double deltaX = x_two - x_one;
        	double deltaY = y_two - y_one;

        	return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
        }

        protected void moveBodies(){
        	this.calcNewVeloc();
            for(int i = 0; i < list.size(); i++){
            	list.get(i).setX( list.get(i).getX() + list.get(i).getXVel());
            	list.get(i).setY( list.get(i).getY() + list.get(i).getYVel());
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(768, 768);
        }

        @Override
        protected void paintComponent(Graphics g) {
        	super.paintComponent(g);
	        Graphics2D g2d;
        	for(int i = 0; i < list.size(); i++){
	            g2d = (Graphics2D) g.create();
	            g2d.setColor(new Color(colors[i]));
	            g2d.fillOval((int)list.get(i).getX(), (int)list.get(i).getY(), (int)list.get(i).size(), (int)list.get(i).size());
	            g2d.dispose();
	        }
        }

    }
	
}
