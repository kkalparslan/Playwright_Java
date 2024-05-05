package d7_actions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import utilities.BrowserUtil;

import java.awt.*;

public class DragAndDrop {
    public static void main(String[] args) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.setViewportSize(width, height);
        page.navigate("https://demoqa.com/droppable");
        // drag and drop
        // page.getByText("Drag me").first().dragTo(page.getByText("Drop here").first());
        // BrowserUtil.waitFor(2);

        /**
         aşağıda önce sürükleyeceğimiz elemente hover yapıp mouse.down yaptık. sonra
         drop yapacağımız elemente hover yapıp mouse.up yaparak bitirdik.yukarıda hazır method
         aşağıda manuel uygulamış olduk.         */

        // manually
        page.getByText("Drag me").first().hover();
        BrowserUtil.waitFor(1);
        page.mouse().down();
        page.getByText("Drop here").first().hover();
        BrowserUtil.waitFor(1);
        page.mouse().up();
        BrowserUtil.waitFor(2);

        page.close();
        browser.close();
        playwright.close();
    }
}
