# Les amis de l'escalade
Site web communautaire autour de l'escalade. 

Principales fonctionnalités :
- recherche et consultation de sites d'escalade
- inscription en tant que membre
- en tant que membre possibilité d'ajouter des sites et des topos
- en tant que membre possibilité de laisser des commentaires sur les sites d'escalade
- en tant que membre possibilité de réserver des topos d'autres membres

## Spécifications techniques
Application web JAVA EE dévéloppée avec le framework Spring à l'aide de Spring Boot. Principaux  starters utilisés :
- Web
- Data JPA avec Hibernate
- Security
- Thymeleaf
- Connecteur Mysql

BDD : MariaDB

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

**Apache Tomcat 9**

Installation Apacha Tomcat 9 : http://tomcat.apache.org/tomcat-9.0-doc/setup.html

**MariaDB (ou MySQL)**

Installation MariaDB : https://mariadb.com/kb/en/library/binary-packages/

## Récupérer le projet 

### via git clone
En ligne de commande, se placer dans le répertoire voulu puis exécuter la commande `git clone https://github.com/gaelle8278/OC-les-amis-de-lescalade.git`. Une fois le projet récupéré renommer le dossier les-amis-de-lescalade pour plus de simplicité.

- Sous Windows, dans l'invite de commande :
```
cd D:/DevProjets
git clone https://github.com/gaelle8278/OC-les-amis-de-lescalade.git
rename OC-les-amis-de-lescalade les-amis-de-lescalade
```

- Sous Linux, dans un terminal :
```
cd ~/DevProjets
git clone https://github.com/gaelle8278/OC-les-amis-de-lescalade.git
mv OC-les-amis-de-lescalade les-amis-de-lescalade
```

### via l'archive zip

Télécharger l'archive depuis la page du projet : https://github.com/gaelle8278/OC-les-amis-de-lescalade. Cliquer sur le bouton vert "Clone or download" puis Download ZIP.

Se rendre dans le dossier où l'archive a été téléchargée puis la décompresser, l'archive se nomme OC-les-amis-de-lescalade-master.zip : 
- Sous Windows utiliser son utilitaire préféré : winrar, 7zip, peazip ...
- Sous Linux utiliser la commande : `unzip OC-les-amis-de-lescalade-master.zip`

Cela crée le dossier OC-les-amis-de-lescalade-master, le renommer les-amis-de-lescalade (soit en faisant clic droit sur le dossier puis renommer soit en ligne de commande).


## Configuration de la base de données 

### Méthode recommandée
Le script mysql_database_schema_and_data.sql présent dans le dossier bdd/ du projet permet la création de la base de données, l'import des données ainsi que la création de l'utilisateur administrateur de la base de données.

Importer ce fichier grâce à mysql et à l'utilisateur administrateur de votre base de données.

Dans une ligne de commande :
```
cd les-amis-de-lescalade
mysql -u root -p < bdd/mysql_database_schema_and_data.sql
```

Eléments crées :
* Base de donnée: lesamisdelescalade
* Utilisateur : 

 identifiant : dbuser

 mot de passe : userpwd`



### Méthode manuelle 
Il est possible de configurer les éléments de la base de données séparément s'il l'on souhaite utiliser une configuration personnalisée :
1. Se connecter à la base de données MySQL/MariaDB
2. Créer une base de données
3. Se placer sur la base de données
2. Importer le fichier bdd/mysql_database_schema.sql qui permet de créer les tables
3. Importer le fichier bdd/mysql_database_data.sql qui permet d'insérer les données
4. Ajouter un utilisateur avec les droits SELECT,UPDATE,INSERT,DELETE sur la base de données créée

NB : si le numéro de port de MySQL/MariaDB n'est pas celui par défaut (3306), si les noms de la base de données, de l'utilisateur ou du mot de passe sont différents de ceux indiqués dans la méthode recommandée, il faut les spécifier dans le fichier application.properties se trouvant dans le dossier src/main/resources/ du projet :
* spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:**[port]**/**[nom de la base de donnée]**
* spring.datasource.username=__[identifiant utilisateur bdd]__
* spring.datasource.password=__[mot de passe utilisateur bdd]__

## Déployer l'application

1.Produire l'archive war à partir des sources.

Pour cela se placer dans à la racine du projet et utiliser la commande maven pour packager l'application.

Sous Windows, dans l'invite de commande ou dans un terminal sous Linux :
```
mvn package
```

2.Déployer l'archive war produite sous Tomcat

L'archive war est produite dans le dossier target/ du projet. Placer cette archive war dans le dossier webapps/ de votre installation Tomcat :

- Sous Windows, dans l'invite de commande :
```
cd target 
copy lesamisdelescalade.war D:\DevEnv\apache-tomcat-9.0.29\webapps
```

- Sous  Linux, dans un terminal :
```
cd target 
cp lesamisdelescalade.war /opt/apache-tomcat-9.0.29/webapps
```

Si Tomcat est démarré le déploiement s'effectue immédiatement automatiquement. 
S'il est arrété, démarrez-le. Le déploiement s'effectuera alors automatiquement après le démarrage.


## Utilisation de l'application web

L'adresse de la page d'accueil de l'application est http://localhost:8080/lesamisdelescalade 
*NB :par défaut Tomcat est configuré pour utiliser le port 8080, à adapter si configuration personnalisée.*

Comptes Membres :
* login : mlaplace@maboite.fr / mdp : test 
* login : kbauer@test.com / mdp : test
* login : dpalmer@test.com / mdp : test

Compte Admin :
* login : jbirc@test.com / mdp : test




