package ru.netology.javaqa.planner.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        assertArrayEquals(expected, actual);
    }


    @Test
    public void testSearch() {
        SimpleTask simpleTask1 = new SimpleTask(1, "Buy groceries");
        SimpleTask simpleTask2 = new SimpleTask(2, "Clean the house");
        String[] subtasks = {"Milk", "Eggs", "Bread"};
        Epic epic = new Epic(3, subtasks);
        Meeting meeting = new Meeting(4, "Project Review", "Project X", "Tomorrow");

        Todos todos = new Todos();
        todos.add(simpleTask1);
        todos.add(simpleTask2);
        todos.add(epic);
        todos.add(meeting);


        Task[] expected = new Task[]{meeting};
        Task[] result = todos.search("Project");

        assertArrayEquals(expected, result);

    }


    @Test
    public void testSearchNoMatch() {
        SimpleTask simpleTask = new SimpleTask(1, "Buy groceries");
        String[] subtasks = {"Milk", "Eggs", "Bread"};
        Epic epic = new Epic(2, subtasks);
        Meeting meeting = new Meeting(3, "Project Review", "Project X", "Tomorrow");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] result = todos.search("Vacation");
        assertEquals(0, result.length);
        Task[] expected = {};
        assertArrayEquals(expected, result);
    }


    @Test
    public void testSearchEmptyTodos() {
        Todos todos = new Todos();
        Task[] result = todos.search("Project");
        assertEquals(0, result.length);
        Task[] expected = {};
        assertArrayEquals(expected, result);
    }


    @Test
    public void testEqualsSameObject() {
        Task[] tasks1 = {new SimpleTask(1, "Task 1")};
        Task[] tasks2 = {new SimpleTask(1, "Task 1")};
        assertArrayEquals(tasks1, tasks2);
    }


    @Test
    public void testEqualsEqualTasks() {
        Task[] tasks1 = {new SimpleTask(1, "Task 1")};
        Task[] tasks2 = {new SimpleTask(1, "Task 1")};
        assertArrayEquals(tasks1, tasks2);
    }


    @Test
    public void testEqualsDifferentTasks() {
        Task task1 = new SimpleTask(1, "Task 1");
        Task task2 = new SimpleTask(2, "Task 2");
        Assertions.assertFalse(task1.equals(task2));
    }

    @Test
    public void testEqualsDifferentTypes() {
        Task task1 = new SimpleTask(1, "Task 1");
        Object object = new Object();
        Assertions.assertFalse(task1.equals(object));
    }

    @Test
    public void testHashCode() {
        Task task1 = new SimpleTask(1, "Task 1");
        Task task2 = new SimpleTask(1, "Task 1");
        Assertions.assertEquals(task1.hashCode(), task2.hashCode());
    }


    @Test
    public void testConstructorAndGetId() {
        Task task = new SimpleTask(1, "Task 1");
        assertEquals(1, task.getId());
    }


    @Test
    public void testGetTopic() {
        Meeting meeting = new Meeting(1, "Project Review", "Project X", "Tomorrow");
        assertEquals("Project Review", meeting.getTopic());
    }


    @Test
    public void testGetProject() {
        Meeting meeting = new Meeting(1, "Project Review", "Project X", "Tomorrow");
        assertEquals("Project X", meeting.getProject());
    }


    @Test
    public void testGetStart() {
        Meeting meeting = new Meeting(1, "Project Review", "Project X", "Tomorrow");
        assertEquals("Tomorrow", meeting.getStart());
    }


    @Test
    public void testGetSubtasks() {
        String[] subtasks = {"Milk", "Eggs", "Bread"};

        Epic epic = new Epic(1, subtasks);

        String[] retrievedSubtasks = epic.getSubtasks();

        assertArrayEquals(subtasks, retrievedSubtasks);
    }

    @Test
    public void testGetTitle() {
        SimpleTask simpleTask = new SimpleTask(1, "Buy groceries");

        String retrievedTitle = simpleTask.getTitle();

        assertEquals("Buy groceries", retrievedTitle);
    }


    @Test
    public void testEqualsNullObject() {
        Task task1 = new SimpleTask(1, "Task 1");
        Assertions.assertFalse(task1.equals(null));
    }

    @Test
    public void testSearchMultipleTasksMatchingQuery() {
        // Создание задач и добавление их в менеджер
        Todos todos = new Todos();
        SimpleTask simpleTask1 = new SimpleTask(1, "Task 1");
        SimpleTask simpleTask2 = new SimpleTask(2, "Task 2");
        todos.add(simpleTask1);
        todos.add(simpleTask2);

        Task[] result = todos.search("Task");

        assertArrayEquals(new Task[]{simpleTask1, simpleTask2}, result);
    }

    @Test
    public void testSearchOneTaskMatchingQuery() {

        Todos todos = new Todos();
        SimpleTask simpleTask = new SimpleTask(1, "Task");
        todos.add(simpleTask);

        Task[] result = todos.search("Task");

        assertArrayEquals(new Task[]{simpleTask}, result);
    }

    @Test
    public void testSearchNoTasksMatchingQuery() {

        Todos todos = new Todos();
        SimpleTask simpleTask = new SimpleTask(1, "Task");
        todos.add(simpleTask);

        Task[] result = todos.search("InvalidQuery");

        assertArrayEquals(new Task[0], result);
    }


}
