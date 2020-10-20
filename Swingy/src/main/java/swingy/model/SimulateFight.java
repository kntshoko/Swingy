package swingy.model;

public class SimulateFight {

    private String result;
    
    public SimulateFight(ChooseHero hero, VillainCreator villain){
        int herohealth = hero.getDefance() - villain.getHits() - villain.getAttack();
        int villainHealth = villain.getDefance() - hero.getHits() - hero.getAttack();

        if(herohealth >= villainHealth)
        {
            result = "hero";
        }
        else{
            result = "villain";
        }
    }

    public String getResult(){
        return result + " won";
    }
}
