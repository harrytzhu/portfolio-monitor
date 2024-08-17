
-- t_stock definition
DROP TABLE t_stock;
CREATE TABLE t_stock (
	id TEXT NOT NULL,
	symbol TEXT NOT NULL,
	expected_return NUMERIC NOT NULL,
	volatility NUMERIC NOT NULL,
	initial_price NUMERIC NOT NULL,
	CONSTRAINT t_security_pk PRIMARY KEY (id)
);

-- t_option definition
DROP TABLE t_option;
CREATE TABLE t_option (
	id TEXT NOT NULL,
	symbol TEXT NOT NULL,
	stock_symbol TEXT NOT NULL,
	option_type TEXT NOT NULL,
	volatility NUMERIC NOT NULL,
	strike_price NUMERIC NOT NULL,
	days_to_maturity INTEGER NOT NULL,
	CONSTRAINT t_security_pk PRIMARY KEY (id)
);

INSERT INTO t_stock
(id, symbol, expected_return, volatility, initial_price)
VALUES('1', 'AAPL', 0.15, 0.3, 220.0);
INSERT INTO t_stock
(id, symbol, expected_return, volatility, initial_price)
VALUES('2', 'TELSA', 0.2, 0.5, 210.0);
INSERT INTO t_option
(id, symbol, stock_symbol, option_type, volatility, strike_price, days_to_maturity)
VALUES('3', 'AAPL-AUG-2024-225-C', 'AAPL', 'CALL', 0.3, 225, 10);
INSERT INTO t_option
(id, symbol, stock_symbol, option_type, volatility, strike_price, days_to_maturity)
VALUES('4', 'AAPL-AUG-2024-225-P', 'AAPL', 'PUT', 0.3, 225, 10);
INSERT INTO t_option
(id, symbol, stock_symbol, option_type, volatility, strike_price, days_to_maturity)
VALUES('5', 'TESLA-SEP-2024-225-C', 'AAPL', 'CALL', 0.5, 215, 17);
INSERT INTO t_option
(id, symbol, stock_symbol, option_type, volatility, strike_price, days_to_maturity)
VALUES('6', 'AAPL-SEP-2024-225-P', 'AAPL', 'PUT', 0.5, 215, 17);