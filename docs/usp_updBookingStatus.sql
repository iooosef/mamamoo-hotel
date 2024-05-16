USE [HotelAppDB]
GO
/****** Object:  StoredProcedure [dbo].[usp_updBookingStatus]    Script Date: 05/16/2024 07:07:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[usp_updBookingStatus]
	-- Add the parameters for the stored procedure here
	@bkng_id INT,
	@status NVARCHAR(MAX)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;


	DECLARE @current_status NVARCHAR(max) = (SELECT status FROM HotelAppDB.dbo.Bookings WHERE bkng_id = 5)
	DECLARE @is_checkout_null INT = (
		SELECT 
			CASE 
				WHEN EXISTS (SELECT 0 FROM HotelAppDB.dbo.Bookings WHERE bkng_id = 5 AND check_out_datetime IS NOT NULL) THEN 1
				ELSE 1
			END)
			
	IF (@current_status <> 'paid' AND @status = 'paid' AND @is_checkout_null = 1)
	BEGIN
		UPDATE HotelAppDB.dbo.Bookings
		SET check_out_datetime = GETDATE()
		WHERE bkng_id = @bkng_id
	END	

    -- Insert statements for procedure here
	UPDATE HotelAppDB.dbo.Bookings
	SET status = @status
	WHERE bkng_id = @bkng_id
END
SELECT @current_status, @is_checkout_null