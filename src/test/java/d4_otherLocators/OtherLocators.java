package d4_otherLocators;

import com.microsoft.playwright.*;

import java.awt.*;

public class OtherLocators {

    public static void main(String[] args) throws InterruptedException {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("http://www.getir.com");

        page.setViewportSize(width, height);

        // css matching by text
        // 1. has text
        Locator loginText = page.locator("h5:has-text('Giriş yap veya kayıt ol')");
        System.out.println("1. loginText = " + loginText.innerText());

        // 2. text
        Locator loginText2 = page.locator("h5:text('Giriş yap veya kayıt ol')");
        // burada daha katı/spesifik bir arama yapar. taxti sadece böyle ise getirir
        System.out.println("2. loginText2 = " + loginText2.innerText());


        // element matching one of the conditions
        Locator continueButton = page.locator("button:has-text('Telefon numarası ile devam et'), button:has-text('login button')");
        System.out.println("3. continueButton = " + continueButton.innerText());

        // css: pick n-th match from the one query result
        Locator loginButton = page.locator(":nth-match(:text('Giriş yap'), 1)");

        System.out.println("4. loginButton = " + loginButton.innerText());
        loginButton.click();
        Thread.sleep(2000);

        //id, data-testid, data-test-id, data-test selector
        Locator loginPhoneNumber = page.locator("data-testid=modal").locator("data-testid=input");
        System.out.println("5. loginPhoneNumber = " + loginPhoneNumber.innerText());

        // xpath locator
        Locator loginContinueButton = page.locator("(//button[@type='submit'])[2]");
        System.out.println("6. loginContinueButton = " + loginContinueButton.innerText());

        page.close();
        browser.close();
        playwright.close();

    }
}
