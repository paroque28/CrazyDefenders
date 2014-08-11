package crazydefenders;

import javax.swing.ImageIcon;

public class Nativo extends Sprite {
	private final String r = "/images/nRastrero.png";
	private final String v = "/images/nVolumen.png";
	private final String t = "/images/nTele.png";
	int type; // 1: Rastrero, 2: Volumen, 3: Teledirigidos 
	
	
    public Nativo(int x, int y, int tipo) {
        this.x = x;
        this.y = y;
        this.type = tipo;
        
        ImageIcon ii = new ImageIcon(this.getClass().getResource(imagen(tipo)));
        setDimensions(ii.getImage().getWidth(null), ii.getImage().getHeight(null)); 

        setImage(ii.getImage());

    }
    private String imagen(int x)
    {
    	String temp = t;
    	if (x == 1){
    		temp = r;
    	}
    	else{
    		if (x == 2)
        		temp = v;
    	}
    	
    	return temp;
    }

    public void act(int x, int y) {
        this.x += x;
        this.y += y;
    }





}