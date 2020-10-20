package swingy.control;

import swingy.model.Chance;
import swingy.model.ChooseHero;
import swingy.model.CreateMap;
import swingy.model.SimulateFight;
import swingy.model.VillainCreator;
import swingy.model.NewHero;
import swingy.model.update;
import swingy.view.GuiViews;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class GuiControl {

    private ChooseHero hero;
    private Chance chance = new Chance();
    private VillainCreator villain;
    private SimulateFight simulate;
    private GuiViews guiViews;
    private int x, y, e, num, count;
    static Validator validator;

    public GuiControl() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        guiViews = new GuiViews();
        guiViews.B1().addActionListener(new Landing());
        guiViews.B2().addActionListener(new Choose());
        guiViews.B3().addActionListener(new Create());
        guiViews.B4().addActionListener(new Select());
        guiViews.B5().addActionListener(new Save());
        guiViews.B6().addActionListener(new Play());
        guiViews.B7().addActionListener(new South());
        guiViews.B8().addActionListener(new West());
        guiViews.B9().addActionListener(new East());
        guiViews.B10().addActionListener(new North());
        guiViews.B11().addActionListener(new Run());
        guiViews.B12().addActionListener(new Fight());
        guiViews.B13().addActionListener(new Continue());
    }

    public class Landing implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            guiViews.Landing();
        }
    }

    public class Choose implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            guiViews.Choose();
        }
    }

    public class Create implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            guiViews.Create();
        }
    }

    public Boolean MakeHero(String name, int cl) {
        NewHero nHero = new NewHero(name, cl);
        Set<ConstraintViolation<NewHero>> validationErrors = validator.validate(nHero);
        if (!validationErrors.isEmpty()) {
            for (ConstraintViolation<NewHero> error : validationErrors) {
                System.out.println(String.format("%s - %s [%s]", error.getPropertyPath().toString(), error.getMessage(),
                        nHero.getName()));
                guiViews.setMessage(String.format("%s - %s [%s]", error.getPropertyPath().toString(),
                        error.getMessage(), nHero.getName()));
            }
            validationErrors.clear();
            return false;
        }
        nHero.Save();
        return true;
    }

    public class Save implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (!MakeHero(guiViews.getView(), guiViews.getType()))
                guiViews.Create();
            else
                guiViews.Choose();
        }
    }

    public class North implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            y++;
            if (y > e) {
                new update(num, "level", hero.getLevel());
                new update(num, " ", hero.getLevel());
                guiViews.Stats(hero.Stats());
            } else if (chance.block(hero.getLevel()).equals("villain") && count > 0) {
                guiViews.Fight(x, y, e);
            } else
                guiViews.Play(x, y, e);
        }
    }

    public class West implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            x--;
            if (x == 0) {
                new update(num, "level", hero.getLevel());
                new update(num, " ", hero.getLevel());
                guiViews.Stats(hero.Stats());
            } else if (chance.block(hero.getLevel()).equals("villain") && count > 0) {
                guiViews.Fight(x, y, e);
            } else {
                guiViews.Play(x, y, e);
            }
        }
    }

    public class East implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            x++;
            if (x > e) {
                new update(num, "level", hero.getLevel());
                new update(num, " ", hero.getLevel());
                guiViews.Stats(hero.Stats());
            } else if (chance.block(hero.getLevel()).equals("villain") && count > 0) {
                guiViews.Fight(x, y, e);
            } else
                guiViews.Play(x, y, e);
        }
    }

    public class South implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            y--;
            if (y < 1) {
                new update(num, "level", hero.getLevel());
                new update(num, " ", hero.getLevel());
                guiViews.Stats(hero.Stats());
            } else if (chance.block(hero.getLevel()).equals("villain") && count > 0) {
                guiViews.Fight(x, y, e);
            } else
                guiViews.Play(x, y, e);
        }
    }

    public class Select implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            num = guiViews.getNum();
            hero = new ChooseHero(num);
            CreateMap map = new CreateMap(hero.getLevel());
            e = map.getMap();
            y = e / 2 + 1;
            x = e / 2 + 1;
            guiViews.Stats(hero.Stats());
        }
    }

    public class Play implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            hero = new ChooseHero(num);
            CreateMap map = new CreateMap(hero.getLevel());
            e = map.getMap();
            y = e / 2 + 1;
            x = e / 2 + 1;
            count = hero.getLevel() * 10;
            guiViews.Play(x, y, e);
        }
    }

    public class Run implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            villain = new VillainCreator(hero.getLevel());
            if (chance.flight(hero, villain).equals("Caught")) {
                simulate = new SimulateFight(hero, villain);
                if (simulate.getResult().equals("villain won")) {
                    guiViews.Results(hero.Stats(), simulate.getResult(), villain.Stats());
                } else if (simulate.getResult().equals("hero won")) {
                    if (!villain.getArtifact().equals("none")) {
                        new update(num, villain.getArtifact(), villain.getScore());
                    }
                    count--;
                    guiViews.Results(hero.Stats(), simulate.getResult(), villain.Stats());
                }
            }
        }
    }

    public class Fight implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            villain = new VillainCreator(hero.getLevel());
            simulate = new SimulateFight(hero, villain);
            if (simulate.getResult().equals("villain won")) {
                guiViews.Results(hero.Stats(), simulate.getResult(), villain.Stats());
            } else if (simulate.getResult().equals("hero won")) {
                if (!villain.getArtifact().equals("none")) {
                    new update(num, villain.getArtifact(), villain.getScore());
                }
                count--;
                guiViews.Results(hero.Stats(), simulate.getResult(), villain.Stats());
            }
            ;
        }
    }

    public class Continue implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            hero = new ChooseHero(num);
            if (simulate.getResult().equals("villain won")) {
                guiViews.Stats(hero.Stats());
            } else {
                guiViews.Play(x, y, e);
            }
        }
    }
}
