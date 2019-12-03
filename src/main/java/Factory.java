import algorithms.Decomposition;
import algorithms.Euclid;
import algorithms.GenerateRandom;
import algorithms.PrimarilyTest;

public class Factory {
    private Decomposition decomposition;
    private Euclid euclid;
    private PrimarilyTest primarilyTest;
    private GenerateRandom generateRandom;

    public Decomposition getDecomposition() {
        if (decomposition == null) {
            decomposition = new Decomposition();
        }
        return decomposition;
    }

    public Euclid getEuclid() {
        if (euclid == null) {
            euclid = new Euclid();
        }
        return euclid;
    }

    public PrimarilyTest getPrimarilyTest() {
        if (primarilyTest == null) {
            primarilyTest = new PrimarilyTest();
        }
        return primarilyTest;
    }

    public GenerateRandom getGenerateRandom() {
        if (generateRandom == null) {
            generateRandom = new GenerateRandom();
        }
        return generateRandom;
    }
}
