-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 12-02-2014 a las 05:31:43
-- Versión del servidor: 5.5.27
-- Versión de PHP: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `bdinventario`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE IF NOT EXISTS `categoria` (
  `nCatCodigo` int(11) NOT NULL AUTO_INCREMENT,
  `cCatDescripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`nCatCodigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`nCatCodigo`, `cCatDescripcion`) VALUES
(1, 'Pelicula'),
(2, 'Musica'),
(3, 'musica en mp3'),
(4, 'pelicula BluRay'),
(5, 'VCD');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `nCliCodigo` int(11) NOT NULL AUTO_INCREMENT,
  `cCliNit` varchar(20) DEFAULT NULL,
  `cCliCi` varchar(20) DEFAULT NULL,
  `cCliNombre` varchar(30) NOT NULL,
  `cCliDireccion` varchar(50) DEFAULT NULL,
  `cCliNumTelefono` varchar(20) DEFAULT NULL,
  `cCliTipoTelefono` varchar(20) DEFAULT NULL,
  `cCliNroFax` varchar(20) DEFAULT NULL,
  `cCliEmail` varchar(40) DEFAULT NULL,
  `cCliOtros` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`nCliCodigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`nCliCodigo`, `cCliNit`, `cCliCi`, `cCliNombre`, `cCliDireccion`, `cCliNumTelefono`, `cCliTipoTelefono`, `cCliNroFax`, `cCliEmail`, `cCliOtros`) VALUES
(1, 'sfdsf', 'sdfsdfdsf', 'Jorge', 'fdsfdsfds', 'sdfdsfdswerew', 'Celular', 'sdfdsf', 'sdfdsfdsfwerewrwer', 'wergth jhgbnbgnsdgbsgf  dg dsf gdfgds\ngdsfgdsfgdsgsdf'),
(2, '13123', 'ert56756gf', 'tyjuytrut', 'fdgdfg', 'dfgdfgdf', 'Casa', 'dfgdfg', 'dgdfgyuthgfhgf', 'retertertgdf dgfdfg gdfgdfg ertert   egfdfg\ndfgdf gdfgf dg dfgrre'),
(3, '--', '--', 'MIGUEL', NULL, NULL, NULL, NULL, NULL, NULL),
(4, '--', '--', 'FRANCISCO', NULL, NULL, NULL, NULL, NULL, NULL),
(5, '', '', 'Juan', '', '', 'Seleccionar...', '', '', ''),
(6, 'piedra', '1105539397', 'nelson ', 'epoca', '2589311', 'Casa', '2589311458', 'nopiedra@utpl.edu.ec', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE IF NOT EXISTS `compra` (
  `nComCodigo` int(11) NOT NULL AUTO_INCREMENT,
  `nProvCodigo` int(11) NOT NULL,
  `cComFecha` date NOT NULL,
  `nProCodigo` int(11) NOT NULL,
  `nComCantidad` int(11) NOT NULL,
  `nComPrecio` double NOT NULL,
  `nComPrecioTotal` double NOT NULL,
  `cComSerie` varchar(30) DEFAULT NULL,
  `cComCodigo` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`nComCodigo`),
  KEY `R_4` (`nProvCodigo`),
  KEY `R_5` (`nProCodigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Volcado de datos para la tabla `compra`
--

