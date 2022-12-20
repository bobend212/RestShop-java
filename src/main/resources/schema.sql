DROP TABLE IF EXISTS productData;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS cart;

CREATE TABLE cart (
  cart_id BIGINT AUTO_INCREMENT NOT NULL,
   order_status INT,
   total_price FLOAT,
   CONSTRAINT pk_cart PRIMARY KEY (cart_id)
);

CREATE TABLE product (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255),
   price FLOAT,
   cart_id BIGINT,
   CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product ADD CONSTRAINT FK_PRODUCT_ON_CART_ID FOREIGN KEY (cart_id) REFERENCES cart (cart_id);

CREATE TABLE productData (
  id BIGINT NOT NULL AUTO_INCREMENT,
   name VARCHAR(255),
   price FLOAT,
   PRIMARY KEY (id)
);