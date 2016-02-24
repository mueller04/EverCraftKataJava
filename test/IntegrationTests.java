import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class IntegrationTests {

    Play play;
    EverCraftCharacter everCharacter;
    EverCraftCharacter attackingEverCharacter;

    @Before
    public void beforeEachMethod(){
        play = new Play();
        everCharacter = new EverCraftCharacter("Defending Character", EverCraftCharacter.Alignment.Neutral);
        attackingEverCharacter = new EverCraftCharacter("Attacking Character", EverCraftCharacter.Alignment.Good);
    }

    @Test
    public void rollModifierAddsToAttackRole(){
        //Arrange
        //Add enough experience points to level up to level 4 which adds 2 to attack modifier (1 for each even level achieved)
        attackingEverCharacter.addExperiencePoints(3000);
        attackingEverCharacter.getAttackRollModifierCalculatePreTurnUpdate();

        //Act
        String result = play.roll(everCharacter, attackingEverCharacter, 8);

        //Assert
        assertEquals("it's a hit", result);
    }


}
