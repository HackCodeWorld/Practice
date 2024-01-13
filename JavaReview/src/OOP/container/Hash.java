package OOP.container;

import java.util.HashMap;
import java.util.Scanner;

public class Hash {

}

class Coin {
    private HashMap<Integer, String> coinNames = new HashMap<>();

    public Coin(){
        coinNames.put(1, "penny");
        coinNames.put(10, "dime");
        coinNames.put(25, "quarter");
        coinNames.put(50, "half-dollar");
        coinNames.put(50, "50 cent");
        System.out.println(coinNames);
    }
    public String getName(int amount) {
        if (coinNames.containsKey(amount)) return coinNames.get(amount);
        else return "not found";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(true){
            int amount = in.nextInt();
            Coin coin = new Coin();
            String name = coin.getName(amount);
            System.out.println(name);
        }
    }
}
