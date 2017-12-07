package gaspar.web.model;

import com.google.common.base.Preconditions;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import java.io.Closeable;
import java.io.IOException;

@Data
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserContext implements Closeable {

    @Autowired
    private transient final WebDriver driver;

    @Value("${web.username}")
    private String username;

    @Value("${web.password}")
    private String passwoord;

    protected UserContext(WebDriver driver) {
        Preconditions.checkNotNull(driver);
        this.driver = driver;
    }

    @Override
    public void close() throws IOException {
        driver.close();
    }

    WebDriver getDriver() {
        return driver;
    }

}
