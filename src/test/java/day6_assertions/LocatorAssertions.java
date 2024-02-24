package day6_assertions;


import com.microsoft.playwright.*;

import java.awt.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LocatorAssertions {
    public static void main(String[] args) {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.setViewportSize(width, height);
        page.navigate("https://www.ebay.com/");

        // Locators assertions

        // containsText
        Locator signIn = page.getByText("Sign in").first();
        assertThat(signIn).containsText("Sign");

        // hasAttribute
        Locator searchBox = page.getByPlaceholder("Search for anything");
        assertThat(searchBox).hasAttribute("type", "text");

        // hasText
        Locator register = page.getByText("register").first();
        assertThat(register).hasText("register");

        // isEditable
        assertThat(searchBox).isEditable();
        System.out.println("searchBox.isEditable() = " + searchBox.isEditable());

        // isEmpty
        assertThat(searchBox).isEmpty();
        System.out.println("searchBox.isEditable() = " + searchBox.isEditable());

        // isVisible
        assertThat(searchBox).isVisible();
        System.out.println("searchBox.isVisible() = " + searchBox.isVisible());

        // isEnable
        assertThat(register).isEnabled();
        System.out.println("register.isEnabled() = " + register.isEnabled());


        page.close();
        browser.close();
        playwright.close();
    }
}
