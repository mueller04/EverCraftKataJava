
public class EverCraftCharacter {

    private String name;
    private Enum.Alignment alignment;
    private Enum.LifeStatus lifeStatus;
    private int experiencePoints = 0;
    private int hitPoints = 5;
    private Abilities abilities;
    private Enum.CharacterClassEnum characterClassEnum;

    public EverCraftCharacter(String name, Enum.Alignment alignment){
        this.name = name;
        this.alignment = alignment;
        this.lifeStatus = Enum.LifeStatus.Alive;
        this.abilities = new Abilities();
        this.characterClassEnum = Enum.CharacterClassEnum.DEFAULT;
    }

    public void setCharacterClass(Enum.CharacterClassEnum characterClass){
        if (alignment == Enum.Alignment.Evil && characterClass == Enum.CharacterClassEnum.DEFENDER) {
            throw new IllegalArgumentException("Defender Class cannot have evil alignment");
        } else if (alignment == Enum.Alignment.Good && characterClass == Enum.CharacterClassEnum.WARLORD){
            throw new IllegalArgumentException("Warlord Class cannot have good alignment");
        } else {
            this.characterClassEnum = characterClass;
            setClassModifiers();
        }
    }

    private void setClassModifiers(){
        if (characterClassEnum == Enum.CharacterClassEnum.DEFENDER) {
            hitPoints *= 2;
        }

    }

    public void updateLifeStatus(){
        if (this.getHitPoints() <= 0){
            this.setLifeStatus(Enum.LifeStatus.Dead);
        }
    }

    public int getModifiedArmor(){
        int armor = 10;
        int dexterityScore = abilities.getDexterityScore();
        int dexterityModifier = abilities.getDexterityModifier(dexterityScore);

        if (characterClassEnum == Enum.CharacterClassEnum.DEFENDER){
            if (dexterityModifier >= 0) {
                dexterityModifier *=2;
            }
        }

        armor += dexterityModifier;
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

        int strengthModifier = calculateStrengthModifier();

        if (isCritical) {
            strengthModifier *= 2;
        }

        int attackRollLevelModifier = getAttackRollModifier(level);
        int totalAttackScore = 1 + attackRollLevelModifier + strengthModifier;

        if (characterClassEnum == Enum.CharacterClassEnum.WARLORD && isCritical) {
            totalAttackScore *= 3;
        } else if (isCritical) {
            totalAttackScore *= 2;
        }

        if (totalAttackScore < 1) {
            totalAttackScore = 1;
        }

        return totalAttackScore;
    }

    private int calculateStrengthModifier(){
        if (characterClassEnum == Enum.CharacterClassEnum.ROGUE) {
            int dexterityScore = this.getAbilities().getDexterityScore();
            return this.getAbilities().getDexterityModifier(dexterityScore);
        } else {
            int strengthScore = this.getAbilities().getStrengthScore();
            return this.getAbilities().getStrengthModifier(strengthScore);
        }
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

    public Enum.Alignment getAlignment(){
        return alignment;
    }

    public void setAlignment(Enum.Alignment alignment){
        this.alignment = alignment;
    }

    public Enum.LifeStatus getLifeStatus(){
        return lifeStatus;
    }

    public void setLifeStatus(Enum.LifeStatus lifeStatus){
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

    public Enum.CharacterClassEnum getCharacterClass() { return characterClassEnum; }
}
