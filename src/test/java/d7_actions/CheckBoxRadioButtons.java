package d7_actions;

import com.microsoft.playwright.*;
import utilities.BrowserUtil;

import java.awt.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CheckBoxRadioButtons {

    public static void main(String[] args) {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.setViewportSize(width, height);
        page.navigate("https://www.ebay.com/");

        Locator registerButton = page.getByText("register").first();
        registerButton.click();

        // checkboxes and radio buttons
        Locator businessAccount = page.getByText("Business account").first();
        /** 1. yöntem
         businessAccount.check();
         assertThat(businessAccount).isChecked();
         BrowserUtil.waitFor(3); */

        // 2. yöntem
        businessAccount.setChecked(true);
        assertThat(businessAccount).isChecked();
        BrowserUtil.waitFor(2);

        page.close();
        browser.close();
        playwright.close();
    }
}
