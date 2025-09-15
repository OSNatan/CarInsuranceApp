INSERT INTO owner (name, email) VALUES ('Ana Pop', 'ana.pop@example.com');
INSERT INTO owner (name, email) VALUES ('Bogdan Ionescu', 'bogdan.ionescu@example.com');
INSERT INTO owner (name, email) VALUES ('Alin Marcu', 'alin.marcu@example.com');
INSERT INTO owner (name, email) VALUES ('Maria Ion', 'maria.ion@example.com');
INSERT INTO owner (name, email) VALUES ('Ciprian Ciobanu', 'ciprian.ciobanu@example.com');

INSERT INTO car (vin, make, model, year_of_manufacture, owner_id) VALUES ('VIN12345', 'Dacia', 'Logan', 2018, 1);
INSERT INTO car (vin, make, model, year_of_manufacture, owner_id) VALUES ('VIN67890', 'VW', 'Golf', 2021, 2);
INSERT INTO car (vin, make, model, year_of_manufacture, owner_id) VALUES ('VIN24445', 'VW', 'Passat', 2021, 2);
INSERT INTO car (vin, make, model, year_of_manufacture, owner_id) VALUES ('VIN21437', 'Audi', 'A4', 2021, 2);
INSERT INTO car (vin, make, model, year_of_manufacture, owner_id) VALUES ('VIN97824', 'Dacia', 'Sandero', 2021, 2);


INSERT INTO insurancepolicy (car_id, provider, start_date, end_date) VALUES ( 1, 'Allianz', DATE '2024-01-01', DATE '2024-12-31');
INSERT INTO insurancepolicy (car_id, provider, start_date, end_date) VALUES ( 1, 'Groupama', DATE '2025-01-01', DATE '2025-03-01');
INSERT INTO insurancepolicy (car_id, provider, start_date, end_date) VALUES ( 2, 'Allianz', DATE '2025-03-01', DATE '2025-09-30');
