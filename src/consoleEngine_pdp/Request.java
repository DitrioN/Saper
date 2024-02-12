package consoleEngine_pdp;

import mainFunctional.MainMenu;

public class Request {
    private String[] name;

    public Request () {
    }

    protected void requestMenu () {
    }

    public void callMenu () {
        requestMenu();
        MainMenu.firstMenu();
    }

    public void setName (String... name) {
        this.name = name;
    }

    public String[] getName () {
        return name;
    }

    public String getName (int i) {
        return name[i];
    }
}
