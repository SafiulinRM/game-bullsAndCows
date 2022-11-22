package app.services.numberServices;

import java.util.Scanner;

/**
 * Класс, который работает с введенным числом пользователя
 */
public final class InputNumberService {
    /**
     * Число, которое ввел пользователь, разложенное на цифры в массив
     */
    private static Integer[] userNumber;

    /**
     * ввести число пользователем
     */
    public static Integer[] enterNumber() {
        Scanner scanner = new Scanner(System.in);
        boolean numberIsOk = false;
        while (!numberIsOk) {
            System.out.println("Введите число");
            int number = scanner.nextInt();
            numberIsOk = validationInputNumber(number);
        }
        return userNumber;
    }

    /**
     * Валидация данных, которые ввел пользователь
     *
     * @param number число, введенное пользователем
     * @return подтверждение/отклонение, что можно продолжать дальше
     */
    private static boolean validationInputNumber(int number) {
        if (number < 0) {
            System.out.println("Некорректный запрос! Число не является положительным, ваше число: " + number);
            return false;
        } else if (number < 1000 || number > 9999) {
            System.out.println("Некорректный запрос! Число не является четырехзначным, ваше число: " + number);
            return false;
        } else if (!checkInputNumber(number)) {
            System.out.println("Некорректный запрос! Цифры в числе не являются разными, ваше число: " + number);
            return false;
        }
        return true;
    }

    /**
     * Проверка числа, что цифры в нем разные
     *
     * @param number число введенное пользователем
     * @return возвращает уникальные/не уникальные цифры в числе
     */
    private static boolean checkInputNumber(int number) {
        Integer[] digits = new Integer[4];
        for (int i = 3; i >= 0; i--) {
            digits[i] = number % 10;
            number = number / 10;
            if (!checkDigitUniq(i, digits)) {
                return false;
            }
        }
        userNumber = digits;
        return true;
    }

    /**
     * проверка цифры на уникальность в числе
     *
     * @param point  позиция цифры в числе
     * @param digits массив цифр числа
     * @return возвращает уникальная/не уникальная цифра в числе
     */
    private static boolean checkDigitUniq(int point, Integer[] digits) {
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != null && i != point) {
                if (digits[i].equals(digits[point]))
                    return false;
            }
        }
        return true;
    }
}
