public class Monster{
    private int col; 
    private int row;
    private int health;
    private int speed;
    private int attack;
    
    public Monster(){
        col = (int)(Math.random() * 10);
        row = (int)(Math.random() * 10);
        health = 100;
        attack = (int)(Math.random() * 30) + 1;
        speed = (int)(Math.random() * 4);
    }
    
    public int getCol(){
        return col;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getAttack(){
        int newAttack;
        int bottomRange = attack-10;
        int topRange = attack;
        
        newAttack = (int)((Math.random() * (topRange - bottomRange)) + bottomRange);
        if(newAttack < 0){
            while(newAttack <= 0){
                newAttack += 1;
            }
        }
        return newAttack;
    }
    
    public int getHealth(){
        return health;
    }
    
    public int getSpeed(){
        return speed;
    }
    
    public void setHealth(int newHealth){
        health = newHealth;
    }
    
    public void setAttack(int newAttack){
        attack = newAttack;
    }
}
