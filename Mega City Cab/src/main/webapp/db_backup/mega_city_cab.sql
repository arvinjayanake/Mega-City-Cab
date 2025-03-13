-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 13, 2025 at 12:46 PM
-- Server version: 8.0.18
-- PHP Version: 7.4.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mega_city_cab`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
CREATE TABLE IF NOT EXISTS `booking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `vehicle_id` int(11) NOT NULL,
  `driver_id` int(11) NOT NULL DEFAULT '0',
  `pickup_location` varchar(255) NOT NULL,
  `pickup_datetime` datetime NOT NULL,
  `dropoff_location` varchar(255) NOT NULL,
  `total_distance` decimal(10,2) NOT NULL,
  `tax` double(10,2) NOT NULL DEFAULT '0.00',
  `discount` double(10,2) NOT NULL DEFAULT '0.00',
  `total_price` double(10,2) NOT NULL DEFAULT '0.00',
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `payment_method` tinyint(1) NOT NULL DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`id`, `customer_id`, `vehicle_id`, `driver_id`, `pickup_location`, `pickup_datetime`, `dropoff_location`, `total_distance`, `tax`, `discount`, `total_price`, `status`, `payment_method`, `created_at`) VALUES
(11, 27, 16, -1, 'Galle', '2025-03-08 08:20:00', 'Colombo', '130.00', 0.00, 0.00, 32500.00, 3, 2, '2025-03-07 10:49:34'),
(12, 33, 11, 37, 'Colombo', '2025-03-10 08:31:00', 'Matara', '200.00', 0.00, 0.00, 24000.00, 6, 2, '2025-03-07 11:36:23'),
(13, 33, 7, 37, 'Battaramulla', '2025-03-14 16:25:00', 'Kollupitiya', '15.00', 153.60, 0.00, 4953.60, 5, 2, '2025-03-08 10:56:10'),
(14, 33, 11, -1, 'Colombo', '2025-03-10 09:00:00', 'Matara', '200.00', 768.00, 0.00, 24768.00, 1, 2, '2025-03-08 17:54:49'),
(15, 33, 6, -1, 'Matara', '2025-03-11 08:30:00', 'Colombo', '200.00', 1920.00, 0.00, 61920.00, 1, 2, '2025-03-08 17:56:45'),
(16, 33, 18, -1, 'Malabe', '2025-03-22 06:20:00', 'Kandy', '113.00', 723.20, 699.70, 22623.50, 1, 2, '2025-03-09 09:47:24');

-- --------------------------------------------------------

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
CREATE TABLE IF NOT EXISTS `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `config_key` varchar(50) NOT NULL,
  `value` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `description` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `config`
--

INSERT INTO `config` (`id`, `config_key`, `value`, `type`, `description`, `created_at`) VALUES
(1, 'tax', '3.2', 'double', 'Tax percentage on each booking.', '2025-03-08 08:56:08');

-- --------------------------------------------------------

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
CREATE TABLE IF NOT EXISTS `discount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(8) DEFAULT NULL,
  `type` tinyint(1) NOT NULL DEFAULT '0',
  `value` int(11) NOT NULL DEFAULT '0',
  `enabled` tinyint(1) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `discount`
--

INSERT INTO `discount` (`id`, `code`, `type`, `value`, `enabled`, `created_at`) VALUES
(1, 'H7S9S9F8', 0, 100, 1, '2025-03-09 04:14:57'),
(2, 'R45RFD5S', 1, 3, 1, '2025-03-09 04:16:55');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE IF NOT EXISTS `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `booking_id` int(11) NOT NULL,
  `payment_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `card_no` varchar(50) DEFAULT NULL,
  `amount` decimal(10,2) NOT NULL,
  `type` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`id`, `booking_id`, `payment_date`, `card_no`, `amount`, `type`) VALUES
