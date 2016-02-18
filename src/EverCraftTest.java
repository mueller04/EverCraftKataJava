import org.junit.Test;
import static org.junit.Assert.*;

public class EverCraftTest {

    @Test
    public void canGetCharacterName(){
        //Arrange
        String expectedName = "Example Name";
        EverCraftCharacter everCharacter = new EverCraftCharacter(expectedName, "good");

        //Act
        String result = everCharacter.getName();

        //Assert
        assertEquals(expectedName, result);
    }

    @Test
    public void canSetCharacterName(){
        //Arrange
        EverCraftCharacter everCharacter = new EverCraftCharacter("Example Name", "good");

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
        String expectedAlignment = "Good";
        EverCraftCharacter everCharacter = new EverCraftCharacter("Example Name", expectedAlignment);

        //Act
        String result = everCharacter.getAlignment();

        //Assert
        assertEquals(expectedAlignment, result);
    }




}
