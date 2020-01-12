import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileBrowser {

    private ArrayList<String> results = new ArrayList<>();
    private Pattern search;
    private String rootPath;

    public FileBrowser(String searchTerm, String rootPath) {
        search = Pattern.compile(searchTerm, Pattern.CASE_INSENSITIVE);
        this.rootPath = rootPath;
    }



    public ArrayList<String> search() throws IOException {
        searchRecursive(rootPath);

        return results;
    }

    public void searchRecursive(String path) throws IOException {

        File dir = new File(path);
        File[] directoryListing = dir.listFiles();

        if (directoryListing != null) {
            for (File child: directoryListing) {
                Matcher matcher = search.matcher(child.getName());

                if (matcher.find()) {
                    results.add(child.getCanonicalPath());
                }
                if (child.isDirectory()) {

                    searchRecursive(child.toString());

                }
            }
        }

    }
}



