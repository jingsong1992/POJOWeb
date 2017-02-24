package ce.user.LogType;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import ce.user.common.LogUtility;
import ce.user.common.Type;

public class CEDailyLog {
	
	public static void info(Object caller,Object message){
		Logger log = Logger.getLogger(LogUtility.getLogClassName(caller, Type.LOG_DailyLOG));
		DOMConfigurator.configure("test_log.xml");
		log.info(message);
	}
	
	public static void error(Object caller,Object message){
		Logger log = Logger.getLogger(LogUtility.getLogClassName(caller, Type.LOG_DailyLOG));
		DOMConfigurator.configure("test_log.xml");
		log.error(message);
	}
	
	public static void warn(Object caller,Object message){
		Logger log = Logger.getLogger(LogUtility.getLogClassName(caller, Type.LOG_DailyLOG));
		DOMConfigurator.configure("test_log.xml");
		log.warn(message);
	}
	
	public static void debug(Object caller,Object message){
		Logger log = Logger.getLogger(LogUtility.getLogClassName(caller, Type.LOG_DailyLOG));
		DOMConfigurator.configure("test_log.xml");
		log.error(message);
	}
	
	
	public void a(String message){
		info(this, message);
	}

	public static void main(String[] args) {
		CEDailyLog test = new CEDailyLog();
		test.a("ddddddd");
	}
}
