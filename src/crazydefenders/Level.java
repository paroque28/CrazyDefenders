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
	
	
	public Level(int tiempo)
	{
		this.setTime(tiempo);
	}
	
	public void addRastrero(int x, int y)
	{
		nativosRastreros.add(new Nativo(x,y,1));
	}
	
	public void addVolumen(int x, int y)
	{
		nativosVolumen.add(new Nativo(x,y,2));
	}
	
	public void addTele(int x, int y)
	{
		nativosTele.add(new Nativo(x,y,3));
	}
	public void addJefe(){
		System.out.println("falta jefe");
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
	

}
