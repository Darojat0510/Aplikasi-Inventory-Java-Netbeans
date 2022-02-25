-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 06 Mar 2021 pada 20.00
-- Versi server: 10.4.14-MariaDB
-- Versi PHP: 7.3.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inventory`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `id` varchar(20) NOT NULL,
  `nama` varchar(225) NOT NULL,
  `username` varchar(225) NOT NULL,
  `password` varchar(225) NOT NULL,
  `hakakses` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`id`, `nama`, `username`, `password`, `hakakses`) VALUES
('A002', 'Joni', 'joni', '12345678', 'petugas'),
('A003', 'Mario', 'mario', '12345678', 'petugas');

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `kodebarang` varchar(11) NOT NULL,
  `namabarang` varchar(225) NOT NULL,
  `jenisbarang` varchar(225) NOT NULL,
  `stok` varchar(120) NOT NULL,
  `status` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`kodebarang`, `namabarang`, `jenisbarang`, `stok`, `status`) VALUES
('A002', 'Joni', 'Joni', '1234567', 'petugas'),
('B002', 'bbbb', 'Elektronik', '30', 'Baik'),
('B003', 'ajat', 'Elektronik', '90', 'Baik'),
('B004', 'ajat', 'Elektronik', '156', 'Baik');

-- --------------------------------------------------------

--
-- Struktur dari tabel `keluar`
--

CREATE TABLE `keluar` (
  `notransaksi` varchar(20) NOT NULL,
  `tgltransaksi` date NOT NULL,
  `kodesupplier` varchar(20) NOT NULL,
  `namasupplier` varchar(225) NOT NULL,
  `kodebarang` varchar(20) NOT NULL,
  `namabarang` varchar(225) NOT NULL,
  `jumlahkeluar` varchar(50) NOT NULL,
  `keteramgan` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `keluar`
--

INSERT INTO `keluar` (`notransaksi`, `tgltransaksi`, `kodesupplier`, `namasupplier`, `kodebarang`, `namabarang`, `jumlahkeluar`, `keteramgan`) VALUES
('K001', '2021-03-18', 'babababab', 'bababbaba', 'bababab', 'babababba', '50', 'Baik'),
('K002', '2021-03-06', 'S003', 'pt mari jaya', 'B004', 'ajat', '40', 'Baik');

-- --------------------------------------------------------

--
-- Struktur dari tabel `masuk`
--

CREATE TABLE `masuk` (
  `notransaksi` varchar(20) NOT NULL,
  `tgltransaksi` date NOT NULL,
  `kodesupplier` varchar(20) NOT NULL,
  `namasupplier` varchar(225) NOT NULL,
  `kodebarang` varchar(20) NOT NULL,
  `namabarang` varchar(225) NOT NULL,
  `jumlahmasuk` varchar(50) NOT NULL,
  `keterangan` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `masuk`
--

INSERT INTO `masuk` (`notransaksi`, `tgltransaksi`, `kodesupplier`, `namasupplier`, `kodebarang`, `namabarang`, `jumlahmasuk`, `keterangan`) VALUES
('K002', '2021-03-03', 'S003', 'pt mari jaya', 'B003', 'ajat', '20', 'Baik'),
('K003', '2021-03-03', 'S002', 'Pt indonesia Raya', 'B004', 'ajat', '20', 'Baik'),
('K004', '2021-03-03', 'S003', 'pt mari jaya', 'B004', 'ajat', '20', 'Baik'),
('K005', '2021-03-03', 'S002', 'Pt indonesia Raya', 'B004', 'ajat', '30', 'Baik'),
('K006', '2021-03-02', 'S003', 'pt mari jaya', 'B004', 'ajat', '30', 'Baik'),
('K007', '2021-03-03', 'S001', 'Pt Mulia Bersama', 'B004', 'ajat', '30', 'Baik'),
('K008', '2021-03-02', 'S002', 'Pt indonesia Raya', 'B004', 'ajat', '30', 'Baik'),
('K009', '2021-03-01', 'S003', 'pt mari jaya', 'B003', 'ajat', '30', 'Baik'),
('K010', '2021-03-19', 'S003', 'pt mari jaya', 'B004', 'ajat', '10', 'Baik'),
('K011', '2021-03-20', 'S003', 'pt mari jaya', 'B004', 'ajat', '6', 'Baik');

-- --------------------------------------------------------

--
-- Struktur dari tabel `supplier`
--

CREATE TABLE `supplier` (
  `kodesupplier` varchar(11) NOT NULL,
  `namasupplier` varchar(225) NOT NULL,
  `email` varchar(120) NOT NULL,
  `notelpon` varchar(120) NOT NULL,
  `alamat` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `supplier`
--

INSERT INTO `supplier` (`kodesupplier`, `namasupplier`, `email`, `notelpon`, `alamat`) VALUES
('S001', 'Pt Mulia Bersama', 'muliabersama@gmail.com', '03201200100', 'jl majaphit raya'),
('S002', 'Pt indonesia Raya', 'indonesia raya no 32', '03191029109', 'jl merdeka raya'),
('S003', 'pt mari jaya', 'marijaya@gmail,com', '03810291099', 'jl mari jaya no31');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kodebarang`);

--
-- Indeks untuk tabel `keluar`
--
ALTER TABLE `keluar`
  ADD PRIMARY KEY (`notransaksi`);

--
-- Indeks untuk tabel `masuk`
--
ALTER TABLE `masuk`
  ADD PRIMARY KEY (`notransaksi`);

--
-- Indeks untuk tabel `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`kodesupplier`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
