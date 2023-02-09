-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 15, 2022 at 05:58 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `umkm`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id_barang` char(5) NOT NULL,
  `nama_barang` varchar(50) DEFAULT NULL,
  `harga_beli` bigint(15) DEFAULT NULL,
  `harga_jual` bigint(15) DEFAULT NULL,
  `stock` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id_barang`, `nama_barang`, `harga_beli`, `harga_jual`, `stock`) VALUES
('BR001', 'gitar', 50000, 60000, 50),
('BR002', 'drum', 100000, 130000, 60),
('BR003', 'senar', 40000, 60000, 100),
('BR004', 'ketipung', 70000, 90000, 20),
('BR005', 'gitar senar baja allegro', 40000, 60000, 35),
('BR006', 'piano', 70000, 100000, 70),
('BR007', '1 set pick gitar', 5000, 7000, 150),
('BR008', 'pelindung piano', 5000, 10000, 66),
('BR009', 'kentrung', 77000, 150000, 77);

-- --------------------------------------------------------

--
-- Table structure for table `detail_penjualan`
--

CREATE TABLE `detail_penjualan` (
  `id_detailstok` int(2) DEFAULT NULL,
  `id_barang` char(5) DEFAULT NULL,
  `jumlah_jual` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detail_penjualan`
--

INSERT INTO `detail_penjualan` (`id_detailstok`, `id_barang`, `jumlah_jual`) VALUES
(1, 'GB321', 5);

--
-- Triggers `detail_penjualan`
--
DELIMITER $$
CREATE TRIGGER `kurang_stock` AFTER INSERT ON `detail_penjualan` FOR EACH ROW BEGIN
	UPDATE barang
	SET stock= stock - NEW.jumlah_jual
	WHERE `id_barang` = NEW.id_barang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `history_hapus_barang`
--

CREATE TABLE `history_hapus_barang` (
  `id_log` int(10) NOT NULL,
  `id_barang` char(5) DEFAULT NULL,
  `nama_barang` varchar(50) DEFAULT NULL,
  `harga` int(10) DEFAULT NULL,
  `stock` int(5) DEFAULT NULL,
  `waktu` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `history_hapus_barang`
--

INSERT INTO `history_hapus_barang` (`id_log`, `id_barang`, `nama_barang`, `harga`, `stock`, `waktu`) VALUES
(1, 'AG345', 'Angklung', 500000, 3, '2022-11-28');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id_login` char(5) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id_login`, `username`, `password`) VALUES
('lg001', 'joko widodo', '12345'),
('lg002', 'Hartanti', '12345'),
('lg003', 'sukarno', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `log_barang`
--

CREATE TABLE `log_barang` (
  `id_log` int(2) NOT NULL,
  `id_barang` char(5) DEFAULT NULL,
  `harga_lama` int(10) DEFAULT NULL,
  `harga_baru` int(10) DEFAULT NULL,
  `waktu` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `log_barang`
--

INSERT INTO `log_barang` (`id_log`, `id_barang`, `harga_lama`, `harga_baru`, `waktu`) VALUES
(1, 'SL246', 50000, 75000, '2022-11-29'),
(2, 'GY322', 50000000, 50000000, '2022-12-02'),
(3, 'SL246', 75000, 75000, '2022-12-02'),
(4, 'SL246', 75000, 75000, '2022-12-02'),
(5, 'PK231', 200000, 200000, '2022-12-08'),
(6, 'DR123', 5000000, 5000000, '2022-12-08');

-- --------------------------------------------------------

--
-- Table structure for table `pembeli`
--

CREATE TABLE `pembeli` (
  `id_pembeli` char(5) NOT NULL,
  `nama_pembeli` varchar(50) DEFAULT NULL,
  `no_telp_pembeli` varchar(13) DEFAULT NULL,
  `alamat_pembeli` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pembeli`
--

INSERT INTO `pembeli` (`id_pembeli`, `nama_pembeli`, `no_telp_pembeli`, `alamat_pembeli`) VALUES
('IA987', 'Ilhan Abhitah Argo', '085345267456', 'Nganjuk'),
('JI189', 'Johan Indra Maulana', '081026616688', 'Kediri'),
('MR897', 'Muhammad Reza Fadilah', '0881026616688', 'Kediri'),
('TH123', 'Tomi Hartanto', '081298678563', 'Ngawi'),
('ZM768', 'Zaki Maulana', '0812346789287', 'Nganjuk');

-- --------------------------------------------------------

--
-- Table structure for table `penjual`
--

CREATE TABLE `penjual` (
  `id_penjual` char(5) NOT NULL,
  `nama_penjual` varchar(50) DEFAULT NULL,
  `no_telp_penjual` varchar(13) DEFAULT NULL,
  `alamat_penjual` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penjual`
--

INSERT INTO `penjual` (`id_penjual`, `nama_penjual`, `no_telp_penjual`, `alamat_penjual`) VALUES
('KR102', 'Kasiran', '081298678563', 'Nganjuk'),
('KR103', 'Tejo', '081343787909', 'Nganjuk'),
('KR104', 'Irfan Maulana', '085656890112', 'Nganjuk'),
('KR105', 'Hendra ', '081655788100', 'Nganjuk'),
('KR106', 'Bachtiar', '082114676998', 'Nganjuk');

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `id_penjualan` varchar(20) NOT NULL,
  `tanggal` varchar(20) DEFAULT NULL,
  `id_supplier` varchar(10) DEFAULT NULL,
  `totalbeli` decimal(10,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`id_penjualan`, `tanggal`, `id_supplier`, `totalbeli`) VALUES
('PJ101', '16-05-2022', 'SP001', '300000');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `id_supplier` varchar(10) NOT NULL,
  `nama_supplier` varchar(50) DEFAULT NULL,
  `no_telp_supplier` varchar(13) DEFAULT NULL,
  `alamat_supplier` varchar(100) DEFAULT NULL,
  `nama_barang` varchar(50) DEFAULT NULL,
  `stock` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id_supplier`, `nama_supplier`, `no_telp_supplier`, `alamat_supplier`, `nama_barang`, `stock`) VALUES
('SP110', 'Sutris', '081221678901', 'Kediri', 'Gitar Gibson', 50),
('SP112', 'Alex', '081991881771', 'Nganjuk', 'Seruling', 40),
('SP113', 'Tanto', '081433755112', 'Nganjuk', 'Snare Drum', 30),
('SP114', 'Mudya', '081777112001', 'Nganjuk', 'Pianika', 25),
('SP115', 'Mudya', '081777112001', 'Nganjuk', 'Pianika', 20);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` char(5) NOT NULL,
  `username` char(5) NOT NULL,
  `id_pembeli` char(5) NOT NULL,
  `id_barang` char(5) NOT NULL,
  `id_supplier` char(5) NOT NULL,
  `tanggal_transaksi` date DEFAULT NULL,
  `stock` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `username`, `id_pembeli`, `id_barang`, `id_supplier`, `tanggal_transaksi`, `stock`) VALUES
('12345', 'KR102', 'TH123', 'GB321', 'SP110', '2022-11-01', 50),
('12346', 'KR103', 'IA987', 'GY123', 'SP111', '2022-11-02', 50),
('12347', 'KR104', 'JI189', 'PK231', 'SP112', '2022-11-06', 50),
('12348', 'KR105', 'MR897', 'SD456', 'SP113', '2022-10-29', 30),
('1239', 'KR106', 'ZM768', 'SL246', 'SP114', '2022-11-05', 40);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indexes for table `history_hapus_barang`
--
ALTER TABLE `history_hapus_barang`
  ADD PRIMARY KEY (`id_log`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id_login`);

--
-- Indexes for table `log_barang`
--
ALTER TABLE `log_barang`
  ADD PRIMARY KEY (`id_log`);

--
-- Indexes for table `pembeli`
--
ALTER TABLE `pembeli`
  ADD PRIMARY KEY (`id_pembeli`);

--
-- Indexes for table `penjual`
--
ALTER TABLE `penjual`
  ADD PRIMARY KEY (`id_penjual`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`id_penjualan`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id_supplier`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD UNIQUE KEY `id_supplier` (`id_supplier`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `id_penjual` (`username`,`id_pembeli`,`id_barang`),
  ADD KEY `id_pembeli` (`id_pembeli`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `history_hapus_barang`
--
ALTER TABLE `history_hapus_barang`
  MODIFY `id_log` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `log_barang`
--
ALTER TABLE `log_barang`
  MODIFY `id_log` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`username`) REFERENCES `penjual` (`id_penjual`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transaksi_ibfk_3` FOREIGN KEY (`id_pembeli`) REFERENCES `pembeli` (`id_pembeli`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
