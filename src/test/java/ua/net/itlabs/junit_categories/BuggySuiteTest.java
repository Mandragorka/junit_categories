package ua.net.itlabs.junit_categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ua.net.itlabs.junit_categories.features.TodosOperationsAtAllFilterTest;
import ua.net.itlabs.junit_categories.categories.Buggy;

@RunWith(Categories.class)
@Suite.SuiteClasses(TodosOperationsAtAllFilterTest.class)
@Categories.IncludeCategory(Buggy.class)
public class BuggySuiteTest {
}
