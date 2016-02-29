import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
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
        Mockito.when(mockDefendingChar.getArmorPlusDexterity()).thenReturn(10);

        //Act
        String result = play.roll(mockDefendingChar, mockAttackingChar, 10);

        //Assert
        assertEquals("it's a hit", result);
    }

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
    public void afterRollingAttackRollModifierIsAdded(){
        //Arrange
        Mockito.when(mockAttackingChar.getAbilities()).thenReturn(mockAbilities);
        Mockito.when(mockAttackingChar.getAttackRollModifierCalculatePreTurnUpdate()).thenReturn(2);
        Mockito.when(mockDefendingChar.getArmorPlusDexterity()).thenReturn(9);

        //Act
        String result = play.roll(mockDefendingChar, mockAttackingChar, 8);

        //Assert
        assertEquals("it's a hit", result);
    }

    //This works in validating that amountToReduceHitPointsBy can double from 1 to 2,
    //but I can't seem to use getStrengthModifier(15) in order to make amountToReduceHitPointsBy equal 4.
    @Test
    public void afterRolling20StrengthModifierIsAddedAndDoubled(){
        //Arrange
        //attackingEverCharacter.getAbilities().setStrengthScore(15);
        //everCharacter.setHitPoints(5);
        Mockito.when(mockAttackingChar.getAbilities()).thenReturn(mockAbilities);
        Mockito.when(mockAttackingChar.getAttackRollModifierCalculatePreTurnUpdate()).thenReturn(0);
        Mockito.when(mockAttackingChar.getAbilities().getStrengthModifier(15)).thenReturn(2);
        Mockito.when(mockDefendingChar.getHitPoints()).thenReturn(5);
        Mockito.when(mockDefendingChar.getArmorPlusDexterity()).thenReturn(10);

        //Act
        play.roll(mockDefendingChar, mockAttackingChar, 20);

        //Assert
        //assertEquals(1, everCharacter.getHitPoints());
        //verify(mockDefendingChar).setHitPoints(1);
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
        Mockito.when(mockDefendingChar.getArmorPlusDexterity()).thenReturn(10);

        //Act
        play.roll(mockDefendingChar, mockAttackingChar, 10);

        //Assert
        verify(mockAttackingChar).addExperiencePoints(10);
    }



}


