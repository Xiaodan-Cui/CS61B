package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.*;

public class WorldGenerator {
    private TETile[][] world;
    KDTree roomlist;
    Queue<Point> Points=new LinkedList();
    int WIDTH;
    int HEIGHT;

    public void creatWorld(TETile[][] world, Random random) {
        WIDTH=world.length;
        HEIGHT=world[0].length;
        drawRooms(random,world);
        roomlist=new KDTree(Points);
        drawHallways(world);
        drawAgate(random,world);
        //ter.renderFrame(world);
    }
    public void drawAgate(Random random,TETile[][] world){
        while(true) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            if (canbegate(x, y,world)){
                world[x][y]=Tileset.LOCKED_DOOR;
                break;}
        }
    }
    public boolean canbegate(int x, int y,TETile[][] world){
        if (!world[x][y].equals(Tileset.WALL)){return false;}
        else if ( (y==0 || world[x][y-1].equals(Tileset.WALL))&& (y==HEIGHT ||world[x][y+1].equals(Tileset.WALL)) &&
                (x==0|| world[x-1][y].equals(Tileset.FLOOR)) && (x==WIDTH||world[x+1][y].equals(Tileset.NOTHING))){return true;}
        else if ( (y==0||world[x][y-1].equals(Tileset.WALL))&& (y==HEIGHT || world[x][y+1].equals(Tileset.WALL)) &&
                (x==WIDTH|| world[x+1][y].equals(Tileset.FLOOR)) && (x==0||world[x-1][y].equals(Tileset.NOTHING))){return true;}
        else if ( (x==0||world[x-1][y].equals(Tileset.WALL))&& (x==WIDTH||world[x+1][y].equals(Tileset.WALL)) &&
                (y==0||world[x][y-1].equals(Tileset.FLOOR)) && (y==WIDTH||world[x][y+1].equals(Tileset.NOTHING))){return true;}
        else if ( (x==0 ||world[x-1][y].equals(Tileset.WALL))&& (x==WIDTH||world[x+1][y].equals(Tileset.WALL) )&&
                (y==HEIGHT ||world[x][y+1].equals(Tileset.FLOOR)) && (y==0 ||world[x][y-1].equals(Tileset.NOTHING))){return true;}
        else{return false;}
    }

    public void drawAroom(room a,TETile[][] world) {
        for (int i =0; i < a.width; i++) {
            for (int j = 0; j < a.length; j++) {
                if (i == 0|| i == a.width-1 || j == 0 || j == a.length-1) {
                    world[a.pos.x+i][a.pos.y+j] = Tileset.WALL;
                }
                else {
                    world[a.pos.x+i][a.pos.y+j] = Tileset.FLOOR;
                }
            }
        }
    }
    public void drawRooms(Random random, TETile[][] world){
        int n=20+random.nextInt(15);
        for(int i=0;i<n;i++) {
            int w = 3 + random.nextInt(10);
            int l = 3 + random.nextInt(10);
            int x = random.nextInt(WIDTH-1);
            int y = random.nextInt(HEIGHT-1);
            if (checkpos(x,y,w,l,world)){
                position p= new position(x,y);
                room a=new room(p,w,l);
                Point point=new Point(p, a);
                Points.add(point);
                drawAroom(a,world);
            }
            else{
                i-=1;
            }
        }
    }
    public boolean checkpos(int x,int y, int w, int l,TETile[][] world){
        for (int j=x;j<=w+x;j++){
            for (int k=y;k<=l+y;k++){
                if (k>=HEIGHT|| j>=WIDTH || !world[j][k].equals(Tileset.NOTHING)){
                    return false;
                }
            }
        }
        return true;
    }
    public void drawHallways(TETile[][] world) {
        while (Points.size()!=0) {
            Point curr = Points.poll();
            curr.isConnected=true;
            Point np=roomlist.nearest(curr);
            if (np!=null){
            drawAhallway(curr.rm, np.rm,world);}
        }
    }
    public void drawAhallway(room r1,room r2,TETile[][] world){
        int r1cx= r1.pos.x+r1.width/2;
        int r1cy=r1.pos.y+r1.length/2;
        int r2cx= r2.pos.x+r2.width/2;
        int r2cy=r2.pos.y+r2.length/2;
        int leftx=Math.min(r1cx,r2cx);
        int rightx=Math.max(r1cx,r2cx);
        int upy=Math.max(r1cy,r2cy);
        int lowy=Math.min(r1cy,r2cy);
        for (int i=leftx;i<=rightx;i++){
            world[i][lowy]=Tileset.FLOOR;}
        for (int i=leftx-1;i<=rightx+1;i++){
            if(!world[i][lowy-1].equals(Tileset.FLOOR)){
                world[i][lowy-1]=Tileset.WALL;}
            if(!world[i][lowy+1].equals(Tileset.FLOOR)){
                world[i][lowy+1]=Tileset.WALL;}
        }
        int hx;
        if (r1cy>=r2cy){hx=r1cx;}
        else{hx=r2cx;}
        for (int j=lowy;j<=upy;j++){
            world[hx][j]=Tileset.FLOOR;}
        for (int j=lowy-1;j<=upy+1;j++){
            if (!world[hx-1][j].equals(Tileset.FLOOR)){
                world[hx-1][j]=Tileset.WALL;}
            if (!world[hx+1][j].equals(Tileset.FLOOR)){
                world[hx+1][j]=Tileset.WALL;}
        }
    }
}