INSERT INTO `compra` (`nComCodigo`, `nProvCodigo`, `cComFecha`, `nProCodigo`, `nComCantidad`, `nComPrecio`, `nComPrecioTotal`, `cComSerie`, `cComCodigo`) VALUES
(1, 1, '2011-11-09', 1, 60, 20, 0, '005', NULL),
(2, 2, '2011-11-14', 1, 30, 18, 0, '002', '201'),
(3, 1, '2011-12-08', 1, 60, 17.5, 1050, '003', '004'),
(4, 1, '2011-12-08', 1, 100, 19.5, 1950, '004', '004'),
(5, 2, '2011-12-08', 2, 50, 25.5, 1275, 'A200', 'B200'),
(6, 2, '2011-12-08', 2, 10, 30, 300, '400', '500'),
(7, 2, '2011-12-08', 1, 10, 20, 200, '400', '500'),
(8, 1, '2011-12-06', 1, 10, 10, 100, 'sadsad', 'sadas'),
(9, 1, '2011-12-07', 1, 10, 15, 150, '324', '234'),
(10, 1, '2011-12-09', 3, 10, 15, 150, '345', '356');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contado`
--

CREATE TABLE IF NOT EXISTS `contado` (
  `nVenCodigo` int(11) NOT NULL,
  `cContEstado` varchar(30) NOT NULL,
  `nContMontoPagado` double DEFAULT NULL,
  PRIMARY KEY (`nVenCodigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `contado`
--

INSERT INTO `contado` (`nVenCodigo`, `cContEstado`, `nContMontoPagado`) VALUES
(1, 'CONTADO', 4200),
(4, 'PagoCancelado', 3000),
(5, 'PagoCancelado', 1500),
(7, 'PagoCancelado', 1200),
(9, 'PagoCancelado', 1200),
(13, 'PagoCancelado', 1800);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `credito`
--

