class Line{
  private Vector2 start = new Vector2(0, 0);
  private Vector2 end = new Vector2(0, 0);
  private int width;

  public Line(int width){
    this.width = width;
  }

  public Line(Vector2 start, Vector2 end, int width){
    this.start = start;
    this.end = end;
    this.width = width;
  }
  
  public Line(Vector2 start, Vector2 end){
    this.start = start;
    this.end = end;
  }
  
  public Line(){
  }

  public Vector2 getStart(){
    return start;
  }

  public Vector2 getEnd(){
    return end;
  }

  public void setStart(Vector2 start){
    this.start = start;
  }

  public void setEnd(Vector2 end){
    this.end = end;
  }

  /*
  public boolean pointOnLine(Vector2 point){
    return (distance(point)<=width/2.0);
  }

  public double distance(Vector2 point){
    int partA = (end.getX()-start.getX())*(start.getY()-point.getY());
    int partB = (start.getX()-point.getX())*(end.getY()-start.getY());
    int partC = (int)(Math.pow(end.getX()-start.getX(),2));
    int partD = (int)(Math.pow(end.getY()-start.getY(),2));
    int partE = Math.abs(partA-partB);
    double partF = Math.sqrt(partC+partD);
    return partE/partF;
  }
  */
}