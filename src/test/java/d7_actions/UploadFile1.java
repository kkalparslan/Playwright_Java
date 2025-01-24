package d7_actions;

import com.microsoft.playwright.*;
import utilities.BrowserUtil;

import java.awt.*;
import java.nio.file.Paths;

public class UploadFile1 {
    public static void main(String[] args) {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.setViewportSize(width, height);
        page.navigate("https://the-internet.herokuapp.com/upload");

        // select one file
        /**
         bir elementin input tag i varsa öncelikle bu tag i olan locator ile başlanmalıdır. aksi takdirde
         upload işlemi başarız olabilecektir.
         */

        Locator dosyaSec = page.locator("#file-upload");
        String filePath = System.getProperty("user.home") + "/IdeaProjects/Playwright_Java/src/test/java/utilities/files/ornek.txt";

        dosyaSec.setInputFiles(Paths.get(filePath));
        page.locator("#file-submit").click();

        BrowserUtil.waitFor(3);

        page.close();
        browser.close();
        playwright.close();
    }
}
