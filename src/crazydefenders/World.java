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
						
						
						if (type == "rastrero")
						{
							level.addRastrero(x, y);
				    	}
				    	else
				    	{
				    		if (type == "volumen")
				    		{
				    			level.addVolumen(x, y);
				    		}
				    		else
				    		{
				    			if (type == "tele")
				    				level.addTele(x, y);
				    		}	
				    	}			    	
					}
					else
					{
						if (name == "reliquia")
						{
							level.addReliquia(x, y);
						}
						else
						{
							if (name == "punto")
							{
								level.addPunto(x, y);
							}
							else
							{
								if (name == "bonus")
								{
									int vida = Integer.parseInt(e.getAttribute("vida"));
									level.addBonus(x, y, vida);
								}
								else
								{
									if (name == "jefe")
									{
										level.addJefe();
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
	}
	
	
}
