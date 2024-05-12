package ph.edu.tip.mamamoo.Models;

import ph.edu.tip.mamamoo.Components.NavButton;

public class NavButtonsModel {
    public NavButton homeButton;
    public NavButton roomsButton;
    public NavButton bookingsButton;
    public NavButton roomServiceButton;

    public NavButtonsModel() {}

    public NavButtonsModel(NavButton homeButton, NavButton roomsButton, NavButton bookingsButton, NavButton guestsButton){
        this.homeButton = homeButton;
        this.roomsButton = roomsButton;
        this.bookingsButton = bookingsButton;
    }

    public NavButton getHomeButton() {
        return homeButton;
    }
    public NavButton getRoomsButton() {
        return roomsButton;
    }
    public NavButton getBookingsButton() {
        return bookingsButton;
    }
    public NavButton getRoomServiceButton() {
        return roomServiceButton;
    }

    public void setHomeButton(NavButton homeButton) {
        this.homeButton = homeButton;
    }
    public void setRoomsButton(NavButton roomsButton) {
        this.roomsButton = roomsButton;
    }
    public void setBookingsButton(NavButton bookingsButton) {
        this.bookingsButton = bookingsButton;
    }
    public void setRoomServiceButton(NavButton roomServiceButton) {
        this.roomServiceButton = roomServiceButton;
    }
}
