package ru.netology.javaqa.planner.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


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
        Assertions.assertArrayEquals(expected, actual);
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


        Task[] result = todos.search("Project");
        Assertions.assertEquals(1, result.length);
        Assertions.assertEquals(meeting, result[0]);
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
        Assertions.assertEquals(0, result.length);
    }

    @Test
    public void testSearchEmptyTodos() {
        Todos todos = new Todos();
        Task[] result = todos.search("Project");
        Assertions.assertEquals(0, result.length);
    }

    @Test
    public void testEqualsSameObject() {
        Task task1 = new SimpleTask(1, "Task 1");
        Assertions.assertTrue(task1.equals(task1));
    }

    @Test
    public void testEqualsEqualTasks() {
        Task task1 = new SimpleTask(1, "Task 1");
        Task task2 = new SimpleTask(1, "Task 1");
        Assertions.assertTrue(task1.equals(task2));
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
        Assertions.assertEquals(1, task.getId());
    }


    @Test
    public void testGetTopic() {
        Meeting meeting = new Meeting(1, "Project Review", "Project X", "Tomorrow");
        Assertions.assertEquals("Project Review", meeting.getTopic());
    }

    @Test
    public void testGetProject() {
        Meeting meeting = new Meeting(1, "Project Review", "Project X", "Tomorrow");
        Assertions.assertEquals("Project X", meeting.getProject());
    }

    @Test
    public void testGetStart() {
        Meeting meeting = new Meeting(1, "Project Review", "Project X", "Tomorrow");
        Assertions.assertEquals("Tomorrow", meeting.getStart());
    }

    @Test
    public void testGetSubtasks() {
        String[] subtasks = {"Milk", "Eggs", "Bread"};

        Epic epic = new Epic(1, subtasks);

        String[] retrievedSubtasks = epic.getSubtasks();

        Assertions.assertArrayEquals(subtasks, retrievedSubtasks);
    }

    @Test
    public void testGetTitle() {
        SimpleTask simpleTask = new SimpleTask(1, "Buy groceries");

        String retrievedTitle = simpleTask.getTitle();

        Assertions.assertEquals("Buy groceries", retrievedTitle);
    }

    @Test
    public void testEqualsNullObject() {
        Task task1 = new SimpleTask(1, "Task 1");
        Assertions.assertFalse(task1.equals(null));
    }


}
