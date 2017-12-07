package gaspar.web.model;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class WebAction implements Runnable {

    private static final int LOAD_TIMEOUT = 10;
    public static final int TIMEOUT_SECONDS = 2;


    protected final UserContext context;

    public WebAction(final UserContext context) {
        Preconditions.checkNotNull(context);
        this.context = context;
    }

    @Override
    public void run() {
        try {
            log.trace("Executing model ...");
            handle();
            log.trace("Action done");
        } catch (RuntimeException e) {
            log.error("Error while executing model", e);
        }
    }

    protected abstract void handle();

    protected void waitForPageLoaded() {
        this.context.getDriver().manage().timeouts().pageLoadTimeout(LOAD_TIMEOUT, TimeUnit.SECONDS);

        if (this.context.getDriver().getTitle().contains("404")) {
            throw new IllegalStateException("page not available");
        }
    }

    protected void waitForElementToLoad(final WebElement element) {
        new WebDriverWait(this.context.getDriver(), TIMEOUT_SECONDS).until(ExpectedConditions
                .visibilityOf(element));
    }

    protected boolean isElementPresent(final By by) {
        // return this.driver.findElements(by).size() < 0;
        try {
            this.context.getDriver().findElement(by);
            return true;
        } catch (final NoSuchElementException e) {
            return false;
        }
    }

}
