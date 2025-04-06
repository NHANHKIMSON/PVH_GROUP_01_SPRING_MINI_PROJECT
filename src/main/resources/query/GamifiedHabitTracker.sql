CREATE DATABASE gamified_Habit_Tracker;

DROP DATABASE gamified_Habit_Tracker;

CREATE DATABASE gamified_Habit_Tracker;

CREATE TABLE achievement(
                            achievement_id uuid PRIMARY KEY,
                            title VARCHAR(50),
                            description VARCHAR(50),
                            badge VARCHAR(50),
                            xp_required INTEGER
);

CREATE TABLE app_users(
                          app_user_id uuid PRIMARY KEY,
                          username VARCHAR(50),
                          email VARCHAR(50),
                          password VARCHAR(50),
                          level INTEGER,
                          xp INTEGER,
                          profile_image VARCHAR(50),
                          is_verified BOOLEAN,
                          created_at timestamp default current_timestamp
);

CREATE TABLE habits(
                       habit_id uuid PRIMARY KEY,
                       title VARCHAR(50),
                       description VARCHAR(50),
                       frequency VARCHAR(50),
                       is_active BOOLEAN,
                       app_user_id uuid,
                       created_at timestamp default current_timestamp,
                       FOREIGN KEY(app_user_id) REFERENCES app_users(app_user_id)
                           ON DELETE CASCADE
                           ON UPDATE CASCADE
);

CREATE TABLE habit_logs(
                           habit_log_id uuid PRIMARY KEY,
                           log_date timestamp,
                           status VARCHAR(50),
                           xp_earned INTEGER,
                           habit_id uuid,
                           FOREIGN KEY(habit_id) REFERENCES habits(habit_id)
                               ON DELETE CASCADE
                               ON UPDATE CASCADE
);

CREATE TABLE app_user_achievements(
                                      app_user_achievement_id uuid PRIMARY KEY,
                                      app_user_id uuid,
                                      achievement_id uuid,
                                      FOREIGN KEY(app_user_id) REFERENCES app_users(app_user_id)
                                          ON DELETE CASCADE
                                          ON UPDATE CASCADE,
                                      FOREIGN KEY(achievement_id) REFERENCES achievement(achievement_id)
                                          ON DELETE CASCADE
                                          ON UPDATE CASCADE
);

-- Insert 5 achievements
INSERT INTO achievement (achievement_id, title, description, badge, xp_required) VALUES
                                                                                     ('10000000-0000-4000-a000-000000000001', 'Early Bird', 'Complete a habit before 7 AM', 'early_bird_badge.png', 100),
                                                                                     ('10000000-0000-4000-a000-000000000002', 'Consistency King', 'Complete a habit for 7 consecutive days', 'consistency_badge.png', 200),
                                                                                     ('10000000-0000-4000-a000-000000000003', 'Habit Master', 'Create and maintain 5 active habits', 'master_badge.png', 500),
                                                                                     ('10000000-0000-4000-a000-000000000004', 'Perfect Week', 'Complete all habits for an entire week', 'perfect_week_badge.png', 300),
                                                                                     ('10000000-0000-4000-a000-000000000005', 'Milestone Maker', 'Reach level 10', 'milestone_badge.png', 1000);


-- Insert 5 app users
INSERT INTO app_users (app_user_id, username, email, password, level, xp, profile_image, is_verified, created_at) VALUES
                                                                                                                      ('20000000-0000-4000-a000-000000000001', 'john_doe', 'john@example.com', 'hashed_password_1', 5, 750, 'john_profile.jpg', true, '2024-01-15 08:30:00'),
                                                                                                                      ('20000000-0000-4000-a000-000000000002', 'jane_smith', 'jane@example.com', 'hashed_password_2', 8, 1200, 'jane_profile.jpg', true, '2024-01-20 10:45:00'),
                                                                                                                      ('20000000-0000-4000-a000-000000000003', 'alex_wong', 'alex@example.com', 'hashed_password_3', 3, 450, 'alex_profile.jpg', true, '2024-02-05 14:20:00'),
                                                                                                                      ('20000000-0000-4000-a000-000000000004', 'maria_garcia', 'maria@example.com', 'hashed_password_4', 7, 950, 'maria_profile.jpg', false, '2024-02-10 09:15:00'),
                                                                                                                      ('20000000-0000-4000-a000-000000000005', 'sam_wilson', 'sam@example.com', 'hashed_password_5', 4, 600, 'sam_profile.jpg', true, '2024-03-01 11:30:00');

