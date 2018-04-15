public class Main {

    private String var = "Ana";
    public static void main(String[] args) {
            Main obj = new Main();
            obj.Show();
    }
    private void Show()
    {
        var = 2;
        System.out.println("Hello world!" + var);
    }

}
