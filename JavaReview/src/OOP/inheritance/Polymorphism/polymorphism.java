package OOP.inheritance.Polymorphism;

import OOP.inheritance.using_inheritatnce.*;

/**
 * Polymorphism & CAST 转型
 * CAST - Assign An Object to ANOTHER VARIABLE
 * MECHANISM: let the manager (the third party to) manage the common obj
 * 也就是Item-father去管理
 * which is diff from C++ (incomplete OOP)
 */
public class polymorphism {
    public static void main(String[] args) {
        // child对象可以assign给father
        Item i1 = new Item();
        Item i2 = new DVD();
        Item i3 = new CD();

        // child对象可以assign给Item Arrays
        DatabaseCD database = new DatabaseCD(); //Item Arrays
        database.add(i3); // CD
        database.add(i2); // DVD

        /** CAST - Assign An Object to ANOTHER VARIABLE
         * MECHANISM: let the manager (the third party to) manage the common obj
         * 也就是Item-father去管理
         * which is diff from C++ (incomplete OOP)
         */
        // CAST 造型
        String s = "hello";
        s = "bye"; // manager switches to "bye" from "hello"

        // let's see: child obj -> father var
        Item item = new Item();
        /**
         * Required type:
         * CD
         * Provided:
         * Item
         */
//        CD cd = item;
        // flipped okay
        // UPCASTING item is CD, just treat it as Item, to use Item's methods
        item = new CD();

        // what if CD(child) -> CD?
        CD cd = new CD(); // ofc okay
        // but...
        /**
         * Required type:
         * CD
         * Provided:
         * Item
         */
//        CD cd2 = item;
        // why??? item is a CD!
        // bc for COMPILER, it's not intelligent enough
        // DOWN-CASTING cd3 is still Item-item, just treat it as CD to use CD's methods
        CD cd3 = (CD) item; // adding (CD) to 造型 CAST (convert by hands)
        // if如果你确定item is a CD
        // ATTENTION: it's not always SAFE!

    }
}
