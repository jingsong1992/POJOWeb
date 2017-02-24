package ce.user.test;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import ce.user.common.LogUtility;
import ce.user.common.Type;

public class TestDailyLOG {

	
	public static void debug(Object caller,Object message){
		Logger log = Logger.getLogger(LogUtility.getLogClassName(caller, Type.LOG_DailyLOG));
		DOMConfigurator.configure("test_log.xml");
		log.info(message);
	}
	
	
	public void a(String message){
		TestDailyLOG.debug(this, message);
	}

	public static void main(String[] args) {
		TestDailyLOG test = new TestDailyLOG();
		test.a("ddddddd");
	}

}
