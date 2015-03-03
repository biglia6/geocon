package geocon.service;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class Init {

    public static void main(String[] args) {

        Client client = ElasticSearch.getClient();

        JSONParser parser = new JSONParser();

        System.out.println("ElasticSearch db Filler");
        System.out.println();

        Scanner sc = new Scanner(System.in);

        try {

            // TODO Da mettere in un metodo ------------------------------------
            System.out.println("Insert path of USERS list file ");
            String path = sc.nextLine();
            Object obj = parser.parse(new FileReader(path));
            JSONArray jsonObject = (JSONArray) obj;
            Iterator it = jsonObject.iterator();
            int numElementsInserted = 0;
            while (it.hasNext()) {
                Object next = it.next();
                IndexResponse risposta = client
                        .prepareIndex(Configuration.PRIMARY_INDEX_NAME,
                                Configuration.USERS_TYPE_NAME)
                        .setSource(next.toString()).execute().actionGet();
                System.out.println("ID: " + risposta.getId());
                numElementsInserted++;
            }
            System.out.println("Added " + numElementsInserted + " users");

            // TODO Da mettere in un metodo ------------------------------------

            System.out.println("Insert path of EVENTS list file ");
            path = sc.nextLine();
            obj = parser.parse(new FileReader(path));
            it = jsonObject.iterator();
            numElementsInserted = 0;
            while (it.hasNext()) {
                Object next = it.next();
                IndexResponse risposta = client
                        .prepareIndex(Configuration.PRIMARY_INDEX_NAME,
                                Configuration.EVENTS_TYPE_NAME)
                        .setSource(next.toString()).execute().actionGet();
                System.out.println("ID: " + risposta.getId());
                numElementsInserted++;
            }
            System.out.println("Added " + numElementsInserted + " events");

            // TODO Da mettere in un metodo ------------------------------------

            System.out.println("Insert path of RESOURCES list file ");
            path = sc.nextLine();
            obj = parser.parse(new FileReader(path));
            it = jsonObject.iterator();
            numElementsInserted = 0;
            while (it.hasNext()) {
                Object next = it.next();
                IndexResponse risposta = client
                        .prepareIndex(Configuration.PRIMARY_INDEX_NAME,
                                Configuration.RESOURCES_TYPE_NAME)
                        .setSource(next.toString()).execute().actionGet();
                System.out.println("ID: " + risposta.getId());
                numElementsInserted++;
            }
            System.out.println("Added " + numElementsInserted + " resources");

            // TODO Da mettere in un metodo ------------------------------------

            System.out.println("Insert path of PLACES list file ");
            path = sc.nextLine();
            obj = parser.parse(new FileReader(path));
            it = jsonObject.iterator();
            numElementsInserted = 0;
            while (it.hasNext()) {
                Object next = it.next();
                IndexResponse risposta = client
                        .prepareIndex(Configuration.PRIMARY_INDEX_NAME,
                                Configuration.PLACES_TYPE_NAME)
                        .setSource(next.toString()).execute().actionGet();
                System.out.println("ID: " + risposta.getId());
                numElementsInserted++;
            }
            System.out.println("Added " + numElementsInserted + " resources");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
            sc.close();
        }
    }
}
