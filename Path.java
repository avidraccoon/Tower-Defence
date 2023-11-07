import java.util.ArrayList;


import java.awt.geom.Path2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Stroke;
  
class Path{
  private ArrayList<Vector2> points = new ArrayList<Vector2>();
  private BufferedImage image = null;
  private boolean keepImage = false;
  private int width;
  
  public ArrayList<Vector2> getPath(){
    return points;
  }
  
  public void addPoint(Vector2 vector){
    points.add(vector);
  }

  public void addPoint(int x, int y){
    points.add(new Vector2(x, y));
  }
  
  public void setWidth(int width){
    this.width = width;
  }

  public int getWidth(){
    return width;
  }

  public boolean getKeepImage(){
    return keepImage;
  }

  public void setKeepImage(boolean keepImage){
    this.keepImage = keepImage;
  }

  public void draw(Graphics2D g2){
    Stroke temp = g2.getStroke();
    Stroke stroke = new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    g2.setStroke(stroke);
    for (int i = 0; i<points.size()-1; i++){
      Vector2 v1 = points.get(i);
      Vector2 v2 = points.get(i+1);
      g2.drawLine(v1.getX(), v1.getY(), v2.getX(), v2.getY());
    }
    g2.setStroke(temp);
  }
  
  public void paintImage(){
    image = new BufferedImage(200, 200, BufferedImage.TYPE_4BYTE_ABGR);
    Graphics2D g2 = image.createGraphics();
    g2.setColor(Color.BLACK);
    draw(g2);
    g2.dispose();
  }

  public void clearImage(){
    image = null;
  }

  public boolean contains(int x, int y){
    if (image == null){
      paintImage();
    }
    int pixelColor = image.getRGB(x, y);
    if (keepImage == false){
      clearImage();
    }
    return (pixelColor == Color.BLACK.getRGB());
  }
  
}