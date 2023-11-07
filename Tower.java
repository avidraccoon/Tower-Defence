import java.awt.Graphics2D;
import java.awt.Color;

import java.util.ArrayList;

class Tower {
  private int x;
  private int y;
  private int width;
  private int height;
  private int projectileSpeed = 3;
  private int attackCooldown;
  private int attackDelay = 25;

  public Tower(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public int getX() {
    return x;
  }

  public int getY(){
    return y;
  }

  public int getWidth(){
    return width;
  }

  public int getHeight(){
    return height;
  }

  public int getProjectileSpeed(){
    return projectileSpeed;
  }

  public int getAttackCooldown(){
    return attackCooldown;
  }

  public void setProjectileSpeed(int projectileSpeed){
    this.projectileSpeed = projectileSpeed;
  }

  public void setAttackCooldown(int attackCooldown){
    this.attackCooldown = attackCooldown;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y){
    this.y = y;
  }

  public void setWidth(int width){
    this.width = width;
  }

  public void setHeight(int height){
    this.height = height;
  }


  public Projectile shoot(Enemy enemy) {
    if (attackCooldown <= 0) {
      PathFollower follower = enemy.getFollower();
      follower.sync();
      int x2 = follower.getTestX();
      int y2 = follower.getTestY();
      int count = 0;
      while (!(distance(new Vector2(x, y),new Vector2(x2, y2))>=count*projectileSpeed-3 && distance(new Vector2(x, y),new Vector2(x2, y2))<=count*projectileSpeed+3) && count<500){
        follower.testNext();
        count++;
        //System.out.println(count + " " + (count-1)*projectileSpeed + " " + (distance(new Vector2(x, y),new Vector2(x2, y2))));
        x2 = follower.getTestX();
        y2 = follower.getTestY();
      }
      if (count >= 400) return null;
      double vx = 500;
      double vy = 500;
      //System.out.println((x2-x) + " " + count);
      if (count != 0){
        vx = (x2-x)/(count*1.0);
        vy = (y2-y)/(count*1.0);
      }
      Projectile projectile = new Projectile(x, y, vx, vy);
      projectile.setDamage(1);
      attackCooldown = attackDelay;
      return projectile;
    }
    return null;
  }
  public double distance(Vector2 vector1, Vector2 vector2){
    return Math.sqrt(Math.pow(vector1.getX()-vector2.getX(), 2)+Math.pow(vector1.getY()-vector2.getY(), 2));
  }
  public void update(ArrayList<Enemy> enemies){
    Enemy closest = null;
    double dist = 0;
    for (Enemy enemy: enemies){
      if (closest == null || distance(new Vector2(this.getX(), this.getY()), enemy.getPos()) < dist){
        closest = enemy;
        dist = distance(new Vector2(this.getX(), this.getY()), enemy.getPos());
      }
    }
    attackCooldown--;
    if (closest != null){
      Projectile result = shoot(closest);
      if (result != null){
        Main.projectiles.add(result);
      }
    }
  }
  public void draw(Graphics2D g) {
    g.setColor(Color.RED);
    g.fillRect(x, y, width, height);
  }
}