
public class Play {

    public String roll(EverCraftCharacter defendingCharacter, EverCraftCharacter attackingCharacter, int rollNumber){

        defendingCharacter.calculateHitPointsAndAttackStrength(false);

        setCharacterClassAbilities(defendingCharacter, attackingCharacter);
        setWeaponAbilities(defendingCharacter, attackingCharacter);

        int modifiedRollNumber = attackingCharacter.getModifiedRollNumber(rollNumber);

        if (modifiedRollNumber >= defendingCharacter.getModifiedArmor()){

            hitCharacter(defendingCharacter, attackingCharacter, modifiedRollNumber);
            clearFlags(defendingCharacter, attackingCharacter);
            return "it's a hit";

        } else {
            clearFlags(defendingCharacter, attackingCharacter);
            return "attack glanced off the armor";
        }
    }

    public boolean isCritical(int rollNumber, EverCraftCharacter attackingCharacter) {
        int criticalRange = attackingCharacter.getCriticalRange();

        boolean isCritical = false;

        for (int i = 0; i <= criticalRange; i++){
            if (rollNumber >= 20 - i){
                isCritical = true;
            }
        }
        return isCritical;
    }

    private void hitCharacter(EverCraftCharacter defendingCharacter, EverCraftCharacter attackingCharacter, int modifiedRollNumber){
        int attackStrength = attackingCharacter.calculateHitPointsAndAttackStrength(isCritical(modifiedRollNumber, attackingCharacter));
        defendingCharacter.setHitPoints(defendingCharacter.getHitPoints() - attackStrength);
        defendingCharacter.updateLifeStatus();
        attackingCharacter.addExperiencePoints(10);
    }

    private void setCharacterClassAbilities(EverCraftCharacter defendingCharacter, EverCraftCharacter attackingCharacter){
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

        if (attackingCharacter.getRace() == Enum.RaceEnum.DWARF
                && defendingCharacter.getRace() == Enum.RaceEnum.ORC) {
            attackingCharacter.setDwarfHitAgainstOrcFlag();
        }

        if (attackingCharacter.getRace() != Enum.RaceEnum.HALFLING
                && defendingCharacter.getRace() == Enum.RaceEnum.HALFLING) {
            defendingCharacter.setHalflingIncreasedArmorFlag();
        }
    }

    private void setWeaponAbilities(EverCraftCharacter defendingCharacter, EverCraftCharacter attackingCharacter){
        if (attackingCharacter.getWeapon() == Enum.Weapon.LONGSWORD && defendingCharacter.getRace() == Enum.RaceEnum.ORC){
            attackingCharacter.setWarAxeAgainstOrcFlag();
        }

    }

    private void clearFlags(EverCraftCharacter defendingCharacter, EverCraftCharacter attackingCharacter){
        defendingCharacter.clearFlags();
        attackingCharacter.clearFlags();
    }

}
