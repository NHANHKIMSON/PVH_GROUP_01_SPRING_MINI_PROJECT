CREATE DATABASE gamified_Habit_Tracker;

-- drop database gamified_Habit_Tracker;

CREATE EXTENSION "uuid-ossp";


CREATE TABLE achievement(
                            achievement_id uuid PRIMARY KEY,
                            title VARCHAR(50),
                            description VARCHAR(50),
                            badge VARCHAR(50),
                            xp_required INTEGER
);

CREATE TABLE app_users(
                          app_user_id uuid default uuid_generate_v4() PRIMARY KEY,
                          username VARCHAR(50),
                          email VARCHAR(50),
                          password VARCHAR(50),
                          level INTEGER,
                          xp  INTEGER,
                          profile_image VARCHAR(50),
                          is_verified BOOLEAN,
                          created_at timestamp
);

CREATE TABLE habits(
                       habit_id uuid default uuid_generate_v4() PRIMARY KEY,
                       title VARCHAR(50),
                       description VARCHAR(50),
                       frequency VARCHAR(50),
                       is_active BOOLEAN,
                       app_user_id uuid,
                       created_at timestamp,
                       FOREIGN KEY(app_user_id) REFERENCES app_users(app_user_id)
);


CREATE TABLE habit_logs(
                           habit_log_id uuid PRIMARY KEY,
                           log_date timestamp,
                           status VARCHAR(50),
                           xp_earned INTEGER,
                           habit_id uuid,
                           FOREIGN KEY(habit_id) REFERENCES habits(habit_id)
);

CREATE TABLE app_user_achievements(
                                      app_user_achievement_id uuid PRIMARY KEY,
                                      app_user_id uuid,
                                      achievement_id uuid,
                                      FOREIGN KEY(app_user_id) REFERENCES app_users(app_user_id),
                                      FOREIGN KEY(achievement_id) REFERENCES achievement(achievement_id)
);

UPDATE habits SET title = 'HHH', description = 'UUU', frequency = 'Daily' WHERE habit_id = '5e9b128f-03df-46f1-9368-475bee5303e6';

DELETE FROM habits WHERE habit_id = '123883c5-dc49-4334-b798-34812c77ee26' returning *;

SELECT * FROM habits;
SELECT * FROM achievement;
SELECT * FROM app_user_achievements;
SELECT * FROM app_users;
SELECT * FROM habit_logs