-- MariaDB dump 10.17  Distrib 10.4.6-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: lesamisdelescalade
-- ------------------------------------------------------
-- Server version	10.4.6-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `lesamisdelescalade`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `lesamisdelescalade` /*!40100 DEFAULT CHARACTER SET utf8 */;

GRANT SELECT,UPDATE,INSERT,DELETE on `lesamisdelescalade`.* to 'dbuser'@'localhost' identified by 'userpwd';

USE `lesamisdelescalade`;

--
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` mediumtext DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `spot_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Sector_Site1_idx` (`spot_id`),
  CONSTRAINT `fk_area_spot` FOREIGN KEY (`spot_id`) REFERENCES `spot` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES (17,'Ravine Bernica','Description du secteur ravine Bernica','2019-12-11 13:35:15',1),(18,'Grand étang','Description du secteur Grand étang','2019-12-11 13:53:18',1),(31,'La grand passage','','2019-12-15 18:52:43',1);
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(45) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `status_date` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `guidebook_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Loan_User1_idx` (`user_id`),
  KEY `fk_Loan_Topo1_idx` (`guidebook_id`),
  CONSTRAINT `fk_loan_guidebook` FOREIGN KEY (`guidebook_id`) REFERENCES `guidebook` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_loan_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (12,'pending','2019-12-15 18:58:13',NULL,1,12),(13,'cancelled','2019-12-15 19:00:29',NULL,4,9),(14,'approved','2019-12-15 19:00:49',NULL,4,2),(15,'pending','2019-12-15 19:02:55',NULL,10,1),(16,'rejected','2019-12-15 19:03:15',NULL,10,2);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `content` mediumtext DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `spot_id` int(11) DEFAULT NULL,
  `moderator_notice` mediumtext DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Comment_User1_idx` (`user_id`),
  KEY `fk_Comment_Site1_idx` (`spot_id`),
  CONSTRAINT `fk_comment_spot` FOREIGN KEY (`spot_id`) REFERENCES `spot` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (3,'superbe!','Mon spot de grimpe préféré','2019-12-11 22:32:20',1,2,NULL),(4,'A faire','Je recommande !','2019-12-12 00:04:39',1,10,NULL),(5,'Je recommande','Superbe lieu ! ','2019-12-12 19:27:24',1,35,NULL),(7,'Beau spot !','Fait avec mon fils. Très bon souvenir','2019-12-15 18:59:28',1,34,NULL),(8,'Site sauvage et préservé','','2019-12-15 19:01:18',4,1,NULL);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guidebook`
--

DROP TABLE IF EXISTS `guidebook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guidebook` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` mediumtext DEFAULT NULL,
  `release_date` date DEFAULT NULL,
  `place` varchar(45) DEFAULT NULL,
  `bookable` tinyint(1) DEFAULT NULL,
  `available` tinyint(1) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Topo_User1_idx` (`user_id`),
  CONSTRAINT `fk_guidebook_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guidebook`
--

LOCK TABLES `guidebook` WRITE;
/*!40000 ALTER TABLE `guidebook` DISABLE KEYS */;
INSERT INTO `guidebook` VALUES (1,'Le Haut Mur','fdsfdsfdsf','2012-12-09','Massif central',1,1,'2019-12-09 22:21:50',1),(2,'Les gorges de l\'Aveyron','sdsd\r\nsqdsqd\r\n\r\nsqdsdsqdjsqdsqjdhjskhdjsqhdkjq','2017-12-10','Aveyron',1,1,'2019-12-12 16:37:47',1),(3,'Les hautes pierres','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque elementum ultrices justo, id porttitor urna vestibulum non. In sagittis convallis egestas. Vivamus et ex sollicitudin nisl sollicitudin fringilla in quis enim. Donec et velit auctor, finibus leo ac, luctus justo. Integer mollis ullamcorper aliquet. Pellentesque interdum mi sit amet nulla fringilla, vitae volutpat massa luctus. Cras semper interdum velit eget maximus. Maecenas dignissim quis dolor sit amet imperdiet. Ut velit arcu, vehicula sit amet consequat at, rhoncus et tortor. Nullam commodo elit dui, vitae mattis metus tincidunt a. Cras felis erat, maximus et pellentesque at, tempus vel nisi. Aliquam dictum sodales sem, et molestie purus molestie non. Fusce accumsan enim at rutrum malesuada. Morbi scelerisque sed erat a venenatis. Curabitur non lobortis nisl. ','2015-11-09','Les Alpes',0,1,'2019-12-09 18:19:51',1),(7,'Les Pyrénées du Levant','plus de 18 site(s) de grimpe présenté(s) dans ce topo guide d\'escalade','2008-11-06','Les pyrénees',0,1,'2019-12-11 17:03:16',1),(9,'Le volcan','erezr','2015-12-03','Guyane',1,1,'2019-12-12 00:05:00',10),(12,'Topo du Grand Etang','fezfezfd','2018-12-03','Saint-Leu',1,1,'2019-12-15 09:11:43',4);
/*!40000 ALTER TABLE `guidebook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pitch`
--

DROP TABLE IF EXISTS `pitch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pitch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(45) DEFAULT NULL,
  `notice` longtext DEFAULT NULL,
  `grade` varchar(5) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `route_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Length_Path1_idx` (`route_id`),
  CONSTRAINT `fk_pitch_route` FOREIGN KEY (`route_id`) REFERENCES `route` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pitch`
--

LOCK TABLES `pitch` WRITE;
/*!40000 ALTER TABLE `pitch` DISABLE KEYS */;
INSERT INTO `pitch` VALUES (6,'1','','4','2019-12-15 18:53:04',8),(7,'2','','3a','2019-12-15 18:53:11',8);
/*!40000 ALTER TABLE `pitch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(25) NOT NULL DEFAULT '',
  `label` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER','Membre'),(2,'ROLE_ADMIN','Administrateur');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `route` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade` varchar(5) DEFAULT NULL,
  `number` varchar(20) DEFAULT NULL,
  `notice` longtext DEFAULT NULL,
  `nb_spits` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `area_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_route_area_idx` (`area_id`),
  CONSTRAINT `fk_route_area` FOREIGN KEY (`area_id`) REFERENCES `area` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (8,'4','1','',2,'2019-12-15 18:52:54',31);
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spot`
--

DROP TABLE IF EXISTS `spot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` longtext DEFAULT NULL,
  `notice` text DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `region` varchar(45) DEFAULT NULL,
  `orientation` varchar(20) DEFAULT NULL,
  `min_height` int(11) DEFAULT NULL,
  `max_height` int(11) DEFAULT NULL,
  `nb_routes` int(11) DEFAULT NULL,
  `min_grade` varchar(40) DEFAULT NULL,
  `max_grade` varchar(40) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `tag` tinyint(4) NOT NULL DEFAULT 0,
  `nb_areas` int(11) DEFAULT NULL,
  `nb_pitches` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Site_User1_idx` (`user_id`),
  CONSTRAINT `fk_spot_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spot`
