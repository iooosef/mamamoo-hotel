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
ALTER PROCEDURE dbo.usp_getAllAmenitiesInRoom
	-- Add the parameters for the stored procedure here
	@RoomNum INT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT ra.room_id, am.amnty_name, ra.amnty_count, am.amnty_desc
	FROM HotelAppDB.dbo.RoomAmenities AS ra
	LEFT JOIN HotelAppDB.dbo.Amenities AS am
	ON ra.amnty_id = am.amnty_id
	WHERE ra.room_id = @RoomNum
END
GO
