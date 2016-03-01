
public class Play {

    public String roll(EverCraftCharacter defendingCharacter, EverCraftCharacter attackingCharacter, int rollNumber){

        defendingCharacter.calculateHitPointsAndAttackStrength(isCritical(rollNumber));

        int attackStrength = attackingCharacter.calculateHitPointsAndAttackStrength(isCritical(rollNumber));

        int modifiedRollNumber = attackingCharacter.getModifiedRollNumber(rollNumber);

        if (modifiedRollNumber >= defendingCharacter.getModifiedArmor()){
            defendingCharacter.setHitPoints(defendingCharacter.getHitPoints() - attackStrength);
            defendingCharacter.updateLifeStatus();
            attackingCharacter.addExperiencePoints(10);
            return "it's a hit";

        } else {
            return "attack glanced off the armor";
        }
    }

    private boolean isCritical(int rollNumber) {
        return (rollNumber == 20);
    }

}
