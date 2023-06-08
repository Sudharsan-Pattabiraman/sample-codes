import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;

/**
 * @author spattabiraman 2022-09-22
 */
class VendingMachineTest {

    public static void main(String[] args) {
        JUnitCore.main("VendingMachineTest");
    }

    @Test
    public void testVendingMachine() {

        VendingMachine machine = new VendingMachine();
        Item cookie = new Item(100);
        Item candyBar = new Item(150);
        machine.addItem("1A", cookie);
        machine.addItem("1A", cookie);
        machine.addItem("2B", candyBar);
        machine.addChange(200);

        Assert.assertEquals(2, machine.getItemCount("1A"));
        Assert.assertEquals(100, machine.getItem("1A").cost);

        VendingMachine.VendedItem vended = machine.vend("1A", 125);
        Assert.assertEquals(cookie, vended.item);
        Assert.assertEquals(25, vended.change);

        // show that the item stock is decremented
        Assert.assertEquals(1, machine.getItemCount("1A"));
        // machine started with 200, the user added 125, and the machine vended 25, so we should have
        // 200 + 125 - 25 = 300
        Assert.assertEquals(300, machine.getChange());

        Assert.assertEquals(0, machine.getItemCount("3C"));
        try {
            machine.getItem("3C");
            Assert.fail("Item is out of stock. Machine should throw exception, but it didn't");
        } catch (IllegalArgumentException ex) {
        }

        try {
            machine.vend("2B", 100);
            Assert.fail(
                "Insufficient provided change to purchase item. Machine should throw exception, but it didn't");
        } catch (IllegalArgumentException ex) {
        }

        try {
            machine.vend("2B", 1000);
            Assert.fail(
                "Machine has insufficient change to vend item. Machine should throw exception, but it didn't");
        } catch (IllegalArgumentException ex) {
        }
    }
}