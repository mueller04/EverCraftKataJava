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
        Mockito.when(mockAttackingChar.getModifiedRollNumberCalculatePreTurnUpdate(anyInt())).thenReturn(10);
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

    //Move some of this functionality into the character class then unit test it in there
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

    @Test
    public void modifiedRollBeatsModifiedArmor(){
        //Arrange
        Mockito.when(mockAttackingChar.getAbilities()).thenReturn(mockAbilities);
        Mockito.when(mockDefendingChar.getModifiedArmor()).thenReturn(9);
        Mockito.when(mockAttackingChar.getModifiedRollNumberCalculatePreTurnUpdate(anyInt())).thenReturn(10);

        //Act
        String result = play.roll(mockDefendingChar, mockAttackingChar, 8);

        //Assert
        assertEquals("it's a hit", result);
    }

    @Test
    public void afterRolling20StrengthModifierIsAddedAndDoubled(){
        //Arrange
        Mockito.when(mockAttackingChar.getAbilities()).thenReturn(mockAbilities);
        Mockito.when(mockAttackingChar.getModifiedRollNumberCalculatePreTurnUpdate(anyInt())).thenReturn(20);
        Mockito.when(mockAttackingChar.getAbilities().getStrengthModifier(anyInt())).thenReturn(2);
        Mockito.when(mockDefendingChar.getHitPoints()).thenReturn(5);
        Mockito.when(mockDefendingChar.getModifiedArmor()).thenReturn(10);

        //Act
        play.roll(mockDefendingChar, mockAttackingChar, 20);

        //Assert
        verify(mockDefendingChar).setHitPoints(1);
    }

    //move this to CharacterTest to test against method calculateHitPoints() verify using getHitPoints that constitution modifier was added
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
        Mockito.when(mockAttackingChar.getAbilities()).thenReturn(mockAbilities);
        Mockito.when(mockDefendingChar.getModifiedArmor()).thenReturn(10);
        Mockito.when(mockAttackingChar.getModifiedRollNumberCalculatePreTurnUpdate(10)).thenReturn(10);

        //Act
        play.roll(mockDefendingChar, mockAttackingChar, 10);

        //Assert
        verify(mockAttackingChar).addExperiencePoints(10);
    }



}


