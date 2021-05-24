CREATE TABLE `DMA_Dumpsters` (
  `Dumpster_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Dumpster_Longtitude` varchar(20) DEFAULT NULL,
  `Dumpster_Latitude` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Dumpster_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

CREATE TABLE `DMA_Dumpsters_Types` (
  `Dumpster_Type` varchar(20) DEFAULT NULL,
  `Dumpster_ID` int(11) DEFAULT NULL,
  `Dumpster_Full` tinyint(1) DEFAULT 0,
  KEY `FK_Dumpster` (`Dumpster_ID`),
  CONSTRAINT `CHK_Dumpster_Type` CHECK (`Dumpster_Type` = 'Restaffald' or `Dumpster_Type` = 'Pap & Papir' or `Dumpster_Type` = 'Plast & Metal' or `Dumpster_Type` = 'Glas' or `Dumpster_Type` = 'Minielektronik' or `Dumpster_Type` = 'Batterier' or `Dumpster_Type` = 'Stort affald')
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE `DMA_Request` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` varchar(45) NOT NULL,
  `RequestURL` varchar(128) NOT NULL,
  `RequestTime` varchar(24) NOT NULL,
  `RequestResponse` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1154 DEFAULT CHARSET=latin1;

CREATE TABLE `DMA_User_Reports` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `User_ID` varchar(48) DEFAULT NULL,
  `Dumpster_ID` int(11) DEFAULT NULL,
  `Dumpster_Type` varchar(20) DEFAULT NULL,
  `Report_Date` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Dumpster` (`Dumpster_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=169 DEFAULT CHARSET=latin1;
