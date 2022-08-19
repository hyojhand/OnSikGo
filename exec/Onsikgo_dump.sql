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


INSERT INTO onsikgo.user (user_id, email, img_url, login_type, nickname, password, role, user_name,ban) VALUES (1, 'ssafy@ssafy.com', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/user/pngwing.com.png', 'ONSIKGO', '김싸피', '$2a$10$zkucUGixA7DjuCblAx.wEugFDuRRz5u.SjCLNTKkqBMjy2kxHGyoC', 'USER', '김싸피',0);
INSERT INTO onsikgo.user (user_id, email, img_url, login_type, nickname, password, role, user_name,ban) VALUES (2, 'ssafystore@ssafy.com', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/user/pngwing.com.png', 'ONSIKGO', '점주 이싸피', '$2a$10$zkucUGixA7DjuCblAx.wEugFDuRRz5u.SjCLNTKkqBMjy2kxHGyoC', 'OWNER', '점주 이싸피',0);
INSERT INTO onsikgo.user (user_id, email, img_url, login_type, nickname, password, role, user_name,ban) VALUES (3, 'ssafystore2@ssafy.com', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/user/pngwing.com.png', 'ONSIKGO', '점주 박싸피', '$2a$10$zkucUGixA7DjuCblAx.wEugFDuRRz5u.SjCLNTKkqBMjy2kxHGyoC', 'OWNER', '점주 박싸피',0);
INSERT INTO onsikgo.user (user_id, email, img_url, login_type, nickname, password, role, user_name,ban) VALUES (4, 'ssafy2@ssafy.com', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/user/pngwing.com.png', 'ONSIKGO', '손싸피', '$2a$10$zkucUGixA7DjuCblAx.wEugFDuRRz5u.SjCLNTKkqBMjy2kxHGyoC', 'USER', '손싸피',0);
INSERT INTO onsikgo.user (user_id, email, img_url, login_type, nickname, password, role, user_name,ban) VALUES (5, 'ssafy3@ssafy.com', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/user/pngwing.com.png', 'ONSIKGO', '정싸피', '$2a$10$zkucUGixA7DjuCblAx.wEugFDuRRz5u.SjCLNTKkqBMjy2kxHGyoC', 'USER', '정싸피',0);
INSERT INTO onsikgo.user (user_id, email, img_url, login_type, nickname, password, role, user_name,ban) VALUES (6, 'admin@admin.com', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/user/pngwing.com.png', 'ONSIKGO', '관리자', '$2a$10$gtYiaI/1u9xTgezC1XUIVOi6DasTDoeUo1jbcnFhS2we2dz5PbgJq', 'ADMIN', '관리자',0);

INSERT INTO onsikgo.store (store_id, address, category, closing_time, extra_address, lat, lng, off_day, store_img_url, store_name, store_num, tel, user_id) VALUES (1, '부산 강서구 명지국제2로28번길 23', 'DESSERT', '22:00', '네오웨스턴 스퀘아 1동 1층', '35.093713', '128.910457', '목요일,토요일', 'http://kkoma.net/web/product/big/201708/3673_shop1_597459.jpg', '따끈따끈 베이커리', '1234567891', '051-252-1234', 2);
INSERT INTO onsikgo.store (store_id, address, category, closing_time, extra_address, lat, lng, off_day, store_img_url, store_name, store_num, tel, user_id) VALUES (2, '부산 강서구 명지국제6로 168 (스타필드 시티 명지점)', 'JAPAN', '23:30', '스타필드 1층', '35.0931119772294', '128.918143592831', '월요일,화요일,목요일,수요일', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwIweng4rnH2n-CqFDiuj7MZS4EwPlAl2pqQ&usqp=CAU', '초밥 전문점', '1234567892', '051-513-5256', 2);
INSERT INTO onsikgo.store (store_id, address, category, closing_time, extra_address, lat, lng, off_day, store_img_url, store_name, store_num, tel, user_id) VALUES (3, '부산 강서구 명지국제6로 6-1', 'SNACK', '22:30', '명지빌딩 1층', '35.0912631779415', '128.900240214', '수요일', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSmH-vn-thBwim6X5btoth9W-FjnaqgGa959g&usqp=CAU', '분식 전문점', '1234567893', '051-251-1422', 2);
INSERT INTO onsikgo.store (store_id, address, category, closing_time, extra_address, lat, lng, off_day, store_img_url, store_name, store_num, tel, user_id) VALUES (4, '부산 사하구 낙동남로 지하 1415', 'WESTERN', '22:00', '아트몰링 1층', '35.1061809647053', '128.96623783418', '화요일,수요일,목요일', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMfCAle1-g517YyQgTBqncKW0ku4x-UCJ-2A&usqp=CAU', '뉴욕 브런치', '1234567894', '051-124-563', 2);
INSERT INTO onsikgo.store (store_id, address, category, closing_time, extra_address, lat, lng, off_day, store_img_url, store_name, store_num, tel, user_id) VALUES (5, '부산광역시 강서구 녹산산단361로 6', 'INGREDIENT', '21:00', '부산지방 중소기업청 1층', '35.089230', '128.857254', '토요일', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTlBrrmjKk6G0UGNHEu-pRlWOsMzjm8YhqT6g&usqp=CAU', '식자재 전문점', '1234567895', '051-515-5124', 2);
INSERT INTO onsikgo.store (store_id, address, category, closing_time, extra_address, lat, lng, off_day, store_img_url, store_name, store_num, tel, user_id) VALUES (6, '부산 강서구 신호산단2로27번길 23', 'KOREA', '22:00', '환타지아빌딩 1층', '35.086090', '128.879729', '목요일', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRIQQkx2_9uKkpGCGYu2S9ZWkcdXrmi_WMoWg&usqp=CAU', '한국의 맛', '1234567896', '051-152-3673', 2);
INSERT INTO onsikgo.store (store_id, address, category, closing_time, extra_address, lat, lng, off_day, store_img_url, store_name, store_num, tel, user_id) VALUES (7, '부산 강서구 명지국제6로 246', 'SNACK', '23:00', '예성빌딩 1층', '35.097038', '128.923160', '토요일', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRR0PO4kLo0X_AbgbDqZIg1P-oowzHHJoZcVA&usqp=CAU', '진심카츠', '1234567897', '051-225-5415', 3);
INSERT INTO onsikgo.store (store_id, address, category, closing_time, extra_address, lat, lng, off_day, store_img_url, store_name, store_num, tel, user_id) VALUES (8, '부산 강서구 명지동 3602-1', 'INGREDIENT', '21:00', '에코펠리스 1층', '35.092120', '128.908429', '목요일', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtTMi_hH9GYRI-bQPvpYX4FX-dt_Bv5zaKpA&usqp=CAU', '명지 과일가게', '1234567898', '051-126-3523', 3);
INSERT INTO onsikgo.store (store_id, address, category, closing_time, extra_address, lat, lng, off_day, store_img_url, store_name, store_num, tel, user_id) VALUES (9, '부산 강서구 녹산산단335로 24-8 101호', 'KOREA', '22:00', '갑을녹산병원 1층', '35.090835', '128.853866', '토요일', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS8kEQ5fY666c9UNH8A7mWyRgeao25XQvGS5g&usqp=CAU', '한식당', '1234567899', '051-524-1425', 3);
INSERT INTO onsikgo.store (store_id, address, category, closing_time, extra_address, lat, lng, off_day, store_img_url, store_name, store_num, tel, user_id) VALUES (10, '부산 강서구 신호산단1로 70 (신호 활어회센터)', 'JAPAN', '23:00', '회센터 1층', '35.082995866835', '128.872665630136', '일요일', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRyB1Z6My-v2MHoUzwvptV5hV0n_DQbFozdHA&usqp=CAU', '회 센터', '1234567890', '051-535-1689', 3);
INSERT INTO onsikgo.store (store_id, address, category, closing_time, extra_address, lat, lng, off_day, store_img_url, store_name, store_num, tel, user_id) VALUES (11, '부산 강서구 녹산산단335로 24-8', 'WESTERN', '23:30', 'CU 녹산공단점 옆', '35.090844', '128.854803', '금요일', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_7rlcgaodk6SJ92zgb69QccJHxIG9-WGNqw&usqp=CAU', '양식 전문점', '2123456789', '051-357-1255', 3);
INSERT INTO onsikgo.store (store_id, address, category, closing_time, extra_address, lat, lng, off_day, store_img_url, store_name, store_num, tel, user_id) VALUES (12, '부산 강서구 명지국제6로 107', 'KOREA', '23:00', '대방디엠시티 1층', '35.092516', '128.911228', '일요일', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRyvU5FFA3rHlydGPwrHyKBRyW9WkyltAzjCg&usqp=CAU', '한식 전문점', '3123456789', '051-654-6225', 3);


INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (1, '오늘의 인기메뉴', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/8ed11e7d-c32f-4f70-b464-6666c617100c.jpeg', '크로아상', 2000, 1);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (2, '사장님 추천메뉴', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/89f4219d-07e8-471d-be05-9c5482fcbb3b.jpeg', '바게트', 5000, 1);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (3, '신메뉴', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/6a8d26a5-db64-435b-adcd-b5e67d96a3c7.jpeg', '메론빵', 3500, 1);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (4, '크림이 가득', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/2e27ef4c-3060-4976-8b97-63fffeeb74da.jpeg', '크림빵', 4000, 1);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (5, '유기농 팥으로 만든 단팥빵', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/8db40289-68da-42f7-a325-c210bab16976.jpeg', '단팥빵', 2000, 1);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (6, '맛있어요', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/6045aa43-0cf8-405c-8b6e-8298bc49afb6.jpeg', '피자빵', 2500, 1);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (7, '맛있어요', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/31aa37c5-8eaa-4c3b-bb4f-95f942c1c669.jpeg', '초밥', 9000, 2);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (8, '달달해요', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/f0eddd20-727a-48c2-aeb3-8c67257e735a.jpeg', '계란초밥', 2000, 2);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (9, '오늘의 추천메뉴', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/ce0935ab-9aae-4f2b-beed-9377320dcc35.jpeg', '초밥도시락', 8000, 2);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (10, '사장님 대표메뉴', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/7999275c-18e1-4093-aad3-cd13b75ae678.jpeg', '연어초밥', 3000, 2);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (11, '사장님 추천메뉴', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/f7df13c9-6cb0-46fe-9dd7-6c38528eb111.jpeg', '초밥세트', 12000, 2);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (12, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/38c17f90-0369-4428-861f-c653615eda3d.jpeg', '떡볶이', 3200, 3);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (13, '가게 대표메뉴', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/e2f09c67-5b95-4fcb-8ad8-76c698dd395a.jpeg', '튀김세트', 4000, 3);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (14, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/a7dadaff-3039-4a97-8e53-2fa6d3fe15c3.jpeg', '김밥', 3000, 3);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (15, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/a678ce0b-d042-406a-9334-13f230299f55.jpeg', '순대', 4000, 3);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (16, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/24794447-d045-4c11-bce7-cf9ceab0213d.jpeg', '라볶이', 5000, 3);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (17, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/61e620d9-3985-4153-a932-40e6f22a4209.jpeg', '대파', 2000, 5);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (18, '맛있어요', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/e21bbeb6-fee3-4452-a3b0-82a85ce80cf8.jpeg', '토마토', 3000, 5);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (19, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/02e56cdd-3b0f-41be-aaf9-ad4cbb44933f.jpeg', '시금치', 4000, 5);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (20, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/598b367d-c1c7-4ce2-8cf7-0f6973c73f58.jpeg', '애호박', 3000, 5);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (21, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/60f31c03-badc-4a28-94f7-8b179416e814.jpeg', '감자', 4000, 5);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (22, 'NewYork의 맛 그대로', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/7e34152e-a4ca-425b-ac11-417715a333c6.jpeg', '에그 베네딕트', 14000, 4);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (23, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/a59db3c8-1f37-4ecd-bba6-898dd671867d.jpeg', '오믈렛', 11000, 4);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (24, '푹신한 팬케이크', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/05b20ec6-9337-43b6-b1d2-47409de398d5.jpeg', '팬케이크', 12000, 4);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (25, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/ef8f9d1a-144a-40e2-b304-992194b6575e.jpeg', '몬테크리스토', 7000, 4);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (26, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/576f6226-425a-410e-8a27-f655946cd151.jpeg', '프렌치 토스트', 6000, 4);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (27, '전주 비빔밥보다 맛있는 비빔밥', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/45eb1e69-d113-445d-9e1f-d4a853f3932c.jpeg', '비빔밥', 7000, 6);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (28, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/b05f1832-ed4f-4558-8365-9c59f12b3583.jpeg', '김치찌개 정식', 8000, 6);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (29, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/88f28b22-9be2-48cf-b8fe-9a6c9ab7c609.jpeg', '불고기', 6000, 6);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (30, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/aa1e57e1-7a83-47e2-ab0b-fd5cabb4c152.jpeg', '잡채', 6000, 6);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (31, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/3a48a380-4d0c-4013-97c2-cd93f2701157.jpeg', '해물파전', 6000, 6);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (32, '부드러워요', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/be517143-c89e-485c-b750-ec29767dc600.jpeg', '로스카츠', 9500, 7);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (33, '맛있어요', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/796d500f-3c99-4a1d-9554-fc976b3aa20d.jpeg', '히레카츠', 9500, 7);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (34, '치즈가 듬뿍', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/795724e3-743d-494b-9b45-feb22d8e212e.jpeg', '치즈 돈까스', 10500, 7);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (35, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/cbca70f4-6dd0-499b-89f7-02640068fdcd.jpeg', '돈까스 덮밥', 7500, 7);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (36, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/96aa1438-ecde-4d12-8eec-33b20055261a.jpeg', '냉모밀', 6000, 7);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (37, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/93477c2c-d794-4880-b5a3-567e5a1d4e5e.jpeg', '수박', 15000, 8);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (38, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/dbe5310f-3ee0-4356-ac32-8e4735a5006b.jpeg', '참외', 9000, 8);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (39, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/24622bf1-9001-4779-8b0c-c7e19775314a.jpeg', '복숭아', 8000, 8);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (40, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/1756f248-0825-4e32-a6fb-0e12837e1181.jpeg', '망고', 8000, 8);
INSERT INTO onsikgo.item (item_id, comment, item_img_url, item_name, price, store_id) VALUES (41, '', 'https://onsikgo.s3.ap-northeast-2.amazonaws.com/79b6bc79-0e6f-4277-b068-f8eabd95b060.jpeg', '사과', 6000, 8);



INSERT INTO onsikgo.sale (sale_id, closed, date, total_price, store_id) VALUES (1, true, '20220801', 34000, 1);
INSERT INTO onsikgo.sale (sale_id, closed, date, total_price, store_id) VALUES (2, true, '20220802', 30200, 1);
INSERT INTO onsikgo.sale (sale_id, closed, date, total_price, store_id) VALUES (3, true, '20220803', 39000, 1);
INSERT INTO onsikgo.sale (sale_id, closed, date, total_price, store_id) VALUES (4, true, '20220804', 13500, 1);
INSERT INTO onsikgo.sale (sale_id, closed, date, total_price, store_id) VALUES (5, true, '20220805', 28000, 1);
INSERT INTO onsikgo.sale (sale_id, closed, date, total_price, store_id) VALUES (6, true, '20220806', 23000, 1);
INSERT INTO onsikgo.sale (sale_id, closed, date, total_price, store_id) VALUES (7, true, '20220807', 14000, 1);
INSERT INTO onsikgo.sale (sale_id, closed, date, total_price, store_id) VALUES (8, true, '20220808', 23500, 1);
INSERT INTO onsikgo.sale (sale_id, closed, date, total_price, store_id) VALUES (9, true, '20220809', 24500, 1);
INSERT INTO onsikgo.sale (sale_id, closed, date, total_price, store_id) VALUES (10, true, '20220810', 18500, 1);
INSERT INTO onsikgo.sale (sale_id, closed, date, total_price, store_id) VALUES (11, true, '20220811', 8500, 1);
INSERT INTO onsikgo.sale (sale_id, closed, date, total_price, store_id) VALUES (12, true, '20220812', 12000, 1);
INSERT INTO onsikgo.sale (sale_id, closed, date, total_price, store_id) VALUES (13, true, '20220813', 13000, 1);
INSERT INTO onsikgo.sale (sale_id, closed, date, total_price, store_id) VALUES (14, true, '20220814', 10000, 1);
INSERT INTO onsikgo.sale (sale_id, closed, date, total_price, store_id) VALUES (15, true, '20220815', 12000, 1);

INSERT INTO onsikgo.sale (sale_id, closed, date, total_price, store_id) VALUES (16, true, '20220816', 19500, 1);
INSERT INTO onsikgo.sale (sale_id, closed, date, total_price, store_id) VALUES (17, true, '20220817', 11000, 1);
INSERT INTO onsikgo.sale (sale_id, closed, date, total_price, store_id) VALUES (18, true, '20220818', 9000, 1);



INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (1, 1500, 3, 5, 1, 1);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (2, 4000, 4, 8, 2, 1);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (3, 2500, 2, 3, 3, 1);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (4, 2000, 0, 2, 4, 1);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (5, 1000, 1, 5, 5, 1);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (6, 1500, 2, 5, 6, 1);

INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (7, 1700, 1, 5, 1, 2);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (8, 4000, 3, 4, 2, 2);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (9, 2800, 2, 5, 3, 2);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (10, 3000, 3, 5, 4, 2);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (11, 1500, 2, 4, 5, 2);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (12, 2000, 0, 1, 6, 2);

INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (13, 1500, 2, 7, 1, 3);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (14, 4500, 1, 4, 2, 3);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (15, 3000, 2, 5, 3, 3);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (16, 3000, 2, 5, 4, 3);

INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (17, 3000, 2, 4, 3, 4);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (18, 3000, 4, 5, 4, 4);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (19, 1000, 1, 4, 5, 4);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (20, 1500, 0, 1, 6, 4);

INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (21, 1500, 3, 5, 1, 5);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (22, 4000, 4, 7, 2, 5);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (23, 3000, 2, 4, 3, 5);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (24, 3000, 4, 5, 4, 5);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (25, 1500, 2, 2, 5, 5);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (26, 2000, 0, 2, 6, 5);

INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (27, 1000, 3, 4, 1, 6);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (28, 3000, 2, 3, 2, 6);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (29, 2000, 2, 3, 3, 6);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (30, 2000, 1, 3, 4, 6);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (31, 1000, 3, 6, 5, 6);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (32, 2000, 0, 5, 6, 6);

INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (33, 1500, 3, 5, 1, 7);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (34, 4500, 2, 4, 2, 7);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (35, 2000, 0, 1, 6, 7);

INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (36, 2000, 3, 5, 1, 8);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (37, 4000, 3, 5, 2, 8);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (38, 2500, 2, 5, 3, 8);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (39, 2000, 3, 5, 4, 8);

INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (40, 1500, 3, 10, 1, 9);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (41, 3500, 3, 7, 2, 9);

INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (42, 1000, 1, 5, 1, 10);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (43, 4000, 2, 3, 2, 10);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (44, 2500, 0, 3, 3, 10);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (45, 3000, 1, 2, 4, 10);

INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (46, 2500, 2, 3, 3, 11);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (47, 3000, 1, 2, 4, 11);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (48, 1500, 2, 4, 5, 11);

INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (49, 3000, 1, 4, 4, 12);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (50, 1000, 1, 2, 5, 12);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (51, 2000, 2, 3, 6, 12);

INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (52, 1500, 2, 3, 1, 13);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (53, 4000, 2, 3, 2, 13);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (54, 2500, 1, 4, 3, 13);

INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (55, 1500, 2, 4, 1, 14);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (56, 3000, 1, 2, 3, 14);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (57, 2000, 0, 2, 4, 14);

INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (58, 1500, 1, 3, 1, 15);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (59, 4500, 1, 3, 2, 15);

INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (60, 1500, 1, 5, 1, 16);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (61, 4500, 1, 4, 2, 16);

INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (62, 1500, 1, 3, 1, 17);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (63, 4000, 1, 3, 2, 17);

INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (64, 1000, 1, 2, 1, 18);
INSERT INTO onsikgo.sale_item (sale_item_id, sale_price, stock, total_stock, item_id, sale_id) VALUES (65, 4000, 1, 3, 2, 18);


INSERT INTO onsikgo.orders (order_id, count, date, order_price, state, sale_item_id, user_id) VALUES (1, 2, '202208011530', 3000, 'PICKUP', 1, 1);
INSERT INTO onsikgo.orders (order_id, count, date, order_price, state, sale_item_id, user_id) VALUES (2, 3, '202208011600', 0, 'CANCEL', 2, 1);
INSERT INTO onsikgo.orders (order_id, count, date, order_price, state, sale_item_id, user_id) VALUES (3, 2, '202208011830', 0, 'CANCEL', 4, 4);
INSERT INTO onsikgo.orders (order_id, count, date, order_price, state, sale_item_id, user_id) VALUES (4, 1, '202208011900', 1000, 'PICKUP', 5, 4);
INSERT INTO onsikgo.orders (order_id, count, date, order_price, state, sale_item_id, user_id) VALUES (5, 1, '202208061530', 1000, 'PICKUP', 27, 1);
INSERT INTO onsikgo.orders (order_id, count, date, order_price, state, sale_item_id, user_id) VALUES (6, 2, '202208071600', 9000, 'PICKUP', 34, 1);
INSERT INTO onsikgo.orders (order_id, count, date, order_price, state, sale_item_id, user_id) VALUES (7, 2, '202208081700', 8000, 'PICKUP', 37, 4);
INSERT INTO onsikgo.orders (order_id, count, date, order_price, state, sale_item_id, user_id) VALUES (8, 3, '202208091600', 10500, 'PICKUP', 41, 1);



INSERT INTO onsikgo.review (review_id, content, created_date, reported, store_id, user_id) VALUES (1, '여기 아주 맛집이네요', '20220810141712', false, 1, 1);
INSERT INTO onsikgo.review (review_id, content, created_date, reported, store_id, user_id) VALUES (2, '마감세일인데도 신선해요', '20220810141758', false, 1, 4);
INSERT INTO onsikgo.review (review_id, content, created_date, reported, store_id, user_id) VALUES (3, '사장님이 친절하신곳', '20220814144603', false, 1, 5);
INSERT INTO onsikgo.review (review_id, content, created_date, reported, store_id, user_id) VALUES (4, '퇴근길에 매일 들리는 맛집', '20220810141712', false, 2, 1);
INSERT INTO onsikgo.review (review_id, content, created_date, reported, store_id, user_id) VALUES (5, '할인율이 엄청나네요', '20220810141758', false, 2, 4);
INSERT INTO onsikgo.review (review_id, content, created_date, reported, store_id, user_id) VALUES (6, '최고의 선택', '20220814144603', false, 2, 5);
INSERT INTO onsikgo.review (review_id, content, created_date, reported, store_id, user_id) VALUES (7, '좋아요~', '20220814145000', false, 3, 1);
INSERT INTO onsikgo.review (review_id, content, created_date, reported, store_id, user_id) VALUES (8, '후회없는 선택', '20220814162030', false, 4, 1);
INSERT INTO onsikgo.review (review_id, content, created_date, reported, store_id, user_id) VALUES (9, '좋아요~', '20220814175000', false, 5, 1);
INSERT INTO onsikgo.review (review_id, content, created_date, reported, store_id, user_id) VALUES (10, '최고네요~', '20220814193000', false, 6, 1);


INSERT INTO onsikgo.follow (id, store_id, user_id) VALUES (1, 1, 1);
INSERT INTO onsikgo.follow (id, store_id, user_id) VALUES (2, 1, 4);
INSERT INTO onsikgo.follow (id, store_id, user_id) VALUES (3, 2, 1);
INSERT INTO onsikgo.follow (id, store_id, user_id) VALUES (4, 2, 4);