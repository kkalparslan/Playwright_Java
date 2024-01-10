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

        // getByText
        Locator loginText = page.getByText("Giriş yap veya kayıt ol");
        System.out.println("1. text : " + loginText.innerText());
        /**
         * loginText buradaki locatro ımız (objemiz), web element gibi düşünülebilir,
         * innerText() methodunu selenium daki getText() gibi düşünebiliriz.
         * Elementin metnini almamızı sağlar
         */

        // Locate in Shadow DOM
        /**
         * Bir üst tag e gidiyor ve bu tag in içinde "..." text varsa bu elementi al gibi hareket ediyor.
         * Burada verilen textin index numarası önemli. Verilmezse şayet en çok karşılaşılan problemlerden
         * olan "strict mode violation" hatası alınabilir. Bu nedenle alınmak istenen textin index numarası
         * da verilmelidir. Örneğin first, last ..nth gibi kavramlar var.
         * Ayrıca diğerleri gibi page.getBy.. olarak değil biraz daha xpath veya css e benzer şekilde
         * page.locator(içine bir selector) olarak alıyoruz
         */
        Locator shodowDom = page.locator("div", new Page.LocatorOptions()
                .setHasText("Giriş yap veya kayıt ol")).last();
        System.out.println("shodow dom: "+ shodowDom.innerText());

        //getByRole
        Locator phoneNumber = page.getByRole(TEXTBOX, new Page.GetByRoleOptions().setName("Telefon Numarası"));
        System.out.println("phone number: "+ phoneNumber.innerText());
        /**
         * Aria role dışındaki locator larda da GetByRoleOptions() seçenekleri kullanılabilir.
         * Sadece buna özgü değil. Hatta 2 locator aynı anda da kullanılabilir.
         */
        phoneNumber.click();
        phoneNumber.fill("555-0-666");
       // System.out.println(phoneNumber.inputValue());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //getByPlaceholder
        Locator phoneNumber2 = page.getByPlaceholder("Telefon Numarası");
        System.out.println("2. phone number: "+ phoneNumber2.innerText());

        //getByLabel
        Locator phoneContinueButton = page.getByLabel("login button");
        System.out.println("phoneContinueButton: " + phoneContinueButton.innerText());

        //click login button
        //getByRole
        Locator loginButton = page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Giriş yap"));
        loginButton.click();


        page.close();
        browser.close();
        playwright.close();
    }
}