(5, 12, '2025-03-07 11:36:41', '485222666584', '24000.00', 0),
(4, 11, '2025-03-07 10:49:50', '107255669885', '32500.00', 0),
(9, 11, '2025-03-07 17:08:56', '107255669885', '32500.00', 1),
(10, 13, '2025-03-08 10:56:49', '4856122644559966', '4953.60', 0),
(11, 14, '2025-03-08 17:55:25', '5566448855111', '24768.00', 0),
(12, 16, '2025-03-09 09:48:04', '1111222233334444', '22623.50', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `nic` varchar(12) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `mobile` varchar(10) NOT NULL,
  `type` tinyint(4) NOT NULL,
  `password` varchar(256) DEFAULT NULL,
  `verification_code` varchar(6) DEFAULT NULL,
  `is_verified` tinyint(1) NOT NULL DEFAULT '0',
  `access_token` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `nic`, `address`, `email`, `mobile`, `type`, `password`, `verification_code`, `is_verified`, `access_token`, `created_at`) VALUES
(1, 'Arvin Jayanake', '920660487V', '458/3, Ambalangoda', 'arvinjnk05@gmail.com', '0771775727', 3, 'ed968e840d10d2d313a870bc131a4e2c311d7ad09bdf32b3418147221f51a6e2', '111111', 1, '3LUifI32x2Oc9hZ__AgCaQ', '2025-02-25 11:01:33'),
(28, 'Mekala Rangani', '896564645V', 'Ambalangoda', 'mekala@gmail.com', '0714546543', 1, 'ea262e62f8ee04f7d16cc64132d0663108bb7d2211ed479c7a37e5a26e1aee5d', '0000', 1, NULL, '2025-03-07 10:25:03'),
(29, 'Uththara Dulanjali', '905465451554', 'Panadura', 'uththara@gmail.com', '0744546546', 1, '00c4e3b8e087ea61db4b83f9fe50b6f7bf4d1db8f3af6e47eaffead847295cbb', '0000', 1, NULL, '2025-03-07 10:25:41'),
(30, 'Isuranga Madushan', '950112115655', 'Kurunegala', 'isuranga@gamil.com', '0714464645', 1, '7d69af6dd9dc520e0445fa7d64d8608016ea4af116470f5aeca85a2df38f7103', '0000', 1, NULL, '2025-03-07 10:27:01'),
(31, 'Manelka Ramesh', '985224555165', 'Balapitiya', 'manelka@gmail.com', '0715656565', 1, 'ea262e62f8ee04f7d16cc64132d0663108bb7d2211ed479c7a37e5a26e1aee5d', '5971', 1, NULL, '2025-03-07 10:28:38'),
(34, 'Suween Hiyanake', '980656446546', 'Ambalangoda', 'suween@gmail.com', '0741213132', 1, '34993e1460c306da62cdf053b866a1072657b22585de8c440960ca8f040e28a8', '0000', 1, NULL, '2025-03-07 10:35:33'),
(32, 'Dimuthu Lakshan', '960445445555', 'Colombo', 'dimuthu@gmail.com', '0711454555', 1, '6f32657d86a83529f5ec4c6540e51f684dc1a7f7b2b0ddd68f880f0678f6ddc7', '6630', 1, NULL, '2025-03-07 10:32:52'),
(33, 'Thison Nayanake', '950454545545', 'Colombo', 'thison@gmail.com', '0714546546', 1, '37c9aa3186e19d9e3cfb4f2ec195d3a3ba27e280be967bda536828003c94a0d2', '0000', 1, 'IN3EMuKWQ3EtMVqO-yOwqw', '2025-03-07 10:34:29'),
(35, 'Lakshan Chinthaka', '925645444V', 'Colombo', 'lakshan@gmail.com', '0714445454', 2, 'f9430a30431829bfa9420368fc97bbb354b2218bde0d0dacf03cc5cb701082ae', '0000', 1, 'r9Lx_ad3bEzIqakPZBVaSw', '2025-03-07 10:36:36'),
(36, 'Chiranga Nimesh', '926556464646', 'Moratuwa', 'chiranga@gmail.com', '0775566552', 2, 'e286516ca5f14c2f7a078c468bdb78f07b28b488438bded8fd4678a69082949d', '0000', 1, NULL, '2025-03-07 10:40:44'),
(27, 'Tharani Gaweshani', '925445445V', 'Galle', 'tharani@gmail.com', '0771031594', 1, '37c9aa3186e19d9e3cfb4f2ec195d3a3ba27e280be967bda536828003c94a0d2', '0000', 1, 'cMyLRE1Nym2gi0KKY_pYAQ', '2025-03-07 10:23:54'),
(37, 'Chanuka Dias', '985112445445', 'Colombo', 'chanuka@gmail.com', '0715554445', 2, '6304fbfe2b22557c34c42a70056616786a733b3d09fb326308c813d6ab712ec0', '0000', 1, 'V9D2vAnEkTJMpywb0fJaoQ', '2025-03-07 10:43:16'),
(40, 'Praneeth Madhupa', '924451212232', 'Galle', 'praneeth@gmail.com', '0714455455', 1, '7729727efe4ea07963c8bf8976a4ba6225110c9811c28f9181d6ff8114e9b69b', '2341', 1, NULL, '2025-03-08 18:06:30');

