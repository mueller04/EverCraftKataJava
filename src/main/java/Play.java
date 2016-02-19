
public class Play {

    public String roll(EverCraftCharacter defendingCharacter, int rollNumber){

        if (rollNumber >= defendingCharacter.getArmor()){

            if (rollNumber == 20) {
                defendingCharacter.setHitPoints(defendingCharacter.getHitPoints() - 2);
            } else {
                defendingCharacter.setHitPoints(defendingCharacter.getHitPoints() - 1);
            }

            defendingCharacter.updateLifeStatus();

            return "it's a hit";
        } else {
            return "attack glanced off the armor";
        }
    }

}
