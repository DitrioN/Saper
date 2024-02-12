package consoleEngine_pdp;

import mainFunctional.MainMenu;

public class Utils {
    public static final String RESET = "\u001B[0m", BLACK = "\u001B[30m", RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m", YELLOW = "\u001B[33m", BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m", CYAN = "\u001B[36m", WHITE = "\u001B[37m";

    public static void chooseMenu (Request[] request, String text) {
        StringBuilder str1 = new StringBuilder("\n...- ").append(text);
        for (int f1 = 0; f1 < request.length; f1++) {
            str1.append("\n   ").append(f1 + 1).append(". ");
            str1.append(request[f1].getName(0));
            str1.append((f1 + 1 < request.length) ? "," : ".");
        }

        System.out.println(str1);

        System.out.print("\n\u001B[32mВы - \u001B[0m");
        String myInput = MainMenu.scan.nextLine();
        boolean error = true;

        for (int f1 = 0; f1 < request.length; f1++) {
            for (String f2 : request[f1].getName()) {
                if (myInput.equalsIgnoreCase(f2) || myInput.equals(f1 + 1 + "")) {
                    request[f1].callMenu();
                    error = false;
                    break;
                }
            }
        }

        if (error) {
            System.out.println("\n...- Эм... Повтори-ка, я не понял.");
            MainMenu.firstMenu();
        }
    }

    public static int inputInt () {
        String str1 = "\n\u001B[32mВы - \u001B[0m";
        System.out.print(str1);

        try {
            return Integer.parseInt(MainMenu.scan.nextLine());
        } catch (NumberFormatException e) {
            String str2 = "\n\u001B[31m...- Некорректный ввод числа! Повторите попытку.\u001B[0m";
            System.out.println(str2);

            return inputInt();
        }
    }

    public static String inputStr () {
        String str1 = "\n\u001B[32mВы - \u001B[0m";
        System.out.println(str1);

        return MainMenu.scan.nextLine();
    }
}
