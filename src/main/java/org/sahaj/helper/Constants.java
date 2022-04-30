package org.sahaj.helper;

public enum Constants {
    PLAYER1("Rohit"),
    ROLLED("Rolled Value: {}"),
    CURPOS("Current Position: {}"),
    SNAKEBITE("Snake Bite at Position: {}"),
    LADDERFOUND("Ladder found at Position: {}");
    public final String value;

    private Constants(String value) {
        this.value = value;
    }

}
