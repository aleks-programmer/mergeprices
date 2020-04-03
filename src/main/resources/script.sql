
create table price
(
	id int auto_increment
		primary key,
	product_code varchar(500) not null,
	number int not null,
	depart int not null,
	begin datetime not null,
	end datetime not null,
	value decimal not null
)
;

INSERT INTO prices.price (id, product_code, number, depart, begin, end, value) VALUES (1, '122856', 1, 1, '2013-01-01 00:00:00', '2013-01-31 23:59:59', 11000);
INSERT INTO prices.price (id, product_code, number, depart, begin, end, value) VALUES (2, '122856', 2, 1, '2013-01-10 00:00:00', '2013-01-20 23:59:59', 99000);
INSERT INTO prices.price (id, product_code, number, depart, begin, end, value) VALUES (3, '6654', 1, 2, '2013-01-01 00:00:00', '2013-01-31 23:59:59', 5000);
INSERT INTO prices.price (id, product_code, number, depart, begin, end, value) VALUES (4, '122856', 1, 1, '2013-01-20 00:00:00', '2013-02-20 23:59:59', 11000);
INSERT INTO prices.price (id, product_code, number, depart, begin, end, value) VALUES (5, '122856', 2, 1, '2013-01-15 00:00:00', '2013-01-25 23:59:59', 92000);
INSERT INTO prices.price (id, product_code, number, depart, begin, end, value) VALUES (6, '6654', 1, 2, '2013-01-12 00:00:00', '2013-01-13 23:59:59', 4000);
INSERT INTO prices.price (id, product_code, number, depart, begin, end, value) VALUES (7, '6654', 1, 2, '2013-01-01 00:00:00', '2013-01-02 23:59:59', 7000);