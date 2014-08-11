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

public class World1 {
    private final String xml = "/xml/levels.xml";
    private ArrayList<Level> levels;

	
	
	
	public World1(){
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
				
				NodeList nlNativo = eLevel.getElementsByTagName("nativo");
				Node eJefe = eLevel.getLastChild();
				NodeList nlPunto = eLevel.getElementsByTagName("punto");
				NodeList nlBonus = eLevel.getElementsByTagName("bonus");
				NodeList nlReliquia = eLevel.getElementsByTagName("reliquia");

				
				
				for (int tempo = 0; tempo < nlNativo.getLength(); tempo++) 
				{
					Node nNativo = nlNativo.item(tempo);
					Element eNativo = (Element) nNativo;
					int x = Integer.parseInt(eNativo.getAttribute("x"));
					int y = Integer.parseInt(eNativo.getAttribute("y"));
					String type = eNativo.getAttribute("type");
					
					
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
				for (int tempo = 0; tempo < nlPunto.getLength(); tempo++) 
				{
					Node nPunto = nlPunto.item(tempo);
					Element ePunto = (Element) nPunto;
					int x = Integer.parseInt(ePunto.getAttribute("x"));
					int y = Integer.parseInt(ePunto.getAttribute("y"));
				}
				for (int tempo = 0; tempo < nlReliquia.getLength(); tempo++) 
				{
					Node nReliquia = nlReliquia.item(tempo);
					Element eReliquia = (Element) nReliquia;
					int x = Integer.parseInt(eReliquia.getAttribute("x"));
					int y = Integer.parseInt(eReliquia.getAttribute("y"));
					
				}
				for (int tempo = 0; tempo < nlBonus.getLength(); tempo++) 
				{
					Node nBonus = nlBonus.item(tempo);
					Element eBonus = (Element) nBonus;
					int x = Integer.parseInt(eBonus.getAttribute("x"));
					int y = Integer.parseInt(eBonus.getAttribute("y"));
					
				}

				
						
	 

			}
		}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
}
