package OOP.inheritance.without_using_inheritatnce;

/**
 * Showing duplicate codes without inheritance
 */
public class DVD {
    private String title;
    private String director;
    private int playingTime;
    private boolean gotIt = false;
    private String comment;

    public DVD(String title, String director, int playingTime, String comment) {
        this.title = title;
        this.director = director;
        this.playingTime = playingTime;
        this.comment = comment;
    }

    public void print() {
        System.out.println(title + ":" + director);
    }

}
