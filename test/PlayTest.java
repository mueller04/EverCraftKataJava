import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class PlayTest {

    Play play;
    EverCraftCharacter everCharacter;
    EverCraftCharacter attackingEverCharacter;

    EverCraftCharacter mockDefendingChar;
    EverCraftCharacter mockAttackingChar;
    Abilities mockAbilities;

    @Before
    public void BeforeMethodClass() {
        play = new Play();
        everCharacter = new EverCraftCharacter("Example Name", EverCraftCharacter.Alignment.Good);
        attackingEverCharacter = new EverCraftCharacter("attacking character", EverCraftCharacter.Alignment.Neutral);

        mockDefendingChar = Mockito.mock(EverCraftCharacter.class);
        mockAttackingChar = Mockito.mock(EverCraftCharacter.class);
        mockAbilities = Mockito.mock(Abilities.class);

    }

    //This appears to be working with the Mocks...
    @Test
    public void rollHitsIfMeetsOpponentsArmorClass(){
        //Arrange
        Mockito.when(mockAttackingChar.getAbilities()).thenReturn(mockAbilities);
        Mockito.when(mockDefendingChar.getArmorPlusDexterity()).thenReturn(10);


        //Act
        String result = play.roll(mockDefendingChar, mockAttackingChar, 10);

        //Assert
        assertEquals("it's a hit", result);
    }

    //This appears to be working with the Mocks...
    @Test
    public void rollHitsIfExceedsOpponentsArmorClass(){
        //Arrange
        Mockito.when(mockAttackingChar.getAbilities()).thenReturn(mockAbilities);
        Mockito.when(mockDefendingChar.getArmorPlusDexterity()).thenReturn(10);


        //Act
        String result = play.roll(mockDefendingChar, mockAttackingChar, 11);

        //Assert
        assertEquals("it's a hit", result);
    }

    //This appears to be working with the Mocks...
    @Test
    public void rollFailsIfLessThanOpponentsArmorClass(){
        //Arrange
        Mockito.when(mockAttackingChar.getAbilities()).thenReturn(mockAbilities);
        Mockito.when(mockDefendingChar.getArmorPlusDexterity()).thenReturn(10);

        //Act
        String result = play.roll(mockDefendingChar, mockAttackingChar, 9);

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

    //You can see from these comments it used to be an integration test
    //I don't know why this test is failing, i'm expecting that a 2 strength modifier plus 8 roll will get me just
    // enough to score a hit
    @Test
    public void afterRollingStrengthModifierIsAddedMocked(){
        //Arrange
        Mockito.when(mockAttackingChar.getAbilities()).thenReturn(mockAbilities);
        Mockito.when(mockAttackingChar.getAbilities().getStrengthModifier(15)).thenReturn(2);
        //mockAttackingChar.getAbilities().setStrengthScore(15);
        everCharacter.setHitPoints(5);

        //Act
        String result = play.roll(everCharacter, mockAttackingChar, 8);

        //Assert
        assertEquals("it's a hit", result);
        //original roll in act is 2
        //original Assert is:
        //assertEquals(3, everCharacter.getHitPoints());
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


