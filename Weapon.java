public class Weapon{
    private int bottomRange;
    private int topRange;
    private String type;
    
    public Weapon(String weaponType, int bottRange, int topRange){
        type = weaponType;
        bottomRange = bottRange;
        this.topRange = topRange;
    }
    
    public int strength(){
        int randNum;
        
        randNum = (int)((Math.random() * (topRange - bottomRange)) + bottomRange + 1);
        return randNum;
    }
}