public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello world!!!");
        String a = "$ 12,5";
        System.out.println(a.replaceAll("[^0-9]", ""));
        double value = Double.parseDouble(a.replaceAll("[^\\d,]", "").replace(',', '.'));
        System.out.println(value);
    }

}
