package ce.user.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.xml.sax.SAXException;

import ce.user.bean.BookChapter;
import ce.user.bean.CEBook;
import ce.user.bean.EEBook;
import ce.user.bean.Library;
import ce.user.xml.GetXMLByDigester;

/**
 * Servlet implementation class GetStringServlet
 */
public class GetStringServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static CacheManager manager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStringServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cacheName = "CACHE_AMT";
		String key = "book.xmlPOJO";
		String fileName = "/book.xml";
		String fileNameRule = "/book_rule.xml";
		manager = CacheManager.create(GetStringServlet.class.getResource("/cache.xml"));
		Element element = GetStringServlet.getObjectFromCache(cacheName, key);
		if(element == null){
			try {
				Library library = GetStringServlet.parseXmlByDigesterRule(fileName, fileNameRule);
				GetStringServlet.getXMLStyle(library);
				GetStringServlet.pushObjectToCache(cacheName, key, library);
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				GetStringServlet.getXMLStyle((Library)(element.getObjectValue()));
			} catch (SAXException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void pushObjectToCache(String aCacheName,String aKey,Object aObj){
		try{  
			Cache cache = manager.getCache(aCacheName);
			Element element = new Element(aKey,aObj);
			cache.put(element);			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Element getObjectFromCache(String cacheName,String key){
		Cache cache = manager.getCache(cacheName);
		if(cache == null){
			return null;
		}
		Element element = cache.get(key);
		return element;	
	}
	
	public static Library parseXmlByDigesterRule(String fileName,String fileRuleName) throws Exception, SAXException{
		Digester digester = DigesterLoader.createDigester(GetStringServlet.class.getResource(fileRuleName));
		Library library = (Library)digester.parse(GetStringServlet.class.getResourceAsStream(fileName));
		return library;
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
