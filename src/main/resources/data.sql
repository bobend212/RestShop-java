-- products available in the store
INSERT INTO PRODUCT (name, price) VALUES('Milk', 2);
INSERT INTO PRODUCT (name, price) VALUES('Butter', 3);
INSERT INTO PRODUCT (name, price) VALUES('Water', 1);
INSERT INTO PRODUCT (name, price) VALUES('Ham', 7);

-- carts
INSERT INTO CART (order_status) VALUES('PENDING');
INSERT INTO CART (order_status) VALUES('PENDING');
INSERT INTO CART (order_status) VALUES('EMPTY');

-- products assigned to carts
INSERT INTO ITEM (product_id, cart_id) VALUES(1, 1);
INSERT INTO ITEM (product_id, cart_id) VALUES(3, 1);
INSERT INTO ITEM (product_id, cart_id) VALUES(3, 2);
INSERT INTO ITEM (product_id, cart_id) VALUES(4, 2);

