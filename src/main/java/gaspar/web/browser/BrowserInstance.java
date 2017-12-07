package gaspar.web.browser;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.DisposableBean;

@Slf4j
public abstract class BrowserInstance implements DisposableBean {

	protected WebDriver driver;

	public BrowserInstance(final URL seleniumServerUrl) throws Exception {
		this.driver = createDriver(seleniumServerUrl);

		this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}

	protected abstract WebDriver createDriver(URL seleniumServerUrl);

	@Override
	public void destroy() throws Exception {
		log.info("Closing browser ...");

		this.driver.quit();
	}

	public WebDriver getDriver() {
		return this.driver;
	}

}
