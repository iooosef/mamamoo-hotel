-- ================================================
-- Template generated from Template Explorer using:
-- Create Procedure (New Menu).SQL
--
-- Use the Specify Values for Template Parameters
-- command (Ctrl-Shift-M) to fill in the parameter
-- values below.
--
-- This block of comments will not be included in
-- the definition of the procedure.
-- ================================================
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[usp_insPayment]
	-- Add the parameters for the stored procedure here
	@bkng_id INT,
	@check_out DATETIME,
	@total_rated_cost MONEY,
	@vat MONEY,
	@senior_pwd_discount MONEY,
	@voucher_discount MONEY,
	@total_service_fee MONEY,
	@total_amount_due MONEY,
    @total_amount_paid MONEY,
    @refund MONEY = NULL,
    @total_tip MONEY = NULL
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
    DECLARE @bkng_fee MONEY = ( SELECT bk.bkng_fee FROM HotelAppDB.dbo.Bookings AS bk LEFT JOIN HotelAppDB.dbo.Rooms AS rm ON bk.room_id = rm.room_id WHERE bk.bkng_id = @bkng_id )

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
	 payment_refund,
	 payment_tip)
	 VALUES
	 (@bkng_id,
	  @Code,
	  GETDATE(),
	  @total_rated_cost,
	  @vat,
	  @senior_pwd_discount,
	  @voucher_discount,
	  @total_service_fee,
	  @total_amount_due,
	  @total_amount_paid,
	  @total_amount_paid - @total_amount_due,
	  @refund,
	  @total_tip
	 )

	 SELECT @room_num AS room_num,
			@guest_name AS guest_name,
			@check_in AS check_in,
			@check_out AS check_out,
			@bkng_fee AS reservation_fee,
			pm.*
	 FROM HotelAppDB.dbo.Payments AS pm
	 WHERE pm.bkng_id = @bkng_id
END
GO
