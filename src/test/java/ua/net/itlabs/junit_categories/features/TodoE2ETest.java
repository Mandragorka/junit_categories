package ua.net.itlabs.junit_categories.features;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import ua.net.itlabs.OpenTodoMVCWIthClearData;
import ua.net.itlabs.junit_categories.categories.Smoke;

import static com.codeborne.selenide.CollectionCondition.empty;
import static ua.net.itlabs.pages.TodoMVC.add;
import static ua.net.itlabs.pages.TodoMVC.assertItemsLeftCounter;
import static ua.net.itlabs.pages.TodoMVC.assertTasks;
import static ua.net.itlabs.pages.TodoMVC.assertVisibleTasks;
import static ua.net.itlabs.pages.TodoMVC.clearCompleted;
import static ua.net.itlabs.pages.TodoMVC.deleteTask;
import static ua.net.itlabs.pages.TodoMVC.editTask;
import static ua.net.itlabs.pages.TodoMVC.filterActive;
import static ua.net.itlabs.pages.TodoMVC.filterAll;
import static ua.net.itlabs.pages.TodoMVC.filterCompleted;
import static ua.net.itlabs.pages.TodoMVC.tasks;
import static ua.net.itlabs.pages.TodoMVC.toggleAll;
import static ua.net.itlabs.pages.TodoMVC.toggleTask;

@Category(Smoke.class)
public class TodoE2ETest extends OpenTodoMVCWIthClearData {

    @Test
    public void lifeCycle() {
        // Create tasks
        add("a");
        add("b");
        add("c");
        add("d");
        assertTasks("a", "b", "c", "d");
        assertItemsLeftCounter(4);

        // Editing of existing task
        editTask("a", "a edited");
        assertTasks("a edited", "b", "c", "d");

        // Delete task
        deleteTask("b");
        assertTasks("a edited", "c", "d");
        assertItemsLeftCounter(3);

        // Mark tasks as completed
        toggleTask("d");
        toggleTask("c");
        assertItemsLeftCounter(1);

        // Mark task as reopened
        toggleTask("c");
        assertItemsLeftCounter(2);

        // Switch to filter active
        filterActive();
        assertVisibleTasks("a edited", "c");

        // Switch to filter completed
        filterCompleted();
        assertVisibleTasks("d");

        // Back to All filter
        filterAll();

        // Delete completed tasks
        clearCompleted();
        assertTasks("a edited", "c");

        // Mark all left tasks as completed and then their removing
        toggleAll();
        assertItemsLeftCounter(0);
        clearCompleted();
        tasks.shouldBe(empty);
    }
}
