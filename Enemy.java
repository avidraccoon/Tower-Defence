import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Color;


import java.util.ArrayList;

class Enemy{
  private PathFollower follower;
  private int health = 1;
  private int damage;
  
  
  public Enemy(int x, int y, Path path){
    follower = new PathFollower(x, y, path);
  }

  public Enemy(EnemyTemplate template, Path path, int x, int y){
    follower = new PathFollower(x, y, path);
    health = template.getHealth();
    damage = template.getDamage();
    follower.setSpeed(template.getSpeed());
  }

  public PathFollower getFollower(){
    return follower;
  }

  public int getHealth(){
    return health;
  }

  public void draw(Graphics2D graph2){
    //follower.next();
    //System.out.println(follower.getX() + " " + follower.getY());
    graph2.setColor(Color.GREEN);
    Ellipse2D.Double circleBorder = new Ellipse2D.Double(follower.getX()-3, follower.getY()-3, 5, 5);
    graph2.fill(circleBorder);
  }

  public boolean collides(Projectile proj){
    if (follower.getX() > proj.getX()-8 && follower.getX() < proj.getX()+8 && follower.getY() > proj.getY()-8 && follower.getY() < proj.getY()+8){
      health-=proj.getDamage();
      return true;
    }
    return false;
  }

  public Vector2 getPos(){
    return new Vector2(follower.getX(), follower.getY());
  }

  public void update(){
    follower.next();
  }
}