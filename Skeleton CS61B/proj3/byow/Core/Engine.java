package byow.Core;

import byow.InputDemo.InputSource;
import byow.InputDemo.KeyboardInputSource;
import byow.InputDemo.StringInputDevice;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.*;
import java.util.Random;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public static long SEED;
    public static Random rand;
    public static TETile[][] world;
    public static Avatar avatar;
    public static Font font;
    public static StringBuilder record;

    public Engine() {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) { for (int y = 0; y < HEIGHT; y += 1) {
            world[x][y] = Tileset.NOTHING;
        }
        }

        //TODO: Initialize random number generator
        record=new StringBuilder();
        rand =new Random(SEED);




    }

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {

        drawStartPage();
        while(true){
        InputSource source=new KeyboardInputSource();
            char action=source.getNextKey();
            avatarAction(action);}
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.
        StringInputDevice sid=new StringInputDevice(input);
        SEED=0;
        while(sid.possibleNextInput()){
            char next=sid.getNextKey();
            if (next=='N'|| next=='n'){
                break;
            }
        }
        while(sid.possibleNextInput()){
            char next=sid.getNextKey();
            if (next=='S'|| next=='s'){
                break;
            }
            SEED=SEED*10+Character.getNumericValue(next);
        }
        rand=new Random(SEED);
        WorldGenerator wg=new WorldGenerator();
        wg.creatWorld(world, rand);
        creatAavatar();
        ter.renderFrame(world);
        return world;
    }
    public void drawStartPage(){
        StdDraw.clear(StdDraw.BLACK);
        font = new Font("Monaco", Font.BOLD,60);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH/2,HEIGHT*3/4,"CS61B: THE GAME");
        font = new Font("Monaco", Font.BOLD,15);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH/2,HEIGHT*5/10,"New Game (N)");
        StdDraw.text(WIDTH/2,HEIGHT*4/10,"Load Game (L)");
        StdDraw.text(WIDTH/2,HEIGHT*3/10,"Quit (Q)");
        StdDraw.show();

    }
    public void creatAavatar(){
        while(true){
            int x=rand.nextInt(WIDTH);
            int y = rand.nextInt(HEIGHT);
            if (world[x][y]==Tileset.FLOOR){
                avatar=new Avatar();
                avatar.pos=new position(x, y);
                world[x][y]=Tileset.AVATAR;
                ter.renderFrame(world);
                break;
            }
        }
    }
    public void avatarAction(char c){

        if (c=='W' || c=='w'){
            record.append(c);
            if (world[avatar.pos.x][avatar.pos.y+1]==Tileset.FLOOR){
                world[avatar.pos.x][avatar.pos.y+1]=Tileset.AVATAR;
                world[avatar.pos.x][avatar.pos.y]=Tileset.FLOOR;
                avatar.pos=new position(avatar.pos.x, avatar.pos.y+1);
                ter.renderFrame(world);
            }
        }
        else if (c=='A'|| c=='a'){
            record.append(c);
            if (world[avatar.pos.x-1][avatar.pos.y]==Tileset.FLOOR){
                world[avatar.pos.x-1][avatar.pos.y]=Tileset.AVATAR;
                world[avatar.pos.x][avatar.pos.y]=Tileset.FLOOR;
                avatar.pos=new position(avatar.pos.x-1, avatar.pos.y);
                ter.renderFrame(world);
            }
        }
        else if (c=='S'|| c=='s'){
            record.append(c);
            if (world[avatar.pos.x][avatar.pos.y-1]==Tileset.FLOOR){
                world[avatar.pos.x][avatar.pos.y-1]=Tileset.AVATAR;
                world[avatar.pos.x][avatar.pos.y]=Tileset.FLOOR;
                avatar.pos=new position(avatar.pos.x, avatar.pos.y-1);
                ter.renderFrame(world);
            }
        }
        else if (c=='D' || c=='d'){
            record.append(c);
            if (world[avatar.pos.x+1][avatar.pos.y]==Tileset.FLOOR){
                world[avatar.pos.x+1][avatar.pos.y]=Tileset.AVATAR;
                world[avatar.pos.x][avatar.pos.y]=Tileset.FLOOR;
                avatar.pos=new position(avatar.pos.x+1, avatar.pos.y);
                ter.renderFrame(world);
            }
        }

       else if (c=='N' || c=='n'){

            record.append(c);
            InputSource source= new KeyboardInputSource();
            SEED=inputSeed(source);
            record.append(SEED);
            record.append('S');
            rand =new Random(SEED);
            WorldGenerator wg=new WorldGenerator();
            wg.creatWorld(world, rand);
            creatAavatar();
            ter.renderFrame(world);
        }
        else if (c=='L' || c=='l'){
            String s=load();
            if(s==""){
                System.exit(0);
            }
            interactWithInputString(s);
        }
        else if (c==':'){
            InputSource  source= new KeyboardInputSource();
            char next=source.getNextKey();
            if (next=='Q'|| next=='q'){
                String stringrecord=record.toString();
                save(stringrecord);
                System.exit(0);}
        }
    }
    public void drawSeed(String seed){
        StdDraw.clear(StdDraw.BLACK);
        font = new Font("Monaco", Font.BOLD,30);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH/2,HEIGHT*6/10,"Please give a seed,press 'S' to confirm ");
        StdDraw.text(WIDTH/2,HEIGHT*5/10,seed);
        StdDraw.show();
    }
    public long inputSeed(InputSource source){
        drawSeed("");
        long seed=0;
        while(source.possibleNextInput()){
            char next=source.getNextKey();
            if (next!='S'&& next!='s'){
                seed=seed*10+Character.getNumericValue(next);
                drawSeed(Long.toString(seed));
            }
            else{
                SEED=seed;
                break;
            }
        }
        return seed;
    }
    public void save(String s){
        File f= new File("./save_date.txt");
        try{
            if(!f.exists()){
            f.createNewFile();
            }
            FileOutputStream fs=new FileOutputStream(f);
            ObjectOutputStream os=new ObjectOutputStream(fs);
            os.writeObject(s);
        }
        catch (IOException e){
            System.out.println(e);
            System.exit(0);
        }

    }
    public String load(){
        File f= new File("./save_date.txt");
        if(f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                return (String) os.readObject();
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            }
            catch (ClassNotFoundException e) {
                System.out.println(e);
                System.exit(0);
            }

        }
        return "";

    }
    public static void main(String[] args){
        Engine eg=new Engine();
        eg.interactWithKeyboard();
        //InputSource source=new KeyboardInputSource();
        //eg.inputSeed(source);

    }

}
