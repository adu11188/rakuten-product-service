CREATE SCHEMA product_categories_schema;

/**
 * Currency table
 */
CREATE TABLE product_categories_schema.currency(currency_id INT auto_increment,currency_name VARCHAR,currency_code VARCHAR(10),currency_symbol VARCHAR(20),PRIMARY KEY (currency_id));
/**
 * Product table
 */
CREATE TABLE product_categories_schema.product(product_id INT auto_increment,product_name VARCHAR,brand VARCHAR,description VARCHAR,sale_price NUMERIC,currency_id INT,available BOOLEAN,gender VARCHAR,color VARCHAR,PRIMARY KEY (product_id),FOREIGN KEY (currency_id) REFERENCES product_categories_schema.currency);

/**
 * Category table
 */
CREATE TABLE product_categories_schema.category(category_id INT auto_increment, category_name VARCHAR, PRIMARY KEY (category_id));

/**
 * Category-Group table
 */
CREATE TABLE product_categories_schema.category_group(category_group_id INT auto_increment,category_group_name VARCHAR, PRIMARY KEY (category_group_id));
/*
 * Mapping tables
 * 
 * */
/**
 * 
 */
CREATE TABLE product_categories_schema.product_category_group_mapping(id INT auto_increment,product_id INT ,category_group_id INT,FOREIGN KEY (product_id) REFERENCES product_categories_schema.product(product_id),FOREIGN KEY (category_group_id) REFERENCES product_categories_schema.category_group(category_group_id),PRIMARY KEY (id)  );
 



CREATE TABLE product_categories_schema.category_group_mapping(category_group_id INT, category_id INT, sequence_no INT, FOREIGN KEY (category_id) REFERENCES product_categories_schema.category(category_id),FOREIGN KEY (category_group_id) REFERENCES product_categories_schema.category_group(category_group_id), PRIMARY KEY (category_group_id,category_id,sequence_no) );

