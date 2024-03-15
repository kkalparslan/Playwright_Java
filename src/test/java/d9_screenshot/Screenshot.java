package d9_screenshot;


import com.microsoft.playwright.*;

import java.awt.*;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Screenshot {

    public static void main(String[] args) {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.setViewportSize(width, height);

        page.navigate("https://www.ebay.com/");

        // Sayfanın resmini alma
        String date = new SimpleDateFormat("_hh_mm_ss_ddMMyyy").format(new Date());
        String filePath = "src/test/java/utilities/screenShots"+ date +".jpg"; //aldığımız ekran
        //görüntülerini kaydetmek istediğimiz dosyanın yolu. utilities altında screenshot isimli
        //bir klasörün altında topladım.

        //fotoğraf cekme sadece pencerede görünen kadarının görüntüsünü alıyor
        // page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filePath)));

        //tüm sayfa tüm sayfanın aşağı scrollar dahil görüntüsünü alıyor.
        //page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filePath)).setFullPage(true));

        //elementin görüntüsünü alma
        Locator searchBox = page.getByPlaceholder("Search for anything");
        // searchBox.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(filePath)));

        /**
         screenshot ile ilgili daha ayrıntılı bilgiler resmi siteden bulunabilir. (API->page->
         screenshot->Arguments altında) dosya uzantısı, kalitesi, ölçeğini, stilini, süresini
         vaya formatını vb. ayarlayabiliyoruz. aşağıda maskeleme örneği var. verilen elementi
         default olarak pembeye boyuyor. bu renkte değiştirilebiliyor.
         */
        //maskeleme
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filePath))
                .setMask(Arrays.asList(searchBox)));

        page.close();
        browser.close();
        playwright.close();
    }
}
