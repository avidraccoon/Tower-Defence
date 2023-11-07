import javax.swing.Timer;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import java.util.ArrayList;

class Main {
  public static int targetFrame = 0;
  public static int calculatedFrame = 0;
  public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
  public static ArrayList<Path> paths = new ArrayList<Path>();
  public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
  public static ArrayList<Tower> towers = new ArrayList<Tower>();
  public static ArrayList<EnemySpawner> spawners = new ArrayList<EnemySpawner>();
  public static int startTime = 0;
  
  public static void update(ArrayList<Enemy> enemies, ArrayList<Projectile> projectiles, ArrayList<Tower> towers){
    for (int i = 0; i<enemies.size(); i++){
      Enemy enemy = enemies.get(i);
      enemy.update();
      if (enemy.getHealth() <= 0){
        enemies.remove(i);
        i--;
      }
    }
    
    for (int i = 0; i<projectiles.size(); i++){
      Projectile proj = projectiles.get(i);
      proj.update();
      if (proj.getX() < 0 || proj.getX() > 200 || proj.getY() < 0 || proj.getY() > 200){
        projectiles.remove(i);
        i--;
      }
      for (int j = 0; j<enemies.size(); j++){
        if (enemies.get(j).collides(proj)){
          projectiles.remove(i);
          i--;
          break;
        }
      }
    }

    
    for (int i = 0; i<towers.size(); i++){
      Tower tower = towers.get(i);
      tower.update(enemies);
      if (tower.getX() < 0 || tower.getX() > 200 || tower.getY() < 0 || tower.getY() > 200){
        towers.remove(i);
        i--;
      }
    }

    
    for (int i = 0; i<spawners.size(); i++){
      EnemySpawner spawner = spawners.get(i);
      spawner.update();
    }
  }

  public static void batchUpdate(int Count, ArrayList<Enemy> enemies, ArrayList<Projectile> projectiles, ArrayList<Tower> towers){
    for (int i = 0; i < Count; i++){
      update(enemies, projectiles, towers);
    }
  }

  public static void checkForUpdate(){
    Main.targetFrame = Main.calculateTargetFrame(20);
    if (Main.targetFrame != Main.calculatedFrame){
      //System.out.println(Main.targetFrame+" "+ Main.calculatedFrame);
      Main.batchUpdate(Main.targetFrame - Main.calculatedFrame, enemies, projectiles, towers);
      Main.calculatedFrame = Main.targetFrame;
    }
  }
  
  public static void main(String[] args) {
    System.out.println("Hello world!");
    Main.startTime = (int) System.currentTimeMillis();
    GraphicsWindow window = new GraphicsWindow();
    window.enemies = Main.enemies;
    window.paths = Main.paths;
    window.projectiles = Main.projectiles;
    window.towers = Main.towers;
    Path path = new Path();
    path.addPoint(new Vector2(10,20));
    path.addPoint(new Vector2(190,20));
    path.addPoint(new Vector2(190,70));
    path.addPoint(new Vector2(10,70));
    path.addPoint(new Vector2(10,120));
    path.addPoint(new Vector2(190,120));
    path.addPoint(new Vector2(190,170));
    path.addPoint(new Vector2(10,170));
    path.setWidth(15);
    Enemy enemy = new Enemy(100, 100, path);
    enemy.getFollower().setSpeed(1);
    //Tower tower = new Tower(40, 40, 10, 10);
    EnemySpawner spawner = new EnemySpawner(50, 50, path);
    Main.spawners.add(spawner);
    WaveGenerator waveGenerator = new WaveGenerator();
    Wave wave = waveGenerator.getWave(0);
    spawner.setWave(wave);
    //window.towers.add(tower);
    window.paths.add(path);
    window.enemies.add(enemy);
    window.repaint();
    int delay = 1000/20;
    ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            //...Perform a task...
            window.repaint();
             Main.checkForUpdate();
            //System.out.println(Main.projectiles.size());
            //Main.checkForUpdate();
          //if (Main.calculatedFrame%250 == 0){
          //  Enemy enemy = new Enemy(100, 100, path);
          //window.enemies.add(enemy);
          //}
        }
    };
    new Timer(delay, taskPerformer).start();
    
  }

  public static int calculateTargetFrame(int delay){
    int timeDiffrence = (int)(System.currentTimeMillis()-Main.startTime);
    return (int)(timeDiffrence/delay);
  }
}