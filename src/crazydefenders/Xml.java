package crazydefenders;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;



public class Xml {
	public Xml()
	{
		
	}
	
	public Xml(String file)
	{
		try{
			URL url = getClass().getResource(file);
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
				NodeList nlNativos = eLevel.getChildNodes();
				Node nNativo = nlNativos.item(0);
				Element eNativo = (Element) nNativo;
				NodeList nlRastreros = eNativo.getChildNodes();
				
				
						for (int tempo = 0; tempo < nlRastreros.getLength(); tempo++) 
						{
							Node nRastrero = nlRastreros.item(tempo);
							Element eRastrero = (Element) nRastrero;
							System.out.println(eRastrero.getAttribute("x"));
							
						}
				
						
	 

			}
		}
			catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
}

