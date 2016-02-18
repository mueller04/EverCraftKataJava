import org.junit.Test;
import static org.junit.Assert.*;

public class EverCraftTest {

    @Test
    public void canGetCharacterName(){
        //Arrange
        String expectedName = "Example Name";
        EverCraftCharacter everCharacter = new EverCraftCharacter(expectedName);

        //Act
        String result = everCharacter.getName();

        //Assert
        assertEquals("Example Name", result);
    }

}
