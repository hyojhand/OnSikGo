-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: i7E201.p.ssafy.io    Database: onsikgo
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authority` (
  `authority_name` varchar(50) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`authority_name`),
  KEY `FKr1wgeo077ok1nr1shx0t70tg8` (`user_id`),
  CONSTRAINT `FKr1wgeo077ok1nr1shx0t70tg8` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `store_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK994r8tobnh44rvgk6xr8qrn1v` (`store_id`),
  KEY `FKjby6aprc2rh3sxi3qu46jb22q` (`user_id`),
  CONSTRAINT `FK994r8tobnh44rvgk6xr8qrn1v` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`),
  CONSTRAINT `FKjby6aprc2rh3sxi3qu46jb22q` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (20);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `item_id` bigint NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `item_img_url` varchar(255) DEFAULT NULL,
  `item_name` varchar(255) NOT NULL,
  `price` int NOT NULL,
  `store_id` bigint DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `FKi0c87m5jy5qxw8orrf2pugs6h` (`store_id`),
  CONSTRAINT `FKi0c87m5jy5qxw8orrf2pugs6h` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `notice_id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `notice_state` varchar(255) NOT NULL,
  `received_id` bigint NOT NULL,
  `state` bit(1) NOT NULL,
  `order_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`notice_id`),
  KEY `FKj474pwq11a682v3i5hk0tqmor` (`order_id`),
  KEY `FKcvf4mh5se36inrxn7xlh2brfv` (`user_id`),
  CONSTRAINT `FKcvf4mh5se36inrxn7xlh2brfv` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKj474pwq11a682v3i5hk0tqmor` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=221 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `count` int NOT NULL,
  `date` varchar(255) NOT NULL,
  `order_price` int DEFAULT NULL,
  `state` varchar(255) NOT NULL,
  `sale_item_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK7lcvqb15bdn3u4t1danqm04pv` (`sale_item_id`),
  KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`),
  CONSTRAINT `FK7lcvqb15bdn3u4t1danqm04pv` FOREIGN KEY (`sale_item_id`) REFERENCES `sale_item` (`sale_item_id`),
  CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `review_id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `created_date` varchar(255) NOT NULL,
  `reported` bit(1) NOT NULL,
  `store_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  KEY `FK74d12ba8sxxu9vpnc59b43y30` (`store_id`),
  KEY `FKiyf57dy48lyiftdrf7y87rnxi` (`user_id`),
  CONSTRAINT `FK74d12ba8sxxu9vpnc59b43y30` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`),
  CONSTRAINT `FKiyf57dy48lyiftdrf7y87rnxi` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale` (
  `sale_id` bigint NOT NULL AUTO_INCREMENT,
  `closed` bit(1) NOT NULL,
  `date` varchar(255) NOT NULL,
  `total_price` int NOT NULL,
  `store_id` bigint DEFAULT NULL,
  PRIMARY KEY (`sale_id`),
  KEY `FKvxeg92t81ww979wrueuy7h5a` (`store_id`),
  CONSTRAINT `FKvxeg92t81ww979wrueuy7h5a` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sale_item`
--

DROP TABLE IF EXISTS `sale_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale_item` (
  `sale_item_id` bigint NOT NULL AUTO_INCREMENT,
  `sale_price` int NOT NULL,
  `stock` int NOT NULL,
  `total_stock` int NOT NULL,
  `item_id` bigint DEFAULT NULL,
  `sale_id` bigint DEFAULT NULL,
  PRIMARY KEY (`sale_item_id`),
  KEY `FKta7t8a3kw997s9as2nwe72llf` (`item_id`),
  KEY `FKar9qqr4n69xw1shum20oflleo` (`sale_id`),
  CONSTRAINT `FKar9qqr4n69xw1shum20oflleo` FOREIGN KEY (`sale_id`) REFERENCES `sale` (`sale_id`),
  CONSTRAINT `FKta7t8a3kw997s9as2nwe72llf` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store` (
  `store_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `closing_time` varchar(255) NOT NULL,
  `extra_address` varchar(255) DEFAULT NULL,
  `lat` varchar(255) NOT NULL,
  `lng` varchar(255) NOT NULL,
  `off_day` varchar(255) DEFAULT NULL,
  `store_img_url` varchar(255) DEFAULT NULL,
  `store_name` varchar(255) NOT NULL,
  `store_num` varchar(255) NOT NULL,
  `tel` varchar(255) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`store_id`),
  KEY `FKn82wpcqrb21yddap4s3ttwnxj` (`user_id`),
  CONSTRAINT `FKn82wpcqrb21yddap4s3ttwnxj` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `login_type` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `ban` bigint,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;