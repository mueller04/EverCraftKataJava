import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

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

    @Test
    public void rollHitsIfMeetsOpponentsArmorClass(){
        //Arrange
        Mockito.when(mockAttackingChar.getAbilities()).thenReturn(mockAbilities);
        Mockito.when(mockAttackingChar.getModifiedRollNumber(anyInt())).thenReturn(10);
        Mockito.when(mockDefendingChar.getModifiedArmor()).thenReturn(10);

        //Act
        String result = play.roll(mockDefendingChar, mockAttackingChar, 10);

        //Assert
        assertEquals("it's a hit", result);
    }

    @Test
    public void rollFailsIfLessThanOpponentsArmorClass(){
        //Arrange
        Mockito.when(mockAttackingChar.getAbilities()).thenReturn(mockAbilities);
        Mockito.when(mockDefendingChar.getModifiedArmor()).thenReturn(10);

        //Act
        String result = play.roll(mockDefendingChar, mockAttackingChar, 9);

        //Assert
        assertEquals("attack glanced off the armor", result);
    }


    @Test
    public void modifiedRollBeatsModifiedArmor(){
        //Arrange
        Mockito.when(mockAttackingChar.getAbilities()).thenReturn(mockAbilities);
        Mockito.when(mockDefendingChar.getModifiedArmor()).thenReturn(9);
        Mockito.when(mockAttackingChar.getModifiedRollNumber(8)).thenReturn(10);

        //Act
        String result = play.roll(mockDefendingChar, mockAttackingChar, 8);

        //Assert
        assertEquals("it's a hit", result);
    }


    @Test
    public void successfulAttackEarns10ExperiencePoints(){
        //Arrange
        Mockito.when(mockAttackingChar.getAbilities()).thenReturn(mockAbilities);
        Mockito.when(mockDefendingChar.getModifiedArmor()).thenReturn(10);
        Mockito.when(mockAttackingChar.getModifiedRollNumber(anyInt())).thenReturn(10);

        //Act
        play.roll(mockDefendingChar, mockAttackingChar, 10);

        //Assert
        verify(mockAttackingChar).addExperiencePoints(10);
    }



}


