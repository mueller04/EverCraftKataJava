
public class EverCraftCharacter {

    private String name;
    private String alignment;

    public EverCraftCharacter(String name, String alignment){
        this.name = name;
        this.alignment = alignment;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAlignment(){
        return alignment;
    }
}
