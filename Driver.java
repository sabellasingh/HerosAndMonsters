import java.io.*;
import java.util.*;
import java.util.ArrayList;
public class Driver{
    public static void main(String args[]){
        Object[][] map = new Object[10][10];
        Hero hiro = new Hero();
        Monster m1 = new Monster();
        Monster m2 = new Monster();
        Monster m3 = new Monster();
        Monster m4 = new Monster();
        Monster m5 = new Monster();
        Monster m6 = new Monster();
        Farmer f1 = new Farmer(1);
        Farmer f2 = new Farmer(2);
        Potion p1 = new Potion();
        Potion p2 = new Potion();
        int objectsPlaced = 10;
        int randCol;
        int randRow;
        boolean gameWon = false;
        boolean heroMoved = false;
        Scanner userInput = new Scanner(System.in);
        String userChoice;
        Object setup[] = new Object[10];
        int[] location = new int[2];
        setup[0] = m1;
        setup[1] = m2;
        setup[2] = m3;
        setup[3] = m4;
        setup[4] = m5;
        setup[5] = m6;
        setup[6] = f1;
        setup[7] = f2;
        setup[8] = p1;
        setup[9] = p2;
        Monster currMonster;
        int monstersKilled = 0;
        
        map[hiro.getRow()][hiro.getCol()] = hiro;
        while(objectsPlaced != 0){
            randCol = (int)(Math.random() * 10); 
            randRow = (int)(Math.random() * 10); 
            if(map[randRow][randCol] == null){
                map[randRow][randCol] = setup[objectsPlaced - 1];
                objectsPlaced -=1;
            }
        }
        while((hiro.getHealth() >= 0 ) && (gameWon != true)){
            printMap(map, setup, hiro);
            location = getMovement(hiro);
            if(checkLocation(hiro, map, setup, location)){
                moveHero(hiro, map, location);
            }
            for(int i = 0; i < 6; i++){
                currMonster = (Monster)(setup[i]);
                if(currMonster.getHealth() <= 0){
                    monstersKilled +=1;
                }
            }
            if(monstersKilled == 6){
                gameWon = true;
            }
            monstersKilled = 0;
        }
        if(hiro.getHealth() <= 0 ){
            System.out.println("\n\nHero has died. Monsters have won and ravaged the city.");
        }else if(gameWon){
            System.out.println("\n\nHero has won and slain all monsters! The city is saved!");
        }
    }
    
