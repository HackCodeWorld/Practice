package OOP.inheritance.using_inheritatnce;

import java.util.ArrayList;

/**
 *
 */
public class DatabaseCD {
//    private ArrayList <CD> listCD = new ArrayList<>();
//    private ArrayList <DVD> listDVD = new ArrayList<>();

    // ONLY 1 container 父类
    private ArrayList<Item> listItem = new ArrayList<>();

//    public void add(CD cd){
//        listCD.add(cd);
//    }
//
//    // Overloading
//    // same method name
//    // different params
//    public void add(DVD dvd){
//        listDVD.add(dvd);
//    }

    // ONLY 1 add & 1 loop
    public void add(Item item) {
        listItem.add(item); // 只要用父类加就行
    }

    public void list() {
//        for (CD cd : listCD) {
//            cd.print();
//        }
//        for (DVD dvd : listDVD) {
//            dvd.print();
//        }
        for (Item item : listItem) {
            // Polymorphism:
            // Declarative type is Item
            // Dynamic type can be DVD or CD
            item.print();
        }
    }

    public static void main(String[] args) {
        Object o = new Object();
        o.toString();

        DatabaseCD db = new DatabaseCD();
        db.add(new CD("abc", "Duke", 10, 20, "nice ... frankly"));
        db.add(new CD("abc", "Duke", 10, 20, "nice ... frankly"));
        db.add(new CD("abc", "Duke", 10, 20, "nice ... frankly"));

        db.add(new DVD("abc", "Duke", 10, "nice ... frankly"));
        db.add(new DVD("abc", "Duke", 10, "nice ... frankly"));
        db.add(new DVD("abc", "Duke", 10, "nice ... frankly"));

        // Now, added a new Class named VideoGame to 扩展
        db.add(new VideoGame("gamer", 2, "nice ... frankly", 10));
        db.add(new VideoGame("gamer", 2, "nice ... frankly", 10));
        db.add(new VideoGame("gamer", 2, "nice ... frankly", 10));

        db.list();
    }
}
