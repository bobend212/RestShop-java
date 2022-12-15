DROP TABLE IF EXISTS cart;

CREATE TABLE cart (
  cart_id int NOT NULL AUTO_INCREMENT,
  status varchar(50),
  totalPrice int,
  PRIMARY KEY (cart_id)
);

insert into cart(cart_id, status, totalPrice) values (1, 'PENDING', 240);