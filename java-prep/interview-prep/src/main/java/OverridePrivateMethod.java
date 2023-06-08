/**
 * @author spattabiraman 2021-05-07
 */
public class OverridePrivateMethod {

    private void methodToBeOverridden() {
        System.out.println("Outer class method");
    }

    class Subclass extends OverridePrivateMethod {

        private void methodToBeOverridden() {
            System.out.println("Subclass method");
        }
    }

    public static void main(String[] args) {


    }
}
