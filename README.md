# Jeu d'assemblage: 

Projet de troisième année de licence d'informatique : création d'une application java permettant de jouer à un jeu d'assemblage 
de forme (créer une forme avec un nombre de pièce prédéfinis en fonction de la difficulté en prenant le moins de place posssible)

## Membre du groupe:

* Bauchez Marguerite
* Cocquerez Olivier
* Lebranchu Paul
* Lemaire Raphaelle


## Comment lancer l'application?

Vous pouvez procéder de deux façons : 


/!\ pour les commandes suivantes, vous devez saisir les commandes sans les ""/!\

* Entrez la commande "ant" dans un terminal ouvert dans le repertoire où se trouve ce document
(vous devez avoir ant d'installez pour lancer cette commande,  lancez la commande et
si ant n'est pas installez, nous vous invitons à consultez cette page pour l'installation sous
linux: https://doc.ubuntu-fr.org/ant et celle-ci pour l'installation sous windows: 
https://www.codeflow.site/fr/article/ant__how-to-install-apache-ant-on-windows)

* Entrez la commande "java -jar dist/livraison-0.1.jar" dans un terminal ouvert dans le repertoire
où se trouve ce document /!\ Si vous n'avez pas lancé la commande ant précédément, vous aurez une
version du .jar qui sera peut être incompatible avec votre ordinateur, si une erreur se produit lors
lancement, lancer la commande "ant" puis relancer la commande "java -jar dist/livraison-0.1.jar"

* La commande "ant" vous permet de compiler le code et de l'executer, mais c'est également elle qui génère
la java.doc et l'archive .jar. pour pouvoir executer cette commande, vous devrez avoir ant d'installé sur
machine.

## Comment jouer au jeu?

Deux options s'offre à vous, une fois le programme lancé, vous pourrez:

* soit joué dans votre terminal en suivant les instructions qui s'affichent sur le terminal

* soit vous pourrez utilisé la fenêtre qui s'est lancé en même temps que votre programme, vous tomberez 
alors sur le menu. Avant de lancer une partie en choissisant votre difficulté, je vous conseillerez de
jetez un oeil au différents control du jeu (appuyer sur le bouton Control du menu) et choissisez ensuite
la difficulté de votre jeu (option personnalisé: entre un entier compris entre 3 et 6)


Il existe une troisième option pour lancer le jeu mais elle est encore en cours de développement: 

Si vous avez les permissions sur votre ordinateur pour executer un fichier .jar venant de l'exterieur,vous
pouvez cliquer sur l'archive .jar présentes dans le fichier dist, cependant, vous ne pourrez jouer qu'à une
version où les images ne sont pas disponibles et où vous ne pourrez jouer qu'en mode visuel (aucun terminal
ne s'ouvrira lorsque vous cliquerez sur l'archive).Comme pour la commande "java -jar dist/livraison-0.1.jar",
il est possible que vous aillez une erreur au lancement avec l'archive .jar, veuillez relancer la commande 
"ant" et réessayez si cela se produit.
