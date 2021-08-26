Installazione del server:
1)Installare postgresql sul sistema(https://www.postgresql.org/)
2)Creare il database "postgres" se non fosse già presente
	2a)Utilizzare il comando "createdb postgres"
3)Da riga di comando navigare fino a bin/serverVaccini.jar
4)Eseguire il server con il comando "java -jar serverVaccini.jar"
5)La procedura di inizializzazione del database si avvia in automatico, si dovrà solamente 
inserire le credenziali giuste(username,password,host,nomeDB)

N.B. La procedura di inizializzazione genera un file config contenete la configurazione con la quale il server si connette al database.
In caso il server non trovasse la configurazione provvederà a riinizializzare il database.

