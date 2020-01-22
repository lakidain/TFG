-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-01-2020 a las 17:42:26
-- Versión del servidor: 10.1.36-MariaDB
-- Versión de PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_springboot_backend`
--
CREATE DATABASE IF NOT EXISTS `db_springboot_backend` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `db_springboot_backend`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit`
--

CREATE TABLE `asi_audit` (
  `id_audit` int(11) NOT NULL,
  `id_user_manager` int(11) NOT NULL,
  `id_company_auditing` int(11) NOT NULL,
  `id_company_audited` int(11) NOT NULL,
  `id_audit_type` int(11) NOT NULL,
  `date_start_audit` date NOT NULL,
  `date_end_audit` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit`
--

INSERT INTO `asi_audit` (`id_audit`, `id_user_manager`, `id_company_auditing`, `id_company_audited`, `id_audit_type`, `date_start_audit`, `date_end_audit`) VALUES
(1, 7, 1, 0, 1, '2019-12-30', '2020-01-01'),
(2, 21, 2, 1, 1, '2019-12-24', '2019-12-25'),
(3, 6, 2, 8, 1, '2019-12-30', '2020-01-01'),
(4, 6, 2, 8, 1, '2019-12-30', '2019-12-31');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_auditors`
--

CREATE TABLE `asi_auditors` (
  `id_auditor` int(11) NOT NULL,
  `name_auditor` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `dni_auditor` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `email_auditor` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `phone_auditor` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `password_auditor` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `name_company` varchar(30) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_auditors`
--

INSERT INTO `asi_auditors` (`id_auditor`, `name_auditor`, `dni_auditor`, `email_auditor`, `phone_auditor`, `password_auditor`, `name_company`) VALUES
(1, 'Ander', '12648712Y', 'pepeyu@hotmail.com', '765847365', 'qwerty', 'Pepeyu S.A'),
(2, 'Andeer Lakidain', 'qwertyuiZ', 'ajsalkslak', '392039209', 'skdslkls', 'Amancio');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_answers`
--

CREATE TABLE `asi_audit_answers` (
  `id_audit_answer` int(11) NOT NULL,
  `answer_audit_answer` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_assets`
--

CREATE TABLE `asi_audit_assets` (
  `id_audit_asset` int(11) NOT NULL,
  `name_audit_asset` varchar(200) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit_assets`
--

INSERT INTO `asi_audit_assets` (`id_audit_asset`, `name_audit_asset`) VALUES
(3, 'Cableado'),
(2, 'Usuario/Password');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_assets_questions`
--

CREATE TABLE `asi_audit_assets_questions` (
  `id_audit_asset_question` int(11) NOT NULL,
  `id_asset` int(11) NOT NULL,
  `id_question` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_questions`
--

CREATE TABLE `asi_audit_questions` (
  `id_audit_question` int(11) NOT NULL,
  `question` varchar(200) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_questions_answers`
--

CREATE TABLE `asi_audit_questions_answers` (
  `id_audit_question_answer` int(11) NOT NULL,
  `id_audit_question` int(11) NOT NULL,
  `id_audit_answer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_types`
--

CREATE TABLE `asi_audit_types` (
  `id_audit_type` int(11) NOT NULL,
  `name_audit_type` varchar(20) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit_types`
--

INSERT INTO `asi_audit_types` (`id_audit_type`, `name_audit_type`) VALUES
(6, 'Base de Datos'),
(1, 'Informática'),
(2, 'Seguridad');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_types_assets`
--

CREATE TABLE `asi_audit_types_assets` (
  `id_audit_type_asset` int(11) NOT NULL,
  `id_audit_type` int(11) NOT NULL,
  `id_audit_asset` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit_types_assets`
--

INSERT INTO `asi_audit_types_assets` (`id_audit_type_asset`, `id_audit_type`, `id_audit_asset`) VALUES
(1, 6, 2),
(2, 6, 3),
(3, 1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_companies`
--

CREATE TABLE `asi_companies` (
  `id_company` int(11) NOT NULL,
  `name_company` varchar(30) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Para que no se repita el nombre de la base de datos',
  `id_user_boss` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_companies`
--

INSERT INTO `asi_companies` (`id_company`, `name_company`, `id_user_boss`) VALUES
(1, 'Accenture', 4),
(2, 'Link', 7),
(8, 'Emergia', 19);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_rols`
--

CREATE TABLE `asi_rols` (
  `id_rol` int(20) NOT NULL,
  `name` varchar(255) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_rols`
--

INSERT INTO `asi_rols` (`id_rol`, `name`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN'),
(3, 'ROLE_BOSS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_users`
--

CREATE TABLE `asi_users` (
  `id` int(11) NOT NULL,
  `username` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `name_user` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `email_user` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `phone_user` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `id_company` int(30) NOT NULL,
  `enabled` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_users`
--

INSERT INTO `asi_users` (`id`, `username`, `name_user`, `password`, `email_user`, `phone_user`, `id_company`, `enabled`) VALUES
(1, '12648712Y', 'Ander Liked di Arraba', '$2a$10$.UhtWEAgWKdk1bUvax91nOWOwPO/w0IzlGTuhIhGtazKrjohzSkQu', 'anderlakidain@gmail.com', '682736485', 1, 1),
(2, '746563A', 'Pepe Yufera', '$2a$10$GQQC/mtwC7x8D/ifCxvCsO/fqS.g1o5OxhyAPHgviqbSOZijOoJGu', 'andlwkee@gmail.com', '746564732', 1, 1),
(4, '75648364R', 'Manolo', '$2a$10$HII7nIhdEZwIDK.FtPlizOLPB8tmGWV8ROFL3mKKL55Fw1e9APV2K', 'loc@gmail.com', '786547385', 1, 1),
(6, '6475738E', 'Pablo', '$2a$10$HJSv9bpuKug/rBAFDfyFSezbVtRfLgdKpr2YtMBI8zE3SyQQaK/qq', 'pl@gmail.com', '784374', 2, 1),
(7, '18273634A', 'Pepe', '$2a$10$K2JigM3yrrltTxVNOpqwXOFiQEZsbHFRVC6qGjyGQ0N2ZDNC1rBmy', 'pepe@hotmail.com', '657456523', 2, 1),
(8, '29832983E', 'lskdslkdslk', '$2a$10$1vbrcSrBYSW3SkiV6biv2.onf9OL0OfEBc7RHEyvMNRTywkwbnpwq', 'epoper@gmail.com', '9829323', 2, 1),
(19, 'qwerty', 'qwerty', '$2a$10$.J05.cp6UmATmY1mVlaD0uRIcmVYi4VWN/5AeL.3EQ8m4ddJAHqV6', 'locos@hotmail.com', '8928392', 8, 1),
(21, 'papo', 'qwerty', '$2a$10$FToyjeCZC4uMNYfPl3mM8O519gEpYuEi2kQFLb0WIdwqFdk9dhyJi', 'erer', '72', 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_users_rols`
--

CREATE TABLE `asi_users_rols` (
  `id` int(11) NOT NULL,
  `id_user` int(20) NOT NULL,
  `id_rol` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_users_rols`
--

INSERT INTO `asi_users_rols` (`id`, `id_user`, `id_rol`) VALUES
(1, 1, 1),
(2, 4, 3);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asi_audit`
--
ALTER TABLE `asi_audit`
  ADD PRIMARY KEY (`id_audit`);

--
-- Indices de la tabla `asi_auditors`
--
ALTER TABLE `asi_auditors`
  ADD PRIMARY KEY (`id_auditor`),
  ADD UNIQUE KEY `dni_auditor` (`dni_auditor`);

--
-- Indices de la tabla `asi_audit_assets`
--
ALTER TABLE `asi_audit_assets`
  ADD PRIMARY KEY (`id_audit_asset`),
  ADD UNIQUE KEY `name_audit_asset` (`name_audit_asset`);

--
-- Indices de la tabla `asi_audit_assets_questions`
--
ALTER TABLE `asi_audit_assets_questions`
  ADD PRIMARY KEY (`id_audit_asset_question`);

--
-- Indices de la tabla `asi_audit_questions`
--
ALTER TABLE `asi_audit_questions`
  ADD PRIMARY KEY (`id_audit_question`);

--
-- Indices de la tabla `asi_audit_questions_answers`
--
ALTER TABLE `asi_audit_questions_answers`
  ADD PRIMARY KEY (`id_audit_question_answer`);

--
-- Indices de la tabla `asi_audit_types`
--
ALTER TABLE `asi_audit_types`
  ADD PRIMARY KEY (`id_audit_type`),
  ADD UNIQUE KEY `name_audit_type` (`name_audit_type`);

--
-- Indices de la tabla `asi_audit_types_assets`
--
ALTER TABLE `asi_audit_types_assets`
  ADD PRIMARY KEY (`id_audit_type_asset`);

--
-- Indices de la tabla `asi_companies`
--
ALTER TABLE `asi_companies`
  ADD PRIMARY KEY (`id_company`),
  ADD UNIQUE KEY `company_name` (`name_company`);

--
-- Indices de la tabla `asi_rols`
--
ALTER TABLE `asi_rols`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indices de la tabla `asi_users`
--
ALTER TABLE `asi_users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indices de la tabla `asi_users_rols`
--
ALTER TABLE `asi_users_rols`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `asi_audit`
--
ALTER TABLE `asi_audit`
  MODIFY `id_audit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `asi_auditors`
--
ALTER TABLE `asi_auditors`
  MODIFY `id_auditor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `asi_audit_assets`
--
ALTER TABLE `asi_audit_assets`
  MODIFY `id_audit_asset` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `asi_audit_assets_questions`
--
ALTER TABLE `asi_audit_assets_questions`
  MODIFY `id_audit_asset_question` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `asi_audit_questions`
--
ALTER TABLE `asi_audit_questions`
  MODIFY `id_audit_question` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `asi_audit_questions_answers`
--
ALTER TABLE `asi_audit_questions_answers`
  MODIFY `id_audit_question_answer` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `asi_audit_types`
--
ALTER TABLE `asi_audit_types`
  MODIFY `id_audit_type` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `asi_audit_types_assets`
--
ALTER TABLE `asi_audit_types_assets`
  MODIFY `id_audit_type_asset` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `asi_companies`
--
ALTER TABLE `asi_companies`
  MODIFY `id_company` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `asi_rols`
--
ALTER TABLE `asi_rols`
  MODIFY `id_rol` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `asi_users`
--
ALTER TABLE `asi_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `asi_users_rols`
--
ALTER TABLE `asi_users_rols`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
