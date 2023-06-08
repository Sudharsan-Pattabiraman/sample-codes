import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author spattabiraman 2022-09-22
 */
public class VendingMachine {

    private final Map<String, List<Item>> items = new HashMap<>();
    private BigDecimal totalChange = BigDecimal.ZERO;

    /**
     * Vend the provided item based on its code. Once the item is vended, it should be removed from
     * the machine's stock and the customer's change added to the machine
     *
     * @param code           the item code
     * @param providedChange the change the user provides to purchase the item
     * @return the vended item
     * @throws IllegalArgumentException if the item is out of stock
     * @throws IllegalArgumentException if providedChange is less than the cost of the item
     * @throws IllegalArgumentException if the vending machine has insufficient change to vend the
     *                                  item
     */
    public VendedItem vend(String code, int providedChange) {
        final Item item = getItem(code);

        if (item.cost > providedChange) {
            throw new IllegalArgumentException(
                String.format("provided change %d is less than the cost of the item", providedChange));
        }

        final int remainingChange = checkBalanceAndCalculateChange(providedChange, item);

        removeItem(code, item);
        addChange(providedChange - remainingChange);

        return new VendedItem(item, remainingChange);
    }

    private int checkBalanceAndCalculateChange(final int providedChange, final Item item) {
        final int remainingChange = providedChange - item.cost;
        if (totalChange.intValue() < remainingChange) {
            throw new IllegalArgumentException(
                " vending machine has insufficient change to vend the item");
        }
        return remainingChange;
    }

    /**
     * Get an item by its code. Throw an exception if not found
     *
     * @return the item
     * @throws IllegalArgumentException if the item is not found or is out of stock
     */
    public Item getItem(String code) {
        return items.getOrDefault(code, List.of()).stream()
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("out of stock"));
    }

    /**
     * Get the number of items with this code. Return 0 if the item is not available or is out of
     * stock
     *
     * @param code the item code
     * @return the number of items
     */
    public int getItemCount(String code) {
        return items.getOrDefault(code, List.of()).size();
    }

    /**
     * Add an item to the vending machine
     *
     * @param code the item code
     * @param item the item
     */
    public void addItem(String code, Item item) {
        items.merge(
            code,
            Collections.singletonList(item),
            (items1, items2) ->
                Stream.concat(items1.stream(), items2.stream()).collect(Collectors.toList()));
    }

    /**
     * Remove an item from the vending machine
     *
     * @param code the item code
     * @param item the item
     */
    public void removeItem(String code, Item item) {
        List<Item> itemsLocal = items.get(code);
        itemsLocal.remove(item);
        //items.get(code).remove(item);
    }

    /**
     * Add change to the vending machine
     *
     * @param change the change to add
     */
    public void addChange(int change) {
        totalChange = totalChange.add(new BigDecimal(change));
    }

    /**
     * Remove change from the vending machine
     *
     * @param change the change to remove
     */
    public void removeChange(int change) {
        totalChange = totalChange.subtract(new BigDecimal(change));
    }

    /**
     * Get the change in the vending machine
     *
     * @return the change in the vending machine
     */
    public int getChange() {
        return totalChange.intValue();
    }

    public static class VendedItem {
        Item item;
        int change;

        public VendedItem(Item item, int change) {
            this.item = item;
            this.change = change;
        }
    }
}

class Item {
    int cost;

    public Item(int cost) {
        this.cost = cost;
    }
}
