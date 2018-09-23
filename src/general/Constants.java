package general;

/**
 *
 * @author patric
 */
public class Constants {
    public final static String USER_DIR = System.getProperty("user.home");
    public final static String DEFAULT_SETTINGS_PATH = 
            USER_DIR + "/.config/WacomUtil";
    public final static String DEFAULT_SETTINGS_FILENAME = "settings.txt";
    public final static String DEFAULT_SETTINGS_FILE = DEFAULT_SETTINGS_PATH 
            + "/" + DEFAULT_SETTINGS_FILENAME; 
}
