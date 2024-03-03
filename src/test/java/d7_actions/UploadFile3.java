package d7_actions;

import com.microsoft.playwright.*;
import utilities.BrowserUtil;

import java.awt.*;
import java.nio.file.Paths;

public class UploadFile3 {

    public static void main(String[] args) {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width, height);

        page.navigate("http://autopract.com/selenium/upload2/");
        /**
         If you don't have input element in hand (it is created dynamically), you can handle the
         Page.onFileChooser(handler) event or use a corresponding waiting method upon your action:
         */
        // uploadad single file with file chooser(without input tag)

        Locator selectFileButton = page.locator("a[id='pickfiles']");
        String filePath = System.getProperty("user.home")+"/IdeaProjects/Playwright_Java/src/test/java/utilities/files/2.png";
        FileChooser fileChooser = page.waitForFileChooser(() -> {
            selectFileButton.click();
        });
        fileChooser.setFiles(Paths.get(filePath));

        BrowserUtil.waitFor(2);


        page.close();
        browser.close();
        playwright.close();
    }
}
