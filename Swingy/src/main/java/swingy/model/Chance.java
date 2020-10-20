package swingy.model;

import static java.lang.Math.random;

public class Chance {

    public String flight(ChooseHero hero, VillainCreator villain) {
        if (hero.getDefance() < villain.getDefance()) {
            return "Fleed";
        }
        return "Caught";
    }

    public String block(int level) {
        int c = (int) (random() * ((level * 23)));
        if (c % 2 == 0) {
            return "none";
        }
        return "villain";
    }
}