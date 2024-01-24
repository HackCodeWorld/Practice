package java_basic.exception;

class OpenException extends Throwable {
    // ...
}

class CloseException extends OpenException {
    // ...
}

class NewException extends Exception {
    // ...
}

class NewClass extends ArrayIdx{
    @Override
    public void f() throws OpenException{}
    /**
     * 覆盖父类的某个Exception不能不是原来的异常类型或者不是其子类
     * 像OpenException和child异常CloseException都是OK的
     */
//    public void f() throws NewException{}
}

public class ArrayIdx {
    static int open() {
        return -1;
    }

    static void readFile() throws OpenException, CloseException {
        if (open() == -1) {
            throw new CloseException();
        }
    }

    public void f() throws OpenException{}

    public static void main(String[] args) {
        try {
            readFile();
        } catch (CloseException e){ // caught related exception
            System.out.println("close"); // 如果
        }
        catch (OpenException e) {
            System.out.println("open");
        }

        try {
            readFile();
        } catch (OpenException e){
            System.out.println("close"); // 如果
        }
        // Exception 'java_basic.exception.CloseException' has already been caught
        // Close is child of Open, so caught OpenException就是包含去caught child
//        catch (CloseException e) { // child exception CloseException can be caught by father exception OpenException
//            System.out.println("open");
//        }

    }
}
