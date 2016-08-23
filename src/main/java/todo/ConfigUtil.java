package todo;
import lombok.extern.log4j.Log4j;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.helpers.Loader;
@Log4j
public class ConfigUtil {


	public static String getString(String key)   {
		try {
			PropertiesConfiguration config = new PropertiesConfiguration(Loader.getResource("appkey.properties"));
//		log.info( config.getString("private"));
//		log.info( config.getString("public"));
			return config.getString(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
