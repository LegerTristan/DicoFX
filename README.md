# DicoFX

## Présentation

Dictionnaire où l'on saisit ces définitions et on peut les réviser via un système de note.
Développé seul en JavaFX durant les vacances d'été 2019.

## Comment lancer DicoFX ?

Il faut posséder au préalable :

* Java (1.8 recommandé)
* Eclipse (ou tout autre IDE qui supporte le langage Java)
* JavaFX (ou e(fx)clipse pour les utilisateurs d'Eclipse)

Il vous faut ensuite **cloner le dépôt** sur votre pc via **Git**, ou **télécharger l'archive ZIP** à partir du *Download* en vert se situant en haut à droite du dépôt. Une fois le projet en votre possession, ouvrez le avec votre IDE préféré, puis lancer l'application (le main de l'application se trouve dans la classe DicoFX.java)

## Fonctionnement du projet

L'application appel en premier la scène du menu principal sur lequel on peut ajouter/éditer/supprimer une définition ou une catégorie.
Un système de sauvegarde automatique a été mis en place pour garder les définitions et les catégories crées en toute simplicité et sans nécessite une action de la part de l'utilisateur.

Toujours depuis le menu, on peut accéder à des exercices de révision d'une définition via le nom de celle-ci et inversement.
Il faut retranscrire la définition exacte, il n'y a pas de limite de temps, une note est affichée à la fin de la session, celle-ci ne sont pas enregistrés par contre.
