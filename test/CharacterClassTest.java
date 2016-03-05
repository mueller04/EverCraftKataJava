import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CharacterClassTest {

    EverCraftCharacter everCharacter;
    DefenderClass defender;


    @Test
    public void defenderClassIncreasesHitPoints(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good);
        everCharacter.setCharacterClass(EverCraftCharacter.CharacterClassEnum.DEFENDER);
        int expectedHitPoints = 10;

        //Act
        int result = everCharacter.getHitPoints();

        //Assert
        assertEquals(expectedHitPoints, result);
    }

    @Test
    public void defenderClassAlignmentCannotBeEvil(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Evil);
        try {
            //Act
            everCharacter.setCharacterClass(EverCraftCharacter.CharacterClassEnum.DEFENDER);
        } catch (Exception e) {
            //Assert
            assertEquals(e.getMessage(), "Defender Class cannot have evil alignment");
        }
    }

}
