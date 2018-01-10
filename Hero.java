public class Hero{
    private int col; 
    private int row;
    private int health;
    private Weapon weapon;
    
    public Hero(){
        col = 0;
        row = 9;
        health = 100;
        weapon = new Weapon("dagger", 10, 30);
    }
    
    public int getCol(){
        return col;
    }
    
    public int getRow(){
        return row;
    }
    
    public void setWeapon(){
        weapon = new Weapon("sword", 20, 50);
    }
    
    public int setCol(int num){
        col = num;
        return col;
    }
    
    public int setRow(int num){
        row = num;
        return row;
    }
    
    public int getHealth(){
        return health;
    }
    
    public void setHealth(int newHealth){
        health = newHealth;
    }
    
    public void useDagger(Monster monster){
        int currHealth;
        
        currHealth = monster.getHealth();
        monster.setHealth(currHealth - weapon.strength());
    }
}