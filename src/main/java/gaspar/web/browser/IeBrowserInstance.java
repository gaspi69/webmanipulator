package gaspar.web.browser;

import java.net.URL;

import gaspar.web.browser.BrowserInstance;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.DisposableBean;

@Slf4j
public class IeBrowserInstance extends BrowserInstance implements
		DisposableBean {

	public IeBrowserInstance(URL seleniumServerUrl) throws Exception {
		super(seleniumServerUrl);
	}

	@Override
	protected WebDriver createDriver(URL seleniumServerUrl) {
		log.info("Initializing IE ...");

		return new RemoteWebDriver(seleniumServerUrl,
				DesiredCapabilities.internetExplorer());
	}

}
