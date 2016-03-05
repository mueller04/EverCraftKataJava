import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CharacterClassTest {

    EverCraftCharacter everCharacter;
    DefenderClass defender;


    @Test
    public void defenderClassIncreasesHitPoints(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good);
        everCharacter.setCharacterClass(EverCraftCharacter.CharacterClassEnum.DEFENDER);
        int expectedHitPoints = 10;

        //Act
        int result = everCharacter.getHitPoints();

        //Assert
        assertEquals(expectedHitPoints, result);
    }

    @Test
    public void defenderClassAlignmentCannotBeEvil(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Evil);
        try {
            //Act
            everCharacter.setCharacterClass(EverCraftCharacter.CharacterClassEnum.DEFENDER);
        } catch (Exception e) {
            //Assert
            assertEquals(e.getMessage(), "Defender Class cannot have evil alignment");
        }
    }

    @Test
    public void defaultClassAlignmentCanBeEvil(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Evil);

        //Act
        everCharacter.setCharacterClass(EverCraftCharacter.CharacterClassEnum.DEFAULT);

        //Assert
        assertEquals(EverCraftCharacter.CharacterClassEnum.DEFAULT, everCharacter.getCharacterClass());
    }

    @Test
    public void defenderClassDoublesDexterityModifierIfPositive(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Neutral);
        everCharacter.getAbilities().setDexterityScore(14);

        //Act
        everCharacter.setCharacterClass(EverCraftCharacter.CharacterClassEnum.DEFENDER);

        //Assert
        assertEquals(14, everCharacter.getModifiedArmor());
    }

    @Test
    public void defenderClassRegularDexterityModifierIfNegative(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Neutral);
        everCharacter.getAbilities().setDexterityScore(5);

        //Act
        everCharacter.setCharacterClass(EverCraftCharacter.CharacterClassEnum.DEFENDER);

        //Assert
        assertEquals(6, everCharacter.getModifiedArmor());
    }

}
