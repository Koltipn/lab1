package lab1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CheckType {

    // Метод для проверки всех типов данных из файла и их возврата в виде списка
    public List<Object> checkTypesFromFile(String filename) {
        List<Object> dataList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                dataList.add(parseLine(line)); // Добавляем результат парсинга строки
            }
        } catch (IOException ex) {
            System.out.println("Ошибка чтения файла: " + ex.getMessage());
        }
        return dataList; // Возвращаем все собранные данные
    }

    // Вспомогательный метод для парсинга строки и определения её типа
    private Object parseLine(String line) {
        // Проверка на Integer (целые числа)
        if (line.matches("^-?\\d+$")) {
            try {
                return Integer.parseInt(line); // Пытаемся записать как Integer
            } catch (NumberFormatException e) {
                // Если число слишком большое для Integer, пробуем Long
                return Long.parseLong(line);
            }
        }
        // Проверка на Double (вещественные числа)
        else if (line.matches("^-?\\d*\\.\\d+$")) {
            return Double.parseDouble(line);
        }
        // Если не число, возвращаем как строку
        return line;
    }
}