package impl;

import java.math.BigInteger;

public class Server {
    private Entity entity;

    public Server() {

    }

    static class Entity {
        public BigInteger publicE;
        public BigInteger publicMod;

        public Entity(BigInteger publicE, BigInteger publicMod) {
            this.publicE = publicE;
            this.publicMod = publicMod;
        }
    }

}
