  # Introduction:
Musketeers Adventure is a mini-game where the player ends up in France as one of the King's Musketeers.
There are three places where one can end up. Drink and play dice with citizens in the Tavern. Polish rapiers, to medicate oneself, sleep in the Barracks.
The Plaza is a place where Guardsmen can set up an ambush and attack the Musketeers.

A Musketeer can have a certain character trait that gives him a small bonus:
- Vain (If he loses to citizens he initiates a brawl)
- Drunkard (If he starts drinking he won't be able to stop)
- Accomplished duelist (Gives +30 to fencing)
- Cardsharp (Throws a dice and gets at least 3 independently)
- Brawler (Gives +10 to Punch)

Definitly Musketeers are so perfectly trained that they get +20 to Fencing.

The exit point of programm is to send musketeers sleep in the Barracks.


## Locations:
- Tavern
- Plaza
- Barracks

## Characters:
- Musketeer
- Guardsman
- Citizen

## Weapon:
- Rapier

## Notes:
PostgreSQL is used to store player data (name, trait).
Before running the app you must rename the file **`jdbc.example.properties`** to **`jdbc.properties`** and **`specify`** your database **`credentials`**.
