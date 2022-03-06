package legacyfighter.dietary;

import legacyfighter.dietary.taxconfigview.TaxConfigViewCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@RestController
public class TaxConfigController {

    @Autowired
    private TaxConfigViewCreator taxConfigViewCreator;

    @GetMapping("/config")
    @Transactional
    public Map<String, List<TaxRule>> taxConfigs() {
        return taxConfigViewCreator.create();
    }

}
