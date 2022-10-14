import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;
import java.util.ArrayList;

class Handler implements URLHandler {
    LinkedList<String> list = new LinkedList<String>();
    LinkedList<String> finalSearch = new LinkedList<String>();
    public String handleRequest(URI url) {
        if (url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");
            if(parameters[0].equals("s")) {
                String[] newList = list.toArray(new String[list.size()]);
                for(int i = 0; i < newList.length; i++) {
                    if(newList[i].contains(parameters[2]) == true) {
                        finalSearch.add(newList[i]);
                    }
                }
                if(finalSearch.size() == 0) {
                    System.out.println("No matches in the search engine found");
                }
                else {
                    String finalList = finalSearch.toString();
                    return String.format("Here are the following matches:", finalList);
                }
            }
        }

        else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                list.add(parameters[1]);
                System.out.println("String has been successfully entered into the search engine!");
                }
            }
        return "testing";     
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
