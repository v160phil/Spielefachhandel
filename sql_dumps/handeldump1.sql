-- MariaDB dump 10.19  Distrib 10.4.32-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: spielefachhandel
-- ------------------------------------------------------
-- Server version	10.4.32-MariaDB

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
-- Table structure for table `kunde`
--

DROP TABLE IF EXISTS `kunde`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kunde` (
  `kundenNr` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `strasse` varchar(50) DEFAULT NULL,
  `telefonnummer` varchar(50) DEFAULT NULL,
  `ort` varchar(50) DEFAULT NULL,
  `plz` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `vorname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`kundenNr`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kunde`
--

LOCK TABLES `kunde` WRITE;
/*!40000 ALTER TABLE `kunde` DISABLE KEYS */;
INSERT INTO `kunde` VALUES (1,'lisa.schneider@example.com','Schillerstraße 22','0176/23456789','Stuttgart','70563','Schneider','Lisa'),(2,'tobias.fischer@example.com','Hauptstraße 10','0151/98765432','Berlin','10115','Fischer','Tobias'),(3,'julia.wagner@example.com','Domstraße 5','0170/34567890','Köln','50667','Wagner','Julia'),(4,'markus.hoffmann@example.com','Sonnenstraße 8','0160/56789012','München','80331','Hoffmann','Markus');
/*!40000 ALTER TABLE `kunde` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `autoID` int(11) NOT NULL AUTO_INCREMENT,
  `anzahl` int(11) DEFAULT NULL,
  `rechnungsNr` int(11) DEFAULT NULL,
  `produktNr` int(11) DEFAULT NULL,
  PRIMARY KEY (`autoID`),
  KEY `rechnungsNr` (`rechnungsNr`),
  KEY `produktNr` (`produktNr`),
  CONSTRAINT `position_ibfk_1` FOREIGN KEY (`rechnungsNr`) REFERENCES `rechnung` (`rechnungsNr`),
  CONSTRAINT `position_ibfk_2` FOREIGN KEY (`produktNr`) REFERENCES `spiel` (`produktNr`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,1,1,1),(2,3,2,2),(3,1,2,3),(4,10,3,3);
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rechnung`
--

DROP TABLE IF EXISTS `rechnung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rechnung` (
  `rechnungsNr` int(11) NOT NULL AUTO_INCREMENT,
  `rechnungsdatum` date DEFAULT NULL,
  `kundenNr` int(11) DEFAULT NULL,
  PRIMARY KEY (`rechnungsNr`),
  KEY `kundenNr` (`kundenNr`),
  CONSTRAINT `rechnung_ibfk_1` FOREIGN KEY (`kundenNr`) REFERENCES `kunde` (`kundenNr`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rechnung`
--

LOCK TABLES `rechnung` WRITE;
/*!40000 ALTER TABLE `rechnung` DISABLE KEYS */;
INSERT INTO `rechnung` VALUES (1,'2023-10-14',1),(2,'2024-03-22',2),(3,'2025-01-01',4);
/*!40000 ALTER TABLE `rechnung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spiel`
--

DROP TABLE IF EXISTS `spiel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spiel` (
  `produktNr` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `genre` varchar(50) DEFAULT NULL,
  `einzelpreis` double DEFAULT NULL,
  `veroeffentlichungsdatum` date DEFAULT NULL,
  PRIMARY KEY (`produktNr`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spiel`
--

LOCK TABLES `spiel` WRITE;
/*!40000 ALTER TABLE `spiel` DISABLE KEYS */;
INSERT INTO `spiel` VALUES (1,'Minecraft','Survival',24.99,'2011-11-18'),(2,'Mario Kart 8 Deluxe','Rennspiel',59.99,'2017-04-27'),(3,'Among Us','Deduktionsspiel',4.49,'2018-06-15'),(4,'Pizza Tower','Plattformspiel',19.5,'2023-01-26');
/*!40000 ALTER TABLE `spiel` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-27 19:44:20
