package ua.net.itlabs;
import com.codeborne.selenide.Configuration;

import java.lang.System;

public class BaseTest {
    {
        Configuration.browser = System.getProperty("driver.browser");
    }
}
