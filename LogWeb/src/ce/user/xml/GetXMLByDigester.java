package ce.user.xml;

import java.io.IOException;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.xml.sax.SAXException;

import ce.user.bean.BookChapter;
import ce.user.bean.CEBook;
import ce.user.bean.EEBook;
import ce.user.bean.Library;
import ce.user.xml.GetXMLByDigester;

public class GetXMLByDigester {

	
	public static void parseXmlByDigester(String fileName){	
		Digester digester = new Digester();
		Library library = null;
		digester.setValidating(false);
		digester.addObjectCreate("library", Library.class);
		digester.addSetProperties("library");
		digester.addObjectCreate("library/EEbook", EEBook.class);
		digester.addSetProperties("library/EEbook");
		digester.addObjectCreate("library/EEbook/chapter", BookChapter.class);
		digester.addBeanPropertySetter("library/EEbook/chapter/no");
		digester.addBeanPropertySetter("library/EEbook/chapter/caption");
		digester.addObjectCreate("library/CEbook", CEBook.class);
		digester.addSetProperties("library/CEbook");
		digester.addObjectCreate("library/CEbook/chapter", BookChapter.class);
		digester.addBeanPropertySetter("library/CEbook/chapter/no");
		digester.addBeanPropertySetter("library/CEbook/chapter/caption");
		digester.addSetNext("library/EEbook/chapter", "addBookChapter");
		digester.addSetNext("library/EEbook", "addEEBook");
		digester.addSetNext("library/CEbook/chapter", "addBookChapter");
		digester.addSetNext("library/CEbook", "addCEBook");
		try {
		library = (Library)digester.parse(GetXMLByDigester.class.getResourceAsStream(fileName));
		GetXMLByDigester.getXMLStyle(library);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void parseXmlByDigesterRule(String fileName,String fileRuleName) throws Exception, SAXException{
		Digester digester = DigesterLoader.createDigester(GetXMLByDigester.class.getResource(fileRuleName));
		Library library = (Library)digester.parse(GetXMLByDigester.class.getResourceAsStream(fileName));
		GetXMLByDigester.getXMLStyle(library);
	}
	
	
	public static void getXMLStyle(Library library) throws IOException, SAXException {

		System.out.println(" 图书馆: " + library.getName());
		System.out.println("共藏书: " +(library.getListEEBook().size()+library.getListCEBook().size()));		
		for(EEBook book : library.getListEEBook()){
			System.out.println("EE共有"+library.getListEEBook().size()+"本书");
			System.out.println("书名: "+book.getTitle()+"  作者: "+book.getAuthor());
			System.out.println(".........................");
			System.out.println("共 "+book.getListBookChapter().size()+"章");
			for(BookChapter bookChapter:book.getListBookChapter()){
				System.out.println(bookChapter.getNo()+":"+bookChapter.getCaption());
			}
			System.out.println("..................");
		}	
		for(CEBook book : library.getListCEBook()){
			System.out.println("CE共有"+library.getListCEBook().size()+"本书");
			System.out.println("书名: "+book.getTitle()+"  作者: "+book.getAuthor());
			System.out.println(".........................");
			System.out.println("共 "+book.getListBookChapter().size()+"章");
			for(BookChapter bookChapter:book.getListBookChapter()){
				System.out.println(bookChapter.getNo()+":"+bookChapter.getCaption());
			}
			System.out.println("..................");
		}
	}

}