-- --------------------------------------------------------

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE IF NOT EXISTS `vehicle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `make` varchar(50) DEFAULT NULL,
  `model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `category` tinyint(1) NOT NULL DEFAULT '0',
  `year` varchar(4) DEFAULT NULL,
  `registration_number` varchar(20) DEFAULT NULL,
  `passenger_capacity` int(11) NOT NULL DEFAULT '0',
  `luggage_capacity` varchar(20) DEFAULT NULL,
  `price_per_km` decimal(10,2) NOT NULL DEFAULT '0.00',
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `vehicle`
--

INSERT INTO `vehicle` (`id`, `make`, `model`, `category`, `year`, `registration_number`, `passenger_capacity`, `luggage_capacity`, `price_per_km`, `status`, `created_at`) VALUES
(4, 'Ford', 'Explorer', 2, '2022', 'GHI101', 7, '4 Large Bags', '300.00', 1, '2025-02-26 06:29:30'),
(21, 'Suzuki', 'Swift RS', 4, '2017', 'CBD6787', 4, '2 Large Bags', '150.00', 0, '2025-03-13 09:50:40'),
(6, 'Toyota', 'Highlander', 2, '2021', 'MNO131', 7, '4 Large Bags', '300.00', 1, '2025-02-26 06:29:30'),
(7, 'Mercedes-Benz', 'Sprinter', 3, '2022', 'PQR415', 12, '8 Large Bags', '320.00', 1, '2025-02-26 06:29:30'),
(19, 'Suzuki', 'Swift RS', 4, '2017', 'CBD6787', 4, '2 Large Bags', '150.00', 0, '2025-03-13 09:46:07'),
(12, 'Hyundai', 'Elantra GT', 4, '2020', 'EFG324', 4, '1 Large Bag', '140.00', 3, '2025-02-26 06:29:30'),
(13, 'Tesla', 'Model 3', 5, '2023', 'HIJ555', 5, '2 Large Bags', '250.00', 1, '2025-02-26 06:29:30'),
(11, 'Nissan', 'Leaf', 5, '2022', 'KLM666', 5, '2 Large Bags', '150.00', 1, '2025-02-26 06:29:30'),
(15, 'Chevrolet', 'Bolt EV', 5, '2021', 'NOP777', 5, '2 Large Bags', '100.00', 1, '2025-02-26 06:29:30'),
(16, 'Honda', 'Vezel', 2, '2015', 'CAL 7374', 5, '4 Large Bags', '250.00', 1, '2025-03-03 05:43:43'),
(20, 'Suzuki', 'Swift RS', 4, '2017', 'CBD6787', 4, '2 Large Bags', '150.00', 0, '2025-03-13 09:46:33'),
(22, 'Suzuki', 'Swift RS', 4, '2017', 'CBD6787', 4, '2 Large Bags', '150.00', 0, '2025-03-13 09:53:01');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
