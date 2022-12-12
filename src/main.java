// Реализовать простой калькулятор
// К калькулятору из предыдущего дз добавить логирование.

// Чубченко Светлана

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int num1 = GetInt(1); // число 1
        int num2 = GetInt(2); // число 2
        char operation = GetOperation(); // операция вычисления
        int result = Calc(num1, num2, operation);
        writeLog("Результат вычисления: " + result);
        System.out.println("Результат вычисления: " + result);
    }

    public static int GetInt(int n){
        System.out.print("Введите число " + n + ": ");
        int num;
        if(scanner.hasNextInt()){
            num = scanner.nextInt();
        } else {
            writeLog("Ошибка ввода");
            System.out.println("Ошибка ввода. Попробуйте еще раз.");
            scanner.next(); // рекурсия
            num = GetInt(n);
        }
        return num;
    }

    public static char GetOperation(){
        System.out.print("Введите операцию: ");
        char operation;
        if(scanner.hasNext()){
            operation = scanner.next().charAt(0);
        } else {
            writeLog("Ошибка ввода");
            System.out.println("Ошибка ввода. Попробуйте еще раз.");
            scanner.next(); // рекурсия
            operation = GetOperation();
        }
        return operation;
    }

    public static int Calc(int num1, int num2, char operation){
        int result;
        writeLog("Операция: " + operation + " Число 1: " + num1 + " Число 2: " + num2);
        switch (operation){
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                System.out.println("Операция не распознана. Повторите ввод.");
                result = Calc(num1, num2, GetOperation()); // рекурсия
        }
        return result;
    }

    public static void writeLog(String msg) {
        // задаем формат даты и времени
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.uuuu HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        // открываем файл для добавления строк
        try (PrintWriter p = new PrintWriter(new FileOutputStream("log.txt", true))) {
            // пишем в лог
            p.println(dtf.format(now) + "\t\t" + msg);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
