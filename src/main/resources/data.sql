INSERT INTO productData(name, price) VALUES('Milk', 2);
INSERT INTO productData(name, price) VALUES('Butter', 3);
INSERT INTO productData(name, price) VALUES('Water', 1);

INSERT INTO cart(order_status, total_price) VALUES(1, 0);
INSERT INTO cart(order_status, total_price) VALUES(0, 0);

INSERT INTO product(name, price, cart_id) VALUES('Milk', 2, 1);
INSERT INTO product(name, price, cart_id) VALUES('Butter', 3, 1);