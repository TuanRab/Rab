package touhou;

import sun.font.TrueTypeFont;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 * Created by huynq on 7/29/17.
 */
public class GameWindow extends Frame {

    private long lastTimeUpdate;
    private long currentTime;

    private Graphics2D windowGraphics;//

    private Graphics2D backbufferGraphics;//

    private BufferedImage backbufferImage;//

    private BufferedImage background;//

    private BufferedImage player;

    private int playerX = 384/2;//
    private int playerY = 400;
    private int backgroundY = 768;
    private int x;
    private int y;

    public GameWindow() {
        background = SpriteUtils.loadImage("assets/images/background/0.png");//
        player = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        setupGameLoop();
        setupWindow();
    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;
    }

    private void setupWindow() {
        this.setSize(384, 768);

        this.setTitle("Touhou - Remade by TuanPTIT");//

        this.setVisible(true);

        this.backbufferImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);//
        this.backbufferGraphics = (Graphics2D) this.backbufferImage.getGraphics();// alt enter ep kieu

        this.windowGraphics = (Graphics2D) this.getGraphics();//

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) { // Xu li nguoi dung an phim
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP :{
                        y=-2;
                        break;
                    }
                    case KeyEvent.VK_DOWN:{
                        y=5;
                        break;
                    }
                    case KeyEvent.VK_LEFT:{
                        x=-5;
                        break;
                    }
                    case KeyEvent.VK_RIGHT:{
                        x=5;
                        break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {// Xu li nguoi dung nha phim
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP :{
                        y=0;
                        break;
                    }
                    case KeyEvent.VK_DOWN:{
                        y=0;
                        break;
                    }
                    case KeyEvent.VK_LEFT:{
                        x=0;
                        break;
                    }
                    case KeyEvent.VK_RIGHT:{
                        x=0;
                        break;
                    }
                }

            }
        });
    }

    public void loop() {
        while(true) {
            if (lastTimeUpdate == -1) {
                lastTimeUpdate = System.currentTimeMillis();
            }
            currentTime = System.currentTimeMillis();
            if (currentTime - lastTimeUpdate > 17) {
                run();
                render();
                lastTimeUpdate = currentTime;//
            }
        }
    }
    
    private void Move(){
        playerX+=x;
        playerY+=y;
    }

    private void run() {
        Move();

    }

    private void render() {
        backbufferGraphics.setColor(Color.black);//
        backbufferGraphics.fillRect(0,0, 384 , 768 );//
        backbufferGraphics.drawImage(background, 0, backgroundY-3109, null);// anh phu
        backgroundY++;
        backbufferGraphics.drawImage(player, playerX, playerY, null);

        windowGraphics.drawImage(backbufferImage, 0, 0, null);// anh chinh

    }
}
