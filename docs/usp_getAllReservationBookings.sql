USE [HotelAppDB]
GO
/****** Object:  StoredProcedure [dbo].[usp_getAllNonReservationBookings]    Script Date: 05/16/2024 07:31:10 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Parayaoan
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[usp_getAllReservationBookings]
	-- Add the parameters for the stored procedure here
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT bk.bkng_id,
		   bk.room_id,
		   bk.booking_datetime,
		   bk.check_in_datetime,
		   bk.check_out_datetime,
		   bk.guest_given_name,
		   bk.guest_last_name,
		   bk.contact_num,
		   bk.status
	FROM HotelAppDB.dbo.Bookings AS bk
	WHERE bk.status = 'reserved' OR bk.status = 'reserved_paid'
END
