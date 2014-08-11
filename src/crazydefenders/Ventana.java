package crazydefenders;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;


public class Ventana extends JPanel implements Runnable,  Defaults {
	private Dimension dimension;
    private ArrayList nativos;
    private Player player;
    
    private int deaths = 0;
    	
	private boolean playing = true;
	private final String explosion = "/images/explosion.png";
    private final String enemy = "/images/enemy.png";
    private final String xml = "/xml/levels.xml";
    
    private Thread animator;
    
    public Ventana() 
    {
        addKeyListener(new TAdapter());
        setFocusable(true);
        dimension = new Dimension(ANCHO, ALTO);
        setBackground(Color.black);

        init();
        setDoubleBuffered(true);
    }
    
    
    public void init() {
    	 nativos = new ArrayList();
    	 player = new Player();
    	 Xml dxml = new Xml(xml);
    	 
    	 if (animator == null || !playing) {
             animator = new Thread( this);
             animator.start();
         }    	 
    	 
    	
    }
    private void drawPlayer(Graphics screen) {

        if (player.isVisible()) {
            screen.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        if (player.isDying()) {
            player.die();
            playing = false;
        }
    }
    
    private void drawDisparo(Graphics screen) 
    {
    	if (player.conDisparos())
    	{
    		ArrayList disparos = player.getDisparos();
    		Iterator it = disparos.iterator();

    		while (it.hasNext()) {
    			Disparo disparo = (Disparo) it.next();

    			if (disparo.isVisible()) {
    				screen.drawImage(disparo.getImage(), disparo.getX(), disparo.getY(), this);
            }            
        }
    	}
    }
    
    public void paint(Graphics screen)
    {
      super.paint(screen);

      screen.setColor(Color.black);
      screen.fillRect(0, 0, dimension.width, dimension.height);
      if (playing) {
        drawPlayer(screen);
        drawDisparo(screen);
      }

      Toolkit.getDefaultToolkit().sync();
      screen.dispose();
    }

    private void animationCycle()  {
    	if (deaths == 12) {
            playing = false;
        }
    	
    	player.act();
    	
    	if (player.conDisparos())
    	{
    		player.moverDisparos();
    	}
    }
	@Override
	public void run() {
		long beforeTime, timeDiff, sleep;		
		beforeTime = System.currentTimeMillis();
		while (playing) {
            repaint();
            animationCycle();
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) 
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            beforeTime = System.currentTimeMillis();
        }		
	}
	private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {

          player.keyPressed(e);

          int x = player.getX();
          int y = player.getY();

          if (playing)
          {
            if (e.isAltDown()) {
            }
          }
        }
    }

}
