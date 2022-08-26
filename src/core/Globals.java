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
    public static String outsideBoard = "  +---------------------------------------+"; // Отступ слева для выравнивания
    public static String insideBoard = "  |---------------------------------------|"; // Отступ слева для выравнивания
    public static String lettersUnderBoard = "    A    B    C    D    E    F    G    H";
}
