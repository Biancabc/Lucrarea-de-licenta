package problem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {

    public static int[] numbersExtractedFromFile(String filePath){
        return numbersExtracted(parse(filePath));
    }

    public static int[] numbersExtracted(String data){
        Pattern p = Pattern.compile("\\d+");
        String[] lines = data.split("\\r?\\n");
        String number = "";
        for (String line : lines) {
            Matcher m = p.matcher(line);
            while (m.find()) {
                number += m.group();
                number += ",";
            }
        }

        String[] no = number.split(",");
        int[] numbersData = new int[no.length];

        for (int k = 0; k < no.length; k++) {
            numbersData[k] = Integer.parseInt(no[k]);
        }

        return numbersData;
    }

    public static String parse(String filePath){
        String data = "";
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data += (myReader.nextLine());
                data += "\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return data;
    }
}
