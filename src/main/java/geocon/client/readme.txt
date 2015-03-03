Semplice applicazione da linea di comando che interagisce con il servizio
SmartSearch.

Utilizzo: smartClient indirizzo comando parametro1 [parametro2]

indirizzo = l'hostname o l'indirizzo IP del server dove il servizio
SmartSearch è in ascolto.

Comandi:

[register|rg] = Registra uno smart object
............... parametro1 = nome del file JSON
............... parametro2 = indirizzo IP dello smart object

discover|ds = Effettua una ricerca
............. parametro1 = query in formato Lucene

getJSON|js = Restituisce i metadati di uno smart object
............ parametro1 = id dello smart object

resolve|ip = Restituisce l'indirizzo IP dello smart object
............ parametro1 = id dello smart object

updateIP|uip = Aggiorna l'indirizzo IP di uno smart object
.............. parametro1 = id dello smart object
.............. parametro2 = nuovo indirizzo IP dello smart object

remove|rm = Rimuove uno smart object
........... parametro1 = id dello smart object
