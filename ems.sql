CREATE DATABASE  IF NOT EXISTS `ems` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ems`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ems
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `qid` int(11) NOT NULL AUTO_INCREMENT,
  `q_statement` varchar(200) NOT NULL,
  `q_statement_img` mediumtext,
  `option1` varchar(100) NOT NULL,
  `option1_img` mediumtext,
  `option2` varchar(100) NOT NULL,
  `option2_img` mediumtext,
  `option3` varchar(100) DEFAULT NULL,
  `option3_img` mediumtext,
  `option4` varchar(100) DEFAULT NULL,
  `option4_img` varchar(100) DEFAULT NULL,
  `correct_answer` varchar(200) DEFAULT NULL,
  `q_time` double DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `subject` varchar(45) NOT NULL,
  `topic` varchar(45) DEFAULT NULL,
  `difficulty_level` varchar(10) DEFAULT 'medium',
  PRIMARY KEY (`qid`),
  UNIQUE KEY `qid_UNIQUE` (`qid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,'this is a question',NULL,'this is a option 1',NULL,'this is a option2','this is a option2 img','this is a option 3',NULL,'this is a option4','this is img4','this is a option 1',2,'Az','PF',NULL,'medium'),(2,'this is a question2',NULL,'this is a option 1',NULL,'this is a option2','this is a option2 img','this is a option 3',NULL,'this is a option4','this is img4','this is a option 3',2,'Az','PF',NULL,'medium'),(3,'this is a question3',NULL,'this is a option 1',NULL,'this is a option2','this is a option2 img','this is a option 3',NULL,'this is a option4','this is img4','this is a option 1',2,'Az','PF',NULL,'medium'),(4,'this is a question4',NULL,'this is a option 1',NULL,'this is a option2','this is a option2 img','this is a option 3',NULL,'this is a option4','this is img4','this is a option 3',2,'Az','PF',NULL,'medium'),(5,'Which is not the property of JAVA',NULL,'PlatForm Independent',NULL,'Open Source',NULL,'Object Oriented',NULL,'Costly IDE',NULL,NULL,2.2,'JAVA','JAVA','JAVA ADVANTAGES','easy'),(6,'Which is the property of JAVA',NULL,'PlatForm dependent',NULL,'Open Source',NULL,'Not Object Oriented',NULL,'Free IDE',NULL,NULL,2.2,'JAVA','JAVA','JAVA ADVANTAGES','easy');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions_result`
--

DROP TABLE IF EXISTS `questions_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions_result` (
  `test_id` int(11) DEFAULT NULL,
  `student_id` varchar(20) DEFAULT NULL,
  `q_id` int(11) DEFAULT NULL,
  `selected_option` varchar(100) DEFAULT NULL,
  `correct_option` varchar(100) DEFAULT NULL,
  `question_marks` double DEFAULT NULL,
  `obtain_marks` double DEFAULT NULL,
  `qr_id` int(11) NOT NULL,
  KEY `q_id_idx` (`q_id`),
  KEY `student_id2_idx` (`student_id`),
  KEY `test_id1` (`test_id`),
  CONSTRAINT `q_id1` FOREIGN KEY (`q_id`) REFERENCES `questions` (`qid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `student_id2` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `test_id1` FOREIGN KEY (`test_id`) REFERENCES `tests_info` (`test_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions_result`
--

LOCK TABLES `questions_result` WRITE;
/*!40000 ALTER TABLE `questions_result` DISABLE KEYS */;
/*!40000 ALTER TABLE `questions_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students` (
  `student_id` varchar(20) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `idstudents_UNIQUE` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES ('1','1231'),('2','1232'),('bitf14a002','Saeed'),('bitf14a027','Azhar');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students_batches`
--

DROP TABLE IF EXISTS `students_batches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students_batches` (
  `batch_id` int(11) NOT NULL,
  `student_id` varchar(20) NOT NULL,
  PRIMARY KEY (`batch_id`,`student_id`),
  KEY `student_id_idx` (`student_id`),
  CONSTRAINT `student_id` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students_batches`
--

LOCK TABLES `students_batches` WRITE;
/*!40000 ALTER TABLE `students_batches` DISABLE KEYS */;
INSERT INTO `students_batches` VALUES (1,'1'),(1,'2'),(1,'bitf14a002'),(1,'bitf14a027');
/*!40000 ALTER TABLE `students_batches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_info`
--

DROP TABLE IF EXISTS `test_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_info` (
  `test_id` int(11) NOT NULL,
  `test_marks` double DEFAULT NULL,
  `total_questions` int(11) DEFAULT NULL,
  `test_max_time` int(11) DEFAULT NULL,
  `test_live_time` date DEFAULT NULL,
  `test_status` varchar(255) DEFAULT NULL,
  `assign_batch_id` int(11) NOT NULL,
  PRIMARY KEY (`test_id`),
  KEY `FK_oey52c8osod58plr1hy6ig65` (`assign_batch_id`),
  CONSTRAINT `FK_oey52c8osod58plr1hy6ig65` FOREIGN KEY (`assign_batch_id`) REFERENCES `students_batches` (`batch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_info`
--

LOCK TABLES `test_info` WRITE;
/*!40000 ALTER TABLE `test_info` DISABLE KEYS */;
INSERT INTO `test_info` VALUES (1,50,20,20,'1970-01-01','live',1),(2,20,22,22,'1970-01-01','pending',1);
/*!40000 ALTER TABLE `test_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_questions`
--

DROP TABLE IF EXISTS `test_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_questions` (
  `test_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `tq_id` int(11) NOT NULL,
  PRIMARY KEY (`test_id`,`question_id`),
  KEY `question_id_idx` (`question_id`),
  CONSTRAINT `FK_smd91buf2i2aycop6tcuewnnm` FOREIGN KEY (`test_id`) REFERENCES `test_info` (`test_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_questions`
--

LOCK TABLES `test_questions` WRITE;
/*!40000 ALTER TABLE `test_questions` DISABLE KEYS */;
INSERT INTO `test_questions` VALUES (1,1,0),(1,2,0),(1,3,0),(1,4,1),(1,5,1),(1,6,1),(2,1,1),(2,2,1),(2,3,1);
/*!40000 ALTER TABLE `test_questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_result`
--

DROP TABLE IF EXISTS `test_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_result` (
  `test_id` int(11) NOT NULL,
  `student_id` varchar(20) NOT NULL,
  `total_marks` double DEFAULT NULL,
  `marks_obtained` double DEFAULT NULL,
  `test_result_id` int(11) NOT NULL,
  PRIMARY KEY (`test_id`,`student_id`),
  KEY `test_id2_idx` (`test_id`),
  KEY `student_id1_idx` (`student_id`),
  CONSTRAINT `FK_tojb2wlo0nyrj7e2x7xucow9l` FOREIGN KEY (`test_id`) REFERENCES `test_info` (`test_id`),
  CONSTRAINT `student_id1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `test_id2` FOREIGN KEY (`test_id`) REFERENCES `tests_info` (`test_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_result`
--

LOCK TABLES `test_result` WRITE;
/*!40000 ALTER TABLE `test_result` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tests_info`
--

DROP TABLE IF EXISTS `tests_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tests_info` (
  `test_id` int(11) NOT NULL,
  `assign_batch_id` int(11) DEFAULT NULL,
  `test_marks` double DEFAULT NULL,
  `total_questions` int(11) DEFAULT NULL,
  `test_max_time` int(11) DEFAULT NULL,
  `test_live_time` datetime DEFAULT NULL,
  `test_status` varchar(15) DEFAULT 'pending',
  PRIMARY KEY (`test_id`),
  KEY `assign_batch_id_idx` (`assign_batch_id`),
  CONSTRAINT `assign_batch_id` FOREIGN KEY (`assign_batch_id`) REFERENCES `students_batches` (`batch_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tests_info`
--

LOCK TABLES `tests_info` WRITE;
/*!40000 ALTER TABLE `tests_info` DISABLE KEYS */;
INSERT INTO `tests_info` VALUES (1,1,30,4,20,'2017-12-12 12:00:59','pending');
/*!40000 ALTER TABLE `tests_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-14 19:04:47
