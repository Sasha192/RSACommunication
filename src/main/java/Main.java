import algorithms.GenerateRandom;
import impl.Client;
import impl.Server;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(256);
        Client client = new Client();
        String hexMsg = GenerateRandom.generate(BigInteger.ZERO, server.getEntity().publicMod).toString(16);
        String stg = client.signMessage(hexMsg, 16);
        Boolean o = server.verifySignMessage(hexMsg, stg, client.getPublicE(16), client.getMod(16));
        System.out.println(o);
    }
}
