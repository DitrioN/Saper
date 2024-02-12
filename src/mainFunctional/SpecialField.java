package mainFunctional;

import consoleEngine_pdp.*;

public class SpecialField extends Request {
    SpecialField () {
        setName("Особый", "Special");
    }

    protected void requestMenu () {
        StringBuilder str1 = new StringBuilder("\n...- Выбран особый режим игры, в нём вы сами задаёте параметры поля и кол-во мин на нём.");
        str1.append("\n1/3 шаг - Введите ширину поля:");

        System.out.println(str1);
        int width = Utils.inputInt();
        if (width < 30) {
            System.out.println("\n2/3 шаг - Введите высоту поля:");
            int height = Utils.inputInt();
            if (height < 30) {
                System.out.println("\n3/3 шаг - Введите кол-во мин:");
                int mine = Utils.inputInt();
                if (mine < (width * height) / 2) {
                    MainMenu.setSaperFieldSize(width, height);
                    MainMenu.setSaperMine(mine);
                    MainMenu.startGame();
                } else {
                    System.out.println("\n...- Кол-во мин не должно занимать больше половины клеток поля:");
                    requestMenu();
                }
            } else {
                System.out.println("\n...- Слишком большая высота. Максимум 30 клеток.");
                requestMenu();
            }
        } else {
            System.out.println("\n...- Слишком большая ширина. Максимум 30 клеток.");
            requestMenu();
        }
    }
}
