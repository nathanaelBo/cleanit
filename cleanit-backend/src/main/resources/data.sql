insert into textile_types (name) values ('Hemd'), ('Hose'), ('Kleid'), ('Anzug'), ('Sonstiges');
insert into cleaning_types (name) values ('Standard'), ('Spezial'), ('Express');
insert into customers (first_name, last_name) values ('Franz', 'Huber'), ('Susanne', 'Mayer'), ('Petra', 'Huber');

insert into orders (customer_id, textile_type_id, cleaning_type_id, notes, order_state) values
(1, 1, 2, 'Fleck am linken Ärmel', 'RECEIVED'),
(1, 4, 1, 'Keine Flecken', 'RECEIVED'),
(3, 3, 1, 'Besonders schonend reinigen', 'RETURNED'),
(2, 2, 3, 'Soll bis morgen fertig sein', 'RECEIVED'),
(1, 2, 1, 'Loch im linken Hosenbein bereits vorhanden', 'CLEANED');