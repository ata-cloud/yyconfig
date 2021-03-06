package apollo.internals;

import apollo.exceptions.ApolloConfigException;
import apollo.util.ExceptionUtil;
import framework.apollo.core.enums.ConfigFileFormat;
import framework.apollo.core.utils.PropertiesUtil;
import framework.apollo.tracer.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
public class PropertiesConfigFile extends AbstractConfigFile {
  private static final Logger logger = LoggerFactory.getLogger(PropertiesConfigFile.class);
  protected AtomicReference<String> m_contentCache;

  public PropertiesConfigFile(String namespace,
                              ConfigRepository configRepository) {
    super(namespace, configRepository);
    m_contentCache = new AtomicReference<>();
  }

  @Override
  protected void update(Properties newProperties) {
    m_configProperties.set(newProperties);
    m_contentCache.set(null);
  }

  @Override
  public String getContent() {
    if (m_contentCache.get() == null) {
      m_contentCache.set(doGetContent());
    }
    return m_contentCache.get();
  }

  String doGetContent() {
    if (!this.hasContent()) {
      return null;
    }

    try {
      return PropertiesUtil.toString(m_configProperties.get());
    } catch (Throwable ex) {
      ApolloConfigException exception =
          new ApolloConfigException(String
              .format("Parse properties file content failed for appNamespace: %s, cause: %s",
                  m_namespace, ExceptionUtil.getDetailMessage(ex)));
      Tracer.logError(exception);
      throw exception;
    }
  }

  @Override
  public boolean hasContent() {
    return m_configProperties.get() != null && !m_configProperties.get().isEmpty();
  }

  @Override
  public ConfigFileFormat getConfigFileFormat() {
    return ConfigFileFormat.Properties;
  }

}
