-- Insert a value into the sport_center table
INSERT INTO sport_center (id, name, max_capacity, opening_time, closing_time)
VALUES (1, 'GitFit', 100, '06:00:00', '22:00:00');

-- Insert a value into the owner table
INSERT INTO owner (id, username, email, password, last_name, first_name, sport_center_id)
VALUES (1, 'admin', 'admin@gitfit.com', 'securepassword', 'Doe', 'John', 1);

-- Insert values into the instructor table
INSERT INTO instructor (id, username, email, password, last_name, first_name, sport_center_id)
VALUES 
(1, 'adam_smith', 'adam.smith@gitfit.com', 'AdamRocks2022', 'Smith', 'Adam', 1),
(2, 'bella_johnson', 'bella.johnson@gitfit.com', 'Bella2022', 'Johnson', 'Bella', 1),
(3, 'charlie_kent', 'charlie.kent@gitfit.com', 'Charlie2022', 'Kent', 'Charlie', 1),
(4, 'diana_murphy', 'diana.murphy@gitfit.com', 'Diana2022', 'Murphy', 'Diana', 1),
(5, 'edward_jones', 'edward.jones@gitfit.com', 'Edward2022', 'Jones', 'Edward', 1),
(6, 'fiona_white', 'fiona.white@gitfit.com', 'Fiona2022', 'White', 'Fiona', 1),
(7, 'george_harris', 'george.harris@gitfit.com', 'George2022', 'Harris', 'George', 1),
(8, 'hannah_baker', 'hannah.baker@gitfit.com', 'Hannah2022', 'Baker', 'Hannah', 1),
(9, 'ian_davis', 'ian.davis@gitfit.com', 'Ian2022', 'Davis', 'Ian', 1),
(10, 'jessica_taylor', 'jessica.taylor@gitfit.com', 'Jessica2022', 'Taylor', 'Jessica', 1);

-- Insert values into the customer table
INSERT INTO customer (id, username, email, password, last_name, first_name, sport_center_id)
VALUES 
(1, 'john_doe', 'john.doe@gitfit.com', 'John2022', 'Doe', 'John', 1),
(2, 'jane_smith', 'jane.smith@gitfit.com', 'Jane2022', 'Smith', 'Jane', 1),
(3, 'bob_johnson', 'bob.johnson@gitfit.com', 'Bob2022', 'Johnson', 'Bob', 1),
(4, 'alice_williams', 'alice.williams@gitfit.com', 'Alice2022', 'Williams', 'Alice', 1),
(5, 'charlie_brown', 'charlie.brown@gitfit.com', 'Charlie2022', 'Brown', 'Charlie', 1),
(6, 'diana_davis', 'diana.davis@gitfit.com', 'Diana2022', 'Davis', 'Diana', 1),
(7, 'edward_miller', 'edward.miller@gitfit.com', 'Edward2022', 'Miller', 'Edward', 1),
(8, 'fiona_wilson', 'fiona.wilson@gitfit.com', 'Fiona2022', 'Wilson', 'Fiona', 1),
(9, 'george_moore', 'george.moore@gitfit.com', 'George2022', 'Moore', 'George', 1),
(10, 'hannah_taylor', 'hannah.taylor@gitfit.com', 'Hannah2022', 'Taylor', 'Hannah', 1);

-- Insert values into the billing table
INSERT INTO billing (id, country, state, postal_code, card_number, address, customer_id)
VALUES 
(1, 'USA', 'California', '90001', '1234567812345678', '123 Main St', 1),
(2, 'USA', 'New York', '10001', '2345678923456789', '456 Broadway', 2),
(3, 'USA', 'Texas', '73301', '3456789034567890', '789 South St', 3),
(4, 'USA', 'Florida', '33101', '4567890145678901', '012 North St', 4),
(5, 'USA', 'Illinois', '60007', '5678901256789012', '345 East St', 5),
(6, 'USA', 'Pennsylvania', '19019', '6789012367890123', '678 West St', 6),
(7, 'USA', 'Ohio', '43085', '7890123478901234', '901 Central St', 7),
(8, 'USA', 'Georgia', '30301', '8901234589012345', '234 Park St', 8),
(9, 'USA', 'North Carolina', '27513', '9012345690123456', '567 Hill St', 9),
(10, 'USA', 'Michigan', '48038', '0123456701234567', '890 Valley St', 10);

