package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        int rows, seatsPerRow, seatsTotal, incomeTotal, rowReseved,
                seatReserved, frontRows, backRows, ticketPrice, input,
                incomeCurrent=0, numberTicketsPurchased=0;
        Scanner scanner = new Scanner(System.in);

        // start
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatsPerRow = scanner.nextInt();
        // cinema ausfüllen
        char[][] cinema = new char[rows][seatsPerRow];
        // Das Array mit "S" füllen
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                cinema[i][j] = 'S';
            }
        }
        // calculate income
        seatsTotal = rows * seatsPerRow;
        if (seatsTotal <= 60) {
            frontRows = rows;
            backRows = rows;
            incomeTotal = seatsTotal * 10;
        } else {
            frontRows = rows / 2;
            backRows = rows - frontRows;
            // Front rows at $10, back rows at $8
            incomeTotal = (frontRows * seatsPerRow * 10) + (backRows * seatsPerRow * 8);
        }

        do {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            input = scanner.nextInt();
            if (input == 1) {
                print(cinema);
            } else if (input == 2) {
                /* TICKET KAUFEN */
                // Reservierung
                boolean rightSeatChoice;
                do {
                    System.out.println("Enter a row number:");
                    rowReseved = scanner.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    seatReserved = scanner.nextInt();

                    // Eingabe validieren
                    if (rowReseved < 1 || rowReseved > cinema.length ||
                            seatReserved < 1 || seatReserved > cinema[0].length) {
                        System.out.println("Wrong input! Please enter valid row and seat numbers.");
                        rightSeatChoice = false; // Ungültige Eingabe
                    } else if (cinema[rowReseved - 1][seatReserved - 1] == 'B') {
                        System.out.println("That ticket has already been purchased! Choose another seat.");
                        rightSeatChoice = false; // Sitzplatz schon reserviert
                    } else {
                        rightSeatChoice = true; // Gültige Eingabe und Sitzplatz ist frei
                    }
                } while (!rightSeatChoice);


                cinema[rowReseved - 1][seatReserved - 1] = 'B';
                numberTicketsPurchased++;

                // calculate ticket price
                if (backRows == frontRows) {
                    ticketPrice = 10;
                } else {
                    if (rowReseved <= frontRows) {
                        ticketPrice = 10;
                    } else {
                        ticketPrice = 8;
                    }
                }
                incomeCurrent += ticketPrice;
                System.out.println("Ticket price:");
                System.out.println("$" + ticketPrice);
            } else if (input == 3){
                /* STATISTICS */
                System.out.println("Number of purchased tickets: " + numberTicketsPurchased);
                System.out.println("Percentage: " + String.format("%.2f", (numberTicketsPurchased * 100.0) / seatsTotal) + "%");
                System.out.println("Current income: $" + incomeCurrent);
                System.out.println("Total income: $" + incomeTotal);
            }
        } while (input!=0);

    }
    public static void print(char[][] cinema){
        System.out.println("Cinema:");
        // Kopfzeile (Spaltennummerierung)
        System.out.print(" ");
        for (int i = 1; i <= cinema[0].length; i++){
            System.out.print(" " + i);
        }
        System.out.println();
        // Zeilen ausgeben mit Nummerierung
        int rowNum = 1; // Startnummer für die Zeilen
        for (char[] row : cinema) {
            System.out.print(rowNum + " "); // Zeilennummer ausgeben
            rowNum++; // Nächste Zeilennummer
            for (char value : row) {
                System.out.print(value + " "); // Werte der aktuellen Zeile ausgeben
            }
            System.out.println(); // Neue Zeile nach der aktuellen Zeile
        }
    }
}