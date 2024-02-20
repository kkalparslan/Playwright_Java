package day5_iFrame;

import com.microsoft.playwright.*;
import com.microsoft.playwright.Frame;


import java.awt.*;
import java.util.List;

public class IframeUrl {

    public static void main(String[] args) throws InterruptedException {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.setViewportSize(width, height);

        page.navigate("https://the-internet.herokuapp.com/iframe");

        Locator title = page.locator("//h3");
        System.out.println("title = " + title.innerText());

        /**
         sayfadaki bütün frame leri bir list e aldık, buradaki Frame i playwright ten import etmek önemli.
         bir for döngüsü ile de frame lerin url lerini aldık ve frame.url() methodu ile de her bir url e
         ulaşarak yazdırdık. sonra Frame classından yararlanarak bir nesne atadık ve =page.frameByUrl("..")
         diyerek istediğimiz frame in url ini string olarak ekliyoruz. burada iki url döndü bize
         1. si sitenin kendi url i diğeri de iframe in url i. frame in url ini yukarıda atadığımız nesneye
         yapıştırarak iletişime geçmiş olduk. frame ile iletişime geçtikten sonra loccator nesnesine frame.getByText
         veya başka bir locate ile ulaştığımız element ile iletişime geçtik. ayrıca name attribute u ile
         iletişime geçebiliyoruz.
         */

        List<Frame> frames = page.frames();
        System.out.println("size = " + frames);

        for(Frame frame: frames){
            System.out.println("url= " + frame.url());
        }

        //frames by Url
        Frame frame = page.frameByUrl("about:blank");

        Locator body = frame.getByText("Your content goes here.");
        body.click();
        body.clear();
        Locator inputText = frame.getByLabel("Rich Text Area. Press ALT-0 for help.");
        inputText.fill("hello world");

        Thread.sleep(2000);

        page.close();
        browser.close();
        playwright.close();
    }
}
