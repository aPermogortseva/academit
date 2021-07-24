package permogortseva.csv;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileInputStream(args[0]));
             PrintWriter writer = new PrintWriter(args[1])) {

            writer.write("<!DOCTYPE html>" + System.lineSeparator() +
                    "<html>" + System.lineSeparator() +
                    "   <head>" + System.lineSeparator() +
                    "      <meta charset=\"utf-8\" />" + System.lineSeparator() +
                    "   </head>" + System.lineSeparator() +
                    "   <body>" + System.lineSeparator());

            writer.write("<table>" + System.lineSeparator());

            while (scanner.hasNextLine()) {
                writer.write("<tr>" + System.lineSeparator());
                writer.write("<td>" + System.lineSeparator());

                StringBuilder currentRow = new StringBuilder(scanner.nextLine());
                while (currentRow.length() == 0 && scanner.hasNextLine()) {
                    currentRow = new StringBuilder(scanner.nextLine());
                }

                int doubleQuotesCount = 0;

                for (int i = 1; i < currentRow.length(); i++) {
                    char currentCharacter = currentRow.charAt(i);
                    char previousCharacter = currentRow.charAt(i - 1);

                    if (previousCharacter == '"') {
                        doubleQuotesCount++;
                    }

                    if (previousCharacter != '"' && previousCharacter != ',') {
                        if (previousCharacter == '<') {
                            writer.write("&lt");
                        } else if (previousCharacter == '>') {
                            writer.write("&gt");
                        } else if (previousCharacter == '&') {
                            writer.write("&amp");
                        } else {
                            writer.write(previousCharacter);
                        }
                    } else if (currentCharacter == (char) 0) {
                        writer.write("<br/>");
                    } else if (previousCharacter == ',' && doubleQuotesCount % 2 == 0) {
                        writer.write(System.lineSeparator() + "</td>" + System.lineSeparator());
                        writer.write("<td>" + System.lineSeparator());
                    } else if (previousCharacter == '"' && currentCharacter == '"' && doubleQuotesCount % 2 != 0) {
                        continue;
                    } else if (previousCharacter == '"' && currentCharacter == '"') {
                        writer.write(previousCharacter);

                        doubleQuotesCount++;

                        i++;
                    } else if (doubleQuotesCount % 2 != 0 && previousCharacter == ',') {
                        writer.write(previousCharacter);
                    }

                    if (i == currentRow.length() - 1 && currentRow.charAt(i) != ',' && currentRow.charAt(i) != '"') {
                        writer.write(currentRow.charAt(i));
                    } else if (i == currentRow.length() - 1 && currentRow.charAt(i) == ',') {
                        writer.write(System.lineSeparator() + "</td>");
                        writer.write(System.lineSeparator() + "<td>");
                    } else if (i == currentRow.length() - 1 && currentRow.charAt(i) == '"' && doubleQuotesCount % 2 != 0) {
                        doubleQuotesCount++;
                    }

                    if (i == currentRow.length() - 1 && doubleQuotesCount % 2 != 0 && scanner.hasNextLine()) {
                        writer.write("<br/>" + System.lineSeparator());
                        currentRow.append(scanner.nextLine());
                        i++;
                    }
                }

                writer.write(System.lineSeparator() + "</td>");
                writer.write(System.lineSeparator() + "</tr>" + System.lineSeparator());
            }

            writer.write("</table>" + System.lineSeparator());

            writer.write(" </body>" + System.lineSeparator() +
                    "</html>");
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + args[0] + " не найден.");
        }
    }
}