-- Insert values into the fitness_class table
INSERT INTO fitness_class (id, name, description, approval_status, sport_center_id)
VALUES 
(1, 'Yoga Beginners', 'A class for beginners to learn basic yoga poses', 0, 1),
(2, 'Advanced Yoga', 'A class for experienced yoga practitioners', 1, 1),
(3, 'Pilates', 'A class to strengthen your core with pilates', 2, 1),
(4, 'Zumba', 'A fun, high-energy dance workout', 0, 1),
(5, 'Kickboxing', 'A class combining boxing and martial arts', 1, 1),
(6, 'Spin Class', 'A high-intensity cycling workout', 2, 1),
(7, 'HIIT', 'High Intensity Interval Training for maximum fat burn', 0, 1),
(8, 'Boot Camp', 'A military-style physical training class', 1, 1),
(9, 'Aerobics', 'A class to improve all elements of fitness', 2, 1),
(10, 'Tai Chi', 'A class for gentle and calming martial arts', 0, 1),
(11, 'Barre', 'A low-impact workout combining ballet, Pilates, and yoga', 0, 1),
(12, 'CrossFit', 'A high-intensity fitness program incorporating various exercises', 0, 1),
(13, 'Pole Dancing', 'A fun and challenging workout using a vertical pole', 0, 1),
(14, 'TRX Suspension Training', 'A total-body workout using suspension straps', 0, 1),
(15, 'Bodyweight Training', 'A strength training workout using only your body weight', 0, 1),
(16, 'Parkour', 'An urban sport involving obstacle course running', 0, 1),
(17, 'Capoeira', 'A Brazilian martial art combining dance and acrobatics', 0, 1),
(18, 'Circuit Training', 'A series of exercises targeting different muscle groups', 0, 1),
(19, 'Hula Hoop Fitness', 'A fun way to exercise using hula hoops', 0, 1),
(20, 'Salsa Fitness', 'A dance fitness class incorporating salsa moves', 0, 1);

