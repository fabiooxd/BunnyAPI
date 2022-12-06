package me.fabio.enums;

public enum Endpoint {

    EU(""),
    US_NY("ny"),
    US_LA("la"),
    AS("sg"),
    AU("syd");

    private final String subDomain;

    Endpoint(String subDomain) {
        this.subDomain = subDomain;
    }

    public String buildUrl() {
        String url = "https://storage.bunnycdn.com";
        if (subDomain.isBlank()) {
            return url;
        } else {
            return subDomain.concat(".").concat(url);
        }
    }
}
