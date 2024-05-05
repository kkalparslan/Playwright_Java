package d8_multipleWindow;

import com.microsoft.playwright.*;
import utilities.BrowserUtil;

import java.awt.*;

public class Tab {

    public static void main(String[] args) {
        /**
         herhangi bir linke tıkladığımızda birden fazla sekme açılabilir. sayfalar arası geçiş nasıl
         yapılabilir? istediğimiz sayfayı nasıl öne alabiliriz? aşağıda yeni sekmeye nasıl geçilir
         konusu işlendi.
         */
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width, height);

        page.navigate("https://the-internet.herokuapp.com/windows");
        System.out.println("page.title() = " + page.title());

        // Get page after a specific action (e.g. clicking a link)
        BrowserUtil.waitFor(1);
        Page newPage = page.context().waitForPage(() ->{  //resmi sitede context.waitForPage olarak
            //kullanılmış. gizli sekmede açmak için. bu da Browserdan geliyor
            page.getByText("Click Here").click(); //opens a new tab
        });
        newPage.waitForLoadState();
        System.out.println("newPage.title() = " + newPage.title());
        BrowserUtil.waitFor(3);

        // navigate to back page->eski sayfaya geri dön
        page.bringToFront();
        BrowserUtil.waitFor(3);

        page.close();
        browser.close();
        playwright.close();
    }
}
