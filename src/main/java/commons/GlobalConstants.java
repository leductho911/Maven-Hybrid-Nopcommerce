package commons;

import java.io.File;

public class GlobalConstants {

	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 10;

	public static final String UPLOAD_FILE = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;

	public static final String BROWSER_USERNAME = "tholeducmitscute_Tuy1BJ";
	public static final String BROWSER_AUTOMATE_KEY = "MV8DH7ALxc2Yvy8NQCxy";
	public static final String BROWSER_STACK_URL = "https://" + BROWSER_USERNAME + ":" + BROWSER_AUTOMATE_KEY + "@hub-apse.browserstack.com/wd/hub";

}
