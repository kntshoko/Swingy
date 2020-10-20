package swingy.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.validation.constraints.Min;

public class ChooseHero {

    private String hero[];
    private String name;
    private String hClass;
    private int attack;
    private int defance;
    private int hits;
    private int level;
    private int experience;

    @Min(value = 0, message = "input should be 0 and abave")
    private int i;

    public ChooseHero(int i) {

        this.i = i;
        Select();
    }

    private void Select() {
        ArrayList<String[]> options = new ArrayList<String[]>();
        try {
            File stats = new File("src/main/java/swingy/control/stats.txt");
            Scanner myReader = new Scanner(stats);
            while (myReader.hasNextLine()) {
                String s = myReader.nextLine();
                String ss[] = s.split(",");
                options.add(ss);
            }
            myReader.close();
            hero = options.get(i);
            name = hero[0];
            hClass = hero[1];
            level = Integer.parseInt(hero[2]);
            experience = Integer.parseInt(hero[3]);
            attack = Integer.parseInt(hero[4]);
            defance = Integer.parseInt(hero[5]);
            hits = Integer.parseInt(hero[6]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getHero() {
        return hero;
    }

    public String getName() {
        return name;
    }

    public String getHeroClass() {
        return hClass;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefance() {
        return defance;
    }

    public int getHits() {
        return hits;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public String Stats() {
        String s;

        s = ("**************************\n");
        s = s + ("       HERO'S STATS\n");
        s = s + ("**************************\n");
        s = s + ("\n NAME\t - " + name);
        s = s + ("\n CLASS\t - " + hClass);
        s = s + ("\n LEVEL\t - " + level);
        s = s + ("\n EXPERIENCE - " + experience);
        s = s + ("\n ATTACK\t - " + attack);
        s = s + ("\n DEFANCE\t - " + defance);
        s = s + ("\n HITS\t - " + hits);
        s = s + ("\n**************************\n");
        s = s + ("**************************\n");
        return s;
    }
}
