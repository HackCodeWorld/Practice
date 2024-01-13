package OOP.inheritance.using_inheritatnce;

public class Item {
    protected String title; // 子类&同包可以access
    private int playingTime;
    private boolean gotIt = false;
    private String comment;

    /**
     * all shared/duplicated attributes stored in farther constructor
     * @param title
     * @param playingTime
     * @param comment
     */
    public Item(String title, int playingTime, String comment) {
        this.title = title;
        this.playingTime = playingTime;
        this.comment = comment;
    }

    public Item() {

    }

    public void print() {
    }
}
