INSERT INTO `telephonedb`.`user_type` (`iduser_type`, `type_name`) VALUES ('1', 'admin');
INSERT INTO `telephonedb`.`user_type` (`iduser_type`, `type_name`) VALUES ('2', 'user');

INSERT INTO `telephonedb`.`user` (`id_user`, `user_type_id`, `name`, `password`, `loggedin`) 
VALUES ('1', '1', 'admin', 'admin', '0');
INSERT INTO `telephonedb`.`user` (`id_user`, `user_type_id`, `name`, `password`, `loggedin`) 
VALUES ('222000', '2', 'root', 'root', '0');
INSERT INTO `telephonedb`.`user` (`id_user`, `user_type_id`, `name`, `password`, `loggedin`) 
VALUES ('222001', '2', 'Petro', 'petro', '0');
INSERT INTO `telephonedb`.`user` (`id_user`, `user_type_id`, `name`, `password`, `loggedin`) 
VALUES ('222002', '2', 'Vasylj', 'vasylj', '0');
INSERT INTO telephonedb.`user` (id_user, user_type_id, `name`, password, loggedin, banned) 
	VALUES (222003, 2, 'Alex', 'alex', false, true);
INSERT INTO telephonedb.`user` (id_user, user_type_id, `name`, password, loggedin, banned) 
	VALUES (222004, 2, 'Tom', 'tom', false, true);



INSERT INTO `telephonedb`.`service_type` (`type_name`, `price`) VALUES ('call', '5');
INSERT INTO `telephonedb`.`service_type` (`type_name`, `price`) VALUES ('sms', '7');
INSERT INTO `telephonedb`.`service_type` (`type_name`, `price`) VALUES ('mms', '14');
INSERT INTO `telephonedb`.`service_type` (`type_name`, `price`) VALUES ('voice mail', '10');
INSERT INTO `telephonedb`.`service_type` (`type_name`, `price`) VALUES ('voice mail listen', '0');


INSERT INTO telephonedb.service (service_type_id, recipient, sender, `date`, duration) 
	VALUES (3, 1, 222000, '2014-04-20', NULL);
INSERT INTO telephonedb.service (service_type_id, recipient, sender, `date`, duration) 
	VALUES (3, 222001, 222000, '2014-04-22', NULL);
INSERT INTO telephonedb.service (service_type_id, recipient, sender, `date`, duration) 
	VALUES (3, 222002, 222000, '2014-04-22', NULL);
INSERT INTO telephonedb.service (service_type_id, recipient, sender, `date`, duration) 
	VALUES (3, 222002, 222000, '2014-04-23', NULL);
	
	
INSERT INTO telephonedb.service (service_type_id, recipient, sender, `date`, duration) 
	VALUES (2, 1, 222002, '2014-04-20', NULL);
INSERT INTO telephonedb.service (service_type_id, recipient, sender, `date`, duration) 
	VALUES (2, 222000, 222002, '2014-04-21', NULL);
INSERT INTO telephonedb.service (service_type_id, recipient, sender, `date`, duration) 
	VALUES (2, 222003, 222002, '2014-04-21', NULL);
INSERT INTO telephonedb.service (service_type_id, recipient, sender, `date`, duration) 
	VALUES (2, 222000, 222002, '2014-04-21', NULL);



INSERT INTO telephonedb.`check` (user_id, service_id, price, expired, paid, creation_date, expire_date) 
	VALUES (222000, 1, 7, false, true, '2014-04-20', '2014-05-20');
INSERT INTO telephonedb.`check` (user_id, service_id, price, expired, paid, creation_date, expire_date) 
	VALUES (222000, 2, 7, true, false, '2014-04-22', '2014-04-22');
INSERT INTO telephonedb.`check` (user_id, service_id, price, expired, paid, creation_date, expire_date) 
	VALUES (222000, 3, 7, false, false, '2014-04-22', '2014-05-22');
INSERT INTO telephonedb.`check` (user_id, service_id, price, expired, paid, creation_date, expire_date) 
	VALUES (222000, 4, 7, false, true, '2014-04-23', '2014-05-23');
	
INSERT INTO telephonedb.`check` (user_id, service_id, price, expired, paid, creation_date, expire_date) 
	VALUES (222002, 5, 7, false, true, '2014-04-20', '2014-05-20');
INSERT INTO telephonedb.`check` (user_id, service_id, price, expired, paid, creation_date, expire_date) 
	VALUES (222002, 6, 7, true, false, '2014-04-21', '2014-05-21');
INSERT INTO telephonedb.`check` (user_id, service_id, price, expired, paid, creation_date, expire_date) 
	VALUES (222002, 7, 7, false, false, '2014-04-21', '2014-05-21');
INSERT INTO telephonedb.`check` (user_id, service_id, price, expired, paid, creation_date, expire_date) 
	VALUES (222002, 8, 7, true, true, '2014-04-21', '2014-04-21');


	
	

