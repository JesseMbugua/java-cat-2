import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * HashUtil.java
 * 
 * Utility class to hash passwords using SHA-256.
 */
public class HashUtil {

    /**
     * Hashes a given password using SHA-256.
     *
     * @param password The plain text password
     * @return The hashed password as a hexadecimal string
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            // Convert byte array into hex string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
