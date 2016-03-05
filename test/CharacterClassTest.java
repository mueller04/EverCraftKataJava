import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CharacterClassTest {

    EverCraftCharacter everCharacter;
    DefenderClass defender;


    @Test
    public void defenderClassIncreasesHitPoints(){
        //Arrange
        defender = new DefenderClass();
        everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good);
        everCharacter.setCharacterClass(defender);
        int expectedHitPoints = 10;

        //Act
        int result = everCharacter.getHitPoints();

        //Assert
        assertEquals(expectedHitPoints, result);
    }

    @Test
    public void defenderClassAlignmentCannotBeEvil(){
        //Arrange
        defender = new DefenderClass();
        everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Evil);
        try {
            //Act
            everCharacter.setCharacterClass(defender);
        } catch (Exception e) {
            //Assert
            assertEquals(e.getMessage(), "Defender Class cannot have evil alignment");
        }
    }

}
