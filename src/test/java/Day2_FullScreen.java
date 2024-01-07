import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.awt.*;
import java.lang.Thread;

public class Day2_FullScreen {

    public static void main(String[] args) {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://b2c-tr.tiens.com/");

        page.setViewportSize(width, height);

        /**page.setViewportSize(1280, 720);
         try {
         Thread.sleep(2000);
         } catch (InterruptedException e) {
         throw new RuntimeException(e);
         }
         // browser ın size ını "https://whatismyviewport.com/" adresinden görebiliriz
         // buna göre browser ekran boyutunu page.setViewportSize(1280, 720) methodu ile size
         vererek ayarlayabiliriz.
         // Ancak farklı ekranlarda çalışabileceğimizi de düşünerek yukarıdaki ayarlamalar ile
         hiç uğraşmadan Javadan biraz yardım alarak Dimension sınıfı ile full screen yapabiliriz*/

        page.close();
        browser.close();
        playwright.close();
    }
}
