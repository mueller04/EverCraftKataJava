import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CharacterClassTest {

    EverCraftCharacter everCharacter;


    @Test
    public void defenderClassIncreasesHitPoints(){
        //Arrange
        DefenderClass defender = new DefenderClass();
        everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good, defender);
        int expectedHitPoints = 10;

        //Act
        String result = everCharacter.getHitPoints();

        //Assert
        assertEquals(expectedHitPoints, result);
    }

}
