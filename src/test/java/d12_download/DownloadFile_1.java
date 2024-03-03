package d12_download;

import com.microsoft.playwright.*;
import utilities.BrowserUtil;

import java.awt.*;
import java.nio.file.Paths;

public class DownloadFile_1 {

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
            // Perform the action that initiates download. farklı locate ler yaptım sadece aşağıda
           // page.getByText("Download").last().click();
           //page.locator("//a[@id='downloadButton']").click();
           //page.locator("#downloadButton").click();
            page.locator("[id='downloadButton']").click();
        });

        System.out.println("download.page().title() = " + download.page().title());
        // download ettiğimiz dosyanın title ı nı aldık
        System.out.println("download.page().url() = " + download.page().url());
        System.out.println("download.page().toString() = " + download.page().toString());

        /**
         * indirme için istediğim yolu verdim aşağıda
         */
        String filePath = System.getProperty("user.home")+"/Downloads/file.jpg";

        // Wait for the download process to complete and save the downloaded file somewhere
        download.saveAs(Paths.get(filePath));
        BrowserUtil.waitFor(1);

        page.close();
        browser.close();
        playwright.close();
    }
}
