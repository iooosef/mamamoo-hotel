USE [HotelAppDB]
GO
/****** Object:  StoredProcedure [dbo].[usp_getAllRoomsInfo]    Script Date: 05/13/2024 04:30:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Parayaoan
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[usp_getAllRoomsInfo]
	-- Add the parameters for the stored procedure here
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT rm.room_id,
		   rm.room_num,
		   rm.room_name,
		   rt.room_type_name,
		   rm.room_desc,
		   rm.room_rate_type,
		   rm.room_rate
	FROM HotelAppDB.dbo.Rooms AS rm
	LEFT JOIN HotelAppDB.dbo.RoomTypes AS rt
	ON rm.room_type = rt.room_type_id
END
