
public class Play {

    public String roll(EverCraftCharacter defendingCharacter, EverCraftCharacter attackingCharacter, int rollNumber){

        int ArmorplusDexterity = defendingCharacter.getArmorPlusDexterity();

        if (rollNumber >= ArmorplusDexterity){

            int strengthScore = attackingCharacter.getAbilities().getStrengthScore();
            int hitPointsToDeduce = attackingCharacter.getAbilities().getStrengthModifier(strengthScore);

            if (hitPointsToDeduce < 1) {
                hitPointsToDeduce = 1;
            }

            if (rollNumber == 20) {
                defendingCharacter.setHitPoints(defendingCharacter.getHitPoints() - hitPointsToDeduce * 2);
            } else {

                defendingCharacter.setHitPoints(defendingCharacter.getHitPoints() - hitPointsToDeduce);
            }

            defendingCharacter.updateLifeStatus();

            return "it's a hit";
        } else {
            return "attack glanced off the armor";
        }
    }

}
