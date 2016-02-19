import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayTest {

    Play play;

    @Before
    public void BeforeMethodClass() {
        play = new Play();
    }

    @Test
    public void rollHitsIfMeetsOpponentsArmorClass(){
        //Arrange
        EverCraftCharacter everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good);

        //Act
        String result = play.roll(everCharacter, 10);

        //Assert
        assertEquals("it's a hit", result);
    }

    @Test
    public void rollHitsIfExceedsOpponentsArmorClass(){
        //Arrange
        EverCraftCharacter everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good);

        //Act
        String result = play.roll(everCharacter, 11);

        //Assert
        assertEquals("it's a hit", result);
    }

    @Test
    public void rollFailsIfLessThanOpponentsArmorClass(){
        //Arrange
        EverCraftCharacter everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good);

        //Act
        String result = play.roll(everCharacter, 9);

        //Assert
        assertEquals("attack glanced off the armor", result);
    }

    @Test
    public void successfulRollLessThan20Takes1HitPoint(){
        //Arrange
        EverCraftCharacter everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good);

        //Act
        play.roll(everCharacter, 15);

        //Assert
        assertEquals(4, everCharacter.getHitPoints());
    }

    @Test
    public void failedrollDoesNotChangeHitPoints(){
        //Arrange
        EverCraftCharacter everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good);

        //Act
        play.roll(everCharacter, 2);

        //Assert
        assertEquals(5, everCharacter.getHitPoints());
    }

    @Test
    public void roll20TakesDoublePoints(){
        //Arrange
        EverCraftCharacter everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good);

        //Act
        play.roll(everCharacter, 20);

        //Assert
        assertEquals(3, everCharacter.getHitPoints());
    }

    @Test
    public void afterRollingCharacterWith0HitPointsIsDead(){
        //Arrange
        EverCraftCharacter everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good);
        everCharacter.setHitPoints(2);

        //Act
        play.roll(everCharacter, 20);

        //Assert
        assertEquals(EverCraftCharacter.LifeStatus.Dead, everCharacter.getLifeStatus());
    }

    @Test
    public void afterRollingCharacterWithLessThan0HitPointsIsDead(){
        //Arrange
        EverCraftCharacter everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good);
        everCharacter.setHitPoints(1);

        //Act
        play.roll(everCharacter, 20);

        //Assert
        assertEquals(EverCraftCharacter.LifeStatus.Dead, everCharacter.getLifeStatus());
    }

    @Test
    public void afterRollingCharacterWithMoreThan0HitPointsIsAlive(){
        //Arrange
        EverCraftCharacter everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good);
        everCharacter.setHitPoints(3);

        //Act
        play.roll(everCharacter, 20);

        //Assert
        assertEquals(EverCraftCharacter.LifeStatus.Alive, everCharacter.getLifeStatus());
    }




}


