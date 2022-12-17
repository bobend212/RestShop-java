DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS cart;

CREATE TABLE cart (
  id_cart BIGINT AUTO_INCREMENT NOT NULL,
   order_status INT,
   total_price FLOAT,
   CONSTRAINT pk_cart PRIMARY KEY (id_cart)
);

CREATE TABLE product (
  id_product BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255),
   price FLOAT,
   id_cart BIGINT,
   CONSTRAINT pk_product PRIMARY KEY (id_product)
);

ALTER TABLE product ADD CONSTRAINT FK_PRODUCT_ON_ID_CART FOREIGN KEY (id_cart) REFERENCES cart (id_cart);

INSERT INTO product(name, price) VALUES('Milk', 2);
INSERT INTO product(name, price) VALUES('Butter', 3);
INSERT INTO product(name, price) VALUES('Water', 1);