package OOP.inheritance.refactored_castle_extensible;

public class Handler {
    // 每个handler都记住game这个对象
    protected Game game; // game obj manager

    public Handler(Game game) {
        this.game = game;
    }

    public Handler() {
    }

    /**
     * word cmd " " 会不报错，do nothing, wait valid input
     * if valid input then 根据handler类型去用覆盖的处理方法doCmd（）
     * @param word
     */
    public void doCmd(String word) {
    }

    public boolean isBye() {
        return false;
    }
}