--

LOCK TABLES `spot` WRITE;
/*!40000 ALTER TABLE `spot` DISABLE KEYS */;
INSERT INTO `spot` VALUES (1,'Ablon','Ablon, nichée à l’écart, dans un recoin sauvage et préservé en plein cœur du massif des Bornes. On est loin de la montagne à touristes, il n’y a plus aucun bruit de moteur... Là, sur le plateau des Glières, lieu célèbre pour la page d’histoire qui s’y est écrite au cours de la dernière guerre mondiale, où les résistants ont fui les occupants allemands, se trouve le Val d’Ablon, un joyau de falaise haut-savoyard encore inconnu il y a une vingtaine d’années. Sur ses fantastiques dalles grises très sculptées, où le rocher est beau partout, l’escalade présente une variété de styles avec prédominance de grandes envolées en 6b, 6c ou 7a, tout en continuité. On doit plus des deux tiers de ces fabuleuses lignes à Robert Durieux, équipeur motivé et amoureux des lieux depuis au moins trente ans. Autre atout de poids : le pied des voies s’ancre dans un alpage ombragé, moelleux et confortable, idéal pour un assurage serein ou des bambins remuants. Ablon, c’est le site majeur de Haute Savoie pour le cadre, la qualité des lignes et du rocher. Ambiance épicéas, oxygène, clarines et reblochon !','','2019-12-15 18:28:46','Falaise','Haute-Savoie','Sud-Est',0,50,NULL,NULL,NULL,'France',1,0,NULL,NULL),(2,'Arudy','La vallée d’Ossau est l’une des trois grandes vallées de montagne de la région béarnaise. Elle s’étire du nord au sud depuis Pau jusqu’au col du Pourtalet, à la frontière espagnole, sous le regard bienveillant du pic du Midi d’Ossau (2884 mètres), à la silhouette bicéphale si caractéristique. En partie basse se trouve le site d’escalade d’Arudy, lieu historique de la grimpe en vallée d’Ossau. «On ne sait pas exactement à quand remonte la pratique de l’escalade arudienne. En 1958, de jeunes grimpeurs ont tenté d’escalader la Pointe centrale de Sesto (la grande pointe blanche) et en 1959, sont apparus les premiers tracés. Dans les années 1970, quelques voies furent pré-équipées depuis le haut des sommets», lit-on dans un texte publié par Ville d’Arudy - Maison d’Ossau, en 1999. «Depuis, des gens viennent du monde entier s’entraîner sur ce rocher-école», lit-on ensuite sur le site Internet de la mairie d’Arudy. Si nous osons relayer telle quelle cette information sans chercher à la vérifier, ajoutons une possible explication à ce succès planétaire: un rocher calcaire très varié où quelque 230 voies offrent un panel de difficultés allant de l’initiation au haut niveau. De quoi drainer toute la planète grimpe dans la région, ou au moins tous les Palois, qui en ont fait leur falaise…','adapté aux enfants','2019-12-15 18:28:47','Falaise','Pyrénées-Atlantiques','sud-ouest',0,600,NULL,NULL,NULL,'France',4,1,NULL,NULL),(3,'Ailefroide','Situé presque au fond de la vallée de la Vallouise, au Nord de L’Argentière la Bessée, existe-t-il meilleur endroit pour grimper en été ? Peut-être, mais Ailefroide a quand même de quoi convaincre. Ici, à 1.500 m d’altitude, on est en pleine ambiance montagne, entourés de sommets, les vrais, comme sur les cartes postales avec de la neige dessus. Ailefroide c’est beau, et c’est d’ailleurs un site naturel majeur, en partie dans le Parc national des Écrins. Autour, le rocher est partout. D’abord, à portée de bras, il y a ce chaos de gravillons de granit géants, dégringolés des montagnes autour, et aménagés en parcours de bloc pour tous les niveaux. Ailefroide a connu une frénésie d’ouverture dans les années 2000 sous l’impulsion d’Anthony Lamiche et de quelques locaux (les Olivier Fourbet, Yann Ghesquiers, François Lombard et consort...), ce qui en fait aujourd’hui un haut lieu du bloc en France, bien placé dans le best of des sites les plus paradisiaques. Ensuite, à deux pas des blocs, il y a ces dalles granitiques d’excellente qualité parcourues de très nombreuses grandes voies disséminées de part et d’autre d’Ailefroide. Et pour compléter, il y a des sites de couennes sur plusieurs secteurs sportifs en dalles lisses et dévers accessibles. Grandes voies, blocs, couennes, tout est accessible depuis le centre du hameau d’Ailefroide. Dans chaque spécialité, il y en a pour tout le monde, du débutant au sur-confirmé, et la diversité d’orientation permet de grimper avec le soleil ou de le fuir, selon la saison. Tout cela donne de quoi s’occuper plusieurs jours. Voire, plusieurs années. Le tout sous le regard bienveillant de sommets mythiques comme Le Pelvoux et La Barre des Écrins...','','2019-12-15 18:28:47','Bloc, Falaise','Hautes-Alpes','',20,40,NULL,'','','France',1,0,NULL,NULL),(4,'Chamiers','Bien qu\'ayant été découvert des années auparavant pour ses falaises, le site de Charmiers restait vierge d’ouvertures du côté des blocs. Le hasard fit bien les choses lorsque, à partir de 2015, Philippe Ribière se domicilia juste au-dessus… Désormais, cela fait maintenant plus de deux ans qu’il s’investit, seul, à défricher, nettoyer, faire des terrasses d’atterrissage aux pieds des blocs qui se trouvent pour la plupart en coteaux. ',NULL,'2019-12-15 18:28:48','Bloc','Ardèche',NULL,0,14,NULL,NULL,NULL,'France',4,0,NULL,NULL),(5,'Valgorge','Blotti au fond de la vallée de Labeaume sur la route qui conduit à Loubaresse (Sud Ardèche), le petit site de Valgorge est praticable dans de bonnes conditions seulement trois mois par an: les blocs ont la particularité d’être situés dans le lit de la rivière Labeaume et donc, suivant les années (et les crues), il se peut que des passages ne soient plus réalisables, faute de réceptions correctes. Si ce très beau granit poli par l’eau peut être perturbant au premier abord, il devient rapidement un vrai bonheur à grimper.','pour toute la famille !','2019-12-15 18:28:49','Bloc','Ardèche','',5,55,NULL,'','','France',1,1,NULL,NULL),(6,'Le Kronthal','Ce n’est plus un scoop, ni un secret pour personne. On grimpe abondamment en Alsace. La falaise du Kronthal, spot de grès rose bien connu, n’a donc plus à rougir de se trouver en Alsace, d’autant qu’elle a même joué les stars au printemps 2016 en accueillant une étape de la caravane Petzl «J’Irai Grimper Chez Vous». Avec environ 130 voies, quasiment toutes équipées par Armand Baudry, cette barre de grès au nom un peu barbare vous réserve l’escalade carrément physique qui va avec. Le Kronthal est une ancienne carrière. Sachez que c’est ici que l’on a extrait les pierres qui ont permis d’édifier la fameuse cathédrale de Strasbourg à la couleur si particulière. Les commandes pour la construction de cathédrales se faisant plus rares (encore un secteur économique qui souffre de la crise), la carrière a été reconvertie en site d’escalade, un choix qui s’est révélé pertinent puisqu’elle est devenue, par sa proximité et sa facilité d’accès (à vingt minutes de Strasbourg), le site majeur de la région (beaucoup de voisins d’outreRhin font également le déplacement). La proximité de la RN1004 (bruyante), et la (sur)fréquentation de la falaise, sont les inconvénients du site, mais voici plutôt ses atouts. Pour les sudistes habitués du calcaire, venir grimper dans le Bas-Rhin permet de se dépayser sur un rocher aux teintes magnifiques allant du rouge au noir en passant par le rose, et au grain hyper adhérent car jamais patiné. La falaise (aussi nommée Marlenheim, du nom du village voisin) offre des voies de style varié. Les difficultés s’échelonnent du 5 au 8b, avec une majorité de voies dans le septième degré. Si le spot n’est pas idéal pour faire ses premières armes, il permet aux débrouillards de progresser rapidement, et aux grimpeurs aguerris de se faire plaisir dans les niveaux plus confirmés s’élevant jusqu’aux diverses nuances du huitième degré (et même du 9a, avec l’emblématique «Aloha»). Le thème de la séance: une escalade sur prises peu abondantes et éloignées, qui nécessite de faire les mouvements, c’est tout (inutile de chercher d’autres solutions).','','2019-12-15 18:28:50','Falaise','Bas-Rhin','sud',0,72,NULL,'','','France',4,0,NULL,NULL),(7,'Le Rocher de 11 heures','La visite des falaises locales s’achève enfin par une autre curiosité géologique, et, pour nous, «grimpesque». Situé à quelque 500 mètres de dénivelé juste au-dessus du Pont du Prêtre, il faut toutefois sortir de la vallée en passant par le hameau de Haut-Siévoz pour accéder au Rocher de 11heures. Équipée en 2007 et 2008, également à la demande la municipalité de Siévoz, cette falaise est en fait constituée de deux secteurs. La «barre du bas» est classique, pourrions-nous dire, tandis que la «lame du haut» est très originale puisque totalement érigée hors du sol sur une vingtaine de mètres de haut. À son sommet, la roche fait seulement dans les deux mètres d’épaisseur, criblée, de plus, de taffoni «corsisants».',NULL,'2019-12-15 18:28:50','Falaise','Isère','sud-ouest',20,600,NULL,NULL,NULL,'France',10,0,NULL,NULL),(8,'Le Rocher du Sphinx','Le Rocher du Sphinx est une tour rocheuse de granit située sur le front de mer à Perros-Guirec, à mi-pente entre la plage de Trestraou et celle de Trestrignel. Avec sa taille modeste et sa forme torturée, il propose des voies courtes, mais d’une diversité intéressante, notamment dans le registre des mouvements à réaliser. On y trouve une belle dalle exposée à l’ouest, offrant une magnifique perspective sur la baie de Trestraou, bien agréable en fin de journée quand le soleil embrase le granit rose de Ploumanac’h. Le Rocher du Sphinx se prête surtout à une pratique tournée vers l’initiation et l’entraînement.',NULL,'2019-12-15 18:28:51','Falaise','Côtes-d\'Armor','Nord, Ouest',0,68,NULL,NULL,NULL,'France',10,0,NULL,NULL),(10,'La Clape','Située dans le département de l’Aude, à deux pas de Narbonne, émergeant des pinèdes du bord de mer, la montagne de la Clape a tout pour séduire. Cette falaise calcaire dénuée de voies extrêmes, et où même les rares représentantes du septième degré sont assez peu fréquentées, compte trois cent cinquante voies et une grosse majorité de 5+/6a. Un vrai nid à voies faciles, ce qui est particulièrement remarquable, car rarement un tel choix est offert dans ce niveau de difficulté. Les falaises ne sont pas d’une amplitude alpine, loin de là, tout juste une vingtaine de mètres, mais constituent une invitation à l’escalade-plaisir où grimpeur et assureur restent proches l’un de l’autre. On peut grimper en toutes saisons dans ce massif où l’on trouve toujours un secteur adapté aux conditions météorologiques: des falaises ensoleillées et à l’abri du vent pour l’hiver, et des parois à l’ombre pour les matinées d’été. La variété et la facilité d’accès des différents secteurs équipés en font un coin de paradis pour les amoureux de tranquillité et de belle grimpe en dilettante, le tout à côté de la mer et aux portes de Gruissan, temple de la planche à voile, dont les chalets, perchés sur pilotis, vous diront certainement quelque chose si vous avez vu un jour le film culte 37°2 le matin…',NULL,'2019-12-15 18:28:52','Falaise','Aude',NULL,0,5,NULL,NULL,NULL,'France',10,0,NULL,NULL),(15,'Bavella','Dans le massif de Bavella poussent des aiguilles de granit rouge moucheté de lichen jaune dont la base se perd dans des vallons touffus. Avec leurs parois vermoulues de tafonis délirants, elles composent un décor extravagant, avec parfois un peu de brume façon baie d’Along, ou des pins suspendus façon Japon... Dépaysement assuré. Les tours sont parcourues de voies rocheuses de plusieurs décennies d’âges, dont l’un des premiers ouvreurs fut Jean-Paul Quilici, guide de haute montagne, et de bon nombre d’itinéraires sportifs modernes sur spits ou goujons, parfois à compléter d’un jeu de friends, qui s’y sont ajoutés au fil du temps, attrayants, plus ou moins audacieux, plus ou moins engagés (plutôt plus que moins), et où il est vraiment question de grimpe. Plus tard (en 1992 pour être précis), Arnaud et François Petit ouvrent avec “Jeef“ l’un des monuments de l’escalade granitique en libre. Au même moment, une flopée de grimpeurs haut de gamme, marseillais et locaux, découvrent ce paradis de l’aventure et mettent en valeur de nouveaux secteurs, dénichant des voies inoubliables qu’ils ouvrent du bas sur un rocher excellent. Sur cette autre planète, le potentiel est si vaste que l’ouverture de nouvelles voies continue sur ces aiguilles magiques, supports de rêves à venir, ou existants, comme l’improbable “Delicatessen“, aussi invraisemblable en difficulté qu’en esthétique avec son granit rouge orné de lichen vert et grignoté d’alvéoles géantes. Les possibilités sont innombrables et le niveau très abordable. Il y a également une école d’escalade à proximité du col de Bavella avec plusieurs secteurs et une centaine de couennes de tous niveaux pour prendre l’ambiance des lieux avant de jeter son dévolu sur l’une des tours. Là, un grand choix d’itinéraires de grande ampleur, classiques et modernes, de F à TD, pour 200 m d’escalade, sur des falaises tout droit sorties d’un décor de BD de science-fiction, vous fera voyager bien plus loin que la Corse...','','2019-12-11 17:06:37','Falaise','Corse-du-Sud','',10,15,NULL,'','','France',1,0,NULL,NULL),(16,'Clemont','La falaise de Clémont est une petite jeune qui a récemment rejoint la longue liste des falaises du département grâce au travail du comité départemental FFME du Doubs. En effet, elle n’était pas vraiment passée inaperçue, mais d’anciens conflits avec les ornithologues avaient jusqu’ici entravé le développement du site. Pour éviter de reproduire ces erreurs, les maîtres-mots auront été concertation, conciliation et patience. Pour enfin aboutir à l’autorisation d’équiper une bonne partie de la falaise. Depuis le temps que les chercheurs de falaises locaux louchaient dessus! Forcément, un magnifique calcaire, une hauteur de 10 à 25 m, un beau potentiel, un cadre sympa et tranquille… Tout cela était très prometteur. Aujourd’hui, la falaise de Clémont a tenu ses promesses, et compte quasiment 80 voies, du 4c au projet non répertorié, avec une quarantaine de voies dans le 6edegré qui valent pour beaucoup le détour, et plus de 25 dans le 7e degré… Et avec son sentier d’accès et les pieds des voies bien aménagés (merci Verticool), des panneaux d’information et de fléchage, de petits galets originaux au pied de chaque voie, tout est fait pour augmenter les joies de l’escalade dans ce site qui a tout pour plaire aux amateurs de nouveauté, de beau rocher, de tranquillité et de cadres agréables.','',NULL,'Falaise','Doubs','Plein sud',0,50,NULL,NULL,NULL,'France',1,0,NULL,NULL),(18,'Kerlouan','Kerlouan, c’est cette pointe bretonne du Finistère nord, qui a comme première voisine la ville de New York. Entre Kerlouan et les Seychelles, la seule différence, c’est quelques degrés et les cocotiers en moins. Sinon, il y a ces mêmes blocs magnifiques éparpillés sur la plage, façonnés par le vent et les embruns en une profusion de lignes où vous pourrez grimper les pieds dans l’eau en compagnie des goélands. Ambiance «grand large» donc pour le bloc à Kerlouan, sur un site rythmé par le va-et-vient incessant de la mer. Ajoutez la beauté des paysages, la tranquillité du lieu, et la quantité et qualité des blocs ouverts, et voilà tout ce qui fait de ce bout de côte un peu excentré un site de bloc de grande qualité. Sur six kilomètres de rivage, des boules de granit posées partout sur le sable: plus de deux mille passages ouverts, répartis sur neuf secteurs, la plus grande concentration se trouvant sur Meneham et le secteur Crémiou, qui propose notamment un chaos à droite composé de nombreux blocs faciles, pas trop haut avec du sable en réception, histoire de prendre, en arrivant, la température du spot.','site adapté pour les enfants',NULL,'Bloc','Finistère','',0,5,NULL,NULL,NULL,'France',1,0,NULL,NULL),(21,'Le Pont du Prêtre','Deux kilomètres plus loin, juste avant Entraigues, laissons sur notre droite la zone de blocs (dont nous allons repar-ler plus loin) et dépassons le village de Valbonnais pour nous enfoncer dans la partie la plus étroite de la vallée de La Bonne. Au niveau de l’ancienne cimenterie du Pont du Prêtre, la falaise cachée éponyme nous réserve alors une belle surprise… Taillées au couteau, ces dalles de calcaire noir bleuté sont uniques, en France c’est sûr, mais peut-être même dans le monde entier !','',NULL,'Falaise','Isère','Ouest',0,22,NULL,NULL,NULL,'France',1,0,NULL,NULL),(22,'Le précipice de Corbière','Plus frais que ça dans le Vercors drômois, il n’y a pas. Et la fraîcheur, en été, c’est « le » petit plus qui fait que Corbière a rencontré et conquis rapidement son public.\r\n\r\nL’endroit a vraiment de quoi séduire. Il s’agit d’un gros bassin d’effondrement ayant laissé un pan de falaise exceptionnel à même de challenger Pierrot Beach dans la catégorie reine : « gros dévers très prisu ». Au sommet, magnifique panorama sur le plateau de Vassieux, les crêtes orientales du Vercors et leur point culminant, le Grand Veymont. Le site bénéficie d’une climatisation 100% écologique intégrée, ce qui est aussi tendance qu’utile en été. La clim est particulièrement efficace car on peut se retrouver à enfiler la doudoune pour assurer au pied des voies tout en bas, notamment les projets à droite de la voie “Shipstern“, au bien nommé secteur Congélateur, par un petit 12°C fort peu estival activement entretenu par un courant d’air frisquet en provenance de l’inframonde.\r\nVers le haut de l’éboulis, au pied des voies du secteur le plus haut, le mercure se cale vers 19-20°C, ce qui est tout à fait agréable, et il n’y a qu’en clippant le relais sommital des voies que vous retrouverez la chaleur « extérieure » (ou plutôt « supérieure », dans ce cas.) La falaise, très déversante, offre une vingtaine de belles voies dans le 7 et le 8, mais il y a aussi un secteur facile, à l’équipement très rapproché, idéal pour les enfants, avec sept voies de difficulté croissante en 4 et 5. Merci Philippe Saury ! Il y a également trois voies dans le 6e degré, pour faire un peu de tout, mais ça démarre au 6b+ avec “Dos au mur“, une voie assez déversante et physique. Pour accéder au reste des lignes, le ticket d’entrée sera dans le 7, avec une belle offre dans le 8.\r\n\r\nCôté projets, sachez que les deux dernières voies de la falaise ont été équipées l’année dernière dans le gros dévers, l’une par Nicolas Glée, l’autre par Benoit Martineau, juste à droite de “Shipstern“, du nom d’une célèbre vague de surf mutante en Tasmanie (ça donne une idée du bon 45° d’inclinaison qu’affiche la paroi). Elles ne seraient pas loin du 9, mais n’ont pas encore été enchaînées et attendent leurs premières réalisations, qui sacreront l’heureux vainqueur du “Challenge Côte d’Or“. Explications.\r\nÀ l’été 2018, Nicolas Glée avait équipé la voie “E-Space Challenge“, en forme de défi : le premier qui enchaînait la voie gagnait 1000 euros de prises de la marque E-Space. Ce fut Sebastian Halenke. Les deux derniers projets faisant l’objet d’un désaccord profond, ils ont inspiré un nouveau challenge. Cette fois, Nico parie dix tablettes de chocolat que c’est du 9. Benoit parie dix tablettes de chocolat que ce n’est pas du 9. Peut-être que s’ils avaient mis en jeu 1000 euros de chocolat, on aurait déjà la réponse, mais si le challenge vous tente et que vous aimez le chocolat et les paris décalés, c’est l’été où jamais !','',NULL,'Falaise','Drôme','Ouest-Est',15,34,NULL,NULL,NULL,'France',1,0,NULL,NULL),(23,'Le Salève','Le Salève est peut-être la plus suisse des falaises françaises, au point qu’elle est parfois appelée « la montagne des Genevois». Elle offre de grandes envolées de tous niveaux au-dessus de Genève pour une belle journée d’escalade suivie d’un after rafraîchissant au bord du lac Léman. Et en cas de canicule, certaines voies sauront vous « refroidir » sans même avoir besoin du lac! Les grandes voies de la face ouest du Salève procurent, en plus d’émotions inoubliables, un voyage sensationnel à travers l’histoire de l’escalade. Hautes en caractère, porteuses des valeurs de la vraie grimpe engagée, elles ont vu passer de grands noms de l’escalade venus s’y tirer la bourre dans les premiers enchaînements extrêmes de l’époque (des Antoine Le Menestrel, Didier Raboutou et consorts…) et des graines de champions devenus grands comme Elie Chevieux… Démodé aux yeux de certains, le Salève reste un formidable laboratoire du geste: par son engagement et sa texture, on y apprend mieux que nulle part ailleurs à poser ses pieds précisément et calmement, bien au-dessus du spit. Cette escalade engagée, cette falaise qui a su rester étonnamment sauvage malgré sa proximité avec la ville, c’est ça, «l’esprit Salève».','',NULL,'Bloc, Falaise','Haute-Savoie','',7,480,450,'3a','8a','France',1,0,NULL,NULL),(26,'Le Thaurac','Le Thaurac n’est pas une falaise, mais plusieurs, peuplées de multiples secteurs et de belles lignes au parfum de calcaire et de rue fétide, aux couleurs de caillou compact et de chênes verts. Des couleurs et des goûts qui, en plus d’être tous dans la nature, conviendront à tout le monde et à toutes les saisons, avec pas moins de six cents voies (dont la moitié dans le 6 !) réparties sur une trentaine de secteurs. Couennes ou grandes voies (de deux à quatre longueurs), ombre ou soleil, vieux pitons rouillés ou plaquettes rutilantes, rails de spits ou grands trous d’air, joli gris ou crépis jaune, voire même quelques colonnettes ici et là, le tout en interconnexion car on peut aisément circuler d’un secteur à l’autre, à condition de ne pas perdre les sentiers... pas facile de résumer le Thaurac. Secteur après secteur, décennie après décennie (en commençant dans les années 1960 avec les frères Dainat), broches après pitons, extrême après facile, chacun y est allé de son inspiration : Fabien Roumanille, Bernard Houles, Jean-Luc Fabre, Patrick Pagès, Jean-Baptiste Poirier, Daniel Joulé, Pierre Rouzo, Jérôme Rochelle, Didier Franco, Laurent Triay, Arno Catzeflis et bien d’autres ont, chacun à leur tour, ajouté leur coup de perfo à cette grande oeuvre collective.','','2019-12-10 20:45:26','Falaise','Hérault','Ouest,Sud-Ouest',5,15,400,'4b','8b','France',1,0,NULL,NULL),(34,'Gorges de L\'Ardèche','Les Gorges de l’Ardèche évoquent, pour le quidam, les canoë jaunes et leurs embouteillages estivaux, la baignade, les guinguettes et les campings, et les cars promenant touristes et troisième âge de belvédère en point de vue... Pour les grimpeurs, ce nom s’associe irrémédiablement à une flopée de sites d’escalades célèbres qui dictaient la mode et les tendances de l’activité grimpe en plein développement à la fin des années 1980. À l’époque, l’Ardèche avait mis le paquet sur l’équipement des falaises et la signalétique sur les parkings. De quoi être dans le mouv’ un petit moment. Et le résultat était là : Chaulet, Mazet, les Actinidias, Casteljau... tout cela vous parle ? Il y en a eu, des hordes de grimpeurs venus user ces voies et s’imprégner de l’identité groupusculaire de la communauté grimpante d’alors. À l’époque, on ne jurait que par l’escalade exigeante sur petites prises, à l’équipement parfois éloigné, et sur du rocher qui n’aurait pas démérité chez Mondial Carrelage. Mais tout passe, tout lasse, comme dirait l’autre, et voilà que tout cela ne fait plus rêver. Les anciens sites ont beau rester sympas, ils ne peuvent se défaire d’un petit côté patiné, démodé, limite ringard. Et faute de renouvellement, l’escalade dans les gorges s’est un peu endormie sous ses... chênes verts, d’autant que le développement de nouveaux secteurs a été freiné par de nombreuses réglementations dues à la création de la Réserve naturelle des Gorges et autres Natura 2000 dans les alentours. Alors aujourd’hui, si on veut grimper dans les Gorges de l’Ardèche sans forcément faire une visite de musée (parce que c’est bien beau la culture grimpe, mais les musées c’est ennuyeux), est-ce que c’est possible ? Est-ce qu’il y a autre chose que les canoës, la dalle et la technique ?Justement, oui. Et à foison, en plus. L’escalade moderne ne demandait qu’à faire sa place, et des nouveaux sites ont vu le jour dans les années 1990, comme la grotte des Branches à Vallon Pont d’Arc, berceau des premiers 8b+ et 8c d’Ardèche, avec des voies de haut niveau sur concrétions, assez conceptuelles à l’époque. Le vrai renouveau de l’Ardèche se joue dans des secteurs incroyables comme la Salpêtrière ou le Pilier d’Autridge, pour ses grandes voies, ouvertes entre fin 1990 et début 2000, mais surtout pour le secteur de couennes de sa baume. Colonnettes, grandes longueurs, cotations un tantinet austères, un soupçon d’engagement, et en arrière plan, la rivière au centre des gorges... Les gorges de l’Ardèche sont plus modernes que jamais.','','2019-12-13 19:42:04','Falaise','Ardèche','Sud',30,50,50,'7a','8b+','France',4,0,NULL,NULL),(35,'Autoire','Un quart des voies d’escalade dans le Lot se trouve à Autoire. Cela devrait suffire pour parler d’un site majeur, avec 271 lignes tracées sur un superbe calcaire, et pas mal de nouveautés venues enrichir et redynamiser les lieux au cours de la dernière décennie (après une longue période de léthargie). À site majeur, cadre majeur, avec la cascade de trente mètres qui tombe au fond de la gorge entaillant le causse, et le vieux village d’Autoire, avec ses belles pierres, sorti tout droit et intact de l’époque médiévale... Si ça c’est pas du décor de cinéma ! C’est donc dans ce décor que se déroule, émergeant des chênes, longue d’un kilomètre, la barre rocheuse d’Autoire. Elle démarre sous le belvédère de Siran, par lequel se fait l’accès, et tous les secteurs se succèdent, à mi-hauteur, avec un accès aisé de barre en barre. Le rocher prend plus ou moins de dévers, change de couleur et de forme, au gré de l’inspiration des éléments et de l’usure du temps. Grimpeurs ou non, tant d’esthétique fait forcément vibrer une corde sensible. Pour l’escalade, le premier à avoir vibré est Patrick Moissinac, qui, en 1983, a équipé les premières voies au secteur Bolivaria, le plus court. Le site est donc suffisamment âgé pour avoir connu la grande époque du fluo et des imprimés flashy et improbables, mais il est toujours resté au goût du jour et en phase avec son époque (heureusement, car celle des années 80 était assez fatigante pour les yeux), sans bien sûr passer à côté de l’actuel engouement pour les colonnes et les jolies voies dures en dévers. Le tout dans une région assez tranquille et un brin sauvage, ce qui garantit la quasi-absence de patine sur les voies. En contrepartie, pour les soirées qui déchirent et les after animés, ce n’est pas... majeur.','','2019-12-15 11:24:00','Falaise','Lot','Est,Sud-Est',5,25,271,'5a','8b','France',10,0,5,NULL),(36,'Test ajout','sdfdsf','','2019-12-15 23:43:57','Falaise','','',NULL,NULL,NULL,'','','France',1,0,NULL,NULL);
/*!40000 ALTER TABLE `spot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(60) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT 0,
  `pseudo` varchar(45) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `checkedCGU` tinyint(1) NOT NULL DEFAULT 0,
  `phone` varchar(10) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `postal_code` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Marc','Laplace','mlaplace@maboite.fr',1,'marc788','$2a$10$us7hkuKXt2GEOknzEcavROCCo80UgcN6WKMDXKgTI4KC.vq5I8QNa',1,'','2019-12-09 17:48:29','',''),(4,'Kim','Bauer','kbauer@test.com',1,'kim65','$2a$10$N8ufUp2.Vu.tTPxPUXuOU.9C3F7YGFRhoIQEyiNFlK6kZYd4A2DZ2',1,'','2019-12-09 18:48:30','',''),(10,'David','Palmer','dpalmer@test.com',1,'david322','$2a$10$I5.6ytTo5IBQ6gRB5IeqDutu1CIG0/d3uAFdChI8vbcYvaq7uQ6g6',1,'','2019-12-12 17:48:32','',''),(125,'Julie','Birc','jbirc@test.com',1,'','$2a$10$maqAeRaHNN6NJ6N5XHMAoOufmtLPbAIz.U4JM6BjjMQO/lpEm5KDC',1,'','2019-12-15 09:18:22','','');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `fk_userrole_user` (`user_id`),
  CONSTRAINT `fk_userole_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_userrole_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(1,4),(1,10),(2,125);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-16 13:26:21
