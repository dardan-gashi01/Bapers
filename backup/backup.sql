-- MariaDB dump 10.19  Distrib 10.4.18-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: bapers
-- ------------------------------------------------------
-- Server version	10.4.18-MariaDB

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
-- Current Database: `bapers`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `bapers` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `bapers`;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'John Smith','John.Smith@outlook.com','Office Manager','12345'),(2,'Jane Doe','Jane.Doe@outlook.com','Shift Manager','12345'),(3,'Alan Smith','Alan.Smith@outlook.com','Technician','12345'),(4,'Jessie','Jessie@outlook.com','Receptionist','12345');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customer_id` int(4) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(50) NOT NULL,
  `contact_name` varchar(50) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `address` varchar(100) NOT NULL,
  `status` varchar(10) NOT NULL,
  `agreed_discount` varchar(50) DEFAULT NULL,
  `discount_rate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'City, University of London','Prof David Rhind','02070408000','Northampton Square, London EC1V 0HB','Valued','Fixed','3'),(2,'Ms Eva Bauyer','Ms Eva Bauyer','02085558989','1, Liverpool street, London EC2V 8NS','Valued','Fixed','2'),(3,'Hello Magazine','Ms Sarah Brocklehurst','02034567809','12 Charter Street, London W1 8NS','Regular',NULL,'');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `invoice_id` int(5) NOT NULL AUTO_INCREMENT,
  `job_id` varchar(9) NOT NULL,
  `customer_id` int(4) NOT NULL,
  `amount` double NOT NULL,
  `job_completed` datetime DEFAULT NULL,
  `invoice_date` datetime DEFAULT NULL,
  `discountedPrice` double DEFAULT NULL,
  PRIMARY KEY (`invoice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,'26031',1,69.5,'2021-03-27 14:45:07','2021-03-26 14:52:24',67.41499999999999),(2,'26032',3,69.5,'2021-03-27 14:45:25','2021-03-26 14:53:30',69.5);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job` (
  `job_id` varchar(9) NOT NULL,
  `customer_id` int(4) NOT NULL,
  `urgency` varchar(10) NOT NULL,
  `status` varchar(10) NOT NULL,
  `special_instructions` varchar(255) DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deadline` varchar(255) DEFAULT NULL,
  `Price` float DEFAULT NULL,
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES ('26031',1,'normal','Complete','','2021-03-26 14:52:17','2021-03-27 14:45:07',69.5),('26032',3,'normal','Complete','','2021-03-26 14:52:21','2021-03-27 14:45:25',69.5),('26033',3,'normal','Progress','','2021-03-26 20:58:09','2021-03-27 20:58:03',0);
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `invoice_id` int(5) NOT NULL,
  `type` varchar(10) NOT NULL,
  `amount` double NOT NULL,
  `card_type` varchar(10) DEFAULT NULL,
  `expiry_date` varchar(255) DEFAULT NULL,
  `CardNumber` varchar(255) DEFAULT NULL,
  `CVC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,2,'Cash',69.5,'NULL','','',''),(2,1,'Card',67.41499999999999,'Credit','01/2023','123456789','888');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `task_id` int(3) NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `department` varchar(20) NOT NULL,
  `duration` int(10) DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,19,'Copy Room',120),(2,49.5,'Development area',60),(3,20,'Packing Department',30),(4,80,'Development area',90),(5,110.3,'Development area',180),(6,8.3,'Copy Room',75),(7,55.5,'Finishing Room',45);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_job`
--

DROP TABLE IF EXISTS `task_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_job` (
  `job_id` varchar(9) NOT NULL,
  `task_id` int(3) NOT NULL,
  `status` varchar(10) NOT NULL,
  `start` varchar(255) DEFAULT NULL,
  `finish` varchar(255) DEFAULT NULL,
  `completed_by` varchar(255) DEFAULT NULL,
  `task_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `time_taken` int(10) DEFAULT NULL,
  KEY `task_id` (`task_id`),
  CONSTRAINT `task_job_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task` (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_job`
--

LOCK TABLES `task_job` WRITE;
/*!40000 ALTER TABLE `task_job` DISABLE KEYS */;
INSERT INTO `task_job` VALUES ('26031',2,'Complete','2021-03-26 20:14:28','2021-03-26 21:14:28','','2021-03-26 20:14:32',60),('26031',3,'Complete','2021-03-26 20:14:32','2021-03-26 20:44:32','jane Doe','2021-03-26 20:14:45',30),('26032',2,'Complete','2021-03-26 14:51:58','2021-03-26 15:51:58','Jane Doe','2021-03-26 18:26:40',NULL),('26032',3,'Complete','2021-03-26 14:51:52','2021-03-26 15:21:52','John Smith','2021-03-26 18:26:40',NULL);
/*!40000 ALTER TABLE `task_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `view1`
--

DROP TABLE IF EXISTS `view1`;
/*!50001 DROP VIEW IF EXISTS `view1`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `view1` (
  `completed_by` tinyint NOT NULL,
  `task_id` tinyint NOT NULL,
  `department` tinyint NOT NULL,
  `task_date` tinyint NOT NULL,
  `start` tinyint NOT NULL,
  `finish` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `view2`
--

DROP TABLE IF EXISTS `view2`;
/*!50001 DROP VIEW IF EXISTS `view2`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `view2` (
  `completed_by` tinyint NOT NULL,
  `total_time` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `view3`
--

DROP TABLE IF EXISTS `view3`;
/*!50001 DROP VIEW IF EXISTS `view3`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `view3` (
  `task_date` tinyint NOT NULL,
  `total_effort` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Current Database: `bapers`
--

USE `bapers`;

--
-- Final view structure for view `view1`
--

/*!50001 DROP TABLE IF EXISTS `view1`*/;
/*!50001 DROP VIEW IF EXISTS `view1`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view1` AS select `tj`.`completed_by` AS `completed_by`,`t`.`task_id` AS `task_id`,`t`.`department` AS `department`,`tj`.`task_date` AS `task_date`,`tj`.`start` AS `start`,`tj`.`finish` AS `finish` from (`task` `t` join `task_job` `tj`) where `t`.`task_id` = `tj`.`task_id` and (`tj`.`completed_by` = 'John Nash' or `tj`.`completed_by` = 'Lee Hong') and `tj`.`task_date` = '2021-03-26' order by `tj`.`completed_by` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view2`
--

/*!50001 DROP TABLE IF EXISTS `view2`*/;
/*!50001 DROP VIEW IF EXISTS `view2`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view2` AS select `view1`.`completed_by` AS `completed_by`,sec_to_time(sum(`view1`.`finish`) * 60) AS `total_time` from `view1` group by `view1`.`completed_by` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view3`
--

/*!50001 DROP TABLE IF EXISTS `view3`*/;
/*!50001 DROP VIEW IF EXISTS `view3`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view3` AS select `view1`.`task_date` AS `task_date`,sec_to_time(sum(`view1`.`finish`) * 60) AS `total_effort` from `view1` order by `view1`.`task_date` */;
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

-- Dump completed on 2021-03-27 10:59:44
