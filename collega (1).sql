-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: simabus-mysql
-- Generation Time: Oct 07, 2020 at 11:06 PM
-- Server version: 5.5.62
-- PHP Version: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `collega`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `cust_id` char(6) NOT NULL DEFAULT '000000',
  `nama` varchar(50) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `pendapatan` decimal(18,2) DEFAULT NULL,
  `kota_id_kota` char(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`cust_id`, `nama`, `alamat`, `pendapatan`, `kota_id_kota`) VALUES
('000001', 'Mochamad Roofi Pandika', 'asdasd', '0.00', 'BDG'),
('000002', 'Mochamad Roofi Pandika', 'asdasd', '0.00', 'BDG'),
('000003', 'Mochamad Roofi Pandika', 'asdasd', '0.00', 'BDG'),
('000004', 'Mochamad Roofi Pandika', 'asdasd', '0.00', 'BDG'),
('000006', 'Mochamad', 'Purwokerto', '0.00', 'BDG'),
('000007', 'Mochamad', 'asdasd', '0.00', 'BDG');

--
-- Triggers `customer`
--
DELIMITER $$
CREATE TRIGGER `RandomCustID` BEFORE INSERT ON `customer` FOR EACH ROW BEGIN

   DECLARE maxid INT Unsigned default 0;
     SET maxid = (SELECT CONVERT(max(cust_id), Unsigned) FROM customer);
   
 IF maxid  IS NULL THEN BEGIN
		SET maxid = 0;
  END;
  ELSE  BEGIN 
  	SET maxid =maxid +1;
  END;
  END IF;
 
 
  

    SET NEW.cust_id = CONCAT('0', LPAD(maxid, 5, '0'));



END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `kota`
--

CREATE TABLE `kota` (
  `id_kota` char(3) NOT NULL,
  `nama` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kota`
--

INSERT INTO `kota` (`id_kota`, `nama`) VALUES
('BDG', 'Bandung'),
('JKT', 'Jakarta'),
('LOP', 'Lombok');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`cust_id`),
  ADD KEY `fk_customer_kota_idx` (`kota_id_kota`);

--
-- Indexes for table `kota`
--
ALTER TABLE `kota`
  ADD PRIMARY KEY (`id_kota`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `fk_customer_kota` FOREIGN KEY (`kota_id_kota`) REFERENCES `kota` (`id_kota`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
