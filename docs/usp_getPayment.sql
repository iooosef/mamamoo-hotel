USE [HotelAppDB]
GO
/****** Object:  StoredProcedure [dbo].[usp_getPayment]    Script Date: 05/15/2024 23:43:44 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[usp_getPayment]
	-- Add the parameters for the stored procedure here
	@bkng_id INT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

	DECLARE @RandomString VARCHAR(12)
	SELECT @RandomString =
		REPLACE(
			REPLACE(
				SUBSTRING(CONVERT(VARCHAR(255), NEWID()), 3, 12) +
				SUBSTRING(CONVERT(VARCHAR(255), NEWID()), 3, 12),
			'-', ''),
		'-', '')
	DECLARE @Code NVARCHAR(12) = (SELECT LEFT(@RandomString, 12) AS RandomString)

    DECLARE @room_num NVARCHAR(MAX) = ( SELECT rm.room_num FROM HotelAppDB.dbo.Bookings AS bk LEFT JOIN HotelAppDB.dbo.Rooms AS rm ON bk.room_id = rm.room_id WHERE bk.bkng_id = @bkng_id )
    DECLARE @guest_name NVARCHAR(MAX) = ( SELECT (bk.guest_given_name + ' ' + bk.guest_last_name) FROM HotelAppDB.dbo.Bookings AS bk LEFT JOIN HotelAppDB.dbo.Rooms AS rm ON bk.room_id = rm.room_id WHERE bk.bkng_id = @bkng_id )
    DECLARE @check_in DATETIME = ( SELECT bk.check_in_datetime FROM HotelAppDB.dbo.Bookings AS bk LEFT JOIN HotelAppDB.dbo.Rooms AS rm ON bk.room_id = rm.room_id WHERE bk.bkng_id = @bkng_id )
    DECLARE @check_out DATETIME = ( SELECT bk.check_out_datetime FROM HotelAppDB.dbo.Bookings AS bk LEFT JOIN HotelAppDB.dbo.Rooms AS rm ON bk.room_id = rm.room_id WHERE bk.bkng_id = @bkng_id )
    DECLARE @bkng_fee MONEY = ( SELECT bk.bkng_fee FROM HotelAppDB.dbo.Bookings AS bk LEFT JOIN HotelAppDB.dbo.Rooms AS rm ON bk.room_id = rm.room_id WHERE bk.bkng_id = @bkng_id )


	 SELECT @room_num AS room_num,
			@guest_name AS guest_name,
			@check_in AS check_in,
			@check_out AS check_out,
			@bkng_fee AS reservation_fee,
			pm.*
	 FROM HotelAppDB.dbo.Payments AS pm
	 WHERE pm.bkng_id = @bkng_id
END
