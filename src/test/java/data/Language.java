package data;

public enum Language {
    LV("Latviešu"),
    RU("Русский"),
    EN("English");

    private final String displayName;

    Language(String displayName) {
        this.displayName = displayName;
    }

    public String getLanguage() {
        return displayName;
    }

}