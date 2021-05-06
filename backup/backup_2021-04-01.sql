-- MariaDB dump 10.19  Distrib 10.4.18-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: bapersdb
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
-- Current Database: `bapersdb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `bapersdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `bapersdb`;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `role` varchar(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (13,'Manager','Manager.login','Office Manager','Get_it_done'),(14,'Accountant','Accountant.ShifManager','Shift Manager','Count_money'),(15,'Clerk','Clerk.ShiftManager','Shift Manager','Paperwork'),(16,'Hello','Hello.receptionist','Receptionist','Hello_there'),(17,'Development','Dev.tech','Technician','Lot_smell'),(18,'Copy','Copy.tech','Technician','Too_dark'),(19,'Packer','Packer.tech','Technician','Pack_it'),(20,'Finish','Finish.tech','Technician','Fine_touch'),(21,'Dardan','Dardan.Gashi@City.ac.uk','Technician','12345');
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
  `contact_name` varchar(50) DEFAULT NULL,
  `phone` varchar(11) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `status` varchar(10) NOT NULL,
  `agreed_discount` varchar(50) DEFAULT NULL,
  `discount_rate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (10,'City, University of London','Prof David Rhind','02070408000','Northampton Square, London EC1V 0HB','Valued','Fixed','3'),(11,'Ms Eva Bauyer','Ms Eva Bauyer','02085558989','1, Liverpool street, London EC2V 8NS','Valued','Fixed','3'),(12,'Hello Magazine','Ms Sarah Brocklehurst','02034567809','12 Charter Street, London W1 8NS','Regular','Flexible','<1000 = 0%, 1000 - 2000 = 1%, > 2000 = 2%'),(15,'InfoPharma Ltd','Mr Alex Wright','02073218001','25 Bond Street, London WC1V 8LS','Valued','Flexible','<1000 = 0%, 1000 - 2000 = 1%, > 2000 = 2%'),(16,'AirVia Ltd','Mr Boris Berezovsky','02073218523','12, Bond Street, London WC1V 8HU','Valued','Variable','1 = 1%, 2 = 1%, 3 = 0%, 4 = 2%, 5 = 2%, 6 = 0%, 7 = 2%'),(17,'Dardan','Dardan Gashi','12345678901','Home','null','Flexible','0  <  v  < 3000 = 3, V > 10');
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
  `job_id` int(4) NOT NULL,
  `customer_id` int(4) NOT NULL,
  `amount` double NOT NULL,
  `job_completed` datetime DEFAULT NULL,
  `invoice_date` datetime DEFAULT NULL,
  `discountedPrice` double DEFAULT NULL,
  PRIMARY KEY (`invoice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (28,1045,10,1000,'2021-04-02 16:35:31','2021-04-01 16:44:08',970),(29,1046,11,319,'2021-04-01 22:36:13','2021-04-01 16:46:53',309.43);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job` (
  `job_id` int(4) NOT NULL AUTO_INCREMENT,
  `customer_id` int(4) NOT NULL,
  `urgency` varchar(10) NOT NULL,
  `status` varchar(10) NOT NULL,
  `special_instructions` varchar(255) DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `deadline` varchar(255) DEFAULT NULL,
  `Price` float DEFAULT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1048 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (1045,10,'normal','Complete','none','2021-04-01 15:42:35','2021-04-02 16:35:31',1000,10),(1046,11,'urgent','Complete','none','2021-04-01 15:46:51','2021-04-01 22:36:13',319,5),(1047,10,'normal','Complete','','2021-03-19 16:51:02','2021-04-02 16:50:16',190.3,1);
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
  `invoice_id` int(5) DEFAULT NULL,
  `type` varchar(10) NOT NULL,
  `amount` double NOT NULL,
  `card_type` varchar(10) DEFAULT NULL,
  `expiry_date` varchar(255) DEFAULT NULL,
  `CardNumber` varchar(255) DEFAULT NULL,
  `CVC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1029 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1027,28,'Cash',970,'NULL','','',''),(1028,29,'Card',309.43,'Debit','01/2023','123456789','888');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `task_id` int(2) NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `department` varchar(20) NOT NULL,
  `duration` int(10) DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,19,'Copy Room',120),(2,49.5,'Development area',60),(3,20,'Packing Department',30),(4,80,'Development area',90),(5,110.3,'Development area',180),(6,8.3,'Copy Room',75);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_job`
--

DROP TABLE IF EXISTS `task_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_job` (
  `task_jobID` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(4) NOT NULL,
  `task_id` int(2) NOT NULL,
  `status` varchar(10) NOT NULL,
  `task_date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `time_taken` int(11) DEFAULT NULL,
  `completed_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`task_jobID`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_job`
--

LOCK TABLES `task_job` WRITE;
/*!40000 ALTER TABLE `task_job` DISABLE KEYS */;
INSERT INTO `task_job` VALUES (32,1045,3,'Complete','2021-04-01','16:38:07',30,'Finish'),(33,1046,6,'Complete','2021-04-01','16:46:34',75,'Finish'),(34,1045,4,'Complete','2021-04-01','16:40:10',90,'Finish'),(35,1046,7,'Complete','2021-04-01','16:46:41',45,'Finish'),(36,1047,5,'Complete','2021-04-01','16:50:42',180,'Finish'),(37,1047,4,'Complete','2021-04-01','16:50:48',90,'Finish');
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
  `start_time` tinyint NOT NULL,
  `time_taken` tinyint NOT NULL
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
-- Current Database: `bapersdb`
--

USE `bapersdb`;

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
/*!50001 VIEW `view1` AS select `tj`.`completed_by` AS `completed_by`,`t`.`task_id` AS `task_id`,`t`.`department` AS `department`,`tj`.`task_date` AS `task_date`,`tj`.`start_time` AS `start_time`,`tj`.`time_taken` AS `time_taken` from (`task` `t` join `task_job` `tj`) where `t`.`task_id` = `tj`.`task_id` and `tj`.`completed_by` = 'Finish' and `tj`.`task_date` = '2021-04-01' order by `tj`.`completed_by` */;
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
/*!50001 VIEW `view2` AS select `view1`.`completed_by` AS `completed_by`,sec_to_time(sum(`view1`.`time_taken`) * 60) AS `total_time` from `view1` group by `view1`.`completed_by` */;
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
/*!50001 VIEW `view3` AS select `view1`.`task_date` AS `task_date`,sec_to_time(sum(`view1`.`time_taken`) * 60) AS `total_effort` from `view1` order by `view1`.`task_date` */;
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

-- Dump completed on 2021-04-01 17:07:32
