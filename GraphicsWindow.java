import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.ArrayList;

public class GraphicsWindow extends JFrame implements MouseListener{


    ArrayList<Path> paths = new ArrayList<Path>();
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    ArrayList<Tower> towers = new ArrayList<Tower>();
    ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    public GraphicsWindow(){

        this.setSize(200,200);
        this.setPreferredSize(new Dimension(200,200));
        this.setTitle("Drawing things");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new windowPanelGraphics(), BorderLayout.CENTER);
        this.setVisible(true);
        this.addMouseListener(this);

    }
    int[] mouseClickPos = {0, 0};

    public void mouseClicked(MouseEvent e){
      //mouseClickPos[0] = e.getX();
      //mouseClickPos[1] = e.getY()-25;
      Tower tower = new Tower(e.getX(), e.getY()-25, 10, 10);
      towers.add(tower);
      //System.out.println(e.getX()+" "+(e.getY()));
      repaint();
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    
  
    public class windowPanelGraphics extends JPanel {

        public windowPanelGraphics(){
            setLayout(new BorderLayout());
            this.setPreferredSize(new Dimension(200,200));
            this.add(new DrawStuff(), BorderLayout.CENTER);
            revalidate();
            repaint();
            this.setVisible(true); //probably not necessary

        }

        private class DrawStuff extends JComponent{
            private void paintPath(Graphics2D graph2, Path path){
              //graph2.setColor(Color.BLACK);
              path.draw(graph2);
            }
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D graph2 = (Graphics2D) g;

                graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    

                Shape rootRect = new Rectangle2D.Float(50, 50, 100, 100);

                //graph2.draw(rootRect);
                for (int i = 0; i<paths.size(); i++){
                  Path path = paths.get(i);
                  if (path.contains(mouseClickPos[0], mouseClickPos[1])){
                    graph2.setColor(Color.RED);
                  }else{
                    graph2.setColor(Color.BLACK);
                  }
                  paintPath(graph2, path);
                }
              Ellipse2D.Double circleBorder = new Ellipse2D.Double(mouseClickPos[0], mouseClickPos[1], 5, 5);
              graph2.draw(circleBorder);
              for (int i = 0; i<enemies.size(); i++){
                Enemy enemy = enemies.get(i);
                enemy.draw(graph2);
              }
              
              for (int i = 0; i<towers.size(); i++){
                Tower tower = towers.get(i);
                tower.draw(graph2);
              }
              
              for (int i = 0; i<projectiles.size(); i++){
                Projectile projectile = projectiles.get(i);
                projectile.draw(graph2);
              }
            }
        }
    }
}