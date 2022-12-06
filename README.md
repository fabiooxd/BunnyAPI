# BunnyCDN Java API

## Usage

Requirements: Java 17 or higher

## Example

```java
public class Main {

    private static final CarrotStorage carrot = new CarrotStorage("key", "zoneName", Endpoint.EU);

    public static Delivery upload(byte[] data, String path, String fileName) {
        Delivery delivery = carrot.upload(data, path, fileName);
        if (!delivery.isSuccessful()) {
            System.out.println(String.format("CODE: %d | MESSAGE: %s", delivery.code(), delivery.message()));
        }

        return delivery;
    }

    public static Delivery download(String path, String fileName) {
        Delivery delivery = carrot.download(path, fileName);
        if (!delivery.isSuccessful()) {
            System.out.println(String.format("CODE: %d | MESSAGE: %s", delivery.code(), delivery.message()));
        }

        return delivery;
    }

    public static void delete(String path, String fileName) {
        Delivery delivery = carrot.delete(path, fileName);
        if (!delivery.isSuccessful()) {
            System.out.println(String.format("CODE: %d | MESSAGE: %s", delivery.code(), delivery.message()));
        }
    }
}
```

## Credits

[Bunny](https://bunny.net/) (BunnyCDN)

[BunnyAPI](https://github.com/BunnyWay/BunnyCDN.Java.Storage) (Original Official BunnyCDN Java API)