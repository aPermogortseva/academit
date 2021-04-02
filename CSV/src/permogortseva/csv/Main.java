package permogortseva.csv;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileInputStream("input.csv"));
             PrintWriter writer = new PrintWriter("output.html")) {

            writer.write("<table>\n");

            while (scanner.hasNextLine()) {
                writer.write("<tr>\n");
                writer.write("<td>\n");

                StringBuilder currentRow = new StringBuilder(scanner.nextLine());

                int doubleQuotesCount = 0;

                for (int i = 1; i < currentRow.length(); i++) {
                    char currentCharacter = currentRow.charAt(i);
                    char previousCharacter = currentRow.charAt(i - 1);

                    if (previousCharacter == '"') {
                        doubleQuotesCount++;
                    }

                    if (previousCharacter != '"' && previousCharacter != ',') {
                        writer.write(previousCharacter);
                    } else if (currentCharacter == (char) 0) {
                        writer.write("<br/>");
                    } else if (previousCharacter == ',' && doubleQuotesCount % 2 == 0) {
                        writer.write("\n</td>\n");
                        writer.write("<td>\n");

                        if (currentCharacter == '"') {
                            doubleQuotesCount++;
                        }

                        i++;
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
                        writer.write("\n</td>");
                        writer.write("\n<td>");
                    }
                    if (i == currentRow.length() - 1 && doubleQuotesCount % 2 != 0 && scanner.hasNextLine()) {
                        writer.write("<br/>\n");
                        currentRow.append(scanner.nextLine());
                        i++;
                    }
                }

                writer.write("\n</td>");
                writer.write("\n</tr>\n");
            }

            writer.write("</table>");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
