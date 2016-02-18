import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EverCraftTest {

    Play play;

    @Before
    public void BeforeMethodClass() {
        play = new Play();
    }

    @Test
    public void canGetCharacterName(){
        //Arrange
        String expectedName = "Example Name";
        EverCraftCharacter everCharacter = new EverCraftCharacter(expectedName, EverCraftCharacter.Alignment.Good);

        //Act
        String result = everCharacter.getName();

        //Assert
        assertEquals(expectedName, result);
    }

    @Test
    public void canSetCharacterName(){
        //Arrange
        EverCraftCharacter everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good);

        //Act
        String expectedName = "Joe";
        everCharacter.setName(expectedName);
        String result = everCharacter.getName();

        //Assert
        assertEquals(expectedName, result);
    }

    @Test
    public void canGetAlignment(){
        //Arrange
        EverCraftCharacter.Alignment expectedAlignment = EverCraftCharacter.Alignment.Good;
        EverCraftCharacter everCharacter = new EverCraftCharacter("Example Name", expectedAlignment);

        //Act
        EverCraftCharacter.Alignment result = everCharacter.getAlignment();

        //Assert
        assertEquals(expectedAlignment, result);
    }

    @Test
    public void canSetAlignment(){
        //Arrange
        EverCraftCharacter everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good);

        //Act
        EverCraftCharacter.Alignment expectedAlignment = EverCraftCharacter.Alignment.Evil;
        everCharacter.setAlignment(expectedAlignment);

        //Assert
        EverCraftCharacter.Alignment result = everCharacter.getAlignment();
        assertEquals(expectedAlignment, result);
    }

    @Test
    public void armorDefaultsTo10(){
        //Arrange
        EverCraftCharacter everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good);

        //Act
        int result = everCharacter.getArmor();

        //Assert
        assertEquals(10, result);
    }

    @Test
    public void hitPointsDefaultsTo5(){
        //Arrange
        EverCraftCharacter everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good);

        //Act
        int result = everCharacter.getHitPoints();

        //Assert
        assertEquals(5, result);
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




}
