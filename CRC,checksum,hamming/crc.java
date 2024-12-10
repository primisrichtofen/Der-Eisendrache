import java.util.zip.CRC32;

public class CRC {
    public static long calculateCRC(byte[] data) {
        CRC32 crc32 = new CRC32();
        crc32.update(data);
        return crc32.getValue();
    }

    public static void main(String[] args) {
        byte[] data = "Hello, World!".getBytes();
        long crc = calculateCRC(data);
        System.out.println("CRC32: " + crc);
    }
}

