USE todokiss;
INSERT INTO status(status_id, status) VALUES(null, 'Concluido');
INSERT INTO status(status_id, status) VALUES(null, 'Pendente');
INSERT INTO status(status_id, status) VALUES(null, 'Indefinido');
INSERT INTO categories(category_id, category) VALUES(null, "Geral")
INSERT INTO tasks (id, description, sub_description, statusId, max_date, categoryId)Values(null, "inicial", "inicial", 1, "2022-02-02", 1);

