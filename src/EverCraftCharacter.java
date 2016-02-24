
public class EverCraftCharacter {

    public enum Alignment {
        Good, Evil, Neutral
    }

    public enum LifeStatus {
        Alive, Dead
    }

    private String name;
    private Alignment alignment;
    private LifeStatus lifeStatus;
    private int armor = 10;
    private int experiencePoints = 0;
    private int hitPoints = 5;
    private Abilities abilities;

    public EverCraftCharacter(String name, Alignment alignment){
        this.name = name;
        this.alignment = alignment;
        this.lifeStatus = LifeStatus.Alive;
        this.abilities = new Abilities();
    }

    public void updateLifeStatus(){
        if (this.getHitPoints() <= 0){
            this.setLifeStatus(EverCraftCharacter.LifeStatus.Dead);
        }
    }

    private void setArmorPlusDexterity(){
        int dexterityScore = abilities.getDexterityScore();
        armor += abilities.getDexterityModifier(dexterityScore);
    }

    public int getAttackRollModifierCalculatePreTurnUpdate() {
        setArmorPlusDexterity();
        int level = getLevel();
        calculateHitPoints(level);
        int attackRollModifier = getAttackRollModifier(level);
        return attackRollModifier;
    }


    public void addExperiencePoints(int experiencePoints) {
        this.experiencePoints += experiencePoints;

    }

    public int getLevel() {
        int level = 1;
        if (experiencePoints != 0) {
            int fractionalLevel = (experiencePoints / 1000) + 1;
            level = (int) Math.floor(fractionalLevel);
        }
        return level;
    }

    private void calculateHitPoints(int level){
        int constitutionModifier = abilities.getConstitutionModifier(abilities.getConstitutionScore());
        hitPoints += constitutionModifier;

        for (int i = 1; i < level; i++) {
            hitPoints += (5 + constitutionModifier);
        }
        if (hitPoints < 1) {
            hitPoints = 1;
        }
    }

    private int getAttackRollModifier(int level) {
        int fractionalLevel = (level / 2);
        int attackRollModifier = (int)Math.floor(fractionalLevel);
        return attackRollModifier;
    }

    //Getters and Setters
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Alignment getAlignment(){
        return alignment;
    }

    public void setAlignment(Alignment alignment){
        this.alignment = alignment;
    }

    public int getArmor(){
        return armor;
    }


    public LifeStatus getLifeStatus(){
        return lifeStatus;
    }

    public void setLifeStatus(LifeStatus lifeStatus){
        this.lifeStatus = lifeStatus;
    }

    public Abilities getAbilities() { return abilities; }

    public int getExperiencePoints(){
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints){
        this.experiencePoints = experiencePoints;
    }


    public int getHitPoints(){
        return hitPoints;
    }

    public void setHitPoints(int hitPoints){
        this.hitPoints = hitPoints;
    }


}
