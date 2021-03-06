# Testaus

Huom! Järjestelmätestauksen kuvat päivitetty vielä 22.6, koska eivät täysin vastanneet lopullista toteutusta. Alkuperäinen (DL mennessä toteutettu) testausdokumentti:  [testing_old.md](./testing_old.md)

## Yksikkötestaus

Testikattavuus: https://eevalaiho.github.io/FocusStacking/coverage/index.html


## Järjestelmätestaus

Ohjelman järjestelmätestausta varten on [Apysinia fulva -merikorallia esittävästä kuvasta](https://free-images.com/display/aplysina_fulva_png.html) tehty 150x100 kuvapisteen kokoisia testikuvia (alla). Kuvat on tehty kuvankäsittelyohjelmalla siten, että kuvasta on ensin leikattu 75x50 kuvapisteen kokoinen pala. Tämä pala on peilattu ensin vertikaalisesti niin, jolloin on saatu 75x100 kuvapisteen kokoinen kuva, joka sitten on peilattu horisontaalisesti. Näin kuvasta on saatu joka neljännekseltään yhtä tarkka kuva (alla).  

![Tarkka](../application/src/main/resources/150x100-koralli-mirrored-sharp.png "Tarkka")

Tämän jälkeen kuvasta on edelleen muokattu kolme testikuvaa, joissa on sumennettu joko vasen-, oikea- tai yläpuoli (alla). 

![Vasemmalta blurri](../application/src/main/resources/150x100-koralli-mirrored-left-blur.png "Vasemmalta blurri")
![Oikealta blurri](../application/src/main/resources/150x100-koralli-mirrored-right-blur.png "Oikealta blurri")
![Ylhäältä blurri](../application/src/main/resources/150x100-koralli-mirrored-top-blur.png "Ylhäältä blurri")

### Eri värikanavien testaus

Tarkkojen pikselien valinnassa käytetty värikanava vaikuttaa ohjelman tuottamiin kuviin. Seuraavat kuvat on tuotettu 32 pikselin ikkunakoolla (punainen, vihreä, sininen):

![Punainen](./images/150x100_RED_32.png "Punainen")
![Vihreä](./images/150x100_GREEN_32.png "Vihreä")
![Sininen](./images/150x100_BLUE_32.png "Sininen")

Kuvista voi havaita silmämääräisesti, että eri värikanavaa käyttämällä kuvaan valikoituu pikseleitä eri kuvista. Tällä kuvalla sininen värikanava tuottaa silmämääräisesti parhaan tuloksen. 

### Eri ikkunakokojen testaus

Kun sinistä värikanavaa käytetän eri ikkunakoilla (8, 16, 32, 64) ohjelma tuottaa seuraavat kuvat:

![Output 8](./images/150x100_BLUE_8.png "Output 8")
![Output 16](./images/150x100_BLUE_16.png "Output 16")
![Output 32](./images/150x100_BLUE_32.png "Output 32")
![Output 64](./images/150x100_BLUE_64.png "Output 64")

Kuvista huomataan, että ikkunan koon kasvattaminen näyttäisi silmämääräisesti parantavan algoritmin tarkkuutta ikkunakokoon 32px asti. 


## Suorituskykytestaus

Ohjelman suorituskykytestauksessa testattiin ikkunakoon ja kuvakoon vaikutusta ohjelman suoritukseen kuluvaan aikaan. 

### Kuvan koko

Kuvakoon vaikutusta ohjelman suoritukseen kuluvaan aikaan testatiin kuvako'oilla 30x20, 150x100 ja 300x200. Testeissä käytettiin ikkunakokoa 16 ja punaista värikanavaa. Metodit kirjoitettiin samaan testiluokkaan ja kunkin metodin suorituksen jälkeen kutsuttiin Java:n sleep-metodia, että eri testimetodit voitiin helpommin eroottaa suorituskykyraportilta. 

![Effect of image size on performance](./performance/imageSize/jprofiler_images/telemetry.png "Effect of image size on performance")

Testi suoritettiin ensin 30x20 (n. 0-0,05min), sitten 150x100 (n. 0,1min-0,65min) ja viimeksi 300x200 (n. 0,7min-3min) kuvakoolla. 

Raportista voidaan huomata, että kuvakoon kasvaessa ohjelman suoritusaika kasvaa merkittävästi. Määrittelyvaiheen suorituskykyanalyysin perusteella tämä oli odotettavissa. 

### Ikkunan koko

Ikkunakoon vaikutusta ohjelman suoritukseen kuluvaan aikaan testatiin ikkunako'oilla 8, 16 ja 32. Testeissä käytettiin kuvakokoa 150x100 ja punaista värikanavaa. Metodit kirjoitettiin samaan testiluokkaan ja kunkin metodin suorituksen jälkeen kutsuttiin Java:n sleep-metodia, että eri testimetodit voitiin helpommin eroottaa suorituskykyraportilta. 

![Effect of window size on performance](./performance/windowSize/jprofiler_images/telemetry.png "Effect of window size on performance")

Testi suoritettiin ensin 8 (n. 0-0,15min), sitten 32 (n. 0,2min-3,3min) ja viimeksi 16 (n. 3,35min-4min) ikkunakoolla. 

Raportista voidaan huomata, että ikkunakoon kasvaessa ohjelman suoritusaika kasvaa merkittävästi. Määrittelyvaiheen suorituskykyanalyysin perusteella tämä oli odotettavissa. 