    public static void printMap(Object[][] map, Object[] setup, Hero hiro){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] == null){
                    System.out.print(".\t");
                }
                if(map[i][j] == hiro){
                    System.out.print("H\t");
                }
                if((map[i][j] == setup[0]) || (map[i][j] == setup[1]) || (map[i][j] == setup[2]) ||(map[i][j] == setup[3]) || (map[i][j] == setup[4]) || (map[i][j] == setup[5])){
                    System.out.print("M\t");
                }
                if((map[i][j] == setup[6]) || (map[i][j] == setup[7])){
                    System.out.print("F\t");
                }
                if((map[i][j] == setup[8]) || (map[i][j] == setup[9])){
                    System.out.print("P\t");
                }
            }
            System.out.println("\n");
        }
    }
    
    public static int[] getMovement(Hero hiro){
        int[] location = new int[2];
        Scanner userInput = new Scanner(System.in);
        String userChoice;
        boolean heroMoved = false;
        System.out.println("Enter direction (north, south, east, west): ");
        userChoice = userInput.nextLine();
        userChoice = userChoice.toLowerCase();
        int row = hiro.getRow(); 
        int col = hiro.getCol(); ;
        if(userChoice.equals("north") || userChoice.equals("w")){
            row -= 1;
        }else if(userChoice.equals("south") || userChoice.equals("s")){
            row += 1;
        }else if(userChoice.equals("east") ||  userChoice.equals("d")){
            col += 1;
        }else if(userChoice.equals("west") || userChoice.equals("a")){
            col -= 1;
        }
        location[0] = row;
        location[1] = col;
        return location;
    }
    
    public static boolean checkLocation(Hero hiro, Object[][] map, Object[] setup, int[] location){
        int row = location[0];
        int col = location[1];
        boolean locationIsValid = true;
        boolean heroRan = false;
        String weaponReceived;
        Monster monster;
        int currAttack;
        if((row < 0) || (col < 0) || (row > 9) || (col > 9)){
            System.out.println("Error! Enter a valid direction for our hero!: ");
            locationIsValid = false;
        }else if(map[row][col] != null){
            if((map[row][col] == setup[0]) || (map[row][col] == setup[1]) || (map[row][col] == setup[2]) ||(map[row][col] == setup[3]) || (map[row][col] == setup[4]) || (map[row][col] == setup[5])){
                heroRan = fightMonster(map, row, col, hiro, location);
                if(heroRan == true){
                    locationIsValid = false;
                }
            }else if((map[row][col] == setup[6]) || (map[row][col] == setup[7])){
                weaponReceived = talkFarmer(map, row, col, hiro, setup);
                if(weaponReceived.equals("armor")){
                    for(int k = 0; k < 5; k++){
                        monster = (Monster)(setup[k]);
                        currAttack = monster.getAttack();
                        monster.setAttack(currAttack * (2 / 3));
                    }
                }else if(weaponReceived.equals("sword")){
                    hiro.setWeapon();
                }
                locationIsValid = false;
            }else if((map[row][col] == setup[8]) || (map[row][col] == setup[9])){
                usePotion(map, row, col, hiro);
            }
        }
        return locationIsValid;
    }
    
    public static void moveHero(Hero hiro, Object[][] map, int[] location){
        int row = location[0]; 
        int col = location[1];
        
        map[hiro.getRow()][hiro.getCol()] = null;
        hiro.setRow(row);
        hiro.setCol(col);
        map[hiro.getRow()][hiro.getCol()] = hiro;
    }
    
    public static boolean fightMonster(Object[][] map, int i, int j, Hero hiro, int[] location){
        Monster monster = (Monster)(map[i][j]);
        boolean run = false;
        int currHealth;
        String choice;
        Scanner UserInput;
        System.out.println("\n\nHero encounters a monster! The monster engages!");
        System.out.println("Health: " + monster.getHealth() + "\tSpeed: " + monster.getSpeed() + "\tAttack: " + monster.getAttack());
        int speed;
        int probability;
        int oldRow;
        int oldCol;
        while(hiro.getHealth() > 0 && run == false && monster.getHealth() > 0){
            System.out.println("\n\nEnter an action (run,attack): ");
            UserInput = new Scanner(System.in);
            choice = UserInput.nextLine().toLowerCase();
            if((choice.equals("attack") == false) && (choice.equals("run") == false)){
                while((choice.equals("attack") == false) && (choice.equals("run") == false)){
                    System.out.println("Enter a valid choice!");
                    System.out.println("\n\nEnter an action (run,attack): ");
                    UserInput = new Scanner(System.in);
                    choice = UserInput.nextLine().toLowerCase();
                    System.out.println(choice);
                }
            }
            
            if(choice.equals("run")){
                probability = (int)((Math.random() * 4) + 1);
                speed = monster.getSpeed();
                if(speed == 3){
                    System.out.println("Hero tries to run! The monster is too fast!");
                }else if(speed == 2){
                    if(probability == 1){
                        System.out.println("Hero succesfully runs!");
                        run = true;
                        break;
                    }else{
                        System.out.println("Hero tries to run! The monster is too fast!");
                    }
                }else if(speed == 1){
                    if((probability == 1) || (probability == 2)){
                        System.out.println("Hero succesfully runs!");
                        run = true;
                        break;
                    }else{
                        System.out.println("Hero tries to run! The monster is too fast!");
                    }
                }else if(speed == 0){
                    if((probability == 1) || (probability == 2) || (probability == 3)){
                        System.out.println("Hero succesfully runs!");
                        run = true;
                        break;
                    }else{
                        System.out.println("Hero tries to run! The monster is too fast!");
                    }
                }
            }else if(choice.equals("attack")){
                hiro.useDagger(monster);
                if(monster.getHealth() > 0){
                    System.out.println("Hero attacks! Monster’s energy goes down to " + monster.getHealth() + "/100");
                }else if(monster.getHealth() <= 0){
                    System.out.println("Hero attacks! Monster is slain! Monster health: 0/100");
                    monster.setHealth(0);
                }
            }
            
            if(run != true){
                currHealth = hiro.getHealth();
                if(currHealth < 0){
                    hiro.setHealth(0);
                    System.out.println("Hero has died.");
                }else if(monster.getHealth() > 0){
                    System.out.println("The monster attacks!");
                    hiro.setHealth(currHealth - monster.getAttack());
                    System.out.println("Hero’s energy goes down to " + hiro.getHealth() + "/100");
                }
            }
        }
        return run;
    }
    
    public static String talkFarmer(Object[][] map, int i, int j, Hero hiro, Object[] setup){
        Farmer farmer = (Farmer)(map[i][j]);
        String weaponReceived = "";
        int monstersKilled = 0;
        Monster currMonster;
        System.out.println("Hero finds a farmer");
        for(int k = 0; k < 6; k++){
            currMonster = (Monster)(setup[k]);
            if(currMonster.getHealth() <= 0){
                monstersKilled += 1;
            }
        }
        weaponReceived = farmer.interact(monstersKilled);
        return weaponReceived;
    }
    
    public static void usePotion(Object[][] map, int i, int j, Hero hiro){
        Potion potion = (Potion)(map[i][j]);
        potion.usePotion(hiro);
        System.out.println("Hero has found a potion! It has restored him to full health!");
        System.out.println("Hero Health: " + hiro.getHealth());
    }
}