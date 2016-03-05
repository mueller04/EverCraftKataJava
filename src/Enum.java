/**
 * Created by Mike on 3/4/16.
 */
public class Enum {

    public enum Alignment {
        Good, Evil, Neutral
    }

    public enum LifeStatus {
        Alive, Dead
    }

    public enum CharacterClassEnum {
        DEFAULT(new DefaultCharacterClass()), DEFENDER(new DefenderClass());

        public CharacterClass characterClass;

        CharacterClassEnum(CharacterClass characterClass) {
            this.characterClass = characterClass;
        }
    }

}
