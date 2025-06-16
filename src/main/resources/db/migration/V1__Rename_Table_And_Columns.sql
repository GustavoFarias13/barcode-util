-- Rename table from prefixes to prefix
CREATE TABLE prefix AS
SELECT 
    id,
    prefix AS code,
    country
FROM prefixes;

-- Drop the old table
DROP TABLE prefixes;