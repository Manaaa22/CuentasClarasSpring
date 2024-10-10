SET FOREIGN_KEY_CHECKS=0;
DELETE FROM usuarios

INSERT INTO usuarios (usuario, nombre, contrasenia, email, foto) VALUES
('link', 'Link', 'password123', 'link@example.com', 'foto1.jpg'),
('miku', 'Micaela', 'password456', 'miku@example.com', 'foto2.jpg');

SET FOREIGN_KEY_CHECKS=1;