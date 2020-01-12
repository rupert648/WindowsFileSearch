import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileSearcher {

    private static BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Incorrect arguments: java FileSearcher <search root path>");
            System.exit(1);
        }

        String searchRoot = args[0];

        String searchTerm = "";

        try {
            System.out.println("Enter Search Term: ");
            searchTerm = sysIn.readLine();

            FileBrowser searcher = new FileBrowser(searchTerm, searchRoot);

            ArrayList<String> results = searcher.search();

            System.out.println(results.size() + " result"+ (results.size() == 1 ? "": "s") + " for search term \""+searchTerm+"\"");
            if (results.size() > 1 ) {
                System.out.println("How many results would you like to print out?: ");
                int amount;
                while (true) {
                    try {
                        amount = Integer.parseInt(sysIn.readLine());

                        if (amount <= results.size() && amount >= 0) {
                            break;
                        }

                        throw new NumberOutOfRangeException();

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number, please try again");
                    } catch (NumberOutOfRangeException e) {
                        System.out.println("Number is not in range, please try again");
                    }
                }

                for (int i = 0; i < amount; i++) {
                    System.out.println(results.get(i));
                }

            } else if (results.size() == 1) {
                System.out.println(results.get(0));
            }

        } catch (IOException e) {
            System.out.println("Problem with the input:\n");
            e.printStackTrace();
            System.out.println("::::::::::::::::::::::::::::");
        }



    }
}