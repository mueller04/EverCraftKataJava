import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mike on 3/4/16.
 */
public class RaceTest {

    EverCraftCharacter everCharacter;


    @Test
    public void orcIncreasesHitPointsBy2(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", Enum.Alignment.Good);
        everCharacter.setRace(Enum.RaceEnum.ORC);

        //Act
        int result = everCharacter.calculateHitPointsAndAttackStrength(false);

        //Assert
        assertEquals(3, result);
    }
}
