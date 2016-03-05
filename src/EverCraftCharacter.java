
public class EverCraftCharacter {

    public enum Alignment {
        Good, Evil, Neutral
    }

    public enum LifeStatus {
        Alive, Dead
    }

    public enum CharacterClassEnum {
        DEFAULT(new DefaultCharacterClass()), DEFENDER(new DefenderClass());

        public CharacterClass characterClass;

        CharacterClassEnum(CharacterClass characterClass) {
            this.characterClass = characterClass;
        }
    }

    private String name;
    private Alignment alignment;
    private LifeStatus lifeStatus;
    private int experiencePoints = 0;
    private int hitPoints = 5;
    private Abilities abilities;
    private CharacterClassEnum characterClassEnum;

    public EverCraftCharacter(String name, Alignment alignment){
        this.name = name;
        this.alignment = alignment;
        this.lifeStatus = LifeStatus.Alive;
        this.abilities = new Abilities();
        this.characterClassEnum = CharacterClassEnum.DEFAULT;
    }

    public void setCharacterClass(CharacterClassEnum characterClass){
        if (alignment == EverCraftCharacter.Alignment.Evil && characterClass == CharacterClassEnum.DEFENDER){
            throw new IllegalArgumentException("Defender Class cannot have evil alignment");
        } else {
            this.characterClassEnum = characterClass;
            setClassModifiers();
        }

    }


    private void setClassModifiers(){
        hitPoints *= characterClassEnum.characterClass.getHitPointMultiplierModifier();
    }

    public void updateLifeStatus(){
        if (this.getHitPoints() <= 0){
            this.setLifeStatus(EverCraftCharacter.LifeStatus.Dead);
        }
    }

    public int getModifiedArmor(){
        int armor = 10;
        int dexterityScore = abilities.getDexterityScore();
        armor += abilities.getDexterityModifier(dexterityScore);
        return armor;
    }

    public int getModifiedRollNumber(int rollNumber) {
        int strengthScore = this.getAbilities().getStrengthScore();
        int strengthModifier = this.getAbilities().getStrengthModifier(strengthScore);
        return rollNumber + strengthModifier;
    }


    public int calculateHitPointsAndAttackStrength(boolean isCritical) {
        int level = getLevel();
        calculateHitPoints(level);

        int strengthScore = this.getAbilities().getStrengthScore();
        int strengthModifier = this.getAbilities().getStrengthModifier(strengthScore);

        if (isCritical) {
            strengthModifier *= 2;
        }

        int attackRollLevelModifier = getAttackRollModifier(level);
        int totalAttackScore = 1 + attackRollLevelModifier + strengthModifier;

        if (isCritical) {
            totalAttackScore *= 2;
        }

        if (totalAttackScore < 1) {
            totalAttackScore = 1;
        }

        return totalAttackScore;
    }


    public void addExperiencePoints(int experiencePoints) {
        this.experiencePoints += experiencePoints;
    }

    public int getLevel() {
        int level = 1;

        if (experiencePoints == 0) {
            experiencePoints = 1;
        }

        int fractionalLevel = (experiencePoints / 1000) + 1;
        level = (int) Math.floor(fractionalLevel);

        return level;
    }



    public void calculateHitPoints(int level){
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

    public CharacterClassEnum getCharacterClass() { return characterClassEnum; }
}
