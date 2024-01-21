package OOP.interface_example.cell;

import java.awt.*;
import java.util.HashMap;

public interface ICell extends ICell2 {
    boolean alive = false;

    void die();

    @Override
    void reborn();

    boolean isAlive();

    void draw(Graphics g, int x, int y, int size);
}

interface ICell2 {
    boolean alive = false;

    void die();

    void reborn();
}


class A extends HashMap implements ICell {
    @Override
    public void die() {

    }

    @Override
    public void reborn() {

    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public void draw(Graphics g, int x, int y, int size) {

    }
}


class K {
    public static void main(String[] args) {
        A a = new A();
        a.put(1,2);
    }
}