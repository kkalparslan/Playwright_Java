package d11_testGenerator;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import utilities.BrowserUtil;

import java.awt.*;

public class TestGenerator {

    public static void main(String[] args) {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.setViewportSize(width, height);
        page.navigate("https://demoqa.com/login");
        System.out.println("page.title() = " + page.title()); //DEMOQA
        BrowserUtil.waitFor(1);




        page.close();
        browser.close();
        playwright.close();
    }
}
