
public class Play {

    public String roll(EverCraftCharacter defendingCharacter, EverCraftCharacter attackingCharacter, int rollNumber){

        defendingCharacter.getModifiedRollNumberCalculatePreTurnUpdate(rollNumber);

        int modifiedRollNumber = attackingCharacter.getModifiedRollNumberCalculatePreTurnUpdate(rollNumber);


        if (modifiedRollNumber >= defendingCharacter.getModifiedArmor()){

            reduceDefendingCharacterHitPoints(defendingCharacter, attackingCharacter, modifiedRollNumber);
            defendingCharacter.updateLifeStatus();
            attackingCharacter.addExperiencePoints(10);
            return "it's a hit";

        } else {
            return "attack glanced off the armor";
        }
    }

    private void reduceDefendingCharacterHitPoints(EverCraftCharacter defendingCharacter, EverCraftCharacter attackingCharacter, int rollNumberAndAttackRollModifier) {

        int strengthScore = attackingCharacter.getAbilities().getStrengthScore();
        int amountToReduceHitPointsBy = attackingCharacter.getAbilities().getStrengthModifier(strengthScore);

        if (amountToReduceHitPointsBy < 1) {
            amountToReduceHitPointsBy = 1;
        }

        if (rollNumberAndAttackRollModifier >= 20) {
            amountToReduceHitPointsBy *= 2;
        }
        defendingCharacter.setHitPoints(defendingCharacter.getHitPoints() - amountToReduceHitPointsBy);
    }
}
