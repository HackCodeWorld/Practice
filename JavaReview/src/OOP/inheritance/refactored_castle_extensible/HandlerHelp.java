package OOP.inheritance.refactored_castle_extensible;

public class HandlerHelp extends Handler{
    @Override
    public void doCmd(String word) { // 以下为用户命令 help
        System.out.println("迷路了吗？你可以做的命令有：go bye help");
        System.out.println("如：\tgo east");
    }
}
