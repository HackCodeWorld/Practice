package OOP.interface_example.cellmachine;

import javax.swing.*;

import OOP.interface_example.cell.Cell;
import OOP.interface_example.field.Field;
import OOP.interface_example.field.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CellMachine {

    class InnerClass {
        // cannot 调用 step()
        // CAN invoke all resource if not in function just inside a class
        int a  = all_resource();
    }

    int all_resource() {
        return 1;
    }

    static void step(){

    }

    public static void main(String[] args) {
        int a;

        Field field = new Field(30, 30);
        // prepare data
        for (int row = 0; row < field.getHeight(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                // initialized cell but not definitively lived
                field.place(row, col, new Cell());
            }
        }
        for (int row = 0; row < field.getHeight(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                Cell cell = field.get(row, col);
                // 1 / 5 cell reborn
                if (Math.random() < 0.2) {
                    cell.reborn();
                }
            }
        }

        View view = new View(field);
        JFrame frame = new JFrame(); // GUI window
        // x red button can close the program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // some settings
        JButton btnStep = new JButton("Stop");

        frame.setResizable(false);
        frame.setTitle("Cells");
        frame.add(view);

        // swing
        // dynamically to suit the component in the container
        // probably not beautiful but practical
        frame.add(btnStep, BorderLayout.SOUTH);

        // add listener for the button signal
        // a member can access other members' stuff
        // anonymous inner class new ActionListener() { ... }
        btnStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("按下啦");
                // Variable 'view' is accessed from within inner class, needs to be final or effectively final
                // : view = new View(field);
				// 可以调用 step()
                step(); // 函数内的内部类需要call static的外部函数（被实例共享的）才不违法
                // 因为我们整一个都在main函数中，所以只能访问final变量
                // a =5;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        frame.pack();
        frame.setVisible(true);

        for (int i = 0; i < 1000; i++) {
            for (int row = 0; row < field.getHeight(); row++) {
                for (int col = 0; col < field.getWidth(); col++) {
                    Cell cell = field.get(row, col);
                    Cell[] neighbour = field.getNeighbour(row, col);
                    int numOfLive = 0;
                    for (Cell c : neighbour) {
                        if (c.isAlive()) {
                            numOfLive++;
                        }
                    }
//					System.out.print("["+row+"]["+col+"]:");
//					System.out.print(cell.isAlive()?"live":"dead");
//					System.out.print(":"+numOfLive+"-->");
                    if (cell.isAlive()) {
                        if (numOfLive < 2 || numOfLive > 3) {
                            cell.die();
//							System.out.print("die");
                        }
                    } else if (numOfLive == 3) {
                        cell.reborn();
//						System.out.print("reborn");
                    }
//					System.out.println();
                }
            }
//			System.out.println("UPDATE");
            frame.repaint();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
