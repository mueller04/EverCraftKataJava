
public class Abilities {

    private MapScoreToModifier strength = new MapScoreToModifier();
    private MapScoreToModifier dexterity = new MapScoreToModifier();
    private MapScoreToModifier constitution = new MapScoreToModifier();
    private MapScoreToModifier wisdom = new MapScoreToModifier();
    private MapScoreToModifier intelligence = new MapScoreToModifier();
    private MapScoreToModifier charisma = new MapScoreToModifier();

    private int strengthScore = 10;
    private int dexterityScore = 10;
    private int constitutionScore = 10;
    private int wisdomScore = 10;
    private int intelligenceScore = 10;
    private int charismaScore = 10;


    //Getters and Setters
    public int getStrengthModifier(int strengthScore){
        return strength.scoreToModifier.get(strengthScore);
    }

    public int getDexterityModifier(int strengthScore){
        return dexterity.scoreToModifier.get(strengthScore);
    }

    public int getConstitutionModifier(int strengthScore){
        return constitution.scoreToModifier.get(strengthScore);
    }

    public int getWisdomModifier(int strengthScore){
        return wisdom.scoreToModifier.get(strengthScore);
    }

    public int getIntelligenceModifier(int strengthScore){
        return intelligence.scoreToModifier.get(strengthScore);
    }

    public int getCharismaModifier(int strengthScore){
        return charisma.scoreToModifier.get(strengthScore);
    }

    public int getStrengthScore(){
        return strengthScore;
    }

    public void setStrengthScore(int strengthScore){
        this.strengthScore = strengthScore;
    }

    public int getDexterityScore(){
        return dexterityScore;
    }

    public int getConstitutionScore(){
        return constitutionScore;
    }

    public int getWisdomScore(){
        return wisdomScore;
    }

    public int getIntelligenceScore(){
        return intelligenceScore;
    }

    public int getCharismaScore(){
        return charismaScore;
    }

}
