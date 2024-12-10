public class HammingCode {
    public static int encode(int data) {
        int parity = (data >> 0) ^ (data >> 1) ^ (data >> 2);
        int encoded = (parity << 3) | data;
        return encoded;
    }

    public static int decode(int encoded) {
        int parity = (encoded >> 3) & 1;
        int data = encoded & 0x7;
        int parity_check = (data >> 0) ^ (data >> 1) ^ (data >> 2);
        if (parity_check != parity) {
            System.out.println("Error detected in the data.");
        } else {
            System.out.println("No error detected.");
        }
        return data;
    }

    public static void main(String[] args) {
        int data = 0b101; 
        int encoded = encode(data);
        System.out.println("Encoded Hamming Code: " + Integer.toBinaryString(encoded));
        int decoded = decode(encoded);
        System.out.println("Decoded Data: " + Integer.toBinaryString(decoded));
    }
}

