import impl.Connection;

public class Main {
    public static void main(String[] args) throws Exception {
        String request = "http://asymcryptwebservice.appspot.com/rsa/serverKey?keySize=256";
        System.out.println(Connection.doGet(request).get().get("publicExponent"));
    }
}
