package ua.net.itlabs.junit_categories.features;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import ua.net.itlabs.OpenTodoMVCWIthClearData;
import ua.net.itlabs.junit_categories.categories.Buggy;
import ua.net.itlabs.junit_categories.categories.Unit;

import static com.codeborne.selenide.CollectionCondition.empty;
import static ua.net.itlabs.pages.TodoMVC.add;
import static ua.net.itlabs.pages.TodoMVC.assertItemsLeftCounter;
import static ua.net.itlabs.pages.TodoMVC.assertTasks;
import static ua.net.itlabs.pages.TodoMVC.clearCompleted;
import static ua.net.itlabs.pages.TodoMVC.deleteTask;
import static ua.net.itlabs.pages.TodoMVC.editTask;
import static ua.net.itlabs.pages.TodoMVC.tasks;
import static ua.net.itlabs.pages.TodoMVC.toggleAll;
import static ua.net.itlabs.pages.TodoMVC.toggleTask;

@Category(Unit.class)
public class TodosOperationsAtAllFilterTest extends OpenTodoMVCWIthClearData {

    @Test
    public void testAddTasks() {
        add("a");
        add("b");
        add("c");
        add("d");
        assertTasks("a", "b", "c", "d");
        assertItemsLeftCounter(4);
    }

    @Test
    public void testEditTask() {
        add("a");
        add("b");
        add("c");
        editTask("a", "a edited");
        assertTasks("a edited", "b", "c");
        assertItemsLeftCounter(3);
    }

    @Test
    public void testDeleteTask() {
        add("a");
        add("b");
        add("c");
        deleteTask("b");
        assertTasks("a", "c");
        assertItemsLeftCounter(2);
    }

    @Test
    @Category(Buggy.class)
    public void testMarkTaskAsCompletedReopenedAndClearCompleted() {
        add("a");
        add("b");
        add("c");
        toggleTask("a");
        toggleTask("c");
        assertItemsLeftCounter(1);

        toggleTask("c");
        assertItemsLeftCounter(2);

        clearCompleted();
        assertItemsLeftCounter(2);
        assertTasks("a", "c");
    }

    @Test
    public void testMarkAllAsCompletedAndClear() {
        add("a");
        add("b");
        add("c");
        toggleAll();
        assertItemsLeftCounter(0);
        clearCompleted();
        tasks.shouldBe(empty);
    }
}
