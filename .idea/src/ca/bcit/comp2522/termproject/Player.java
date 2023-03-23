package ca.bcit.comp2522.termproject;

public class Player extends ca.bcit.comp2522.termproject.Character {
    private int XP_CAP = 100;

    private int special;
    // special requires String description, int stat, int coolDown -> make a new Class for Special?
    // so like a Card but has the extra cool down. Can be a card, just decrease the cool down every turn
    private int level;

    public Player(String name) {
        level = 1;
    }

    public int useSpecial() {
        // todo: useSpecial will do an action (atk/heal/defend) -> does not decay
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
