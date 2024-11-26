-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Nov 19, 2024 at 02:37 PM
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
-- Table structure for table `expenses`
--

CREATE TABLE `expenses` (
  `ID` int(11) NOT NULL,
  `ITEM_NAME` varchar(100) NOT NULL,
  `ITEM_PRICE` decimal(10,2) NOT NULL,
  `NUMBER_UNIT` varchar(100) NOT NULL,
  `SOURCE` varchar(100) NOT NULL,
  `MODE_OF_PAYMENT` varchar(255) DEFAULT NULL,
  `DATE_TIME` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `expenses`
--

INSERT INTO `expenses` (`ID`, `ITEM_NAME`, `ITEM_PRICE`, `NUMBER_UNIT`, `SOURCE`, `MODE_OF_PAYMENT`, `DATE_TIME`) VALUES
(5, 'Bacon', 200.00, 'kg', 'Mall', 'GCash', '2024-11-18 22:19:58'),
(6, 'Banana', 500.00, 'pcs', 'Market', 'Cash', '2024-11-18 22:05:12'),
(7, 'Sushi', 300.00, 'pcs', 'Mall', 'Cash', '2024-11-18 22:14:40');

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
  `ACTUAL` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`ID`, `CATEGORY`, `ITEMS`, `UNIT`, `PRICE`, `BEGINNING`, `QUANTITY_IN`, `QUANTITY_OUT`, `SCRAP`, `SPOILAGE`, `ACTUAL`) VALUES
(42, 'Frozen Goods', 'Bacon', 'kg', 500.00, '3.5 kg', '', '', '', '', '3.5 kg'),
(43, 'Frozen Goods', 'Banana', 'pcs', 200.00, '14 pcs', '', '', '', '', '14 pcs'),
(44, 'Frozen Goods', 'Bacon', 'kg', 0.00, '3.5 kg', '', '', '', '', '3.5 kg'),
(45, 'Frozen Goods', 'Bacon', 'kg', 500.00, '3.8 kg', '', '', '', '', '3.8 kg'),
(46, 'Frozen Goods', 'Bacon', 'kg', 0.00, '3.8 kg', '', '', '', '', '3.8 kg'),
(47, 'Frozen Goods', 'Bacon', 'kg', 400.00, '3.500 kg', '', '', '', '', '3.500 kg'),
(48, 'Frozen Goods', 'Bacon', 'kg', 0.00, '3.755 kg', '', '', '', '', '3.755 kg'),
(49, 'Frozen Goods', 'Banana', 'pcs', 200.00, '35 pcs', '', '', '', '', '35 pcs'),
(50, 'Frozen Goods', 'Bacon', 'kg', 0.00, '3.500 kg', '', '', '', '', '3.500 kg'),
(51, 'Frozen Goods', 'Bacon', 'kg', 2.00, '3.760 kg', '', '', '', '', '3.760 kg'),
(52, 'Frozen Goods', 'Bacon', '', 0.00, '0.0', '', '', '', '', '0.0'),
(54, 'Frozen Goods', 'Bacon', 'kg', 250.00, '19.5 kg', '', '', '', '', '19.5 kg'),
(55, 'Frozen Goods', 'Bacon', 'kg', 350.00, '9.755 kg', '', '', '', '', '9.755 kg'),
(56, 'Frozen Goods', 'Bacon', 'kg', 205.00, '7.735 kg', '', '', '', '', '7.735 kg'),
(60, 'Wrapper', 'Lumpia Wrapper', 'kg', 100.00, '8.7', '0.3', '0.0', '0.0', '0.0', '9.0 kg'),
(61, 'Frozen Goods', 'Bacon', '', 0.00, '0.0', '0.0', '0.0', '0.0', '0.0', '0.000');

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
  `ACTUAL` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventory2`
--

INSERT INTO `inventory2` (`ID`, `CATEGORY`, `ITEM`, `PRICE`, `SF`, `BEGINNING`, `QUANTITY_IN`, `QUANTITY_OUT`, `SPOILAGE`, `ACTUAL`) VALUES
(1, 'Essentials', 'Spoon', 0.00, 0.00, '9.99', '20.0', '0.0', '0.0', '29.990000000000002'),
(2, 'Frozen Goods', 'Bacon', 0.00, 0.00, '0.0', '0.0', '0.0', '0.0', '0.00');

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
(13, 8, '2024-11-19 11:57:22', '2024-11-19 11:57:30');

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
  `StoreLink` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`SupplierID`, `SupplierName`, `Description`, `Address`, `ContactNumber`, `StoreLink`) VALUES
(1, 'Hello', 'Hi', 'Pasay', '0949', 'https://www.messenger.com/e2ee/t/7457411071038121');

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
(8, 'JadeS', '$2a$10$10JF1TaA80qLQ1wNYw.sMOeZjRlQKBKhbrsRSmbS/pqcsKtY07LGq', 'Jade', 'Sta Maria', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `expenses`
--
ALTER TABLE `expenses`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `inventory2`
--
ALTER TABLE `inventory2`
  ADD PRIMARY KEY (`ID`);

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
  ADD PRIMARY KEY (`SupplierID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

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
  MODIFY `logID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `SupplierID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `logs`
--
ALTER TABLE `logs`
  ADD CONSTRAINT `logs_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
