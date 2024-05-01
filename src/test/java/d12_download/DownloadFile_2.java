package d12_download;

import com.microsoft.playwright.*;
import utilities.BrowserUtil;

import java.awt.*;
import java.nio.file.Paths;

public class DownloadFile_2 {

    public static void main(String[] args) {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.setViewportSize(width, height);
        page.navigate("https://demoqa.com/upload-download");

        // Wait for the download to start
        Download download = page.waitForDownload(() -> {
            // Perform the action that initiates download
            page.getByText("Download").last().click();
        });

        String filePath = System.getProperty("user.home")+"/Downloads/";
        //burada sadece dosyanın yolunu verdik. isimlendirme veya format vermeye gerek yok

        // Wait for the download process to complete and save the downloaded file somewhere
        download.saveAs(Paths.get(filePath, download.suggestedFilename()));
        BrowserUtil.waitFor(1);

        page.close();
        browser.close();
        playwright.close();
    }
}
