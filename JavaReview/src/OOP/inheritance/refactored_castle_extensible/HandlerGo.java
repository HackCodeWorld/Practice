package OOP.inheritance.refactored_castle_extensible;

public class HandlerGo extends Handler{

    // 比较曲折的办法拿到Game的指针
    public HandlerGo(Game game) {
        super(game);
    }

    @Override
    public void doCmd(String word) {
        game.goRoom(word); // 就可以调用game的goRoom了
    }
}
