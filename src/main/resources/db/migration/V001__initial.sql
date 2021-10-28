CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE parent
(
    id   UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name CHARACTER VARYING NOT NULL
);

CREATE TABLE child
(
    id        UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name      CHARACTER VARYING NOT NULL,
    parent_id UUID REFERENCES parent (id)
)