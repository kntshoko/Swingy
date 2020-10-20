package swingy;

import swingy.control.ConsoleControl;
import swingy.control.GuiControl;

public class Swingy {

    public static void main(String[] args) {
        if (args.length < 1) {
            return;
        } else if (args[0].equals("gui")) {
            new GuiControl();
            return;
        } else if (args[0].equals("console")) {
            new ConsoleControl();
            return;
        }
    }
}
