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
CREATE PROCEDURE dbo.usp_updPayment
	-- Add the parameters for the stored procedure here
	@bkng_id INT,
	@serviceFeesTotal MONEY,
	@newPayment MONEY
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	UPDATE HotelAppDB.dbo.Payments
	SET payment_total_service_fee = @serviceFeesTotal,
		payment_total_amount_due = payment_total_amount_due + payment_total_service_fee,
		payment_total_amount_paid = payment_total_amount_paid + @newPayment
	WHERE bkng_id = @bkng_id
END
GO
