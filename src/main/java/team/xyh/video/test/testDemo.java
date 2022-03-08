package team.xyh.video.test;

public class testDemo {
    public static void main(String[] args){
        String rule = "^[0-9]+[0-9]+$";
        String test = "1123eq";
        System.out.println(test.matches(rule));
    }
}
