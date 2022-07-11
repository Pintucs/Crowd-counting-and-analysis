-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 23, 2020 at 10:01 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `crowd_counting`
--

-- --------------------------------------------------------

--
-- Table structure for table `emergency_found`
--

CREATE TABLE IF NOT EXISTS `emergency_found` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `img` varchar(100) NOT NULL,
  `date_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `emergency_found`
--

INSERT INTO `emergency_found` (`id`, `img`, `date_time`) VALUES
(1, 'upload/390953de2a14be57e98ff945aa5a7f42.jpg', '2019-08-07 16:37:19');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(300) DEFAULT NULL,
  `gender` varchar(30) DEFAULT NULL,
  `email` varchar(300) DEFAULT NULL,
  `contact` varchar(300) DEFAULT NULL,
  `password` varchar(300) DEFAULT NULL,
  `otp` varchar(90) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `pic` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `gender`, `email`, `contact`, `password`, `otp`, `status`, `pic`) VALUES
(6, 'nitin', 'male', 'nitin@gmail.com', '9451009260', '12345678', '627151', 1, 'profile.png');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
