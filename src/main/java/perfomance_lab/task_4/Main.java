package perfomance_lab.task_4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
//        int[] mas = {0, 0, 0, 9};

        int[] mas = null;
        try {
            Path path = Paths.get(args[0]);
            if (Files.notExists(path)) {
                throw new FileNotFoundException("Файл не был найден - проверьте путь!");
            }
            mas = Files.lines(path).mapToInt(Integer::parseInt).toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        double average = IntStream.of(mas).average().getAsDouble();
        int averageMax = (int) Math.ceil(average); // среднее арифметическое округлённое до большего числа
        int averageMin = (int) Math.floor(average); // среднее арифметическое округлённое до меньшего числа
        int max = Arrays.stream(mas).max().getAsInt(); // максимальный
        int min = Arrays.stream(mas).min().getAsInt(); // минимальный
        int[] resultStep = {0, 0, 0, 0}; // массив для сохранения всех результатов операций

        resultStep[0] = getCountStepStrivingMasForNumber(Arrays.copyOf(mas, mas.length), averageMax); // высчитываем количество шагов для числа averageMax
        resultStep[1] = getCountStepStrivingMasForNumber(Arrays.copyOf(mas, mas.length), averageMin); // высчитываем количество шагов для числа averageMin
        resultStep[2] = getCountStepStrivingMasForNumber(Arrays.copyOf(mas, mas.length), max); // высчитываем количество шагов для числа max
        resultStep[3] = getCountStepStrivingMasForNumber(Arrays.copyOf(mas, mas.length), min); // высчитываем количество шагов для числа min

        // вывод
        System.out.println("Минимальное количество шагов: " + Arrays.stream(resultStep).min().getAsInt());

    }

    public static int getCountStepStrivingMasForNumber(int[] mas, int number) {
        int countStep = 0;
        for (int i = 0; i < mas.length; i++) {
            while (true) {
                if(mas[i] == number) {
                    break;
                }
                mas[i] = mas[i] > number ? --mas[i] : ++mas[i];
                countStep++;
            }
        }
        return countStep;
    }


}
