package gaspar.web.model;

import com.google.common.base.Preconditions;

import java.io.Closeable;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PageModel implements Closeable {

    private final URL pageUrl;

    private final UserContext context;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public PageModel(URL pageUrl, UserContext context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context);
        this.pageUrl = pageUrl;
        this.context = context;
    }

    @Override
    public void close() throws IOException {
        executorService.shutdown();
    }

    public void openPage() {
        executorService.execute(new WebAction(context) {
            @Override
            protected void handle() {
                this.context.getDriver().get(pageUrl.toString());
                waitForPageLoaded();
            }
        });
    }

}
