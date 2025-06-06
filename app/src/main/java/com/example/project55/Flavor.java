package com.example.project55;

/**
 * Enum for beverage flavors.
 */
public enum Flavor {
    COLA, TEA, JUICE, STRAWBERRY_LEMONADE, ORANGE, PEACH, MANGO, RASPBERRY,
    CHERRY, LEMONADE, LIME, MANGO_LEMONADE, PEACH_TEA, RASPBERRY_TEA, PINEAPPLE;

    /**
     * Converts enum to user-friendly string.
     */
    public String toDisplayString() {
        String[] words = this.name().toLowerCase().replace("_", " ").split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1))
                    .append(" ");
        }
        return sb.toString().trim();
    }

}
