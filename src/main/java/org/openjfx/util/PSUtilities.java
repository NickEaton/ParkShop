package org.openjfx.util;

import java.util.Random;

public class PSUtilities {

    public enum firstNames {
        Sullivan,
        Kolby,
        Kadin,
        Josue,
        Jaeden,
        Weston,
        Noah,
        Braiden,
        Julian,
        Mohammad,
        Tyrone,
        Sawyer,
        Abraham,
        Rudy,
        Yurem,
        Marques,
        Scott,
        Frankie,
        Angel,
        Jordon,
        Cael,
        Dustin,
        Rhett,
        Agustin,
        Alijah,
        Payton,
        Wyatt,
        Evan,
        Calvin,
        Jake,
        Nickolas,
        King,
        Ellis,
        Colten,
        Eliezer,
        Kevin,
        Mathew,
        Rigoberto,
        Clay,
        Zain,
        Ricky,
        Pranav,
        Jimmy,
        Brody,
        Valentino,
        Jared,
        Jesus,
        Demarcus,
        Max,
        Johnny,
        Kenneth,
        Mekhi,
        Josh,
        Allen,
        Kyan,
        Ramon,
        Roland,
        Royce,
        Isaias,
        Cullen,
        Alessandro,
        Winston,
        Trystan,
        Demarion,
        Mateo,
        Talan,
        Dean,
        Lincoln,
        Maverick,
        Nikolas,
        Kash,
        Thaddeus,
        Trenton,
        Walker,
        Nikhil,
        Gaige,
        Lorenzo,
        Stephen,
        Quincy,
        Yair,
        Rhys,
        Carl,
        Frederick,
        Brodie,
        Finn,
        Ralph,
        Corbin,
        Ty,
        Keagan,
        Teagan,
        Camden,
        Bradley,
        Darrell,
        Tristin,
        Prince,
        Colin,
        August,
        Alonzo,
        Hugh,
        Kelvin,
        Nick,
        Kristin,
        Aniya,
        Laura,
        Paulina,
        Ashtyn,
        Elena,
        Liberty,
        Aleena,
        Alannah,
        Elyse,
        Delilah,
        Angelique,
        Charlize,
        Mckenzie,
        Theresa,
        Zaniyah,
        Keira,
        Anika,
        June,
        Julissa,
        Autumn,
        Jaida,
        Mira,
        Susan,
        Kaley,
        Denisse,
        Raina,
        Cristina,
        Sanaa,
        Rachel,
        Lydia,
        Michelle,
        Jasmine,
        Katelyn,
        Sabrina,
        Aimee,
        Iris,
        Abby,
        Kailey,
        Molly,
        Hadassah,
        Kenley,
        Kaya,
        Krista,
        Yaritza,
        Elaina,
        Alison,
        Erin,
        Naomi,
        Amirah,
        Ansley,
        Paola,
        Nia,
        Marie,
        Kaiya,
        Presley,
        Arely,
        Fiona,
        Vivian,
        Camryn,
        Ella,
        Angeline,
        Alena,
        Mireya,
        Isabela,
        Princess,
        Sophie,
        Elianna,
        Jessica,
        Kylee,
        Akira,
        Sofia,
        Gabriella,
        Beatrice,
        Dixie,
        Summer,
        Khloe,
        Destinee,
        Elisabeth,
        Carly,
        Karli,
        Gretchen,
        Amber,
        Cristal,
        Jillian,
        Nyla,
        Cheyenne,
        Amira,
        Tanya,
        Maeve,
        Shirley,
        Kimberly,
        Serena,
        Meadow,
        Elaine,
        Arianna,
        Aliyah,
        Vanessa,
        Imani,
        Marley
    }

    public enum lastNames {
        Krueger,
        Fernandez,
        Hardin,
        Conley,
        Fleming,
        Christensen,
        Day,
        Flores,
        Calhoun,
        Richards,
        Vaughan,
        Hardy,
        Doyle,
        Macias,
        Goodman,
        Haney,
        Yates,
        Perry,
        Sheppard,
        Rowe,
        Potts,
        Hayden,
        Jordan,
        Poole,
        Esparza,
        Middleton,
        Mayer,
        Rocha,
        Jackson,
        Key,
        Griffith,
        Rollins,
        Wolf,
        Bauer,
        Zuniga,
        Moreno,
        Clayton,
        Mccarty,
        Malone,
        Brock,
        Kane,
        Dunn,
        Mcpherson,
        Wiggins,
        Pratt,
        Franco,
        Farrell,
        Hopkins,
        Browning,
        Bowen,
        Benson,
        Warren,
        Best,
        Riddle,
        Nolan,
        Wang,
        Burch,
        Harper,
        Cooke,
        Durham,
        Christian,
        Weaver,
        Wyatt,
        Singleton,
        Cain,
        Morrison,
        Vasquez,
        Hodges,
        Dudley,
        Elliott,
        Roman,
        Frank,
        Nelson,
        Bradley,
        Sims,
        Woodard,
        Hill,
        Buchanan,
        Fitzgerald,
        Webb,
        Knapp,
        Werner,
        Douglas,
        Brady,
        Crosby,
        Hamilton,
        Stewart,
        Hanna,
        Meza,
        Cisneros,
        Fry,
        Russo,
        Townsend,
        Schaefer,
        Farley,
        Arnold,
        Allison,
        Hendricks,
        Mcconnell,
        Hogan,
        Giles,
        Frey,
        Simon,
        Page,
        Randolph,
        Palmer,
        Archer,
        Nichols,
        Lynch,
        Mendoza,
        Herman,
        Johnson,
        Buck,
        Luna,
        Chaney,
        Rose,
        Branch,
        Walls,
        Hernandez,
        Flynn,
        Hodge,
        Garza,
        Porter,
        Dillon,
        Garner,
        Blackwell,
        Chandler,
        Mcintosh,
        Underwood,
        Greene,
        Roach,
        Washington,
        Keller,
        Lee,
        Swanson,
        Guerrero,
        Horton,
        Sanford,
        Trevino,
        Kline,
        Davidson,
        Baldwin,
        Pennington,
        Gross,
        Rios,
        Ayers,
        Bowers,
        Stark,
        White,
        Gomez,
        Stokes,
        Carpenter,
        Acevedo,
        Heath,
        Clark,
        Quinn,
        Potter,
        Murphy,
        Wilcox,
        Blevins,
        Barr,
        Schultz,
        Oliver,
        Peters,
        Spears,
        Mccullough,
        Yang,
        West,
        Pace,
        Mejia,
        Sawyer,
        Clay,
        Pitts,
        Perkins,
        Collier,
        Torres,
        Beasley,
        Holland,
        Hays,
        Shepard,
        Golden,
        Edwards,
        Patton,
        Barnett,
        Conway,
        Mahoney,
        Rush,
        Donovan,
        Moses,
        Graves,
        Larsen,
        Choi,
        Newton,
        Faulkner,
        Salinas,
        Vargas,
        Velez,
        Lowe,
        Dougherty,
        Skeleton
    }

    public static String flopNewName() {
        Random r = new Random();

        String fname = firstNames.values()[r.nextInt(firstNames.values().length)].toString();
        String lname = lastNames.values()[r.nextInt(lastNames.values().length)].toString();

        return fname + " " + lname;
    }
}