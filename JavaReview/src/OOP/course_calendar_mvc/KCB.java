package OOP.course_calendar_mvc;


import javax.swing.*;

/**
 * MVC Design
 * View and Control merged to be JTable
 */
public class KCB {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        // j table obj 看见的东西 View
        JTable table = new JTable(new KCBData()); // KCBData implements TableModel - Model/Data
        // Control：用户在界面做了什么动作
        // Model给了接口可以被外界修改数据
        // View在被notified去整个数据展示出来, 不去做什么复杂逻辑 就淡出展示数据 至于数据怎么改的不关心
        // 注意：Control & View have no relation
        JScrollPane pane = new JScrollPane(table);
        frame.add(pane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
