package utils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:src/test/resources/${env}.properties"})
public interface Environment extends Config {
	@Key("App.Url")
	String appUrl();

	@Key("App.User")
	String appUserName();

	@Key("App.Pass")
	String appPassWord();

	@Key("DB.Host")
	String dbHostname();

	@Key("DB.User")
	String dbUsername();

	@Key("DB.Pass")
	String dbPassword();
}