-- Insert 5 habits
INSERT INTO habits (habit_id, title, description, frequency, is_active, app_user_id, created_at) VALUES
                                                                                                     ('30000000-0000-4000-a000-000000000001', 'Morning Workout', '30-minute exercise routine', 'DAILY', true, '20000000-0000-4000-a000-000000000001', '2024-01-16 09:00:00'),
                                                                                                     ('30000000-0000-4000-a000-000000000002', 'Read a Book', 'Read for 20 minutes', 'DAILY', true, '20000000-0000-4000-a000-000000000002', '2024-01-21 08:15:00'),
                                                                                                     ('30000000-0000-4000-a000-000000000003', 'Meditation', '10-minute mindfulness session', 'DAILY', true, '20000000-0000-4000-a000-000000000003', '2024-02-06 06:30:00'),
                                                                                                     ('30000000-0000-4000-a000-000000000004', 'Drink Water', 'Drink 8 glasses of water', 'DAILY', true, '20000000-0000-4000-a000-000000000004', '2024-02-11 07:45:00'),
                                                                                                     ('30000000-0000-4000-a000-000000000005', 'Weekly Review', 'Review weekly goals and progress', 'WEEKLY', true, '20000000-0000-4000-a000-000000000005', '2024-03-02 18:00:00');

-- Insert 5 habit logs
INSERT INTO habit_logs (habit_log_id, log_date, status, xp_earned, habit_id) VALUES
                                                                                 ('40000000-0000-4000-a000-000000000001', '2024-04-01 07:30:00', 'COMPLETED', 50, '30000000-0000-4000-a000-000000000001'),
                                                                                 ('40000000-0000-4000-a000-000000000002', '2024-04-01 19:45:00', 'COMPLETED', 40, '30000000-0000-4000-a000-000000000002'),
                                                                                 ('40000000-0000-4000-a000-000000000003', '2024-04-01 06:15:00', 'COMPLETED', 30, '30000000-0000-4000-a000-000000000003'),
                                                                                 ('40000000-0000-4000-a000-000000000004', '2024-04-01 22:00:00', 'MISSED', 0, '30000000-0000-4000-a000-000000000004'),
                                                                                 ('40000000-0000-4000-a000-000000000005', '2024-04-02 20:30:00', 'COMPLETED', 100, '30000000-0000-4000-a000-000000000005');


-- Insert 5 app user achievements
INSERT INTO app_user_achievements (app_user_achievement_id, app_user_id, achievement_id) VALUES
                                                                                             ('50000000-0000-4000-a000-000000000001', '20000000-0000-4000-a000-000000000001', '10000000-0000-4000-a000-000000000001'),
                                                                                             ('50000000-0000-4000-a000-000000000002', '20000000-0000-4000-a000-000000000002', '10000000-0000-4000-a000-000000000002'),
                                                                                             ('50000000-0000-4000-a000-000000000003', '20000000-0000-4000-a000-000000000002', '10000000-0000-4000-a000-000000000003'),
                                                                                             ('50000000-0000-4000-a000-000000000004', '20000000-0000-4000-a000-000000000003', '10000000-0000-4000-a000-000000000001'),
                                                                                             ('50000000-0000-4000-a000-000000000005', '20000000-0000-4000-a000-000000000004', '10000000-0000-4000-a000-000000000004');

-- If want to make uuid random
-- INSERT INTO achievement VALUES (gen_random_uuid(),'','','','');

-- Sample queries to check data
SELECT * FROM habits;
SELECT * FROM achievement;
SELECT * FROM app_user_achievements;
SELECT * FROM app_users;
SELECT * FROM habit_logs;
