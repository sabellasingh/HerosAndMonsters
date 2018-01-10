public class Farmer{
    private String convo;
    private int col; 
    private int row;
    private int farmerNumber;
    
    public Farmer(int farmerNum){
        convo = "";
        col = (int)(Math.random() * 10);
        row = (int)(Math.random() * 10);
        farmerNumber = farmerNum;
    }
    
    public int getCol(){
        return col;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getFarmerNum(){
        return farmerNumber;
    }
    
    public String interact(int monstersKilled){
        boolean armorReceived = false;
        boolean swordReceived = false;
        String weaponReceived = "";
        if(farmerNumber == 1){
            armorReceived = armorInteraction(monstersKilled);
        }else if(farmerNumber == 2){
            swordReceived = swordInteraction(monstersKilled);
        }
        if(armorReceived == true){
            weaponReceived = "armor";
        }else if(swordReceived == true){
            weaponReceived = "sword";
        }
        return weaponReceived;
    }
    
    private boolean armorInteraction(int monstersKilled){
        boolean armorReceived = false;
        if(monstersKilled < 2){
            System.out.println("Come back when you have killed 2 monsters.\nProve yourself, and I will give you stronger armor");
        }else if(monstersKilled >= 2){
            System.out.println("You have proven yourself worthy.\nTake this armor and slay the monsters!");
            armorReceived = true;
        }
        return armorReceived;
    }
    
    private boolean swordInteraction(int monstersKilled){
        boolean swordReceived = false;
        if(monstersKilled < 4){
            System.out.println("Come back when you have killed 4 monsters.\nProve yourself, and I will give you a sword");
        }else if(monstersKilled >= 2){
            System.out.println("You have proven yourself worthy.\nTake this sword and slay the monsters!");
            swordReceived = true;
        }
        return swordReceived;
    }
}