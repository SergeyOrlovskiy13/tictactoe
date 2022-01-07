package com.company;

import java.util.*;

public class Main {

    static ArrayList<Integer> playerposition = new ArrayList<>();
    static ArrayList<Integer> cpuposition = new ArrayList<>();
    public static void main(String[] args) {
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
        };
        System.out.println("Старт игры");
        draw(gameBoard);
        while (true) {
            System.out.println(" поставьте крестик ");
            Scanner scanner = new Scanner(System.in);
            int pos = scanner.nextInt();
            while (playerposition.contains(pos) || cpuposition.contains(pos)) {
                System.out.println(" Клетка уже занята, выберите другую клетку ");
                pos = scanner.nextInt();
            }
            placePiece(gameBoard, pos, "player");
            Random random = new Random();
            int posCpu = random.nextInt(9) + 1;
            while (cpuposition.contains(posCpu) || playerposition.contains(posCpu)) {
                posCpu = random.nextInt(9) + 1;
            }
            placePiece(gameBoard, posCpu, "cpu");
            draw(gameBoard);
            String res = chekWinner();
            if ( res.length()>0){
                System.out.println(res);
                break;
            }

        }
    }





    public static void draw(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user) {
        char symbol = ' ';
        if (user.equals("player")){
            symbol = 'X';
            playerposition.add(pos);
        }else  if ( user.equals("cpu")){
            symbol= '0';
            cpuposition.add(pos);
        }

        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }
    public static String chekWinner(){
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(3,6,9);
        List midCol = Arrays.asList(2,5,8);
        List rightRow = Arrays.asList(1,4,6);
        List cross1 = Arrays.asList(3,5,7);
        List cross2 = Arrays.asList(1,5,9);

        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightRow);
        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning){
            if (playerposition.containsAll(l)){
                return "Вы победили";
            }
            if (cpuposition.containsAll(l)){
                return "Компьютер победил";
            }
            else if (playerposition.size()+cpuposition.size()==9){
                return "Ничья";
            }
        }
        return "";
    }
}

