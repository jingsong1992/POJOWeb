package ce.user.test;

import org.xml.sax.SAXException;
import ce.user.xml.GetXMLByDigester;


public class TestDigester {
	
	public static void main(String[] args) {
		try {
			GetXMLByDigester.parseXmlByDigesterRule("/book.xml", "/book_rule.xml");
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
