USE HotelAppDB
GO
DELETE FROM Bookings
DBCC CHECKIDENT ('Bookings', RESEED, 0);
INSERT INTO Bookings
(room_id,
 booking_datetime,
 check_in_datetime,
 check_out_datetime,
 guest_given_name,
 guest_last_name,
 email,
 contact_num,
 bkng_fee,
 senior_pwd,
 voucher,
 status
 )
 VALUES
 (1, GETDATE(), (SELECT DATEADD(DAY, 4, GETDATE())), (SELECT DATEADD(DAY, 5, GETDATE())),
  'Bashar', 'Al-Assad', '', '+000000000001', 200,  '', '', 'reserved_paid'),
 (10, GETDATE(), (SELECT DATEADD(DAY, 5, GETDATE())), (SELECT DATEADD(DAY, 6, GETDATE())),
  'Osama', 'Bin Laden', '911@alqaeda.com', '+000000000911', 1200, '091234567812', '', 'reserved_paid'),
 (1, GETDATE(), (SELECT DATEADD(HOUR, -7, GETDATE())), null,
  'Muammar', 'Gadaffi', 'maddog@hotmail.com', '+000000000420', 200, '00012314124', 'AJ23DA2', 'reserved'),
 (2, GETDATE(), (SELECT DATEADD(DAY, 4, GETDATE())), (SELECT DATEADD(DAY, 5, GETDATE())),
  'Ruholla', 'Khomeini', 'iran@iranmail.gov', '+000000000033', 200,  '154013123', '1231241', 'paid'),
 (9, GETDATE(),  (SELECT DATEADD(DAY, -8, GETDATE())), null,
  'Abu Bakr', 'Al-Baghdadi', 'daesh@mossad.gov', '+000000000303', 900, '', '', 'checked-in'),
 (10, GETDATE(),  GETDATE(), (SELECT DATEADD(DAY, -2, GETDATE())),
  'Recep', 'Edrogan', 'greekhater@turkiye.gov', '+000000044444', 1200, '', 'AFA223FA', 'checked-in_paid'),
 (3, GETDATE(), (SELECT DATEADD(DAY, 7, GETDATE())), (SELECT DATEADD(DAY, 8, GETDATE())),
  'Ramzan', 'Kadyrov', 'chechenya@russia.gov', '+000000042069', 400, '33312314124', 'AEEEFA2', 'cancelled')