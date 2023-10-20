package concierto;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Tablero extends JPanel implements Runnable {

    private final Image background;
    private final Image door1;
    private final Image door2;
    private final Image door1Resize;
    private final Image door2Resize;
    private final Image boxoffice;
    private final Image boxofficeResize;
    private Image group1;
    private Image group2;
    private Image group1Resizable;
    private Image group2Resizable;
    private final Thread thread;
    private static final int MAX_CAPACITY = 1000;
    private int CURRENT_CAPACITY;
    private String TEXT_VARIABLE = "El numero de personas es: ";
    private static final int DOOR_WIDTH = 400;
    private static final int DOOR_HEIGHT = 200;
    //private static final int CONCERT_WIDTH = 300;
    private static final int CONCERT_HEIGHT = 220;
    private static final int BOXOFFICE_HEIGHT = 220;
    private static final int BOXOFFICE_WIDTH = 220;
    private static final int GROUP_HEIGHT = 100;
    private static final int GROUP_WIDTH = 100;

    private int x, y;

    public Tablero() {

        setBackground(Color.GRAY);
        setDoubleBuffered(true);
        background = new ImageIcon(this.getClass().getResource("../img/Concert.jpg")).getImage();
        boxoffice = new ImageIcon(this.getClass().getResource("../img/Boxoffice.png")).getImage();
        boxofficeResize = resizeImage(boxoffice, BOXOFFICE_WIDTH, BOXOFFICE_HEIGHT);
        door1 = new ImageIcon(this.getClass().getResource("../img/OpenDoor.png")).getImage();
        door1Resize = resizeImage(door1, DOOR_WIDTH, DOOR_HEIGHT);
        door2 = new ImageIcon(this.getClass().getResource("../img/OpenDoor.png")).getImage();
        door2Resize = resizeImage(door2, DOOR_WIDTH, DOOR_HEIGHT);
        x = 150;
        y = 700;
        peopleGroup1();
        peopleGroup2();
        thread = new Thread(this);
        thread.start();
    }

    void peopleGroup1() {
        group1 = new ImageIcon(this.getClass().getResource("../img/People.png")).getImage();
        group1Resizable = resizeImage(group1, GROUP_WIDTH, GROUP_HEIGHT);
    }

    void peopleGroup2() {
        group2 = new ImageIcon(this.getClass().getResource("../img/People.png")).getImage();
        group2Resizable = resizeImage(group2, GROUP_WIDTH, GROUP_HEIGHT);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(background, 0, 0, getWidth(), CONCERT_HEIGHT, this);

        int doorWidth = door1Resize.getWidth(this);
        int doorHeight = door1Resize.getHeight(this);
        int doorX1 = 0;
        int doorX2 = getWidth() - doorWidth;
        int doorY = getHeight() - doorHeight - 298;
        
        g2.drawImage(door1Resize, doorX1, doorY, this);
        g2.drawImage(door2Resize, doorX2, doorY, this);
        g2.drawImage(boxofficeResize, doorX1 + doorWidth / 2 - boxofficeResize.getWidth(this) / 2 + 318, doorY + doorHeight / 2 - boxofficeResize.getHeight(this) / 2, this);
        g2.drawImage(group1Resizable, x,y , this);
        g2.drawImage(group2Resizable,x+635,y , this);

        g2.setPaint(Color.WHITE); 
        g2.drawString(TEXT_VARIABLE, doorX1 + doorWidth / 2 + 100, doorY + doorHeight / 2 + 300);

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    private Image resizeImage(Image originalImage, int newWidth, int newHeight) {
        BufferedImage bufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();
        return bufferedImage;
    }
    public void ciclo(){
        y -= 200;
        if ( y < 300 ){
            y = 730;
            CURRENT_CAPACITY += 40;
        }
    }

    @Override
    public void run() {
        while (CURRENT_CAPACITY < MAX_CAPACITY) {
            ciclo();
            
            TEXT_VARIABLE = "El numero de personas es: " + CURRENT_CAPACITY;
            repaint();
            try {
                Thread.sleep(100);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
