package stream;
import java.io.IOException;

public class StdInNout {
    public static void main(String[] args) {
        System.out.println("hello world");
        //Scanner scanner = new Scanner(System.in); // public final static InputStream in = null;
        //int returnNegOneMeansEnd = System.in.read();// read byte(s) from inputStream to a container

        byte[] buffer = new byte[1024];
        try {
            int len = System.in.read(buffer); // return how many things I read
            String s = new String(buffer, 0, len);
            System.out.println("读到了：" + len + "字节");
            System.out.println(s);
            System.out.println("s的长度是：" + s.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
