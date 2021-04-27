# JPandas _(PyPandas pour Java)_
![](https://github.com/Quentindmrq/DevopsPandas/workflows/Tests/badge.svg)

## Description
Projet universitaire ayant de pour but de développer une version java de la bibliothèque PyPandas en utilisant
au maximum les techniques de déploiement continu.

Pour la documentation complète voir le lien suivant : https://zefuri.github.io/.

## Fonctionnalités

### Constructeurs
Il y a 2 constructeurs pour notre DataFrame :
* Le premier utilise la classe CoupleLabelData qui est un couple composé d'une chaîne de caractères pour le label et d'une ArrayList pour le contenu de la colonne.
* Le deuxième utilise un fichier CSV qui est parsé pour coller au format de données du DataFrame.

### Affichage
Tous les affichages se font sous la forme d'une chaîne de caractères prenant au format CSV.

#### Méthodes
* *printAll* pour afficher tout le tableau.
* *printUntil* et *printFrom* pour afficher respectivement jusqu'à et depuis un numéro de ligne (inclus).
* *printPart* qui prend 4 arguments : un début et une fin pour les lignes et idem pour les colonnes.

### Sélection
Pour la sélection, on peut choisir de sélectionner des colonnes, des lignes ou bien les deux en même temps avec la sélection avancée.
Chaque résultat de sélection est renvoyé dans un nouveau DataFrame.

#### Méthodes
* *selectColumn* prend en argument une ou plusieurs chaînes de caractères correspondant aux labels des colonnes à sélectionner.
* *selectRow*, *selectRowsFrom* et *selectRowsTo* prend en argument le numéro d'une ligne et renvoie respectivement la ligne correspondante, les lignes depuis le numéro donné jusqu'à la fin et les lignes depuis le début jusqu'au numéro donné (inclus).
* *selectRows* prend en argument deux numéros de lignes *from* et *to* et renvoie les lignes entre ces deux numéros (inclus).
* *advancedSelect* cette fonction prend en compte que le DataFrame possède des colonnes qui elles-mêmes possèdent des colonnes comme valeurs. Elle a plusieurs signatures :
    * *advancedSelect(String ...)* prend en argument une ou plusieurs chaînes de caractères et "entre" successivement dans chaque colonne imbriquée avant de renvoyer la colonne correspondant au dernier label passé en argument.
    * *advancedSelect(int, String ...)* prend en argument un numéro de ligne et une ou plusieurs chaînes de caractères. Fais un appel à la fonction précédente puis appelle *selectRow* sur le résultat en lui passant le numéro de ligne en argument.
    * *advancedSelect(int, int, String ...)* prend en argument deux numéros de ligne. Fais un appel à la fonction *advancedSelect(String ...)* puis appelle *selectRows* sur le résultat en lui passant les deux numéros de ligne en argument.

### Statistique
Chaque calcul de statistique se fait sur une colonne en fournissant le label de cette dernière et renvoie null si le calcul est impossible.

#### Méthodes
* *average* renvoie la moyenne de la colonne.
* *minColumn* et *maxColumn* renvoie respectivement le minimum et le maximum de la colonne.
* *medianColumn* renvoie la valeur médiane de la colonne.


### Workflow
Les push sur le master sont impossible.
Il faut passer par une pull request qui est alors vérifiée par un autre membre de l'équipe.
Une pull request ne peut pas être merge si elle ne passe pas un mvn verify.
Ceci permet de vérifier que tous les tests sont passés avant de merge et aussi, grâce au plugin jacoco, de vérifier que la couverture de code est au moins de 90%.

Lors d'un merge sur le master un mvn deploy est effectué.