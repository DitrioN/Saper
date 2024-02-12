package mainFunctional;

import consoleEngine_pdp.*;

public class SmallField extends Request {
    SmallField () {
        setName("Лёгкий", "Easy");
    }

    protected void requestMenu () {
        MainMenu.setSaperFieldSize(9, 9);
        MainMenu.setSaperMine(10);
        MainMenu.startGame();
    }
}
