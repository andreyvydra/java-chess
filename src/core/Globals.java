package core;

public class Globals {
    public static String letters = "abcdefgh";
    public static String[][] standardField = {
            {"bR", "bN", "bB", "bQ", "bK", "bB", "bN", "bR"},
            {"bP", "bP", "bP", "bP", "bP", "bP", "bP", "bP"},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"wP", "wP", "wP", "wP", "wP", "wP", "wP", "wP"},
            {"wR", "wN", "wB", "wQ", "wK", "wB", "wN", "wR"}
    };

    public static String startMessage =
            """
Шахматы v0.1
Игру начинают белые фигуры. Обозначение фигур следующее
w, b - цвета фигур (белый и чёрный соответственно)
P - пешка
K - король
Q - Королева
R - ладья
B - слон
N - конь
Ход записывается в консоль следующим видом: e2 e4
Чтобы сделать рокировку: 00 или 000 (в короткою и длинную сторону соответственно)
Приятной игры!""";

    public static String incorrectInput = "Неверно введён ход, попробуйте ещё раз";

    public static String impossibleMove = "Такой ход невозможен! Введите другой ход";
    public static String outsideBoard = "  +---------------------------------------+"; // Отступ слева для выравнивания
    public static String insideBoard = "  |---------------------------------------|"; // Отступ слева для выравнивания
    public static String lettersUnderBoard = "    A    B    C    D    E    F    G    H";

}
