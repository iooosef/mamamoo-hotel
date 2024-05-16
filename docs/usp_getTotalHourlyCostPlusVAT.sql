USE [HotelAppDB]
GO
/****** Object:  StoredProcedure [dbo].[usp_getTotalHourlyCostPlusVAT]    Script Date: 05/15/2024 21:35:18 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Parayaoan
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[usp_getTotalHourlyCostPlusVAT]
	-- Add the parameters for the stored procedure here
	@BKNG_ID INT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	DECLARE @check_out_datetime DATETIME = (SELECT TOP 1 check_out_datetime FROM HotelAppDB.dbo.Bookings WHERE bkng_id = @BKNG_ID)

    IF (SELECT TOP 1 check_out_datetime FROM HotelAppDB.dbo.Bookings WHERE bkng_id = @BKNG_ID) IS null
		SET @check_out_datetime = GETDATE();

	DECLARE @duration_hours DECIMAL(10,2)
	SET @duration_hours =
		DATEDIFF(MINUTE,
		(SELECT TOP 1 check_in_datetime FROM HotelAppDB.dbo.Bookings WHERE bkng_id = @BKNG_ID),
		@check_out_datetime)
		/ 60.0;

	DECLARE @IsSeniorPwdEmpty INT
	SELECT @IsSeniorPwdEmpty =
		CASE
			WHEN ISNULL(bk.senior_pwd, '') = '' THEN 0
			ELSE 1
		END
	FROM HotelAppDB.dbo.Bookings AS bk
	WHERE bk.bkng_id = @BKNG_ID;

	DECLARE @IsVoucherEmpty INT
	SELECT @IsVoucherEmpty =
		CASE
			WHEN ISNULL(bk.voucher, '') = '' THEN 0
			ELSE 1
		END
	FROM HotelAppDB.dbo.Bookings AS bk
	WHERE bk.bkng_id = @BKNG_ID;

	SELECT bk.bkng_id, rm.room_num, @check_out_datetime as 'check_out_datetime',
		   (@duration_hours * rm.room_rate)*0.20*-1*@IsSeniorPwdEmpty AS senior_pwd_discount,
		   (@duration_hours * rm.room_rate)*.05*-1*@IsVoucherEmpty AS voucher_discount,
		   (@duration_hours * rm.room_rate) AS total_cost,
		   (@duration_hours * rm.room_rate)*0.12 AS vat
	FROM HotelAppDB.dbo.Bookings AS bk
	LEFT JOIN HotelAppDB.dbo.Rooms AS rm
	ON bk.room_id = rm.room_id
	WHERE bk.bkng_id = @BKNG_ID
END
