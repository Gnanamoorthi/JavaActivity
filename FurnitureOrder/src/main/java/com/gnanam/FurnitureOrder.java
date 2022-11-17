package com.gnanam;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class FurnitureOrder implements FurnitureOrderInterface {
    /**
     * TODO: Create a map of Furniture items to order quantities
     */
    private HashMap<Furniture,Integer> orderedFurniture = new HashMap<>();

    /**
     * Initialize a new mapping of Furniture types to order quantities.
     */
    FurnitureOrder() {
        super();
    }

    public void addToOrder(final Furniture type, final int furnitureCount) {
        if(orderedFurniture.containsKey(type.label())) {
            Integer existVal = orderedFurniture.get(type);
            orderedFurniture.replace(type, (existVal + furnitureCount) );
        } else {
        orderedFurniture.put(type, furnitureCount);
        }
        System.out.println("Ordered for {} " + type+ " in {}" + orderedFurniture);
    }

    public HashMap<Furniture, Integer> getOrderedFurniture() {
        return orderedFurniture;
    }
    public float getTotalOrderCost() {
        AtomicReference<Float> totalCost = new AtomicReference<>(0.0f);
        if(orderedFurniture.isEmpty()) return totalCost.get();

        orderedFurniture.forEach((type,count) ->
                        totalCost.set(totalCost.get() + getTypeCost(type))
        );

        return totalCost.get();
    }

    public int getTypeCount(Furniture type) {
        return orderedFurniture.getOrDefault(type, 0);
    }

    public float getTypeCost(Furniture type) {
        return (type.cost() * getTypeCount(type));
    }

    public int getTotalOrderQuantity() {
        int totalOrder = 0;
        for(int c: orderedFurniture.values()) {
            totalOrder += c;
        }
        return totalOrder;
    }
}
