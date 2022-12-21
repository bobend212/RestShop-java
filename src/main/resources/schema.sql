-- DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS cart;

CREATE TABLE cart (
  cart_id BIGINT AUTO_INCREMENT NOT NULL,
   order_status VARCHAR(50),
   total_price FLOAT,
   CONSTRAINT pk_cart PRIMARY KEY (cart_id)
);

CREATE TABLE item (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255),
   price FLOAT,
   cart_id BIGINT,
   CONSTRAINT pk_item PRIMARY KEY (id)
);

ALTER TABLE item ADD CONSTRAINT FK_ITEM_ON_CART_ID FOREIGN KEY (cart_id) REFERENCES cart (cart_id);

-- CREATE TABLE productData (
--   id BIGINT NOT NULL AUTO_INCREMENT,
--    name VARCHAR(255),
--    price FLOAT,
--    PRIMARY KEY (id)
-- );