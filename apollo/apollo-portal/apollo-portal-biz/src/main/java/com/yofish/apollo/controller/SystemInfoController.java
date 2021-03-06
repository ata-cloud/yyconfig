package com.yofish.apollo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/system-info")
public class SystemInfoController {

  private static final Logger logger = LoggerFactory.getLogger(SystemInfoController.class);
  private static final String CONFIG_SERVICE_URL_PATH = "/META-INF/services/config";
  private static final String ADMIN_SERVICE_URL_PATH = "/META-INF/services/admin";

  private RestTemplate restTemplate;
/*

  @Autowired
  private PortalSettings portalSettings;

  @Autowired
  private RestTemplateFactory restTemplateFactory;

  @PostConstruct
  private void init() {
    restTemplate = restTemplateFactory.getObject();
  }

  @PreAuthorize(value = "@permissionValidator.isSuperAdmin()")
  @RequestMapping(method = RequestMethod.GET)
  public SystemInfo getSystemInfo() {
    SystemInfo systemInfo = new SystemInfo();

    String version = Apollo.VERSION;
    if (isValidVersion(version)) {
      systemInfo.setVersion(version);
    }

    List<Env> allEnvList = portalSettings.getAllEnvs();

    for (Env env : allEnvList) {
      EnvironmentInfo environmentInfo = new EnvironmentInfo();
      String metaServerAddresses = MetaDomainConsts.getMetaServerAddress(env);

      environmentInfo.setEnv(env);
      environmentInfo.setActive(portalSettings.isEnvActive(env));
      environmentInfo.setMetaServerAddress(metaServerAddresses);

      String selectedMetaServerAddress = MetaDomainConsts.getDomain(env);
      try {
        environmentInfo.setConfigServices(getServerAddress(selectedMetaServerAddress, CONFIG_SERVICE_URL_PATH));

        environmentInfo.setAdminServices(getServerAddress(selectedMetaServerAddress, ADMIN_SERVICE_URL_PATH));
      } catch (Throwable ex) {
        String errorMessage = "Loading config/admin services from meta server: " + selectedMetaServerAddress + " failed!";
        logger.error(errorMessage, ex);
        environmentInfo.setErrorMessage(errorMessage + " Exception: " + ex.getMessage());
      }

      systemInfo.addEnvironment(environmentInfo);
    }

    return systemInfo;
  }

  @PreAuthorize(value = "@permissionValidator.isSuperAdmin()")
  @RequestMapping(value = "/health", method = RequestMethod.GET)
  public Health checkHealth(@RequestParam String host) {
    return restTemplate.getForObject(host + "/health", Health.class);
  }

  private ServiceDTO[] getServerAddress(String metaServerAddress, String path) {
    String url = metaServerAddress + path;
    return restTemplate.getForObject(url, ServiceDTO[].class);
  }
*/

  private boolean isValidVersion(String version) {
    return !version.equals("java-null");
  }
}
