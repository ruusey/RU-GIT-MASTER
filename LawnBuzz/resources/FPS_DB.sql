CREATE DATABASE  IF NOT EXISTS `fps` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `fps`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: fps
-- ------------------------------------------------------
-- Server version	5.6.20

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
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `identifier` varchar(68) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `score` double DEFAULT NULL,
  `id_roles` int(11) DEFAULT NULL,
  `id_priv` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,1,'7687-1801','efuller',65.2,1,1);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_event`
--

DROP TABLE IF EXISTS `member_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` varchar(45) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `completed` tinyint(4) DEFAULT '0',
  `type` int(11) DEFAULT '0',
  `description` varchar(100) DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_event`
--

LOCK TABLES `member_event` WRITE;
/*!40000 ALTER TABLE `member_event` DISABLE KEYS */;
INSERT INTO `member_event` VALUES (1,'1','2018-06-15',0,1,'Daves Birthday',0);
/*!40000 ALTER TABLE `member_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_event_map`
--

DROP TABLE IF EXISTS `member_event_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_event_map` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_event_map`
--

LOCK TABLES `member_event_map` WRITE;
/*!40000 ALTER TABLE `member_event_map` DISABLE KEYS */;
INSERT INTO `member_event_map` VALUES (1,1,1);
/*!40000 ALTER TABLE `member_event_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_event_outcome`
--

DROP TABLE IF EXISTS `member_event_outcome`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_event_outcome` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `outcome` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_event_outcome`
--

LOCK TABLES `member_event_outcome` WRITE;
/*!40000 ALTER TABLE `member_event_outcome` DISABLE KEYS */;
INSERT INTO `member_event_outcome` VALUES (1,1,1,2);
/*!40000 ALTER TABLE `member_event_outcome` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_event_req`
--

DROP TABLE IF EXISTS `member_event_req`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_event_req` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) DEFAULT '0',
  `requirement` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_event_req`
--

LOCK TABLES `member_event_req` WRITE;
/*!40000 ALTER TABLE `member_event_req` DISABLE KEYS */;
INSERT INTO `member_event_req` VALUES (2,1,1,1),(5,1,1,2);
/*!40000 ALTER TABLE `member_event_req` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_event_role`
--

DROP TABLE IF EXISTS `member_event_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_event_role` (
  `user_id` int(11) NOT NULL,
  `event_id` int(11) DEFAULT NULL,
  `role` tinyint(8) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_event_role`
--

LOCK TABLES `member_event_role` WRITE;
/*!40000 ALTER TABLE `member_event_role` DISABLE KEYS */;
INSERT INTO `member_event_role` VALUES (1,1,4);
/*!40000 ALTER TABLE `member_event_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT 'null',
  `lastname` varchar(45) DEFAULT 'null',
  `dob` date DEFAULT NULL,
  `phone` varchar(45) DEFAULT 'null',
  `email` varchar(45) DEFAULT 'null',
  `verified` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Erich','Fuller','2017-02-14','ornare.facilisis.eget@quam.co.uk','477-493-6276',0),(2,'Ahmed','Macias','2002-12-27','ante.iaculis@placeratCrasdictum.ca','241-876-3300',0),(3,'Arsenio','Justice','2003-12-11','pretium.neque.Morbi@massa.ca','495-120-8403',0),(4,'Bruno','Morgan','2006-10-05','Vivamus.nisi@egestas.ca','484-393-1106',0),(5,'Cedric','Ford','2006-09-22','Nulla.aliquet.Proin@arcuMorbi.net','275-342-1158',0),(6,'Baxter','Barber','2015-12-17','ipsum.porta.elit@enimnonnisi.co.uk','517-413-9245',0),(7,'Gemma','Hoffman','2004-09-01','tempus.scelerisque.lorem@orci.org','658-962-0789',0),(8,'Latifah','Castillo','2009-04-17','Maecenas@et.org','933-981-8242',0),(9,'Olympia','Sherman','2013-02-08','dignissim.tempor@nascetur.com','874-492-1651',0),(10,'Melyssa','Villarreal','2005-04-16','magna@cursuspurus.org','203-696-7640',0),(11,'Jason','Davenport','2004-03-11','in@sitametultricies.net','481-765-4371',0),(12,'Timon','Raymond','2004-05-05','pellentesque@inconsectetuer.co.uk','991-491-3618',0),(13,'Sylvia','Mcneil','2018-08-06','luctus.sit.amet@faucibusorci.net','629-628-1635',0),(14,'Wayne','Marshall','2013-03-13','lectus@iaculis.org','438-341-7773',0),(15,'Jaime','Barrera','2002-09-26','est.mauris@ligula.co.uk','234-590-3651',0),(16,'Eaton','Woods','2011-09-03','orci.Phasellus@ullamcorperDuisat.net','158-815-1703',0),(17,'Galvin','Lambert','2005-03-20','iaculis@imperdiet.com','645-724-5359',0),(18,'Freya','Cantu','2005-08-13','In@afelisullamcorper.net','840-140-2415',0),(19,'Jocelyn','Donaldson','2009-03-23','turpis@lacus.co.uk','306-597-6610',0),(20,'Violet','Shelton','2009-11-24','vel.pede.blandit@commodohendrerit.net','133-861-9685',0),(21,'Ivan','Cobb','2004-04-05','ullamcorper.magna@Nuncut.edu','882-140-7916',0),(22,'Tyler','Miles','2007-05-28','Pellentesque@auctorvitaealiquet.edu','281-448-9089',0),(23,'Xanthus','Williamson','2003-07-16','ante.Nunc.mauris@lectuspede.net','472-718-1105',0),(24,'Tanya','Pittman','2010-04-16','diam.dictum@Curabituregestasnunc.co.uk','325-687-7473',0),(25,'Althea','Ferguson','2016-12-17','Sed.dictum.Proin@venenatisvel.edu','953-247-1034',0),(26,'Heather','Benjamin','1999-12-23','nunc.sed.libero@Inlorem.edu','623-224-2587',0),(27,'Cynthia','Carson','2004-07-20','lectus.rutrum.urna@molestie.net','375-528-8029',0),(28,'Fritz','Collier','2010-04-12','ornare.egestas.ligula@lacus.co.uk','843-431-9870',0),(29,'Whitney','Ellison','2016-09-08','Aliquam.rutrum.lorem@vulputatelacus.com','236-157-2404',0),(30,'Lamar','Reyes','2002-03-29','urna.nec.luctus@dictumeu.net','699-384-3609',0),(31,'Keegan','Richards','2005-05-19','in.felis@sedtortor.co.uk','175-515-1081',0),(32,'Mohammad','Whitaker','2016-08-06','gravida.sit.amet@lorem.net','299-990-0147',0),(33,'Belle','Dean','2015-06-03','vel.venenatis.vel@justoProin.ca','745-491-5700',0),(34,'Jenna','Fox','2016-03-23','molestie.sodales@duiFuscediam.com','741-203-8693',0),(35,'Galena','Christian','2007-11-26','nisl.Quisque@Pellentesque.org','973-205-6500',0),(36,'Finn','Ellis','2002-05-30','sagittis@dolor.net','652-685-8816',0),(37,'Yoshi','Zamora','2014-03-08','gravida.sagittis@magnaPhasellus.net','407-171-9919',0),(38,'Burton','Clements','2011-04-15','dignissim.lacus@neque.edu','415-204-3134',0),(39,'Diana','Macias','2005-01-24','dis.parturient@lectuspede.edu','411-820-8389',0),(40,'Sydney','Reynolds','2007-07-17','tellus@rhoncusid.com','554-134-4781',0),(41,'Maya','Compton','2013-04-24','pede@senectus.com','484-353-0130',0),(42,'Katell','Richardson','2003-01-10','nisi@etmagnis.net','275-905-4979',0),(43,'Adena','House','2013-10-05','senectus.et@SedmolestieSed.org','513-545-4952',0),(44,'Phyllis','Garrett','2011-09-13','eu@elit.co.uk','312-331-5780',0),(45,'Hayes','Mason','2004-12-14','augue.ut@Donecconsectetuermauris.org','809-813-1904',0),(46,'Barry','Acevedo','2010-01-14','magnis@nunc.com','143-914-0741',0),(47,'Barry','Swanson','2018-01-31','facilisis@parturient.ca','985-367-9834',0),(48,'Anika','Clarke','2005-12-10','In.lorem@Pellentesque.com','738-581-2865',0),(49,'Rigel','Wilson','2009-10-02','id.mollis.nec@ridiculusmus.com','180-652-1135',0),(50,'Carly','Kane','2019-01-06','dolor.Quisque@temporarcu.ca','448-504-6700',0),(51,'Patrick','Oneal','2010-10-02','pretium.aliquet@vestibulumMauris.org','990-995-9657',0),(52,'Tana','Phelps','2017-04-16','Cras.dictum@sitametdiam.com','488-353-7013',0),(53,'Roary','Sparks','1999-09-14','arcu@Mauris.ca','113-646-4894',0),(54,'Willa','Clarke','2013-12-11','lacus@semegestas.com','447-846-0866',0),(55,'Lisandra','Wong','1998-09-03','neque@quam.org','780-257-5696',0),(56,'Kyle','Alvarado','2002-03-16','Curae.Phasellus.ornare@turpisnon.net','115-635-3693',0),(57,'Kenneth','Richmond','2013-07-07','egestas@erat.com','723-329-5099',0),(58,'Knox','Cannon','1999-02-04','vitae@Namporttitorscelerisque.ca','176-945-6563',0),(59,'Beck','Cunningham','2008-10-08','sit.amet@Phasellus.org','971-926-4615',0),(60,'Aristotle','Morris','2011-09-04','in.consectetuer.ipsum@id.org','836-514-7270',0),(61,'Kellie','Luna','2016-06-01','Quisque.purus.sapien@Aenean.com','543-880-1274',0),(62,'Aubrey','Andrews','2007-08-17','arcu.Morbi@laoreetipsumCurabitur.com','399-216-5783',0),(63,'Zephania','Knapp','1999-01-16','cubilia@fringillaornareplacerat.ca','871-232-1644',0),(64,'Guy','Conley','2013-10-11','malesuada.fames@volutpat.com','604-233-8643',0),(65,'Berk','Guerra','2006-04-10','eget.ipsum@mollis.ca','702-673-7314',0),(66,'Hope','Gentry','2002-06-07','arcu.Morbi.sit@egestashendreritneque.com','858-392-6834',0),(67,'Dustin','Jefferson','1998-11-19','lectus.ante.dictum@inceptos.co.uk','220-186-9770',0),(68,'Sebastian','Nielsen','2017-07-11','sapien.molestie@consequatdolor.net','574-760-0793',0),(69,'Anastasia','Hansen','2017-01-16','tellus@dictumeu.co.uk','238-958-0769',0),(70,'Logan','Nicholson','2008-07-18','ipsum.dolor@egestashendreritneque.net','251-717-7827',0),(71,'Teagan','Murphy','2005-03-29','Aenean.eget@ultricesposuerecubilia.ca','765-644-6564',0),(72,'Erich','Burke','2010-02-17','lorem.ipsum@maurisanunc.net','890-661-4196',0),(73,'Dieter','Bryan','2009-08-28','aliquet.diam@Pellentesque.edu','619-237-4084',0),(74,'Natalie','Mcguire','2001-05-16','nunc.ac@Aeneansedpede.ca','854-282-0304',0),(75,'Fiona','Schmidt','2002-10-09','nunc@sagittissemper.co.uk','703-816-2911',0),(76,'Cally','Navarro','2002-05-12','nisi.sem@a.com','311-668-5146',0),(77,'Travis','Rowe','2011-05-07','tincidunt.nibh@id.edu','533-241-2650',0),(78,'Mariam','Bonner','2010-05-04','blandit.congue@aliquetnec.ca','269-198-9274',0),(79,'Pascale','Wilkerson','2017-04-13','Nunc@euturpis.org','610-393-0285',0),(80,'Grant','Rowe','2017-03-14','commodo@sit.edu','761-668-5771',0),(81,'Tanya','Ellison','2002-09-29','Nam.tempor@ornaretortor.org','631-743-3005',0),(82,'Leslie','Franco','2001-11-20','nisi.Mauris.nulla@idante.net','170-523-6732',0),(83,'Candace','Weaver','2016-12-29','Proin.dolor@Fuscediam.ca','818-264-9163',0),(84,'Rhoda','Washington','2003-12-08','elit@nequeIn.ca','971-922-3325',0),(85,'Dahlia','Camacho','2016-05-10','Etiam.ligula.tortor@pharetranibh.org','887-697-4575',0),(86,'Yoko','Dorsey','2018-05-13','suscipit.est@maurisa.edu','272-895-0221',0),(87,'Kenyon','Mcintyre','2012-12-15','mollis.dui.in@egetvenenatis.com','848-692-1162',0),(88,'Leigh','Leonard','2017-04-13','metus.Aenean@placerataugue.com','651-866-8380',0),(89,'Zoe','Bird','2011-05-25','lectus.a@augue.co.uk','735-617-8672',0),(90,'Marvin','Tyson','1999-07-15','fringilla.porttitor@lacusMauris.co.uk','731-714-5978',0),(91,'Daphne','Bentley','2011-04-29','ipsum.Donec@Suspendisseseddolor.ca','795-330-1277',0),(92,'Magee','Ortiz','2016-11-04','malesuada.malesuada.Integer@ante.co.uk','160-864-3618',0),(93,'Karina','Byrd','2003-12-23','amet.lorem@dapibus.ca','626-537-4656',0),(94,'Audra','Dale','2006-01-03','a@Donec.net','487-671-0032',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-10 10:31:02
