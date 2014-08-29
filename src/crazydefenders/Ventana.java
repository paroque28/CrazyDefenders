package crazydefenders;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;


public class Ventana extends JPanel implements Runnable,  Defaults {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4414099620631565310L;
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
    private void drawJefe(Graphics screen)
    {
    	Jefe jefe = world.getJefe();
    	if (jefe.isVisible()) {
            screen.drawImage(jefe.getImage(), jefe.getX(), jefe.getY(), this);
            if (jefe.getDisparo() != null)
            	screen.drawImage(jefe.getDisparo().getImage(), jefe.getDisparo().getX(), jefe.getDisparo().getY(), this);
        }
    }
    private void drawTerreno(Graphics screen)
    {
    	ArrayList<Reliquia> heart = world.getReliquia();
    	Iterator<Reliquia> it = heart.iterator();

		while (it.hasNext()) {
			Reliquia reliquia = (Reliquia) it.next();
			screen.drawImage(reliquia.getImage(), reliquia.getX()-player.getDx(), reliquia.getY(), this);
		}
    	screen.setColor(Color.white);
    	ArrayList<int[]> points = world.getTerreno();
    	for (int x = 1; x < points.size(); x++)
    	{
    		int[] xy1 = points.get(x-1);
    		int[] xy2 = points.get(x);
    		screen.drawLine(xy1[0]-player.getDx(),xy1[1],xy2[0]-player.getDx(), xy2[1]);
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
    			screen.drawImage(rastrero.getImage(), rastrero.getX()-player.getDx(), rastrero.getY(), this);
    		}
    		
    	}
    	
    	ArrayList<Nativo> vol = world.getVolumen();
    	if (vol.size() != 0)
    	{
    		for (int t=0; t<vol.size(); t++)
    		{
    			Nativo volumen = vol.get(t);
    			screen.drawImage(volumen.getImage(), volumen.getX()-player.getDx(), volumen.getY(), this);
    		}
    		
    	}
    	
    	
    	
    	ArrayList<Nativo> tel = world.getTele();
    	if (tel.size() != 0)
    	{
    		for (int t=0; t<tel.size(); t++)
    		{
    			Nativo tele = tel.get(t);
    			screen.drawImage(tele.getImage(), tele.getX()-player.getDx(), tele.getY(), this);
    		}
    		
    	}
    		
    }
    private void gameOver()
    {
    	String message = "Game Over, Your Score : " + player.getPuntaje();
        Graphics screen = this.getGraphics();

        screen.setColor(Color.black);
        screen.fillRect(0, 0, ANCHO, ALTO);

        screen.setColor(new Color(0, 32, 48));
        screen.fillRect(ANCHO/2 - 150, ALTO/2 - 30, 300, 60);
        screen.setColor(Color.white);
        screen.drawRect(ANCHO/2 - 150, ALTO/2 - 30, 300, 60);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        screen.setColor(Color.white);
        screen.setFont(small);
        screen.drawString(message, (ANCHO - metr.stringWidth(message))/2, 
            ALTO/2);
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
        drawJefe(screen);
    	drawTerreno(screen);
      }
    	  

      Toolkit.getDefaultToolkit().sync();
      screen.dispose();
    }

    private void animationCycle()  {
    	
    	player.act();
    	world.act(player);
    	player.setLargo(world.getLargo());
    	
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
		gameOver();
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
