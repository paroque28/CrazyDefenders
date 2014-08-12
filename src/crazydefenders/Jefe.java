package crazydefenders;

public class Jefe extends Sprite{
	private int vida;
	private int velocidad;
	public Jefe(int v, int vida)
	{
		this.vida = vida;
		this.velocidad = v;
	}
	public int getVelocidad() {
		return velocidad;
	}

	
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida += vida;
	}
	
}
