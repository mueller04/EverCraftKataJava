
public class Program {


    public static void main(String[] args){
        EverCraftCharacter everCharacter = new EverCraftCharacter("Defending Character", Enum.Alignment.Evil);
        EverCraftCharacter attackingEverCharacter = new EverCraftCharacter("Attacking Character", Enum.Alignment.Good);
        everCharacter.setCharacterClass(Enum.CharacterClassEnum.DEFENDER);
        Play play = new Play();

        play.roll(everCharacter, attackingEverCharacter, 20);
    }

}
