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

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;


public class Ventana extends JPanel implements Runnable,  Defaults {
	private Dimension dimension;
    private Player player;
    private World world;
    
    	
	private boolean playing = true;
	
    
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
    	 player = new Player();
    	 world = new World();
    	 
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
    		ArrayList<Disparo> disparos = player.getDisparos();
    		Iterator<Disparo> it = disparos.iterator();

    		while (it.hasNext()) {
    			Disparo disparo = (Disparo) it.next();

    			if (disparo.isVisible()) {
    				screen.drawImage(disparo.getImage(), disparo.getX(), disparo.getY(), this);
            }            
        }
    	}
    }
    private void drawNativos(Graphics screen)
    {
    	ArrayList<Nativo> rast = world.getRastrero();
    	if (rast.size() != 0)
    	{
    		for (int t=0; t<rast.size(); t++)
    		{
    			
    			Nativo rastrero = rast.get(t);
    			screen.drawImage(rastrero.getImage(), rastrero.getX(), rastrero.getY(), this);
    		}
    		
    	}
    	
    	ArrayList<Nativo> vol = world.getVolumen();
    	if (vol.size() != 0)
    	{
    		for (int t=0; t<vol.size(); t++)
    		{
    			Nativo volumen = vol.get(t);
    			screen.drawImage(volumen.getImage(), volumen.getX(), volumen.getY(), this);
    		}
    		
    	}
    	
    	
    	
    	ArrayList<Nativo> tel = world.getTele();
    	if (tel.size() != 0)
    	{
    		for (int t=0; t<tel.size(); t++)
    		{
    			Nativo tele = tel.get(t);
    			screen.drawImage(tele.getImage(), tele.getX(), tele.getY(), this);
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
        drawNativos(screen);
      }

      Toolkit.getDefaultToolkit().sync();
      screen.dispose();
    }

    private void animationCycle()  {
    	if (world.gameOver()) {
            playing = false;
        }
    	
    	player.act();
    	world.act(player);
    	
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
			animationCycle();
            repaint();
            
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

        }
    }

}
