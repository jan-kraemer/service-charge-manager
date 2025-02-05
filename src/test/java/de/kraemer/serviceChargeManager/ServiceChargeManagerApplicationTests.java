package de.kraemer.serviceChargeManager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ServiceChargeManagerApplicationTests {
  private final ApplicationModules modules =
      ApplicationModules.of(ServiceChargeManagerApplication.class);

  @Test
  void contextLoads() {}

  @Test
  void compliesWithModulithRules() {
    this.modules.verify();

    new Documenter(modules).writeModulesAsPlantUml().writeIndividualModulesAsPlantUml();
  }
}
