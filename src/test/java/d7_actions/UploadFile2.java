package d7_actions;

import com.microsoft.playwright.*;
import utilities.BrowserUtil;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadFile2 {
    public static void main(String[] args) {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.setViewportSize(width, height);
        page.navigate("https://demo.automationtesting.in/FileUpload.html");

        // select multiple files
        Locator chooseFile = page.locator("input[id='input-4']");
        String filePath1 = System.getProperty("user.home")+"/IdeaProjects/Playwright_Java/src/test/java/utilities/files/ornek.txt";
        String filePath2 = System.getProperty("user.home")+ "/IdeaProjects/Playwright_Java/src/test/java/utilities/files/english course.docx";

        chooseFile.setInputFiles(new Path [] {Paths.get(filePath1), Paths.get(filePath2)});

        Locator uploadButton = page.getByTitle("Upload selected files");
        uploadButton.click();
        BrowserUtil.waitFor(3);

        page.close();
        browser.close();
        playwright.close();
    }
}
