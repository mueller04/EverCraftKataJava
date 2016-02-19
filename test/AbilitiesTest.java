import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AbilitiesTest {

    @Test
    public void AbilitiesDefaultTo10(){
        //Arrange

        //Act
        Abilities abilities = new Abilities();

        //Assert
        assertEquals(10, abilities.getStrength());
        assertEquals(10, abilities.getDexterity());
        assertEquals(10, abilities.getConstitution());
        assertEquals(10, abilities.getWisdom());
        assertEquals(10, abilities.getIntelligence());
        assertEquals(10, abilities.getCharisma());
    }




}
