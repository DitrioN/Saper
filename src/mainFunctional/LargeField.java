package mainFunctional;

import consoleEngine_pdp.*;

public class LargeField extends Request {
    LargeField () {
        setName("Сложный", "Hard");
    }

    protected void requestMenu () {
        MainMenu.setSaperFieldSize(30, 16);
        MainMenu.setSaperMine(99);
        MainMenu.startGame();
    }
}
