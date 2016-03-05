class DefaultCharacterClass implements CharacterClass {

    int hitPointMultiplierModifier = 1;

    public int getHitPointMultiplierModifier() {
        return hitPointMultiplierModifier;
    }

    public void validateAlignment(EverCraftCharacter.Alignment alignment) throws IllegalArgumentException{
        // DO NOTHING
    }

}
