package org.example;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String RESET = "\u001B[0m";
    static String RED = "\u001B[31m";
    static String BLUE = "\u001B[34m";
    static String GREEN = "\u001B[32m";
    static Random random = new Random();
    static char[] numbers = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static void main(String[] args) {
        System.out.println("Welcome to X and O game !");

        while (true) {
            System.out.println("\nSetting:");
            System.out.println("1 -> Play with friend");
            System.out.println("2 -> Play with robot");
            System.out.println("0 -> quit");
            switch (scanner.nextLine()) {
                case "0" -> {
                    return;
                }
                case "1" -> play_friend();
                case "2" -> play_robot();
                default -> System.out.println("O'zing ushnaqasan!");
            }
        }


    }

    private static void play_friend() {
        fixArr();
        List<Integer> list = new java.util.ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        cons();
        while (true) {
            if (list.isEmpty()) break;
            Integer num = getInteger(list);
            numbers[num - 1] = 'X';
            cons();
            list.remove(num);
            if (check()) {
                System.out.println("\n" + RED + "X gamer Win" + RESET);
                break;
            }
            if (list.isEmpty()) {
                System.out.println("\n" + GREEN + "Durrang" + RESET);
                break;
            }
            num = getInteger(list);
            numbers[num - 1] = 'O';
            cons();
            list.remove(num);
            if (check()) {
                System.out.println("\n" + BLUE + "O gamer Win" + RESET);
                break;
            }
        }


    }

    private static Integer getInteger(List<Integer> list) {
        Integer num = null;
        System.out.println("1 dan 9 gacha bo'sh joy sonini kiriting : ");
        do {
            try {
                num = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.println("Iltimos raqam yozing !!!!");
            }
            if (num != null && num > 0 && num < 10 && !list.contains(num)) {
                num = null;
                System.err.println("Bo'sh joyni tanlang !");
            }
        } while (num == null);
        return num;
    }


    private static void play_robot() {
        fixArr();
        Character hod = null;
        do {
            System.out.println("""
                    \nChoose:
                    1.X
                    2.O
                    3.quit
                    """);
            String s = scanner.nextLine();
            if (Objects.equals(s, "3")) return;
            if (s.equals("1") || s.equalsIgnoreCase("x")) {
                hod = 'X';
            } else if (s.equals("2") || s.equals("0") || s.equalsIgnoreCase("o")) {
                hod = 'O';
            }
            if (hod == null) System.out.println("Wrong!");
        } while (hod == null);
        char robotHod = 'O';
        if (hod == 'O') robotHod = 'X';
        List<Integer> list = new java.util.ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        if (robotHod == 'X') {
            while (true) {
                Integer robotNum = random.nextInt(1, 10);
                if (list.contains(robotNum)) {
                    numbers[robotNum - 1] = robotHod;
                    list.remove(robotNum);
                    break;
                }
            }
        }
        while (true) {
            cons();
            if (list.isEmpty()) break;
            Integer num = getInteger(list);
            numbers[num - 1] = hod;
            list.remove(num);
            if (check()) {
                cons();
                System.out.println("\n" + BLUE + "Win" + RESET);
                break;
            }
            if (list.isEmpty()) {
                cons();
                break;
            }
            while (true) {
                Integer robotNum = random.nextInt(1, 10);
                if (list.contains(robotNum)) {
                    numbers[robotNum - 1] = robotHod;
                    list.remove(robotNum);
                    break;
                }
            }
            if (check()) {
                cons();
                System.out.println("\n" + RED + "Lose" + RESET);
                break;
            }
        }
        if (list.isEmpty() && !check()) System.out.println("\n" + GREEN + "Durrang" + RESET);

    }

    private static void fixArr() {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (char) (i + 1 + '0');
        }
    }

    private static boolean check() {
        for (int i = 0; i < 3; i++) {
            if (numbers[3 * i] == numbers[1 + (3 * i)] && numbers[1 + (3 * i)] == numbers[2 + (3 * i)]) return true;
            if (numbers[i] == numbers[3 + i] && numbers[3 + i] == numbers[6 + i]) return true;
        }
        if (numbers[0] == numbers[4] && numbers[4] == numbers[8]) return true;
        return (numbers[2] == numbers[4] && numbers[4] == numbers[6]);
    }

    private static void cons() {
        System.out.println(" _____________");
        for (int i = 0; i < 9; i++) {
            System.out.print(" | ");
            if (numbers[i] == 'X') System.out.print(RED + numbers[i] + RESET);
            else if (numbers[i] == 'O') System.out.print(BLUE + numbers[i] + RESET);
            else System.out.print(GREEN + numbers[i] + RESET);
            if ((i + 1) % 3 == 0) {
                System.out.println(" |");
                System.out.println(" |---|---|---|");
            }
        }

    }
    // TODO: 23.12.2023  with robotga easy midium and hard qo'shib shunga qarab logika qilish kere
}