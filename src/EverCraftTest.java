import org.junit.Test;
import static org.junit.Assert.*;

public class EverCraftTest {

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




}
