CREATE TABLE IF NOT EXISTS PRODUCT (
   ID BIGINT AUTO_INCREMENT,
   PRODUCT_NAME VARCHAR(200) NOT NULL,
   CURRENCY VARCHAR(3) NOT NULL,
   PRICE DECIMAL(19,6) NOT NULL,
   PRICE_EUR DECIMAL(19,6)  NOT NULL,
   CREATION_DATE TIMESTAMP NOT NULL,
   UPDATE_DATE TIMESTAMP NOT NULL
);
---