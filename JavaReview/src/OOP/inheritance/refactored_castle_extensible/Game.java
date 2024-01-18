package OOP.inheritance.refactored_castle_extensible;

import java.util.HashMap;
import java.util.Scanner;

/**
 * A castle game
 */
public class Game {
    private Room currentRoom;
    private HashMap<String, Handler> handlers = new HashMap<>();

    public Game() {
        handlers.put("bye", new HandlerBye());
        handlers.put("help", new HandlerHelp());
        handlers.put("go", new HandlerGo(this));
        createRooms();
    }

    private void createRooms() {
        Room outside, lobby, pub, study, bedroom;

        // 制造房间
        outside = new Room("城堡外");
        lobby = new Room("大堂");
        pub = new Room("小酒吧");
        study = new Room("书房");
        bedroom = new Room("卧室");

        // 初始化房间的出口
        // 城堡外
        outside.setExits("east", lobby);
        outside.setExits("south", study);
        outside.setExits("west", pub);
        // 大堂
        lobby.setExits("west", outside);
        // 小酒吧
        pub.setExits("east", outside);
        // 书房
        study.setExits("north", outside);
        study.setExits("east", bedroom);
        // 卧室
        bedroom.setExits("west", study);

        /** new features extension 加了 'up' 'down' 的方向 **/
        lobby.setExits("up", pub);
        pub.setExits("down", lobby);
        currentRoom = outside;  //	从城堡门外开始
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("欢迎来到城堡！");
        System.out.println("这是一个超级无聊的游戏。");
        System.out.println("如果需要帮助，请输入 'help' 。");
        System.out.println();
        showPrompt(); // ✅
    }

    public void goRoom(String direction) {
        Room nextRoom = null;
        // 利用多态，让room对象自己把握出口的关系
        // 做到解耦合
        nextRoom = currentRoom.getExit(direction); // go to next room on that direction
        if (nextRoom == null) {
            System.out.println("那里没有门！");
        } else {
            currentRoom = nextRoom;
            showPrompt(); // ✅
        }
    }

    public void showPrompt() { // ✅
        System.out.println("你在" + currentRoom);
        System.out.print("出口有: ");
        // 利用多态，让room对象自己把握出口的关系
        // 做到解耦合
        System.out.println(currentRoom.getExitDescription());// ✅
        System.out.println();
    }

    public void play(){
        Scanner in = new Scanner(System.in);
        while (true) {
            String line = in.nextLine();
            String[] words = line.split(" ");
            Handler handler = handlers.get(words[0]);

            String value = "";
            if(words.length > 1) value = words[1];

            if(handler != null){
                // " "会不报错，不做任何事情 等待valid input
                // 又利用到了多态的特性，让对应的类型去handle而不是硬编码
                // 这需要改变数据在初始化的时候就行，框架内部代码不需要改变
                // 确保了代码的可扩展性和强壮性
                handler.doCmd(value);
                if(handler.isBye()){
                    break;
                }
            }
        }
        in.close();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.printWelcome();
        game.play();
        System.out.println("感谢您的光临。再见！");
    }

}
