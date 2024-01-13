package OOP.inheritance.using_inheritatnce;

/**
 * DVD is 子类-child of Item
 * inherit everything from farther-父类 -> Item
 * AND can add more things about itself
 * AND child子类可以change父类private fields if using father's function
 * AND child子类的attributes包含farther的copies of attributes
 * AND IF child子类's field with same name as 父类 then it is the one in child
 * AND IF executing in 父类 it is the one in 父类
 */
public class DVD extends Item{
    private String director;

    public DVD(String title, String director, int playingTime, String comment) {
        // use farther constructor ITEM()
        // inherit all attributes from ITEM
        super(title, playingTime, comment);
        this.director = director;
    }

    public DVD() {

    }

    public void print() {
        System.out.println("DVD" + ":" + title + ":" + director);
    }
}
