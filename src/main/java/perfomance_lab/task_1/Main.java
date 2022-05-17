package perfomance_lab.task_1;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        ArrayList<Integer> newMas = new ArrayList<>();
        int summ = 0;

        // создаем массив
        Integer[] mas = new Integer[n];
        if(m>n) {
            // Заполняем массив циклично добавляя недостающие элементы
            mas = new Integer[n + (m-n)];
            for (int i = 0; i < mas.length; i++) {
                if((i+1)>=n) {
                    mas[i] = i%n+1;
                    continue;
                }
                mas[i] = i+1;
            }
        } else {
            for (int i = 0; i < mas.length; i++) {
                mas[i] = i+1;
            }
        }



        // заполняем лист цифрами нужного отрезка и нужной цикличностью
        int delta = 0;
        for (int i = 0; i < m; ) {
            newMas.add(mas[(i+delta)%n]);
            i++;
            if(i == m) {
                if(newMas.get(newMas.size()-1) == mas[0]){
                    break;
                }
                delta = i - 1 + delta;
                i = 0;
            }
        }

        System.out.print("\nНаш путь: ");
        for (int i = 0; i < newMas.size(); i = i+m) {
            System.out.print(newMas.get(i));
        }

        for (Integer i :newMas) {
            summ= summ + i;
        }
        System.out.println();
        System.out.println(" Длина всего пути " + summ);
    }


}
