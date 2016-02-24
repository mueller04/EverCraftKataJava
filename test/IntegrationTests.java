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


    //What I changed in the code is adding a setter in Abilities, and modifying getArmorPlusDexterity in EverCraftCharacter.  Does
    //This mean I really should be adding a test in EverCraftCharacterTest which tests that getArmorPlusDexterity directly
    // instead of this test?  Would I not need this "integration test" where the results
    //Of that accurately impact the hit points?
    //The long method names make me wonder if these tests arent isolated enough and are "integration tests"
    @Test
    public void addDexterityModifierToArmorClassMeetArmorPointsResultsInHit(){
        //Arrange
        everCharacter.getAbilities().setDexterityScore(14);

        //Act
        String result = play.roll(everCharacter, attackingEverCharacter, 12);

        //Assert
        assertEquals("it's a hit", result);
    }

    //same concerns as above
    @Test
    public void addDexterityModifierToArmorClassLessPointsResultsInMiss(){
        //Arrange
        everCharacter.getAbilities().setDexterityScore(12);

        //Act
        String result = play.roll(everCharacter, attackingEverCharacter, 10);

        //Assert
        assertEquals("attack glanced off the armor", result);
    }


}
