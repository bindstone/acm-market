CREATE TABLE IF NOT EXISTS EXCHANGE_RATE(
    ID            BIGINT AUTO_INCREMENT,
    ISO_CCY       VARCHAR(3) UNIQUE NOT NULL,
    EXCHANGE_RATE DECIMAL(19, 6) NOT NULL,
    CREATION_DATE TIMESTAMP NOT NULL,
    UPDATE_DATE TIMESTAMP NOT NULL
);
---