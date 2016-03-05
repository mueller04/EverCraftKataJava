
public class Play {

    public String roll(EverCraftCharacter defendingCharacter, EverCraftCharacter attackingCharacter, int rollNumber){

        defendingCharacter.calculateHitPointsAndAttackStrength(isCritical(rollNumber));

        useCharacterClassAbilities(defendingCharacter, attackingCharacter);

        int modifiedRollNumber = attackingCharacter.getModifiedRollNumber(rollNumber);

        if (modifiedRollNumber >= defendingCharacter.getModifiedArmor()){

            hitCharacter(defendingCharacter, attackingCharacter, modifiedRollNumber);
            return "it's a hit";

        } else {
            return "attack glanced off the armor";
        }
    }

    private boolean isCritical(int rollNumber) {
        return (rollNumber >= 20);
    }

    private void hitCharacter(EverCraftCharacter defendingCharacter, EverCraftCharacter attackingCharacter, int modifiedRollNumber){
        int attackStrength = attackingCharacter.calculateHitPointsAndAttackStrength(isCritical(modifiedRollNumber));
        defendingCharacter.setHitPoints(defendingCharacter.getHitPoints() - attackStrength);
        defendingCharacter.updateLifeStatus();
        attackingCharacter.addExperiencePoints(10);
    }

    private void useCharacterClassAbilities(EverCraftCharacter defendingCharacter, EverCraftCharacter attackingCharacter){
        if (defendingCharacter.getCharacterClass() == Enum.CharacterClassEnum.DEFENDER){
            attackingCharacter.getAbilities().setStrengthScore(10);
        }

        if (attackingCharacter.getCharacterClass() == Enum.CharacterClassEnum.WARLORD){
            defendingCharacter.getAbilities().setDexterityScore(10);
        }

        if (attackingCharacter.getCharacterClass() == Enum.CharacterClassEnum.ROGUE
                && defendingCharacter.getAlignment() == Enum.Alignment.Evil) {
            attackingCharacter.setRogueHitAgainstEvilFlag();
        }
    }

}
