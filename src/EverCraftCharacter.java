
public class EverCraftCharacter {

    private String name;
    private Enum.Alignment alignment;
    private Enum.LifeStatus lifeStatus;
    private int experiencePoints = 0;
    private int hitPoints = 5;
    private Abilities abilities;
    private Enum.CharacterClassEnum characterClassEnum;
    private Enum.RaceEnum raceEnum;

    //Class Related
    private boolean rogueHitAgainstEvilFlag = false;

    //Race Related
    private boolean dwarfHitAgainstOrcFlag = false;

    public EverCraftCharacter(String name, Enum.Alignment alignment){
        this.name = name;
        this.alignment = alignment;
        this.lifeStatus = Enum.LifeStatus.Alive;
        this.abilities = new Abilities();
        this.characterClassEnum = Enum.CharacterClassEnum.DEFAULT;
        this.raceEnum = Enum.RaceEnum.HUMAN;
    }

    public void setCharacterClass(Enum.CharacterClassEnum characterClass){
        if (alignment == Enum.Alignment.Evil && characterClass == Enum.CharacterClassEnum.DEFENDER) {
            throw new IllegalArgumentException("Defender Class cannot have evil alignment");
        } else if (alignment == Enum.Alignment.Good && characterClass == Enum.CharacterClassEnum.WARLORD) {
            throw new IllegalArgumentException("Warlord Class cannot have good alignment");
        } else if ((alignment == Enum.Alignment.Good || alignment == Enum.Alignment.Evil) && characterClass == Enum.CharacterClassEnum.ROGUE) {
            throw new IllegalArgumentException("Warlord Class cannot have good or evil alignment");
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

        if (raceEnum == Enum.RaceEnum.ORC){
            armor += 2;
        }

        int dexterityModifier = calculateDexterityModifier();

        armor += dexterityModifier;
        return armor;
    }

    private int calculateDexterityModifier() {
        int dexterityScore = abilities.getDexterityScore();
        int dexterityModifier = abilities.getDexterityModifier(dexterityScore);

        if (characterClassEnum == Enum.CharacterClassEnum.DEFENDER){
            if (dexterityModifier >= 0) {
                dexterityModifier *=2;
            }
        }
        if (raceEnum == Enum.RaceEnum.ELF) {
            dexterityModifier += 1;
        }
        return dexterityModifier;
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

        if (raceEnum == Enum.RaceEnum.ORC) {
            totalAttackScore += 2;
        }

        if (rogueHitAgainstEvilFlag) {
            totalAttackScore += 2;
        }

        if (dwarfHitAgainstOrcFlag) {
            totalAttackScore += 2;
        }

        if (isCritical) {
            totalAttackScore *= criticalHit();
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
        } else if (characterClassEnum == Enum.CharacterClassEnum.WARLORD) {
            int strengthScore = this.getAbilities().getStrengthScore();
            return (this.getAbilities().getStrengthModifier(strengthScore) * 2);
        } else {
            int strengthScore = this.getAbilities().getStrengthScore();
            return this.getAbilities().getStrengthModifier(strengthScore);
        }
    }

    private int criticalHit(){
        if (characterClassEnum == Enum.CharacterClassEnum.WARLORD || rogueHitAgainstEvilFlag) {
            return 3;
        } else  {
            return 2;
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

        constitutionModifier = calculateRaceConsitutionModifier(constitutionModifier);

        hitPoints += constitutionModifier;

        for (int i = 1; i < level; i++) {
            if (characterClassEnum == Enum.CharacterClassEnum.WARLORD) {
                hitPoints += calculateLevelUpConstitutionModifier(6, constitutionModifier);
            } else {
                hitPoints += calculateLevelUpConstitutionModifier(5, constitutionModifier);
            }
        }
        if (hitPoints < 1) {
            hitPoints = 1;
        }
    }

    private int calculateLevelUpConstitutionModifier(int base, int constitutionModifier){
        if (raceEnum == Enum.RaceEnum.DWARF){
            return (base + (constitutionModifier * 2));
        } else {
            return (base + constitutionModifier);
        }
    }

    private int calculateRaceConsitutionModifier(int constitutionModifier) {
        if (raceEnum == Enum.RaceEnum.DWARF) {
            return constitutionModifier += 1;
        } else if (raceEnum == Enum.RaceEnum.ELF) {
            return constitutionModifier -= 1;
        } else {
            return constitutionModifier;
        }
    }

    private int getAttackRollModifier(int level) {
        int fractionalLevel = (level / 2);
        int attackRollModifier = (int)Math.floor(fractionalLevel);
        return attackRollModifier;
    }

    public int getCriticalRange(){
        return 0;
    }

    public void clearFlags(){
        rogueHitAgainstEvilFlag = false;
        dwarfHitAgainstOrcFlag = false;
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

    public Enum.RaceEnum getRace() { return raceEnum; }

    public void setRogueHitAgainstEvilFlag() { rogueHitAgainstEvilFlag = true; }

    public void setDwarfHitAgainstOrcFlag() { dwarfHitAgainstOrcFlag = true; }

    public void setRace(Enum.RaceEnum raceEnum) { this.raceEnum = raceEnum; }
}
