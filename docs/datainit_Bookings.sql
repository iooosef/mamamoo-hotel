USE HotelAppDB
GO
INSERT INTO Bookings
(room_id,
 booking_datetime,
 check_in_datetime,
 check_out_datetime,
 guest_given_name,
 guest_last_name,
 contact_num,
 bkng_fee,
 status
 )
 VALUES
 (1, GETDATE(), (SELECT DATEADD(DAY, 4, GETDATE())), (SELECT DATEADD(DAY, 5, GETDATE())),
  'Bashar', 'Al-Assad', '+000000000001', 200, 'reserved'),
 (6, GETDATE(), (SELECT DATEADD(DAY, 5, GETDATE())), (SELECT DATEADD(DAY, 6, GETDATE())),
  'Osama', 'Bin Laden', '+000000000069', 1200, 'reserved'),
 (1, GETDATE(), (SELECT DATEADD(DAY, 7, GETDATE())), (SELECT DATEADD(DAY, 8, GETDATE())),
  'Muammar', 'Gadaffi', '+000000000420', 200, 'reserved')