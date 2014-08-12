package crazydefenders;

import java.util.Random;

import javax.swing.ImageIcon;

public class Nativo extends Sprite implements Defaults {
	private final String r = "/images/nRastrero.png";
	private final String v = "/images/nVolumen.png";
	private final String t = "/images/nTele.png";
	int type; // 1: Rastrero, 2: Volumen, 3: Teledirigidos
	int contador;
	
	
    public Nativo(int x, int y, int tipo) {
    	Random gen = new Random();
        this.x = x;
        this.y = y;
        this.type = tipo;
        contador = gen.nextInt(30);
        ImageIcon ii = new ImageIcon(this.getClass().getResource(imagen()));
        setDimensions(ii.getImage().getWidth(null), ii.getImage().getHeight(null)); 

        setImage(ii.getImage());

    }
    private String imagen()
    {
    	String temp = t;
    	if (type == 1){
    		temp = r;
    	}
    	else{
    		if (type == 2)
        		temp = v;
    	}
    	
    	return temp;
    }

    public void act(int x, int y) {
    	if (contador > MOVIMIENTO)
    	{
    		this.setDirection(10);
    		contador = 0;
    	}
        setX(this.getX() + (x*this.getDirection()));
        setY(this.getY() + (y*this.getDirectionY()));
        contador++;
    }
    public void perseguir(int x, int y)
    {
    	if (y-this.y < 0)
    		setY(this.getY() - 1);
    	else
    		if (y-this.y > 0)
        		setY(this.getY() + 1);
    	if (x-this.x < 0)
    		setX(this.getX() - 1);
    	else
    		if (x-this.x > 0)
        		setX(this.getX() + 1);
    }





}