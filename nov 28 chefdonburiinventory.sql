-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 28, 2024 at 07:38 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

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
  `itemID` int(11) NOT NULL,
  `itemName` varchar(255) NOT NULL,
  `actual` double NOT NULL,
  `alertTime` datetime NOT NULL,
  `resolved` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
(5, 'Bacon', 1.00, 'kg', 'Mall', 'GCash', '2024-11-28 12:23:59', 9, '2024-11-28 12:23:59'),
(6, 'Bananas', 500.00, 'pcs', 'Market', 'Cash', '2024-11-28 14:33:40', 9, '2024-11-28 14:33:40'),
(7, 'Sushi', 300.00, 'pcs', 'Mall', 'Cash', '2024-11-18 22:14:40', NULL, '2024-11-27 22:41:40');

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
(42, 'Frozen Goods', 'Bacon', 'kg', 500.00, '3.5 kg', '', '', '', '', '3.5 kg', NULL, '2024-11-27 22:41:40'),
(43, 'Frozen Goods', 'Banana', 'pcs', 200.00, '14 pcs', '', '', '', '', '14 pcs', NULL, '2024-11-27 22:41:40'),
(44, 'Frozen Goods', 'Bacon', 'kg', 0.00, '3.5 kg', '', '', '', '', '3.5 kg', NULL, '2024-11-27 22:41:40'),
(45, 'Frozen Goods', 'Bacon', 'kg', 500.00, '3.8 kg', '', '', '', '', '3.8 kg', NULL, '2024-11-27 22:41:40'),
(46, 'Frozen Goods', 'Bacon', 'kg', 0.00, '3.8 kg', '', '', '', '', '3.8 kg', NULL, '2024-11-27 22:41:40'),
(47, 'Frozen Goods', 'Bacon', 'kg', 400.00, '3.500 kg', '', '', '', '', '3.500 kg', NULL, '2024-11-27 22:41:40'),
(48, 'Frozen Goods', 'Bacon', 'kg', 0.00, '3.755 kg', '', '', '', '', '3.755 kg', NULL, '2024-11-27 22:41:40'),
(49, 'Frozen Goods', 'Banana', 'pcs', 200.00, '35 pcs', '', '', '', '', '35 pcs', NULL, '2024-11-27 22:41:40'),
(50, 'Frozen Goods', 'Bacon', 'kg', 0.00, '3.500 kg', '', '', '', '', '3.500 kg', NULL, '2024-11-27 22:41:40'),
(51, 'Frozen Goods', 'Bacon', 'kg', 2.00, '3.760 kg', '', '', '', '', '3.760 kg', NULL, '2024-11-27 22:41:40'),
(52, 'Frozen Goods', 'Bacon', 'kg', 2.00, '0.0', '0.1', '0.1', '0.0', '0.0', '0.0 kg', 9, '2024-11-28 10:13:14'),
(54, 'Frozen Goods', 'Bacon', 'kg', 250.00, '19.5 kg', '', '', '', '', '19.5 kg', NULL, '2024-11-27 22:41:40'),
(55, 'Frozen Goods', 'Bacon', 'kg', 350.00, '9.755 kg', '', '', '', '', '9.755 kg', NULL, '2024-11-27 22:41:40'),
(56, 'Frozen Goods', 'Bacon', 'kg', 205.00, '7.735 kg', '', '', '', '', '7.735 kg', NULL, '2024-11-27 22:41:40'),
(60, 'Wrapper', 'Lumpia Wrapper', 'kg', 1.00, '8.7', '0.3', '0.0', '0.0', '0.0', '9.0 kg', 9, '2024-11-28 07:55:00'),
(61, 'Frozen Goods', 'Bacon', 'kg', 2.00, '0.0', '0.0', '0.0', '0.0', '0.0', '0.0 kg', 9, '2024-11-28 14:33:11');

-- --------------------------------------------------------

--
-- Table structure for table `inventory2`
--

