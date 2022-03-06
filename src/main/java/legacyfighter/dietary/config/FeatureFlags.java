package legacyfighter.dietary.config;

import jdk.jfr.Label;
import org.togglz.core.Feature;
import org.togglz.core.context.FeatureContext;

public enum FeatureFlags implements Feature {

    @Label("Eager tax config view")
    EAGER_TAX_CONFIG_VIEW;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }

}
