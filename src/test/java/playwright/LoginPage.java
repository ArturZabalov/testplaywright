package playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("http://playwright.dev");
        System.out.println(page.title());

        assertThat(page).hasTitle(Pattern.compile("Playwright")); // ожидаем что заголовк должен содержать подстроку

        Locator getStarted = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Get Started")); // создаем локатор
        Locator getFirstScript = page.getByText("text=First Script");

        assertThat(getStarted).hasAttribute("href", "/docs/intro"); //ожидаем что аттрибут будет содержать значение
        assertThat(getFirstScript).hasAttribute("");

        getStarted.click(); // кликаем на локатор

        assertThat(page.getByRole(AriaRole.HEADING,
                new Page.GetByRoleOptions().setName("Installation"))).isVisible(); //

        browser.close();
        playwright.close();
    }
}