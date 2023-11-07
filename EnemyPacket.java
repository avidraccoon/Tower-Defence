class EnemyPacket{
  private EnemyTemplate enemyTemplate;
  private int count;
  private int spacing;

  public EnemyPacket(int count, int spacing, EnemyTemplate enemyTemplate){
    this.enemyTemplate = enemyTemplate;
    this.count = count;
    this.spacing = spacing;
  }

  public EnemyTemplate getEnemyTemplate(){
    return enemyTemplate;
  }

  public int getCount(){
    return count;
  }

  public int getSpacing(){
    return spacing;
  }
}