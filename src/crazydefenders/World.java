package crazydefenders;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class World {
    private final String xml = "/xml/levels.xml";
    private ArrayList<Level> levels;
    private int nlevels;
    private int nlevel;

	
	
	
	public World(){
		levels = new ArrayList<Level>();
		try{
			URL url = getClass().getResource(xml);
			File fXmlFile = new File(url.getPath());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
		
			doc.getDocumentElement().normalize();
	 
			NodeList nlLevel = doc.getElementsByTagName("level");

	 
			for (int temp = 0; temp < nlLevel.getLength(); temp++) 
			{
	 
				Node nLevel = nlLevel.item(temp);
				Element eLevel = (Element) nLevel;
				int tiempo = Integer.parseInt(eLevel.getAttribute("tiempo"));
				
				Level level = new Level(tiempo);
				levels.add(level);
				
				NodeList nlEles = eLevel.getChildNodes();

				
				
				for (int tempo = 0; tempo < nlEles.getLength(); tempo++) 
				{
					Node nE = nlEles.item(tempo);
					if (nE.getNodeName() == "#text") continue;
					Element e = (Element) nE;
					String name = e.getNodeName();
					int x = Integer.parseInt(e.getAttribute("x"));
					int y = Integer.parseInt(e.getAttribute("y"));
					if (name == "nativo")
					{
						String type = e.getAttribute("type");
			
						
						if (type.equals("rastrero"))
						{							
							level.addRastrero(x, y);
				    	}
				    	else
				    	{
				    		if (type.equals("volumen"))
				    		{
				    			level.addVolumen(x, y);
				    		}
				    		else
				    		{
				    			if (type.equals("tele"))
				    				level.addTele(x, y);
				    		}	
				    	}			    	
					}
					else
					{
						if (name.equals("reliquia"))
						{
							level.addReliquia(x, y);
						}
						else
						{
							if (name.equals("punto"))
							{
								level.addPunto(x, y);
							}
							else
							{
								if (name.equals("bonus"))
								{
									int vida = Integer.parseInt(e.getAttribute("vida"));
									level.addBonus(x, y, vida);
								}
								else
								{
									if (name.equals("jefe"))
									{
										level.addJefe(x,y);
										
									}
								}
							}
							
						}
					}
					
				}

				
						
	 

			}
		}
			catch (Exception e) {
				e.printStackTrace();
			}
		nlevels = levels.size();
		setnLevel(1);
	}
	public void act(Player player)
	{
		Level level = levels.get(nlevel-1);
		boolean value = level.act(player);
		levels.set(nlevel-1, level);
		if (value)
			nlevel ++;
	}
	
	public ArrayList<Nativo> getRastrero()
	{
		return levels.get(nlevel-1).getRastrero();
	}
	public Jefe getJefe()
	{
		return levels.get(nlevel-1).getJefe();
	}
	public ArrayList<Nativo> getVolumen()
	{
		return levels.get(nlevel-1).getVolumen();
	}
	public ArrayList<Nativo> getTele()
	{
		return levels.get(nlevel-1).getTele();
	}
	public ArrayList<int[]> getTerreno()
	{
		return levels.get(nlevel-1).getTerreno();
	}
	public int getLargo()
	{
		return levels.get(nlevel-1).getLargo();
	}
	



	private void setnLevel(int nlevel) {
		this.nlevel = nlevel;
	}
	public Boolean gameOver(){
		 return nlevel == nlevels;
	}
}