-- Insert values into the session table
-- Insert values into the session table
INSERT INTO session (id, price, start_time, end_time, date, instructor_id, fitness_class_id, sport_center_id)
VALUES 
(1, 50, '10:00:00', '11:00:00', '2024-06-01', 1, 1, 1),
(2, 60, '12:00:00', '13:00:00', '2024-06-02', 2, 14, 1),
(3, 70, '14:00:00', '15:00:00', '2024-06-03', 3, 7, 1),
(4, 80, '16:00:00', '17:00:00', '2024-06-04', 4, 10, 1),
(5, 90, '18:00:00', '19:00:00', '2024-06-05', 5, 11, 1),
(6, 100, '20:00:00', '21:00:00', '2024-06-06', 6, 10, 1),
(7, 110, '10:00:00', '11:00:00', '2024-06-07', 7, 16, 1),
(8, 120, '12:00:00', '13:00:00', '2024-06-08', 8, 19, 1),
(9, 130, '14:00:00', '15:00:00', '2024-06-09', 9, 15, 1),
(10, 140, '16:00:00', '17:00:00', '2024-06-10', 10, 12, 1),
(11, 120, '09:30:00', '10:30:00', '2024-06-01', 3, 14, 1),
(12, 130, '10:30:00', '11:30:00', '2024-06-02', 8, 17, 1),
(13, 110, '11:30:00', '12:30:00', '2024-06-03', 2, 18, 1),
(14, 140, '12:30:00', '13:30:00', '2024-06-04', 7, 4, 1),
(15, 100, '13:30:00', '14:30:00', '2024-06-05', 4, 16, 1),
(16, 120, '14:30:00', '15:30:00', '2024-06-06', 9, 20, 1),
(17, 130, '15:30:00', '16:30:00', '2024-06-07', 1, 12, 1),
(18, 110, '16:30:00', '17:30:00', '2024-06-08', 6, 14, 1),
(19, 100, '17:30:00', '18:30:00', '2024-06-09', 5, 7, 1),
(20, 140, '18:30:00', '19:30:00', '2024-06-10', 10, 4, 1),
(21, 130, '09:00:00', '10:00:00', '2024-06-11', 1, 19, 1),
(22, 120, '10:00:00', '11:00:00', '2024-06-12', 8, 12, 1),
(23, 100, '11:00:00', '12:00:00', '2024-06-13', 2, 7, 1),
(24, 140, '12:00:00', '13:00:00', '2024-06-14', 3, 17, 1),
(25, 120, '13:00:00', '14:00:00', '2024-06-01', 7, 16, 1),
(26, 130, '14:00:00', '15:00:00', '2024-06-02', 9, 4, 1),
(27, 110, '15:00:00', '16:00:00', '2024-06-03', 10, 17, 1),
(28, 100, '16:00:00', '17:00:00', '2024-06-04', 4, 11, 1),
(29, 140, '17:00:00', '18:00:00', '2024-06-05', 6, 10, 1),
(30, 120, '18:00:00', '19:00:00', '2024-06-06', 5, 11, 1),
(31, 130, '09:30:00', '10:30:00', '2024-06-07', 10, 15, 1),
(32, 100, '10:30:00', '11:30:00', '2024-06-08', 1, 7, 1),
(33, 120, '11:30:00', '12:30:00', '2024-06-09', 8, 14, 1),
(34, 130, '12:30:00', '13:30:00', '2024-06-10', 2, 1, 1),
(35, 110, '13:30:00', '14:30:00', '2024-06-11', 7, 3, 1),
(36, 100, '14:30:00', '15:30:00', '2024-06-12', 9, 16, 1),
(37, 140, '15:30:00', '16:30:00', '2024-06-13', 10, 10, 1),
(38, 130, '16:30:00', '17:30:00', '2024-06-14', 4, 20, 1),
(39, 100, '17:30:00', '18:30:00', '2024-06-01', 6, 15, 1),
(40, 140, '18:30:00', '19:30:00', '2024-06-02', 5, 18, 1),
(41, 120, '09:00:00', '10:00:00', '2024-06-03', 10, 14, 1),
(42, 130, '10:00:00', '11:00:00', '2024-06-04', 1, 7, 1),
(43, 100, '11:00:00', '12:00:00', '2024-06-05', 8, 4, 1),
(44, 140, '12:00:00', '13:00:00', '2024-06-06', 2, 15, 1),
(45, 130, '13:00:00', '14:00:00', '2024-06-07', 7, 7, 1),
(46, 100, '14:00:00', '15:00:00', '2024-06-08', 9, 1, 1),
(47, 120, '15:00:00', '16:00:00', '2024-06-09', 10, 17, 1),
(48, 130, '16:00:00', '17:00:00', '2024-06-10', 4, 12, 1),
(49, 100, '17:00:00', '18:00:00', '2024-06-11', 6, 20, 1),
(50, 110, '18:00:00', '19:00:00', '2024-06-12', 5, 16, 1);


-- Insert values into the registration table
INSERT INTO registration (id, date, session_id, customer_id, sport_center_id)
VALUES
(1, '2024-01-15', 10, 1, 1),
(2, '2024-02-02', 25, 2, 1),
(3, '2024-03-20', 38, 3, 1),
(4, '2024-04-05', 12, 4, 1),
(5, '2024-05-10', 48, 5, 1),
(6, '2024-01-08', 20, 6, 1),
(7, '2024-02-14', 35, 7, 1),
(8, '2024-03-30', 45, 8, 1),
(9, '2024-04-25', 2, 9, 1),
(10, '2024-05-31', 30, 10, 1),
(11, '2024-01-23', 16, 1, 1),
(12, '2024-02-09', 28, 2, 1),
(13, '2024-03-17', 42, 3, 1),
(14, '2024-04-10', 8, 4, 1),
(15, '2024-05-15', 19, 5, 1),
(16, '2024-01-05', 44, 6, 1),
(17, '2024-02-20', 33, 7, 1),
(18, '2024-03-05', 27, 8, 1),
(19, '2024-04-15', 49, 9, 1),
(20, '2024-05-20', 14, 10, 1),
(21, '2024-01-18', 5, 2, 1),
(22, '2024-02-28', 24, 3, 1),
(23, '2024-03-10', 39, 4, 1),
(24, '2024-04-20', 4, 5, 1),
(25, '2024-05-25', 21, 6, 1),
(26, '2024-01-30', 15, 7, 1),
(27, '2024-02-06', 40, 8, 1),
(28, '2024-03-15', 36, 9, 1),
(29, '2024-04-30', 11, 10, 1),
(30, '2024-05-05', 3, 1, 1);

