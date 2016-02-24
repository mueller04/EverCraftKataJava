import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayTest {

    Play play;
    EverCraftCharacter everCharacter;
    EverCraftCharacter attackingEverCharacter;

    @Before
    public void BeforeMethodClass() {
        play = new Play();
        everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good);
        attackingEverCharacter = new EverCraftCharacter("attacking character", EverCraftCharacter.Alignment.Neutral);
    }

    @Test
    public void rollHitsIfMeetsOpponentsArmorClass(){
        //Arrange

        //Act
        String result = play.roll(everCharacter, attackingEverCharacter, 10);

        //Assert
        assertEquals("it's a hit", result);
    }

    @Test
    public void rollHitsIfExceedsOpponentsArmorClass(){
        //Arrange

        //Act
        String result = play.roll(everCharacter, attackingEverCharacter, 11);

        //Assert
        assertEquals("it's a hit", result);
    }

    @Test
    public void rollFailsIfLessThanOpponentsArmorClass(){
        //Arrange

        //Act
        String result = play.roll(everCharacter, attackingEverCharacter, 9);

        //Assert
        assertEquals("attack glanced off the armor", result);
    }

    @Test
    public void successfulRollLessThan20Takes1HitPoint(){
        //Arrange

        //Act
        play.roll(everCharacter, attackingEverCharacter, 15);

        //Assert
        assertEquals(4, everCharacter.getHitPoints());
    }

    @Test
    public void failedrollDoesNotChangeHitPoints(){
        //Arrange

        //Act
        play.roll(everCharacter, attackingEverCharacter, 2);

        //Assert
        assertEquals(5, everCharacter.getHitPoints());
    }

    @Test
    public void roll20TakesDoublePoints(){
        //Arrange

        //Act
        play.roll(everCharacter, attackingEverCharacter, 20);

        //Assert
        assertEquals(3, everCharacter.getHitPoints());
    }

    @Test
    public void afterRollingCharacterWith0HitPointsIsDead(){
        //Arrange
        everCharacter.setHitPoints(2);

        //Act
        play.roll(everCharacter, attackingEverCharacter, 20);

        //Assert
        assertEquals(EverCraftCharacter.LifeStatus.Dead, everCharacter.getLifeStatus());
    }

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

    @Test
    public void afterRollingStrengthModifierIsAdded(){
        //Arrange
        attackingEverCharacter.getAbilities().setStrengthScore(15);
        everCharacter.setHitPoints(5);

        //Act
        play.roll(everCharacter, attackingEverCharacter, 15);

        //Assert
        assertEquals(3, everCharacter.getHitPoints());
    }

    @Test
    public void afterRolling20StrengthModifierIsAddedAndDoubled(){
        //Arrange
        attackingEverCharacter.getAbilities().setStrengthScore(15);
        everCharacter.setHitPoints(5);

        //Act
        play.roll(everCharacter, attackingEverCharacter, 20);

        //Assert
        assertEquals(1, everCharacter.getHitPoints());
    }



    @Test
    public void constitutionAddsToHitPoints(){
        //Arrange
        everCharacter.setHitPoints(10);
        everCharacter.getAbilities().setConstitutionScore(1);

        //Act
        play.roll(everCharacter, attackingEverCharacter, 10);

        //Assert
        assertEquals(4, everCharacter.getHitPoints());
    }

    @Test
    public void successfulAttackEarns10ExperiencePoints(){
        //Arrange

        //Act
        play.roll(everCharacter, attackingEverCharacter, 10);

        //Assert
        assertEquals(10, attackingEverCharacter.getExperiencePoints());
    }



}


