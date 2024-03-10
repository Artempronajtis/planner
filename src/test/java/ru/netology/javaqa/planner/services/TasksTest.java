package ru.netology.javaqa.planner.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TasksTest {
    @Test
    public void testSimpleTaskMatches() {
        SimpleTask simpleTask = new SimpleTask(1, "Buy groceries");
        Assertions.assertTrue(simpleTask.matches("groceries"));
        Assertions.assertFalse(simpleTask.matches("meeting"));
    }

    @Test
    public void testEpicMatches() {
        String[] subtasks = {"Milk", "Eggs", "Bread"};
        Epic epic = new Epic(2, subtasks);
        Assertions.assertTrue(epic.matches("Eggs"));
        Assertions.assertFalse(epic.matches("Fruit"));

    }

    @Test
    public void testMeetingMatches() {
        Meeting meeting = new Meeting(3, "Project Review", "Project X", "Tomorrow");
        Assertions.assertTrue(meeting.matches("Project"));
        Assertions.assertFalse(meeting.matches("Task"));
    }

    @Test
    public void testMatches() {

        Task task = new SimpleTask(1, "Task 1");
        Assertions.assertFalse(task.matches("query"));
    }

    @Test
    public void testMatchesTopic() {

        Meeting meeting = new Meeting(1, "Project Review", "Project X", "Tomorrow");
        Assertions.assertTrue(meeting.matches("Review"));
    }

    @Test
    public void testMatchesProject() {

        Meeting meeting = new Meeting(1, "Project Review", "Project X", "Tomorrow");
        Assertions.assertTrue(meeting.matches("X"));
    }

    @Test
    public void testMatchesNoMatch() {

        Meeting meeting = new Meeting(1, "Project Review", "Project X", "Tomorrow");
        Assertions.assertFalse(meeting.matches("Meeting"));
    }

    @Test
    public void testMatchesMatchingQuery() {
        SimpleTask simpleTask = new SimpleTask(1, "Buy groceries");
        Assertions.assertTrue(simpleTask.matches("Buy groceries"));
    }

    @Test
    public void testMatchesNonMatchingQuery() {
        SimpleTask simpleTask = new SimpleTask(1, "Buy groceries");
        Assertions.assertFalse(simpleTask.matches("Clean house"));
    }

    @Test
    public void testMatchesReturnsFalse() {
        Task task = new SimpleTask(1, "Buy groceries");
        Assertions.assertFalse(task.matches("query"));
    }

    @Test
    public void testMatchesAlwaysFalse() {

        Task task = new SimpleTask(1, "Example Task");


        Assertions.assertFalse(task.matches("query"));
        Assertions.assertFalse(task.matches("123"));
        Assertions.assertFalse(task.matches("some text"));
    }


    @Test
    public void testMatchesWithNonEmptyQuery() {
        Task task = new SimpleTask(1, "Example Task");
        Assertions.assertFalse(task.matches("some non-empty query"));
    }


}
