
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
    private int hitPoints = 5;
    private int experiencePoints = 0;
    private int level = 1;
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

    private void setHitPointsPlusConstitution(){
        int constitutionScore = abilities.getConstitutionScore();
        int newHitPoints = hitPoints + abilities.getConstitutionModifier(constitutionScore);
        if (newHitPoints < 1) {
            newHitPoints = 1;
        }
        hitPoints = newHitPoints;
    }

    public void preTurnUpdate() {
        setArmorPlusDexterity();
        setHitPointsPlusConstitution();
    }

    public void addExperiencePoints(int experiencePoints) {
        this.experiencePoints += experiencePoints;
        calculateLevel();
    }

    private void calculateLevel() {
        int fractionalLevel = (experiencePoints / 1000) + 1;
        level = (int)Math.floor(fractionalLevel);
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

    public int getHitPoints(){
        return hitPoints;
    }

    public void setHitPoints(int hitPoints){
        this.hitPoints = hitPoints;
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

    public int getLevel(){
        return level;
    }


}
