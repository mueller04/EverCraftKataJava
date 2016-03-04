

public class DefenderClass implements CharacterClass {

    int hitPointMultiplierModifier = 2;

    public int getHitPointMultiplierModifier(){
        return hitPointMultiplierModifier;
    }

    public void validateAlignment(EverCraftCharacter.Alignment alignment) throws IllegalArgumentException {
        if (alignment == EverCraftCharacter.Alignment.Evil){
            throw new IllegalArgumentException("Defender Class cannot have evil alignment");
        }
    }
}
