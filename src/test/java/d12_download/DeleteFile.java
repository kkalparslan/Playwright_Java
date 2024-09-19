package d12_download;

import com.microsoft.playwright.*;
import utilities.BrowserUtil;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeleteFile {

    public static void main(String[] args) throws IOException {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.setViewportSize(width, height);
        page.navigate("https://demoqa.com/upload-download");

        Download download = page.waitForDownload(() -> {
            page.getByText("Download").last().click();
        });

        String date = new SimpleDateFormat("_hh_mm_ss_ddMMyyy").format(new Date());
        String filePath = System.getProperty("user.home")+"/Downloads/"+date+"file.jpg";
        download.saveAs(Paths.get(filePath));
        BrowserUtil.waitFor(1);

        //dosyanın indiğini assert etme/ validate to the file downloaded
        boolean isFileDownloaded = Files.exists(Paths.get(filePath));
        assert isFileDownloaded; //==true olarak da assert edebiliriz ama bu haliyle basit yap diyor.

        System.out.println("isFileDownloaded = " + isFileDownloaded);

        //download olan dosyayı silme
        download.delete(); //resmi sitedeki silme metodu ama dosyayı genelde silmiyor. aşağıdaki
        // kodu deniyoruz.

        Files.deleteIfExists(Paths.get(filePath)); //dosya silindi


        page.close();
        browser.close();
        playwright.close();
    }
}
