import com.microsoft.playwright.*;
import java.nio.file.Paths;

public class Day1_Demo {

        public static void main(String[] args) {
            try (Playwright playwright = Playwright.create()) {
                BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
                launchOptions.setHeadless(false);
                //headless modu false diyerek headed mod istedim
                Browser browser = playwright.chromium().launch(launchOptions);
                Page page = browser.newPage();
                page.navigate("https:www.google.com");
                System.out.println("title: "+page.title());
                page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
                //bu yola screenshot ları ekleyecek
                browser.close();
            }
        }
    }



