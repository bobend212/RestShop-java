--DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS item;
--DROP TABLE IF EXISTS cart;

--CREATE TABLE product (
--  id BIGINT AUTO_INCREMENT NOT NULL,
--   name VARCHAR(255),
--   price FLOAT,
--   CONSTRAINT pk_product PRIMARY KEY (id)
--);

--CREATE TABLE cart (
--  cart_id BIGINT AUTO_INCREMENT NOT NULL,
--   order_status VARCHAR(255),
--   CONSTRAINT pk_cart PRIMARY KEY (cart_id)
--);
--
--CREATE TABLE item (
--  id BIGINT AUTO_INCREMENT NOT NULL,
--   product_id BIGINT,
--   CONSTRAINT pk_item PRIMARY KEY (id)
--);

CREATE TABLE item (
  id BIGINT AUTO_INCREMENT NOT NULL,
   product_id BIGINT,
   cart_id BIGINT,
   CONSTRAINT pk_item PRIMARY KEY (id)
);

ALTER TABLE item ADD CONSTRAINT FK_ITEM_ON_CART FOREIGN KEY (cart_id) REFERENCES cart (cart_id);

ALTER TABLE item ADD CONSTRAINT FK_ITEM_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);



