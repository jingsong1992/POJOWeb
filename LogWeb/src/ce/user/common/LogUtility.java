package ce.user.common;

public class LogUtility {
	public static String getLogClassName(Object caller, String strLogType) {
		StringBuffer sbfLoggerName = new StringBuffer();
		sbfLoggerName.append(strLogType);
		
		if (null != caller) {
			sbfLoggerName.append(".").append(caller.getClass().getName());
		}
			
		return sbfLoggerName.toString();
	}
}
