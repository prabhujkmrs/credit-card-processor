CREATE TABLE IF NOT EXISTS card (
    card_number NUMERIC IDENTITY PRIMARY KEY,
    name CHARACTER (50),
    balance DECIMAL,
    limit NUMERIC
);
