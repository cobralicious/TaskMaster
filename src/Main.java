import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        // Список задач
        ArrayList<String> tasks = new ArrayList<>();

        while (running) {
            System.out.println("\u001B[36m┌───────────────────────────────────┐\u001B[0m");
            System.out.println("\u001B[36m│            \u001B[33mMAIN MENU\u001B[36m              │\u001B[0m");
            System.out.println("\u001B[36m├───────────────────────────────────┤\u001B[0m");
            System.out.println("\u001B[32m│  1️⃣  📌 Add a new task            │\u001B[0m");
            System.out.println("\u001B[32m│  2️⃣  📋 Show all tasks            │\u001B[0m");
            System.out.println("\u001B[32m│  3️⃣  ✏  Edit a task               │\u001B[0m");
            System.out.println("\u001B[32m│  4️⃣  ❌ Delete a task             │\u001B[0m");
            System.out.println("\u001B[32m│  5️⃣  📜 Show task history         │\u001B[0m");
            System.out.println("\u001B[32m│  6️⃣  🗑  Clear all tasks           │\u001B[0m");
            System.out.println("\u001B[31m│  7️⃣  🔴 Exit                      │\u001B[0m");
            System.out.println("\u001B[36m└───────────────────────────────────┘\u001B[0m");
            System.out.print("\u001B[35mChoose an option: \u001B[0m");

            String choose = sc.nextLine();
            switch (choose) {
                case "1":
                    addTask(tasks);
                    break;
                case "2":
                    showTasks(tasks);
                    break;
                case "3":
                    editTask(tasks);
                    break;
                case "4":
                    deleteTask(tasks);
                    break;
                case "5":
                    showHistory();
                    break;
                case "6":
                    clearAllTasks(tasks);
                    break;
                case "7":
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Метод для добавления задачи
    public static void addTask(ArrayList<String> tasks) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\u001B[34mEnter task name: \u001B[0m");
        String task = sc.nextLine();
        tasks.add(task);
        System.out.println("\u001B[32mTask added: \"" + task + "\"\u001B[0m");

        // Сохраняем задачу в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("history.txt", true))) {
            writer.write(task);
            writer.newLine();  // Переход на новую строку после каждой задачи
        } catch (IOException e) {
            System.out.println("\u001B[31mError saving task to file.\u001B[0m");
        }
    }

    public static void showTasks(ArrayList<String> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("\u001B[31mNo tasks available.\u001B[0m");  // Красный цвет для сообщения
        } else {
            System.out.println("\u001B[36mYour tasks:\u001B[0m");  // Голубой цвет для заголовка
            System.out.println("\u001B[32m────────────────────────\u001B[0m");  // Зелёная линия
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\u001B[33m" + (i + 1) + ".\u001B[0m \u001B[37m" + tasks.get(i) + "\u001B[0m");  // Жёлтые номера и белый текст задач
            }
            System.out.println("\u001B[32m────────────────────────\u001B[0m");  // Зелёная линия в конце
        }

    }

    public static void editTask(ArrayList<String> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("\u001B[31mNo tasks available.\u001B[0m");  // Красный цвет для сообщения
        } else {
            System.out.println("\u001B[36mYour tasks:\u001B[0m");  // Голубой цвет для заголовка
            System.out.println("\u001B[32m────────────────────────\u001B[0m");  // Зелёная линия
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\u001B[33m" + (i + 1) + ".\u001B[0m \u001B[37m" + tasks.get(i) + "\u001B[0m");  // Жёлтые номера и белый текст задач
            }
            System.out.println("\u001B[32m────────────────────────\u001B[0m");  // Зелёная линия в конце
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("\u001B[34mEnter the task number you want to edit: \u001B[0m");
        int taskIndex = sc.nextInt() - 1;  // Получаем индекс задачи для редактирования (с учетом того, что индексация с 1)
        sc.nextLine();  // Очищаем буфер после nextInt()

        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            System.out.print("\u001B[34mEnter new task name: \u001B[0m\u001B[32m");
            String editedTask = sc.nextLine();
            tasks.set(taskIndex, editedTask);  // Заменяем старую задачу на новую
            System.out.println("\u001B[36mTask \u001B[33m" + (taskIndex + 1) + "\u001B[36m has been edited to: \u001B[32m" + editedTask + "\u001B[0m");
        } else {
            System.out.println("\u001B[31mInvalid task number.\u001B[0m");
        }
    }

    public static void deleteTask(ArrayList<String> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("\u001B[31mNo tasks available.\u001B[0m");  // Красный цвет для сообщения
        } else {
            System.out.println("\u001B[36mYour tasks:\u001B[0m");  // Голубой цвет для заголовка
            System.out.println("\u001B[32m────────────────────────\u001B[0m");  // Зелёная линия
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\u001B[33m" + (i + 1) + ".\u001B[0m \u001B[37m" + tasks.get(i) + "\u001B[0m");  // Жёлтые номера и белый текст задач
            }
            System.out.println("\u001B[32m────────────────────────\u001B[0m");  // Зелёная линия в конце
        }

        System.out.print("\u001B[32mEnter the task number you want to delete: \u001B[0m");
        Scanner sc = new Scanner(System.in);
        int taskNumber = sc.nextInt() - 1;  // Получаем индекс задачи для удаления (с учетом того, что индексация с 1)

        // Проверяем, что номер задачи корректный
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            System.out.println("\u001B[34mTask \"" + tasks.get(taskNumber) + "\" deleted.\u001B[0m");
            tasks.remove(taskNumber);  // Удаляем задачу из списка

            // Сохраняем обновленный список задач в файл
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("history.txt"))) {
                for (String task : tasks) {
                    writer.write(task);
                    writer.newLine();  // Переход на новую строку после каждой задачи
                }
            } catch (IOException e) {
                System.out.println("\u001B[31mError saving tasks to file.\u001B[0m");
            }

        } else {
            System.out.println("\u001B[31mTask number " + (taskNumber + 1) + " not found.\u001B[0m");
        }
    }

    public static void clearAllTasks(ArrayList<String> tasks) {
        tasks.clear();  // Clear the task list
        System.out.println("\u001B[34mAll tasks have been cleared.\u001B[0m");

        // Clear the task history file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("history.txt"))) {
            // The file is cleared by opening it in write mode without appending
        } catch (IOException e) {
            System.out.println("\u001B[31mError clearing task history file.\u001B[0m");
        }
    }

    public static void showHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader("history.txt"))) {
            String line;
            if ((line = reader.readLine()) == null) {
                System.out.println("\u001B[31mNo task history available.\u001B[0m");  // Если файл пустой
            } else {
                System.out.println("\u001B[36mTask History:\u001B[0m");  // Голубой цвет для заголовка
                System.out.println("\u001B[32m────────────────────────\u001B[0m");  // Зелёная линия
                do {
                    System.out.println("\u001B[37m" + line + "\u001B[0m");  // Белый текст для задач из файла
                } while ((line = reader.readLine()) != null);
                System.out.println("\u001B[32m────────────────────────\u001B[0m");  // Зелёная линия в конце
            }
        } catch (IOException e) {
            System.out.println("\u001B[31mError reading history from file.\u001B[0m");
        }
    }



}
