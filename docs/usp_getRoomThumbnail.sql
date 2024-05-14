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
ALTER PROCEDURE dbo.usp_getRoomThumbnail
	-- Add the parameters for the stored procedure here
	@RoomNum INT
AS
BEGIN
		-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT TOP 1 rp.room_id, rp.room_pic_img
	FROM HotelAppDB.dbo.RoomPics AS rp
	LEFT JOIN HotelAppDB.dbo.Rooms AS rm
	ON rp.room_id = rm.room_id
	WHERE rp.room_id = @RoomNum
END
GO
