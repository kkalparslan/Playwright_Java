package day8_multipleWindow;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import utilities.BrowserUtil;

import java.awt.*;
import java.util.List;

public class MultipleWindow {

    public static void main(String[] args) {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width, height);
        page.navigate("https://demoqa.com/browser-windows");

        // multiple window
        // Get popup after a specific action (e.g., click)
        Page popup = page.waitForPopup(new Page.WaitForPopupOptions().setPredicate(
                p-> p.context().pages().size() == 2), ()->{
            page.getByText("New Window").first().click();
        });

        List <Page> pages = popup.context().pages();
        System.out.println("pages.size() = " + pages.size());

        pages.forEach(tab->{
            tab.waitForLoadState();
            System.out.println("tab.title() = " + tab.title());
        });

        System.out.println("pages.get(0).url() = " + pages.get(0).url());
        System.out.println("pages.get(1).url() = " + pages.get(1).url());

        Page firstPage = null;
        Page secondPage = null;

        if(pages.get(0).url().equals("https://demoqa.com/browser-windows")){
            firstPage = pages.get(0);
        }else {
            secondPage = pages.get(1);
        }

        BrowserUtil.waitFor(3);
        firstPage.bringToFront();
        BrowserUtil.waitFor(2);




       // BrowserUtil.waitFor(3);
        page.close();
        browser.close();
        playwright.close();
    }
}
