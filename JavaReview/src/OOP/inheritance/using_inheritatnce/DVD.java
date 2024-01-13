package OOP.inheritance.using_inheritatnce;

/**
 * DVD is 子类 of Item
 * inherit everything from farther Item
 * AND can add more things about itself
 */
public class DVD extends Item{
    private String director;

    public DVD(String title, String director, int playingTime, String comment) {
        // use farther constructor ITEM()
        // inherit all attributes from ITEM
        super(title, playingTime, comment);
        this.director = director;
    }

    public void print() {
        System.out.println("DVD" + ":" + title + ":" + director);
    }
}