CREATE TABLE IF NOT EXISTS `credito` (
  `nVenCodigo` int(11) NOT NULL,
  `nCreNroCuotas` int(11) NOT NULL,
  `nCreMontoPorCuota` double DEFAULT NULL,
  `cCreEstado` varchar(20) NOT NULL,
  `nCreMontoDebito` double DEFAULT NULL,
  PRIMARY KEY (`nVenCodigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `credito`
--

INSERT INTO `credito` (`nVenCodigo`, `nCreNroCuotas`, `nCreMontoPorCuota`, `cCreEstado`, `nCreMontoDebito`) VALUES
(3, 5, 840, 'PagoCancelado', 4200),
(6, 5, 240, 'PagoCancelado', 1200),
(8, 2, 600, 'Debito', 1200),
(10, 3, 400, 'Debito', 1200),
(11, 5, 240, 'Debito', 1542),
(12, 3, 400, 'Debito', 1542);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cronogramapago`
--

CREATE TABLE IF NOT EXISTS `cronogramapago` (
  `nCPagoCodigo` int(11) NOT NULL AUTO_INCREMENT,
  `nNroCuota` int(11) NOT NULL,
  `cCPagoFechaInicio` date NOT NULL,
  `cCPagoFecha` date NOT NULL,
  `nCPagoCuota` double NOT NULL,
  `nCPagoRecibido` double DEFAULT NULL,
  `nVenCodigo` int(11) DEFAULT NULL,
  PRIMARY KEY (`nCPagoCodigo`),
  KEY `R_16` (`nVenCodigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Volcado de datos para la tabla `cronogramapago`
--

INSERT INTO `cronogramapago` (`nCPagoCodigo`, `nNroCuota`, `cCPagoFechaInicio`, `cCPagoFecha`, `nCPagoCuota`, `nCPagoRecibido`, `nVenCodigo`) VALUES
(1, 1, '2011-11-20', '2011-12-20', 840, 840, 3),
(2, 2, '2011-12-20', '2012-01-19', 840, 840, 3),
(3, 3, '2012-01-19', '2012-02-18', 840, 840, 3),
(4, 4, '2012-02-18', '2012-03-19', 840, 840, 3),
(5, 5, '2012-03-19', '2012-04-18', 840, 840, 3),
(6, 1, '2011-11-28', '2011-12-28', 240, 240, 6),
(7, 2, '2011-12-28', '2012-01-27', 240, 240, 6),
(8, 3, '2012-01-27', '2012-02-26', 240, 240, 6),
(9, 4, '2012-02-26', '2012-03-27', 240, 240, 6),
(10, 5, '2012-03-27', '2012-04-26', 240, 240, 6),
(11, 1, '2011-11-28', '2011-12-28', 600, 0, 8),
(12, 2, '2011-12-28', '2012-01-27', 600, 0, 8),
(13, 1, '2011-11-28', '2011-12-28', 400, 0, 10),
(14, 2, '2011-12-28', '2012-01-27', 400, 0, 10),
(15, 3, '2012-01-27', '2012-02-26', 400, 0, 10),
(16, 1, '2011-11-28', '2011-12-13', 240, 0, 11),
(17, 2, '2011-12-13', '2011-12-28', 240, 0, 11),
(18, 3, '2011-12-28', '2012-01-12', 240, 0, 11),
(19, 4, '2012-01-12', '2012-01-27', 240, 0, 11),
(20, 5, '2012-01-27', '2012-02-11', 240, 0, 11),
(21, 1, '2011-11-28', '2011-12-28', 400, 0, 12),
(22, 2, '2011-12-28', '2012-01-27', 400, 0, 12),
(23, 3, '2012-01-27', '2012-02-26', 400, 0, 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Detalle_venta`
--

CREATE TABLE IF NOT EXISTS `Detalle_venta` (
  `nVenCodigo` int(11) DEFAULT NULL,
  `nProCodigo` int(11) DEFAULT NULL,
  `nTraCodigo` int(11) NOT NULL AUTO_INCREMENT,
  `nTraPrecio` double DEFAULT NULL,
  `nTraCantidad` int(11) DEFAULT NULL,
  `nTraPrecioTotal` double DEFAULT NULL,
  PRIMARY KEY (`nTraCodigo`),
  KEY `R_9` (`nVenCodigo`),
  KEY `R_10` (`nProCodigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Volcado de datos para la tabla `Detalle_venta`
--

INSERT INTO `Detalle_venta` (`nVenCodigo`, `nProCodigo`, `nTraCodigo`, `nTraPrecio`, `nTraCantidad`, `nTraPrecioTotal`) VALUES
(1, 1, 1, 60, 30, 1800),
(1, 2, 2, 60, 10, 600),
(2, 1, 3, 60, 30, 1800),
(2, 2, 4, 60, 10, 600),
(3, 1, 5, 60, 30, 1800),
(3, 2, 6, 60, 10, 600),
(4, 1, 7, 60, 20, 1200),
(4, 2, 8, 60, 10, 600),
(5, 1, 9, 60, 10, 600),
(5, 2, 10, 60, 5, 300),
(6, 1, 11, 60, 10, 600),
(6, 2, 12, 60, 10, 600),
(7, 2, 13, 60, 10, 600),
(7, 1, 14, 60, 10, 600),
(8, 1, 15, 60, 10, 600),
(8, 2, 16, 60, 10, 600),
(9, 1, 17, 60, 10, 600),
(9, 2, 18, 60, 10, 600),
(10, 1, 19, 60, 10, 600),
(10, 2, 20, 60, 10, 600),
(11, 1, 21, 60, 10, 600),
(11, 2, 22, 60, 10, 600),
(12, 2, 23, 60, 10, 600),
(12, 1, 24, 60, 10, 600),
(13, 2, 25, 60, 10, 600),
(13, 1, 26, 60, 20, 1200);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personal`
--

CREATE TABLE IF NOT EXISTS `personal` (
  `nPerCodigo` int(11) NOT NULL AUTO_INCREMENT,
  `cPerNombre` varchar(30) NOT NULL,
  `cPerDireccion` varchar(50) DEFAULT NULL,
  `cPerCi` varchar(30) DEFAULT NULL,
  `cPerTipoTelefono` varchar(30) DEFAULT NULL,
  `cPerNumTelefono` varchar(30) DEFAULT NULL,
  `cPerEstado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`nPerCodigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `personal`
--

INSERT INTO `personal` (`nPerCodigo`, `cPerNombre`, `cPerDireccion`, `cPerCi`, `cPerTipoTelefono`, `cPerNumTelefono`, `cPerEstado`) VALUES
(1, 'ytutyuyt', 'tyutyuty', '542367568', 'Casa', 'ertert', 'Activo'),
(2, 'efren', 'san pedro', '1105539397', 'Celular', '0992176345', 'Activo'),
(3, 'jorge', 'san jose', '110452278', 'Celular', '099245678', 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE IF NOT EXISTS `producto` (
  `nProCodigo` int(11) NOT NULL AUTO_INCREMENT,
  `cProNombre` varchar(100) NOT NULL,
  `nProCantidad` int(11) NOT NULL,
  `nProPrecioCompra` double NOT NULL,
  `nProPrecioVenta` double NOT NULL,
  `nProUtilidad` varchar(100) DEFAULT NULL,
  `cProDescripcion` varchar(500) DEFAULT NULL,
  `nCatCodigo` int(11) DEFAULT NULL,
  `cProMarca` varchar(30) NOT NULL,
  `cCodProProv` varchar(45) DEFAULT NULL,
  `cProEstado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`nProCodigo`),
  KEY `R_19` (`nCatCodigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`nProCodigo`, `cProNombre`, `nProCantidad`, `nProPrecioCompra`, `nProPrecioVenta`, `nProUtilidad`, `cProDescripcion`, `nCatCodigo`, `cProMarca`, `cCodProProv`, `cProEstado`) VALUES
(1, 'los vengadores', 20, 15, 30, '30', 'pelicula en formatao clasico', 1, 'Sony', '001', 'Activo'),
(2, 'Los Pitufos', 80, 10, 15, '15', 'primera enterga de los pitufos la pelicula', 1, 'Sony', '002', 'Activo'),
(3, 'los panchos', 20, 5, 15, '15', 'disco de musica de los panchos', 2, 'laser', '003', 'Activo'),
(4, 'la magia ', 10, 20, 20, '0', 'película animada para niñps', 1, 'Sony', '002', 'Activo'),
(5, 'Titan', 10, 5.5, 6.3, '14,5', 'Nro 2', 2, '', '', 'Activo'),
(6, 'los niños del maiz', 10, 4.3, 5.2, '20,9', 'pelicula en buena calidad', 4, 'sony', 'R456R', 'Activo'),
(7, 'rampunsel', 10, 16, 20, '25', 'pelicula animada', 1, 'DVD', '0003', 'Activo'),
(8, 'no se aceptan devoluciones', 15, 8, 12, '20', 'pelicula triste ', 1, 'DVD', 'R00236', 'Activo'),
(9, 'Goku', 2, 5, 6, '20', 'sadsad', 4, 'Sony', '546546', 'Activo'),
(10, 'Diablo', 10, 10, 15, '50', 'terror', 1, 'VCD', 'R0464', 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE IF NOT EXISTS `proveedor` (
  `nProvCodigo` int(11) NOT NULL AUTO_INCREMENT,
  `nProvNit` int(15) NOT NULL,
  `cProvNombre` varchar(30) NOT NULL,
  `cProvDireccion` varchar(40) DEFAULT NULL,
  `cProvNroFax` varchar(20) DEFAULT NULL,
  `cProvPaginaWeb` varchar(30) DEFAULT NULL,
  `cProvEmail` varchar(35) DEFAULT NULL,
  `cProvTipoTelefono` varchar(20) DEFAULT NULL,
  `cProvNumTelefono` varchar(50) DEFAULT NULL,
  `cProvEstado` varchar(45) NOT NULL,
  `cProvObservacion` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`nProvCodigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`nProvCodigo`, `nProvNit`, `cProvNombre`, `cProvDireccion`, `cProvNroFax`, `cProvPaginaWeb`, `cProvEmail`, `cProvTipoTelefono`, `cProvNumTelefono`, `cProvEstado`, `cProvObservacion`) VALUES
(1, 324234, 'laser', 'sucre y lourdes', '2589311', 'no tiene', 'laserqgmail.com', 'Casa', '2589311', 'Activo', ''),
(2, 105566, 'music loxa S.A', 'azuay y colon', '2589644', 'www.loxamusic.com', 'loxa@hotmail.com', 'Celular', '0992176345', 'Activo', ''),
(3, 110553, 'JC music', 'gran colombia y guaranda', '254896', 'no tine', 'jcmusic@hotmail.com', 'Celular', '0992176345', 'Activo', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_venta`
--

CREATE TABLE IF NOT EXISTS `tipo_venta` (
  `nTipDocumento` int(11) NOT NULL AUTO_INCREMENT,
  `cTipDocDescripcion` varchar(25) NOT NULL,
  `cTipDocSerie` varchar(20) DEFAULT NULL,
  `cTipDocCodigo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`nTipDocumento`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Volcado de datos para la tabla `tipo_venta`
--

INSERT INTO `tipo_venta` (`nTipDocumento`, `cTipDocDescripcion`, `cTipDocSerie`, `cTipDocCodigo`) VALUES
(1, 'NOTA VENTA', 'S001', 'C00001'),
(2, 'NOTA VENTA', 'S002', 'C00002'),
(3, 'NOTA VENTA', 'S003', 'C00003'),
(4, 'NOTA VENTA', 'S004', 'C00004'),
(5, 'NOTA VENTA', 'S005', 'C00005'),
(6, 'NOTA VENTA', 'S006', 'C00006'),
(7, 'NOTA VENTA', 'S007', 'C00007'),
(8, 'NOTA VENTA', 'S008', 'C00008'),
(9, 'NOTA VENTA', 'S009', 'C00009'),
(10, 'NOTA VENTA', 'S010', 'C00010'),
(11, 'NOTA VENTA', 'S011', 'C00011'),
(12, 'NOTA VENTA', 'S012', 'C00012'),
(13, 'NOTA VENTA', 'S013', 'C00013'),
(14, 'FACTURA', 'S014', 'C00014'),
(15, 'FACTURA', 'S015', 'C00015'),
(16, 'NOTA VENTA', 'S016', 'C00016'),
(17, 'NOTA VENTA', 'S017', 'C00017'),
(18, 'NOTA VENTA', 'S018', 'C00018'),
(19, 'NOTA VENTA', 'S019', 'C00019'),
(20, 'NOTA VENTA', 'S020', 'C00020'),
(21, 'NOTA VENTA', 'S021', 'C00021'),
(22, 'NOTA VENTA', 'S022', 'C00022'),
(23, 'NOTA VENTA', 'S023', 'C00023'),
(24, 'NOTA VENTA', 'S024', 'C00024'),
(25, 'FACTURA', 'S025', 'C00025'),
(26, 'NOTA VENTA', 'S026', 'C00026');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `nUsuCodigo` int(11) NOT NULL AUTO_INCREMENT,
  `cUsuId` varchar(30) DEFAULT NULL,
  `cUsuClave` varchar(30) DEFAULT NULL,
  `nPerCodigo` int(11) DEFAULT NULL,
  `cUsuEstado` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`nUsuCodigo`),
  KEY `R_23` (`nPerCodigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`nUsuCodigo`, `cUsuId`, `cUsuClave`, `nPerCodigo`, `cUsuEstado`) VALUES
(1, 'Jorge', '123', 1, 'Activo'),
(3, 'luis', '522844', 1, 'Activo'),
(4, 'efren', '1234', 2, 'Activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE IF NOT EXISTS `venta` (
  `nVenCodigo` int(11) NOT NULL AUTO_INCREMENT,
  `nCliCodigo` int(11) NOT NULL,
  `nPerCodigo` int(11) NOT NULL,
  `cVenFecha` date NOT NULL,
  `nVenMontoTotal` double NOT NULL,
  `nTipDocumento` int(11) NOT NULL,
  `cVenEstado` varchar(9) NOT NULL,
  `cFormapago` varchar(9) NOT NULL,
  PRIMARY KEY (`nVenCodigo`),
  KEY `R_7` (`nCliCodigo`),
  KEY `R_8` (`nPerCodigo`),
  KEY `R_21` (`nTipDocumento`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`nVenCodigo`, `nCliCodigo`, `nPerCodigo`, `cVenFecha`, `nVenMontoTotal`, `nTipDocumento`, `cVenEstado`, `cFormapago`) VALUES
(1, 1, 1, '2011-11-17', 4200, 4, 'REALIZADA', 'CONTADO'),
(2, 1, 1, '2011-11-20', 4200, 5, 'REALIZADA', 'CREDITO'),
(3, 1, 1, '2011-11-20', 4200, 6, 'REALIZADA', 'CREDITO'),
(4, 1, 1, '2011-11-27', 3000, 7, 'REALIZADA', 'CONTADO'),
(5, 1, 1, '2011-11-27', 1500, 8, 'REALIZADA', 'CONTADO'),
(6, 1, 1, '2011-11-28', 1200, 9, 'REALIZADA', 'CREDITO'),
(7, 1, 1, '2011-11-28', 1200, 10, 'REALIZADA', 'CONTADO'),
(8, 2, 1, '2011-11-28', 1200, 11, 'REALIZADA', 'CREDITO'),
(9, 2, 1, '2011-11-28', 1200, 12, 'REALIZADA', 'CONTADO'),
(10, 1, 1, '2011-11-28', 1200, 13, 'REALIZADA', 'CREDITO'),
(11, 1, 1, '2011-11-28', 1542, 14, 'REALIZADA', 'CREDITO'),
(12, 1, 1, '2011-11-28', 1542, 15, 'REALIZADA', 'CREDITO'),
(13, 4, 1, '2011-12-08', 1800, 17, 'REALIZADA', 'CONTADO');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `compra_ibfk_1` FOREIGN KEY (`nProvCodigo`) REFERENCES `proveedor` (`nProvCodigo`),
  ADD CONSTRAINT `compra_ibfk_2` FOREIGN KEY (`nProCodigo`) REFERENCES `producto` (`nProCodigo`);

--
-- Filtros para la tabla `contado`
--
ALTER TABLE `contado`
  ADD CONSTRAINT `contado_ibfk_1` FOREIGN KEY (`nVenCodigo`) REFERENCES `venta` (`nVenCodigo`) ON DELETE CASCADE;

--
-- Filtros para la tabla `credito`
--
ALTER TABLE `credito`
  ADD CONSTRAINT `credito_ibfk_1` FOREIGN KEY (`nVenCodigo`) REFERENCES `venta` (`nVenCodigo`) ON DELETE CASCADE;

--
-- Filtros para la tabla `cronogramapago`
--
ALTER TABLE `cronogramapago`
  ADD CONSTRAINT `cronogramapago_ibfk_1` FOREIGN KEY (`nVenCodigo`) REFERENCES `credito` (`nVenCodigo`);

--
-- Filtros para la tabla `Detalle_venta`
--
ALTER TABLE `Detalle_venta`
  ADD CONSTRAINT `Detalle_venta_ibfk_1` FOREIGN KEY (`nVenCodigo`) REFERENCES `venta` (`nVenCodigo`),
  ADD CONSTRAINT `Detalle_venta_ibfk_2` FOREIGN KEY (`nProCodigo`) REFERENCES `producto` (`nProCodigo`);

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_2` FOREIGN KEY (`nCatCodigo`) REFERENCES `categoria` (`nCatCodigo`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`nPerCodigo`) REFERENCES `personal` (`nPerCodigo`);

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`nCliCodigo`) REFERENCES `cliente` (`nCliCodigo`),
  ADD CONSTRAINT `venta_ibfk_2` FOREIGN KEY (`nPerCodigo`) REFERENCES `personal` (`nPerCodigo`),
  ADD CONSTRAINT `venta_ibfk_3` FOREIGN KEY (`nTipDocumento`) REFERENCES `tipo_venta` (`nTipDocumento`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
