
public class Play {

    public String roll(EverCraftCharacter defendingCharacter, EverCraftCharacter attackingCharacter, int rollNumber){

        defendingCharacter.preTurnUpdate();
        attackingCharacter.preTurnUpdate();

        if (rollNumber >= defendingCharacter.getArmor()){

            reducedefendingCharacterHitPoints(defendingCharacter, attackingCharacter, rollNumber);
            defendingCharacter.updateLifeStatus();
            return "it's a hit";

        } else {
            return "attack glanced off the armor";
        }
    }

    private void reducedefendingCharacterHitPoints(EverCraftCharacter defendingCharacter, EverCraftCharacter attackingCharacter, int rollNumber) {

        int strengthScore = attackingCharacter.getAbilities().getStrengthScore();
        int amountToReduceHitPointsBy = attackingCharacter.getAbilities().getStrengthModifier(strengthScore);

        if (amountToReduceHitPointsBy < 1) {
            amountToReduceHitPointsBy = 1;
        }

        if (rollNumber == 20) {
            defendingCharacter.setHitPoints(defendingCharacter.getHitPoints() - amountToReduceHitPointsBy * 2);
        } else {

            defendingCharacter.setHitPoints(defendingCharacter.getHitPoints() - amountToReduceHitPointsBy);
        }
    }

}
