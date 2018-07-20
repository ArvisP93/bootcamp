-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: db_cbs
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu18.04.1

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
-- Temporary table structure for view `Cinema_rooms`
--

DROP TABLE IF EXISTS `Cinema_rooms`;
/*!50001 DROP VIEW IF EXISTS `Cinema_rooms`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `Cinema_rooms` AS SELECT 
 1 AS `cinema_id`,
 1 AS `name`,
 1 AS `latitude`,
 1 AS `longitude`,
 1 AS `room_id`,
 1 AS `room_name`,
 1 AS `seats`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `Cinemas`
--

DROP TABLE IF EXISTS `Cinemas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cinemas` (
  `cinema_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  PRIMARY KEY (`cinema_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cinemas`
--

LOCK TABLES `Cinemas` WRITE;
/*!40000 ALTER TABLE `Cinemas` DISABLE KEYS */;
INSERT INTO `Cinemas` VALUES (1,'Kino RIO',57.39648,21.5647),(2,'Kino Zupa',57.39637,21.61726),(3,'Kino Pope',57.40125,21.86332),(4,'Forum Cinemas',56.9463,24.11679);
/*!40000 ALTER TABLE `Cinemas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Movies`
--

DROP TABLE IF EXISTS `Movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Movies` (
  `movie_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `genre` varchar(30) NOT NULL,
  PRIMARY KEY (`movie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Movies`
--

LOCK TABLES `Movies` WRITE;
/*!40000 ALTER TABLE `Movies` DISABLE KEYS */;
INSERT INTO `Movies` VALUES (1,'Harijs Poters','Adventure'),(2,'Ugunsgreks','Drama'),(3,'Doktors Dulitls','Komedija'),(4,'Emila nedarbi','Trilleris'),(5,'Atrs un bez zelastibas Tolmets','Komedija');
/*!40000 ALTER TABLE `Movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Rooms`
--

DROP TABLE IF EXISTS `Rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rooms` (
  `room_id` int(11) NOT NULL AUTO_INCREMENT,
  `cinema_id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `seats` int(11) NOT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Rooms`
--

LOCK TABLES `Rooms` WRITE;
/*!40000 ALTER TABLE `Rooms` DISABLE KEYS */;
INSERT INTO `Rooms` VALUES (1,1,'Liela zale',100),(2,1,'Maza Zale',50),(3,2,'Dzelzs Zale',40),(4,3,'Popes Zale',60),(5,4,'Auditorija SCAPE',602),(6,4,'Auditorija 2',427),(7,4,'Auditorija 3',182),(8,4,'Auditorija 4',165);
/*!40000 ALTER TABLE `Rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `Show_info`
--

DROP TABLE IF EXISTS `Show_info`;
/*!50001 DROP VIEW IF EXISTS `Show_info`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `Show_info` AS SELECT 
 1 AS `cinema_id`,
 1 AS `cinema_name`,
 1 AS `room_name`,
 1 AS `show_id`,
 1 AS `movie_id`,
 1 AS `name`,
 1 AS `genre`,
 1 AS `room_id`,
 1 AS `date`,
 1 AS `taken_seats`,
 1 AS `total_seats`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `Show_movies`
--

DROP TABLE IF EXISTS `Show_movies`;
/*!50001 DROP VIEW IF EXISTS `Show_movies`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `Show_movies` AS SELECT 
 1 AS `show_id`,
 1 AS `movie_id`,
 1 AS `name`,
 1 AS `genre`,
 1 AS `room_id`,
 1 AS `date`,
 1 AS `taken_seats`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `Shows`
--

DROP TABLE IF EXISTS `Shows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Shows` (
  `show_id` int(11) NOT NULL AUTO_INCREMENT,
  `cinema_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `taken_seats` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`show_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Shows`
--

LOCK TABLES `Shows` WRITE;
/*!40000 ALTER TABLE `Shows` DISABLE KEYS */;
INSERT INTO `Shows` VALUES (3,4,4,5,'2018-08-31 06:00:00',''),(4,4,4,5,'2018-08-31 08:00:00',''),(5,4,4,6,'2018-08-31 10:00:00',''),(6,4,4,7,'2018-08-31 10:00:00',''),(7,4,3,8,'2018-09-01 12:00:00',''),(8,2,1,3,'2018-07-24 00:00:00',''),(9,2,2,3,'2018-07-24 04:00:00',''),(10,1,5,2,'2018-07-23 18:00:00',''),(11,1,4,1,'2018-07-23 19:00:00',''),(12,1,4,1,'2018-07-23 22:00:00',''),(13,3,2,4,'2018-07-22 08:00:00',''),(14,3,2,4,'2018-07-21 08:00:00',''),(15,3,2,4,'2018-07-21 09:00:00',''),(16,3,2,4,'2018-07-21 10:00:00',''),(17,3,2,4,'2018-07-21 11:00:00',''),(18,3,2,4,'2018-07-21 12:00:00',''),(19,3,2,4,'2018-07-21 13:00:00',''),(20,3,2,4,'2018-07-21 14:00:00',''),(21,3,2,4,'2018-07-21 15:00:00',''),(22,3,2,4,'2018-07-21 16:00:00','');
/*!40000 ALTER TABLE `Shows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `role` varchar(5) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'tomsmiezis','123456','mannav@epas.ts','user'),(2,'tomstukmanis','parole123','ventil@oru.pavelnie.ks','admin'),(3,'arvispukitis','mangarsopelmeni','dzivoju1534@gad.aa','user'),(4,'julijazelenuhina','parole','suns@kak.is','user');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `created_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'mkyong','111@yahoo.com','2017-02-11'),(2,'yflow','222@yahoo.com','2017-02-12'),(3,'zilap','333@yahoo.com','2017-02-13');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `Cinema_rooms`
--

/*!50001 DROP VIEW IF EXISTS `Cinema_rooms`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `Cinema_rooms` AS select `Cinemas`.`cinema_id` AS `cinema_id`,`Cinemas`.`name` AS `name`,`Cinemas`.`latitude` AS `latitude`,`Cinemas`.`longitude` AS `longitude`,`Rooms`.`room_id` AS `room_id`,`Rooms`.`name` AS `room_name`,`Rooms`.`seats` AS `seats` from (`Cinemas` join `Rooms`) where (`Cinemas`.`cinema_id` = `Rooms`.`cinema_id`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `Show_info`
--

/*!50001 DROP VIEW IF EXISTS `Show_info`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `Show_info` AS select `cr`.`cinema_id` AS `cinema_id`,`cr`.`name` AS `cinema_name`,`cr`.`room_name` AS `room_name`,`sm`.`show_id` AS `show_id`,`sm`.`movie_id` AS `movie_id`,`sm`.`name` AS `name`,`sm`.`genre` AS `genre`,`sm`.`room_id` AS `room_id`,`sm`.`date` AS `date`,`sm`.`taken_seats` AS `taken_seats`,`cr`.`seats` AS `total_seats` from (`Cinema_rooms` `cr` join `Show_movies` `sm`) where (`cr`.`room_id` = `sm`.`room_id`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `Show_movies`
--

/*!50001 DROP VIEW IF EXISTS `Show_movies`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `Show_movies` AS select `sh`.`show_id` AS `show_id`,`mv`.`movie_id` AS `movie_id`,`mv`.`name` AS `name`,`mv`.`genre` AS `genre`,`sh`.`room_id` AS `room_id`,`sh`.`date` AS `date`,`sh`.`taken_seats` AS `taken_seats` from (`Movies` `mv` join `Shows` `sh`) where (`mv`.`movie_id` = `sh`.`movie_id`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-20 11:30:11
