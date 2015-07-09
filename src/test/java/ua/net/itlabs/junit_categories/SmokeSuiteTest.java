package ua.net.itlabs.junit_categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ua.net.itlabs.junit_categories.features.TodoE2ETest;
import ua.net.itlabs.junit_categories.categories.Smoke;

@RunWith(Categories.class)
@Suite.SuiteClasses(TodoE2ETest.class)
@Categories.IncludeCategory(Smoke.class)
public class SmokeSuiteTest {
}
