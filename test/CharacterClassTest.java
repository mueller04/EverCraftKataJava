import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CharacterClassTest {

    EverCraftCharacter everCharacter;


    @Test
    public void defenderClassIncreasesHitPoints(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", Enum.Alignment.Good);
        everCharacter.setCharacterClass(Enum.CharacterClassEnum.DEFENDER);
        int expectedHitPoints = 10;

        //Act
        int result = everCharacter.getHitPoints();

        //Assert
        assertEquals(expectedHitPoints, result);
    }

    @Test
    public void defenderClassAlignmentCannotBeEvil(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", Enum.Alignment.Evil);
        try {
            //Act
            everCharacter.setCharacterClass(Enum.CharacterClassEnum.DEFENDER);
        } catch (Exception e) {
            //Assert
            assertEquals(e.getMessage(), "Defender Class cannot have evil alignment");
        }
    }

    @Test
    public void defaultClassAlignmentCanBeEvil(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", Enum.Alignment.Evil);

        //Act
        everCharacter.setCharacterClass(Enum.CharacterClassEnum.DEFAULT);

        //Assert
        assertEquals(Enum.CharacterClassEnum.DEFAULT, everCharacter.getCharacterClass());
    }

    @Test
    public void defenderClassDoublesDexterityModifierIfPositive(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", Enum.Alignment.Neutral);
        everCharacter.getAbilities().setDexterityScore(14);

        //Act
        everCharacter.setCharacterClass(Enum.CharacterClassEnum.DEFENDER);

        //Assert
        assertEquals(14, everCharacter.getModifiedArmor());
    }

    @Test
    public void defenderClassRegularDexterityModifierIfNegative(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", Enum.Alignment.Neutral);
        everCharacter.getAbilities().setDexterityScore(5);

        //Act
        everCharacter.setCharacterClass(Enum.CharacterClassEnum.DEFENDER);

        //Assert
        assertEquals(6, everCharacter.getModifiedArmor());
    }

    @Test
    public void warlordClassTripleDamageOnCriticalHits(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", Enum.Alignment.Evil);
        everCharacter.setCharacterClass(Enum.CharacterClassEnum.WARLORD);

        //Act
        int result = everCharacter.calculateHitPointsAndAttackStrength(true);

        //Assert
        assertEquals(3, result);
    }

    @Test
    public void warlordClassAlignmentCannotBeGood(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", Enum.Alignment.Good);
        try {
            //Act
            everCharacter.setCharacterClass(Enum.CharacterClassEnum.WARLORD);
        } catch (Exception e) {
            //Assert
            assertEquals(e.getMessage(), "Warlord Class cannot have good alignment");
        }
    }

}
