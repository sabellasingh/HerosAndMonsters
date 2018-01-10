public class Armor{
    int defense;
    public Armor(){
        defense = 1/3;
    }
    
    public void useArmor(Monster monster){
        int currAtt;
        currAtt = monster.getAttack();
        currAtt = currAtt/3; 
        monster.setAttack(currAtt);
    }
}