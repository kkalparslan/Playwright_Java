package d10_keyboard;

import com.microsoft.playwright.*;
import utilities.BrowserUtil;

import java.awt.*;

public class Keyboard {
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

        Locator userName = page.getByPlaceholder("UserName");
        userName.click();

        // insert text
        page.keyboard().insertText("Alparslan");
        BrowserUtil.waitFor(1);

        // press
        page.keyboard().press("Control+A");
        page.keyboard().press("Backspace");
        BrowserUtil.waitFor(1);

        // type
        page.keyboard().type("Ozturk");
        BrowserUtil.waitFor(1);

        // down
        page.keyboard().down("Shift");
        page.keyboard().press("A");
        BrowserUtil.waitFor(1);

        // up
        page.keyboard().up("Shift");
        page.keyboard().press("h");
        BrowserUtil.waitFor(1);

        // general example:
        page.keyboard().press("Control+A");
        page.keyboard().press("Backspace");
        BrowserUtil.waitFor(1);

        page.keyboard().type("Hello World!");
        BrowserUtil.waitFor(1);
        page.keyboard().press("ArrowLeft");
        page.keyboard().down("Shift");
        for (int i = 0; i < " World".length(); i++) { // 1 er saniye bekleyerek " World" kelimesini yukarıdaki
            // down("Shift") ile birlikte harf harf sildirdik for loop ile. Geriye "Hello!" kaldı
            page.keyboard().press("ArrowLeft");
            BrowserUtil.waitFor(1);
        }
        // for (int i = 0; i < " World".length(); i++)
        //    page.keyboard().press("ArrowLeft");
        page.keyboard().up("Shift");
        page.keyboard().press("Backspace"); // Result text will end up saying "Hello!"
        BrowserUtil.waitFor(2);


        page.close();
        browser.close();
        playwright.close();
    }
}
