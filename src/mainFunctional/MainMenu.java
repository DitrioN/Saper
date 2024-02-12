package mainFunctional;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import consoleEngine_pdp.Utils;
import consoleEngine_pdp.Request;

public class MainMenu {
    private static final Request[] asc = {
            new SmallField(),
            new MediumField(),
            new LargeField(),
            new SpecialField()
    };
    private static Ceil[][] saperField;
    private static int mine;
    private static int opens;
    public static Scanner scan;

    public static void main (String[] args) {
        scan = new Scanner(System.in);
        firstMenu();
    }

    public static void firstMenu () {
        Utils.chooseMenu(getAsc(), "Выбери уровень сложности:");
    }

    public static void startGame () {
        StringBuilder str1 = new StringBuilder("\n");

        for (int f1 = 0; f1 < saperField.length; f1++) {
            for (int f2 = 0; f2 < saperField[f1].length; f2++) {
                if (saperField[f1][f2].isOpen()) {
                    switch (saperField[f1][f2].getType()) {
                        case mine:
                            str1.append(Utils.BLACK).append("X");
                            break;
                        case empty:
                            String[] clr = {"\u001B[0m", "\u001B[34m", "\u001B[32m", "\u001B[31m", "\u001B[34m", "\u001B[31m", "\u001B[36m", "\u001B[30m", "\u001B[37m"};
                            str1.append(clr[saperField[f1][f2].getRadius()]);
                            str1.append(saperField[f1][f2].getRadius());
                            break;
                    }
                } else {
                    str1.append(Utils.RESET).append("=");
                    if (saperField[f1][f2].getRadius() == 0) {

                    }
                }
                if (f2 + 1 < saperField[f1].length)
                    str1.append(" ");
            }
            if (f1 + 1 < saperField.length)
                str1.append("\n");
        }

        str1.append("\u001B[0m\n\n");
        str1.append("...- Введите координаты ячейки, которую хотите открыть.");
        str1.append("\n1/2 Шаг - № Столбца:");

        System.out.println(str1);
        int x = Utils.inputInt() - 1;

        System.out.println("\n2/2 Шаг - № Строки:");
        int y = Utils.inputInt() - 1;

        if ((x >= 0 && x < saperField.length) && (y >= 0 && y < saperField[x].length)) {
            if (! saperField[y][x].isOpen()) {
                if (saperField[y][x].getType() == CeilType.mine) {
                    System.out.println(Utils.RED + "\n...- БА-БАМ!!! Вы наступили на мину." + Utils.RESET);

                    StringBuilder str2 = new StringBuilder("\n");
                    for (int f1 = 0; f1 < saperField.length; f1++) {
                        for (int f2 = 0; f2 < saperField[f1].length; f2++) {
                                switch (saperField[f1][f2].getType()) {
                                    case mine:
                                        str2.append(Utils.RED).append("X");
                                        break;
                                    case empty:
                                        str2.append(Utils.WHITE);
                                        if (saperField[f1][f2].isOpen()) {
                                            str2.append(saperField[f1][f2].getRadius());
                                        } else {
                                            str2.append("=");
                                        }
                                        break;
                                }
                            if (f2 + 1 < saperField[f1].length)
                                str2.append(" ");
                        }
                        if (f1 + 1 < saperField.length)
                            str2.append("\n");
                    }
                    System.out.println(str2 + Utils.RESET);
                } else {
                    if (opens - 1 == mine) {
                        System.out.println(Utils.GREEN + "\n...- Поздравляю, вы победили!!!" + Utils.RESET);
                    } else {
                        if (saperField[y][x].getRadius() > 0) {
                            System.out.println("\n...- Ячейка пуста, но рядом что-то есть...");
                            opens -= 1;
                            saperField[y][x].setOpen(true);
                        } else {
                            System.out.println("\n...- Ячейка пуста, и это хорошо.");
                            openCeil(y, x);
                        }
                        startGame();
                    }
                }
            } else {
                System.out.println("\n...- Данная ячейка уже открыта, введите координаты другой ячейки.");
                startGame();
            }
        } else {
            System.out.println("\n\u001B[31m...- Введёные координаты выходят за рамки поля, повторите попытку.\u001B[0m");
            startGame();
        }
    }

    public static Request[] getAsc () {
        return asc;
    }

    public static void setSaperFieldSize (int width, int height) {
        opens = width * height;

        saperField = new Ceil[height][width];
        for (int f1 = 0; f1 < saperField.length; f1++) {
            for (int f2 = 0; f2 < saperField[f1].length; f2++) {
                saperField[f1][f2] = new Ceil();
            }
        }
    }

    public static void setSaperMine (int mine) {
        MainMenu.mine = mine;

        for (int f1 = 0; f1 < mine; f1++) {
            miner();
        }
        for (int f1 = 0; f1 < saperField.length; f1++) {
            for (int f2 = 0; f2 < saperField[f1].length; f2++) {
                if (saperField[f1][f2].getType() == CeilType.mine) {
                    for (int f3 = Math.max(0, f1 - 1); f3 < Math.min(saperField.length, f1 + 2); f3++) {
                        for (int f4 = Math.max(0, f2 - 1); f4 < Math.min(saperField[f3].length, f2 + 2); f4++) {
                            saperField[f3][f4].addRadius();
                        }
                    }
                }
            }
        }
    }

    private static void miner () {
        int rndY = ThreadLocalRandom.current().nextInt(0, saperField.length - 1);
        int rndX = ThreadLocalRandom.current().nextInt(0, saperField[rndY].length - 1);

        if (saperField[rndY][rndX].getType() == CeilType.empty) {
            saperField[rndY][rndX].setType(CeilType.mine);
        } else {
            miner();
        }
    }

    private static void openCeil (int y, int x) {
        opens -= 1;
        saperField[y][x].setOpen(true);

        for (int f1 = Math.max(0, y - 1); f1 < Math.min(y + 2, saperField.length); f1++) {
            for (int f2 = Math.max(0, x - 1); f2 < Math.min(x + 2, saperField[f1].length); f2++) {
                if (! (f1 == y && f2 == x) && ! saperField[f1][f2].isOpen()) {
                    if (saperField[f1][f2].getRadius() == 0)
                        openCeil(f1, f2);
                }
                if ((f1 == y || f2 == x) && saperField[f1][f2].getRadius() > 0)
                    saperField[f1][f2].setOpen(true);
            }
        }
    }
}
