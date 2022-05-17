interface MenuState {
    void handle(MenuContext context, String action);
    String printMenuOptions();
}