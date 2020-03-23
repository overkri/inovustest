package com.example.demo.Service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service
public class MainService {

    private static final HashMap<Integer, Character> symbolMap = new HashMap<>();

    static {
        symbolMap.put(1, 'А');
        symbolMap.put(2, 'В');
        symbolMap.put(3, 'Е');
        symbolMap.put(4, 'К');
        symbolMap.put(5, 'М');
        symbolMap.put(6, 'Н');
        symbolMap.put(7, 'О');
        symbolMap.put(8, 'Р');
        symbolMap.put(9, 'С');
        symbolMap.put(10, 'Т');
        symbolMap.put(11, 'У');
        symbolMap.put(12, 'Х');
    }

    Random random = new Random();

    StringBuilder stringBuilder = new StringBuilder();

    private static final String MESSAGE = "First generate number, so you can generate next";

    private int firstCharId;

    private int secondCharId;

    private int thirdCharId;

    private String number;

    public void generate() {
        firstCharId = random.nextInt(12) + 1;
        secondCharId = random.nextInt(12) + 1;
        thirdCharId = random.nextInt(12) + 1;
        number = "" + random.nextInt(10) + random.nextInt(10) + random.nextInt(10);
    }

    public String generateCarNumber() {
        char firstChar = symbolMap.get(firstCharId);
        char secondChar = symbolMap.get(secondCharId);
        char thirdChar = symbolMap.get(thirdCharId);
        stringBuilder
                .append(firstChar)
                .append(number)
                .append(secondChar)
                .append(thirdChar)
                .append(" 116RUS ");
        return stringBuilder.toString();
    }

    public String nextNumber() {
        try {
            if (Integer.parseInt(number) == 999) {
                int smallest = Math.min(Math.min(firstCharId, secondCharId), thirdCharId);
                incrementCharacter(smallest);
                setNumber("000");
            } else {
                setNumber(incrementNumber());
            }
            return generateCarNumber();

        } catch (NumberFormatException e) {
            return MESSAGE;
        }
    }


    private void incrementCharacter(int smallest) {
        if (smallest == firstCharId) {
            setFirstCharId(firstCharId + 1);
            return;
        }
        if (smallest == secondCharId) {
            setSecondCharId(secondCharId + 1);
            return;
        }
        setThirdCharId(thirdCharId + 1);
    }

    private String incrementNumber() {
        int newNumber = Integer.parseInt(number) + 1;
        String prefix = newNumber < 10 ? "00" : "0";
        return newNumber < 100 ? prefix + newNumber : String.valueOf(newNumber);
    }

    private void setFirstCharId(int firstCharId) {
        this.firstCharId = firstCharId;
    }

    private void setSecondCharId(int secondCharId) {
        this.secondCharId = secondCharId;
    }

    private void setThirdCharId(int thirdCharId) {
        this.thirdCharId = thirdCharId;
    }

    private void setNumber(String number) {
        this.number = number;
    }
}
