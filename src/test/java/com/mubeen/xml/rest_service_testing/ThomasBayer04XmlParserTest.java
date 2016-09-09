package com.mubeen.xml.rest_service_testing;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import junit.framework.TestCase;

public class ThomasBayer04XmlParserTest extends TestCase {

	public void testXMLParser() throws Exception {
		ThomasBayerGetService helperService = new ThomasBayerGetService();
		String response = helperService.sendGet("http://www.thomas-bayer.com/sqlrest");

		// since loadXMLFromString method is static, there is no need to create
		// xmlParser object
		Document doc = XMLParser.loadXMLFromString(response);
		doc.getDocumentElement().normalize();

		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		// Extracting individual node elements
		NodeList customerList = doc.getElementsByTagName("CUSTOMERList");
		Node clist = customerList.item(0);
		Element customerElement = (Element) clist;

		System.out.println("CustomerList link: " + customerElement.getAttribute("xlink:href"));
		System.out.println("CustomerList Text Content: " + customerElement.getTextContent());

		System.out.println("--------------------------");
		//learning to iterate over all the child-nodes
		Element rootElement = doc.getDocumentElement();
		NodeList children = rootElement.getChildNodes();
		Node current = null;
		int count = children.getLength();
		for (int i = 0; i < count; i++) {
			current = children.item(i);
			if (current.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) current;
				System.out.println(element.getAttribute("xlink:href") + " -> "+element.getTextContent());
			}
		}
	}
	
	public void testCustomerList() throws Exception{
		
		ThomasBayerGetService helperService = new ThomasBayerGetService();
		String response = helperService.sendGet("http://www.thomas-bayer.com/sqlrest/CUSTOMER/");
		
		Document doc = XMLParser.loadXMLFromString(response);
		doc.getDocumentElement().normalize();
		
		Element rootElement = doc.getDocumentElement();
		NodeList children = rootElement.getChildNodes();
		Node current = null;
		int count = children.getLength();
		for (int i = 0; i < count; i++) {
			current = children.item(i);
			if (current.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) current;
				System.out.println(element.getAttribute("xlink:href") + " -> "+element.getTextContent());
			}
		}
	}
	
	public void testCustomerData() throws Exception{
		ThomasBayerGetService helperService = new ThomasBayerGetService();
		String response = helperService.sendGet("http://www.thomas-bayer.com/sqlrest/CUSTOMER/");
		
		Document doc = XMLParser.loadXMLFromString(response);
		doc.getDocumentElement().normalize();
		
		Element rootElement = doc.getDocumentElement();
		NodeList children = rootElement.getChildNodes();
		Node current = null;
		int count = children.getLength();
		for (int i = 0; i < count; i++) {
			current = children.item(i);
			if (current.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) current;
//				System.out.println(element.getAttribute("xlink:href") + " -> "+element.getTextContent());
				String customerUrl = element.getAttribute("xlink:href");
				String customerResponse = helperService.sendGet(customerUrl);
				Document doc2 = XMLParser.loadXMLFromString(customerResponse);
				Element customerRootElement = doc2.getDocumentElement();
				NodeList custChildren = customerRootElement.getChildNodes();
				Node current2 = null;
				int count2 = custChildren.getLength(); //5
				//System.out.println("count2 = " + count2);
				for (int i2 = 0; i2 < count2; i2++) {
					current2 = custChildren.item(i2);
					if (current2.getNodeType() == Node.ELEMENT_NODE) {
						Element element2 = (Element) current2;
						System.out.print(element2.getTextContent() + ", ");
					}
				}
				System.out.println();
			}
		}
			
		
	}

}
