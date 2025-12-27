package exceptions;

import items.Item;

public class InventoryFullException extends Exception {
    private final Item item;
    private final int currentSize;
    private final int maxSize;

    public InventoryFullException(Item item, int currentSize, int maxSize) {
        super(String.format("Инвентарь полон (%d/%d), невозможно добавить: %s",
                currentSize, maxSize, item.getName()));
        this.item = item;
        this.currentSize = currentSize;
        this.maxSize = maxSize;
    }

    @Override
    public String getMessage() {
        return String.format("Переполнение: сумка заполнена (%d/%d). невозможно добавить '%s'",
                currentSize, maxSize, item.getName());
    }

    public Item getItem() {
        return item;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public int getMaxSize() {
        return maxSize;
    }
}
