import java.awt.Graphics2D;

class EnemySpawner{
  private int x;
  private int y;
  private Wave wave;
  private int spacingCountDown = 0;
  private Path path;

  public EnemySpawner(int x, int y, Path path){
    this.x = x;
    this.y = y;
    this.path = path;
  }

  public void spawnEnemy(){
    if (wave != null){
      if (spacingCountDown == 0){
        if (wave.getEnemyPacket() != null){
          int temp = wave.getEnemyPacket().getSpacing();
        EnemyTemplate template = wave.getEnemyTemplate();
        if (template != null){
          Enemy enemy = new Enemy(template, path, x, y);
          Main.enemies.add(enemy);
          spacingCountDown = temp;
        }
        }
      }
    }
    if (spacingCountDown > 0) spacingCountDown--;
  }

  public void update(){
    spawnEnemy();
  }

  public void draw(Graphics2D graph2){
    
  }

  public void setWave(Wave wave){
    this.wave = wave;
  }

  public Wave getWave(){
    return wave;
  }

  public void setPath(Path path){
    this.path = path;
  }

  public Path getPath(){
    return path;
  }
}