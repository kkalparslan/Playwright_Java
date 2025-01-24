package d7_actions;

import com.microsoft.playwright.*;
import utilities.BrowserUtil;

import java.awt.*;

public class TextInput {

    public static void main(String[] args) {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.setViewportSize(width, height);
        page.navigate("https://www.ebay.com/");

        // Text input
        Locator searchBox = page.getByPlaceholder("Search for anything");
        searchBox.fill("tiens");

        // Keys and shortcuts
        // searchBox.press("Enter"); buradaki kod ile aşağıdaki satırdaki kod arasında işlev olarak
        // herhangi fark yok. Tercihe kalmış.

        page.keyboard().press("Enter");
        BrowserUtil.waitFor(3); // utilities BrowserUtil sınıfı oluşturdum. ihtiyaç duyduğum
        // methodları yerleştirebilirim.

        page.close();
        browser.close();
        playwright.close();
    }
}
