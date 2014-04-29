SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `telephoneDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
CREATE SCHEMA IF NOT EXISTS `telephonedb` DEFAULT CHARACTER SET utf8 ;
USE `telephoneDB` ;

-- -----------------------------------------------------
-- Table `telephoneDB`.`user_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `telephoneDB`.`user_type` (
  `iduser_type` INT NOT NULL AUTO_INCREMENT,
  `type_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iduser_type`),
  UNIQUE INDEX `type_name_UNIQUE` (`type_name` ASC),
  UNIQUE INDEX `iduser_type_UNIQUE` (`iduser_type` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `telephoneDB`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `telephoneDB`.`user` (
  `id_user` INT NOT NULL,
  `user_type_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `loggedin` TINYINT(1) NOT NULL,
  `banned` TINYINT(1) NULL,
  INDEX `fk_user_user_type1_idx` (`user_type_id` ASC),
  PRIMARY KEY (`id_user`),
  UNIQUE INDEX `id_user_UNIQUE` (`id_user` ASC),
  CONSTRAINT `fk_user_user_type1`
    FOREIGN KEY (`user_type_id`)
    REFERENCES `telephoneDB`.`user_type` (`iduser_type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `telephoneDB`.`service_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `telephoneDB`.`service_type` (
  `idservice_type` INT NOT NULL AUTO_INCREMENT,
  `type_name` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  PRIMARY KEY (`idservice_type`),
  UNIQUE INDEX `idservice_type_UNIQUE` (`idservice_type` ASC),
  UNIQUE INDEX `type_name_UNIQUE` (`type_name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `telephoneDB`.`service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `telephoneDB`.`service` (
  `id_service` INT NOT NULL AUTO_INCREMENT,
  `service_type_id` INT NOT NULL,
  `recipient` INT NOT NULL,
  `sender` INT NOT NULL,
  `date` DATE NOT NULL,
  `duration` INT NULL,
  PRIMARY KEY (`id_service`),
  UNIQUE INDEX `id_service_UNIQUE` (`id_service` ASC),
  INDEX `fk_service_service_type1_idx` (`service_type_id` ASC),
  INDEX `fk_service_user1_idx` (`recipient` ASC),
  INDEX `fk_service_user2_idx` (`sender` ASC),
  CONSTRAINT `fk_service_service_type1`
    FOREIGN KEY (`service_type_id`)
    REFERENCES `telephoneDB`.`service_type` (`idservice_type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_user1`
    FOREIGN KEY (`recipient`)
    REFERENCES `telephoneDB`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_user2`
    FOREIGN KEY (`sender`)
    REFERENCES `telephoneDB`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `telephoneDB`.`check`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `telephoneDB`.`check` (
  `check_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `service_id` INT NOT NULL,
  `price` INT NOT NULL,
  `expired` TINYINT(1) NOT NULL,
  `paid` TINYINT(1) NOT NULL,
  `creation_date` DATE NOT NULL,
  `expire_date` DATE NOT NULL,
  PRIMARY KEY (`check_id`, `user_id`, `service_id`),
  UNIQUE INDEX `check_id_UNIQUE` (`check_id` ASC),
  INDEX `fk_check_user1_idx` (`user_id` ASC),
  INDEX `fk_check_service1_idx` (`service_id` ASC),
  CONSTRAINT `fk_check_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `telephoneDB`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_check_service1`
    FOREIGN KEY (`service_id`)
    REFERENCES `telephoneDB`.`service` (`id_service`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `telephonedb` ;

-- -----------------------------------------------------
-- Table `telephonedb`.`user_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `telephonedb`.`user_type` (
  `iduser_type` INT(11) NOT NULL AUTO_INCREMENT,
  `type_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iduser_type`),
  UNIQUE INDEX `type_name_UNIQUE` (`type_name` ASC),
  UNIQUE INDEX `iduser_type_UNIQUE` (`iduser_type` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `telephonedb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `telephonedb`.`user` (
  `id_user` INT(11) NOT NULL AUTO_INCREMENT,
  `user_type_iduser_type` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `banned` TINYINT(1) NULL DEFAULT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE INDEX `id_user_UNIQUE` (`id_user` ASC),
  INDEX `fk_user_user_type1_idx` (`user_type_iduser_type` ASC),
  CONSTRAINT `fk_user_user_type1`
    FOREIGN KEY (`user_type_iduser_type`)
    REFERENCES `telephonedb`.`user_type` (`iduser_type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `telephonedb`.`check`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `telephonedb`.`check` (
  `check_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `price` INT(11) NOT NULL,
  `expired` TINYINT(1) NOT NULL,
  `paid` TINYINT(1) NOT NULL,
  `creaction_date` DATE NOT NULL,
  `expire_date` DATE NOT NULL,
  PRIMARY KEY (`check_id`, `user_id`),
  UNIQUE INDEX `check_id_UNIQUE` (`check_id` ASC),
  INDEX `fk_check_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_check_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `telephonedb`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `telephonedb`.`service_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `telephonedb`.`service_type` (
  `idservice_type` INT(11) NOT NULL AUTO_INCREMENT,
  `type_name` VARCHAR(45) NOT NULL,
  `price` INT(11) NOT NULL,
  PRIMARY KEY (`idservice_type`),
  UNIQUE INDEX `idservice_type_UNIQUE` (`idservice_type` ASC),
  UNIQUE INDEX `type_name_UNIQUE` (`type_name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `telephonedb`.`service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `telephonedb`.`service` (
  `id_service` INT(11) NOT NULL AUTO_INCREMENT,
  `service_type_id` INT(11) NOT NULL,
  `recipient` INT(11) NOT NULL,
  `sender` INT(11) NOT NULL,
  `date` DATE NOT NULL,
  `duration` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_service`),
  UNIQUE INDEX `id_service_UNIQUE` (`id_service` ASC),
  INDEX `fk_service_service_type1_idx` (`service_type_id` ASC),
  INDEX `fk_service_user1_idx` (`recipient` ASC),
  INDEX `fk_service_user2_idx` (`sender` ASC),
  CONSTRAINT `fk_service_service_type1`
    FOREIGN KEY (`service_type_id`)
    REFERENCES `telephonedb`.`service_type` (`idservice_type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_user1`
    FOREIGN KEY (`recipient`)
    REFERENCES `telephonedb`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_user2`
    FOREIGN KEY (`sender`)
    REFERENCES `telephonedb`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
