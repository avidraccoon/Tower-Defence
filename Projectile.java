import java.awt.Graphics2D;
import java.awt.Color;

class Projectile{
  private double x;
  private double y;
  private double vx;
  private double vy;
  private int damage;

  public Projectile(double x, double y, double vx, double vy){
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
  }

  public int getDamage(){
    return damage;
  }

  public void setDamage(int damage){
    this.damage = damage;
  }

  public double getX(){
    return x;
  }

  public double getY(){
    return y;
  }

  public double getVX(){
    return vx;
  }

  public double getVY(){
    return vy;
  }

  public void setX(double x){
    this.x = x;
  }

  public void setY(double y){
    this.y = y;
  }

  public void setVX(double vx){
    this.vx = vx;
  }

  public void setVY(double vy){
    this.vy = vy;
  }

  public void update(){
    x += vx;
    y += vy;
  }

  public void draw(Graphics2D graph2){
    //System.out.println(x + " " + y + " " + vx + " " + vy);
    graph2.setColor(Color.RED);
    graph2.fillOval((int) x, (int) y, 5, 5);
  }
}