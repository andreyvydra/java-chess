package core;

public class Globals {
    public static String letters = "abcdefgh";
    public static String[][] standardField = {
            {"bR", "bB", "bN", "bQ", "bK", "bN", "bB", "bR"},
            {"bP", "bP", "bP", "bP", "bP", "bP", "bP", "bP"},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"wP", "wP", "wP", "wP", "wP", "wP", "wP", "wP"},
            {"wR", "wB", "wN", "wQ", "wK", "wN", "wB", "wR"}
    };
    public static String outsideBoard = "  +---------------------------------------+"; // Отступ слева для выравнивания
    public static String insideBoard = "  |---------------------------------------|"; // Отступ слева для выравнивания
    public static String lettersUnderBoard = "    A    B    C    D    E    F    G    H";
}
