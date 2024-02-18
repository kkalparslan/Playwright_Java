package day3_locators;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.awt.*;

import static com.microsoft.playwright.options.AriaRole.BUTTON;
import static com.microsoft.playwright.options.AriaRole.TEXTBOX;

public class Built_in_Locators {

    public static void main(String[] args){

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("http://www.getir.com");

        page.setViewportSize(width, height);

        System.out.println("title: "+ page.title());
        System.out.println("url: "+ page.url());

        // getByText:
        Locator loginText = page.getByText("Giriş yap veya kayıt ol");
        System.out.println("1. text : " + loginText.innerText());
        /**
         * loginText buradaki locator ımız (objemiz), selenium daki web element gibi düşünülebilir,
         * innerText() methodunu selenium daki getText() gibi düşünebiliriz. Elementin metnini almamızı sağlar
         */

        // Locate in Shadow DOM:
        /**
         * Bir üst tag e gidiyor ve bu tag in içinde "..." text varsa bu elementi al gibi hareket ediyor.
         * Burada verilen textin index numarası önemli. (birden fazla text element varsa tabi) Verilmezse
         * şayet en çok karşılaşılan problemlerden olan "strict mode violation" hatası alınabilir. Bu
         * nedenle alınmak istenen textin index numarası da verilmelidir. Örneğin first, last ..nth gibi
         * kavramlar var. Ayrıca diğerleri gibi page.getBy.. olarak değil biraz daha xpath veya css e
         * benzer şekilde page.locator(içine bir selector) olarak alıyoruz
         */
        Locator shodowDom = page.locator("div", new Page.LocatorOptions()
                .setHasText("Giriş yap veya kayıt ol")).last();
        System.out.println("shodow dom: "+ shodowDom.innerText());

        // getByRole:
        Locator phoneNumber = page.getByRole(TEXTBOX, new Page.GetByRoleOptions().setName("Telefon Numarası"));
        System.out.println("phone number: "+ phoneNumber.innerText());
        /**
         * AriaRole.Textbox diye de konulabilir ilk role paranetresi, yukarıdaki gibi sadece TEXTBOX olarak da
         * Aria role dışındaki locator larda da GetByRoleOptions() seçenekleri kullanılabilir.
         * Sadece buna özgü değil. Hatta 2 locator aynı anda da kullanılabilir.
         */
        phoneNumber.click();
        phoneNumber.fill("555-0-666");
        System.out.println("1.phone number: "+phoneNumber.inputValue());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //getByPlaceholder:
        Locator phoneNumber2 = page.getByPlaceholder("Telefon Numarası");
        System.out.println("2. phone number: "+ phoneNumber2.innerText());

        //getByLabel:
        Locator phoneContinueButton = page.getByLabel("login button");
        System.out.println("3. phoneContinueButton: " + phoneContinueButton.innerText());

        // click login button
        // getByRole:
        Locator loginButton = page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Giriş yap"));
        loginButton.click();

        // test Id:
        /**
         farklı bir pop up (modal) ta açılan elementleri DOM alanında testId modal olarak gördük.
         getByTestId(modal) ile içine girdikten sonra başka bir locator ile de tıklamak istediğimiz
         elementi bulduk. burada placeholder ile aldık.
         */
        Locator loginPhoneNumber = page.getByTestId("modal").getByPlaceholder("Telefon Numarası");
        System.out.println("4. phone number: "+loginPhoneNumber.innerText());
        loginPhoneNumber.click();
        loginPhoneNumber.fill("74647347");

        Locator cancelButton = page.getByTestId("modal").getByTestId("button").first();
        cancelButton.click();

        // getByAlt text:
        /**
         * bir fotoğrafın alt attribute ı olmak zorunda. bu attribute ın isminden faydalanadık.
         */
        Locator category = page.getByAltText("Su & İçecek");

        // getByTestId and filter options
        Locator category2 = page.getByTestId("text").filter(new Locator.FilterOptions().setHasText("Su & İçecek"));
        System.out.println("5. category: "+category2.innerText());

        // css and filter options
        Locator category3 = page.locator("div").filter(new Locator.FilterOptions().setHasText("Su & İçecek"));
        // buradaki element istediğimiz element olmayabilir. bu nedenle aşağıda daha spesifik bir tane bulundu.

        Locator category4 = page.locator("sc-b6b4847f-7 > .sc-6df7862-1")
                .filter(new Locator.FilterOptions().setHasText("Su & İçecek"));
        //category4.click();


        page.close();
        browser.close();
        playwright.close();
    }
}
