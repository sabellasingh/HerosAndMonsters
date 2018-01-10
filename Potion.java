public class Potion{
    private int fullHealth;
    
    public Potion(){
        fullHealth = 100;
    }
    
    public void usePotion(Hero hero){
        hero.setHealth(fullHealth);
    }
}