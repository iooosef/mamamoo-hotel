CREATE TABLE Rooms (
    room_id INT IDENTITY(1,1) PRIMARY KEY,
    room_num NVARCHAR(6) NOT NULL,
    room_name NVARCHAR(MAX) NOT NULL,
    room_type NVARCHAR(MAX) NOT NULL,
    room_desc NVARCHAR(MAX),
    room_rate_type NVARCHAR(MAX) NOT NULL,
    room_rate MONEY NOT NULL
);

CREATE TABLE RoomPics (
    room_pic_id INT IDENTITY(1,1) PRIMARY KEY,
    room_id INT NOT NULL,
    room_pic_img IMAGE NOT NULL
);

CREATE TABLE RoomTypes (
    room_type_id INT IDENTITY(1,1) PRIMARY KEY,
    room_type_name NVARCHAR(MAX) NOT NULL,
    bkng_fee MONEY NOT NULL
);

CREATE TABLE Amenities (
    amnty_id INT IDENTITY(1,1) PRIMARY KEY,
    amnty_name NVARCHAR(MAX) NOT NULL,
    amnty_desc NVARCHAR(MAX),
    amnty_img IMAGE
);

CREATE TABLE RoomAmenities (
    room_amnty_id INT IDENTITY(1,1) PRIMARY KEY,
    room_id INT NOT NULL,
    amnty_id INT NOT NULL,
    amnty_count INT NOT NULL
);

CREATE TABLE Bookings (
    bkng_id INT IDENTITY(1,1) PRIMARY KEY,
    room_id INT NOT NULL,
    booking_datetime DATETIME NOT NULL,
    check_in_datetime DATETIME NOT NULL,
    check_out_datetime DATETIME,
    guest_given_name NVARCHAR(MAX) NOT NULL,
    guest_last_name NVARCHAR(MAX) NOT NULL,
    email NVARCHAR(MAX),
    contact_num NVARCHAR(15) NOT NULL,
    bkng_fee MONEY NOT NULL,
    senior_pwd NVARCHAR(MAX),
    voucher NVARCHAR(MAX),
    status NVARCHAR(MAX) NOT NULL
);

CREATE TABLE ServiceFees (
    service_id INT IDENTITY(1,1) PRIMARY KEY,
    bkng_id INT NOT NULL,
    service_desc NVARCHAR(MAX) NOT NULL,
    service_cost MONEY NOT NULL
);

CREATE TABLE PenaltyFees (
    penalty_id INT IDENTITY(1,1) PRIMARY KEY,
    bkng_id INT NOT NULL,
    penalty_desc NVARCHAR(MAX) NOT NULL,
    penalty_cost MONEY NOT NULL
);

CREATE TABLE Discounts (
    dscnt_id INT IDENTITY(1,1) PRIMARY KEY,
    dscnt_code NVARCHAR(64) NOT NULL,
    dscnt_amount DECIMAL(12,4) NOT NULL,
    dscnt_count INT NOT NULL,
    dscnt_expiry DATETIME NOT NULL,
    dscnt_dti_permit NVARCHAR(MAX) NOT NULL
);

CREATE TABLE Payments (
    payment_id INT IDENTITY(1,1) PRIMARY KEY,
    bkng_id INT NOT NULL,
    payment_code NVARCHAR(50) NOT NULL,
    payment_datetime DATETIME NOT NULL,
    payment_total_rated_cost MONEY NOT NULL,
    payment_vat MONEY NOT NULL,
    payment_senior_pwd_discount MONEY,
    payment_voucher_discount MONEY,
    payment_total_service_fee MONEY,
    payment_total_amount_due MONEY NOT NULL,
    payment_total_amount_paid MONEY NOT NULL,
    payment_change MONEY NOT NULL,
    payment_refund MONEY NOT NULL,
    payment_tip MONEY
);

CREATE TABLE PaymentDiscounts (
    pay_dscnt_id INT IDENTITY(1,1) PRIMARY KEY,
    payment_id INT NOT NULL,
    dscnt_id INT NOT NULL
);

CREATE TABLE PaymentMethods (
    pay_method_id INT IDENTITY(1,1) PRIMARY KEY,
    pay_method_name NVARCHAR(MAX) NOT NULL
);

CREATE TABLE Memberships (
    member_id INT IDENTITY(1,1) PRIMARY KEY,
    member_code NVARCHAR(16) NOT NULL,
    member_full_name NVARCHAR(MAX) NOT NULL,
    member_contact_number NVARCHAR(MAX) NOT NULL,
    member_email NVARCHAR(MAX) NOT NULL
);