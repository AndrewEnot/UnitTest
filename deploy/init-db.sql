-- Создание схемы
CREATE SCHEMA IF NOT EXISTS my_report;

-- Создание последовательности
CREATE SEQUENCE IF NOT EXISTS my_report.my_report_id_seq;

-- Создание таблицы
CREATE TABLE IF NOT EXISTS my_report.units
(
    unit_name_short text NOT NULL,
    unit_name_full  text NOT NULL,
    primary key (unit_name_short)
);

-- Создание таблицы
CREATE TABLE IF NOT EXISTS my_report.currencies
(
    currency_name_short text NOT NULL,
    currency_name_full  text NOT NULL,
    primary key (currency_name_short)
);

CREATE TABLE IF NOT EXISTS my_report.brands
(
    brand_name text NOT NULL,
    company_owner  text NOT NULL,
    rate BIGINT,
    year_creation BIGINT,
    primary key (brand_name)
);

-- Создание таблицы
CREATE TABLE IF NOT EXISTS my_report.basic
(
    id         integer NOT NULL DEFAULT nextval('my_report.my_report_id_seq'),
    brand_name text,
    product    text,
    unit       text,
    quantity   float,
    price      float,
    currency   text,
    primary key (id),
    foreign key (brand_name) references my_report.brands (brand_name),
    foreign key (unit) references my_report.units (unit_name_short),
    foreign key (currency) references my_report.currencies (currency_name_short)
);
