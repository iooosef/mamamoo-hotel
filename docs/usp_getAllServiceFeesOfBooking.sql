USE [HotelAppDB]
GO
/****** Object:  StoredProcedure [dbo].[usp_getTotalServiceFeesOfBooking]    Script Date: 05/15/2024 20:20:28 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[usp_getAllServiceFeesOfBooking]
	-- Add the parameters for the stored procedure here
	@BKNG_ID int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	DECLARE @room_num NVARCHAR(MAX) = (SELECT rm.room_num
										FROM HotelAppDB.dbo.Bookings AS bk
										LEFT JOIN HotelAppDB.dbo.Rooms AS rm
										ON bk.room_id = rm.room_id
										WHERE bk.bkng_id = @BKNG_ID
										)

    -- Insert statements for procedure here
	SELECT sf.bkng_id, @room_num AS room_num, sf.service_desc, sf.service_cost
	FROM HotelAppDB.dbo.ServiceFees AS sf
	LEFT JOIN HotelAppDB.dbo.Bookings AS bk
	ON sf.bkng_id = bk.bkng_id
	WHERE sf.bkng_id = @BKNG_ID
END
