create database if not exists weather_data;
use weather_data;
create table measurements
(
    id             int primary key,
    city_name      varchar(50),
    `date`         varchar(50),
    `time`         varchar(50),
    temperature    int,
    wind_speed     decimal,
    wind_direction int,
    humidity       int
)