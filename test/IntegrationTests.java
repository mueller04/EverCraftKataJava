import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class IntegrationTests {

    Play play;
    EverCraftCharacter everCharacter;
    EverCraftCharacter attackingEverCharacter;

    @Before
    public void beforeEachMethod(){
        everCharacter = new EverCraftCharacter("Defending Character", EverCraftCharacter.Alignment.Neutral);
        attackingEverCharacter = new EverCraftCharacter("Attacking Character", EverCraftCharacter.Alignment.Good);
        play = new Play();
    }

    @Test
    public void rollModifierAddsToAttackRole(){
        //Arrange
        //Add enough experience points to level up to level 4 which adds additional 2 to attack (1 for each even level achieved)
        attackingEverCharacter.addExperiencePoints(3000);

        //Act
        String result = play.roll(everCharacter, attackingEverCharacter, 10);

        //Assert
        assertEquals(2, everCharacter.getHitPoints());
    }


    @Test
    public void addDexterityModifierToArmorClassMeetArmorPointsResultsInHit(){
        //Arrange
        everCharacter.getAbilities().setDexterityScore(14);

        //Act
        String result = play.roll(everCharacter, attackingEverCharacter, 12);

        //Assert
        assertEquals("it's a hit", result);
    }

    @Test
    public void addDexterityModifierToArmorClassLessPointsResultsInMiss(){
        //Arrange
        everCharacter.getAbilities().setDexterityScore(12);

        //Act
        String result = play.roll(everCharacter, attackingEverCharacter, 10);

        //Assert
        assertEquals("attack glanced off the armor", result);
    }

    //Can this realistically be mocked or should this be moved to integration tests?
    @Test
    public void successfulRollLessThan20Takes1HitPoint(){
        //Arrange

        //Act
        play.roll(everCharacter, attackingEverCharacter, 15);

        //Assert
        assertEquals(4, everCharacter.getHitPoints());

    }

    //Can this realistically be mocked or should this be moved to integration tests?
    @Test
    public void failedrollDoesNotChangeHitPoints(){
        //Arrange

        //Act
        play.roll(everCharacter, attackingEverCharacter, 2);

        //Assert
        assertEquals(5, everCharacter.getHitPoints());
    }

    //Can this realistically be mocked or should this be moved to integration tests?
    @Test
    public void roll20TakesDoublePoints(){
        //Arrange
        everCharacter.setHitPoints(5);

        //Act
        play.roll(everCharacter, attackingEverCharacter, 20);

        //Assert
        assertEquals(3, everCharacter.getHitPoints());
    }

    //Can this realistically be mocked or should this be moved to integration tests?
    //I do have a unitTest in EverCraftCharacterTest for UpdateLifeStatus, is it necessary to have an integration test?
    @Test
    public void afterRollingCharacterWith0HitPointsIsDead(){
        //Arrange
        everCharacter.setHitPoints(2);

        //Act
        play.roll(everCharacter, attackingEverCharacter, 20);

        //Assert
        assertEquals(EverCraftCharacter.LifeStatus.Dead, everCharacter.getLifeStatus());
    }

    //same as above
    @Test
    public void afterRollingCharacterWithLessThan0HitPointsIsDead(){
        //Arrange
        everCharacter.setHitPoints(1);

        //Act
        play.roll(everCharacter, attackingEverCharacter, 20);

        //Assert
        assertEquals(EverCraftCharacter.LifeStatus.Dead, everCharacter.getLifeStatus());
    }

    @Test
    public void afterRollingCharacterWithMoreThan0HitPointsIsAlive(){
        //Arrange
        everCharacter.setHitPoints(3);

        //Act
        play.roll(everCharacter, attackingEverCharacter, 20);

        //Assert
        assertEquals(EverCraftCharacter.LifeStatus.Alive, everCharacter.getLifeStatus());
    }


}
