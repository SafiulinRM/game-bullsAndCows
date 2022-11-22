package app.services.numberServices;

import java.util.Random;

/**
 * Класс генерации 4х значного числа
 */
public final class GeneratorNumber {
    /**
     * Генерация 4 х значного числа с уникальными цифрами
     *
     * @return 4 х значное число
     */
    public static int[] generateNumber() {
        int[] number = new int[4];
        for (int i = 0; i < number.length; i++) {
            number[i] = getUniqDigit(number);
        }
        return number;
    }

    /**
     * Получаем уникальную цифру, если цифра в массиве не уникальна, метод вызывается еще раз,
     * работает рекурсивность
     *
     * @param number массив цифр
     * @return возвращает уникальную цифру
     */
    private static int getUniqDigit(int[] number) {
        int point = generateDigit();
        for (Integer digit : number) {
            if (digit != null) {
                if (digit == point) {
                    point = getUniqDigit(number);
                }
            }
        }
        return point;
    }

    /**
     * Генерация рандомной цифры
     *
     * @return возвращает цифру
     */
    private static int generateDigit() {
        Random random = new Random();
        return random.nextInt(9);
    }
}
