package crazydefenders;


import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;



public class Player extends Sprite implements Defaults{


    private final String izq = "/images/craftLeft.png";
    private final String der = "/images/craft.png";
    //private long beforeTime = System.currentTimeMillis();
    private ArrayList<Disparo> disparos;
    private int contadorDisparos;
    private int vida;


    public Player() {
    	vida = 3;
        ImageIcon ii = new ImageIcon(this.getClass().getResource(der));
        ImageIcon iii = new ImageIcon(this.getClass().getResource(izq));

        setDimensions(ii.getImage().getWidth(null), ii.getImage().getHeight(null)); 

        setImage(ii.getImage());
        
		setImageLeft(iii.getImage());
		
        setX(START_X);
        setY(START_Y);
    	disparos = new ArrayList<Disparo>();
    }
    
    public ArrayList<Disparo> getDisparos(){
    	return disparos;
    }
    
    public void moverDisparos()
    {
    	int len = disparos.size();
    	int i = 0;
		
		while (i  < len) {
			Disparo disparo = disparos.get(i);    	
			disparo.setX(disparo.getX() + (disparo.getDirection() * 4));
			if (disparo.getX() < 0 || disparo.getX() > ANCHO)
			{
				disparos.remove(i);
				len --;
				contadorDisparos --;
			}
			else
			{
				disparos.set(i, disparo);
				i++;
			}
				
		}
	}
    
    private void disparar(){
    	Disparo disparo = new Disparo(this.x + width/2, this.y + height/2, this.direction);

        disparos.add(disparo);
        contadorDisparos ++;
    }
    
    public boolean conDisparos(){
		return contadorDisparos > 0;
    	
    }
    
    private void dieDisparo(int i){
    	disparos.remove(i);
    	contadorDisparos --;
    }



    
    public void act() {

        x += dx;
        y += dy;
        if (dx < 0){
        	setDirection(-1);
        }
        if (dx > 0){
        	setDirection(1);
        }
    	
        
        if (x <= width) 
            x = width;
        if (x >= ANCHO - 2*width) 
            x = ANCHO - 2*width;
        if (y <= 0) 
            y = 0;
        if (y >= ALTO -10- height) 
            y = ALTO - 10- height;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            dx = -4;
        }

        if (key == KeyEvent.VK_RIGHT)
        {
            dx = 4;
        }
        if (key == KeyEvent.VK_UP)
        {
            dy = -4;
        }
        if (key == KeyEvent.VK_DOWN)
        {
            dy = 4;
        }
        
        
        if (key == KeyEvent.VK_SPACE)
        {
        	disparar();
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT)
        {
            dx = 0;
        }
        if (key == KeyEvent.VK_UP)
        {
            dy = 0;
        }
        if (key == KeyEvent.VK_DOWN)
        {
            dy = 0;
        }
    }

	public int getVida() {
		return vida;
	}

	public void addVida(int vida) {
		this.vida = vida;
	}
}