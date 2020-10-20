package swingy.control;

import java.io.File;
import java.util.Scanner;

import swingy.model.Chance;
import swingy.model.ChooseHero;
import swingy.model.CreateMap;
import swingy.model.NewHero;
import swingy.model.SimulateFight;
import swingy.model.VillainCreator;
import swingy.model.update;
import swingy.view.ConsoleViews;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ConsoleControl {

    private ConsoleViews consoleViews = new ConsoleViews();
    private ChooseHero hero;
    private VillainCreator villain;
    private SimulateFight simulate;
    private Chance chance = new Chance();
    private int x, y, e, num, count;
    private Scanner input = new Scanner(System.in);
    static Validator validator;
    private String putS;

    public ConsoleControl() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        Control();
    }

    public Boolean SelectHero() {
        hero = new ChooseHero(num);
        Set<ConstraintViolation<ChooseHero>> validationErrors = validator.validate(hero);
        if (!validationErrors.isEmpty()) {
            for (ConstraintViolation<ChooseHero> error : validationErrors) {
                System.out.println(String.format("%s - %s [%s]", error.getPropertyPath().toString(), error.getMessage(),
                        hero.getName()));
            }
            validationErrors.clear();
            return false;
        }
        return true;
    }

    public Boolean MakeHero(String name, int cl) {
        NewHero nHero = new NewHero(name, cl);
        Set<ConstraintViolation<NewHero>> validationErrors = validator.validate(nHero);
        if (!validationErrors.isEmpty()) {
            for (ConstraintViolation<NewHero> error : validationErrors) {
                System.out.println(String.format("%s - %s [%s]", error.getPropertyPath().toString(), error.getMessage(),
                        nHero.getName()));
            }
            validationErrors.clear();
            return false;
        }
        nHero.Save();
        return true;
    }

    private int One() {
        boolean t = false;
        putS = null;
        do {
            consoleViews.intstruction2();
            String name = input.nextLine();
            consoleViews.intstruction9();
            putS = input.nextLine();
            if (!name.equals("") && putS.length() == 1) {
                char c = putS.charAt(0);
                if (Character.isDigit(c)) {
                    int cl = Integer.parseInt(putS);
                    t = MakeHero(name, cl);
                }
            }
        } while (t == false);
        return 1;
    }

    private void Control() {

        putS = "d";
        int v = 0;

        while (v == 0) {
            consoleViews.intstruction1();
            putS = input.nextLine();
            if (putS.equals("1")) {
                v = One();
            } else if (putS.equals("2")) {
                v = 1;
            }
        }
        consoleViews.intstruction3();
        String st = "";
        try {
            File stats = new File("src/main/java/swingy/control/stats.txt");

            if (!stats.exists()) {
                System.out.println("and the");
            }
            Scanner myReader = new Scanner(stats);

            int i = 0;
            while (myReader.hasNextLine()) {
                String s = myReader.nextLine();
                String ss[] = s.split(",");
                if (i == 0)
                    st = ss[0];
                else
                    st = st + "," + ss[0];
                i++;
            }
            myReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (st.equals("")) {
            boolean t = false;
            putS = null;
            do {
                consoleViews.intstruction2();

                String name = input.nextLine();
                consoleViews.intstruction9();
                int cl = input.nextInt();
                t = MakeHero(name, cl);
            } while (t == false);
            v = 1;
        } else {
            while (v == 1) {
                consoleViews.intstruction6(st);
                putS = input.nextLine();
                int t = 0;

                putS = putS.trim();
                for (char m : putS.toCharArray()) {
                    if (!Character.isDigit(m)) {
                        t++;
                    }
                }
                if (putS.length() > 0 && putS.split(" ").length == 1 && t == 0) {
                    num = Integer.parseInt(putS);
                    if (num >= 0 && num < st.split(",").length) {
                        v = 0;
                    } else {
                        v = 1;
                    }
                }
            }
        }

        if (SelectHero()) {

        }
        consoleViews.intstruction7(hero.Stats());
        do {
            consoleViews.intstruction5();
            putS = input.nextLine();
            if (putS.equals("1")) {
                CreateMap map = new CreateMap(hero.getLevel());
                e = map.getMap();
                y = e / 2 + 1;
                x = e / 2 + 1;
                count = hero.getLevel() * 10;
                int t = 0;
                while (y > 0 && x > 0 && y <= e && x <= e && t == 0) {
                    consoleViews.intstruction4();
                    putS = input.nextLine();
                    switch (putS) {
                        case "n":
                            y++;
                            break;
                        case "w":
                            x--;
                            break;
                        case "s":
                            y--;
                            break;
                        case "e":
                            x++;
                            break;
                        default:
                            break;
                    }
                    if (chance.block(hero.getLevel()).equals("villain") && count > 0) {
                        consoleViews.intstruction8();
                        putS = input.nextLine();
                        villain = new VillainCreator(hero.getLevel());
                        if (putS.equals("1")) {
                            simulate = new SimulateFight(hero, villain);
                            if (simulate.getResult().equals("villain won")) {
                                t = 1;
                            } else if (simulate.getResult().equals("hero won")) {
                                if (!villain.getArtifact().equals("none")) {
                                    new update(num, villain.getArtifact(), villain.getScore());
                                }
                            }
                            consoleViews.intstruction7(simulate.getResult().toUpperCase());
                            consoleViews.intstruction7(hero.Stats());
                            consoleViews.intstruction7(villain.Stats());
                        } else {
                            if (chance.flight(hero, villain).equals("Caught")) {
                                simulate = new SimulateFight(hero, villain);
                                if (simulate.getResult().equals("villain won")) {
                                    t = 1;
                                } else if (simulate.getResult().equals("hero won")) {
                                    if (!villain.getArtifact().equals("none")) {
                                        new update(num, villain.getArtifact(), villain.getScore());
                                    }

                                }

                                consoleViews.intstruction7(simulate.getResult().toUpperCase());
                                consoleViews.intstruction7(hero.Stats());
                                consoleViews.intstruction7(villain.Stats());
                            }
                        }
                        count--;
                    }
                }
                if (t == 0) {
                    new update(num, "level", hero.getLevel());
                    new update(num, " ", hero.getLevel());
                }
            }
        } while (putS.equals("1"));
    }
}
