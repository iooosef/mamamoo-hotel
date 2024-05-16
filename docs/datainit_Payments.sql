USE HotelAppDB
GO
DELETE FROM Payments
DBCC CHECKIDENT ('Payments', RESEED, 0);

INSERT INTO Payments
(bkng_id,
payment_code,
payment_datetime,
payment_total_rated_cost,
payment_vat,
payment_senior_pwd_discount,
payment_voucher_discount,
payment_total_service_fee,
payment_total_amount_due,
payment_total_amount_paid,
payment_change,
payment_tip,
payment_refund
)
VALUES
(4, -- bkng_id,
 (SELECT LEFT((REPLACE(
        REPLACE(
            SUBSTRING(CONVERT(VARCHAR(255), NEWID()), 3, 12) +
            SUBSTRING(CONVERT(VARCHAR(255), NEWID()), 3, 12),
        '-', ''),
    '-', '')), 12) AS RandomString), -- payment_code
 (SELECT DATEADD(HOUR, +13, GETDATE())), -- payment_datetime
 13200.00, -- payment_total_rated_cost
 1584.00, -- payment_vat
 -2640.00, -- payment_senior_pwd_discount
 -660.00, -- payment_voucher_discount
 0, -- payment_total_service_fee
 13200.00 + 1584.00 + 0 - 2640.00 - 660.00, -- payment_total_amount_due
 12000.00, -- payment_total_amount_paid
 12000.00 - (13200.00 + 1584.00 + 0 - 2640.00 - 660.00), -- payment_change
 2000, -- payment_tip,
 0
),
(1, -- bkng_id,
 (SELECT LEFT((REPLACE(
         REPLACE(
             SUBSTRING(CONVERT(VARCHAR(255), NEWID()), 3, 12) +
             SUBSTRING(CONVERT(VARCHAR(255), NEWID()), 3, 12),
         '-', ''),
     '-', '')), 12) AS RandomString), -- payment_code
 (SELECT DATEADD(HOUR, +2, GETDATE())), -- payment_datetime
 8400.00, -- payment_total_rated_cost
 1008.00, -- payment_vat
 0.00, -- payment_senior_pwd_discount
 0.00, -- payment_voucher_discount
 0, -- payment_total_service_fee
 8400.00 + 1008.00 - 0.00 - 0.00, -- payment_total_amount_due
 10000.00, -- payment_total_amount_paid
 10000.00 - (8400.00 + 1008.00 + 0 - 0.00 - 0.00), -- payment_change
 0, -- payment_tip
 0
),
(2, -- bkng_id,
 (SELECT LEFT((REPLACE(
         REPLACE(
             SUBSTRING(CONVERT(VARCHAR(255), NEWID()), 3, 12) +
             SUBSTRING(CONVERT(VARCHAR(255), NEWID()), 3, 12),
         '-', ''),
     '-', '')), 12) AS RandomString), -- payment_code
 (SELECT DATEADD(HOUR, +3, GETDATE())), -- payment_datetime
 16000.00, -- payment_total_rated_cost
 1920.00, -- payment_vat
 -3200.00, -- payment_senior_pwd_discount
 0.00, -- payment_voucher_discount
 0, -- payment_total_service_fee
 16000.00 + 1920.00 - 0.00 - 0.00, -- payment_total_amount_due
 20000.00, -- payment_total_amount_paid
 20000.00 - (16000.00 + 1920.00 + 0 - 3200.00 - 0.00), -- payment_change
 1800, -- payment_tip
 0
),
(6, -- bkng_id,
 (SELECT LEFT((REPLACE(
         REPLACE(
             SUBSTRING(CONVERT(VARCHAR(255), NEWID()), 3, 12) +
             SUBSTRING(CONVERT(VARCHAR(255), NEWID()), 3, 12),
         '-', ''),
     '-', '')), 12) AS RandomString), -- payment_code
 (SELECT DATEADD(HOUR, +4, GETDATE())), -- payment_datetime
 16000.00, -- payment_total_rated_cost
 1920.00, -- payment_vat
 0.00, -- payment_senior_pwd_discount
 -800.00, -- payment_voucher_discount
 0.00, -- payment_total_service_fee
 16000.00 + 1920.00 + 0.00 - 0.00 - 800.00, -- payment_total_amount_due
 18000.00, -- payment_total_amount_paid
 18000.00 - (16000.00 + 1920.00 + 0.00 - 0.00 - 800.00), -- payment_change
 5000, -- payment_tip
 0
),
(7, -- bkng_id,
 (SELECT LEFT((REPLACE(
         REPLACE(
             SUBSTRING(CONVERT(VARCHAR(255), NEWID()), 3, 12) +
             SUBSTRING(CONVERT(VARCHAR(255), NEWID()), 3, 12),
         '-', ''),
     '-', '')), 12) AS RandomString), -- payment_code
 (SELECT DATEADD(HOUR, +6, GETDATE())), -- payment_datetime
 2200.00, -- payment_total_rated_cost
 264.00, -- payment_vat
 -440.00, -- payment_senior_pwd_discount
 -110.00, -- payment_voucher_discount
 0.00, -- payment_total_service_fee
 2200.00 + 264.00 + 0.00 - 440.00 - 110.00, -- payment_total_amount_due
 2000.00, -- payment_total_amount_paid
 2000.00 - (2200.00 + 264.00 + 0.00 - 440.00 - 110.00), -- payment_change
 0, -- payment_tip
 2000
)