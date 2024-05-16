USE HotelAppDB
GO
DELETE FROM HotelAppDB.dbo.ServiceFees
DBCC CHECKIDENT ('ServiceFees', RESEED, 0);
INSERT INTO HotelAppDB.dbo.ServiceFees
(bkng_id, service_desc, service_cost)
VALUES
(6, '1 Bouteille de Champagne', 50000),
(6, '1 Deluxe Food Service A', 20000),
(5, '1 Halal Food Service A', 4000)