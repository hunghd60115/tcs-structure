-- ----------------------------
-- Table structure for `tbl_logs`
-- ----------------------------

DROP TABLE IF EXISTS `tbl_logs`;
CREATE  TABLE `tbl_logs` (
  `ID` VARCHAR(45) NOT NULL COMMENT 'ID',
  `CHANNEL_CODE` VARCHAR(45) NOT NULL COMMENT 'Channel Code',
  `START_TIME` VARCHAR(12) NOT NULL COMMENT 'Start time', 
  `END_TIME` VARCHAR(12) NOT NULL COMMENT 'End time', 
  `TYPE` VARCHAR(12) NOT NULL COMMENT 'Type of log',
  `DETAIL` VARCHAR(255) NOT NULL COMMENT 'Detail of log',
  `SCORE` INT(31) DEFAULT NULL COMMENT 'Score', 
  PRIMARY KEY (`ID`))
ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_unicode_ci COMMENT='Filter channel';