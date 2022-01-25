use dbtest;

select * from countries order by id;
select * from cities order by id;
select id, user_login, user_password, city_id from users where id = 1;

SELECT
    cities.id as city_id, cities.city_name as city_name, cities.country_id as city_country_id
FROM cities
         INNER JOIN countries ON countries.id=cities.country_id where country_id = 2;

SELECT
    users.id as user_id, users.user_login as user_login, users.user_password as user_password, users.city_id as city_id
FROM users
        INNER JOIN cities on users.city_id = cities.id where city_id = 1;