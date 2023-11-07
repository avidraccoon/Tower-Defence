import java.util.ArrayList;

class Wave{
  private ArrayList<EnemyPacket> enemyPackets = new ArrayList<EnemyPacket>();
  private boolean finsihedSpawning;
  private int waveNum;
  private int enemyPacketIndex;
  private int enemyPacketSpawned;

  public Wave(int waveNum){
    this.waveNum = waveNum;
  }

  public EnemyPacket getEnemyPacket(){
    if (enemyPacketIndex < enemyPackets.size()){
      return enemyPackets.get(enemyPacketIndex);
    }
    return null;
  }
  
  public EnemyTemplate getEnemyTemplate(){
    if (enemyPackets.size() > enemyPacketIndex && enemyPacketSpawned < enemyPackets.get(enemyPacketIndex).getCount()){
      EnemyPacket packet = enemyPackets.get(enemyPacketIndex);
      EnemyTemplate template = packet.getEnemyTemplate();
      enemyPacketSpawned++;
      if (enemyPacketSpawned == packet.getCount()){
        enemyPacketIndex++;
        enemyPacketSpawned = 0;
      }
      return template;
    }
    return null;
  }

  public void addEnemies(EnemyTemplate template, int count, int Spacing){
    enemyPackets.add(new EnemyPacket(count, Spacing, template));
  }
}