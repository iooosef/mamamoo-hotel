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
ALTER PROCEDURE dbo.usp_getBookingsOfRoom
	-- Add the parameters for the stored procedure here
	@RoomNum INT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT bk.bkng_id, bk.check_in_datetime, bk.check_out_datetime, bk.status
	FROM HotelAppDB.dbo.Bookings AS bk
	LEFT JOIN HotelAppDB.dbo.Rooms AS rm
	ON bk.room_id = rm.room_id
	WHERE (bk.status <> 'cancelled' OR bk.status <> 'paid') AND
		  bk.room_id = @RoomNum
END
GO
