import com.microsoft.playwright.*;

public class Day3_Locators {

    public static void main(String[] args){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("http://www.getir.com");
        System.out.println(page.title());
        System.out.println(page.url());

        // getByText
        Locator loginText = page.getByText("Giriş yap veya kayıt ol");
        System.out.println("1. text : " + loginText.innerText());
        /**
         * innerText() methodunu selenium daki getText() gibi düşünebiliriz.
         * Elementin metnini almamızı sağlar
         */

        // Locate in Shadow DOM
        /**
         * Bir üst tag e gidiyor ve bu tag in içinde ... text varsa bu elementi al gibi hareket ediyor.
         * Burada verilen textin index numarası önemli. verilmezse şayet en çok karşılaşılan problemlerden
         * olan "strict mode violation" hatası alınır. bu nedenle alınmak istenen textin index numarası da
         * verilmelidir. örneğin first, last ..nth gibi.
         */
        Locator shodowDom = page.locator("div", new Page.LocatorOptions().setHasText("Giriş yap veya kayıt ol")).last();
        System.out.println("shodow dom: "+ shodowDom.innerText());

        //getByRole










        page.close();
        browser.close();
        playwright.close();
    }
}
