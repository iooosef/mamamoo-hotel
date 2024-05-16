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
-- Author:		Parayaoan
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE dbo.getRoomRateTypeOfBooking
	-- Add the parameters for the stored procedure here
	@BKNG_ID int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT rm.room_rate_type
	FROM HotelAppDB.dbo.Rooms AS rm
	LEFT JOIN HotelAppDB.dbo.Bookings AS bk
	ON rm.room_id = bk.room_id
	WHERE bk.bkng_id = @BKNG_ID
END
GO
