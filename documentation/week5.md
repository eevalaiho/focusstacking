# Viikkoraportti 5 (7.6.2019)

## Mitä olen tehnyt tällä viikolla?

Olen:
* Toteuttanut oman ArrayList -luokan ja sille testejä
* Toteuttanut oman Complex -luokan ja sille testejä
* Viilaillut testejä muutenkin
* Edistänyt päätoiminnallisuutta
* Testikattavuus [https://eevalaiho.github.io/FocusStacking/coverage/index.html](https://eevalaiho.github.io/FocusStacking/coverage/index.html)
* Kuvia

Ajankäyttö: 30h

## Miten ohjelma on edistynyt? 

Paremmin, oikeastaan hyvin. Ohjelma toimii ja valitsee paljon oikeita pikseleitä. 

## Mitä opin tällä viikolla / tänään?

Että fft:n ja 2D fft:n testikeissit voi laskea kivasti R:llä. Miten github.io konffataan näyttämään ., _ ja #-alkuisia tiedostoja ja hakemistoja. 

## Mikä jäi epäselväksi tai tuottanut vaikeuksia? Vastaa tähän kohtaan rehellisesti, koska saat tarvittaessa apua tämän kohdan perusteella.

Olen epävarma siitä, miten maksimi L^2 normi pitää laskea. Kaksi lähestymistapaa, joista [1] näyttäisi valitsevan oikeammat pikselit (... että olikohan tässä nyt sitten validia kysymystä lainkaan):

[1]

```
summa = 0
Jokaiselle Fourier-muunnosmatriisin alkiolle:
   summa += Abs(alkio)^2              # Tässä Abs koska alkio on kompleksiluku
Palauta Sqrt(summa)
```
[2]

```
maksimi = 0
Jokaiselle Fourier-muunnosmatriisin alkiolle:
   normi = L2Normi(alkio)             # Tässä L2Normi laksetaan Sqrt(alkio.re^2 + alkio.im^2)
   Jos normi > maksimi:
       maksimi = normi
Palauta maksimi
```

## Mitä teen seuraavaksi?

Kirjoitan puuttuvia testejä ja dokumentaatiota. Parametrisoin main-metodin. Valmistaudun ensi viikon demoon.

## Kysymyksiä


