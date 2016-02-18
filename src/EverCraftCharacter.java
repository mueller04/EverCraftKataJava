
public class EverCraftCharacter {

    public enum Alignment {
        Good, Evil, Neutral
    }

    private String name;
    private Alignment alignment;

    public EverCraftCharacter(String name, Alignment alignment){
        this.name = name;
        this.alignment = alignment;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Alignment getAlignment(){
        return alignment;
    }

    public void setAlignment(Alignment alignment){
        this.alignment = alignment;
    }
}
