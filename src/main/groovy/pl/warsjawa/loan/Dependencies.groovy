package pl.warsjawa.loan


public enum Dependencies {
    FRED, CLIENTS

    @Override
    String toString() {
        return super.toString().toLowerCase()
    }
}