
public class Program {


    public static void main(String[] args){
        EverCraftCharacter everCharacter = new EverCraftCharacter("Defending Character", EverCraftCharacter.Alignment.Evil);
        EverCraftCharacter attackingEverCharacter = new EverCraftCharacter("Attacking Character", EverCraftCharacter.Alignment.Good);
        everCharacter.setCharacterClass(EverCraftCharacter.CharacterClassEnum.DEFENDER);
        Play play = new Play();

        play.roll(everCharacter, attackingEverCharacter, 20);
    }

}
