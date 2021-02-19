package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

//        System.out.println("Printing deadlines");
//        printDeadlines(tasksData);
//
//        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
//
//        printData(tasksData);
//        printDataWithSteams(tasksData);
        printDeadlinesWithSteams(tasksData);
        System.out.println(countDeadlinesWithStream(tasksData));
        printDeadlineWithSortedSteams(tasksData);
        ArrayList<Task> filteredList = filterTaskByString(tasksData, "11");
        printData(filteredList);
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }
    private static int countDeadlinesWithStream(ArrayList<Task> tasks){
        int count = (int)tasks.stream()
                .filter((t) -> t instanceof Deadline)
                .count();
        return count;
    }
    public static void printDataWithSteams(ArrayList<Task> tasksData){
        tasksData.stream()
                .forEach(System.out::println);
    }
    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDeadlinesWithSteams(ArrayList<Task> tasksData) {
        System.out.println("streaming");
        tasksData.stream()
                .filter((t) -> t instanceof Deadline)
                .forEach(System.out::println);
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlineWithSortedSteams(ArrayList<Task> tasks){
        tasks.stream()
                .filter((t) -> t instanceof Deadline)
                .sorted((a, b) -> a.getDescription().toLowerCase().compareTo(b.getDescription().toLowerCase()))
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterTaskByString(ArrayList<Task> tasks, String filterString){
        ArrayList<Task> filterList = (ArrayList<Task>) tasks.stream()
                .filter((s) -> s.getDescription().contains(filterString))
                .collect(toList());
        return filterList;
    }
}
