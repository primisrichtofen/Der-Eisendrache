public class Checksum {
    public static int calculateChecksum(byte[] data) {
        int sum = 0;
        for (byte b : data) {
            sum += b;
        }
        return sum % 256;
    }

    public static void main(String[] args) {
        byte[] data = { 10, 20, 30, 40 }; 
        int checksum = calculateChecksum(data);
        System.out.println("Checksum: " + checksum);
    }
}

