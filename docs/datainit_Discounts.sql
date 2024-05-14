USE HotelAppDB
GO
INSERT INTO Discounts
(dscnt_code, dscnt_amount, dscnt_count, dscnt_expiry, dscnt_dti_permit)
VALUES
('I0IOTA', 500, 30, (SELECT DATEADD(MONTH, 3, GETDATE())), '2024-0000001'),
('101KMS', 1000, 7, (SELECT DATEADD(MONTH, 1, GETDATE())), '2024-0000002')