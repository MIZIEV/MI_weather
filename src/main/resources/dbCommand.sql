CREATE TABLE weather_data (
                              id BIGINT NOT NULL AUTO_INCREMENT,
                              PRIMARY KEY (id)
);

CREATE TABLE forecast (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          dt BIGINT,
                          feels_like DOUBLE PRECISION,
                          temp DOUBLE PRECISION,
                          weather_data_id BIGINT NOT NULL,
                          PRIMARY KEY (id),
                          FOREIGN KEY (weather_data_id) REFERENCES weather_data (id)
);

CREATE TABLE weather (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         description VARCHAR(255),
                         icon VARCHAR(255),
                         main VARCHAR(255),
                         forecast_id BIGINT NOT NULL,
                         PRIMARY KEY (id),
                         FOREIGN KEY (forecast_id) REFERENCES forecast (id)
);