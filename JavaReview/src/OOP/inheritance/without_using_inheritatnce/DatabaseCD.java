package OOP.inheritance.without_using_inheritatnce;

import java.util.ArrayList;

/**
 * Showing duplicate codes without inheritance
 */
public class DatabaseCD {
    private ArrayList <CD> listCD = new ArrayList<>();
    private ArrayList <DVD> listDVD = new ArrayList<>();

    public void add(CD cd){
        listCD.add(cd);
    }

    // Overloading
    // same method name
    // different params
    public void add(DVD dvd){
        listDVD.add(dvd);
    }

    public void list(){
        for (CD cd: listCD) {
            cd.print();
        }
        for (DVD dvd: listDVD) {
            dvd.print();
        }
    }

    public static void main(String[] args) {
        DatabaseCD db = new DatabaseCD();
        db.add(new CD("abc", "Duke", 10, 20, "nice ... frankly"));
        db.add(new CD("abc", "Duke", 10, 20, "nice ... frankly"));
        db.add(new CD("abc", "Duke", 10, 20, "nice ... frankly"));

        db.add(new DVD("abc", "Duke", 10,  "nice ... frankly"));
        db.add(new DVD("abc", "Duke", 10,  "nice ... frankly"));
        db.add(new DVD("abc", "Duke", 10,  "nice ... frankly"));

        db.list();
    }
}
