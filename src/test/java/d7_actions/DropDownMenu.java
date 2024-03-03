package d7_actions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import utilities.BrowserUtil;

import java.awt.*;

public class DropDownMenu {

    public static void main(String[] args) {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.setViewportSize(width, height);
        page.navigate("https://www.ebay.com/");

        /**
         * elementin select tag i varsa value yada label optionlarına göre seçim yapabiliriz.
         */

        // select options
        Locator selectCategory = page.getByLabel("Select a category for search");
        BrowserUtil.waitFor(2);

        // select by value
        selectCategory.selectOption("625"); // Cameras & Photo
        BrowserUtil.waitFor(2);

        // select by label
        selectCategory.selectOption(new SelectOption().setLabel("Dolls & Bears"));
        BrowserUtil.waitFor(2);


        page.close();
        browser.close();
        playwright.close();
    }
}
