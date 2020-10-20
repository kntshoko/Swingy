package swingy.model;

import static java.lang.Math.random;

public class VillainCreator {

    String villain[] = new String[5];
    private int attack;
    private int defance;
    private int hits;
    private int score;
    private String artifact;

    public VillainCreator(int level) {

        String artifacts[] = { "weapon", "armor", "helm", "none" };
        artifact = artifacts[(int) (random() * (4000) % 4)];
        attack = (int) (random() * (5 * level));
        defance = (int) (random() * (5 * level));
        hits = (int) (random() * (5 * level));
        if (artifact != "none")
            score = (int) (random() * (3));
        else
            score = 0;
    }

    public String getArtifact() {
        return artifact;
    }

    public int getDefance() {
        return defance;
    }

    public int getHits() {
        return hits;
    }

    public int getAttack() {
        return attack;
    }

    public int getScore() {
        return score;
    }

    public String Stats() {
        String s;

        s = ("**************************\n");
        s = s + ("       VILLAIN STATS\n");
        s = s + ("**************************\n\n");
        s = s + ("\n ATTACK\t- " + attack);
        s = s + ("\n DEFANCE\t- " + defance);
        s = s + ("\n HITS\t- " + hits);
        s = s + ("\n ARTIFACT\t- " + artifact);
        s = s + ("\n POINTS\t- " + score);
        s = s + ("\n**************************\n");
        s = s + ("**************************\n");
        return s;
    }
}
