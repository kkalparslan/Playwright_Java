import com.microsoft.playwright.*;
import java.nio.file.Paths;

public class FirstExample {

        public static void main(String[] args) {
            try (Playwright playwright = Playwright.create()) {
                BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
                launchOptions.setHeadless(false);
                Browser browser = playwright.chromium().launch(launchOptions);
                Page page = browser.newPage();
                page.navigate("https:www.hepsiburada.com");
                page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
                browser.close();
            }
        }
    }
      /**

            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
            launchOptions.setHeadless(false); // headful modunu etkinleştirin

            Browser browser = playwright.chromium().launch(launchOptions);
            Page page = browser.newPage();
            page.navigate("https://www.google.com");
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
            browser.close();
        }*/


