package app.services;

import app.services.interfaces.GameService;
import app.services.numberServices.GeneratorNumber;
import app.services.numberServices.InputNumberService;

import java.util.*;

/**
 * Реализация игрового сервиса {@link GameService}
 */
public class GameServiceImpl implements GameService {
    /**
     * Число, которое ввел пользователь, разложенное на цифры в массив
     */
    private Integer[] userNumber;
    /**
     * Список ответов на запросы пользователя
     */
    private final List<String> responses = new ArrayList<>();
    /**
     * Список введенных чисел пользователя
     */
    private final List<Integer[]> requests = new ArrayList<>();
    /**
     * Флаг, который говорит что игра закончена/не закончена
     */
    private boolean isCompleted = false;

    @Override
    public void run() {
        int[] compNumber = GeneratorNumber.generateNumber();
        // можно проверить какое число сгенерировал компьютер если раскомментить строчку
        System.out.println(Arrays.toString(compNumber));
        printMenu();
        while (!isCompleted) {
            try {
                userNumber = InputNumberService.enterNumber();
            } catch (InputMismatchException e) {
                System.out.println("Некорректный запрос! Введено не число");
                userNumber = InputNumberService.enterNumber();
            }
            System.out.println(Arrays.toString(userNumber));
            compareUserNumberWithCompNumber(userNumber, compNumber);
            getHistoryRequestsAndResponses();
        }
    }

    /**
     * Получить историю запросов и ответов
     */
    private void getHistoryRequestsAndResponses() {
        System.out.println("История запросов и ответов");
        for (int i = 0; i < requests.size(); i++) {
            System.out.println(Arrays.toString(requests.get(i)));
            System.out.println(responses.get(i));
        }
    }

    /**
     * Сравнение сгенерированного числа компьютером с введенным пользователем
     *
     * @param userNumber число введенное пользователем
     * @param compNumber число сгенерированное компьютером
     */
    private void compareUserNumberWithCompNumber(Integer[] userNumber, int[] compNumber) {
        int cows = 0;
        int bulls = 0;
        for (int i = 0; i < userNumber.length; i++) {
            if (userNumber[i] == compNumber[i]) {
                bulls++;
            } else {
                cows = countCows(cows, i, compNumber);
            }
        }
        getResult(bulls, cows);
    }

    /**
     * Подсчет коров(сколько цифр совпадает в числе компьютера с числом пользователя)
     * вызывается в цикле при подсчете быков
     *
     * @param cows       количество коров
     * @param i          позиция цифры в массиве(в числе)
     * @param compNumber число сгенерированное компьютером
     * @return количество коров
     */
    private int countCows(int cows, int i, int[] compNumber) {
        for (int digit : compNumber) {
            if (userNumber[i] == digit) {
                cows++;
            }
        }
        return cows;
    }

    /**
     * Сохранение результата в историю запросов и ответов
     *
     * @param bulls количество быков
     * @param cows  количество коров
     */
    private void getResult(int bulls, int cows) {
        String response;
        if (bulls == 4) {
            response = "Число угадано!";
            isCompleted = true;
        } else {
            response = bulls + " бык., " + cows + " кор.";
        }
        responses.add(response);
        requests.add(userNumber);
    }

    /**
     * Печатает правила игры и просит пользователя ввести число
     */
    private void printMenu() {
        System.out.println("Правила игры в 'Быки и коровы'\n" +
                "Я загадал четырехзначное положительное число так, чтобы все цифры числа были разные.\n" +
                "Вы должны" +
                " отгадать это число\n" +
                "В каждый ход называется число, тоже четырехзначное и с разными цифрами.\n" +
                "Если цифра из названного числа есть в отгадываемом числе, то эта ситуация называется «корова».\n" +
                "Если цифра из названного числа есть в отгадываемом числе и стоит в том же месте, то\n" +
                "эта ситуация называется «бык».\n" +
                "Например, первый игрок задумал 8569, а второй игрок назвал 8974. То первый игрок должен\n" +
                "сказать: «Один бык и одна корова» (1б,1к). ");
        System.out.println("Введи 4-х значное положительное число с разными цифрами");
    }
}
