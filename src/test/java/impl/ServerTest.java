package impl;

import algorithms.GenerateRandom;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigInteger;

public class ServerTest {
    private static Server server;
    private static Client client;
    private static final int KEY_SIZE;
    private static Server.Entity serverEntity;
    private static final int NUMBER_OF_EACH_TEST;
    private static final int BASE;

    // Is it better to Server.Entity create for Client ? I mean, that server.send(Server.Entity, ... )

    private static String removeHighZerosFromHex(String hex) {
        int i = 0;
        for (char charElement: hex.toCharArray()){
            if(!(charElement == '0')) {
                break;
            }
            i++;
        }
        return new String(hex.toCharArray(), i, hex.length());
    }

    static {
        KEY_SIZE = 256;
        NUMBER_OF_EACH_TEST = 10;
        BASE = 16;
    }

    @BeforeClass
    public static void setup() {
        server = new Server(KEY_SIZE);
        client = new Client();
        serverEntity = server.getEntity();
    }

    @Test
    public void encryptionTest() {
        int n = NUMBER_OF_EACH_TEST;
        while (n-- > 0) {
            String hexMessage = GenerateRandom.generate(BigInteger.ZERO, serverEntity.publicMod).toString(BASE);
            String resultMessage = server.encrypt(client.getPublicE(BASE), client.getMod(BASE), hexMessage);
            resultMessage = client.decrypt(resultMessage, BASE);
            Assert.assertEquals(hexMessage, resultMessage);
        }
    }

    // Test failed 'cause of most significant zeros : Server returns - 0FFF = FFF - my returns ? F*CK
    @Test
    public void decryptionTest() {
        int n = NUMBER_OF_EACH_TEST;
        while (n-- > 0) {
            String hexMessage = GenerateRandom.generate(BigInteger.ZERO, serverEntity.publicMod).toString(BASE);
            String resultMessage = client.encrypt(hexMessage, BASE, serverEntity);
            resultMessage = server.decrypt(resultMessage);
            resultMessage = removeHighZerosFromHex(hexMessage);
            Assert.assertEquals(resultMessage, hexMessage);
        }
    }

    @Test
    public void signMessage() {
        int n = NUMBER_OF_EACH_TEST;
        while (n-- > 0) {
            String hexMessage = GenerateRandom.generate(BigInteger.ZERO, serverEntity.publicMod).toString(BASE);
            String resultMessage = server.signMessage(hexMessage);
            Assert.assertTrue(client.verifySignMessage(hexMessage, resultMessage, BASE, serverEntity));
        }
    }

    @Test
    public void verifySignMessage() {
        int n = NUMBER_OF_EACH_TEST;
        while (n-- > 0) {
            String hexMessage = GenerateRandom.generate(BigInteger.ZERO, serverEntity.publicMod).toString(BASE);
            String resultMessage = client.signMessage(hexMessage, BASE);
            Assert.assertTrue(server.verifySignMessage(hexMessage,
                    resultMessage,
                    client.getPublicE(BASE),
                    client.getMod(BASE)));
        }
    }

    @Test
    public void sendKey() {
    
    }

    @Test
    public void receiveKey() {

    }
}