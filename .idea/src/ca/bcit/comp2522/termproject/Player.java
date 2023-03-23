package ca.bcit.comp2522.termproject;

public class Player extends Character{
    private int XP_CAP = 100;

    private int special;
    private int level;

    public Player(String name) {
        super(name);
        level = 1;
    }

    public int useSpecial() {
        // todo: useSpecial will do an action (atk/heal/defend) and then decay
        return 0;
    }

    public void gainXp(int rewardXP) {
        // add xp to current character xp
        // if xp is greater than limit, level increases

        System.out.println("You have gained " + rewardXP + "XP!\n" +
                "You are currently Lvl. " + level + "!\n" +
                "You need " + (rewardXP % XP_CAP) + " XP more to level up."); // actually modulo by current XP
    }
}
