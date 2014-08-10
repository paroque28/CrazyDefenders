package crazydefenders;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


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
	 
			NodeList nList = doc.getElementsByTagName("level");

	 
			for (int temp = 0; temp < nList.getLength(); temp++) {
	 
				Node nNode = nList.item(temp);
	 
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
	 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	 
					Element eElement = (Element) nNode;
	 
					//System.out.println("Staff id : " + eElement.getAttribute("x"));
					System.out.println("First Name : " + eElement.getElementsByTagName("nativos").item(0).getTextContent());
					//System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
					//System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
					//System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
	 
				}
			}
		}
			catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
}

