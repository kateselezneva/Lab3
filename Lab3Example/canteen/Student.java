package canteen;

import java.util.HashMap;
import java.util.Map;

class Student {
    private final String name; // Имя студента
    private final Map<String, Boolean> labs = new HashMap<>(); // Лабораторные работы и их статус

    // Конструктор студента
    public Student(String name) {
        this.name = name;
    }

    // Добавить лабораторную работу
    public void addLab(String labName) {
        labs.putIfAbsent(labName, false);
    }

    // Отметить лабораторную работу как выполненную
    public void markLabAsCompleted(String labName) {
        if (labs.containsKey(labName)) {
            labs.put(labName, true);
        } else {
            System.out.println("Лабораторная работа \"" + labName + "\" не найдена у студента " + name + ".");
        }
    }

    // Показать статус лабораторных работ
    public void showStatus() {
        System.out.println("Студент: " + name);
        for (Map.Entry<String, Boolean> entry : labs.entrySet()) {
            System.out.println("\t" + entry.getKey() + ": " + (entry.getValue() ? "Выполнено" : "Не выполнено"));
        }
    }
}
