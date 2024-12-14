import ge.tbc.testautomation.data.Constants;
import org.testng.annotations.Factory;

public class ParametrizedSwoopFactory {
    @Factory
    public Object[] createInstances() {
        return new Object[]{
                new ParametrizedSwoopTests2(Constants.CATEGORY_1),
                new ParametrizedSwoopTests2(Constants.CATEGORY_2),
                new ParametrizedSwoopTests2(Constants.CATEGORY_3)
        };
    }
}
