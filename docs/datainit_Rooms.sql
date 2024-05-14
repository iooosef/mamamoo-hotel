USE HotelAppDB
GO
INSERT INTO dbo.Rooms
(room_num, room_name, room_type, room_desc, room_rate_type, room_rate)
VALUES
('001', 'Single Bed Standard',1,'16 Sq m room with city view, bath and shower combination, and mini-fridge','hour', 350),
('002', 'Double Bed Standard',1,'32 Sq m room with city view, bath and shower combination, and mini-fridge','hour', 550),
('003', 'Single Bed Premium',2,'Reenergize in a 20 Sq m with 1 king-sized bed guestroom which features amenities to complete your stay. Enjoy soft duck down duvets and a bathroom equipped with adjustable power showers, a 32-inch LED cable TV, internet access and complimentary tea and coffee at the mini bar.','night',2200),
('004', 'Double Bed Premium',2,'Reenergize in a 44 Sq m with 1 king-sized bed guestroom which features amenities to complete your stay. Enjoy soft duck down duvets and a bathroom equipped with adjustable power showers, a 32-inch LED cable TV, internet access and complimentary tea and coffee at the mini bar.','night',5000),
('005', 'Double Bed Premium',2,'Boasting a generous 48 Sq m space with 2 queen-sized beds for guests or families on vacation, the room is provided with duck-down duvets and a selection of soft and firm pillows. This room is equipped with adjustable power showers, a 32-inch LED cable TV, minibar with complimentary coffee and tea.','night',6000),
('006', 'Single Bed Standard',1,'16 Sq m room with city view, bath and shower combination, and mini-fridge','hour', 350),
('007', 'Single Bed Standard',1,'16 Sq m room with city view, bath and shower combination, and mini-fridge','hour', 350),
('008', 'Double Bed Standard',1,'32 Sq m room with city view, bath and shower combination, and mini-fridge','hour', 550),
('022', 'Single King Suite',3,'Unwind in a spacious 68 Sq m suite complete with an array of amenities and enjoy the comforts of a separate bedroom and receiving area. This suite comes with a 32-inch LCD TV, complimentary coffee and tea, a mini bar, wireless internet, & a large bathroom with a bathtub & shower.','night',10000),
('024', 'Double Queens Suite',3,'Complete with top-of-the-line amenities, this 116 Sq m suite has two rooms with king-sized beds, a receiving lounge with a bar area, bathrooms with adjustable power showers, 32-inch LED cable TV, a complete mini bar, complimentary coffee and tea, and wireless internet access.','night',16000)