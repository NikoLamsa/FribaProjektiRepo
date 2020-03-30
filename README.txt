Asennus komentoriviltä:

Tallenna projekti ja 
Navigoi kansioon \fribaProjekti jossa pom.xml sijaitsee 

~%tallennus_sijainti%\fribaProjekti

Aja sijainnissa komento:(k�ytt�� mavenia)
   mvn clean install

	tai

   mvn clean package

(clean ei myöskään pakollinen mutta siivoaa aiemmat rojut)


kun kääntö (Build Success) on valmis ja onnistunut
mene kansioon ...\FribaProjekti\target

käynnistä sovellus komennolla:
java -jar sovellus-0.0.1-SNAPSHOT.jar

Sovellus on myös mahdollista käynnistää kuvakkeesta mutta prosessi joudutaan tappamaan 
taustalta kun sovellus halutaan sulkea. 


Kun sovellus on käynnissä voi sille lähettää komentoja komentoriviltä seuraavasti: (käytä erillistä konsolia)

POST:
curl -X POST localhost:8080/Kiekot -d "{\"name\": \"Teksti\"}" -H "Content-type:application/json"   TAI
curl -X POST localhost:8080/Kiekot -H 'Content-type:application/json' -d '{"name": "Teksti"}'  
-----------------------------------------
PUT:
curl -X PUT localhost:8080/Kiekot/numero -d "{\"name\": \"Teksti\"}" -H "Content-type:application/json" TAI
curl -X PUT localhost:8080/Kiekot/numero -H 'Content-type:application/json' -d '{"name": "Teksti"}'

(ylemmät rivit ainakin windowsissa)
-----------------------------------------
GET all:
curl -X GET localhost:8080/Kiekot
-----------------------------------------
GET id:
curl -X GET localhost:8080/Kiekot/ID/numero
----------------------------------------
GET nimellä:
curl -X GET localhost:8080/Kiekot/Nimi/nimi
----------------------------------------
DELETE:
curl -X DELETE localhost:8080/Kiekot/numero


Voi myös tarkastella selaimessa localhost:8080/Kiekot

