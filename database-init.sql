
-- t_stock definition
DROP TABLE IF EXISTS t_stock;
CREATE TABLE t_stock (
	id VARCHAR(32) NOT NULL,
	symbol VARCHAR(128) NOT NULL,
	expected_return DOUBLE NOT NULL,
	volatility DOUBLE NOT NULL,
	initial_price DOUBLE NOT NULL,
	CONSTRAINT t_stock_pk PRIMARY KEY (id)
);
CREATE INDEX t_stock_symbol_idx
ON t_stock (symbol);

-- t_option definition
DROP TABLE IF EXISTS t_option;
CREATE TABLE t_option (
	id VARCHAR(32) NOT NULL,
    symbol VARCHAR(128) NOT NULL,
	stock_symbol VARCHAR(128) NOT NULL,
	option_type VARCHAR(16) NOT NULL,
	volatility DOUBLE NOT NULL,
	strike_price DOUBLE NOT NULL,
	days_to_maturity SMALLINT NOT NULL,
	CONSTRAINT t_option_pk PRIMARY KEY (id)
);
CREATE INDEX t_option_symbol_idx
ON t_option (symbol);
CREATE INDEX t_option_stock_symbol_idx
ON t_option (stock_symbol);

INSERT INTO t_stock
(id, symbol, expected_return, volatility, initial_price)
VALUES('1', 'AAPL', 0.15, 0.3, 220.0);
INSERT INTO t_stock
(id, symbol, expected_return, volatility, initial_price)
VALUES('2', 'TESLA', 0.2, 0.5, 210.0);
INSERT INTO t_option
(id, symbol, stock_symbol, option_type, volatility, strike_price, days_to_maturity)
VALUES('1', 'AAPL-AUG-2024-225-C', 'AAPL', 'CALL', 0.3, 225, 10);
INSERT INTO t_option
(id, symbol, stock_symbol, option_type, volatility, strike_price, days_to_maturity)
VALUES('2', 'AAPL-AUG-2024-225-P', 'AAPL', 'PUT', 0.3, 225, 10);
INSERT INTO t_option
(id, symbol, stock_symbol, option_type, volatility, strike_price, days_to_maturity)
VALUES('3', 'TESLA-SEP-2024-215-C', 'TESLA', 'CALL', 0.5, 215, 17);
INSERT INTO t_option
(id, symbol, stock_symbol, option_type, volatility, strike_price, days_to_maturity)
VALUES('4', 'TESLA-SEP-2024-215-P', 'TESLA', 'PUT', 0.5, 215, 17);