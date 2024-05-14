USE HotelAppDB
GO
INSERT INTO RoomPics
(room_id, room_pic_img)
VALUES
(1, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SINGLE BEDROOM _ STANDARD\kingbed1.png', Single_blob) as room_pic_img)),
(1, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SINGLE BEDROOM _ STANDARD\kingbed2.png', Single_blob) as room_pic_img)),
(1, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SINGLE BEDROOM _ STANDARD\kingbed3.png', Single_blob) as room_pic_img)),
(2, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\DOUBLE BED ROOMS _ STANDRAD\2SingleBed1.png', Single_blob) as room_pic_img)),
(2, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\DOUBLE BED ROOMS _ STANDRAD\2SingleBed2.png', Single_blob) as room_pic_img)),
(2, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\DOUBLE BED ROOMS _ STANDRAD\Bathroom1.png', Single_blob) as room_pic_img)),
(3, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SINGLE BEDROOM _ PREMIUM\1KingBed1.png', Single_blob) as room_pic_img)),
(3, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SINGLE BEDROOM _ PREMIUM\1KingBed2.png', Single_blob) as room_pic_img)),
(3, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SINGLE BEDROOM _ PREMIUM\1KingBed3.png', Single_blob) as room_pic_img)),
(3, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SINGLE BEDROOM _ PREMIUM\1KingBed4.png', Single_blob) as room_pic_img)),
(3, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SINGLE BEDROOM _ PREMIUM\Bathroom3.png', Single_blob) as room_pic_img)),

(4, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\DOUBLE BED ROOM _ PREMIUM\2KingBed1.png', Single_blob) as room_pic_img)),
(4, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\DOUBLE BED ROOM _ PREMIUM\2QueenBed1.png', Single_blob) as room_pic_img)),
(4, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\DOUBLE BED ROOM _ PREMIUM\2QueenBed2.png', Single_blob) as room_pic_img)),
(4, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\DOUBLE BED ROOM _ PREMIUM\2QueenBed3.png', Single_blob) as room_pic_img)),
(4, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\DOUBLE BED ROOM _ PREMIUM\Bathroom2.png', Single_blob) as room_pic_img)),
(4, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\DOUBLE BED ROOM _ PREMIUM\PremiumLivingRoomArea.png', Single_blob) as room_pic_img)),

(5, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\DOUBLE BED ROOM _ PREMIUM\2KingBed1.png', Single_blob) as room_pic_img)),
(5, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\DOUBLE BED ROOM _ PREMIUM\2QueenBed1.png', Single_blob) as room_pic_img)),
(5, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\DOUBLE BED ROOM _ PREMIUM\2QueenBed2.png', Single_blob) as room_pic_img)),
(5, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\DOUBLE BED ROOM _ PREMIUM\2QueenBed3.png', Single_blob) as room_pic_img)),
(5, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\DOUBLE BED ROOM _ PREMIUM\Bathroom2.png', Single_blob) as room_pic_img)),
(5, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\DOUBLE BED ROOM _ PREMIUM\PremiumLivingRoomArea.png', Single_blob) as room_pic_img)),


(6, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SINGLE BEDROOM _ STANDARD\kingbed1.png', Single_blob) as room_pic_img)),
(6, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SINGLE BEDROOM _ STANDARD\kingbed2.png', Single_blob) as room_pic_img)),
(6, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SINGLE BEDROOM _ STANDARD\kingbed3.png', Single_blob) as room_pic_img)),
(6, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SUITES\2bedroomsuitelivingroom1.png', Single_blob) as room_pic_img)),
(6, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SUITES\bedroomsuite.png', Single_blob) as room_pic_img)),
(6, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SUITES\suitelivingroom.png', Single_blob) as room_pic_img)),

(7, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SUITES\2bedroomsuite.png', Single_blob) as room_pic_img)),
(7, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SUITES\2bedroomsuite2.png', Single_blob) as room_pic_img)),
(7, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SUITES\2bedroomsuitelivingroom1.png', Single_blob) as room_pic_img)),
(7, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SUITES\bedroomsuite.png', Single_blob) as room_pic_img)),
(7, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SUITES\suitelivingroom.png', Single_blob) as room_pic_img)),

(8, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SINGLE BEDROOM _ STANDARD\kingbed1.png', Single_blob) as room_pic_img)),
(8, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SINGLE BEDROOM _ STANDARD\kingbed2.png', Single_blob) as room_pic_img)),
(8, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SINGLE BEDROOM _ STANDARD\kingbed3.png', Single_blob) as room_pic_img)),

(9, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SINGLE BEDROOM _ STANDARD\kingbed1.png', Single_blob) as room_pic_img)),
(9, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SINGLE BEDROOM _ STANDARD\kingbed2.png', Single_blob) as room_pic_img)),
(9, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\SINGLE BEDROOM _ STANDARD\kingbed3.png', Single_blob) as room_pic_img)),

(10, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\DOUBLE BED ROOMS _ STANDRAD\2SingleBed1.png', Single_blob) as room_pic_img)),
(10, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\DOUBLE BED ROOMS _ STANDRAD\2SingleBed2.png', Single_blob) as room_pic_img)),
(10, (SELECT BulkColumn FROM OPENROWSET(Bulk 'D:\Documents\TIP\ITE 012\FINAL PROJ\HOTEL PICTURES\DOUBLE BED ROOMS _ STANDRAD\Bathroom1.png', Single_blob) as room_pic_img))