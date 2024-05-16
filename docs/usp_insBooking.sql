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
CREATE PROCEDURE dbo.usp_insBooking
	-- Add the parameters for the stored procedure here
	@room_id INT,
	@check_in DATETIME,
	@check_out DATETIME = null,
	@fname NVARCHAR(MAX),
	@lname NVARCHAR(MAX),
	@email NVARCHAR(MAX) = null,
	@contact_num NVARCHAR(MAX),
	@senior_pwd NVARCHAR(MAX) = null,
	@voucher NVARCHAR(MAX) = null,
	@status NVARCHAR(MAX)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	INSERT INTO HotelAppDB.dbo.Bookings
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
	(@room_id,
	GETDATE(),
	@check_in,
	@check_out,
	@fname,
	@lname,
	@email,
	@contact_num,
	(SELECT bkng_fee FROM HotelAppDB.dbo.RoomTypes WHERE room_type_id = @room_id),
	@senior_pwd,
	@voucher,
	@status)
END
GO
