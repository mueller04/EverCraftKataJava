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
}
