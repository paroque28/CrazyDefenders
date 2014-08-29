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
    private int puntaje;
    private int vida;
    private int dX = 0;
    

    public void score(int points)
    {
    	puntaje += points;
    }
	public int getCx()
    {
    	return x+dX;
    }


    public int getDx() {
		return dX;
	}

	public Player() {
    	vida = VIDA;
        ImageIcon ii = new ImageIcon(this.getClass().getResource(der));
        ImageIcon iii = new ImageIcon(this.getClass().getResource(izq));

        setDimensions(ii.getImage().getWidth(null), ii.getImage().getHeight(null)); 

        setImage(ii.getImage());
        
		setImageLeft(iii.getImage());
		
        setX(START_X);
        setY(START_Y);
    	disparos = new ArrayList<Disparo>();
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
	    {
	        x = width;
	        setdX(-4);
	    }
	    else
	    {
	    	if (x >= ANCHO - 2*width)
	    	{
	    		x = ANCHO - 2*width;
	    		setdX(4);
	    	}
	    }
	    if (y <= 0) 
	        y = 0;
	    else
	    	if (y >= ALTO -10- height) 
	    		y = ALTO - 10- height;
	}
    private void setdX(int i) {
		this.dX += i;
		if (this.dX <=0)
			this.dX = 0;
		if (this.dX >= largo-ANCHO)
			this.dX = largo-ANCHO;
		
	}


	public void hurt() {
		this.vida --;
		if (vida <=0)
			this.setDying(true);
	}
	public void addVida(int vida) {
		this.vida = vida;
	}

	public int getVida() {
		return vida;
	}

	private void disparar(){
		Disparo disparo = new Disparo(this.x + width/2, this.y + height/2, this.direction);
	
	    disparos.add(disparo);
	    contadorDisparos ++;
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
			if (disparo.act())
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
    
    public boolean conDisparos(){
		return contadorDisparos > 0;
    	
    }
    
    public void dieDisparo(int i){
    	disparos.remove(i);
    	contadorDisparos --;
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
	public int getPuntaje() {
		return puntaje;
	}
}