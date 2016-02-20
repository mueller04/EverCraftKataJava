import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class EverCraftCharacterTest {

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



    //This is where I should use mocking to say it's expected i'm handed back an abilities object and i'm done..I believe
    //OR would I want to do an object compare between a mocked object and the object handed back from getAbilities?
    @Test
    public void charachterHasAbilitiesScoresIncludingStrengthModifier(){
        //Arrange
        EverCraftCharacter everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good);
        int expectedStrengthScore = 0;

        //Act
        int strengthScore = everCharacter.getAbilities().getStrengthScore();
        int result = everCharacter.getAbilities().getStrengthModifier(strengthScore);

        //Assert
        assertEquals(expectedStrengthScore, result);
    }


    @Test
    public void constitutionAddsToHitPoints(){
        //Arrange
        EverCraftCharacter everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good);
        everCharacter.getAbilities().setConstitutionScore(14);

        //Act
        everCharacter.getHitPointsPlusConstitution();

        //Assert
        int result = everCharacter.getHitPointsPlusConstitution();
        assertEquals(7, result);
    }

    @Test
    public void constitutionAddsToHitPoints1IsMinimum(){
        //Arrange
        EverCraftCharacter everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good);
        everCharacter.getAbilities().setConstitutionScore(1);

        //Act
        everCharacter.getHitPointsPlusConstitution();

        //Assert
        int result = everCharacter.getHitPointsPlusConstitution();
        assertEquals(1, result);
    }


}
