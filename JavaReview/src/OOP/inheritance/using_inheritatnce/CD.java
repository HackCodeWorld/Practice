package OOP.inheritance.using_inheritatnce;

/**
 * CD is 子类 of Item
 * inherit everything from farther-父类 -> Item
 * AND can add more things about itself
 * AND child子类可以change父类private fields if using father's function
 * AND child子类的attributes包含farther的copies of attributes
 * AND IF child子类's field with same name as 父类 then it is the one in child
 * AND IF executing in 父类 it is the one in 父类
 */
public class CD extends Item {
    private String artist;

    public CD(String title, String artist, int numofTracks, int playingTime, String comment) {
        // use farther constructor ITEM()
        // inherit all attributes from ITEM
        super(title, playingTime, comment);
        this.artist = artist;
    }

    public CD() {

    }

    @Override
    public String toString() {
        return "CD{" +
                "artist='" + artist + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return artist.equals(((CD)obj).artist);
    }

    public static void main(String[] args) {
        CD cd = new CD("a", "artist", 10, 10, "ok");
        CD cd2 = new CD("a", "artist", 10, 10, "ok");
        // use the root class 'Object' toString() method
        System.out.println(cd.toString());

        // Override Object class's equals
        System.out.println(cd.equals(cd2));
    }

    public void print() {
        System.out.println("CD: " + title + ":" + artist);
    }
}
