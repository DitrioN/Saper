package mainFunctional;

import consoleEngine_pdp.*;

public class MediumField extends Request {
    MediumField () {
        setName("Средний", "Medium");
    }

    protected void requestMenu () {
        MainMenu.setSaperFieldSize(16, 16);
        MainMenu.setSaperMine(40);
        MainMenu.startGame();
    }
}
