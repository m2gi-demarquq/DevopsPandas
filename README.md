# JPandas _(PyPandas pour Java)_
![](https://github.com/Quentindmrq/DevopsPandas/workflows/Tests/badge.svg)

## Description
Projet universitaire ayant de pour but de développer une version java de la bibliothèque PyPandas en utilisant
au maximum les techniques de déploiement continu.

## Fonctionnalités

il y a 2 constructeurs :
le premier utilise la classe couplelabeldata qui est un couple d'un string pour le label et d'une arrayList pour le contenue de la colonne
le deuxieme utilise un fichier csv

affichage:
tous les affichages se font sous la forme d'un string formaté au format csv
methodes:
print_all pour afficher tout le tableau
print_until et print_from pour afficher respectivement jusqu'a et depuis un numero de ligne, le numero donné est inclus

print_part qui prend 4 arguments : un début et une fin pour les lignes et les colonnes

statistiques:

average, mincolumn, maxcolumn, medianecolumn
qui prennent un label de colonne et rendent respectivement la moyenne, le min, le max, la mediane de cette colonne
si l'operation est impossible un null sera rendu.

### Workflow
les push sur le master sont impossible.
il faut passer par une pull request qui est alors vérifier par un autre membre de l'equipe
une pull request ne peut pas être merge si elle ne passe pas un mvn verify
ceci permet de verifier que tout les tests sont passé avant de merge et aussi grace au plugin jacoco de verifier que la couverture de code est au moins à 90%

lors d'un merge sur le master un mvn deploy est effectué.

### Version 0.1 (beta)
