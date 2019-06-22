INSERT INTO `resources` (`resource_id` ,`resource_name`,`reorder`, `inactive`) VALUES (1,"Salt",false, false); 
INSERT INTO `resources` (`resource_id` ,`resource_name`,`reorder`, `inactive`) VALUES (2,"Vand",false, false); 
INSERT INTO `resources` (`resource_id` ,`resource_name`,`reorder`, `inactive`) VALUES (3,"Citron",false, false); 
INSERT INTO `resources` (`resource_id` ,`resource_name`,`reorder`, `inactive`) VALUES (4,"Peber",false, false); 
INSERT INTO `resources` (`resource_id` ,`resource_name`,`reorder`, `inactive`) VALUES (5,"Timian",false, false); 
INSERT INTO `resources` (`resource_id` ,`resource_name`,`reorder`, `inactive`) VALUES (6,"Karry",false, false); 

INSERT INTO `resource_batches` (`resource_batch_id` , `resource_id` , `resource_batch_amount` ,`supplier_name`,`is_leftover` ) VALUES (1, 1,250,"Roskilde a/s" , false); 
INSERT INTO `resource_batches` (`resource_batch_id` , `resource_id` , `resource_batch_amount` ,`supplier_name`,`is_leftover` ) VALUES (2, 2,250,"Novo Nordisk" , false); 
INSERT INTO `resource_batches` (`resource_batch_id` , `resource_id` , `resource_batch_amount` ,`supplier_name`,`is_leftover` ) VALUES (3, 3,250,"Salling group" , false); 
INSERT INTO `resource_batches` (`resource_batch_id` , `resource_id` , `resource_batch_amount` ,`supplier_name`,`is_leftover` ) VALUES (4, 4,250,"Borup Kemi" , false); 
INSERT INTO `resource_batches` (`resource_batch_id` , `resource_id` , `resource_batch_amount` ,`supplier_name`,`is_leftover` ) VALUES (5, 5,250,"DTU", false); 

insert into `roles` (`role_id`, `rolename`)values(1,"Administrator" );
insert into `roles` (`role_id`, `rolename`)values(2,"Farmaceut" );
insert into `roles` (`role_id`, `rolename`)values(3,"Laborant" );
insert into `roles` (`role_id`, `rolename`)values(4,"ProduktionsLeder" );

insert into `users` (`user_id`, `username`, `initials`,`inactive`)values( 1, "Hans Byager","HB",false);
insert into `users` (`user_id`, `username`, `initials`,`inactive`)values( 2, "Mette Tobiasen","MT",false);
insert into `users` (`user_id`, `username`, `initials`,`inactive`)values( 3, "Emil Lundqvist","",false);
insert into `users` (`user_id`, `username`, `initials`,`inactive`)values( 4, "Casper Hansen","CH",false);

insert into `recipes` (`recipe_id`, `recipe_end_date`, `recipe_name`, `product_amount`, `author_id_user_id`) values (1 , '9999-12-31' , "Saltvand" , 3 , 1); 
insert into `recipes` (`recipe_id`, `recipe_end_date`, `recipe_name`, `product_amount`, `author_id_user_id`) values (2 , '2019-09-29' , "CitronVand" , 1 , 1); 
insert into `recipes` (`recipe_id`, `recipe_end_date`, `recipe_name`, `product_amount`, `author_id_user_id`) values (3 , '2018-09-29' , "TimianVand" , 7 , 1); 
insert into `recipes` (`recipe_id`, `recipe_end_date`, `recipe_name`, `product_amount`, `author_id_user_id`) values (4 , '2032-03-11' , "KarrySaltVand" , 19 , 1); 

insert into `rel_roles_users` (`user_id`,`role_id`)values(1,4);
insert into `rel_roles_users` (`user_id`,`role_id`)values(1,2);
insert into `rel_roles_users` (`user_id`,`role_id`)values(1,3);

insert into `rel_roles_users` (`user_id`,`role_id`)values(2,3);

insert into `rel_roles_users` (`user_id`,`role_id`)values(3,3);
insert into `rel_roles_users` (`user_id`,`role_id`)values(3,2);

insert into `rel_roles_users` (`user_id`,`role_id`)values(4,1);
insert into `rel_roles_users` (`user_id`,`role_id`)values(4,2);
insert into `rel_roles_users` (`user_id`,`role_id`)values(4,3);
insert into `rel_roles_users` (`user_id`,`role_id`)values(4,4);

insert into `rel_recipes_resources`(`resource_id`,`resource_amount`, `recipe_id`, `recipe_end_date`, `tolerance`)values(1,0.100,1,'9999-12-31', 2 );
insert into `rel_recipes_resources`(`resource_id`,`resource_amount`, `recipe_id`, `recipe_end_date`, `tolerance`)values(2,0.050,1,'9999-12-31', 2.5);
insert into `rel_recipes_resources`(`resource_id`,`resource_amount`, `recipe_id`, `recipe_end_date`, `tolerance`)values(2,0.150,2,'2019-09-29', 2);
insert into `rel_recipes_resources`(`resource_id`,`resource_amount`, `recipe_id`, `recipe_end_date`, `tolerance`)values(3,0.100,2,'2019-09-29', 2.5);
insert into `rel_recipes_resources`(`resource_id`,`resource_amount`, `recipe_id`, `recipe_end_date`, `tolerance`)values(5,0.100,3,'2018-09-29', 2.5);
insert into `rel_recipes_resources`(`resource_id`,`resource_amount`, `recipe_id`, `recipe_end_date`, `tolerance`)values(2,0.100,3,'2018-09-29', 2);


insert into `product_batches`(`product_batch_id`,`creation_date`,`order_status`,`production_EndDate`, `inactive`,`recipe_id`,`recipe_end_date`,`productionleader_id_user_id`) values (1, '2018-09-29' ,"oprettet",'9999-12-31' , false,  1, '9999-12-31', 1);
insert into `product_batches`(`product_batch_id`,`creation_date`,`order_status`,`production_EndDate`, `inactive`,`recipe_id`,`recipe_end_date`,`productionleader_id_user_id`) values (2, '2018-09-29' ,"oprettet",'9999-12-31' , false, 2, '2019-09-29', 1);
insert into `product_batches`(`product_batch_id`,`creation_date`,`order_status`,`production_EndDate`, `inactive`,`recipe_id`,`recipe_end_date`,`productionleader_id_user_id`) values (3, '2018-09-29' ,"oprettet",'9999-12-31' ,false, 3, '2018-09-29', 1);

insert into `rel_productBatches_resourceBatches`(`resource_batch_id`,`product_batch_id`,`net_amount`,`tara`) values (1, 1, 0.0, 0.0);
insert into `rel_productBatches_resourceBatches`(`resource_batch_id`,`product_batch_id`,`net_amount`,`tara`) values (2, 1, 0.0, 0.0);
insert into `rel_productBatches_resourceBatches`(`resource_batch_id`,`product_batch_id`,`net_amount`,`tara`) values (2, 2, 0.0, 0.0);
insert into `rel_productBatches_resourceBatches`(`resource_batch_id`,`product_batch_id`,`net_amount`,`tara`) values (3, 1, 0.0, 0.0);




