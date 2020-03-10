-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-03-2020 a las 17:08:08
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
  `name_appointment` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `description_appointment` varchar(2000) COLLATE utf8_spanish_ci NOT NULL,
  `date_appointment` datetime NOT NULL,
  `state_appointment` int(11) NOT NULL COMMENT '0 Open and 1 Close'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_appointment`
--

INSERT INTO `asi_appointment` (`id_appointment`, `id_audit`, `name_appointment`, `description_appointment`, `date_appointment`, `state_appointment`) VALUES
(2, 1, 'Coches de la flota', 'Phasellus felis nisi, vehicula quis imperdiet ut, efficitur vitae lorem. Praesent aliquam eget lorem eu convallis. Fusce viverra ullamcorper sagittis. Quisque tincidunt ipsum ante, vitae venenatis dui dapibus ultricies. Pellentesque gravida ut nisi malesu', '2020-02-13 01:50:00', 0),
(3, 1, 'Cableado Electrico', 'Nos encargaremos de hablar sobre el cableado electrico', '2020-01-31 14:00:00', 1),
(4, 5, 'Consolas de Nintendo', 'Vamos a comprobar que las consolas siguen la politica de seguridad', '2020-03-26 20:00:00', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_appointments_gallery`
--

CREATE TABLE `asi_appointments_gallery` (
  `id_gallery` int(11) NOT NULL,
  `id_appointment` int(11) NOT NULL,
  `photo_gallery` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `description_gallery` varchar(255) COLLATE utf8_spanish_ci NOT NULL
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
  `date_end_audit` date NOT NULL,
  `state_audit` int(11) NOT NULL COMMENT '0 Open and 1 Close'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit`
--

INSERT INTO `asi_audit` (`id_audit`, `id_user_manager`, `id_company_auditing`, `id_company_audited`, `id_audit_type`, `date_start_audit`, `date_end_audit`, `state_audit`) VALUES
(1, 7, 2, 13, 6, '2019-12-31', '2020-01-03', 0),
(2, 7, 2, 1, 1, '2019-12-24', '2020-01-23', 0),
(3, 21, 2, 8, 1, '2019-12-30', '2020-01-01', 0),
(5, 7, 2, 13, 6, '2020-01-26', '2020-01-30', 0),
(6, 7, 2, 17, 6, '2020-02-03', '2020-02-04', 0),
(7, 21, 2, 17, 1, '2020-03-16', '2020-03-18', 0);

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
(16, 'El 100% de los empleados tienen asignado los permisos mínimos'),
(37, 'El 25% de los empleados'),
(38, 'El 50% de los empleados'),
(39, 'El 75% de los empleados'),
(18, 'Entre el 50% al 75% de los empleados tienen asignado los permisos mínimos'),
(7, 'Más del 50% de las contraseñas sigue la política de seguridad'),
(17, 'Más del 75% de los empleados tienen asignado los permisos mínimos'),
(9, 'Menos del 25% de las contraseñas sigue la política de seguridad'),
(8, 'Menos del 50% de las contraseñas sigue la política de seguridad'),
(19, 'Menos del 50% de los empleados tienen asignado los permisos mínimos'),
(36, 'Ningún empleado'),
(10, 'Ninguna de las contraseñas sigue la política de seguridad'),
(20, 'Ninguno de los empleados tiene asignado los permisos mínimos'),
(35, 'No existe'),
(15, 'No, en ningún caso'),
(30, 'Nunca se cambian'),
(26, 'Se cambia cada 30 días'),
(27, 'Se cambia cada 60 días'),
(29, 'Se cambian una vez cada 2 años'),
(28, 'Se cambian una vez cada año'),
(11, 'Sí y para cada una de las consultas'),
(31, 'Sí y se utiliza siempre'),
(13, 'Sí, aunque entre un 50% al 75% de las consultas'),
(12, 'Sí, aunque entre un 75% al 99% de las consultas'),
(14, 'Sí, aunque menos del 50% de las consultas'),
(34, 'Sí, aunque se utiliza un 25% de las veces'),
(33, 'Sí, aunque se utiliza un 50% de las veces'),
(32, 'Sí, aunque se utiliza un 75% al 99% de las veces'),
(6, 'Todas las contraseñas siguen la política de seguridad'),
(40, 'Todos los empleados');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_assets`
--

CREATE TABLE `asi_audit_assets` (
  `id_audit_asset` int(11) NOT NULL,
  `name_audit_asset` varchar(255) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit_assets`
--

INSERT INTO `asi_audit_assets` (`id_audit_asset`, `name_audit_asset`) VALUES
(3, 'Cableado'),
(7, 'Redes'),
(4, 'SGDB'),
(2, 'Usuario/Password');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_assets_threats`
--

CREATE TABLE `asi_audit_assets_threats` (
  `id_audit_asset_threat` int(11) NOT NULL,
  `id_audit_asset` int(11) NOT NULL,
  `id_audit_threat` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit_assets_threats`
--

INSERT INTO `asi_audit_assets_threats` (`id_audit_asset_threat`, `id_audit_asset`, `id_audit_threat`) VALUES
(1, 2, 3),
(2, 2, 4),
(3, 4, 5),
(4, 2, 6),
(14, 3, 4),
(16, 7, NULL);

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
(7, 1, 34, 0, 0, 1),
(11, 1, 35, 0, 0, 1),
(12, 6, 39, 0, 0, 0),
(13, 5, 34, 1, 1, 0),
(14, 5, 35, 0, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_questions`
--

CREATE TABLE `asi_audit_questions` (
  `id_audit_question` int(11) NOT NULL,
  `question_audit_question` varchar(255) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit_questions`
--

INSERT INTO `asi_audit_questions` (`id_audit_question`, `question_audit_question`) VALUES
(6, '¿Cada cuánto tiempo cambian las contraseñas los empleados?'),
(7, '¿Existe un historial de contraseñas y se utiliza?'),
(8, '¿Qué porcentaje de empleados han sido suplantados en el último año?'),
(4, '¿Qué porcentaje de empleados tienen asignados los permisos mínimos necesarios para completar sus tareas autorizadas?'),
(3, '¿Tiene delimitado el número de registros que puede devolver una consulta SQL?'),
(2, '¿Todas las contraseñas siguen la política de seguridad establecidas?');

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
(6, 2, 6, 1),
(7, 2, 7, 2),
(8, 2, 8, 3),
(9, 2, 9, 4),
(10, 2, 10, 5),
(11, 3, 11, 1),
(12, 3, 12, 2),
(13, 3, 13, 3),
(14, 3, 14, 4),
(15, 3, 15, 5),
(16, 4, 16, 1),
(17, 4, 17, 2),
(18, 4, 18, 3),
(19, 4, 19, 4),
(20, 4, 20, 5),
(21, 5, 21, 1),
(22, 5, 22, 2),
(23, 5, 23, 3),
(24, 5, 24, 4),
(25, 5, 25, 5),
(26, 6, 26, 1),
(27, 6, 27, 2),
(28, 6, 28, 3),
(29, 6, 29, 4),
(30, 6, 30, 5),
(31, 7, 31, 1),
(32, 7, 32, 2),
(33, 7, 33, 3),
(34, 7, 34, 4),
(35, 7, 35, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_reports`
--

CREATE TABLE `asi_audit_reports` (
  `id_report` int(11) NOT NULL,
  `id_audit` int(11) NOT NULL,
  `route_report` varchar(200) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit_reports`
--

INSERT INTO `asi_audit_reports` (`id_report`, `id_audit`, `route_report`) VALUES
(43, 1, '266b570f-a806-4722-8abc-fc074e265d76'),
(44, 1, '5d2c76a5-a703-48d6-8654-84a1d1434814');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_results`
--

CREATE TABLE `asi_audit_results` (
  `id_audit_results` int(11) NOT NULL,
  `availability_weight_audit_results` int(11) NOT NULL,
  `confidentiality_weight_audit_results` int(11) NOT NULL,
  `integrity_weight_audit_results` int(11) NOT NULL,
  `legality_weight_audit_results` int(11) NOT NULL,
  `id_audit` int(11) NOT NULL,
  `id_audit_threat` int(11) NOT NULL,
  `audit_threat_probability_audit_results` int(11) NOT NULL,
  `id_audit_vulnerability` int(11) NOT NULL,
  `audit_vulnerability_probability_audit_results` float NOT NULL,
  `security_probability_failure_audit_results` float NOT NULL,
  `impact_level_audit_results` int(11) NOT NULL,
  `asset_risk_audit_results` float NOT NULL,
  `recomendation_audit_results` varchar(1000) COLLATE utf8_spanish_ci NOT NULL,
  `endDate_audit_results` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit_results`
--

INSERT INTO `asi_audit_results` (`id_audit_results`, `availability_weight_audit_results`, `confidentiality_weight_audit_results`, `integrity_weight_audit_results`, `legality_weight_audit_results`, `id_audit`, `id_audit_threat`, `audit_threat_probability_audit_results`, `id_audit_vulnerability`, `audit_vulnerability_probability_audit_results`, `security_probability_failure_audit_results`, `impact_level_audit_results`, `asset_risk_audit_results`, `recomendation_audit_results`, `endDate_audit_results`) VALUES
(247, 2, 3, 2, 4, 1, 3, 4, 2, 2, 3, 3, 2.66667, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sodales tincidunt sem, id pulvinar velit dignissim a. Praesent at urna aliquam, faucibus turpis nec, finibus sapien. Suspendisse euismod lacinia nisi, a ultrices massa hendrerit vel', '2020-03-09 21:53:50'),
(248, 2, 4, 3, 1, 1, 4, 5, 2, 2, 3.5, 2, 2.83333, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sodales tincidunt sem, id pulvinar velit dignissim a. Praesent at urna aliquam, faucibus turpis nec, finibus sapien. Suspendisse euismod lacinia nisi, a ultrices massa hendrerit vel', '2020-03-09 21:53:50'),
(249, 5, 5, 3, 2, 1, 5, 5, 3, 3, 4, 3, 3.33333, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sodales tincidunt sem, id pulvinar velit dignissim a. Praesent at urna aliquam, faucibus turpis nec, finibus sapien. Suspendisse euismod lacinia nisi, a ultrices massa hendrerit vel', '2020-03-09 21:53:50'),
(250, 3, 3, 5, 2, 1, 6, 4, 4, 4, 4, 4, 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sodales tincidunt sem, id pulvinar velit dignissim a. Praesent at urna aliquam, faucibus turpis nec, finibus sapien. Suspendisse euismod lacinia nisi, a ultrices massa hendrerit vel', '2020-03-09 21:53:50'),
(251, 5, 5, 5, 5, 1, 3, 5, 2, 2, 3.5, 5, 3.5, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sodales tincidunt sem, id pulvinar velit dignissim a. Praesent at urna aliquam, faucibus turpis nec, finibus sapien. Suspendisse euismod lacinia nisi, a ultrices massa hendrerit vel', '2020-03-09 22:12:01'),
(252, 5, 5, 5, 5, 1, 4, 5, 2, 2, 3.5, 5, 3.5, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sodales tincidunt sem, id pulvinar velit dignissim a. Praesent at urna aliquam, faucibus turpis nec, finibus sapien. Suspendisse euismod lacinia nisi, a ultrices massa hendrerit vel', '2020-03-09 22:12:01'),
(253, 5, 5, 5, 5, 1, 5, 5, 3, 3, 4, 5, 3.66667, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sodales tincidunt sem, id pulvinar velit dignissim a. Praesent at urna aliquam, faucibus turpis nec, finibus sapien. Suspendisse euismod lacinia nisi, a ultrices massa hendrerit vel', '2020-03-09 22:12:01'),
(254, 5, 5, 5, 5, 1, 6, 5, 4, 4, 4.5, 5, 3.83333, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sodales tincidunt sem, id pulvinar velit dignissim a. Praesent at urna aliquam, faucibus turpis nec, finibus sapien. Suspendisse euismod lacinia nisi, a ultrices massa hendrerit vel', '2020-03-09 22:12:01');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_threats`
--

CREATE TABLE `asi_audit_threats` (
  `id_audit_threat` int(11) NOT NULL,
  `name_audit_threat` varchar(255) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit_threats`
--

INSERT INTO `asi_audit_threats` (`id_audit_threat`, `name_audit_threat`) VALUES
(4, 'De Diccionario'),
(3, 'Fuerza Bruta'),
(5, 'Inyección SQL'),
(6, 'Suplantación de Identidad');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_threat_vulnerabilities_questions`
--

CREATE TABLE `asi_audit_threat_vulnerabilities_questions` (
  `id_audit_threat_vulnerability_question` int(11) NOT NULL,
  `id_audit_threat` int(11) NOT NULL,
  `id_audit_vulnerability` int(11) NOT NULL,
  `id_audit_question` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit_threat_vulnerabilities_questions`
--

INSERT INTO `asi_audit_threat_vulnerabilities_questions` (`id_audit_threat_vulnerability_question`, `id_audit_threat`, `id_audit_vulnerability`, `id_audit_question`) VALUES
(2, 3, 2, 2),
(3, 5, 3, 3),
(4, 5, 3, 4),
(6, 4, 2, 6),
(7, 3, 2, 7),
(8, 6, 4, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_types`
--

CREATE TABLE `asi_audit_types` (
  `id_audit_type` int(11) NOT NULL,
  `name_audit_type` varchar(255) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit_types`
--

INSERT INTO `asi_audit_types` (`id_audit_type`, `name_audit_type`) VALUES
(6, 'Base de Datos'),
(1, 'Informática'),
(2, 'Seguridades');

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
(3, 1, 3),
(4, 6, 4),
(7, 1, 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_audit_vulnerabilities`
--

CREATE TABLE `asi_audit_vulnerabilities` (
  `id_audit_vulnerability` int(11) NOT NULL,
  `name_audit_vulnerability` varchar(255) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_audit_vulnerabilities`
--

INSERT INTO `asi_audit_vulnerabilities` (`id_audit_vulnerability`, `name_audit_vulnerability`) VALUES
(3, 'Acceso sin restricciones a BD'),
(2, 'Contraseñas sencillas e intuitivas'),
(4, 'Correos sin proteger y no destrucción de los registros de cuentas de usuario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_companies`
--

CREATE TABLE `asi_companies` (
  `id_company` int(11) NOT NULL,
  `name_company` varchar(100) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Para que no se repita el nombre de la base de datos',
  `cif_company` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `business_name_company` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `adress_company` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `email_company` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `phone_company` varchar(12) COLLATE utf8_spanish_ci NOT NULL,
  `id_user_boss` int(11) NOT NULL,
  `type_company` int(11) NOT NULL COMMENT '1 Audita , 2 Auditada'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_companies`
--

INSERT INTO `asi_companies` (`id_company`, `name_company`, `cif_company`, `business_name_company`, `adress_company`, `email_company`, `phone_company`, `id_user_boss`, `type_company`) VALUES
(1, 'Accenture', '', '', '', '', '0', 4, 0),
(2, 'Link', 'E38420854', '-', '-', 'lol@lol.com', '675647565', 7, 0),
(8, 'Emergia', '', '', '', '', '0', 19, 0),
(11, 'Pepeyu', '-', '-', '-', '-', '-', 27, 1),
(13, 'Nintendo', 'E38420824', 'Nintendo S.A', 'C/Japon', 'nintendo@nintendo.com', '764563546', 34, 2),
(15, 'Repsol', '-', '-', '-', '-', '-', 37, 1),
(17, 'Github', 'Q4955482G', 'Github S.A', 'C/ Estados Unidos ', 'github@github.com', '657465564', 39, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_messages`
--

CREATE TABLE `asi_messages` (
  `id_message` int(11) NOT NULL,
  `id_appointment` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `text_message` varchar(10000) COLLATE utf8_spanish_ci NOT NULL,
  `timestamp_message` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_messages`
--

INSERT INTO `asi_messages` (`id_message`, `id_appointment`, `id_user`, `text_message`, `timestamp_message`) VALUES
(1, 2, 7, 'Morbi dignissim nec odio nec dictum. Cras elementum, massa eget luctus viverra, erat ipsum rutrum nisi, non scelerisque lectus leo id neque. Quisque ultrices at risus sed porta. Suspendisse vel augue leo. Curabitur faucibus orci a libero semper rutrum vitae id ligula. Sed massa lorem, tempor id quam vel, pharetra tristique dolor. In pellentesque, libero quis tempor varius, libero mi mollis eros, scelerisque ullamcorper justo ipsum dignissim urna. Fusce dignissim urna et arcu aliquet, sit amet consectetur sem hendrerit. Aenean mattis ante nec odio varius, non convallis justo posuere. Vivamus ac sem non augue lacinia porttitor non ut nisi. Interdum et malesuada fames ac ante ipsum primis in faucibus. Morbi a leo et enim mattis ornare non non elit.', '2020-01-28 18:31:56'),
(2, 2, 7, 'Pellentesque malesuada laoreet enim sed bibendum. Quisque lobortis sed justo nec posuere. Vivamus mattis sem tellus, a elementum tortor dignissim in. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Praesent vel leo a mauris convallis semper. Nulla porttitor vel tortor non ultrices. Fusce eleifend nisi ac arcu finibus rhoncus. Ut nec enim ac quam scelerisque malesuada.', '2020-01-28 19:01:36'),
(3, 2, 7, 'Vestibulum consequat turpis sed massa porta, non imperdiet sapien scelerisque. Nam vestibulum sit amet tortor nec facilisis. Sed pretium mi vitae ex viverra, in dignissim urna congue. Proin sem felis, accumsan nec interdum et, condimentum sit amet eros. Suspendisse sollicitudin et nisl at pharetra. Integer dignissim erat sapien, non pulvinar magna rhoncus eu. Morbi ultricies risus id neque consequat, dictum convallis arcu ornare. Proin iaculis mauris sit amet est faucibus, sit amet maximus purus gravida.', '2020-01-28 22:25:14'),
(4, 3, 7, 'Ut consectetur feugiat hendrerit. Donec nisi ex, aliquet ut lectus a, bibendum vulputate libero. Cras eu purus ligula. Sed eleifend nisl vel tincidunt commodo. Nullam fermentum consectetur ante consectetur congue. Integer dolor tortor, dapibus eget maximus non, fringilla in sem. Phasellus volutpat elit et ante convallis cursus. Vestibulum feugiat ipsum a venenatis bibendum. Morbi interdum euismod fermentum. Mauris in elit eros. Vestibulum convallis libero vitae dui aliquet efficitur. Proin mauris ligula, commodo quis enim sed, convallis pellentesque arcu. Donec et nisi quis urna consectetur tincidunt eu non odio. Proin bibendum nisl sed fringilla iaculis. Quisque eu luctus dolor.', '2020-01-28 22:26:11');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_questionnaire`
--

CREATE TABLE `asi_questionnaire` (
  `id_questionnaire` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_audit` int(11) NOT NULL,
  `id_asset` int(11) NOT NULL,
  `timestamp_questionnaire` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_questionnaire`
--

INSERT INTO `asi_questionnaire` (`id_questionnaire`, `id_user`, `id_audit`, `id_asset`, `timestamp_questionnaire`) VALUES
(3, 7, 1, 2, '2020-03-02 11:45:30'),
(4, 7, 1, 4, '2020-03-02 11:45:53'),
(5, 8, 1, 2, '2020-03-05 11:32:12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asi_questionnaire_answers`
--

CREATE TABLE `asi_questionnaire_answers` (
  `id_questionnaire_answers` int(11) NOT NULL,
  `id_questionnaire` int(11) NOT NULL,
  `id_pregunta` int(11) NOT NULL,
  `id_respuesta` int(11) NOT NULL,
  `id_vulnerability` int(11) NOT NULL,
  `id_threat` int(11) NOT NULL,
  `score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `asi_questionnaire_answers`
--

INSERT INTO `asi_questionnaire_answers` (`id_questionnaire_answers`, `id_questionnaire`, `id_pregunta`, `id_respuesta`, `id_vulnerability`, `id_threat`, `score`) VALUES
(5, 3, 2, 6, 2, 3, 1),
(6, 3, 6, 27, 2, 4, 2),
(7, 3, 7, 33, 2, 3, 3),
(8, 3, 8, 39, 4, 6, 4),
(9, 4, 3, 11, 3, 5, 1),
(10, 4, 4, 19, 3, 5, 4),
(11, 5, 4, 19, 3, 5, 4);

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
  `username` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `name_user` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `email_user` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `phone_user` varchar(12) COLLATE utf8_spanish_ci NOT NULL,
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
(7, '18273634A', 'Pepe', '$2a$10$eZZ1TjQx/Bqy0xYOZDsxqepSW7lOPYGR.ljT6GNTQ1nd56pYPKdXu', 'pepe@hotmail.com', '657456523', 2, 1),
(8, '29832983E', 'lskdslkdslk', '$2a$10$1vbrcSrBYSW3SkiV6biv2.onf9OL0OfEBc7RHEyvMNRTywkwbnpwq', 'epoper@gmail.com', '9829323', 2, 1),
(19, 'qwerty', 'qwerty', '$2a$10$.J05.cp6UmATmY1mVlaD0uRIcmVYi4VWN/5AeL.3EQ8m4ddJAHqV6', 'locos@hotmail.com', '8928392', 8, 1),
(21, 'papo', 'qwerty', '$2a$10$FToyjeCZC4uMNYfPl3mM8O519gEpYuEi2kQFLb0WIdwqFdk9dhyJi', 'erer', '72', 2, 1),
(27, '76564678Y', 'qwerty', '$2a$10$Iz.Pxb55gJMTlyOeHgX9tuO2Aw8fsc7acGESZcxFXe6rdxoxROTh.', 'nintendo@nintendo.com', '876453645', 11, 1),
(32, '11111111Z', 'admin', '$2a$10$ZmWpnjs3ZUPWZ.Qd8dsKEud6CzNUGc92dNTajq9/80TusHZPWMTpC', 'admin', 'admin', 0, 1),
(34, '65746353Y', 'Lobo Estepario', '$2a$10$h80vHCi1NVpKtb1mkYkGW.81bVDElKu8owG/f8eBpibqC6qNaossm', 'loboestepario@gmail.com', '756453645', 13, 1),
(35, '65743253Y', 'Manel Force', '$2a$10$McS8xtOeTBSTwKuiNfjHSe1.nSEC7bOIIVKyMD/3t0FSEE8nW9r12', 'manelForce@gmail.com', '657465746', 13, 1),
(37, '65746352A', 'EDUARDO CRUCES CARAVACA', '$2a$10$f24trVLiDBnYJZiip4RKMuSv4bQur1rbMr/mpOOH9Jc/MZvpSEdLi', 'eduardocruces@gmail.com', '657564564', 15, 1),
(38, '64567364Z', 'ISMAEL GRAÑA HERVAS', '$2a$10$3Qy7ju9JBLbUlBbclGOFDu.1.Crs4I8h4Ig0gnZGY/G7fG0oMV.zG', 'ismaelgrana@gmail.com', '765746573', 15, 0),
(39, '44955482A', 'PABLO MOTOS', '$2a$10$0U.aAwgNmMTkuwDUpgSN/.qVLE7ez3ZlDm4DUkis5nyz8uVNnUDlO', 'locdwo@hotmail.com', '758675647', 17, 1),
(44, '47564756A', 'EDUARDO CRUCES CARAVALA', '$2a$10$OMQbhFfoe03z.Z6VZCZgtu95T8Os43xo881EPKGYZonzzXtbVZQpi', 'lsaols@oslkos.com', '857684768', 13, 1);

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
(10, 38, 1),
(11, 8, 1),
(12, 39, 5),
(13, 39, 4),
(14, 7, 1),
(15, 40, 3),
(16, 40, 1),
(17, 41, 3),
(18, 41, 1),
(19, 42, 1),
(20, 43, 4),
(21, 44, 4);

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
  ADD PRIMARY KEY (`id_audit_answer`),
  ADD UNIQUE KEY `answer_audit_answer` (`answer_audit_answer`);

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
  ADD PRIMARY KEY (`id_audit_question`),
  ADD UNIQUE KEY `question_audit_question` (`question_audit_question`);

--
-- Indices de la tabla `asi_audit_questions_answers`
--
ALTER TABLE `asi_audit_questions_answers`
  ADD PRIMARY KEY (`id_audit_question_answer`);

--
-- Indices de la tabla `asi_audit_reports`
--
ALTER TABLE `asi_audit_reports`
  ADD PRIMARY KEY (`id_report`);

--
-- Indices de la tabla `asi_audit_results`
--
ALTER TABLE `asi_audit_results`
  ADD PRIMARY KEY (`id_audit_results`);

--
-- Indices de la tabla `asi_audit_threats`
--
ALTER TABLE `asi_audit_threats`
  ADD PRIMARY KEY (`id_audit_threat`),
  ADD UNIQUE KEY `name_audit_threat` (`name_audit_threat`);

--
-- Indices de la tabla `asi_audit_threat_vulnerabilities_questions`
--
ALTER TABLE `asi_audit_threat_vulnerabilities_questions`
  ADD PRIMARY KEY (`id_audit_threat_vulnerability_question`);

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
  ADD PRIMARY KEY (`id_audit_vulnerability`),
  ADD UNIQUE KEY `name_audit_vulnerability` (`name_audit_vulnerability`);

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
-- Indices de la tabla `asi_questionnaire`
--
ALTER TABLE `asi_questionnaire`
  ADD PRIMARY KEY (`id_questionnaire`);

--
-- Indices de la tabla `asi_questionnaire_answers`
--
ALTER TABLE `asi_questionnaire_answers`
  ADD PRIMARY KEY (`id_questionnaire_answers`);

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
  MODIFY `id_appointment` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `asi_appointments_gallery`
--
ALTER TABLE `asi_appointments_gallery`
  MODIFY `id_gallery` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `asi_audit`
--
ALTER TABLE `asi_audit`
  MODIFY `id_audit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `asi_audit_answers`
--
ALTER TABLE `asi_audit_answers`
  MODIFY `id_audit_answer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT de la tabla `asi_audit_assets`
--
ALTER TABLE `asi_audit_assets`
  MODIFY `id_audit_asset` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `asi_audit_assets_threats`
--
ALTER TABLE `asi_audit_assets_threats`
  MODIFY `id_audit_asset_threat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `asi_audit_employees`
--
ALTER TABLE `asi_audit_employees`
  MODIFY `id_audit_employees` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `asi_audit_questions`
--
ALTER TABLE `asi_audit_questions`
  MODIFY `id_audit_question` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `asi_audit_questions_answers`
--
ALTER TABLE `asi_audit_questions_answers`
  MODIFY `id_audit_question_answer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT de la tabla `asi_audit_reports`
--
ALTER TABLE `asi_audit_reports`
  MODIFY `id_report` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT de la tabla `asi_audit_results`
--
ALTER TABLE `asi_audit_results`
  MODIFY `id_audit_results` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=255;

--
-- AUTO_INCREMENT de la tabla `asi_audit_threats`
--
ALTER TABLE `asi_audit_threats`
  MODIFY `id_audit_threat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `asi_audit_threat_vulnerabilities_questions`
--
ALTER TABLE `asi_audit_threat_vulnerabilities_questions`
  MODIFY `id_audit_threat_vulnerability_question` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `asi_audit_types`
--
ALTER TABLE `asi_audit_types`
  MODIFY `id_audit_type` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `asi_audit_types_assets`
--
ALTER TABLE `asi_audit_types_assets`
  MODIFY `id_audit_type_asset` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `asi_audit_vulnerabilities`
--
ALTER TABLE `asi_audit_vulnerabilities`
  MODIFY `id_audit_vulnerability` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `asi_companies`
--
ALTER TABLE `asi_companies`
  MODIFY `id_company` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `asi_messages`
--
ALTER TABLE `asi_messages`
  MODIFY `id_message` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `asi_questionnaire`
--
ALTER TABLE `asi_questionnaire`
  MODIFY `id_questionnaire` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `asi_questionnaire_answers`
--
ALTER TABLE `asi_questionnaire_answers`
  MODIFY `id_questionnaire_answers` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `asi_rols`
--
ALTER TABLE `asi_rols`
  MODIFY `id_rol` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `asi_users`
--
ALTER TABLE `asi_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT de la tabla `asi_users_rols`
--
ALTER TABLE `asi_users_rols`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
