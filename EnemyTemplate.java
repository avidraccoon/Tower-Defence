class EnemyTemplate{
  private int health;
  private int speed;
  private int id;
  private int damage;
  
  public EnemyTemplate(int health, int speed, int id, int damage){
    this.health = health;
    this.speed = speed;
    this.id = id;
    this.damage = damage;
  }
  
  public int getHealth(){
    return health;
  }
  
  public int getSpeed(){
    return speed;
  }

  public int getId(){
    return id;
  }

  public int getDamage(){
    return damage;
  }

  public void setHealth(int health){
    this.health = health;
  }

  public void setSpeed(int speed){
    this.speed = speed;
  }

  public void setId(int id){
    this.id = id;
  }
  
}