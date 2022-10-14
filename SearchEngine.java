import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.LinkedList;

class Handler implements URLHandler {
    LinkedList<String> list = new LinkedList<String>();
    LinkedList<String> finalSearch = new LinkedList<String>();
    public String handleRequest(URI url) {
        if (url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");
            if(parameters[0].equals("s")) {
                for(int i = 0; i < list.size(); i++) {
                    if(list.get(i).contains(parameters[2]) == true) {
                        finalSearch.add(list.get(i));
                    }
                }
                if(finalSearch.size() <= 0) {
                    return("No matches in the search engine found");
                }
                else {
                    String finalList = finalSearch.toString();
                    String[] finalTest = finalSearch.toArray(new String[finalSearch.size()]);
                    System.out.println(finalList);
                    return ("Here are the following matches:");
                }
            }
        }

        else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                list.add(parameters[1]);
                String[] test = list.toArray(new String[list.size()]);
                return("String has been successfully entered into the search engine! This is the current search engine word bank" + Arrays.toString(test));
                }
            }   
        return ("welcome to cc's search engine!!! type /add?s=[enter any word here] after the url to add some terms to the search engine & type /search?s=[enter any word here] after the url to see if your words match up with the words inputted in the search engine!");  
    }
}


class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
