import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AbilitiesTest.class,
        EverCraftCharacterTest.class,
        PlayTest.class,
        CharacterClassTest.class,
        RaceTest.class,
        IntegrationTests.class,
        WeaponTest.class})

    public class AllTests {
    }