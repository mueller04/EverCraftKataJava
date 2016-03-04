
public interface CharacterClass {

    int getHitPointMultiplierModifier();

    void validateAlignment(EverCraftCharacter.Alignment alignment) throws IllegalArgumentException;
}