CREATE TABLE `inventory2` (
  `ID` int(11) NOT NULL,
  `CATEGORY` varchar(100) DEFAULT NULL,
  `ITEM` varchar(100) DEFAULT NULL,
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

INSERT INTO `inventory2` (`ID`, `CATEGORY`, `ITEM`, `PRICE`, `SF`, `BEGINNING`, `QUANTITY_IN`, `QUANTITY_OUT`, `SPOILAGE`, `ACTUAL`, `LAST_EDITED_BY`, `LAST_EDITED_ON`) VALUES
(1, 'Essentials', 'Spoon', 1.00, 0.00, '9.99', '20.0', '0.0', '0.0', '29.99', 9, '2024-11-28 14:33:31'),
(2, 'Frozen Goods', 'Bacon', 0.20, 0.00, '0.0', '0.0', '0.0', '0.0', '0.00', 9, '2024-11-28 08:03:23');

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
(11, 8, '2024-11-19 11:16:29', '2024-11-19 11:16:37'),
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
(23, 9, '2024-11-27 21:57:16', '2024-11-27 22:01:05'),
(24, 9, '2024-11-27 22:01:31', '2024-11-27 22:11:27'),
(25, 9, '2024-11-27 22:11:43', '2024-11-27 22:56:04'),
(26, 9, '2024-11-27 22:56:16', '2024-11-27 22:56:34'),
(27, 9, '2024-11-27 22:56:37', '2024-11-27 23:03:16'),
(28, 9, '2024-11-27 23:11:55', '2024-11-27 23:17:30'),
(29, 9, '2024-11-27 23:17:49', '2024-11-27 23:19:43'),
(30, 9, '2024-11-27 23:20:01', '2024-11-27 23:26:27'),
(31, 9, '2024-11-27 23:26:39', '2024-11-27 23:53:13'),
(32, 9, '2024-11-27 23:41:16', '2024-11-27 23:53:13'),
(33, 9, '2024-11-27 23:51:48', '2024-11-27 23:53:13'),
(34, 9, '2024-11-27 23:53:34', '2024-11-28 02:13:23'),
(35, 9, '2024-11-27 23:58:48', '2024-11-28 02:13:23'),
(36, 9, '2024-11-28 00:03:13', '2024-11-28 02:13:23'),
(37, 9, '2024-11-28 02:11:32', '2024-11-28 02:13:23'),
(38, 8, '2024-11-28 02:13:40', '2024-11-28 02:18:50'),
(39, 9, '2024-11-28 04:03:43', '2024-11-28 04:18:00'),
(40, 9, '2024-11-28 04:16:10', '2024-11-28 04:18:00'),
(41, 9, '2024-11-28 04:18:11', '2024-11-28 05:04:51'),
(42, 9, '2024-11-28 04:21:36', '2024-11-28 05:04:51'),
(43, 9, '2024-11-28 04:23:43', '2024-11-28 05:04:51'),
(44, 9, '2024-11-28 04:48:17', '2024-11-28 05:04:51'),
(45, 9, '2024-11-28 04:49:43', '2024-11-28 05:04:51'),
(46, 9, '2024-11-28 04:51:37', '2024-11-28 05:04:51'),
(47, 9, '2024-11-28 04:53:33', '2024-11-28 05:04:51'),
(48, 9, '2024-11-28 04:57:35', '2024-11-28 05:04:51'),
(49, 9, '2024-11-28 05:04:35', '2024-11-28 05:04:51'),
(50, 8, '2024-11-28 05:04:57', '2024-11-28 05:53:53'),
(51, 9, '2024-11-28 05:33:32', '2024-11-28 05:35:14'),
(52, 9, '2024-11-28 05:34:58', '2024-11-28 05:35:14'),
(53, 9, '2024-11-28 05:35:54', '2024-11-28 05:41:56'),
(54, 9, '2024-11-28 05:38:17', '2024-11-28 05:41:56'),
(55, 9, '2024-11-28 05:42:44', '2024-11-28 06:30:05'),
(56, 8, '2024-11-28 05:51:48', '2024-11-28 05:53:53'),
(57, 8, '2024-11-28 05:53:31', '2024-11-28 05:53:53'),
(58, 9, '2024-11-28 05:54:16', '2024-11-28 06:30:05'),
(59, 9, '2024-11-28 05:55:15', '2024-11-28 06:30:05'),
(60, 9, '2024-11-28 05:57:29', '2024-11-28 06:30:05'),
(61, 9, '2024-11-28 05:58:56', '2024-11-28 06:30:05'),
(62, 9, '2024-11-28 06:00:36', '2024-11-28 06:30:05'),
(63, 9, '2024-11-28 06:01:32', '2024-11-28 06:30:05'),
(64, 9, '2024-11-28 06:01:50', '2024-11-28 06:30:05'),
(65, 9, '2024-11-28 06:05:16', '2024-11-28 06:30:05'),
(66, 9, '2024-11-28 06:06:18', '2024-11-28 06:30:05'),
(67, 9, '2024-11-28 06:07:38', '2024-11-28 06:30:05'),
(68, 9, '2024-11-28 06:11:02', '2024-11-28 06:30:05'),
(69, 9, '2024-11-28 06:12:19', '2024-11-28 06:30:05'),
(70, 9, '2024-11-28 06:26:07', '2024-11-28 06:30:05'),
(71, 9, '2024-11-28 06:27:33', '2024-11-28 06:30:05'),
(72, 9, '2024-11-28 06:29:10', '2024-11-28 06:30:05'),
(73, 9, '2024-11-28 06:29:31', '2024-11-28 06:30:05'),
(74, 9, '2024-11-28 06:30:10', '2024-11-28 06:31:33'),
(75, 9, '2024-11-28 06:32:15', '2024-11-28 06:32:23'),
(76, 9, '2024-11-28 06:32:46', '2024-11-28 06:37:13');

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
(1, 'He', 'Hi', 'Pasay', '0949', 'https://www.messenger.com/e2ee/t/7457411071038121', 9, '2024-11-28 14:33:57');

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
(8, 'JadeS', '$2a$10$10JF1TaA80qLQ1wNYw.sMOeZjRlQKBKhbrsRSmbS/pqcsKtY07LGq', 'Jade', 'Sta Maria', 'admin'),
(9, 'CHILL GUY', '$2a$10$b58DdnC/NBfDFNDCjlsfbu//udeq0XKwq3IuEz7VmdTot/WgxLxOS', '1', '1', 'Admin');

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
  MODIFY `alertID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `expenses`
--
ALTER TABLE `expenses`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT for table `inventory2`
--
ALTER TABLE `inventory2`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
  MODIFY `logID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=77;

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `SupplierID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

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
