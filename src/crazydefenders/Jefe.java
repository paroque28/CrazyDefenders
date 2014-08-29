package crazydefenders;

import javax.swing.ImageIcon;

public class Jefe extends Sprite{
	private int vida;
	private final String imagen = "/images/jefe.png";
	private int contador =200;
	private Disparo disparo = null;
	public Jefe(int x, int y)

	{
		ImageIcon ii = new ImageIcon(this.getClass().getResource(imagen));
		setImage(ii.getImage());
		this.setVisible(false);
		this.vida = 10;
		this.setX(x);
		this.setY(y);
        setDimensions(ii.getImage().getWidth(null), ii.getImage().getHeight(null)); 

	}

	public boolean bulletReceived() {
		this.vida -= 1;
		if (vida == 0)
			return true;
		else
			return false;
				
	}
	
	public void act(){
		
		if (this.contador>0){
			this.setX(x-this.direction);
			this.setY(y-this.direction);
			contador -= 1;
		}
		else{
			contador = MOVIMIENTO;
			this.setDirection(-this.direction);
		}
		dispare();
	}
	public void aparece(){
		this.setVisible(true);
	}

	public Disparo getDisparo() {
		return disparo;
	}

	private void dispare() {
		if (this.disparo == null)
			this.disparo=new Disparo(this.x , this.y+ this.getHeight()/2, -1);
		else
		{
			this.disparo.act();
			if (this.disparo.act())
				this.disparo = null;
		}
	}
	public void recibeDisparo(){
		this.disparo = null;
	}
	
}
