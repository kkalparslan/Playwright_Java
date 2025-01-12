package d1_openBrowser;

import com.microsoft.playwright.*;



public class OpenBrowser {

        public static void main(String[] args) {
           /** try (Playwright playwright = Playwright.create()) {
                BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
                launchOptions.setHeadless(false);
                //headless modu false diyerek headed mod istedim
                Browser browser = playwright.chromium().launch(launchOptions);
                Page page = browser.newPage();
                page.navigate("https:www.google.com");
                System.out.println("title: "+page.title());
                page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
                //bu yola screenshot ları ekleyecek
                browser.close();*/

                /**
                 * testi try bloğunda çalıştırırsak page/browser ı otımatik olarak kapatıyor
                 * ve testi bitiriyor. try bloğu kullanmaz isek page/browser ve tümüyle testin
                 * kapanması için aşağıdaki gibi ayrı ayrı close() methodları kullandım.
                 */
                Playwright playwright = Playwright.create();
                Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
                Page page = browser.newPage();
                page.navigate("http://playwright.dev");
                System.out.println(page.title());
                page.close();
                browser.close();
                playwright.close();
        }
    }



