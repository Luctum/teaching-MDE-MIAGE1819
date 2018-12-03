M2MIAGE - IDM - Réalisé par Hugo PALLA et Romain BOITARD

# Projet Générateur de vidéo
Projet ayant pour objectif la création d'un générateur de vidéo aléatoire utilisant des fichiers de spécification .videogen
Les technologies utilisées pour la partie web sont le framework java Spark pour le backend et Vue.js pour le frontend

# Installation du projet

## Prérequis 

* Eclipse d'installé avec les bibliothèques XTEXT disponible ( https://www.eclipse.org/Xtext/download.html )
* ffmpeg téléchargé et ajouté au Path
* node js installé et npm ajouté au Path
* Videos nécessaires pour utiliser les fichiers videogen fournis : https://drive.google.com/file/d/1GAnu-Pn9hNklpWdQqqaxosXeSr9uQCoh/view?usp=sharing

## Installation

* Importez le projet sur eclipse
* Compilez la grammaire Xtext ( clique droit sur le fichier *org.xtext.example.videogen/src/org/xtext/example/mydsl/VideoGen.xtext* > run as > Generate Xtext Artifacts )
* Supprimez le projet VideoGenToolSuite et réimportez le en tant que **projet maven** afin d'obtenir les dépendances
* Dans *VideoGenToolSuite/videogentool-front* exécutez la commande **npm install** pour obtenir les librairies javascript
* Ajoutez à la racine du projet VideoGenToolSuite un fichier appellé *conf* et ajoutez tel quel le chemin vers votre répertoire contenant les vidéos. ( attention de ne pas oublier les caractères d'échappement sur Windows)

## Utilisation

* Exécutez la commande **npm run serve**
* Lancez le serveur *VideoGenToolSuite/src/main/java/VideoGenToolSuite/App.java*
* Rendez-vous sur **http://localhost:8080/#/** pour avoir accès à l'interface ( ne pas oublier le **#** )

Le .videogen utilisé peut être changé dans *App.java* lors de l'instantiation de la classe VideoGenerator 

# Teaching resources for MDE (aka IDM) course at University of Rennes 1 (MIAGE)

## Short description

This course introduces model-driven engineering (MDE) foundations and techniques within the context of software development. 
At the end, students can elaborate metamodels, develop specialized tools, create their own languages, transform models, and automatically build variants of artefacts out of textual or graphical specifications. 
They are also able to understand and recognize classes of software systems, engineering scenarios, or contemporary frameworks for which the MDE concepts apply. 
In a sense, students are ready to apply state-of-the-art techniques for engineering software -- now and in the upcoming years!

Tools and languages like Xtext, Xtend, EMF, FAMILIAR are used to make it practical. 
We illustrate the course with a running project, VideoGen, a *configurable generator of generators of video variants*.  

## Agenda 

* introduction to model-driven software development -- MDLE.pdf 
* domain-specific languages (DSL) -- DSL.pdf
* external DSLs and Xtext -- DSLAndXtext.pdf
* metamodeling and EMF -- ModelManagementXtend1.pdf 
* model transformation-- ModelManagementXtend1.pdf 
* meta-programming, annotations, advanced compositions -- ModelManagementXtend2.pdf 
* Xtend, a modern programming language implemented with MDE principles (Xtext included) -- ModelManagementXtend2.pdf
* software product lines, variability modeling, and configuration: some cases (VaryLaTeX, JHipster testing) -- SPLVariability-misc.pdf
  * JHipster: https://github.com/FAMILIAR-project/HackOurLanguages-SIF/blob/master/slides/SamplingJHipsterCaseStudy.pdf 
  * VaryLaTeX: https://hal.inria.fr/hal-01659161/

## Evaluation 

* 50% (homework + project)
* 50% (partiel) 

## Lab sessions 

* 2 TDs 
   * TD1: https://docs.google.com/document/d/1Nriv6F6_jssuzWWPz8WSkXJeiuElNz-Rp5btVtjyRJQ/edit?usp=sharing
   * TD2: cf ExamIDM16.pdf (a typical exam)
* 6 TPs
  * TP1: Xtext, first steps with VideoGen https://docs.google.com/document/d/1EvY8nVgnw7YwXNln6XhvOnBn46sGpVFrr6xMustssAs/edit?usp=sharing
  * TP2: Model transformation, let's play video variants! https://docs.google.com/document/d/1cYbPolJo03fYydBrJrgVuuJdWmnK_IhFtswIk9g0jeI/edit?usp=sharing
  * TP3: Model transformation, data analysis https://docs.google.com/document/d/1OETjPKA7_bOuGKKt19Wc_fN4l7MWM0NUm8ra2N72oNM/edit?usp=sharing
  * TP4: test, refactoring, and continuing https://docs.google.com/document/d/1VnuDxgmJpkxeYBddPbOR4L0XIPxgsdtdWCK0jKZMqYc/edit?usp=sharing
  * TP5/TP6/projet: TP* (putting all together) https://docs.google.com/document/d/1VtFK0tDDLfxOmYktWliVA7M0WFXDv3mxreeghdub9cA/edit?usp=sharing


## Exercices/Homework 

Jhipster and GPLs/DSLs: http://tinyurl.com/jhipster-langs1819 (collaborative list of all languages used in Jhipster) 

Project: VideoGen (incl. 4 lab sessions)

## Resources 

Eclipse/Xtext: https://eclipse.org/downloads/eclipse-packages/ (Eclipse IDE for Java and DSL Developers)

* see "slides" folder of this repo (the updloads of slides will follow the progress of the courses)
* Resources used for the IDM course in 2016-217: https://github.com/acherm/teaching-MDE1617
* JOOQ and SQL: https://github.com/acherm/teaching-MDE1617/wiki/JOOQ-homework
* http://www.mathieuacher.com/teaching/MDE/ (content of previous years)
* variability and product lines: http://teaching.variability.io

