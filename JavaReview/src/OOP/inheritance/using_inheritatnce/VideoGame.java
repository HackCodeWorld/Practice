package OOP.inheritance.using_inheritatnce;

public class VideoGame extends Item {
    private int numOfPlayers;

    public VideoGame(String title, int playingTime, String comment, int numOfPlayers) {
        super(title, playingTime, comment);
        this.numOfPlayers = numOfPlayers;
    }

    public VideoGame(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    @Override
    public void print() {
        System.out.println("VideoGame: ");
        super.print();
    }

    public static void main(String[] args) {

    }
}
