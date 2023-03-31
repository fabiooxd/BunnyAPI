# BunnyCDN Java API

## Usage

Requirements: Java 17 or higher

## Example

```java
public class Main {

    private static final CarrotStorage carrot = new CarrotStorage("key", "zoneName", Endpoint.EU);

    public static Delivery upload(byte[] data, String path, String fileName) {
        Delivery delivery = carrot.upload(data, path, fileName);
        handleError(delivery);

        return delivery;
    }

    public static Delivery download(String path, String fileName) {
        Delivery delivery = carrot.download(path, fileName);
        handleError(delivery);

        return delivery;
    }

    public static void delete(String path, String fileName) {
        Delivery delivery = carrot.delete(path, fileName);
        handleError(delivery);
    }

    private static void handleError(Delivery delivery) {
        if (!delivery.isSuccessful()) {
            final String message = JsonHelper.deserialize(delivery.message(), JsonObject.class).get("message").getAsString();
            Logger.error(String.format("CODE: %d (%s", delivery.code(), message.toUpperCase() + ")"));
        }
    }
}
```

## Credits

[Bunny](https://bunny.net/) (BunnyCDN)

[BunnyAPI](https://github.com/BunnyWay/BunnyCDN.Java.Storage) (Original Official BunnyCDN Java API)

![YourKit](https://www.yourkit.com/images/yklogo.png)
-----
YourKit supports open source projects with innovative and intelligent tools
for monitoring and profiling Java and .NET applications.
YourKit is the creator of <a href="https://www.yourkit.com/java/profiler/">YourKit Java Profiler</a>,
<a href="https://www.yourkit.com/dotnet-profiler/">YourKit .NET Profiler</a>,
and <a href="https://www.yourkit.com/youmonitor/">YourKit YouMonitor</a>.
