package app;

import app.services.GameServiceImpl;
import app.services.interfaces.GameService;

/**
 * Класс, в котором запускается игра.
 */
public class GameApp {
    /**
     * Запуск класса игрового сервиса
     * @param args массив
     */
    public static void main(String[] args) {
        GameService gameService = new GameServiceImpl();
        gameService.run();
    }
}
