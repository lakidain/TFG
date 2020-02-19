-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-02-2020 a las 12:55:37
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
-- Estructura de tabla para la tabla `asi_appointment`
--

CREATE TABLE `asi_appointment` (
  `id_appointment` int(11) NOT NULL,
  `id_audit` int(11) NOT NULL,
  `name_appointment` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `description_appointment` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `date_appointment` datetime NOT NULL,
  `state_appointment` int(11) NOT NULL COMMENT '0 Open and 1 Close'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_appointment`
--

INSERT INTO `asi_appointment` (`id_appointment`, `id_audit`, `name_appointment`, `description_appointment`, `date_appointment`, `state_appointment`) VALUES
(2, 1, 'Coches wapos', 'Phasellus felis nisi, vehicula quis imperdiet ut, efficitur vitae lorem. Praesent aliquam eget lorem eu convallis. Fusce viverra ullamcorper sagittis. Quisque tincidunt ipsum ante, vitae venenatis dui dapibus ultricies. Pellentesque gravida ut nisi malesu', '2020-01-28 23:50:00', 0),
(3, 1, 'Cableado Electrico', 'Nos encargaremos de hablar sobre el cableado electrico', '2020-01-31 14:00:00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_appointments_gallery`
--

CREATE TABLE `asi_appointments_gallery` (
  `id_gallery` int(11) NOT NULL,
  `id_appointment` int(11) NOT NULL,
  `photo_gallery` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `description_gallery` varchar(100) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_appointments_gallery`
--

INSERT INTO `asi_appointments_gallery` (`id_gallery`, `id_appointment`, `photo_gallery`, `description_gallery`) VALUES
(1, 2, '31247.jpg', 'Hola'),
(2, 2, '47741453-189c-4f4f-a6cb-57eb291d152f_31247.jpg', 'Saludos'),
(3, 2, '4f7ba53a-ad70-4b02-9f54-aeb4d68dc65f_31247.jpg', 'Adios'),
(4, 2, 'a08039d5-a6af-4273-9f66-b2fee0daa57b_31247.jpg', 'Buenos Dias'),
(5, 2, '63fd0881-5adb-432f-bb01-9f2a13e25773_31247.jpg', 'Que tal'),
(6, 2, '5b43ee4e-94ca-4ddc-af39-8015b6ac2e1d_amazon-reveals-new-server-chip-to-take-on-intel-1280x720.jpg', 'Buen invierno'),
(7, 3, 'f14d88b6-c399-444b-9381-445dd46daced_Añadirunpocodetexto(4).png', 'Hasta luego'),
(8, 2, 'b11039fc-e62a-4688-91ee-595593925b79_servicioscpd.jpg', 'undefined'),
(9, 2, '78475be1-6767-44ce-bb85-38b1efd8d668_servicioscpd.jpg', 'Cableado'),
(10, 2, 'ec0594c8-82ea-4b6b-8fae-af8cabc9cb7f_01-1170x832.jpg', 'Mas cables');

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
(1, 7, 1, 13, 1, '2019-12-30', '2020-01-01'),
(2, 21, 2, 1, 1, '2019-12-24', '2019-12-25'),
(3, 6, 2, 8, 1, '2019-12-30', '2020-01-01'),
(4, 6, 2, 8, 1, '2019-12-30', '2019-12-31'),
(5, 7, 2, 13, 6, '2020-01-26', '2020-01-30');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_answers`
--

CREATE TABLE `asi_audit_answers` (
  `id_audit_answer` int(11) NOT NULL,
  `answer_audit_answer` varchar(100) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit_answers`
--

INSERT INTO `asi_audit_answers` (`id_audit_answer`, `answer_audit_answer`) VALUES
(6, 'Todas las contraseñas siguen la política de seguridad'),
(7, 'Más del 50% de las contraseñas sigue la política de seguridad'),
(8, 'Menos del 50% de las contraseñas sigue la política de seguridad'),
(9, 'Menos del 25% de las contraseñas sigue la política de seguridad'),
(10, 'Ninguna de las contraseñas sigue la política de seguridad'),
(11, 'Sí y para cada una de las consultas'),
(12, 'Sí, aunque entre un 75% al 99% de las consultas'),
(13, 'Sí, aunque entre un 50% al 75% de las consultas'),
(14, 'Sí, aunque menos del 50% de las consultas'),
(15, 'No, en ningún caso'),
(16, 'El 100% de los empleados tienen asignado los permisos mínimos'),
(17, 'Más del 75% de los empleados tienen asignado los permisos mínimos'),
(18, 'Entre el 50% al 75% de los empleados tienen asignado los permisos mínimos'),
(19, 'Menos del 50% de los empleados tienen asignado los permisos mínimos'),
(20, 'Ninguno de los empleados tiene asignado los permisos mínimos');

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
(4, 'SGDB'),
(2, 'Usuario/Password');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_assets_threats`
--

CREATE TABLE `asi_audit_assets_threats` (
  `id_audit_asset_threat` int(11) NOT NULL,
  `id_audit_asset` int(11) NOT NULL,
  `id_audit_threat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit_assets_threats`
--

INSERT INTO `asi_audit_assets_threats` (`id_audit_asset_threat`, `id_audit_asset`, `id_audit_threat`) VALUES
(1, 2, 3),
(2, 2, 4),
(3, 4, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_employees`
--

CREATE TABLE `asi_audit_employees` (
  `id_audit_employees` int(11) NOT NULL,
  `id_audit` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `appointment_permit_audit_employees` int(11) NOT NULL,
  `questionnaire_permit_audit_employees` int(11) NOT NULL,
  `report_permit_audit_employees` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit_employees`
--

INSERT INTO `asi_audit_employees` (`id_audit_employees`, `id_audit`, `id_user`, `appointment_permit_audit_employees`, `questionnaire_permit_audit_employees`, `report_permit_audit_employees`) VALUES
(6, 1, 33, 0, 0, 0),
(7, 1, 34, 0, 0, 0),
(11, 1, 35, 0, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_questions`
--

CREATE TABLE `asi_audit_questions` (
  `id_audit_question` int(11) NOT NULL,
  `question_audit_question` varchar(300) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit_questions`
--

INSERT INTO `asi_audit_questions` (`id_audit_question`, `question_audit_question`) VALUES
(2, '¿Todas las contraseñas siguen la política de seguridad establecida?'),
(3, '¿Tiene delimitado el número de registros que puede devolver una consulta SQL?'),
(4, '¿Qué porcentaje de empleados tienen asignados los permisos mínimos necesarios para completar sus tareas autorizadas?');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_questions_answers`
--

CREATE TABLE `asi_audit_questions_answers` (
  `id_audit_question_answer` int(11) NOT NULL,
  `id_audit_question` int(11) NOT NULL,
  `id_audit_answer` int(11) NOT NULL,
  `score_audit_question_answer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit_questions_answers`
--

INSERT INTO `asi_audit_questions_answers` (`id_audit_question_answer`, `id_audit_question`, `id_audit_answer`, `score_audit_question_answer`) VALUES
(6, 2, 6, 5),
(7, 2, 7, 4),
(8, 2, 8, 3),
(9, 2, 9, 2),
(10, 2, 10, 1),
(11, 3, 11, 5),
(12, 3, 12, 4),
(13, 3, 13, 3),
(14, 3, 14, 2),
(15, 3, 15, 1),
(16, 4, 16, 5),
(17, 4, 17, 4),
(18, 4, 18, 3),
(19, 4, 19, 2),
(20, 4, 20, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_threats`
--

CREATE TABLE `asi_audit_threats` (
  `id_audit_threat` int(11) NOT NULL,
  `name_audit_threat` varchar(30) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit_threats`
--

INSERT INTO `asi_audit_threats` (`id_audit_threat`, `name_audit_threat`) VALUES
(3, 'Fuerza Bruta'),
(4, 'De Diccionario'),
(5, 'Inyección SQL');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_threats_vulnerabilities`
--

CREATE TABLE `asi_audit_threats_vulnerabilities` (
  `id_audit_threat_vulnerability` int(11) NOT NULL,
  `id_audit_threat` int(11) NOT NULL,
  `id_audit_vulnerability` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit_threats_vulnerabilities`
--

INSERT INTO `asi_audit_threats_vulnerabilities` (`id_audit_threat_vulnerability`, `id_audit_threat`, `id_audit_vulnerability`) VALUES
(1, 3, 2),
(2, 4, 2),
(3, 5, 3);

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
(3, 1, 3),
(4, 6, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_vulnerabilities`
--

CREATE TABLE `asi_audit_vulnerabilities` (
  `id_audit_vulnerability` int(11) NOT NULL,
  `name_audit_vulnerability` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit_vulnerabilities`
--

INSERT INTO `asi_audit_vulnerabilities` (`id_audit_vulnerability`, `name_audit_vulnerability`) VALUES
(2, 'Contraseñas sencillas e intuitivas'),
(3, 'Acceso sin restricciones a BD');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_vulnerabilities_questions`
--

CREATE TABLE `asi_audit_vulnerabilities_questions` (
  `id_audit_vulnerability_question` int(11) NOT NULL,
  `id_audit_vulnerability` int(11) NOT NULL,
  `id_audit_question` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit_vulnerabilities_questions`
--

INSERT INTO `asi_audit_vulnerabilities_questions` (`id_audit_vulnerability_question`, `id_audit_vulnerability`, `id_audit_question`) VALUES
(2, 2, 2),
(3, 3, 3),
(4, 3, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_companies`
--

CREATE TABLE `asi_companies` (
  `id_company` int(11) NOT NULL,
  `name_company` varchar(30) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Para que no se repita el nombre de la base de datos',
  `cif_company` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `business_name_company` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `adress_company` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `email_company` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `phone_company` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `id_user_boss` int(11) NOT NULL,
  `type_company` int(11) NOT NULL COMMENT '1 Audita , 2 Auditada'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_companies`
--

INSERT INTO `asi_companies` (`id_company`, `name_company`, `cif_company`, `business_name_company`, `adress_company`, `email_company`, `phone_company`, `id_user_boss`, `type_company`) VALUES
(1, 'Accenture', '', '', '', '', '0', 4, 0),
(2, 'Link', '', '', '', '', '0', 7, 0),
(8, 'Emergia', '', '', '', '', '0', 19, 0),
(11, 'Pepeyu', '-', '-', '-', '-', '-', 27, 1),
(13, 'Nintendo', 'E38420824', 'Nintendo S.A', 'C/Japon', 'nintendo@nintendo.com', '764563546', 34, 2),
(15, 'Repsol', '-', '-', '-', '-', '-', 37, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_messages`
--

CREATE TABLE `asi_messages` (
  `id_message` int(11) NOT NULL,
  `id_appointment` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `text_message` varchar(2000) COLLATE utf8_spanish_ci NOT NULL,
  `timestamp_message` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_messages`
--

INSERT INTO `asi_messages` (`id_message`, `id_appointment`, `id_user`, `text_message`, `timestamp_message`) VALUES
(1, 2, 7, 'Morbi dignissim nec odio nec dictum. Cras elementum, massa eget luctus viverra, erat ipsum rutrum nisi, non scelerisque lectus leo id neque. Quisque ultrices at risus sed porta. Suspendisse vel augue leo. Curabitur faucibus orci a libero semper rutrum vitae id ligula. Sed massa lorem, tempor id quam vel, pharetra tristique dolor. In pellentesque, libero quis tempor varius, libero mi mollis eros, scelerisque ullamcorper justo ipsum dignissim urna. Fusce dignissim urna et arcu aliquet, sit amet consectetur sem hendrerit. Aenean mattis ante nec odio varius, non convallis justo posuere. Vivamus ac sem non augue lacinia porttitor non ut nisi. Interdum et malesuada fames ac ante ipsum primis in faucibus. Morbi a leo et enim mattis ornare non non elit.', '2020-01-28 18:31:56'),
(2, 2, 7, 'Pellentesque malesuada laoreet enim sed bibendum. Quisque lobortis sed justo nec posuere. Vivamus mattis sem tellus, a elementum tortor dignissim in. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Praesent vel leo a mauris convallis semper. Nulla porttitor vel tortor non ultrices. Fusce eleifend nisi ac arcu finibus rhoncus. Ut nec enim ac quam scelerisque malesuada.', '2020-01-28 19:01:36'),
(3, 2, 7, 'Vestibulum consequat turpis sed massa porta, non imperdiet sapien scelerisque. Nam vestibulum sit amet tortor nec facilisis. Sed pretium mi vitae ex viverra, in dignissim urna congue. Proin sem felis, accumsan nec interdum et, condimentum sit amet eros. Suspendisse sollicitudin et nisl at pharetra. Integer dignissim erat sapien, non pulvinar magna rhoncus eu. Morbi ultricies risus id neque consequat, dictum convallis arcu ornare. Proin iaculis mauris sit amet est faucibus, sit amet maximus purus gravida.', '2020-01-28 22:25:14'),
(4, 3, 7, 'Ut consectetur feugiat hendrerit. Donec nisi ex, aliquet ut lectus a, bibendum vulputate libero. Cras eu purus ligula. Sed eleifend nisl vel tincidunt commodo. Nullam fermentum consectetur ante consectetur congue. Integer dolor tortor, dapibus eget maximus non, fringilla in sem. Phasellus volutpat elit et ante convallis cursus. Vestibulum feugiat ipsum a venenatis bibendum. Morbi interdum euismod fermentum. Mauris in elit eros. Vestibulum convallis libero vitae dui aliquet efficitur. Proin mauris ligula, commodo quis enim sed, convallis pellentesque arcu. Donec et nisi quis urna consectetur tincidunt eu non odio. Proin bibendum nisl sed fringilla iaculis. Quisque eu luctus dolor.', '2020-01-28 22:26:11'),
(5, 2, 7, 'Buenos dias', '2020-01-29 23:21:51');

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
(1, 'ROLE_AUDITOR'),
(2, 'ROLE_ADMIN'),
(3, 'ROLE_AUDITORBOSS'),
(4, 'ROLE_AUDITED'),
(5, 'ROLE_AUDITEDBOSS');

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
(7, '18273634A', 'Pepe', '$2a$10$K2JigM3yrrltTxVNOpqwXOFiQEZsbHFRVC6qGjyGQ0N2ZDNC1rBmy', 'pepe@hotmail.com', '657456523', 2, 1),
(8, '29832983E', 'lskdslkdslk', '$2a$10$1vbrcSrBYSW3SkiV6biv2.onf9OL0OfEBc7RHEyvMNRTywkwbnpwq', 'epoper@gmail.com', '9829323', 2, 1),
(19, 'qwerty', 'qwerty', '$2a$10$.J05.cp6UmATmY1mVlaD0uRIcmVYi4VWN/5AeL.3EQ8m4ddJAHqV6', 'locos@hotmail.com', '8928392', 8, 1),
(21, 'papo', 'qwerty', '$2a$10$FToyjeCZC4uMNYfPl3mM8O519gEpYuEi2kQFLb0WIdwqFdk9dhyJi', 'erer', '72', 2, 1),
(27, '76564678Y', 'qwerty', '$2a$10$Iz.Pxb55gJMTlyOeHgX9tuO2Aw8fsc7acGESZcxFXe6rdxoxROTh.', 'nintendo@nintendo.com', '876453645', 11, 1),
(32, '11111111Z', 'admin', '$2a$10$ZmWpnjs3ZUPWZ.Qd8dsKEud6CzNUGc92dNTajq9/80TusHZPWMTpC', 'admin', 'admin', 0, 1),
(34, '65746353Y', 'Lobo Estepario', '$2a$10$h80vHCi1NVpKtb1mkYkGW.81bVDElKu8owG/f8eBpibqC6qNaossm', 'loboestepario@gmail.com', '756453645', 13, 1),
(35, '65743253Y', 'Manel Force', '$2a$10$McS8xtOeTBSTwKuiNfjHSe1.nSEC7bOIIVKyMD/3t0FSEE8nW9r12', 'manelForce@gmail.com', '657465746', 13, 1),
(37, '65746352A', 'EDUARDO CRUCES CARAVACA', '$2a$10$f24trVLiDBnYJZiip4RKMuSv4bQur1rbMr/mpOOH9Jc/MZvpSEdLi', 'eduardocruces@gmail.com', '657564564', 15, 1),
(38, '64567364Z', 'ISMAEL GRAÑA HERVAS', '$2a$10$3Qy7ju9JBLbUlBbclGOFDu.1.Crs4I8h4Ig0gnZGY/G7fG0oMV.zG', 'ismaelgrana@gmail.com', '765746573', 15, 0);

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
(3, 7, 3),
(4, 32, 2),
(5, 34, 5),
(6, 34, 4),
(7, 35, 4),
(8, 37, 3),
(9, 37, 1),
(10, 38, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asi_appointment`
--
ALTER TABLE `asi_appointment`
  ADD PRIMARY KEY (`id_appointment`);

--
-- Indices de la tabla `asi_appointments_gallery`
--
ALTER TABLE `asi_appointments_gallery`
  ADD PRIMARY KEY (`id_gallery`);

--
-- Indices de la tabla `asi_audit`
--
ALTER TABLE `asi_audit`
  ADD PRIMARY KEY (`id_audit`);

--
-- Indices de la tabla `asi_audit_answers`
--
ALTER TABLE `asi_audit_answers`
  ADD PRIMARY KEY (`id_audit_answer`);

--
-- Indices de la tabla `asi_audit_assets`
--
ALTER TABLE `asi_audit_assets`
  ADD PRIMARY KEY (`id_audit_asset`),
  ADD UNIQUE KEY `name_audit_asset` (`name_audit_asset`);

--
-- Indices de la tabla `asi_audit_assets_threats`
--
ALTER TABLE `asi_audit_assets_threats`
  ADD PRIMARY KEY (`id_audit_asset_threat`);

--
-- Indices de la tabla `asi_audit_employees`
--
ALTER TABLE `asi_audit_employees`
  ADD PRIMARY KEY (`id_audit_employees`);

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
-- Indices de la tabla `asi_audit_threats`
--
ALTER TABLE `asi_audit_threats`
  ADD PRIMARY KEY (`id_audit_threat`);

--
-- Indices de la tabla `asi_audit_threats_vulnerabilities`
--
ALTER TABLE `asi_audit_threats_vulnerabilities`
  ADD PRIMARY KEY (`id_audit_threat_vulnerability`);

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
-- Indices de la tabla `asi_audit_vulnerabilities`
--
ALTER TABLE `asi_audit_vulnerabilities`
  ADD PRIMARY KEY (`id_audit_vulnerability`);

--
-- Indices de la tabla `asi_audit_vulnerabilities_questions`
--
ALTER TABLE `asi_audit_vulnerabilities_questions`
  ADD PRIMARY KEY (`id_audit_vulnerability_question`);

--
-- Indices de la tabla `asi_companies`
--
ALTER TABLE `asi_companies`
  ADD PRIMARY KEY (`id_company`),
  ADD UNIQUE KEY `company_name` (`name_company`);

--
-- Indices de la tabla `asi_messages`
--
ALTER TABLE `asi_messages`
  ADD PRIMARY KEY (`id_message`);

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
-- AUTO_INCREMENT de la tabla `asi_appointment`
--
ALTER TABLE `asi_appointment`
  MODIFY `id_appointment` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `asi_appointments_gallery`
--
ALTER TABLE `asi_appointments_gallery`
  MODIFY `id_gallery` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `asi_audit`
--
ALTER TABLE `asi_audit`
  MODIFY `id_audit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `asi_audit_answers`
--
ALTER TABLE `asi_audit_answers`
  MODIFY `id_audit_answer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `asi_audit_assets`
--
ALTER TABLE `asi_audit_assets`
  MODIFY `id_audit_asset` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `asi_audit_assets_threats`
--
ALTER TABLE `asi_audit_assets_threats`
  MODIFY `id_audit_asset_threat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `asi_audit_employees`
--
ALTER TABLE `asi_audit_employees`
  MODIFY `id_audit_employees` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `asi_audit_questions`
--
ALTER TABLE `asi_audit_questions`
  MODIFY `id_audit_question` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `asi_audit_questions_answers`
--
ALTER TABLE `asi_audit_questions_answers`
  MODIFY `id_audit_question_answer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `asi_audit_threats`
--
ALTER TABLE `asi_audit_threats`
  MODIFY `id_audit_threat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `asi_audit_threats_vulnerabilities`
--
ALTER TABLE `asi_audit_threats_vulnerabilities`
  MODIFY `id_audit_threat_vulnerability` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `asi_audit_types`
--
ALTER TABLE `asi_audit_types`
  MODIFY `id_audit_type` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `asi_audit_types_assets`
--
ALTER TABLE `asi_audit_types_assets`
  MODIFY `id_audit_type_asset` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `asi_audit_vulnerabilities`
--
ALTER TABLE `asi_audit_vulnerabilities`
  MODIFY `id_audit_vulnerability` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `asi_audit_vulnerabilities_questions`
--
ALTER TABLE `asi_audit_vulnerabilities_questions`
  MODIFY `id_audit_vulnerability_question` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `asi_companies`
--
ALTER TABLE `asi_companies`
  MODIFY `id_company` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `asi_messages`
--
ALTER TABLE `asi_messages`
  MODIFY `id_message` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `asi_rols`
--
ALTER TABLE `asi_rols`
  MODIFY `id_rol` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `asi_users`
--
ALTER TABLE `asi_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT de la tabla `asi_users_rols`
--
ALTER TABLE `asi_users_rols`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
