-- MySQL dump 10.13  Distrib 5.6.17, for osx10.6 (i386)
--
-- Host: localhost    Database: improvonline
-- ------------------------------------------------------
-- Server version	5.6.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Chatrooms`
--

DROP TABLE IF EXISTS `Chatrooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Chatrooms` (
  `idChatrooms` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(200) NOT NULL,
  `hasGameStarted` tinyint(1) DEFAULT '0',
  `CurrentGame` varchar(45) DEFAULT NULL,
  `maxPlayers` int(11) DEFAULT NULL,
  `maxSpectators` int(11) DEFAULT NULL,
  `starttime` datetime DEFAULT NULL,
  `prompt` varchar(100) DEFAULT NULL,
  `gameTime` varchar(45) DEFAULT '1',
  `usernamewithturn` varchar(45) DEFAULT NULL,
  `currentLetter` varchar(1) DEFAULT 'A',
  PRIMARY KEY (`idChatrooms`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Chatrooms`
--

LOCK TABLES `Chatrooms` WRITE;
/*!40000 ALTER TABLE `Chatrooms` DISABLE KEYS */;
INSERT INTO `Chatrooms` VALUES (1,'TalkativeTurtles','Turtles don\'t like talking to the hand, because they don\'t have hands.\nPrettyPrincesses=OMG LOL IKR LMFAO IDC OMG!!!! XOXOXO!!!!!',0,'Cards',20,50,'2015-04-23 01:49:03','Everyone has superpowers','1','kate','A'),(2,'PrettyPrincesses','OMG LOL IKR LMFAO IDC OMG!!!! XOXOXO!!!!!',0,'Cards',20,50,'2015-04-22 22:11:05','Failed Oscar categories','1','kate','T'),(3,'SleepySloths','Zzzzz..zzzz....huh what?',0,'Alphabet Game',20,50,'2015-04-22 22:17:09','He is watching','1','kate','G'),(4,'BouncyBurritos','Bounce salsa bounce bounce beanz bounce bounce BOUNCE!',0,'Word Association',20,50,'2015-04-22 19:55:11','College is all about cliques','1','kate','A'),(5,'FancyFish','Who dat? Who dat? F-I-S-H!',0,'Yes, And',20,50,NULL,NULL,'1',NULL,NULL),(6,'General','Fun fun fun.\nFun fun fun.',0,'Yes, And',20,50,NULL,NULL,'1',NULL,NULL);
/*!40000 ALTER TABLE `Chatrooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Prompts`
--

DROP TABLE IF EXISTS `Prompts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Prompts` (
  `idPrompts` int(11) NOT NULL,
  `Prompt` varchar(100) NOT NULL,
  PRIMARY KEY (`idPrompts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Prompts`
--

LOCK TABLES `Prompts` WRITE;
/*!40000 ALTER TABLE `Prompts` DISABLE KEYS */;
INSERT INTO `Prompts` VALUES (1,'Oompa Loompas'),(2,'Soldiers trying to impress a chimp'),(3,'A teenager is trying to eat a chair'),(4,'Failed spit-takes'),(5,'Miserable marching band camp stories'),(6,'Failed Oscar categories'),(7,'Girly sleepover'),(8,'Makeover gone wrong'),(9,'Old people trying to understand the internet'),(10,'Facebook is down'),(11,'Hipsters complaining about things'),(12,'Punny campaign slogans'),(13,'Banned farming techniques'),(14,'Chess game gone wrong'),(15,'Giant ants running after you'),(16,'You are turned into a bug'),(17,'Everyone is a fairytale character'),(18,'A man is mugged in front of you'),(19,'Your teacher is absent'),(20,'You are at a superhero convention'),(21,'The chestnuts are not roasting on an open fire'),(22,'Mistletoe between two people'),(23,'You are all millionaires'),(24,'Sunscreen is not enough apparently'),(25,'Your identity is stolen'),(26,'The police wont help'),(27,'Someones car breaks down'),(28,'There is a Mormon outside your door'),(29,'At a science convention'),(30,'Proudly show off your new invention'),(31,'The forest is not so green'),(32,'Carnegie Hall is your destination'),(33,'You are pulled over'),(34,'The hitchhiker looks cute'),(35,'Paparazzi is chasing a little girl'),(36,'The pizza is late'),(37,'You want pizza'),(38,'Your roommate called this morning'),(39,'Only one of you can go to outer space'),(40,'Mental facility is broken into'),(41,'Spongebob is real'),(42,'Disney World is closed forever'),(43,'One of you is just named President of the World'),(44,'The color orange isnt real'),(45,'You are color blind'),(46,'Your dog runs away'),(47,'A cat lady knocks on the door'),(48,'The wax figure suddenly moves'),(49,'Zayn left One Direction - you are devastated'),(50,'Simon Cowell saysyes'),(51,'Elvis is alive'),(52,'The Asian is following you'),(53,'A serial killer is amongst us'),(54,'You found the cure'),(55,'Vampires roam the plains'),(56,'You all live in a small cabin in the woods'),(57,'Dysfunctional family stars in a Disney movie'),(58,'The piano breaks'),(59,'Goldfish died this morning'),(60,'You run out of Poptarts'),(61,'Its trending on Twitter'),(62,'The rain makes pretty sounds'),(63,'Your band gets signed'),(64,'The beach is crowded'),(65,'This waiter has a long beard'),(66,'Your milk tea tastes funny'),(67,'There is a new circle of Hell'),(68,'The McDonalds drive-thru'),(69,'Kiddie Meals dont exist anymore'),(70,'Elsa is now frozen'),(71,'Mad tattoo artist on a rampage'),(72,'Hide your kids'),(73,'Its too late to apologize'),(74,'Take me to church'),(75,'You have a long list of Starbucks lovers'),(76,'You have a blank space'),(77,'No right swipes on Tinder'),(78,'One last time'),(79,'Someone has to hibernate for 1000 years'),(80,'I knew you were trouble when you skipped in'),(81,'A little old man said Geronimo'),(82,'Daddy issues'),(83,'Sequins before noon'),(84,'College is all about cliques'),(85,'Anna did not want to build a snowman'),(86,'Reindeers may not be better than people'),(87,'Everytime it rains'),(88,'Lights would not guide you home'),(89,'The mad scientist has a jar of hearts'),(90,'Big mean men crying'),(91,'You are turning into titanium'),(92,'Everyone develops a sixth sense'),(93,'Dreams are a wish your heart makes'),(94,'Somewhere only your mom knows about'),(95,'You run out of gas'),(96,'You fall out of bed'),(97,'There is no milk left'),(98,'Hogwarts gets wifi access'),(99,'A sweaty pianist left his burger on your porch'),(100,'Everyone is a supernatural creature'),(101,'Everyone has superpowers'),(102,'Everyone is at a funeral for a Smurf'),(103,'A tour guide is held hostage by a goat'),(104,'Yoga class turned interesting'),(105,'Things happen in alleys'),(106,'A classy farmers conference'),(107,'You are in a deserted island'),(108,'The parade is coming'),(109,'Grandma wont stop'),(110,'Your house turns into a zoo'),(111,'Senior Prom turns out awful'),(112,'An attractive Jedi walks in the room'),(113,'Your tattoo means something'),(114,'A random QR code appears'),(115,'You won 10,000 dollars'),(116,'Gordon Ramsay just shouted at your group'),(117,'The janitor gives you a glare'),(118,'Fight against corruption'),(119,'Cupid sucks at archery'),(120,'A bird delivers something to your window'),(121,'He is watching'),(122,'Monopoly ruins your life'),(123,'The turkey is raw'),(124,'The concert is sold out'),(125,'The alarm rings'),(126,'Her skin looks green'),(127,'You dont see the iceberg'),(128,'Lettuce makes you sick');
/*!40000 ALTER TABLE `Prompts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat` (
  `start_time` datetime DEFAULT NULL,
  `idChat` int(11) NOT NULL,
  `prompt` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guests`
--

DROP TABLE IF EXISTS `guests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guests` (
  `guestid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`guestid`),
  UNIQUE KEY `guestid_UNIQUE` (`guestid`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1011 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guests`
--

LOCK TABLES `guests` WRITE;
/*!40000 ALTER TABLE `guests` DISABLE KEYS */;
INSERT INTO `guests` VALUES (372,'Guest101175'),(589,'Guest10165'),(295,'Guest102713'),(809,'Guest103506'),(350,'Guest103927'),(477,'Guest105140'),(969,'Guest10695'),(700,'Guest106990'),(598,'Guest107522'),(993,'Guest108035'),(658,'Guest108143'),(567,'Guest108357'),(814,'Guest109595'),(958,'Guest111645'),(824,'Guest112091'),(591,'Guest112192'),(737,'Guest119370'),(844,'Guest123352'),(548,'Guest123631'),(653,'Guest123961'),(921,'Guest125225'),(926,'Guest133097'),(613,'Guest136807'),(569,'Guest13943'),(705,'Guest144989'),(318,'Guest147655'),(766,'Guest148563'),(915,'Guest149516'),(970,'Guest151729'),(530,'Guest152436'),(359,'Guest153126'),(679,'Guest155263'),(444,'Guest156617'),(887,'Guest158121'),(1008,'Guest159394'),(427,'Guest160064'),(923,'Guest160635'),(719,'Guest162471'),(449,'Guest16335'),(756,'Guest163371'),(821,'Guest164904'),(848,'Guest164923'),(285,'Guest166168'),(485,'Guest166224'),(606,'Guest170031'),(712,'Guest170250'),(868,'Guest172041'),(660,'Guest173249'),(976,'Guest176133'),(1003,'Guest177137'),(563,'Guest177201'),(974,'Guest183864'),(986,'Guest184043'),(982,'Guest18549'),(1007,'Guest186053'),(832,'Guest187585'),(819,'Guest189457'),(529,'Guest192390'),(775,'Guest192911'),(831,'Guest193813'),(500,'Guest19412'),(507,'Guest194249'),(990,'Guest194668'),(780,'Guest195094'),(474,'Guest197029'),(698,'Guest197399'),(681,'Guest200181'),(279,'Guest200337'),(498,'Guest201721'),(903,'Guest203621'),(424,'Guest204174'),(282,'Guest204621'),(841,'Guest205217'),(734,'Guest205383'),(539,'Guest208493'),(450,'Guest208798'),(597,'Guest209363'),(560,'Guest210026'),(593,'Guest21080'),(702,'Guest211233'),(812,'Guest21161'),(317,'Guest212956'),(434,'Guest213123'),(853,'Guest213914'),(918,'Guest213955'),(684,'Guest214248'),(407,'Guest215199'),(840,'Guest216239'),(757,'Guest216290'),(876,'Guest216316'),(972,'Guest217220'),(678,'Guest219877'),(270,'Guest220269'),(349,'Guest220754'),(805,'Guest220799'),(947,'Guest222180'),(910,'Guest223058'),(406,'Guest223618'),(670,'Guest227042'),(398,'Guest229060'),(949,'Guest231656'),(431,'Guest232403'),(787,'Guest233065'),(826,'Guest237535'),(1009,'Guest239290'),(779,'Guest24062'),(439,'Guest240966'),(368,'Guest241483'),(690,'Guest244007'),(743,'Guest245690'),(409,'Guest246710'),(358,'Guest247463'),(806,'Guest249151'),(422,'Guest249988'),(376,'Guest253331'),(552,'Guest253891'),(994,'Guest254047'),(703,'Guest255065'),(610,'Guest25676'),(748,'Guest2570'),(473,'Guest257299'),(960,'Guest258369'),(664,'Guest259141'),(628,'Guest259382'),(381,'Guest259582'),(397,'Guest260311'),(992,'Guest260716'),(998,'Guest261240'),(911,'Guest262502'),(542,'Guest264486'),(773,'Guest266217'),(501,'Guest266897'),(355,'Guest268221'),(647,'Guest268673'),(467,'Guest271164'),(348,'Guest271259'),(336,'Guest272738'),(392,'Guest273380'),(421,'Guest275859'),(465,'Guest27599'),(765,'Guest278750'),(442,'Guest278766'),(769,'Guest282700'),(867,'Guest283350'),(506,'Guest283678'),(940,'Guest283971'),(691,'Guest284916'),(784,'Guest287963'),(902,'Guest288269'),(683,'Guest288668'),(778,'Guest289051'),(894,'Guest290432'),(673,'Guest292317'),(952,'Guest297548'),(881,'Guest300200'),(896,'Guest301068'),(869,'Guest303056'),(457,'Guest304638'),(663,'Guest306471'),(608,'Guest307593'),(791,'Guest311374'),(655,'Guest311379'),(642,'Guest312369'),(418,'Guest313442'),(481,'Guest314684'),(760,'Guest315112'),(697,'Guest315529'),(930,'Guest315559'),(438,'Guest315956'),(847,'Guest317047'),(942,'Guest317571'),(489,'Guest318165'),(352,'Guest320903'),(862,'Guest321885'),(307,'Guest322050'),(886,'Guest323066'),(615,'Guest323339'),(588,'Guest323738'),(816,'Guest323915'),(934,'Guest325568'),(667,'Guest326569'),(716,'Guest329359'),(555,'Guest329651'),(541,'Guest332827'),(423,'Guest333956'),(547,'Guest33454'),(377,'Guest334696'),(556,'Guest335899'),(879,'Guest335938'),(327,'Guest336475'),(961,'Guest338566'),(730,'Guest342542'),(508,'Guest343222'),(997,'Guest343631'),(322,'Guest344017'),(583,'Guest345270'),(277,'Guest346844'),(948,'Guest346954'),(714,'Guest349745'),(1000,'Guest350230'),(634,'Guest35369'),(466,'Guest354223'),(823,'Guest354568'),(378,'Guest356577'),(584,'Guest361318'),(835,'Guest362642'),(488,'Guest363839'),(492,'Guest364533'),(758,'Guest364795'),(496,'Guest366393'),(561,'Guest366750'),(314,'Guest370320'),(313,'Guest375168'),(735,'Guest375362'),(526,'Guest376631'),(854,'Guest377117'),(932,'Guest377698'),(479,'Guest378048'),(781,'Guest37811'),(399,'Guest380402'),(693,'Guest381048'),(404,'Guest381668'),(261,'Guest38303'),(901,'Guest383489'),(490,'Guest384778'),(616,'Guest384852'),(822,'Guest385008'),(920,'Guest387341'),(574,'Guest387485'),(357,'Guest388907'),(695,'Guest389212'),(416,'Guest389828'),(659,'Guest3918'),(480,'Guest394531'),(592,'Guest399699'),(1010,'Guest404632'),(396,'Guest40563'),(510,'Guest405764'),(973,'Guest406355'),(580,'Guest407526'),(331,'Guest408058'),(566,'Guest410187'),(710,'Guest410868'),(717,'Guest413589'),(576,'Guest414672'),(334,'Guest415370'),(581,'Guest415718'),(499,'Guest416072'),(971,'Guest41644'),(387,'Guest419591'),(713,'Guest420482'),(379,'Guest420601'),(345,'Guest421153'),(420,'Guest421616'),(721,'Guest422244'),(448,'Guest42245'),(837,'Guest422594'),(984,'Guest424294'),(443,'Guest425048'),(975,'Guest426605'),(662,'Guest428144'),(829,'Guest429058'),(808,'Guest429398'),(441,'Guest430803'),(269,'Guest431440'),(553,'Guest435532'),(475,'Guest436762'),(447,'Guest438709'),(503,'Guest438997'),(551,'Guest439321'),(638,'Guest440271'),(364,'Guest440393'),(722,'Guest444582'),(865,'Guest445024'),(512,'Guest446110'),(872,'Guest44641'),(946,'Guest447875'),(428,'Guest44810'),(685,'Guest453037'),(298,'Guest454388'),(856,'Guest454893'),(729,'Guest457569'),(676,'Guest457697'),(696,'Guest458800'),(639,'Guest46529'),(929,'Guest467725'),(706,'Guest468592'),(785,'Guest471637'),(797,'Guest47206'),(786,'Guest47244'),(677,'Guest476336'),(626,'Guest478568'),(342,'Guest478667'),(979,'Guest478710'),(941,'Guest479913'),(897,'Guest481254'),(525,'Guest482301'),(999,'Guest484656'),(742,'Guest485766'),(328,'Guest486947'),(400,'Guest488882'),(966,'Guest489235'),(319,'Guest490347'),(527,'Guest490529'),(744,'Guest494668'),(996,'Guest494905'),(452,'Guest496017'),(493,'Guest502316'),(882,'Guest504876'),(977,'Guest508865'),(533,'Guest513701'),(522,'Guest51396'),(978,'Guest514205'),(460,'Guest517444'),(672,'Guest518968'),(855,'Guest520212'),(453,'Guest52140'),(361,'Guest522995'),(795,'Guest523168'),(609,'Guest52402'),(603,'Guest524838'),(861,'Guest525601'),(763,'Guest527827'),(842,'Guest529100'),(511,'Guest529138'),(709,'Guest529178'),(817,'Guest530030'),(701,'Guest531585'),(513,'Guest531830'),(311,'Guest535250'),(630,'Guest538316'),(858,'Guest538921'),(919,'Guest539832'),(733,'Guest540220'),(878,'Guest540499'),(944,'Guest541286'),(263,'Guest544097'),(594,'Guest545123'),(401,'Guest547429'),(607,'Guest547441'),(776,'Guest550981'),(645,'Guest552208'),(981,'Guest552395'),(799,'Guest554932'),(625,'Guest555014'),(264,'Guest556092'),(557,'Guest556647'),(764,'Guest558586'),(329,'Guest559176'),(476,'Guest559968'),(914,'Guest5623'),(957,'Guest564503'),(582,'Guest565922'),(619,'Guest568154'),(636,'Guest570025'),(486,'Guest57317'),(266,'Guest575274'),(635,'Guest576718'),(620,'Guest576916'),(347,'Guest577648'),(749,'Guest578711'),(909,'Guest580328'),(640,'Guest581439'),(732,'Guest584071'),(519,'Guest585017'),(870,'Guest586147'),(417,'Guest586320'),(543,'Guest587953'),(674,'Guest58914'),(753,'Guest592304'),(644,'Guest593328'),(959,'Guest593920'),(494,'Guest596187'),(554,'Guest596338'),(323,'Guest60057'),(873,'Guest600861'),(436,'Guest601262'),(680,'Guest602287'),(955,'Guest609268'),(657,'Guest60989'),(689,'Guest613861'),(617,'Guest61513'),(900,'Guest615619'),(860,'Guest616583'),(487,'Guest618745'),(623,'Guest619782'),(711,'Guest620261'),(528,'Guest620559'),(595,'Guest620776'),(455,'Guest622360'),(767,'Guest622538'),(363,'Guest622704'),(320,'Guest626082'),(272,'Guest626234'),(524,'Guest629302'),(922,'Guest629440'),(366,'Guest629648'),(585,'Guest629870'),(925,'Guest630168'),(927,'Guest631776'),(413,'Guest633305'),(302,'Guest634623'),(954,'Guest634803'),(265,'Guest635937'),(1004,'Guest636705'),(772,'Guest638146'),(937,'Guest639046'),(534,'Guest639499'),(491,'Guest640780'),(470,'Guest642433'),(540,'Guest642978'),(571,'Guest643312'),(315,'Guest644541'),(532,'Guest645556'),(863,'Guest645993'),(559,'Guest646679'),(353,'Guest646863'),(651,'Guest647879'),(839,'Guest650427'),(521,'Guest651407'),(750,'Guest654002'),(917,'Guest6553'),(980,'Guest65537'),(612,'Guest656114'),(895,'Guest656132'),(928,'Guest656412'),(849,'Guest66188'),(671,'Guest661933'),(746,'Guest663848'),(509,'Guest66438'),(458,'Guest664490'),(275,'Guest664672'),(356,'Guest665032'),(694,'Guest66821'),(627,'Guest677900'),(783,'Guest678289'),(621,'Guest679453'),(326,'Guest680468'),(762,'Guest681734'),(890,'Guest682159'),(339,'Guest682469'),(728,'Guest682876'),(834,'Guest685590'),(782,'Guest687099'),(1001,'Guest688167'),(482,'Guest691436'),(564,'Guest691943'),(1002,'Guest692402'),(893,'Guest698377'),(951,'Guest698688'),(815,'Guest699411'),(989,'Guest699441'),(813,'Guest699658'),(395,'Guest700840'),(945,'Guest701382'),(936,'Guest702480'),(495,'Guest70303'),(688,'Guest703503'),(602,'Guest704133'),(877,'Guest70543'),(403,'Guest707041'),(537,'Guest707539'),(739,'Guest708054'),(575,'Guest710744'),(988,'Guest714532'),(761,'Guest716682'),(724,'Guest716972'),(573,'Guest719979'),(316,'Guest722720'),(935,'Guest723912'),(562,'Guest72409'),(340,'Guest725819'),(846,'Guest727013'),(725,'Guest727413'),(1005,'Guest727713'),(864,'Guest727811'),(964,'Guest729488'),(523,'Guest735387'),(908,'Guest735537'),(622,'Guest737788'),(520,'Guest738282'),(656,'Guest738338'),(267,'Guest739740'),(360,'Guest74149'),(276,'Guest741884'),(871,'Guest74511'),(875,'Guest747521'),(461,'Guest747556'),(727,'Guest749218'),(924,'Guest749513'),(579,'Guest750156'),(794,'Guest752829'),(375,'Guest755298'),(774,'Guest756170'),(545,'Guest756862'),(956,'Guest757875'),(830,'Guest758403'),(391,'Guest759135'),(600,'Guest759151'),(707,'Guest759672'),(741,'Guest759819'),(483,'Guest762480'),(346,'Guest762900'),(699,'Guest763255'),(517,'Guest764313'),(800,'Guest764456'),(271,'Guest764474'),(631,'Guest765515'),(686,'Guest765841'),(723,'Guest768263'),(596,'Guest770181'),(796,'Guest771318'),(736,'Guest773258'),(692,'Guest775503'),(740,'Guest777976'),(962,'Guest778742'),(383,'Guest782494'),(987,'Guest783622'),(715,'Guest784742'),(666,'Guest785495'),(604,'Guest786621'),(747,'Guest787342'),(687,'Guest787898'),(515,'Guest788301'),(648,'Guest792094'),(818,'Guest792185'),(777,'Guest79242'),(633,'Guest797301'),(939,'Guest801657'),(931,'Guest802122'),(456,'Guest807124'),(558,'Guest809017'),(324,'Guest80961'),(965,'Guest809895'),(410,'Guest810970'),(983,'Guest811184'),(889,'Guest811934'),(341,'Guest812740'),(535,'Guest81307'),(565,'Guest814625'),(857,'Guest814787'),(798,'Guest816208'),(665,'Guest816730'),(802,'Guest817155'),(568,'Guest819605'),(1006,'Guest81979'),(650,'Guest821412'),(820,'Guest821837'),(299,'Guest821846'),(943,'Guest823692'),(793,'Guest824275'),(833,'Guest824678'),(374,'Guest824928'),(497,'Guest825081'),(850,'Guest825385'),(365,'Guest82598'),(412,'Guest826678'),(599,'Guest827587'),(801,'Guest829886'),(649,'Guest831861'),(963,'Guest831949'),(577,'Guest832440'),(578,'Guest83457'),(771,'Guest835106'),(462,'Guest835398'),(950,'Guest835679'),(268,'Guest838557'),(874,'Guest838823'),(262,'Guest842192'),(325,'Guest84367'),(885,'Guest844285'),(430,'Guest844522'),(590,'Guest844571'),(433,'Guest845682'),(652,'Guest847536'),(367,'Guest850201'),(518,'Guest851108'),(891,'Guest852437'),(646,'Guest853606'),(408,'Guest856335'),(550,'Guest856955'),(807,'Guest858435'),(792,'Guest859808'),(637,'Guest860160'),(310,'Guest860695'),(843,'Guest860783'),(388,'Guest861010'),(394,'Guest862064'),(938,'Guest862854'),(810,'Guest869952'),(531,'Guest874348'),(505,'Guest875585'),(454,'Guest875798'),(643,'Guest879033'),(898,'Guest879748'),(468,'Guest880115'),(859,'Guest88012'),(907,'Guest880217'),(611,'Guest880870'),(437,'Guest882150'),(953,'Guest882227'),(425,'Guest883707'),(811,'Guest883999'),(828,'Guest885703'),(825,'Guest88813'),(415,'Guest888871'),(720,'Guest89363'),(393,'Guest894447'),(549,'Guest894666'),(351,'Guest897450'),(884,'Guest897826'),(912,'Guest898449'),(718,'Guest898849'),(731,'Guest900769'),(451,'Guest901077'),(514,'Guest903600'),(669,'Guest90578'),(852,'Guest906568'),(370,'Guest907148'),(682,'Guest907597'),(790,'Guest908949'),(432,'Guest909126'),(836,'Guest90932'),(904,'Guest909395'),(827,'Guest910579'),(738,'Guest911005'),(618,'Guest911836'),(546,'Guest912293'),(344,'Guest912713'),(632,'Guest913149'),(654,'Guest914268'),(704,'Guest917839'),(570,'Guest919168'),(614,'Guest920114'),(851,'Guest920625'),(572,'Guest921544'),(788,'Guest924542'),(866,'Guest925075'),(675,'Guest928286'),(933,'Guest931152'),(726,'Guest932277'),(296,'Guest935966'),(708,'Guest935977'),(435,'Guest936474'),(382,'Guest93699'),(516,'Guest937148'),(789,'Guest93738'),(536,'Guest937472'),(544,'Guest937746'),(804,'Guest938996'),(967,'Guest940231'),(991,'Guest940262'),(321,'Guest941245'),(985,'Guest94447'),(880,'Guest944842'),(668,'Guest945596'),(888,'Guest947400'),(803,'Guest948770'),(754,'Guest950232'),(586,'Guest950620'),(371,'Guest950699'),(502,'Guest95176'),(906,'Guest952487'),(899,'Guest953169'),(916,'Guest953718'),(419,'Guest957151'),(624,'Guest957257'),(601,'Guest957352'),(913,'Guest958398'),(661,'Guest961326'),(838,'Guest962406'),(354,'Guest963756'),(373,'Guest964058'),(605,'Guest964432'),(770,'Guest966027'),(892,'Guest966660'),(538,'Guest967164'),(484,'Guest969315'),(464,'Guest969505'),(752,'Guest973405'),(414,'Guest974540'),(478,'Guest975685'),(290,'Guest976983'),(745,'Guest98048'),(768,'Guest981115'),(504,'Guest982614'),(429,'Guest985152'),(845,'Guest985701'),(641,'Guest986283'),(883,'Guest986952'),(968,'Guest992044'),(446,'Guest993752'),(759,'Guest993784'),(995,'Guest993979'),(629,'Guest994516'),(905,'Guest996116'),(751,'Guest996446'),(755,'Guest996915'),(587,'Guest999316');
/*!40000 ALTER TABLE `guests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `chat_id` int(11) NOT NULL DEFAULT '0',
  `user_name` varchar(64) DEFAULT NULL,
  `message` text,
  `post_time` datetime DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (126,4,'kate','dasdas','2015-04-22 19:54:29'),(127,4,'kate','hi','2015-04-22 19:54:38'),(128,4,'kate','didnt get that','2015-04-22 19:54:47'),(129,4,'kate','hi','2015-04-22 19:55:08'),(130,4,'kate','don\'t you dare','2015-04-22 19:55:18'),(152,2,'kate','dsa','2015-04-22 22:11:14'),(163,3,'kate','a','2015-04-22 22:17:12'),(164,3,'kate','b','2015-04-22 22:17:14'),(165,3,'kate','c','2015-04-22 22:17:17'),(166,3,'kate','d','2015-04-22 22:17:20'),(167,3,'kate','e','2015-04-22 22:17:22'),(168,3,'kate','f','2015-04-22 22:17:25');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playing`
--

DROP TABLE IF EXISTS `playing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playing` (
  `idplaying` int(11) NOT NULL AUTO_INCREMENT,
  `idChatroom` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `isTurn` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idplaying`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playing`
--

LOCK TABLES `playing` WRITE;
/*!40000 ALTER TABLE `playing` DISABLE KEYS */;
/*!40000 ALTER TABLE `playing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spectating`
--

DROP TABLE IF EXISTS `spectating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spectating` (
  `idspectating` int(11) NOT NULL AUTO_INCREMENT,
  `idChatroom` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`idspectating`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spectating`
--

LOCK TABLES `spectating` WRITE;
/*!40000 ALTER TABLE `spectating` DISABLE KEYS */;
/*!40000 ALTER TABLE `spectating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `question` varchar(200) NOT NULL,
  `answer` varchar(45) NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `userid_UNIQUE` (`userid`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'kate','1234','What is your favorite color?','Pink'),(2,'mgmalana','1234','What was the name of your first pet?','lala'),(3,'albertpls','1','What was the name of your first pet?','1'),(15,'dsa','dasd','What was the name of your first pet?','asd');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-23 13:44:04
