-- products available in the store
INSERT INTO PRODUCT (name, price) VALUES('Milk', 2);
INSERT INTO PRODUCT (name, price) VALUES('Butter', 3);
INSERT INTO PRODUCT (name, price) VALUES('Water', 1);
INSERT INTO PRODUCT (name, price) VALUES('Ham', 7);

-- carts
INSERT INTO CART (order_status, total_price) VALUES(1, 0);
INSERT INTO CART (order_status, total_price) VALUES(1, 0);
INSERT INTO CART (order_status, total_price) VALUES(0, 0);

-- products assigned to carts
INSERT INTO ITEM (name, price, cart_id) VALUES('Milk', 2, 1);
INSERT INTO ITEM (name, price, cart_id) VALUES('Butter', 3, 1);
INSERT INTO ITEM (name, price, cart_id) VALUES('Butter', 3, 2);