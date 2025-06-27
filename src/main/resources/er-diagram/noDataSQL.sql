-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.36 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.7.0.6850
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for lms_system
CREATE DATABASE IF NOT EXISTS `lms_system` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `lms_system`;

-- Dumping structure for table lms_system.author
CREATE TABLE IF NOT EXISTS `author` (
  `id` varchar(10) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Data exporting was unselected.

-- Dumping structure for table lms_system.book
CREATE TABLE IF NOT EXISTS `book` (
  `isbn` varchar(10) NOT NULL,
  `title` varchar(45) NOT NULL,
  `copies` int NOT NULL,
  `status_id` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'S001',
  `gerne_id` varchar(10) NOT NULL,
  `author_id` varchar(10) NOT NULL,
  PRIMARY KEY (`isbn`),
  KEY `fk_book_status1_idx` (`status_id`),
  KEY `fk_book_gerne1_idx` (`gerne_id`),
  KEY `fk_book_author1_idx` (`author_id`),
  CONSTRAINT `fk_book_author1` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`),
  CONSTRAINT `fk_book_gerne1` FOREIGN KEY (`gerne_id`) REFERENCES `gerne` (`gerne_id`),
  CONSTRAINT `fk_book_status1` FOREIGN KEY (`status_id`) REFERENCES `book_status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Data exporting was unselected.

-- Dumping structure for table lms_system.book_status
CREATE TABLE IF NOT EXISTS `book_status` (
  `id` varchar(10) NOT NULL,
  `status` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Data exporting was unselected.

-- Dumping structure for table lms_system.fine
CREATE TABLE IF NOT EXISTS `fine` (
  `id` varchar(10) NOT NULL,
  `reason` varchar(45) NOT NULL,
  `paid_date` date NOT NULL,
  `paid_time` time NOT NULL,
  `amount` double NOT NULL,
  `member_id` varchar(10) NOT NULL,
  `book_isbn` varchar(10) NOT NULL,
  `fine_status_id` varchar(10) NOT NULL DEFAULT '2',
  PRIMARY KEY (`id`),
  KEY `fk_fine_fine_status1_idx` (`fine_status_id`),
  KEY `fk_fine_member1_idx` (`member_id`),
  KEY `fk_fine_book1_idx` (`book_isbn`),
  CONSTRAINT `fk_fine_book1` FOREIGN KEY (`book_isbn`) REFERENCES `book` (`isbn`),
  CONSTRAINT `fk_fine_fine_status1` FOREIGN KEY (`fine_status_id`) REFERENCES `fine_status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Data exporting was unselected.

-- Dumping structure for table lms_system.fine_status
CREATE TABLE IF NOT EXISTS `fine_status` (
  `id` varchar(10) NOT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Data exporting was unselected.

-- Dumping structure for table lms_system.gerne
CREATE TABLE IF NOT EXISTS `gerne` (
  `gerne_id` varchar(10) NOT NULL,
  `name` varchar(35) NOT NULL,
  PRIMARY KEY (`gerne_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Data exporting was unselected.

-- Dumping structure for table lms_system.member
CREATE TABLE IF NOT EXISTS `member` (
  `id` varchar(10) NOT NULL,
  `name` varchar(45) NOT NULL,
  `nic` varchar(15) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `password` varchar(80) DEFAULT NULL,
  `type_id` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_member_member_type1_idx1` (`type_id`),
  CONSTRAINT `fk_member_member_type1` FOREIGN KEY (`type_id`) REFERENCES `member_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Data exporting was unselected.

-- Dumping structure for table lms_system.member_has_book
CREATE TABLE IF NOT EXISTS `member_has_book` (
  `member_id` varchar(10) NOT NULL,
  `book_isbn` varchar(10) NOT NULL,
  `issue_qty` int DEFAULT NULL,
  `issue_date` date DEFAULT NULL,
  `issue_time` time DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  PRIMARY KEY (`member_id`,`book_isbn`),
  KEY `fk_member_has_book_book1_idx` (`book_isbn`),
  KEY `fk_member_has_book_member1_idx` (`member_id`),
  CONSTRAINT `fk_member_has_book_book1` FOREIGN KEY (`book_isbn`) REFERENCES `book` (`isbn`),
  CONSTRAINT `fk_member_has_book_member1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Data exporting was unselected.

-- Dumping structure for table lms_system.member_type
CREATE TABLE IF NOT EXISTS `member_type` (
  `id` varchar(5) NOT NULL DEFAULT '',
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Data exporting was unselected.

-- Dumping structure for table lms_system.return_book
CREATE TABLE IF NOT EXISTS `return_book` (
  `member_id` varchar(10) NOT NULL,
  `book_isbn` varchar(10) NOT NULL,
  `returned_date` date NOT NULL,
  `returned_time` time NOT NULL,
  PRIMARY KEY (`member_id`,`book_isbn`),
  KEY `fk_return_book_member1_idx` (`member_id`),
  KEY `fk_return_book_book1_idx` (`book_isbn`),
  CONSTRAINT `fk_return_book_member1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Data exporting was unselected.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
