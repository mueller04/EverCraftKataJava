
public class Program {


    public static void main(String[] args){
        DefenderClass defenderClass = new DefenderClass();
        EverCraftCharacter everCharacter = new EverCraftCharacter("Defending Character", EverCraftCharacter.Alignment.Evil);
        EverCraftCharacter attackingEverCharacter = new EverCraftCharacter("Attacking Character", EverCraftCharacter.Alignment.Good);
        everCharacter.setCharacterClass(new DefenderClass());
        Play play = new Play();

        play.roll(everCharacter, attackingEverCharacter, 20);

    }

}
