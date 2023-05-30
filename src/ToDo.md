


-[x]  1. Maak in de map models een klasse aan voor RemoteController, CI-Module en WallBracket (voeg de juiste annotatie, variables, getters&setters en constructors toe).
-[x]  2. Maak in de map repositories voor elk model een Repository aan (die elk de JpaRepository extends bevat).
-[x]  3. Maak in de map controllers voor elk model een Controller aan (met juiste annotatie, constructor en requestMappings).
-[x]  4. Maak in de map dtos voor elk model een Dto en InputDto aan (met juiste variables en toewijzingen).
-[x]  5. Maak in de map services voor elk model een Service aan (met juiste annotatie, constructor en functions).
-[x]  6. Leg een OneToOne relatie tussen Television en RemoteController door in beide models @OneToOne toe te voegen, gevolgd door het model waar de relatie mee ligt in de vorm van Model object (bijvoorbeeld Television television) op de volgende regel.
-[x]  7. Een OneToOne relatie heeft een eigenaar nodig. Maak de Television eigenaar door in RemoteController achter de @OneToOne mappedBy toe te voegen op deze manier _@OneToOne(mappedBy = "remotecontroller"). Dit zorgt ervoor dat in de Television tabel een kolom wordt toegevoegd met de naam remotecontroller_id. Vergeet niet de getter en setter toe te voegen na het leggen van de relatie in de modellen.

afmaken data sql file 


-[ ]  8. Om deze kolom te vullen zal je in servicelaag ook een functie moeten maken die een koppeling maakt tussen de Television en de RemoteController. Dit doe je in de TelevisionService.-[ ]  2.
-[ ]  9. Voeg de functie "assignRemoteControllerToTelevision" toe in de TelevisionService. Zoals je ziet, herkent de TelevisionService de RemoteControllerRepository niet, dit komt omdat we deze nog niet gekoppeld hebben in de constructor, gelukkig hoef je niet alles opnieuw te doen. Je kan bovenaan in de TelevisionService onder de private TelevisionRepository een private RemoteControllerRepository declareren. En dan in de bestaande constructor deze toevoegen op dezelfde manier als de TelevisionRepository. Dit resulteert in: