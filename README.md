# Les amis de l'escalade
Site web communautaire autour de l'escalade. 

Principales fonctionnalités :
- recherche et consultation de sites d'escalade
- inscription en tant que membre
- en tant que membre possibilité d'ajouter des sites et des topos
- en tant que membre possibilité de laisser des commentaires sur les sites d'escalade
- en tant que membre possibilité de réserver des topos d'autres membres

# Spécifications techniques
Application web JAVA EE dévéloppée avec le framework Spring à l'aide de Spring Boot. Principaux  starters utilisés :
- Web
- Data JPA avec Hibernate
- Security
- Thymeleaf
- Connecteur Mysql

BDD : MySQL

Webjars Bootstrap et JQuery pour le front.


## Prérequis 
**Un JDK version 8** minimum. 2 possibilités :
- le télécharger depuis le site d'oracle (le JDK est utilisable gratuitement pour des projets non commerciaux) :
	- Pour télécharger le JDK : https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
	- Les instructions d'installation se trouve ici : https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

- le télécharger depuis AdoptOpenJDK (utilisable gratuitement quelque soit les projets)
	- pour télécharger le JDK : https://adoptopenjdk.net/
	- les instructions d'installation se trouve ici : https://adoptopenjdk.net/installation.html#

**Apache Maven 3** :
- Pour télécharger Apache Maven : https://maven.apache.org/download.cgi
- Pour installer Apache Maven : https://maven.apache.org/install.html

## Récupérer le projet 

### via git clone
En ligne de commande, se placer dans le répertoire voulu puis exécuter la commande `git clone https://github.com/gaelle8278/OC-les-amis-de-lescalade.git`. 

- Sous Windows, dans l'invite de commande :
```
cd D:/DevProjets
git clone https://github.com/gaelle8278/OC-les-amis-de-lescalade.git
```

- Sous Linux, dans un terminal :
```
cd ~/DevProjets
git clone https://github.com/gaelle8278/OC-les-amis-de-lescalade.git
```

### via l'archive zip

Télécharger l'archive depuis la page du projet : https://github.com/gaelle8278/OC-les-amis-de-lescalade. Cliquer sur le bouton vert "Clone or download" puis Download ZIP.

Se rendre dans le dossier où l'archive a été téléchargée puis la décompresser, l'archive se nomme OC-les-amis-de-lescalade-master.zip : 
- Sous Windows utiliser son utilitaire préféré : winrar, 7zip, peazip ...
- Sous Linux utiliser la commande : `unzip OC-les-amis-de-lescalade-master.zip`

Cela crée le dossier OC-les-amis-de-lescalade-master, le renommer les-amis-de-lescalade (soit en faisant clic droit sur le dossier puis renommer soit en ligne de commande).


## Configuration

application.properties

## Compiler l'application avec Apache Maven

produire le war à partir des sources

mvn package

## Déployer l'application

Déployer le war sous Tomcat

Importer la bdd dans Mysql et le jeu de données



## Comptes tests



