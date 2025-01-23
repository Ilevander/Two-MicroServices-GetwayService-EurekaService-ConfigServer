CREATE TABLE Commande (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          description VARCHAR(255) NOT NULL,
                          quantite INT NOT NULL,
                          date DATE NOT NULL,
                          montant DOUBLE NOT NULL
);

-- Insert 20 rows of sample data into the Commande table with explicit IDs
INSERT INTO Commande (id, description, quantite, date, montant) VALUES
                                                                    (1, 'Produit A', 10, '2025-01-01', 100.50),
                                                                    (2, 'Produit B', 5, '2025-01-02', 200.75),
                                                                    (3, 'Produit C', 20, '2025-01-03', 300.00),
                                                                    (4, 'Produit D', 15, '2025-01-04', 150.25),
                                                                    (5, 'Produit E', 8, '2025-01-05', 80.00),
                                                                    (6, 'Produit F', 12, '2025-01-06', 120.00),
                                                                    (7, 'Produit G', 6, '2025-01-07', 60.50),
                                                                    (8, 'Produit H', 18, '2025-01-08', 180.75),
                                                                    (9, 'Produit I', 10, '2025-01-09', 100.00),
                                                                    (10, 'Produit J', 7, '2025-01-10', 70.00),
                                                                    (11, 'Produit K', 9, '2025-01-11', 90.50),
                                                                    (12, 'Produit L', 14, '2025-01-12', 140.75),
                                                                    (13, 'Produit M', 11, '2025-01-13', 110.00),
                                                                    (14, 'Produit N', 4, '2025-01-14', 40.25),
                                                                    (15, 'Produit O', 3, '2025-01-15', 30.00),
                                                                    (16, 'Produit P', 16, '2025-01-16', 160.00),
                                                                    (17, 'Produit Q', 2, '2025-01-17', 20.50),
                                                                    (18, 'Produit R', 13, '2025-01-18', 130.75),
                                                                    (19, 'Produit S', 1, '2025-01-19', 10.00),
                                                                    (20, 'Produit T', 19, '2025-01-20', 190.00);
