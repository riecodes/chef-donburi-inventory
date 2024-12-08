-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Dec 07, 2024 at 03:31 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `chefdonburiinventory`
--

-- --------------------------------------------------------

--
-- Table structure for table `alerts`
--

CREATE TABLE `alerts` (
  `alertID` int(11) NOT NULL,
  `category` varchar(255) NOT NULL,
  `itemID` int(11) NOT NULL,
  `itemName` varchar(255) NOT NULL,
  `actual` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `alerts`
--

INSERT INTO `alerts` (`alertID`, `category`, `itemID`, `itemName`, `actual`) VALUES
(133, 'Frozen Goods', 62, 'Bacon', 0),
(134, 'Frozen Goods', 63, 'Beef', 0),
(135, 'Frozen Goods', 64, 'Ground Pork', 0),
(136, 'Frozen Goods', 65, 'Kani', 0),
(137, 'Essentials', 3, 'Kani', 0),
(138, 'Frozen Goods', 66, 'Bacon', 0),
(139, 'Frozen Goods', 67, 'Beef', 0),
(140, 'Frozen Goods', 68, 'Ground Pork', 0),
(141, 'Frozen Goods', 69, 'Kani', 0),
(142, 'Frozen Goods', 70, 'Chicken', 0),
(143, 'Frozen Goods', 71, 'Pork', 0),
(144, 'Vegetables', 72, 'Onion', 5),
(145, 'Sushi', 73, 'SUHU', 0),
(146, 'Frozen Goods', 75, 'bacon', 1),
(147, 'Frozen Goods', 76, 'Bacon', 3),
(148, 'Dry Ingredients', 79, '10', 20),
(149, 'Frozen Goods', 77, 'Spoon', 19),
(150, 'Sushi', 78, 'Hello', 10),
(151, 'Essentials', 4, 'Spoon', 20);

-- --------------------------------------------------------

--
-- Table structure for table `expenses`
--

CREATE TABLE `expenses` (
  `ID` int(11) NOT NULL,
  `ITEM_NAME` varchar(100) NOT NULL,
  `ITEM_PRICE` decimal(10,2) NOT NULL,
  `NUMBER_UNIT` varchar(100) NOT NULL,
  `SOURCE` varchar(100) NOT NULL,
  `MODE_OF_PAYMENT` varchar(255) DEFAULT NULL,
  `DATE_TIME` datetime DEFAULT NULL,
  `LAST_EDITED_BY` int(11) DEFAULT NULL,
  `LAST_EDITED_ON` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `expenses`
--

INSERT INTO `expenses` (`ID`, `ITEM_NAME`, `ITEM_PRICE`, `NUMBER_UNIT`, `SOURCE`, `MODE_OF_PAYMENT`, `DATE_TIME`, `LAST_EDITED_BY`, `LAST_EDITED_ON`) VALUES
(5, 'Bacon', 1.00, 'kg', 'Mall', 'GCash', '2024-11-28 12:23:59', NULL, '2024-11-28 12:23:59'),
(6, 'Bananas', 500.00, 'pcs', 'Market', 'Cash', '2024-11-28 14:33:40', NULL, '2024-11-28 14:33:40');

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `ID` int(11) NOT NULL,
  `CATEGORY` varchar(100) DEFAULT NULL,
  `ITEMS` varchar(100) DEFAULT NULL,
  `UNIT` varchar(50) DEFAULT NULL,
  `PRICE` decimal(10,2) DEFAULT NULL,
  `BEGINNING` varchar(50) DEFAULT NULL,
  `QUANTITY_IN` varchar(50) DEFAULT NULL,
  `QUANTITY_OUT` varchar(50) DEFAULT NULL,
  `SCRAP` varchar(50) DEFAULT NULL,
  `SPOILAGE` varchar(50) DEFAULT NULL,
  `ACTUAL` varchar(50) DEFAULT NULL,
  `LAST_EDITED_BY` int(11) DEFAULT NULL,
  `LAST_EDITED_ON` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`ID`, `CATEGORY`, `ITEMS`, `UNIT`, `PRICE`, `BEGINNING`, `QUANTITY_IN`, `QUANTITY_OUT`, `SCRAP`, `SPOILAGE`, `ACTUAL`, `LAST_EDITED_BY`, `LAST_EDITED_ON`) VALUES
(75, 'Frozen Goods', 'bacon', 'kg', 50.00, '1.0', '0.5', '0.5', '0.0', '0.0', '1.0 kg', 8, '2024-12-03 20:07:57'),
(76, 'Frozen Goods', 'Bacon', 'kg', 20.00, '0.5', '0.0', '0.0', '0.0', '0.0', '0.5 kg', 8, '2024-12-07 21:16:52'),
(77, 'Frozen Goods', 'Spoon', 'pcs', 19.00, '19.0', '0.0', '0.0', '0.0', '0.0', '19 pcs', 8, '2024-12-07 21:41:24'),
(78, 'Sushi', 'Hello', 'pcs', 100.00, '10.0', '0.0', '0.0', '0.0', '0.0', '10 pcs', 8, '2024-12-07 21:15:57'),
(79, 'Dry Ingredients', '10', 'pcs', 50.00, '21.0', '0.0', '0.0', '0.0', '0.0', '21 pcs', 8, '2024-12-07 22:21:54');

-- --------------------------------------------------------

--
-- Table structure for table `inventory2`
--

CREATE TABLE `inventory2` (
  `ID` int(11) NOT NULL,
  `CATEGORY` varchar(100) DEFAULT NULL,
  `ITEMS` varchar(100) DEFAULT NULL,
  `PRICE` decimal(10,2) DEFAULT NULL,
  `SF` decimal(10,2) DEFAULT NULL,
  `BEGINNING` varchar(50) DEFAULT NULL,
  `QUANTITY_IN` varchar(50) DEFAULT NULL,
  `QUANTITY_OUT` varchar(50) DEFAULT NULL,
  `SPOILAGE` varchar(50) DEFAULT NULL,
  `ACTUAL` varchar(50) DEFAULT NULL,
  `LAST_EDITED_BY` int(11) DEFAULT NULL,
  `LAST_EDITED_ON` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventory2`
--

INSERT INTO `inventory2` (`ID`, `CATEGORY`, `ITEMS`, `PRICE`, `SF`, `BEGINNING`, `QUANTITY_IN`, `QUANTITY_OUT`, `SPOILAGE`, `ACTUAL`, `LAST_EDITED_BY`, `LAST_EDITED_ON`) VALUES
(4, 'Essentials', 'Spoon', 0.00, 0.00, '20.0', '0.0', '0.0', '0.0', '20.0', 8, '2024-12-07 22:21:16');

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE `logs` (
  `logID` int(11) NOT NULL,
  `userID` int(11) DEFAULT NULL,
  `loginTime` timestamp NOT NULL DEFAULT current_timestamp(),
  `logoutTime` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `logs`
--

INSERT INTO `logs` (`logID`, `userID`, `loginTime`, `logoutTime`) VALUES
(12, 8, '2024-11-19 11:25:32', '2024-11-19 11:26:42'),
(13, 8, '2024-11-19 11:57:22', '2024-11-19 11:57:30'),
(14, 8, '2024-11-26 13:07:32', '2024-11-26 13:08:49'),
(15, 8, '2024-11-26 13:09:10', '2024-11-26 14:01:22'),
(16, 8, '2024-11-27 13:57:19', '2024-11-27 14:23:52'),
(17, 8, '2024-11-27 14:28:03', '2024-11-27 21:05:22'),
(18, 8, '2024-11-27 21:04:22', '2024-11-27 21:05:22'),
(19, 8, '2024-11-27 21:05:40', '2024-11-27 21:05:56'),
(20, 8, '2024-11-27 21:45:35', '2024-11-27 21:55:40'),
(21, 8, '2024-11-27 21:56:14', '2024-11-27 21:56:28'),
(22, 8, '2024-11-27 21:56:47', '2024-11-27 21:56:59'),
(38, 8, '2024-11-28 02:13:40', '2024-11-28 02:18:50'),
(50, 8, '2024-11-28 05:04:57', '2024-11-28 05:53:53'),
(56, 8, '2024-11-28 05:51:48', '2024-11-28 05:53:53'),
(57, 8, '2024-11-28 05:53:31', '2024-11-28 05:53:53'),
(84, 8, '2024-11-28 07:47:14', '2024-11-28 07:47:21'),
(85, 8, '2024-11-28 07:47:59', '2024-11-28 11:59:48'),
(86, 8, '2024-11-28 07:51:16', '2024-11-28 11:59:48'),
(87, 8, '2024-11-28 07:51:41', '2024-11-28 11:59:48'),
(89, 8, '2024-11-28 11:59:40', '2024-11-28 11:59:48'),
(90, 8, '2024-11-29 15:19:49', '2024-11-29 15:19:55'),
(143, 8, '2024-11-30 05:05:21', '2024-11-30 19:50:02'),
(144, 8, '2024-11-30 19:49:12', '2024-11-30 19:50:02'),
(145, 8, '2024-11-30 19:51:04', '2024-11-30 19:54:28'),
(146, 8, '2024-11-30 20:13:46', '2024-11-30 20:19:47'),
(147, 8, '2024-11-30 20:34:11', '2024-11-30 20:35:21'),
(148, 8, '2024-11-30 20:36:18', '2024-11-30 20:36:47'),
(149, 8, '2024-12-01 19:46:50', '2024-12-01 20:50:03'),
(150, 8, '2024-12-01 20:52:04', '2024-12-01 20:53:11'),
(151, 8, '2024-12-01 20:54:35', '2024-12-01 21:10:01'),
(152, 8, '2024-12-01 21:09:43', '2024-12-01 21:10:01'),
(153, 8, '2024-12-01 21:12:14', '2024-12-01 21:12:21'),
(154, 8, '2024-12-01 21:14:13', '2024-12-01 21:14:37'),
(155, 8, '2024-12-01 21:26:10', '2024-12-01 21:39:24'),
(156, 8, '2024-12-01 21:37:13', '2024-12-01 21:39:24'),
(157, 8, '2024-12-01 21:41:56', '2024-12-01 21:42:17'),
(158, 8, '2024-12-01 21:45:05', '2024-12-01 21:46:25'),
(159, 8, '2024-12-01 21:46:12', '2024-12-01 21:46:25'),
(160, 8, '2024-12-01 21:48:28', '2024-12-01 21:49:27'),
(161, 8, '2024-12-01 21:49:39', '2024-12-01 21:51:56'),
(162, 8, '2024-12-01 22:02:33', '2024-12-01 22:02:51'),
(163, 8, '2024-12-01 22:03:21', '2024-12-01 22:03:27'),
(164, 8, '2024-12-01 22:06:11', '2024-12-01 22:06:46'),
(165, 8, '2024-12-01 22:09:11', '2024-12-01 22:13:28'),
(166, 8, '2024-12-01 22:13:40', '2024-12-01 22:13:54'),
(167, 8, '2024-12-01 22:15:09', '2024-12-01 22:15:32'),
(168, 8, '2024-12-01 22:16:17', '2024-12-01 22:19:25'),
(169, 8, '2024-12-01 22:19:20', '2024-12-01 22:19:25'),
(170, 8, '2024-12-01 22:26:00', '2024-12-01 22:26:15'),
(171, 8, '2024-12-01 22:27:13', '2024-12-01 22:38:41'),
(172, 8, '2024-12-01 22:42:01', '2024-12-01 22:42:13'),
(173, 8, '2024-12-01 22:42:52', '2024-12-01 22:44:04'),
(174, 8, '2024-12-01 22:49:30', '2024-12-01 22:50:15'),
(175, 8, '2024-12-01 22:53:04', '2024-12-01 22:53:23'),
(176, 8, '2024-12-01 23:02:42', '2024-12-01 23:02:54'),
(177, 8, '2024-12-01 23:04:18', '2024-12-01 23:09:37'),
(178, 8, '2024-12-01 23:13:36', '2024-12-01 23:13:55'),
(179, 8, '2024-12-01 23:15:57', '2024-12-01 23:16:10'),
(180, 8, '2024-12-01 23:16:57', '2024-12-01 23:21:29'),
(181, 8, '2024-12-01 23:21:53', '2024-12-01 23:22:16'),
(182, 8, '2024-12-01 23:23:14', '2024-12-01 23:26:34'),
(183, 8, '2024-12-01 23:26:09', '2024-12-01 23:26:34'),
(184, 8, '2024-12-01 23:28:43', '2024-12-01 23:29:02'),
(185, 8, '2024-12-01 23:38:17', '2024-12-01 23:39:15'),
(186, 8, '2024-12-01 23:42:56', '2024-12-01 23:43:57'),
(187, 8, '2024-12-01 23:47:37', '2024-12-01 23:47:51'),
(188, 8, '2024-12-01 23:49:15', '2024-12-01 23:49:28'),
(189, 8, '2024-12-02 00:01:28', '2024-12-02 00:16:32'),
(190, 8, '2024-12-02 00:15:56', '2024-12-02 00:16:32'),
(191, 8, '2024-12-02 00:17:56', '2024-12-02 00:18:11'),
(192, 8, '2024-12-02 00:20:24', '2024-12-02 00:23:42'),
(193, 8, '2024-12-02 00:23:51', '2024-12-02 00:24:39'),
(194, 8, '2024-12-02 00:26:31', '2024-12-02 00:26:52'),
(195, 8, '2024-12-02 00:29:55', '2024-12-02 00:30:18'),
(196, 8, '2024-12-02 00:32:52', '2024-12-02 00:32:59'),
(197, 8, '2024-12-02 00:34:00', '2024-12-02 00:34:32'),
(198, 8, '2024-12-02 00:35:02', '2024-12-02 00:35:14'),
(199, 8, '2024-12-02 00:35:48', '2024-12-02 00:36:06'),
(200, 8, '2024-12-02 00:44:13', '2024-12-02 00:44:20'),
(201, 8, '2024-12-02 00:48:40', '2024-12-02 00:49:35'),
(202, 8, '2024-12-02 00:51:03', '2024-12-02 00:51:33'),
(203, 8, '2024-12-02 00:54:47', '2024-12-02 00:55:04'),
(204, 8, '2024-12-02 01:23:11', '2024-12-02 01:24:26'),
(205, 8, '2024-12-02 01:28:09', '2024-12-02 01:30:20'),
(206, 8, '2024-12-02 01:30:37', '2024-12-02 01:30:50'),
(207, 8, '2024-12-02 01:31:20', '2024-12-02 01:31:33'),
(208, 8, '2024-12-02 01:33:32', '2024-12-02 01:37:34'),
(209, 8, '2024-12-02 01:43:23', '2024-12-02 01:43:58'),
(210, 8, '2024-12-02 01:50:31', '2024-12-02 01:51:47'),
(211, 8, '2024-12-02 01:52:00', '2024-12-02 01:59:01'),
(212, 8, '2024-12-02 01:57:52', '2024-12-02 01:59:01'),
(213, 8, '2024-12-02 02:01:58', '2024-12-02 02:03:44'),
(214, 8, '2024-12-02 02:06:28', '2024-12-02 02:06:55'),
(215, 8, '2024-12-02 02:09:04', '2024-12-02 02:10:42'),
(216, 8, '2024-12-02 02:11:26', '2024-12-02 02:19:43'),
(217, 8, '2024-12-02 02:18:40', '2024-12-02 02:19:43'),
(218, 8, '2024-12-03 07:28:47', '2024-12-03 07:33:03'),
(219, 8, '2024-12-03 07:34:14', '2024-12-03 07:50:34'),
(220, 8, '2024-12-03 07:50:48', '2024-12-03 08:01:41'),
(221, 8, '2024-12-03 08:29:41', '2024-12-03 10:02:29'),
(222, 8, '2024-12-03 10:02:38', '2024-12-03 10:43:51'),
(223, 13, '2024-12-03 10:43:58', '2024-12-03 10:44:02'),
(224, 11, '2024-12-03 10:44:07', '2024-12-03 10:44:10'),
(225, 8, '2024-12-03 10:44:32', '2024-12-03 11:20:18'),
(226, 11, '2024-12-03 11:20:25', '2024-12-03 11:20:29'),
(227, 8, '2024-12-03 11:28:22', '2024-12-03 12:37:36'),
(228, 8, '2024-12-04 18:37:25', '2024-12-04 18:37:40'),
(229, 8, '2024-12-07 12:42:39', '2024-12-07 12:43:00'),
(230, 8, '2024-12-07 12:48:19', '2024-12-07 12:48:30'),
(231, 8, '2024-12-07 12:56:30', '2024-12-07 12:56:38'),
(232, 8, '2024-12-07 12:57:15', '2024-12-07 12:57:33'),
(233, 8, '2024-12-07 12:58:05', '2024-12-07 12:58:16'),
(234, 8, '2024-12-07 13:03:03', '2024-12-07 13:08:22'),
(235, 8, '2024-12-07 13:08:55', '2024-12-07 13:09:02'),
(236, 8, '2024-12-07 13:09:54', '2024-12-07 13:10:08'),
(237, 8, '2024-12-07 13:10:34', '2024-12-07 13:10:44'),
(238, 8, '2024-12-07 13:15:06', '2024-12-07 13:17:12'),
(239, 8, '2024-12-07 13:17:43', '2024-12-07 13:18:55'),
(240, 8, '2024-12-07 13:19:48', '2024-12-07 13:20:04'),
(241, 8, '2024-12-07 13:22:29', '2024-12-07 13:24:00'),
(242, 8, '2024-12-07 13:24:47', '2024-12-07 13:25:37'),
(243, 8, '2024-12-07 13:29:10', '2024-12-07 13:29:22'),
(244, 8, '2024-12-07 13:29:54', '2024-12-07 13:30:12'),
(245, 8, '2024-12-07 13:34:03', '2024-12-07 13:34:34'),
(246, 8, '2024-12-07 13:38:27', '2024-12-07 13:39:58'),
(247, 8, '2024-12-07 13:39:36', '2024-12-07 13:39:58'),
(248, 8, '2024-12-07 13:40:43', '2024-12-07 13:42:04'),
(249, 8, '2024-12-07 13:43:57', '2024-12-07 13:44:12'),
(250, 8, '2024-12-07 13:46:04', '2024-12-07 13:46:11'),
(251, 8, '2024-12-07 13:51:44', '2024-12-07 13:52:00'),
(252, 8, '2024-12-07 13:54:28', '2024-12-07 13:54:36'),
(253, 8, '2024-12-07 14:09:42', '2024-12-07 14:09:51'),
(254, 8, '2024-12-07 14:16:47', '2024-12-07 14:17:52'),
(255, 8, '2024-12-07 14:20:46', '2024-12-07 14:23:12'),
(256, 8, '2024-12-07 14:28:06', '2024-12-07 14:28:24');

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `SupplierID` int(11) NOT NULL,
  `SupplierName` varchar(100) NOT NULL,
  `Description` text DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `ContactNumber` varchar(15) DEFAULT NULL,
  `StoreLink` varchar(255) DEFAULT NULL,
  `LAST_EDITED_BY` int(11) DEFAULT NULL,
  `LAST_EDITED_ON` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`SupplierID`, `SupplierName`, `Description`, `Address`, `ContactNumber`, `StoreLink`, `LAST_EDITED_BY`, `LAST_EDITED_ON`) VALUES
(1, 'He', 'Hi', 'Pasay', '0949', 'https://www.messenger.com/e2ee/t/7457411071038121', NULL, '2024-11-28 14:33:57');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `userName` varchar(50) NOT NULL,
  `userPassword` varchar(100) NOT NULL,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `userName`, `userPassword`, `firstname`, `lastname`, `role`) VALUES
(8, 'JadeS', '$2a$10$10JF1TaA80qLQ1wNYw.sMOeZjRlQKBKhbrsRSmbS/pqcsKtY07LGq', 'Jade', 'Sta Maria', 'Admin'),
(11, 'vj', '$2a$10$QJ9hc53hSOUW5aH9VwgcZeTMgczM2BMBtZqtXi85ZdFJkH7UL104m', 'vj', 'vj', 'Admin'),
(12, 'oas', '$2a$10$XYs2kb3MLHumVP1RSjEPUeaXW.xMtSKIK1bRvW1Sdc3xGahKnK3nO', 'oas', 'oas', 'Admin'),
(13, 'mark', '$2a$10$scKkwFIrY7HSyVIJ31SHV.NImpTGVD8Y6n.q8joZKR2p/Xg5q483O', 'mark', 'mark', 'Admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alerts`
--
ALTER TABLE `alerts`
  ADD PRIMARY KEY (`alertID`);

--
-- Indexes for table `expenses`
--
ALTER TABLE `expenses`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `userID` (`LAST_EDITED_BY`) USING BTREE;

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `userID` (`LAST_EDITED_BY`) USING BTREE;

--
-- Indexes for table `inventory2`
--
ALTER TABLE `inventory2`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `userID` (`LAST_EDITED_BY`) USING BTREE;

--
-- Indexes for table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`logID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`SupplierID`),
  ADD KEY `userID` (`LAST_EDITED_BY`) USING BTREE;

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `alerts`
--
ALTER TABLE `alerts`
  MODIFY `alertID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=152;

--
-- AUTO_INCREMENT for table `expenses`
--
ALTER TABLE `expenses`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;

--
-- AUTO_INCREMENT for table `inventory2`
--
ALTER TABLE `inventory2`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
  MODIFY `logID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=257;

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `SupplierID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `expenses`
--
ALTER TABLE `expenses`
  ADD CONSTRAINT `fk_expenses_LASTEDITEDBY` FOREIGN KEY (`LAST_EDITED_BY`) REFERENCES `users` (`userID`) ON DELETE SET NULL;

--
-- Constraints for table `inventory`
--
ALTER TABLE `inventory`
  ADD CONSTRAINT `fk_inventory_LASTEDITEDBY` FOREIGN KEY (`LAST_EDITED_BY`) REFERENCES `users` (`userID`) ON DELETE SET NULL;

--
-- Constraints for table `inventory2`
--
ALTER TABLE `inventory2`
  ADD CONSTRAINT `fk_inventory2_LASTEDITEDBY` FOREIGN KEY (`LAST_EDITED_BY`) REFERENCES `users` (`userID`) ON DELETE SET NULL;

--
-- Constraints for table `logs`
--
ALTER TABLE `logs`
  ADD CONSTRAINT `logs_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`) ON DELETE CASCADE;

--
-- Constraints for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD CONSTRAINT `fk_suppliers_LASTEDITEDBY` FOREIGN KEY (`LAST_EDITED_BY`) REFERENCES `users` (`userID`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
