
public class EverCraftCharacter {

    public enum Alignment {
        Good, Evil, Neutral
    }

    private String name;
    private Alignment alignment;
    private int armor = 10;
    private int hitPoints = 5;

    public EverCraftCharacter(String name, Alignment alignment){
        this.name = name;
        this.alignment = alignment;
    }

    //Getters and Setters
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

    public int getArmor(){
        return armor;
    }

    public int getHitPoints(){
        return hitPoints;
    }
}
