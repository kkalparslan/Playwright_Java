package d6_assertions;

import com.microsoft.playwright.*;

import java.awt.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PageAssertions {

    public static void main(String[] args) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.setViewportSize(width, height);
        page.navigate("https://www.ebay.com/");

        // page assertions

        // hasUrl
        assertThat(page).hasURL("https://www.ebay.com/");
        System.out.println("page.url() = " + page.url());

        // hasTitle
        assertThat(page).hasTitle("Electronics, Cars, Fashion, Collectibles & More | eBay");
        System.out.println("page.title() = " + page.title());

        // not()
        assertThat(page).not().hasTitle("test");

        page.close();
        browser.close();
        playwright.close();
    }
}
