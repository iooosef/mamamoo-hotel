USE [HotelAppDB]
GO
/****** Object:  StoredProcedure [dbo].[usp_checkForConflict]    Script Date: 05/16/2024 15:14:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[usp_checkForConflict]
	-- Add the parameters for the stored procedure here
	@room_id INT,
	@check_in DATETIME,
	@check_out DATETIME = null
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	
	IF (@check_out = null)
	BEGIN
		SET @check_out = DATEADD(HOUR, 36, @check_in)
	END

    -- Insert statements for procedure here
	IF EXISTS ( 
	  SELECT 1 
	  FROM HotelAppDB.dbo.Bookings
	  WHERE room_id = @room_id
		AND (
		  -- Using ISNULL to replace NULL check_out_datetime with 36 hours from check_in_datetime
		  ((@check_in < ISNULL(check_out_datetime, DATEADD(HOUR, 36, check_in_datetime)) AND @check_in > check_in_datetime) OR
		   (@check_out > check_in_datetime AND @check_out < ISNULL(check_out_datetime, DATEADD(HOUR, 36, check_in_datetime))))
		)
	)
	BEGIN
		SELECT 1 AS 'hasConflict'
	END
	ELSE
	BEGIN
		SELECT 0 AS 'hasConflict'
	END
	
	
END
