-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 27, 2026 at 03:51 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shared_calendar`
--

-- --------------------------------------------------------

--
-- Table structure for table `calendars`
--

CREATE TABLE `calendars` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `calendars`
--

INSERT INTO `calendars` (`id`, `name`) VALUES
(1, 'Zajednički kalendar');

-- --------------------------------------------------------

--
-- Table structure for table `calendar_users`
--

CREATE TABLE `calendar_users` (
  `id` int(11) NOT NULL,
  `calendar_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `calendar_users`
--

INSERT INTO `calendar_users` (`id`, `calendar_id`, `user_id`) VALUES
(3, 1, 3),
(4, 1, 4);

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE `events` (
  `id` int(11) NOT NULL,
  `calendar_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` text DEFAULT NULL,
  `start` datetime NOT NULL,
  `end` datetime NOT NULL,
  `color` varchar(7) DEFAULT NULL,
  `repeat_type` enum('none','daily','weekly','monthly') NOT NULL DEFAULT 'none',
  `repeat_interval` int(11) DEFAULT 1,
  `repeat_weekday` int(11) DEFAULT NULL,
  `repeat_monthday` int(11) DEFAULT NULL,
  `repeat_until` date DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`id`, `calendar_id`, `title`, `description`, `start`, `end`, `color`, `repeat_type`, `repeat_interval`, `repeat_weekday`, `repeat_monthday`, `repeat_until`, `created_by`) VALUES
(1, 1, 'Primjer događaja', 'Ovo je test event', '2025-01-01 10:00:00', '2025-01-01 12:00:00', '#ff0000', 'none', 1, NULL, NULL, NULL, ''),
(2, 1, 'kupujem ciklu', 'adada', '2026-02-12 19:50:00', '2026-02-12 21:50:00', NULL, 'none', 1, NULL, NULL, NULL, ''),
(3, 1, 'kupujem ciklu', 'adada', '2026-02-17 19:50:00', '2026-02-17 21:50:00', NULL, 'none', 1, NULL, NULL, NULL, ''),
(4, 1, 'jedem govna', 'jedem govna', '2026-02-18 16:28:00', '2026-02-18 18:31:00', NULL, 'none', 1, NULL, NULL, NULL, ''),
(5, 1, 'jedem govna', '', '2026-02-12 20:17:00', '2026-02-12 21:17:00', NULL, 'none', 1, NULL, NULL, NULL, ''),
(6, 1, 'branje grozda', 'ja sam mala pa sam pala', '2026-03-04 18:09:00', '2026-03-04 20:11:00', NULL, 'none', 1, NULL, NULL, NULL, ''),
(7, 1, 'bok', 'juhuuuuuuuuuuuuuuuuuuu', '2026-03-04 18:10:00', '2026-03-04 19:10:00', NULL, 'none', 1, NULL, NULL, NULL, ''),
(11, 1, 'skola', '', '2026-03-12 07:39:00', '2026-03-12 16:39:00', NULL, 'weekly', 1, 1, 0, '2026-04-22', ''),
(12, 1, 'dadad', '', '2026-03-17 12:58:00', '2026-03-17 13:00:00', NULL, 'none', 1, 1, 0, '0000-00-00', ''),
(13, 1, 'jako kul', '', '2026-03-17 02:33:00', '2026-03-17 15:33:00', NULL, 'none', 1, 1, 0, '0000-00-00', ''),
(14, 1, 'jessir', '', '2026-03-17 05:37:00', '2026-03-17 08:38:00', NULL, 'none', 1, 1, 0, '0000-00-00', ''),
(15, 1, 'oiufoija', '', '2026-03-17 13:44:00', '2026-03-17 17:44:00', NULL, 'none', 1, 1, 0, '0000-00-00', ''),
(16, 1, 'ja sam', '', '2026-03-17 13:50:00', '2026-03-17 16:50:00', NULL, 'none', 1, 1, 0, '0000-00-00', NULL),
(17, 1, 'adada', '', '2026-03-18 03:40:00', '2026-03-18 19:40:00', NULL, 'none', 1, 1, 0, '0000-00-00', 'Nikolina'),
(18, 1, 'dadadadad', 'jucer sam isel kupiti jabuke al ih je bilo prevec i nesam mogel nositi pa sam ih se hitil na pod', '2026-03-18 02:49:00', '2026-03-18 19:49:00', NULL, 'none', 1, 1, 0, '0000-00-00', 'Nikolina'),
(20, 1, 'jessir', '', '2026-03-04 17:56:00', '2026-03-04 21:56:00', NULL, 'none', 1, 1, 0, '0000-00-00', 'Rafael'),
(21, 1, 'wowwwww', '', '2026-03-18 03:36:00', '2026-03-18 21:36:00', NULL, 'none', 1, 1, 0, '0000-00-00', 'Rafael'),
(22, 1, 'bokiccc', '', '2026-03-18 18:38:00', '2026-03-18 21:38:00', NULL, 'none', 1, 1, 0, '0000-00-00', 'Rafael'),
(23, 1, 'bokic', 'audha9iudhaiuhdaiudhaadadadaiudhaiudhaiudhad adzuagdzuag  dzagdzuagdzuga z gadzugdazugdazug  zagdzuagdzua\r\naoidhaoidhaoiudhaodhaoihdaoihda', '2026-03-18 03:03:00', '2026-03-18 19:03:00', NULL, 'none', 1, 1, 0, '0000-00-00', 'Rafael'),
(26, 1, 'ovo dela ne?', '', '2026-04-07 15:02:00', '2026-04-07 19:02:00', NULL, 'none', 1, 1, 0, '0000-00-00', 'Rafael'),
(27, 1, 'kupujem traktor', '', '2026-04-23 18:08:00', '2026-04-23 20:08:00', NULL, 'none', 1, 1, 0, '0000-00-00', 'Rafael'),
(28, 1, 'aj aj aj my litl bartflafy', '', '2026-04-21 17:16:00', '2026-04-21 21:16:00', NULL, 'daily', 1, 1, 0, '2026-04-29', 'Rafael'),
(29, 1, 'ihihihihhi', '', '2026-04-08 18:17:00', '2026-04-08 22:18:00', NULL, 'weekly', 1, 4, 0, '2026-04-25', 'Rafael');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password_hash`, `created_at`) VALUES
(3, 'Rafael', '$2y$10$GH.222zGzNcIr7vMbIoIyubsSyGWDt0eASijCiwaWo8u9SKUmYqma', '2026-02-22 19:17:11'),
(4, 'Nikolina', '$2y$10$LKvAt8Jy.Tmfo2bUKr8.5.8unE/W8MrL4BMcXAla04jqY6BaVOLUS', '2026-02-22 19:17:11'),
(5, 'zmaj', '$2y$10$fNdw7/dismyCWLveRIPSQOMkXLgfMZdQwJkKGFsyFZhKbu2LY2kCi', '2026-04-04 11:01:35');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `calendars`
--
ALTER TABLE `calendars`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `calendar_users`
--
ALTER TABLE `calendar_users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `calendar_id` (`calendar_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`id`),
  ADD KEY `calendar_id` (`calendar_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `calendars`
--
ALTER TABLE `calendars`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `calendar_users`
--
ALTER TABLE `calendar_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `events`
--
ALTER TABLE `events`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `calendar_users`
--
ALTER TABLE `calendar_users`
  ADD CONSTRAINT `calendar_users_ibfk_1` FOREIGN KEY (`calendar_id`) REFERENCES `calendars` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `calendar_users_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `events_ibfk_1` FOREIGN KEY (`calendar_id`) REFERENCES `calendars` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
