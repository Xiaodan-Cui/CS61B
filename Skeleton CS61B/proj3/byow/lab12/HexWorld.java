package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;

    public static void addHexagon(TETile[][] tiles, int size, int startX, int startY, TETile tiletype) {
        // fills in a block 14 tiles wide by 4 tiles tall
        int lx = 3 * size - 2;

        for (int y = 0; y < size; y += 1) {
            for (int x = 0; x < lx; x += 1) {
                if(x>size-y-2 && x<2*size+y-1) {
                tiles[startX + x][startY + y] = tiletype;}
            }
        }
        for (int y = size; y < 2*size; y += 1) {
            for (int x = 0; x < lx; x += 1) {
                if(x>y-size-1 && x<4*size-y-2) {
                    tiles[startX + x][startY + y] = tiletype;}
            }
        }

    }
    public static void HexTess(){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        addHexagon(world, 3,10,0,Tileset.MOUNTAIN);
        addHexagon(world, 3,5,3,Tileset.FLOWER);
        addHexagon(world, 3,15,3,Tileset.MOUNTAIN);
        addHexagon(world, 3,0,6,Tileset.GRASS);
        addHexagon(world, 3,10,6,Tileset.MOUNTAIN);
        addHexagon(world, 3,20,6,Tileset.SAND);
        addHexagon(world, 3,5,9,Tileset.MOUNTAIN);
        addHexagon(world, 3,15,9,Tileset.TREE);
        addHexagon(world, 3,0,6,Tileset.GRASS);
        addHexagon(world, 3,10,6,Tileset.MOUNTAIN);
        addHexagon(world, 3,20,6,Tileset.SAND);
        addHexagon(world, 3,5,9,Tileset.MOUNTAIN);
        addHexagon(world, 3,15,9,Tileset.TREE);
        addHexagon(world, 3,0,12,Tileset.GRASS);
        addHexagon(world, 3,10,12,Tileset.MOUNTAIN);
        addHexagon(world, 3,20,12,Tileset.TREE);
        addHexagon(world, 3,5,15,Tileset.MOUNTAIN);
        addHexagon(world, 3,15,15,Tileset.SAND);
        addHexagon(world, 3,0,18,Tileset.MOUNTAIN);
        addHexagon(world, 3,10,18,Tileset.MOUNTAIN);
        addHexagon(world, 3,20,18,Tileset.FLOWER);
        addHexagon(world, 3,5,21,Tileset.GRASS);
        addHexagon(world, 3,15,21,Tileset.FLOWER);
        addHexagon(world, 3,10,24,Tileset.TREE);

        ter.renderFrame(world);

    }




    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        HexTess();



        // draws the world to the screen

    }


}
