class PathFollower{
  private int x;
  private int y;
  private int testX;
  private int testY;
  private Path path;
  private int speed;
  private int pathPos;
  private int pathSubpos;
  private int testPathPos;
  private int testPathSubpos;

  public PathFollower(int x, int y, Path path){
    this.x = x;
    this.y = y;
    this.path = path;
  }
  public void sync(){
    testPathPos = pathPos;
    testPathSubpos = pathSubpos;
  }

  public int getTestX(){
    return testX;
  }

  public int getTestY(){
    return testY;
  }
  
  public int getX(){
    return x;
  }

  public int getY(){
    return y;
  }

  public Vector2 getPos(){
    return new Vector2(x, y);
  }

  public Path getPath(){
    return path;
  }

  public int getSpeed(){
    return speed;
  }

  public void setSpeed(int speed){
    this.speed = speed;
  }

  public void setX(int x){
    this.x = x;
  }

  public void setY(int y){
    this.y = y;
  }

  public int getPOS(){
    return pathPos;
  }

  public int getSubpos(){
    return pathSubpos;
  }

  public void setPos(int pathPos){
    this.pathPos = pathPos;
  }

  public void setSubpos(int pathSubpos){
    this.pathSubpos = pathSubpos;
  }

  public void setPos(int x, int y){
    this.x = x;
    this.y = y;
  }

  public void setPos(Vector2 vector){
    this.x = vector.getX();
    this.y = vector.getY();
  }

  public void setPath(Path path){
    this.path = path;
    this.pathPos = 0;
    this.pathSubpos = 0;
  }

  public void next(){
    pathSubpos+=speed;
    //System.out.println(pathSubpos);
    while (pathPos+1<path.getPath().size() && distance(path.getPath().get(pathPos), path.getPath().get(pathPos+1))<=pathSubpos){
      pathSubpos-=distance(path.getPath().get(pathPos), path.getPath().get(pathPos+1));
      pathPos++;
    }
    if (pathPos+1<path.getPath().size()){
      x = (int) path.getPath().get(pathPos).getX()+(distanceX(path.getPath().get(pathPos), path.getPath().get(pathPos+1))/distance(path.getPath().get(pathPos), path.getPath().get(pathPos+1))*pathSubpos);
      y = (int) path.getPath().get(pathPos).getY()+(distanceY(path.getPath().get(pathPos), path.getPath().get(pathPos+1))/distance(path.getPath().get(pathPos), path.getPath().get(pathPos+1))*pathSubpos);
    }
  }
  public void testNext(){
    testPathSubpos+=speed;
    //System.out.println(pathSubpos);
    while (testPathPos+1<path.getPath().size() && distance(path.getPath().get(testPathPos), path.getPath().get(testPathPos+1))<=testPathSubpos){
      testPathSubpos-=distance(path.getPath().get(testPathPos), path.getPath().get(testPathPos+1));
      testPathPos++;
    }
    if (testPathPos+1<path.getPath().size()){
      testX = (int) path.getPath().get(testPathPos).getX()+(distanceX(path.getPath().get(testPathPos), path.getPath().get(testPathPos+1))/distance(path.getPath().get(testPathPos), path.getPath().get(testPathPos+1))*testPathSubpos);
      testY = (int) path.getPath().get(testPathPos).getY()+(distanceY(path.getPath().get(testPathPos), path.getPath().get(testPathPos+1))/distance(path.getPath().get(testPathPos), path.getPath().get(testPathPos+1))*testPathSubpos);
    }
  }
  public int distanceX(Vector2 pos1, Vector2 pos2){
    return (int) pos2.getX()-pos1.getX();
  }
  public int distanceY(Vector2 pos1, Vector2 pos2){
    return (int) pos2.getY()-pos1.getY();
  }
  public int distance(Vector2 pos1, Vector2 pos2){
    return (int) Math.sqrt(Math.pow(pos1.getX()-pos2.getX(), 2) + Math.pow(pos1.getY()-pos2.getY(), 2));
  }
}