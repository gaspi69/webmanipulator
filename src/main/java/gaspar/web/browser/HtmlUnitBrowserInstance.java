package gaspar.web.browser;

import java.net.URL;

import gaspar.web.browser.BrowserInstance;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

@Slf4j
public class HtmlUnitBrowserInstance extends BrowserInstance {

	public HtmlUnitBrowserInstance(URL seleniumServerUrl) throws Exception {
		super(seleniumServerUrl);
	}

	@Override
	protected WebDriver createDriver(URL seleniumServerUrl) {
		log.info("Initializing HtmlUnit browser ...");

		return new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
	}

}
