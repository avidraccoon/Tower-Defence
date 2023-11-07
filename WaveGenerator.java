import java.util.ArrayList;
class WaveGenerator{
  private ArrayList<Wave> waves = new ArrayList<Wave>();
  private int currentWave = 0;

  public WaveGenerator(){
    this.initializeWaves();
  }
  
  public WaveGenerator(int startWave){
    currentWave = startWave;
    this.initializeWaves();
  }
  
  public Wave getWave(int waveNum){ 
    if (currentWave < waves.size()){
      return waves.get(currentWave);
    }
    return null;
  }

  public void initializeWaves(){
    //Wave 1
    Wave wave1 = new Wave(1);
    wave1.addEnemies(new EnemyTemplate(0, 1, 1, 1), 1, 300);
    wave1.addEnemies(new EnemyTemplate(3, 2, 1, 1), 50, 5);
    wave1.addEnemies(new EnemyTemplate(25, 1, 1, 1), 10, 150);
    wave1.addEnemies(new EnemyTemplate(1, 3, 1, 1), 30, 50);
    waves.add(wave1);
  }
}