import processing.core.PApplet;



public class DungeonGame extends PApplet {
	Maze m;
	
  
  public static void main(String[] args) {
    PApplet.main("DungeonGame");
  }

  public void settings() {
    size(800,600);
  }

  public void setup() {
   m = new Maze(this, 50);
   m.tiles[0]= loadImage("skull.png");
   m.tiles[1]= loadImage("1.png");
   m.tiles[2]= loadImage("2.png");
   m.tiles[3]= loadImage("3.png");
   m.tiles[4]= loadImage("4.png");
  
   m.walls[0]= loadImage("wall0.png");
   m.walls[1]= loadImage("wall1.png");
   m.walls[2]= loadImage("wall2.png");
   m.walls[3]= loadImage("wall3.png");
   
   m.torches[0]= loadImage("torch1.png");
   m.torches[1]= loadImage("torch2.png");
   m.torches[2]= loadImage("torch3.png");
   
   m.ceiling[0]=loadImage("ceilingLeftUp.png");
   m.ceiling[1]=loadImage("ceilingRightUp.png");
   m.ceiling[2]=loadImage("ceilingBottomLeft.png");
   m.ceiling[3]=loadImage("ceilingBottomRight.png");
   m.ceiling[4]=loadImage("ceilingMiddleLeft.png");
   m.ceiling[5]=loadImage("ceilingMiddleRight.png");
   m.ceiling[6]=loadImage("ceilingTop.png");
   m.ceiling[7]=loadImage("ceilingBottom.png");
  }

  public void draw() {
	  background(0);
	  frameRate(600);
	  m.generate(8, 8);
	  m.spawn();
  }
}
