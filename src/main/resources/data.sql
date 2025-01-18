INSERT INTO owner (id, name)
SELECT 1,
    'John Doe'
FROM DUAL
WHERE NOT EXISTS (
        SELECT 1
        FROM owner
        WHERE name = 'John Doe'
    );
INSERT INTO owner (id, name)
SELECT 2,
    'Jane Smith'
FROM DUAL
WHERE NOT EXISTS (
        SELECT 1
        FROM owner
        WHERE name = 'Jane Smith'
    );
INSERT INTO club (id, name, owner_id)
SELECT 1,
    'Elite Club',
    (
        SELECT id
        FROM owner
        WHERE name = 'John Doe'
    )
FROM DUAL
WHERE NOT EXISTS (
        SELECT 1
        FROM club
        WHERE name = 'Elite Club'
            AND owner_id = (
                SELECT id
                FROM owner
                WHERE name = 'John Doe'
            )
    );
INSERT INTO club (id, name, owner_id)
SELECT 2,
    'Fitness Hub',
    (
        SELECT id
        FROM owner
        WHERE name = 'Jane Smith'
    )
FROM DUAL
WHERE NOT EXISTS (
        SELECT 1
        FROM club
        WHERE name = 'Fitness Hub'
            AND owner_id = (
                SELECT id
                FROM owner
                WHERE name = 'Jane Smith'
            )
    );
INSERT INTO club (id, name, owner_id)
SELECT 3,
    'Fitness Elite club',
    (
        SELECT id
        FROM owner
        WHERE name = 'Jane Smith'
    )
FROM DUAL
WHERE NOT EXISTS (
        SELECT 1
        FROM club
        WHERE name = 'Fitness Elite club'
            AND owner_id = (
                SELECT id
                FROM owner
                WHERE name = 'Jane Smith'
            )
    );
INSERT INTO member (id, name)
SELECT 1,
    'John'
WHERE NOT EXISTS (
        SELECT 1
        FROM member
        WHERE name = 'John'
    )
UNION ALL
SELECT 2,
    'Jack'
WHERE NOT EXISTS (
        SELECT 1
        FROM member
        WHERE name = 'Jack'
    )
UNION ALL
SELECT 3,
    'Alice Johnson'
WHERE NOT EXISTS (
        SELECT 1
        FROM member
        WHERE name = 'Alice Johnson'
    )
UNION ALL
SELECT 4,
    'Bob Williams'
WHERE NOT EXISTS (
        SELECT 1
        FROM member
        WHERE name = 'Bob Williams'
    );