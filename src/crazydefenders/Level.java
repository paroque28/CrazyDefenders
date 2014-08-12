package crazydefenders;

import java.util.ArrayList;

public class Level {
	private int time;
	
	private ArrayList<Nativo> nativosVolumen = new ArrayList<Nativo>();
	private ArrayList<Nativo> nativosTele = new ArrayList<Nativo>();
	private ArrayList<Nativo> nativosRastreros = new ArrayList<Nativo>();
	private ArrayList<int[]> terreno= new ArrayList<int[]>();
	private ArrayList<int[]> reliquias= new ArrayList<int[]>();
	private ArrayList<int[]> bonus= new ArrayList<int[]>();
	
	private Jefe jefe;
	
	
	public Level(int tiempo)
	{
		this.setTime(tiempo);
	}
	
	public void addRastrero(int x, int y)
	{
		nativosRastreros.add(new Nativo(x,y,1));
	}
	
	public ArrayList<Nativo> getRastrero()
	{
		return nativosRastreros;
	}
	
	public void addVolumen(int x, int y)
	{
		nativosVolumen.add(new Nativo(x,y,2));
	}
	
	public ArrayList<Nativo> getVolumen()
	{
		return nativosVolumen;
	}
	
	public void addTele(int x, int y)
	{
		nativosTele.add(new Nativo(x,y,3));
	}
	
	public ArrayList<Nativo> getTele()
	{
		return nativosTele;
	}
	public void addJefe(int v,int vida){
		jefe = new Jefe(v, vida);
	}
	public void addReliquia(int x, int y){
		int[] xy = {x,y};
		reliquias.add(xy);
	}
	public void addPunto(int x, int y){
		int[] xy = {x,y};
		terreno.add(xy);
	}
	public void addBonus(int x, int y, int bonus){
		int[] xyb = {x,y,bonus};
		this.bonus.add(xyb);
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public boolean act(Player player)
	{
		int x = player.getX();
		int y = player.getY();
		for (int t = 0; t < nativosRastreros.size(); t++)
		{
			Nativo ele = nativosRastreros.get(t);
			if(collisionPoint(ele, nativosRastreros, player, t))			
				ele.act(3, 0);
		}
		
		for (int t = 0; t < nativosVolumen.size(); t++)
		{
			Nativo ele = nativosVolumen.get(t);
			if(collisionPoint(ele, nativosVolumen, player, t))
			ele.act(2, 1);
		}
		for (int t = 0; t < nativosTele.size(); t++)
		{
			Nativo ele = nativosTele.get(t);
			if(collisionPoint(ele, nativosTele, player, t))
			ele.perseguir(x, y);
		}
		
		
		
		
		return !jefe.isVisible();
	}

	public Jefe getJefe() {
		return jefe;
	}
	
	private boolean collisionPoint(Nativo ele, ArrayList<Nativo> sprites, Player player, int i)
	{
		ArrayList< Disparo> disparos = player.getDisparos();
		if (ele.isDying())
		{
			sprites.remove(i);
			return false;
		}
		else
		{
			if (collision(ele.getX(),ele.getY(), ele.getWidth(), ele.getHeight(), player.getX(),player.getY(), player.getWidth(), player.getHeight()))
			{
				ele.setDying(true);
				player.hurt();
			}
			for (int c = 0 ; c < disparos.size(); c++)
			{
				Disparo disparo = disparos.get(c);
				if (collision(ele.getX(),ele.getY(), ele.getWidth(), ele.getHeight(), disparo.getX(),disparo.getY(), disparo.getWidth(), disparo.getHeight()))
				{
					ele.setDying(true);
					disparos.remove(c);
				}
				
			}
			return true;
		}
	}
	private boolean collision(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2 )
	{
		if ((calc(x1, x1 + w1, x2) || calc(x1, x1 + w1, x2+w2)) && (calc(y1, y1 + h1, y2) || calc(y1, y1 + h1, y2+h2)) )
			return true;
		else			
			return false;
		
	}
	private boolean calc(int x1, int x2, int med)
	{
		if (med >= x1)
			if (med <= x2)
				return true;
		return false;
	}
	
}

