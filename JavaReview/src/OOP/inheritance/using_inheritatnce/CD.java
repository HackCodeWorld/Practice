package OOP.inheritance.using_inheritatnce;

/**
 * CD is 子类 of Item
 * inherit everything from farther Item
 * AND can add more things about itself
 */
public class CD extends Item {
    private String artist;

    public CD(String title, String artist, int numofTracks, int playingTime, String comment) {
        // use farther constructor ITEM()
        // inherit all attributes from ITEM
        super(title, playingTime, comment);
        this.artist = artist;
    }
    public static void main(String[] args) {

    }

    public void print() {
        System.out.println("CD: " + title + ":" + artist);
    }
}
