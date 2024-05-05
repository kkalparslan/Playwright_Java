package d7_actions;

import com.microsoft.playwright.*;
import utilities.BrowserUtil;

import java.awt.*;

public class MouseClick {

    public static void main(String[] args) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.setViewportSize(width, height);
        page.navigate("https://demoqa.com/buttons");

        // Generic click
        Locator clickMe = page.getByText("Click Me").nth(2);
        clickMe.click();
        BrowserUtil.waitFor(2);

        // Double click double click için dbclick() methodu uyguluyoruz.
        Locator doubleClickMe = page.getByText("Double Click Me");
        doubleClickMe.dblclick();
        BrowserUtil.waitFor(2);

        // Hover over element Hover over için hover() methodu uyguluyoruz.
        page.getByText("Right Click Me").hover();
        BrowserUtil.waitFor(2);

        page.close();
        browser.close();
        playwright.close();
    }
}
