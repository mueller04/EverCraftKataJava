import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mike on 3/4/16.
 */
public class RaceTest {

    EverCraftCharacter everCharacter;


    @Test
    public void orcIncreasesHitPointsBy2(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", Enum.Alignment.Good);
        everCharacter.setRace(Enum.RaceEnum.ORC);

        //Act
        int result = everCharacter.calculateHitPointsAndAttackStrength(false);

        //Assert
        assertEquals(3, result);
    }

    @Test
    public void orcIncreasesArmorBy2(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", Enum.Alignment.Good);
        everCharacter.setRace(Enum.RaceEnum.ORC);


        //Act
        int result = everCharacter.getModifiedArmor();

        //Assert
        assertEquals(12, result);
    }

    @Test
    public void dwarfIncreasesConstitutionModifierBy1(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", Enum.Alignment.Good);
        everCharacter.setRace(Enum.RaceEnum.DWARF);
        everCharacter.getAbilities().setConstitutionScore(20);

        //Act
        everCharacter.calculateHitPoints(1);

        //Assert
        assertEquals(11, everCharacter.getHitPoints());
    }

    @Test
        public void dwarfDoublesConstitutionModifierPerLevelNonWarlord(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", Enum.Alignment.Good);
        everCharacter.setRace(Enum.RaceEnum.DWARF);
        everCharacter.getAbilities().setConstitutionScore(20);

        //Act
        everCharacter.calculateHitPoints(2);

        //Assert
        assertEquals(28, everCharacter.getHitPoints());
    }

    @Test
    public void elfSubtractsFrom1ToConstitutionModifier(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", Enum.Alignment.Good);
        everCharacter.setRace(Enum.RaceEnum.ELF);
        everCharacter.getAbilities().setConstitutionScore(20);

        //Act
        everCharacter.calculateHitPoints(1);

        //Assert
        assertEquals(9, everCharacter.getHitPoints());
    }

    @Test
    public void elfAdds1ToDexterityModifier(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", Enum.Alignment.Good);
        everCharacter.setRace(Enum.RaceEnum.ELF);

        //Act
        int result = everCharacter.getModifiedArmor();

        //Assert
        assertEquals(11, result);
    }

    @Test
    public void nonElfReturns0ForCriticalRange(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", Enum.Alignment.Good);
        everCharacter.setRace(Enum.RaceEnum.DWARF);

        //Act
        int result = everCharacter.getCriticalRange();

        //Assert
        assertEquals(0, result);
    }

    @Test
    public void elfReturns1ForCriticalRange(){
        //Arrange
        everCharacter = new EverCraftCharacter("Example Name", Enum.Alignment.Good);
        everCharacter.setRace(Enum.RaceEnum.ELF);

        //Act
        int result = everCharacter.getCriticalRange();

        //Assert
        assertEquals(1, result);
    }

}
