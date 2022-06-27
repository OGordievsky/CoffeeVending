DROP TABLE IF EXISTS goods;
DROP TABLE IF EXISTS checks CASCADE ;
DROP TABLE IF EXISTS checklines CASCADE ;
DROP SEQUENCE IF EXISTS global_seq;
DROP SEQUENCE IF EXISTS check_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE goods
(
    id      INT8 PRIMARY KEY DEFAULT nextval('global_seq'),
    ean             INT8                         NOT NULL,
    name            VARCHAR                     NOT NULL,
    price           FLOAT                       NOT NULL
);
CREATE UNIQUE INDEX goods_unique_idx ON goods (ean);

CREATE SEQUENCE check_seq START WITH 1;
CREATE TABLE checks
(
    id          INT8 PRIMARY KEY DEFAULT nextval('global_seq'),
    check_id    INT8        DEFAULT nextval('check_seq'),
    date        DATE        DEFAULT now()       NOT NULL,
    time        TIME        DEFAULT now()       NOT NULL,
    total       FLOAT                            NOT NULL
);
CREATE UNIQUE INDEX checks_unique_idx ON checks (check_id);

CREATE TABLE checklines
(
    line_id      INT8 PRIMARY KEY DEFAULT nextval('global_seq'),
    check_id     INT8                       NOT NULL,
    goods_ean    INT                        NOT NULL,
    count        INT                        NOT NULL,
    total        FLOAT                      NOT NULL,
    FOREIGN KEY (check_id) REFERENCES checks (check_id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX checklines_unique_idx ON checks (check_id);