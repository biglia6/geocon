package geocon.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * <pre>
 * Semplice applicazione da linea di comando che interagisce con il servizio
 * SmartSearch.
 * 
 * Utilizzo: smartClient indirizzo comando parametro1 [parametro2]
 * 
 * indirizzo = l'hostname o l'indirizzo IP del server dove il servizio
 * SmartSearch è in ascolto.
 * 
 * Comandi:
 * 
 * [register|rg] = Registra uno smart object
 * ............... parametro1 = nome del file JSON
 * ............... parametro2 = indirizzo IP dello smart object
 * 
 * discover|ds = Effettua una ricerca
 * ............. parametro1 = query in formato Lucene
 * 
 * getJSON|js = Restituisce i metadati di uno smart object
 * ............ parametro1 = id dello smart object
 * 
 * resolve|ip = Restituisce l'indirizzo IP dello smart object
 * ............ parametro1 = id dello smart object
 * 
 * updateIP|uip = Aggiorna l'indirizzo IP di uno smart object
 * .............. parametro1 = id dello smart object
 * .............. parametro2 = nuovo indirizzo IP dello smart object
 * 
 * remove|rm = Rimuove uno smart object
 * ........... parametro1 = id dello smart object
 * </pre>
 * 
 * @author Marco Lackovic <mlackovic@dimes.unical.it>
 * @version 1.0 3/may/2013
 */
public class GeoconClient {
    private static final String helpFileName = "readme.txt";

    private GeoconClient() {

    }

    /*
     * Restituisce le istruzioni sull'utilizzo dell'applicazione, caricate da un
     * file di testo posizionato nella stessa cartella di dove si trova questa
     * classe.
     */
    private static String getUsage() throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            InputStream is = GeoconClient.class
                    .getResourceAsStream(helpFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            if (args.length < 3) {
                System.out
                        .println("\nERROR: The second parameter is missing.\n");

                System.out.println(getUsage());

                return;
            }
            String server = args[0];
            System.out.println("Connecting...");
            SearchClient s = new SearchClient(server);
            String method = args[1].replaceAll("[^A-Za-z0-9 ]", "");
            String parameter1 = args[2];
            String parameter2;
            System.out.println("Invoking " + method + "(" + parameter1
                    + ") on " + server + "...");
            switch (method) {
            case "register":
            case "rg":
                if (args.length < 4) {
                    System.out
                            .println("\nERROR: The second parameter is missing.\n");
                    System.out.println(getUsage());
                    return;
                }
                parameter1 = FileUtils.readFileToString(new File(parameter1));
                parameter2 = args[3];
                print(s.register(parameter1, parameter2));
                break;
            case "discover":
            case "ds":
                print(s.discover(parameter1));
                break;
            case "getJSON":
            case "js":
                print(s.getJSON(parameter1));
                break;
            case "resolve":
            case "ip":
                print(s.resolve(parameter1));
                break;
            case "updateIP":
            case "uip":
                if (args.length < 4) {
                    System.out
                            .println("\nERROR: The second parameter is missing.\n");
                    System.out.println(getUsage());
                    return;
                }
                parameter2 = args[3];
                print(s.updateIP(parameter1, parameter2));
                break;
            case "remove":
            case "rm":
                print(s.remove(parameter1));
                break;
            case "test":
                print(s.test(parameter1));
                break;
            default:
                print("\nERROR: The specified command '" + method
                        + "' is not valid.\n");
                System.out.println(getUsage());
                break;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\nStack trace:");
            e.printStackTrace();
        }
    }

    private static void print(String s) {
        if (s.startsWith(Consts.ERROR)) {
            System.err.println(s);
        } else {
            System.out.println("Result:\n" + s);
        }
    }

}
