-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


-- -----------------------------------------------------
-- Schema s190344
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `s190344` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema s190344
-- -----------------------------------------------------
USE `s190344` ;

-- -----------------------------------------------------
-- Table `s190344`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `s190344`.`roles` (
  `role_id` INT NOT NULL,
  `rolename` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `roleId_UNIQUE` (`role_id` ASC) VISIBLE,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `s190344`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `s190344`.`users` (
  `user_id` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `initials` VARCHAR(45) NOT NULL,
  `inactive` TINYINT NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `brugerId_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `userName_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `s190344`.`recipes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `s190344`.`recipes` (
  `recipe_id` INT NOT NULL,
  `recipe_end_date` DATE NOT NULL,
  `recipe_name` VARCHAR(145) NOT NULL,
  `product_amount` INT NOT NULL,
  `author_id_user_id` INT NOT NULL,
  PRIMARY KEY (`recipe_id`, `recipe_end_date`),
  INDEX `fk_Recepter_Brugere1_idx` (`author_id_user_id` ASC) VISIBLE,
  CONSTRAINT `author_id_user_id`
    FOREIGN KEY (`author_id_user_id`)
    REFERENCES `s190344`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `s190344`.`resources`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `s190344`.`resources` (
  `resource_id` INT NOT NULL,
  `resource_name` VARCHAR(45) NOT NULL,
  `reorder` BIT NOT NULL,
  `inactive` BIT,
  PRIMARY KEY (`resource_id`),
  UNIQUE INDEX `resource_id_UNIQUE` (`resource_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `s190344`.`resource_batches`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `s190344`.`resource_batches` (
  `resource_batch_id` INT NOT NULL,
  `resource_batch_amount` DOUBLE NOT NULL,
  `supplier_name` VARCHAR(145) NOT NULL,
  `is_leftover` BIT NOT NULL,
  `resource_id` INT NOT NULL,
  PRIMARY KEY (`resource_batch_id`),
  UNIQUE INDEX `batchId_UNIQUE` (`resource_batch_id` ASC) VISIBLE,
  INDEX `fk_RaavareBatches_Raavare1_idx` (`resource_id` ASC) VISIBLE,
  CONSTRAINT `FK3_resource_id`
    FOREIGN KEY (`resource_id`)
    REFERENCES `s190344`.`resources` (`resource_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `s190344`.`product_batches`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `s190344`.`product_batches` (
  `product_batch_id` INT NOT NULL,
  `creation_date` DATE NOT NULL,
  `order_status` VARCHAR(20) NOT NULL,
  `production_EndDate` DATE NOT NULL,
  `inactive`BIT NOT NULL,
  `recipe_id` INT NOT NULL,
  `recipe_end_date` DATE NOT NULL,
  `productionleader_id_user_id` INT NOT NULL,
  PRIMARY KEY (`product_batch_id`),
  UNIQUE INDEX `productBatchNr_UNIQUE` (`product_batch_id` ASC) VISIBLE,
  INDEX `fk_ProductBatches_Recepter1_idx` (`recipe_id` ASC, `recipe_end_date` ASC) VISIBLE,
  INDEX `fk_ProductBatches_Brugere1_idx` (`productionleader_id_user_id` ASC) VISIBLE,
  CONSTRAINT `FK1_recipe_PM`
    FOREIGN KEY (`recipe_id` , `recipe_end_date`)
    REFERENCES `s190344`.`recipes` (`recipe_id` , `recipe_end_date`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_productionleader_id_user_id`
    FOREIGN KEY (`productionleader_id_user_id`)
    REFERENCES `s190344`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `s190344`.`rel_recipes_resources`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `s190344`.`rel_recipes_resources` (
  `resource_id` INT NOT NULL,
  `recipe_id` INT NOT NULL,
  `recipe_end_date` DATE NOT NULL,
  `resource_amount` DOUBLE NOT NULL,
  `tolerance`	double NOT NULL,
  PRIMARY KEY (`resource_id`, `recipe_id`, `recipe_end_date`),
  INDEX `fk_relReceptRaavare_Recepter1_idx` (`recipe_id` ASC, `recipe_end_date` ASC) VISIBLE,
  CONSTRAINT `FK2_resource_id`
    FOREIGN KEY (`resource_id`)
    REFERENCES `s190344`.`resources` (`resource_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_recipe_PM`
    FOREIGN KEY (`recipe_id` , `recipe_end_date`)
    REFERENCES `s190344`.`recipes` (`recipe_id` , `recipe_end_date`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `s190344`.`rel_productBatches_resourceBatches`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `s190344`.`rel_productBatches_resourceBatches` (
  `resource_batch_id` INT NOT NULL,
  `product_batch_id` INT NOT NULL,
  `net_amount` DOUBLE NOT NULL,
  `tara` double NOT NULL, 
  PRIMARY KEY (`resource_batch_id`, `product_batch_id`),
  INDEX `fk_relProductRaavare_RaavareBatches1_idx` (`resource_batch_id` ASC) VISIBLE,
  CONSTRAINT `FK1_product_batch_id`
    FOREIGN KEY (`product_batch_id`)
    REFERENCES `s190344`.`product_batches` (`product_batch_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_resource_batch_id`
    FOREIGN KEY (`resource_batch_id`)
    REFERENCES `s190344`.`resource_batches` (`resource_batch_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `s190344`.`rel_users_productBatches`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `s190344`.`rel_users_productBatches` (
  `user_id` INT NOT NULL,
  `product_batch_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `product_batch_id`),
  INDEX `fk_relBrugereProduct_ProductBatches1_idx` (`product_batch_id` ASC) VISIBLE,
  CONSTRAINT `FK_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `s190344`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_product_batch_id`
    FOREIGN KEY (`product_batch_id`)
    REFERENCES `s190344`.`product_batches` (`product_batch_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `s190344`.`rel_roles_users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `s190344`.`rel_roles_users` (
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  CONSTRAINT `FK_role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `s190344`.`roles` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK2_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `s190344`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

    


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;